package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.domain.*;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.devanado.DevanadoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.transRotacion.TransRotacionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.transRotacion.TransRotacionInsertDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.transformador.*;
import pe.gob.osinergmin.prie.admin.backend.mapper.*;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTransformadorService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class AdmTransformadorServiceImpl implements AdmTransformadorService{

    @Autowired
    private AdmTransformadorMapper admTransformadorMapper;

    @Autowired
    private AdmDevanadoMapper admDevanadoMapper; ;

    @Autowired
    private AdmTransRotacionMapper admTransRotacionMapper; ;

    @Autowired
    private AdmTipoSistTransMapper admTipoSistTransMapper;

    @Autowired
    private AdmSubestacionMapper admSubestacionMapper;

    @Autowired
    private AdmEmpresaMapper admEmpresaMapper;

    @Autowired
    private HttpServletRequest request;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Transactional
    @Override
    public MessageDTO deleteByPrimaryKey(Integer idTransformador) {
        try {
            if (idTransformador == null) {
                return new MessageDTO(Constantes.ERROR, "El ID del Transformador no puede ser nulo.");
            }

            AdmTransformador existe = admTransformadorMapper.selectByPrimaryKey(idTransformador);
            if (existe == null) {
                return new MessageDTO(Constantes.ERROR, "El transformador con el ID proporcionado no existe.");
            }

            admDevanadoMapper.deleteByTransformadorId(idTransformador);
            admTransRotacionMapper.deleteByTransformadorId(idTransformador);
            int rowsAffected = admTransformadorMapper.deleteByPrimaryKey(idTransformador);

            if (rowsAffected > 0) {
                return new MessageDTO(Constantes.SUCCES, "Transformador eliminado correctamente.");
            } else {
                return new MessageDTO(Constantes.ERROR, "No se pudo eliminar el transformador.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Ocurrió un error al eliminar el transformador.");
        }
    }

    @Transactional
    @Override
    public MessageDTO insert(AdmTransformadorFormDTO record) {
        try {

            if (record.getIdTipoSisTrans() == null) {
                return new MessageDTO(Constantes.ERROR, "El ID del tipo de sistema de transmisión no puede ser nulo.");
            }
            AdmTipoSistTrans existeIdTipoSisTrans = admTipoSistTransMapper.selectByPrimaryKey(record.getIdTipoSisTrans());
            if (existeIdTipoSisTrans == null) {
                return new MessageDTO(Constantes.ERROR, "El tipo de sistema de transmisión especificado no existe.");
            }

            if (record.getCodEquipo() == null || record.getCodEquipo().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código del equipo no puede estar vacío.");
            }

            if (record.getCodSubestacion() == null || record.getCodSubestacion().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código de la subestación no puede estar vacío.");
            }
            AdmSubestacion existeCodSubestacion = admSubestacionMapper.selectByPrimaryKey(record.getCodSubestacion());
            if (existeCodSubestacion == null) {
                return new MessageDTO(Constantes.ERROR, "La subestación especificada no existe.");
            }

            if (record.getCodEmpresa() == null || record.getCodEmpresa().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código de la empresa no puede estar vacío.");
            }
            AdmEmpresa existeEmpresa = admEmpresaMapper.selectByPrimaryKey(record.getCodEmpresa());
            if (existeEmpresa == null) {
                return new MessageDTO(Constantes.ERROR, "La empresa especificada no existe.");
            }

            if (record.getNumSerie() == null || record.getNumSerie().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El número de serie no puede estar vacío.");
            }

            if (record.getAnnioPes() == null) {
                return new MessageDTO(Constantes.ERROR, "El año de puesta en servicio no puede ser nulo.");
            }

            AdmTransformador existe = admTransformadorMapper.selectByPrimaryKey(record.getIdTransformador());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "Ya existe un transformador con ese ID.");
            }

            int currentYear = LocalDate.now().getYear();
            if (record.getAnnioPes() > currentYear) {
                return new MessageDTO(Constantes.ERROR, "El año de puesta en servicio no debe ser mayor al año actual (" + currentYear + ").");
            }

            Integer newId = admTransformadorMapper.getNextId();

            AdmTransformadorInsertarDTO admTransformador = new AdmTransformadorInsertarDTO();
            admTransformador.setIdTransformador(newId);
            admTransformador.setIdTipoSisTrans(record.getIdTipoSisTrans());
            admTransformador.setCodEquipo(record.getCodEquipo());
            admTransformador.setCodSubestacion(record.getCodSubestacion());
            admTransformador.setCodEmpresa(record.getCodEmpresa());
            admTransformador.setNumSerie(record.getNumSerie());
            admTransformador.setAnnioPes(record.getAnnioPes());
            admTransformador.setMesPes(record.getMesPes());
            admTransformador.setDiaPes(record.getDiaPes());
            admTransformador.setPotOnanPri(record.getPotOnanPri());
            admTransformador.setPotOnanSec(record.getPotOnanSec());
            admTransformador.setPotOnanTer(record.getPotOnanTer());
            admTransformador.setPotOnafPri(record.getPotOnafPri());
            admTransformador.setPotOnafSec(record.getPotOnafSec());
            admTransformador.setPotOnafTer(record.getPotOnafTer());
            admTransformador.settPrimario(record.gettPrimario());
            admTransformador.settSecundario(record.gettSecundario());
            admTransformador.settTerciario(record.gettTerciario());
            admTransformador.setGrupoConexion(record.getGrupoConexion());
            admTransformador.setTaps(record.getTaps());
            admTransformador.setVccPs(record.getVccPs());
            admTransformador.setVccPt(record.getVccPt());
            admTransformador.setVccSt(record.getVccSt());
            admTransformador.setPcuPs(record.getPcuPs());
            admTransformador.setPcuPt(record.getPcuPt());
            admTransformador.setPcuSt(record.getPcuSt());
            admTransformador.setPfe(record.getPfe());
            admTransformador.setTipoRegulacion(record.getTipoRegulacion());
            admTransformador.setPesoTotal(record.getPesoTotal());
            admTransformador.setPesoCobre(record.getPesoCobre());
            admTransformador.setFecAlta(record.getFecAlta());
            admTransformador.setFecBaja(record.getFecBaja());
            admTransformador.setEstado(record.getEstado());
            admTransformador.setEstadoRegistro(record.getEstadoRegistro());
            admTransformador.setUsuarioCreacion("admin");
            admTransformador.setTerminalCreacion(IpUtils.getClientIp(request));
            admTransformador.setFechaCreacion(new Date());
            admTransformadorMapper.insertarFechasString(admTransformador);

            return new MessageDTO(Constantes.SUCCES, "Transformador insertado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Ocurrió un error al insertar el transformador.");
        }
    }


    @Override
    public int insertSelective(AdmTransformador record) {
        return admTransformadorMapper.insertSelective(record);
    }

    @Override
    public AdmTransformador selectByPrimaryKey(Integer idTransformador) {
        return admTransformadorMapper.selectByPrimaryKey(idTransformador);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmTransformador record) {
        return admTransformadorMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(AdmTransformadorFormActualizarDTO record) {
        try {
            if (record.getIdTransformador() == null) {
                return new MessageDTO(Constantes.ERROR, "El ID del transformador no puede ser nulo.");
            }

            AdmTransformador existe = admTransformadorMapper.selectByPrimaryKey(record.getIdTransformador());
            if (existe == null) {
                return new MessageDTO(Constantes.ERROR, "El transformador con el ID proporcionado no existe.");
            }

            if (record.getIdTipoSisTrans() == null) {
                return new MessageDTO(Constantes.ERROR, "El ID del tipo de sistema de transmisión no puede ser nulo.");
            }
            AdmTipoSistTrans existeIdTipoSisTrans = admTipoSistTransMapper.selectByPrimaryKey(record.getIdTipoSisTrans());
            if (existeIdTipoSisTrans == null) {
                return new MessageDTO(Constantes.ERROR, "El tipo de sistema de transmisión especificado no existe.");
            }

            if (record.getCodEquipo() == null || record.getCodEquipo().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código del equipo no puede estar vacío.");
            }

            if (record.getCodSubestacion() == null || record.getCodSubestacion().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código de la subestación no puede estar vacío.");
            }
            AdmSubestacion existeCodSubestacion = admSubestacionMapper.selectByPrimaryKey(record.getCodSubestacion());
            if (existeCodSubestacion == null) {
                return new MessageDTO(Constantes.ERROR, "La subestación especificada no existe.");
            }

            if (record.getCodEmpresa() == null || record.getCodEmpresa().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código de la empresa no puede estar vacío.");
            }
            AdmEmpresa existeEmpresa = admEmpresaMapper.selectByPrimaryKey(record.getCodEmpresa());
            if (existeEmpresa == null) {
                return new MessageDTO(Constantes.ERROR, "La empresa especificada no existe.");
            }

            if (record.getNumSerie() == null || record.getNumSerie().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El número de serie no puede estar vacío.");
            }

            if (record.getAnnioPes() == null) {
                return new MessageDTO(Constantes.ERROR, "El año de puesta en servicio no puede ser nulo.");
            }

            int currentYear = LocalDate.now().getYear();
            if (record.getAnnioPes() > currentYear) {
                return new MessageDTO(Constantes.ERROR, "El año de puesta en servicio no debe ser mayor al año actual (" + currentYear + ").");
            }

            BigDecimal maxNumber9_2 = new BigDecimal("99999999.99");
            BigDecimal maxNumber9_3 = new BigDecimal("9999999.999");
            int maxNumber4 = 9999;
            int maxNumber3 = 999;

            if (record.getAnnioPes() != null && record.getAnnioPes() > maxNumber4) {
                return new MessageDTO(Constantes.ERROR, "El año de puesta en servicio excede el máximo permitido (9999)");
            }

            if (record.getMesPes() != null && record.getMesPes() > 99) {
                return new MessageDTO(Constantes.ERROR, "El mes de puesta en servicio excede el máximo permitido (99)");
            }

            if (record.getDiaPes() != null && record.getDiaPes() > 99) {
                return new MessageDTO(Constantes.ERROR, "El día de puesta en servicio excede el máximo permitido (99)");
            }

            if (record.getTaps() != null && record.getTaps() > maxNumber3) {
                return new MessageDTO(Constantes.ERROR, "El valor de TAPS excede el máximo permitido (999)");
            }

            Map<String, BigDecimal> fieldsNumber9_2 = new HashMap<>();
            fieldsNumber9_2.put("potOnanPri", record.getPotOnanPri());
            fieldsNumber9_2.put("potOnanSec", record.getPotOnanSec());
            fieldsNumber9_2.put("potOnanTer", record.getPotOnanTer());
            fieldsNumber9_2.put("potOnafPri", record.getPotOnafPri());
            fieldsNumber9_2.put("potOnafSec", record.getPotOnafSec());
            fieldsNumber9_2.put("potOnafTer", record.getPotOnafTer());
            fieldsNumber9_2.put("tPrimario", record.gettPrimario());
            fieldsNumber9_2.put("tSecundario", record.gettSecundario());
            fieldsNumber9_2.put("tTerciario", record.gettTerciario());
            fieldsNumber9_2.put("vccPs", record.getVccPs());
            fieldsNumber9_2.put("vccPt", record.getVccPt());
            fieldsNumber9_2.put("vccSt", record.getVccSt());
            fieldsNumber9_2.put("pcuPs", record.getPcuPs());
            fieldsNumber9_2.put("pcuPt", record.getPcuPt());
            fieldsNumber9_2.put("pcuSt", record.getPcuSt());
            fieldsNumber9_2.put("pfe", record.getPfe());

            for (Map.Entry<String, BigDecimal> entry : fieldsNumber9_2.entrySet()) {
                BigDecimal value = entry.getValue();
                if (value != null) {
                    if (value.abs().compareTo(maxNumber9_2) > 0) {
                        return new MessageDTO(Constantes.ERROR, "El valor de " + entry.getKey() + " excede el máximo permitido, debe tener un maximo de 7 enteros y 2 decimales");
                    }
                }
            }

            Map<String, BigDecimal> fieldsNumber9_3 = new HashMap<>();
            fieldsNumber9_3.put("pesoTotal", record.getPesoTotal());
            fieldsNumber9_3.put("pesoCobre", record.getPesoCobre());

            for (Map.Entry<String, BigDecimal> entry : fieldsNumber9_3.entrySet()) {
                BigDecimal value = entry.getValue();
                if (value != null) {
                    if (value.abs().compareTo(maxNumber9_3) > 0) {
                        return new MessageDTO(Constantes.ERROR, "El valor de " + entry.getKey() + " excede el máximo permitido, debe tener un maximo de 7 enteros y 2 decimales");
                    }
                }
            }

            AdmTransformadorInsertarDTO admTransformador = new AdmTransformadorInsertarDTO();
            admTransformador.setIdTransformador(record.getIdTransformador());
            admTransformador.setIdTipoSisTrans(record.getIdTipoSisTrans());
            admTransformador.setCodEquipo(record.getCodEquipo());
            admTransformador.setCodSubestacion(record.getCodSubestacion());
            admTransformador.setCodEmpresa(record.getCodEmpresa());
            admTransformador.setNumSerie(record.getNumSerie());
            admTransformador.setAnnioPes(record.getAnnioPes());
            admTransformador.setMesPes(record.getMesPes());
            admTransformador.setDiaPes(record.getDiaPes());
            admTransformador.setPotOnanPri(record.getPotOnanPri());
            admTransformador.setPotOnanSec(record.getPotOnanSec());
            admTransformador.setPotOnanTer(record.getPotOnanTer());
            admTransformador.setPotOnafPri(record.getPotOnafPri());
            admTransformador.setPotOnafSec(record.getPotOnafSec());
            admTransformador.setPotOnafTer(record.getPotOnafTer());
            admTransformador.settPrimario(record.gettPrimario());
            admTransformador.settSecundario(record.gettSecundario());
            admTransformador.settTerciario(record.gettTerciario());
            admTransformador.setGrupoConexion(record.getGrupoConexion());
            admTransformador.setTaps(record.getTaps());
            admTransformador.setVccPs(record.getVccPs());
            admTransformador.setVccPt(record.getVccPt());
            admTransformador.setVccSt(record.getVccSt());
            admTransformador.setPcuPs(record.getPcuPs());
            admTransformador.setPcuPt(record.getPcuPt());
            admTransformador.setPcuSt(record.getPcuSt());
            admTransformador.setPfe(record.getPfe());
            admTransformador.setTipoRegulacion(record.getTipoRegulacion());
            admTransformador.setPesoTotal(record.getPesoTotal());
            admTransformador.setPesoCobre(record.getPesoCobre());
            admTransformador.setFecAlta(record.getFecAlta());
            admTransformador.setFecBaja(record.getFecBaja());
            admTransformador.setEstado(record.getEstado());
            admTransformador.setEstadoRegistro(record.getEstadoRegistro());
            admTransformador.setUsuarioActualizacion("admin");
            admTransformador.setTerminalActualizacion(IpUtils.getClientIp(request));
            admTransformador.setFechaActualizacion(new Date());
            int rowsAffected = admTransformadorMapper.actualizarFechasString(admTransformador);

            admDevanadoMapper.deleteByTransformadorId(record.getIdTransformador());
            if (record.getDevanado() != null && !record.getDevanado().isEmpty()) {
                for (DevanadoDTO devanado : record.getDevanado()) {
                    AdmDevanado admDevanado = new AdmDevanado();
                    Integer newIdDevanado = admDevanadoMapper.getNextId();
                    admDevanado.setIdDevanado(newIdDevanado);

                    admDevanado.setCodDevanado(devanado.getCodDevanado());
                    admDevanado.setIdTransformador(record.getIdTransformador());
                    admDevanado.setCodBarra(devanado.getCodBarra());
                    admDevanado.setAreaDemanda(devanado.getAreaDemanda());
                    admDevanado.setPotDevanado(devanado.getPotDevanado());
                    admDevanado.setEstado(devanado.getEstado());
                    admDevanado.setUsuarioCreacion("admin");
                    admDevanado.setUsuarioActualizacion("admin");
                    admDevanado.setTerminalCreacion(IpUtils.getClientIp(request));
                    admDevanado.setTerminalActualizacion(IpUtils.getClientIp(request));
                    admDevanado.setFechaCreacion(new Date());
                    admDevanado.setFechaActualizacion(new Date());

                    admDevanadoMapper.insert(admDevanado);
                }
            }

            if (record.getTransRotacion() != null && !record.getTransRotacion().isEmpty()) {
                for (TransRotacionDTO rotacionDTO : record.getTransRotacion()) {
                    if (rotacionDTO.getCodSubestacionDestino() != null && !rotacionDTO.getCodSubestacionDestino().isEmpty()) {
                        Integer newIdTransfRotacion = admTransRotacionMapper.getNextId();
                        TransRotacionInsertDTO admTransRotacion = new TransRotacionInsertDTO();
                        admTransRotacion.setIdTransfRotacion(newIdTransfRotacion);

                        admTransRotacion.setUsuarioCreacion("admin");
                        admTransRotacion.setUsuarioActualizacion("admin");
                        admTransRotacion.setTerminalCreacion(IpUtils.getClientIp(request));
                        admTransRotacion.setTerminalActualizacion(IpUtils.getClientIp(request));
                        admTransRotacion.setFechaCreacion(new Date());
                        admTransRotacion.setFechaActualizacion(new Date());

                        admTransRotacion.setCodSubestacion(record.getCodSubestacion());
                        admTransRotacion.setIdTransformador(record.getIdTransformador());
                        admTransRotacion.setCodSubestacionDestino(rotacionDTO.getCodSubestacionDestino());
                        admTransRotacion.setFecMovimiento(rotacionDTO.getFecMovimiento());
                        admTransRotacion.setSituacion("0");
                        admTransRotacion.setEstado("1");

                        admTransRotacionMapper.insertarNuevo(admTransRotacion);
                    }
                }
            }

            if (rowsAffected > 0) {
                return new MessageDTO(Constantes.SUCCES, "Transformador actualizado correctamente.");
            } else {
                return new MessageDTO(Constantes.ERROR, "No se pudo actualizar el transformador.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Ocurrió un error al actualizar el transformador.");
        }
    }


    @Override
    public List<AdmTransformadorDTO> selectAll() {
        List<AdmTransformadorDTO> transformadores = admTransformadorMapper.selectAll();

        for (AdmTransformadorDTO transformador : transformadores) {
            Integer idTransformador = transformador.getIdTransformador();

            List<DevanadoDTO> devanados = admDevanadoMapper.selectByTransformadorId(idTransformador);
            transformador.setDevanado(devanados);

            List<TransRotacionDTO> transRotaciones = admTransRotacionMapper.selectByTransformadorId(idTransformador);
            transformador.setTransRotacion(transRotaciones);
        }

        return transformadores;
    }

    @Transactional
    @Override
    public PageInfo<AdmTransformadorDTO> filtrar(AdmTransformadorSearchDTO admTransformadorSearchDTO) {
        try {
            String orderBy = "";
            if (admTransformadorSearchDTO.getSort() != null && !admTransformadorSearchDTO.getSort().isEmpty()) {

                List<String> allowedSortFields = Arrays.asList("codEquipo", "codEmpresa", "codSubestacion", "estadoRegistro", "idTransformador", "fechaCreacion");
                if (allowedSortFields.contains(admTransformadorSearchDTO.getSort())) {
                    String sortDirection = "ASC";
                    if ("DESC".equalsIgnoreCase(admTransformadorSearchDTO.getOrder())) {
                        sortDirection = "DESC";
                    }
                    String sortColumn = mapSortFieldToColumn(admTransformadorSearchDTO.getSort());
                    orderBy = sortColumn + " " + sortDirection;
                }
            }

            if (!orderBy.isEmpty()) {
                PageHelper.startPage(admTransformadorSearchDTO.getPage(), admTransformadorSearchDTO.getSize(), orderBy);
            } else {
                PageHelper.startPage(admTransformadorSearchDTO.getPage(), admTransformadorSearchDTO.getSize());
            }

            List<AdmTransformadorDTO> transformadores = admTransformadorMapper.filtrar(admTransformadorSearchDTO);

            for (AdmTransformadorDTO transformador : transformadores) {
                Integer idTransformador = transformador.getIdTransformador();

                List<DevanadoDTO> devanados = admDevanadoMapper.selectByTransformadorId(idTransformador);
                transformador.setDevanado(devanados);

                List<TransRotacionDTO> transRotaciones = admTransRotacionMapper.selectByTransformadorId(idTransformador);
                transformador.setTransRotacion(transRotaciones);
            }

            return new PageInfo<>(transformadores);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String mapSortFieldToColumn(String sortField) {
        switch (sortField) {
            case "codEquipo":
                return "COD_EQUIPO";
            case "codEmpresa":
                return "COD_EMPRESA";
            case "codSubestacion":
                return "COD_SUBESTACION";
            case "estadoRegistro":
                return "ESTADO_REGISTRO";
            default:
                return "ID_TRANSFORMADOR";
        }
    }


    private Date parseDate(String dateString) throws ParseException {
        return DATE_FORMAT.parse(dateString);
    }
}
