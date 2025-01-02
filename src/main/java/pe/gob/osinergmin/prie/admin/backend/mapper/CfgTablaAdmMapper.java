package pe.gob.osinergmin.prie.admin.backend.mapper;

import org.apache.ibatis.annotations.Param;
import pe.gob.osinergmin.prie.admin.backend.domain.CfgTablaAdm;
import pe.gob.osinergmin.prie.admin.backend.dto.tabla.CfgTablaAdmDTO;

import java.util.List;
import java.util.Map;

public interface CfgTablaAdmMapper {
    int deleteByPrimaryKey(String codTabla);

    int insert(CfgTablaAdm record);

    int insertSelective(CfgTablaAdm record);

    CfgTablaAdm selectByPrimaryKey(String codTabla);

    int updateByPrimaryKeySelective(CfgTablaAdm record);

    int updateByPrimaryKey(CfgTablaAdm record);

    CfgTablaAdm getTablaByPK(@Param("codTabla") String codTabla);

    int existeTabla(@Param("codTabla") String codTabla);

    List<String> obtenerTablasPermitidas();

    List<Map<String, Object>> seleccionarDesdeTabla(@Param("codTabla") String codTabla);

    void insertarEnTablaDinamica(@Param("codTabla") String codTabla, @Param("parametros") Map<String, Object> parametros);

    void actualizarEnTablaDinamica(
            @Param("codTabla") String codTabla,
            @Param("parametros") Map<String, Object> parametros,
            @Param("primaryKeyValues") Map<String, Object> primaryKeyValues
    );

    void eliminarEnTablaDinamica(
            @Param("codTabla") String codTabla,
            @Param("primaryKeyValues") Map<String, Object> primaryKeyValues
    );


    List<CfgTablaAdmDTO> listarPorTabla();

    List<Map<String, Object>> ejecutarConsultaDinamica(@Param("sql") String sql);

    List<Map<String, Object>> getChildTables(
            @Param("owner") String owner,
            @Param("tableName") String tableName
    );

    int deleteByForeignKeyWithTrim(
            @Param("tableName") String tableName,
            @Param("primaryKeyValues") Map<String, Object> primaryKeyValues
    );

}