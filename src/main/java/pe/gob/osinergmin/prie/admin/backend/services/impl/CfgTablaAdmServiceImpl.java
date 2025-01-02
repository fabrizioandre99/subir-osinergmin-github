package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.common.ForeignKeyRelation;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.domain.CfgCampoAdm;
import pe.gob.osinergmin.prie.admin.backend.domain.CfgTablaAdm;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tabla.CfgTablaAdmDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.CfgCampoAdmMapper;
import pe.gob.osinergmin.prie.admin.backend.mapper.CfgTablaAdmMapper;
import pe.gob.osinergmin.prie.admin.backend.services.CfgTablaAdmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CfgTablaAdmServiceImpl implements CfgTablaAdmService {

    private static final Logger logger = LoggerFactory.getLogger(CfgTablaAdmServiceImpl.class);


    @Autowired
    private CfgTablaAdmMapper cfgTablaAdmMapper;

    @Autowired
    private CfgCampoAdmMapper cfgCampoAdmMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public int deleteByPrimaryKey(String codTabla) {
        return cfgTablaAdmMapper.deleteByPrimaryKey(codTabla);
    }

    @Override
    public int insert(CfgTablaAdm record) {
        return cfgTablaAdmMapper.insert(record);
    }

    @Override
    public int insertSelective(CfgTablaAdm record) {
        return cfgTablaAdmMapper.insertSelective(record);
    }

    @Override
    public CfgTablaAdm selectByPrimaryKey(String codTabla) {
        return cfgTablaAdmMapper.selectByPrimaryKey(codTabla);
    }

    @Override
    public int updateByPrimaryKeySelective(CfgTablaAdm record) {
        return cfgTablaAdmMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CfgTablaAdm record) {
        return cfgTablaAdmMapper.updateByPrimaryKey(record);
    }

    @Override
    public Map<String, Object> listarDatos(String codTabla) {

        List<CfgCampoAdm> camposTabla = cfgCampoAdmMapper.getCamposByTabla(codTabla);

        Map<String, List<Map<String, Object>>> opcionesSelectMap = new HashMap<>();

        for (CfgCampoAdm campo : camposTabla) {
            if ("select".equalsIgnoreCase(campo.getType())) {
                String queryDominio = campo.getDominio();

                if (queryDominio != null && !queryDominio.trim().isEmpty()) {
                    try {
                        List<Map<String, Object>> opcionesSelect = cfgTablaAdmMapper.ejecutarConsultaDinamica(queryDominio);

                        opcionesSelectMap.put(campo.getCodCampo(), opcionesSelect);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("Error al ejecutar la consulta de dominio: " + queryDominio, e);
                    }
                }
            }
        }

        Map<String, Object> resultadoFinal = new HashMap<>();
        resultadoFinal.put("selectOptions", opcionesSelectMap);

        return resultadoFinal;
    }

    @Transactional
    @Override
    public void insertarDatosDinamicos(String codTabla, Map<String, Object> parametros) {
        String usuarioActual = "admin";
        String terminalActual = IpUtils.getClientIp(request);
        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());

        String[] schemaAndTable = splitSchemaAndTable(codTabla);
        String schema = schemaAndTable[0];
        String tableName = schemaAndTable[1];

        List<Map<String, Object>> camposTabla = cfgCampoAdmMapper.getCamposByAllTabColumns(schema, tableName);

        List<String> camposFecha = camposTabla.stream()
                .filter(campo -> campo.get("DATA_TYPE").toString().equalsIgnoreCase("DATE"))
                .map(campo -> campo.get("COLUMN_NAME").toString())
                .collect(Collectors.toList());

        for (String campoFecha : camposFecha) {
            if (parametros.containsKey(campoFecha) && parametros.get(campoFecha) instanceof String) {
                String fechaString = (String) parametros.get(campoFecha);
                try {
                    Date fecha = parseDate(fechaString);
                    parametros.put(campoFecha, new java.sql.Date(fecha.getTime()));
                } catch (ParseException e) {
                    throw new RuntimeException("Formato de fecha incorrecto para el campo " + campoFecha + ". Se esperaba formato 'yyyy-MM-dd', 'MM-dd-yyyy' o 'dd-MM-yyyy'");
                }
            }
        }

        List<CfgCampoAdm> primaryKeyFields = cfgCampoAdmMapper.getPKs(codTabla);

        if (primaryKeyFields.isEmpty()) {
            throw new RuntimeException("La tabla " + codTabla + " no tiene definida una clave primaria.");
        }

        Map<String, Object> primaryKeyValues = new HashMap<>();
        for (CfgCampoAdm pkField : primaryKeyFields) {
            String pk = pkField.getCodCampo();
            if (!parametros.containsKey(pk) || parametros.get(pk) == null || parametros.get(pk).toString().trim().isEmpty()) {
                throw new RuntimeException("El campo clave primaria " + pk + " es obligatorio.");
            }
            primaryKeyValues.put(pk, parametros.get(pk));
        }

        boolean exists = existeRegistro(codTabla, primaryKeyValues);

        if (exists) {
            throw new RuntimeException("El registro ya existe. No se puede insertar duplicados.");
        }

        List<String> posiblesCamposAuditoria = Arrays.asList(
                "USUARIO_CREACION", "TERMINAL_CREACION", "FECHA_CREACION",
                "AD_COD_USUARIO", "AD_FECHA", "USUARIO_ACTUALIZACION",
                "TERMINAL_ACTUALIZACION", "FECHA_ACTUALIZACION",
                "IP_CREACION",
                "ESTADO_REGISTRO"
        );


        for (String campoAuditoria : posiblesCamposAuditoria) {
            if (camposTabla.stream().anyMatch(campo -> campo.get("COLUMN_NAME").toString().equalsIgnoreCase(campoAuditoria))) {
                switch (campoAuditoria) {
                    case "USUARIO_CREACION":
                        if (!parametros.containsKey("USUARIO_CREACION")) {
                            parametros.put("USUARIO_CREACION", usuarioActual);
                        }
                        break;
                    case "TERMINAL_CREACION":
                        if (!parametros.containsKey("TERMINAL_CREACION")) {
                            parametros.put("TERMINAL_CREACION", terminalActual);
                        }
                        break;
                    case "FECHA_CREACION":
                        if (!parametros.containsKey("FECHA_CREACION")) {
                            parametros.put("FECHA_CREACION", fechaActual);
                        }
                        break;
                    case "ESTADO_REGISTRO":
                        if (!parametros.containsKey("ESTADO_REGISTRO")) {
                            parametros.put("ESTADO_REGISTRO", "1");
                        }
                        break;
                    case "IP_CREACION":
                        if (!parametros.containsKey("IP_CREACION")) {
                            parametros.put("IP_CREACION", terminalActual);
                        }
                        break;
                }
            }
        }

        cfgTablaAdmMapper.insertarEnTablaDinamica(codTabla, parametros);
    }

    private Date parseDate(String dateString) throws ParseException {
        List<String> dateFormats = Arrays.asList("yyyy-MM-dd", "MM-dd-yyyy", "dd-MM-yyyy");
        ParseException parseException = null;
        for (String format : dateFormats) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat(format);
                formatter.setLenient(false);
                return formatter.parse(dateString);
            } catch (ParseException e) {
                parseException = e;
            }
        }
        throw new ParseException("Formato de fecha inválido: " + dateString, 0);
    }


    private boolean existeRegistro(String codTabla, Map<String, Object> primaryKeyValues) {
        String[] schemaAndTable = splitSchemaAndTable(codTabla);
        String schema = schemaAndTable[0];
        String tableName = schemaAndTable[1];

        List<Map<String, Object>> camposTabla = cfgCampoAdmMapper.getCamposByAllTabColumns(schema, tableName);

        Map<String, String> campoTipos = new HashMap<>();
        for (Map<String, Object> campo : camposTabla) {
            String columnName = campo.get("COLUMN_NAME").toString();
            String dataType = campo.get("DATA_TYPE").toString();
            campoTipos.put(columnName, dataType);
        }

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(1) AS COUNT FROM ").append(codTabla).append(" WHERE 1=1");

        for (Map.Entry<String, Object> entry : primaryKeyValues.entrySet()) {
            String campo = entry.getKey();
            Object valor = entry.getValue();

            String dataType = campoTipos.get(campo);

            if (dataType != null && dataType.equalsIgnoreCase("DATE")) {
                sql.append(" AND TRUNC(").append(campo).append(") = TO_DATE('").append(valor).append("', 'YYYY-MM-DD')");
            } else if (dataType != null &&
                    ("VARCHAR".equalsIgnoreCase(dataType) ||
                            "VARCHAR2".equalsIgnoreCase(dataType) ||
                            "CHAR".equalsIgnoreCase(dataType))) {
                sql.append(" AND ").append(campo).append(" = '").append(valor).append("'");
            } else {
                if (valor instanceof String) {
                    sql.append(" AND ").append(campo).append(" = '").append(valor).append("'");
                } else {
                    sql.append(" AND ").append(campo).append(" = ").append(valor);
                }
            }
        }

        List<Map<String, Object>> result = cfgTablaAdmMapper.ejecutarConsultaDinamica(sql.toString());

        int count = 0;
        if (!result.isEmpty()) {
            Map<String, Object> row = result.get(0);
            Object countObj = row.get("COUNT");
            if (countObj instanceof Number) {
                count = ((Number) countObj).intValue();
            } else {
                count = Integer.parseInt(countObj.toString());
            }
        }

        return count > 0;
    }

    @Transactional
    @Override
    public void actualizarDatosDinamicos(String codTabla, Map<String, Object> parametros, Map<String, Object> primaryKeyValues) {
        String usuarioActual = "admin";
        String terminalActual = IpUtils.getClientIp(request);
        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());

        String[] schemaAndTable = splitSchemaAndTable(codTabla);
        String schema = schemaAndTable[0];
        String tableName = schemaAndTable[1];

        List<Map<String, Object>> camposTabla = cfgCampoAdmMapper.getCamposByAllTabColumns(schema, tableName);

        List<String> camposFecha = camposTabla.stream()
                .filter(campo -> campo.get("DATA_TYPE").toString().equalsIgnoreCase("DATE"))
                .map(campo -> campo.get("COLUMN_NAME").toString())
                .collect(Collectors.toList());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for (String campoFecha : camposFecha) {
            if (parametros.containsKey(campoFecha) && parametros.get(campoFecha) instanceof String) {
                String fechaString = (String) parametros.get(campoFecha);
                try {
                    Date fecha = formatter.parse(fechaString);
                    parametros.put(campoFecha, new java.sql.Date(fecha.getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Error al convertir la fecha: " + fechaString);
                }
            }
        }

        for (String key : primaryKeyValues.keySet()) {
            Object value = primaryKeyValues.get(key);
            boolean esFecha = camposTabla.stream()
                    .anyMatch(campo -> campo.get("COLUMN_NAME").toString().equalsIgnoreCase(key) &&
                            campo.get("DATA_TYPE").toString().equalsIgnoreCase("DATE"));

            if (esFecha && value instanceof String) {
                try {
                    Date fecha = formatter.parse((String) value);
                    primaryKeyValues.put(key, new java.sql.Date(fecha.getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Error al convertir la fecha en clave primaria: " + value);
                }
            }
        }

        if (camposTabla.stream().anyMatch(campo -> campo.get("COLUMN_NAME").toString().equalsIgnoreCase("USUARIO_ACTUALIZACION"))
                && !parametros.containsKey("USUARIO_ACTUALIZACION")) {
            parametros.put("USUARIO_ACTUALIZACION", usuarioActual);
        }
        if (camposTabla.stream().anyMatch(campo -> campo.get("COLUMN_NAME").toString().equalsIgnoreCase("TERMINAL_ACTUALIZACION"))
                && !parametros.containsKey("TERMINAL_ACTUALIZACION")) {
            parametros.put("TERMINAL_ACTUALIZACION", terminalActual);
        }
        if (camposTabla.stream().anyMatch(campo -> campo.get("COLUMN_NAME").toString().equalsIgnoreCase("FECHA_ACTUALIZACION"))
                && !parametros.containsKey("FECHA_ACTUALIZACION")) {
            parametros.put("FECHA_ACTUALIZACION", fechaActual);
        }

        cfgTablaAdmMapper.actualizarEnTablaDinamica(codTabla, parametros, primaryKeyValues);
    }

    @Override
    @Transactional
    public void eliminarDatosDinamicos(String codTabla, Map<String, Object> primaryKeyValues) {
        List<String> tablasPermitidas = cfgTablaAdmMapper.obtenerTablasPermitidas();
        if (!tablasPermitidas.contains(codTabla)) {
            throw new RuntimeException("Tabla no permitida para eliminación: " + codTabla);
        }

        Map<String, Object> normalizedPrimaryKeyValues = new HashMap<>();
        for (Map.Entry<String, Object> entry : primaryKeyValues.entrySet()) {
            normalizedPrimaryKeyValues.put(entry.getKey().toUpperCase().trim(), entry.getValue());
        }
        primaryKeyValues = normalizedPrimaryKeyValues;

        String[] schemaAndTable = splitSchemaAndTable(codTabla);
        String schema = schemaAndTable[0];
        String tableName = schemaAndTable[1];

        List<Map<String, Object>> camposTabla = cfgCampoAdmMapper.getCamposByAllTabColumns(schema, tableName);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        for (Map.Entry<String, Object> entry : primaryKeyValues.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            boolean esFecha = camposTabla.stream()
                    .anyMatch(campo -> campo.get("COLUMN_NAME").toString().equalsIgnoreCase(key) &&
                            campo.get("DATA_TYPE").toString().equalsIgnoreCase("DATE"));

            if (esFecha && value instanceof String str) {
                try {
                    Date fecha = formatter.parse(str);
                    entry.setValue(new java.sql.Date(fecha.getTime()));
                } catch (ParseException e) {
                    logger.error("Error al convertir la fecha en clave primaria: {}", value, e);
                    throw new RuntimeException("Error al convertir la fecha en clave primaria: " + value);
                }
            }
        }

        List<Map<String, Object>> childTablesFromDB = cfgTablaAdmMapper.getChildTables(schema, tableName);
        List<ForeignKeyRelation> childRelationsManual = Constantes.TABLE_RELATIONS.getOrDefault(codTabla, Collections.emptyList());

        if (!childTablesFromDB.isEmpty()) {
            logger.info("Usando relaciones obtenidas desde la base de datos para la tabla: {}", codTabla);
            for (Map<String, Object> child : childTablesFromDB) {
                String childTable = ((String) child.get("TABLE_NAME")).trim();
                String childColumn = ((String) child.get("COLUMN_NAME")).trim();
                String parentColumn = ((String) child.get("PK_COLUMN_NAME")).trim();

                if (!tablasPermitidas.contains(childTable)) {
                    throw new RuntimeException("Tabla hija no permitida para eliminación: " + childTable);
                }

                Object value = primaryKeyValues.get(parentColumn.toUpperCase().trim());
                if (value == null) {
                    throw new RuntimeException("No se encontró el valor de la columna primaria " + parentColumn + " en primaryKeyValues");
                }

                Map<String, Object> childKeyValues = new HashMap<>();
                childKeyValues.put(childColumn, value);

                int filasEliminadas = cfgTablaAdmMapper.deleteByForeignKeyWithTrim(childTable, childKeyValues);
                logger.info("Eliminadas {} filas de la tabla hija {}", filasEliminadas, childTable);
            }
        }

        if (!childRelationsManual.isEmpty()) {
            logger.info("Procesando relaciones manuales para la tabla: {}", codTabla);
            for (ForeignKeyRelation relation : childRelationsManual) {
                String childTable = relation.getChildTable().trim();
                String childColumn = relation.getChildColumn().trim().toUpperCase();
                String parentColumn = relation.getParentColumn().trim().toUpperCase();

                if (!tablasPermitidas.contains(childTable)) {
                    throw new RuntimeException("Tabla hija no permitida para eliminación: " + childTable);
                }

                Object value = primaryKeyValues.get(parentColumn);
                if (value == null) {
                    throw new RuntimeException("No se encontró el valor de la columna primaria " + parentColumn + " en primaryKeyValues");
                }

                Map<String, Object> childKeyValues = new HashMap<>();
                childKeyValues.put(childColumn, value);

                logger.info("Eliminando registros en la tabla hija {} donde {} = {}", childTable, childColumn, value);

                int filasEliminadas = cfgTablaAdmMapper.deleteByForeignKeyWithTrim(childTable, childKeyValues);
                logger.info("Eliminadas {} filas de la tabla hija {}", filasEliminadas, childTable);
            }
        }

        cfgTablaAdmMapper.eliminarEnTablaDinamica(codTabla, primaryKeyValues);
        logger.info("Eliminado el registro de la tabla principal: {}", codTabla);
    }


    @Override
    public List<CfgTablaAdmDTO> listarPorTabla() {
        return cfgTablaAdmMapper.listarPorTabla();
    }

    @Override
    public PageInfo<Map<String, Object>> listarConFiltro(String codTabla, Map<String, Object> filtros, PaginacionDTO paginacion) {
        List<CfgCampoAdm> camposFiltrables = cfgCampoAdmMapper.getCamposFiltrables(codTabla);
        List<CfgCampoAdm> camposTabla = cfgCampoAdmMapper.getCamposByTabla(codTabla);

        Map<String, Object> filtrosValidos = new HashMap<>();
        for (CfgCampoAdm campo : camposFiltrables) {
            String nombreCampo = campo.getCodCampo();
            if (filtros.containsKey(nombreCampo) && filtros.get(nombreCampo) != null && !filtros.get(nombreCampo).toString().isEmpty()) {
                filtrosValidos.put(nombreCampo, filtros.get(nombreCampo));
            }
        }

        Map<String, String> tipoDatoMap = new HashMap<>();
        for (CfgCampoAdm campo : camposFiltrables) {
            String nombreCampo = campo.getCodCampo();
            if (filtrosValidos.containsKey(nombreCampo)) {
                tipoDatoMap.put(nombreCampo, campo.getTipoDato());
            }
        }

        String sort = paginacion.getSort();
        String order = paginacion.getOrder();

        List<String> allowedSortFields = camposTabla.stream()
                .map(CfgCampoAdm::getCodCampo)
                .collect(Collectors.toList());

        if (sort != null && !sort.trim().isEmpty()) {
            if (!allowedSortFields.contains(sort)) {
                throw new RuntimeException("Campo de ordenamiento no permitido: " + sort);
            }

            if (order == null || (!order.equalsIgnoreCase("asc") && !order.equalsIgnoreCase("desc"))) {
                order = "asc";
            } else {
                order = order.toUpperCase();
            }
        }

        String sql = construirConsultaConFiltros(codTabla, filtrosValidos, tipoDatoMap, sort, order, camposTabla);
        System.out.println("SQL final: " + sql);

        PageHelper.startPage(paginacion.getPage(), paginacion.getSize());

        List<Map<String, Object>> resultados = cfgTablaAdmMapper.ejecutarConsultaDinamica(sql);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Map<String, Object> row : resultados) {
            row.remove("PAGEHELPER_ROW_ID");

            for (Map.Entry<String, Object> entry : row.entrySet()) {
                Object value = entry.getValue();

                if (value instanceof oracle.sql.TIMESTAMP) {
                    try {
                        Date dateValue = ((oracle.sql.TIMESTAMP) value).timestampValue();
                        String formattedDate = sdf.format(dateValue);
                        row.put(entry.getKey(), formattedDate);
                    } catch (SQLException e) {
                        logger.error("Error al convertir TIMESTAMP", e);
                        throw new RuntimeException("Error al convertir TIMESTAMP", e);
                    }
                } else if (value instanceof java.sql.Timestamp || value instanceof java.util.Date) {
                    String formattedDate = sdf.format((Date) value);
                    row.put(entry.getKey(), formattedDate);
                } else if (value instanceof String) {
                    row.put(entry.getKey(), ((String) value).trim());
                }
            }
        }

        return new PageInfo<>(resultados);
    }

    @Override
    public List<Map<String, Object>> obtenerCamposPorTabla(String codTabla) {
        String[] schemaAndTable = splitSchemaAndTable(codTabla);
        String schema = schemaAndTable[0];
        String tableName = schemaAndTable[1];

        List<Map<String, Object>> columnasTabla = cfgCampoAdmMapper.getCamposByAllTabColumns(schema, tableName);

        List<CfgCampoAdm> camposDefinidos = cfgCampoAdmMapper.getCamposByTabla(codTabla);

        List<Map<String, Object>> columnasFiltradas = columnasTabla.stream()
                .filter(columna -> camposDefinidos.stream()
                        .anyMatch(campo -> campo.getCodCampo().equalsIgnoreCase((String) columna.get("COLUMN_NAME"))))
                .collect(Collectors.toList());

        return columnasFiltradas;
    }



    private String construirConsultaConFiltros(String codTabla, Map<String, Object> filtrosValidos,
                                               Map<String, String> tipoDatoMap, String sort, String order,
                                               List<CfgCampoAdm> camposTabla) {
        StringBuilder sql = new StringBuilder("SELECT ");

        boolean firstColumn = true;
        Set<String> columnasIncluidas = new HashSet<>();

        for (CfgCampoAdm campo : camposTabla) {
            if (campo.getQueryLogico() == null || campo.getQueryLogico().trim().isEmpty()) {
                if (!columnasIncluidas.contains(campo.getCodCampo()) && !campo.getCodCampo().equalsIgnoreCase("ESTADO")) {
                    if (!firstColumn) {
                        sql.append(", ");
                    }
                    sql.append("T.").append(campo.getCodCampo());
                    columnasIncluidas.add(campo.getCodCampo());
                    firstColumn = false;
                }
            }
        }

        for (CfgCampoAdm campo : camposTabla) {
            if (campo.getQueryLogico() != null && !campo.getQueryLogico().trim().isEmpty()) {
                if (!firstColumn) {
                    sql.append(", ");
                }

                String queryLogico = campo.getQueryLogico().trim();
                String alias = extractAlias(queryLogico);

                if (alias != null && !alias.isEmpty()) {
                    sql.append(queryLogico);
                } else {
                    sql.append(queryLogico).append(" AS ").append(campo.getCodCampo()).append("_query");
                }

                columnasIncluidas.add(campo.getCodCampo());
                firstColumn = false;
            }
        }

        sql.append(" FROM ").append(codTabla).append(" T WHERE 1 = 1");

        for (Map.Entry<String, Object> entry : filtrosValidos.entrySet()) {
            String campo = entry.getKey();
            Object valor = entry.getValue();

            CfgCampoAdm campoConfig = camposTabla.stream()
                    .filter(c -> c.getCodCampo().equalsIgnoreCase(campo))
                    .findFirst()
                    .orElse(null);

            if (campoConfig != null) {
                if ("DATE".equalsIgnoreCase(campoConfig.getTipoDato())) {
                    sql.append(" AND TRUNC(T.").append(campo).append(") = TO_DATE('").append(valor).append("', 'YYYY-MM-DD')");
                } else if ("VARCHAR".equalsIgnoreCase(campoConfig.getTipoDato()) ||
                        "VARCHAR2".equalsIgnoreCase(campoConfig.getTipoDato()) ||
                        "CHAR".equalsIgnoreCase(campoConfig.getTipoDato())) {
                    sql.append(" AND UPPER(T.").append(campo).append(") LIKE UPPER('%").append(sanitizeInput(valor.toString())).append("%')");
                } else {
                    if (valor instanceof String) {
                        sql.append(" AND T.").append(campo).append(" = '").append(sanitizeInput(valor.toString())).append("'");
                    } else {
                        sql.append(" AND T.").append(campo).append(" = ").append(valor);
                    }
                }
            }
        }

        if (sort != null && !sort.trim().isEmpty()) {
            List<String> allowedSortFields = camposTabla.stream()
                    .map(CfgCampoAdm::getCodCampo)
                    .collect(Collectors.toList());

            if (!allowedSortFields.contains(sort)) {
                throw new RuntimeException("Campo de ordenamiento no permitido: " + sort);
            }

            if (order == null || (!order.equalsIgnoreCase("asc") && !order.equalsIgnoreCase("desc"))) {
                order = "asc";
            } else {
                order = order.toUpperCase();
            }

            CfgCampoAdm campoOrden = camposTabla.stream()
                    .filter(c -> c.getCodCampo().equalsIgnoreCase(sort))
                    .findFirst()
                    .orElse(null);

            if (campoOrden != null && campoOrden.getQueryLogico() != null && !campoOrden.getQueryLogico().trim().isEmpty()) {
                String aliasOrden = extractAlias(campoOrden.getQueryLogico());
                if (aliasOrden == null || aliasOrden.isEmpty()) {
                    aliasOrden = campoOrden.getCodCampo() + "_query";
                }
                sql.append(" ORDER BY ").append(aliasOrden).append(" ").append(order);
            } else {
                sql.append(" ORDER BY T.").append(sort).append(" ").append(order);
            }
        }

        return sql.toString();
    }

    private String extractAlias(String queryLogico) {
        String lower = queryLogico.toLowerCase();
        int asIndex = lower.indexOf(" as ");
        if (asIndex != -1) {
            String alias = queryLogico.substring(asIndex + 4).trim();
            alias = alias.replaceAll("^\"|\"$", "");
            return alias;
        }
        return null;
    }

    private String sanitizeInput(String input) {
        if (input == null) {
            return null;
        }
        return input.replace("'", "''");
    }

    private String[] splitSchemaAndTable(String codTabla) {
        String[] parts = codTabla.split("\\.");
        String schema;
        String tableName;
        if (parts.length == 2) {
            schema = parts[0];
            tableName = parts[1];
        } else {
            schema = "ADMIN_GART";
            tableName = codTabla;
        }
        return new String[]{schema, tableName};
    }
}
