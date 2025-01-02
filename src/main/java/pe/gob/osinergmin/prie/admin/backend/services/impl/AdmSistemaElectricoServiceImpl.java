package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmSistemaElectrico;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sisElectrico.*;
import pe.gob.osinergmin.prie.admin.backend.dto.sistemaElectricoDet.SistemaElectricoDetDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sistemaElectricoDet.SistemaElectricoDetResultDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmBarraSiselecAlimMapper;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmPliegoSiselecMapper;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmSistemaElectricoDetMapper;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmSistemaElectricoMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmSistemaElectricoService;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class AdmSistemaElectricoServiceImpl implements AdmSistemaElectricoService {

    @Autowired
    private AdmSistemaElectricoMapper admSistemaElectricoMapper;

    @Autowired
    private AdmSistemaElectricoDetMapper admSistemaElectricoDetMapper;

    @Autowired
    private AdmPliegoSiselecMapper admPliegoSiselecMapper;

    @Autowired
    private AdmBarraSiselecAlimMapper admBarraSiselecAlimMapper;

    @Autowired
    private HttpServletRequest request;

    private static final Logger logger = LoggerFactory.getLogger(AdmSistemaElectricoServiceImpl.class);

    @Transactional
    @Override
    public MessageDTO deleteByPrimaryKey(String codSisElec) {
        try {
            if (codSisElec == null || codSisElec.isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código de sistema eléctrico es requerido.");
            }

            AdmSistemaElectrico existe = admSistemaElectricoMapper.selectByPrimaryKey(codSisElec);
            if (existe == null) {
                return new MessageDTO(Constantes.ERROR, "El sistema eléctrico no existe. No se puede eliminar.");
            }
            admSistemaElectricoDetMapper.deleteByCodSisElec(codSisElec);
            admBarraSiselecAlimMapper.borrarPorCodSisElec(codSisElec);
            admPliegoSiselecMapper.deleteByCodSisElec(codSisElec);
            admSistemaElectricoMapper.deleteByPrimaryKey(codSisElec);
            return new MessageDTO(Constantes.SUCCES, "Eliminado exitosamente.");
        } catch (Exception e) {
            logger.error("Error al eliminar el sistema eléctrico", e);
            return new MessageDTO(Constantes.ERROR, "Error al eliminar: " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public MessageDTO insert(AdmSistemaElectricoCrearDTO record) {
        try {
            if (record.getCodSisElec() == null || record.getCodSisElec().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código de sistema eléctrico es requerido.");
            }
            if (record.getCodSisElec().length() != 6) {
                return new MessageDTO(Constantes.ERROR, "El código de sistema eléctrico debe tener 6 caracteres.");
            }
            if (record.getNomSisElec() != null && record.getNomSisElec().length() > 200) {
                return new MessageDTO(Constantes.ERROR, "El nombre del sistema eléctrico no debe exceder 200 caracteres.");
            }
            if (record.getCodEmpresa() != null && record.getCodEmpresa().length() != 4) {
                return new MessageDTO(Constantes.ERROR, "El código de empresa debe tener 4 caracteres.");
            }
            if (record.getTipSisElec() != null && record.getTipSisElec().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El tipo de sistema eléctrico debe tener 1 carácter.");
            }
            if (record.getCodSectorTipico() != null && record.getCodSectorTipico().length() != 3) {
                return new MessageDTO(Constantes.ERROR, "El código de sector típico debe tener 3 caracteres.");
            }
            if (record.getEsSisElec() == null || (!record.getEsSisElec().equals("0") && !record.getEsSisElec().equals("1"))) {
                return new MessageDTO(Constantes.ERROR, "El estado del sistema eléctrico debe ser '0' o '1'.");
            }

            AdmSistemaElectrico existe = admSistemaElectricoMapper.selectByPrimaryKey(record.getCodSisElec());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "Ya existe un sistema eléctrico con el código proporcionado.");
            }

            SistemaElectricoInsertDTO sistemaElectrico = new SistemaElectricoInsertDTO();
            sistemaElectrico.setCodSisElec(record.getCodSisElec());
            sistemaElectrico.setNomSisElec(record.getNomSisElec());
            sistemaElectrico.setCodEmpresa(record.getCodEmpresa());
            sistemaElectrico.setTipSisElec(record.getTipSisElec());
            sistemaElectrico.setCodSectorTipico(record.getCodSectorTipico());
            sistemaElectrico.setFecIntercon(record.getFecIntercon());
            sistemaElectrico.setAreaDemanda(record.getAreaDemanda());
            sistemaElectrico.setEsSisElec(record.getEsSisElec());

            if ("1".equals(record.getEsSisElec())) {
                sistemaElectrico.setFecAlta(record.getFecAlta());
            } else {
                sistemaElectrico.setFecBaja(record.getFecBaja());
            }

            sistemaElectrico.setAdCodUsuario("admin");
            sistemaElectrico.setAdFecha(new Date());
            sistemaElectrico.setIpCreacion(IpUtils.getClientIp(request));

            admSistemaElectricoMapper.insert(sistemaElectrico);

            return new MessageDTO(Constantes.SUCCES, "Sistema eléctrico insertado correctamente.");
        } catch (Exception e) {
            logger.error("Error al insertar el sistema eléctrico", e);
            return new MessageDTO(Constantes.ERROR, "Error al insertar: " + e.getMessage());
        }
    }

    @Override
    public int insertSelective(AdmSistemaElectrico record) {
        return admSistemaElectricoMapper.insertSelective(record);
    }

    @Override
    public AdmSistemaElectrico selectByPrimaryKey(String codSisElec) {
        return admSistemaElectricoMapper.selectByPrimaryKey(codSisElec);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmSistemaElectrico record) {
        return admSistemaElectricoMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(AdmSistemaElectricoActualizarDTO record) {
        try {
            if (record.getCodSisElec() == null || record.getCodSisElec().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código de sistema eléctrico es requerido.");
            }
            if (record.getCodSisElec().length() != 6) {
                return new MessageDTO(Constantes.ERROR, "El código de sistema eléctrico debe tener 6 caracteres.");
            }
            if (record.getNomSisElec() != null && record.getNomSisElec().length() > 200) {
                return new MessageDTO(Constantes.ERROR, "El nombre del sistema eléctrico no debe exceder 200 caracteres.");
            }
            if (record.getCodEmpresa() != null && record.getCodEmpresa().length() != 4) {
                return new MessageDTO(Constantes.ERROR, "El código de empresa debe tener 4 caracteres.");
            }
            if (record.getTipSisElec() != null && record.getTipSisElec().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El tipo de sistema eléctrico debe tener 1 carácter.");
            }
            if (record.getCodSectorTipico() != null && record.getCodSectorTipico().length() != 3) {
                return new MessageDTO(Constantes.ERROR, "El código de sector típico debe tener 3 caracteres.");
            }
            if (record.getEsSisElec() == null || (!record.getEsSisElec().equals("0") && !record.getEsSisElec().equals("1"))) {
                return new MessageDTO(Constantes.ERROR, "El estado del sistema eléctrico debe ser '0' o '1'.");
            }

            AdmSistemaElectrico existe = admSistemaElectricoMapper.selectByPrimaryKey(record.getCodSisElec());
            if (existe == null) {
                return new MessageDTO(Constantes.ERROR, "El sistema eléctrico no existe");
            }

            SistemaElectricoInsertDTO sistemaElectrico = new SistemaElectricoInsertDTO();
            sistemaElectrico.setCodSisElec(record.getCodSisElec());
            sistemaElectrico.setNomSisElec(record.getNomSisElec());
            sistemaElectrico.setCodEmpresa(record.getCodEmpresa());
            sistemaElectrico.setTipSisElec(record.getTipSisElec());
            sistemaElectrico.setCodSectorTipico(record.getCodSectorTipico());
            sistemaElectrico.setFecIntercon(record.getFecIntercon());
            sistemaElectrico.setAreaDemanda(record.getAreaDemanda());
            sistemaElectrico.setEsSisElec(record.getEsSisElec());

            if ("1".equals(record.getEsSisElec())) {
                sistemaElectrico.setFecAlta(record.getFecAlta());
            } else if ("0".equals(record.getEsSisElec())) {
                sistemaElectrico.setFecBaja(record.getFecBaja());
            }

            sistemaElectrico.setUsActualizacion("admin");
            sistemaElectrico.setFeActualizacion(new Date());
            sistemaElectrico.setIpActualizacion(IpUtils.getClientIp(request));

            admSistemaElectricoMapper.actualizarDos(sistemaElectrico);
            admSistemaElectricoDetMapper.deleteByCodSisElec(record.getCodSisElec());

            if (record.getSistemaElectricoDets() != null && !record.getSistemaElectricoDets().isEmpty()) {
                for (SistemaElectricoDetDTO detDTO : record.getSistemaElectricoDets()) {
                    SistemaElectricoDetDTO det = new SistemaElectricoDetDTO();
                    det.setCodSisElec(record.getCodSisElec());
                    det.setFechaAsociacion(detDTO.getFechaAsociacion());
                    det.setCodSisElecAnt(detDTO.getCodSisElecAnt());
                    det.setAdCodUsuario("admin");
                    det.setAdFecha(new Date());

                    Integer maxId = admSistemaElectricoDetMapper.selectMaxIdSeDet();
                    det.setIdSeDet(maxId + 1);

                    int rowsAffected = admSistemaElectricoDetMapper.insertarDos(det);
                    logger.info("Insertado detalle ID {}: filas afectadas {}", det.getIdSeDet(), rowsAffected);
                }
            }

            return new MessageDTO(Constantes.SUCCES, "Se actualizó correctamente el sistema eléctrico y sus detalles.");
        } catch (Exception e) {
            logger.error("Error al actualizar sistema eléctrico", e);
            return new MessageDTO(Constantes.ERROR, "Error al actualizar: " + e.getMessage());
        }
    }


    @Override
    public List<AdmSistemaElectricoDTO> selectAll() {
        return admSistemaElectricoMapper.selectAll();
    }

    @Override
    public PageInfo<AdmSistemaElectricoDTO> filtrar(AdmSistemaElectricoSearchDTO admSistemaElectricoSearchDTO) {
        try {
            String sortField = mapSortField(admSistemaElectricoSearchDTO.getSort());
            String order = validateOrder(admSistemaElectricoSearchDTO.getOrder());

            PageHelper.startPage(admSistemaElectricoSearchDTO.getPage(), admSistemaElectricoSearchDTO.getSize(), sortField + " " + order);

            List<AdmSistemaElectricoDTO> resultList = admSistemaElectricoMapper.filtrar(admSistemaElectricoSearchDTO);
            return new PageInfo<>(resultList);
        } catch (Exception e) {
            logger.error("Error al filtrar sistemas eléctricos", e);
            return new PageInfo<>();
        }
    }

    private String mapSortField(String sort) {
        switch (sort != null ? sort : "") {
            case "codSisElec": return "COD_SIS_ELEC";
            case "nomSisElec": return "NOM_SIS_ELEC";
            case "codEmpresa": return "COD_EMPRESA";
            case "tipSisElec": return "TIP_SIS_ELEC";
            case "codSectorTipico": return "COD_SECTOR_TIPICO";
            default: return "COD_SIS_ELEC";
        }
    }

    private String validateOrder(String order) {
        if ("ASC".equalsIgnoreCase(order) || "DESC".equalsIgnoreCase(order)) {
            return order.toUpperCase();
        }
        return "ASC";
    }
}
