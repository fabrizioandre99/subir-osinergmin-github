package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmModoOperacion;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmPliegoSiselec;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.pliego.AdmPliegoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.pliego.AdmPliegoSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.pliego.PliegoActualizarDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.pliego.PliegoCrearDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.pliegoSiselec.PliegoSiselecDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.pliegoSiselec.PliegoSiselecFormDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmPliegoMapper;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmPliego;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmPliegoSiselecMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmPliegoService;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class AdmPliegoServiceImpl implements AdmPliegoService{

    @Autowired
    private AdmPliegoMapper admPliegoMapper;

    @Autowired
    private AdmPliegoSiselecMapper admPliegoSiselecMapper;

    private static final Logger logger = LoggerFactory.getLogger(AdmSistemaElectricoServiceImpl.class);

    @Transactional
    @Override
    public MessageDTO deleteByPrimaryKey(String codPliego) {
        try {
            if (codPliego == null || codPliego.isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código del pliego es requerido.");
            }

            AdmPliego existe = admPliegoMapper.selectByPrimaryKey(codPliego);

            if (existe != null) {
                admPliegoSiselecMapper.deleteByCodPliego(codPliego);
                admPliegoMapper.deleteByPrimaryKey(codPliego);
                return new MessageDTO(Constantes.SUCCES, "Pliego eliminado con éxito.");
            }
            return new MessageDTO(Constantes.ERROR, "El pliego no existe. No se pudo eliminar.");
        } catch (Exception e) {
            logger.error("Error al eliminar el pliego", e);
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Transactional
    @Override
    public MessageDTO insert(PliegoCrearDTO pliegoCrearDTO) {
        try {
            if (pliegoCrearDTO.getCodPliego() == null || pliegoCrearDTO.getCodPliego().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código del pliego es requerido.");
            }
            if (pliegoCrearDTO.getCodPliego().length() != 6) {
                return new MessageDTO(Constantes.ERROR, "El código del pliego debe tener 6 caracteres.");
            }
            if (pliegoCrearDTO.getNomPliego() != null && pliegoCrearDTO.getNomPliego().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "El nombre del pliego no debe exceder 100 caracteres.");
            }

            AdmPliego existe = admPliegoMapper.selectByPrimaryKey(pliegoCrearDTO.getCodPliego());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "Ya existe un pliego con el código proporcionado.");
            }

            AdmPliego admPliego = new AdmPliego();
            admPliego.setCodPliego(pliegoCrearDTO.getCodPliego());
            admPliego.setNomPliego(pliegoCrearDTO.getNomPliego());
            admPliego.setFecAlta(new Date());
            admPliego.setAdFecha(new Date());
            admPliego.setAdCodUsuario("admin");
            admPliegoMapper.insert(admPliego);

            return new MessageDTO(Constantes.SUCCES, "Pliego insertado correctamente.");
        } catch (Exception e) {
            logger.error("Error al insertar el pliego", e);
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }


    @Override
    public int insertSelective(AdmPliego record) {
        return admPliegoMapper.insertSelective(record);
    }

    @Override
    public AdmPliego selectByPrimaryKey(String codPliego) {
        return admPliegoMapper.selectByPrimaryKey(codPliego);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmPliego record) {
        return admPliegoMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(PliegoActualizarDTO pliegoActualizarDTO) {
        try {

            if (pliegoActualizarDTO.getCodPliego() == null || pliegoActualizarDTO.getCodPliego().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código del pliego es requerido.");
            }
            if (pliegoActualizarDTO.getCodPliego().length() != 6) {
                return new MessageDTO(Constantes.ERROR, "El código del pliego debe tener 6 caracteres.");
            }
            if (pliegoActualizarDTO.getNomPliego() != null && pliegoActualizarDTO.getNomPliego().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "El nombre del pliego no debe exceder 100 caracteres.");
            }

            AdmPliego existe = admPliegoMapper.selectByPrimaryKey(pliegoActualizarDTO.getCodPliego());
            if (existe == null) {
                return new MessageDTO(Constantes.ERROR, "El pliego no existe.");
            }

            AdmPliego admPliego = new AdmPliego();
            admPliego.setCodPliego(pliegoActualizarDTO.getCodPliego());
            admPliego.setNomPliego(pliegoActualizarDTO.getNomPliego());
            admPliego.setAdFecha(new Date());
            admPliego.setAdCodUsuario("admin");
            admPliegoMapper.updateByPrimaryKeySelective(admPliego);
            admPliegoSiselecMapper.deleteByCodPliego(pliegoActualizarDTO.getCodPliego());

            if (pliegoActualizarDTO.getSistemaElectronico() != null && !pliegoActualizarDTO.getSistemaElectronico().isEmpty()) {
                for (PliegoSiselecFormDTO pliegoSiselecFormDTO : pliegoActualizarDTO.getSistemaElectronico()) {
                    AdmPliegoSiselec admPliegoSiselec = new AdmPliegoSiselec();
                    admPliegoSiselec.setAnnioMes(pliegoSiselecFormDTO.getAnnioMes());
                    admPliegoSiselec.setCodPliego(pliegoActualizarDTO.getCodPliego());
                    admPliegoSiselec.setCodSisElec(pliegoSiselecFormDTO.getCodSisElec());
                    admPliegoSiselec.setAdFecha(new Date());
                    admPliegoSiselec.setAdCodUsuario("admin");

                    admPliegoSiselecMapper.insert(admPliegoSiselec);
                }
            }

            return new MessageDTO(Constantes.SUCCES, "Pliego actualizado correctamente.");
        } catch (Exception e) {
            logger.error("Error al actualizar pliego", e);
            return new MessageDTO(Constantes.ERROR, "Error al actualizar: " + e.getMessage());
        }
    }

    @Override
    public List<AdmPliegoDTO> selectAllAdmPliegos() {
        return admPliegoMapper.selectAllAdmPliegos();
    }

    @Override
    public PageInfo<AdmPliegoDTO> filtro(AdmPliegoSearchDTO admPliegoSearchDTO) {
        try {
            String sortField = mapSortField(admPliegoSearchDTO.getSort());
            String order = validateOrder(admPliegoSearchDTO.getOrder());
            String orderByClause = sortField + " " + order;

            PageHelper.startPage(admPliegoSearchDTO.getPage(), admPliegoSearchDTO.getSize(), orderByClause);
            List<AdmPliegoDTO> pliegos = admPliegoMapper.filtro(admPliegoSearchDTO);

            for (AdmPliegoDTO pliego : pliegos) {
                List<PliegoSiselecDTO> sistemasElectricos = admPliegoSiselecMapper.getSistemasElectricosByCodPliego(pliego.getCodPliego());
                pliego.setSistemasElectricos(sistemasElectricos);
            }

            if (admPliegoSearchDTO.getSortSistemasElectricos() != null && !admPliegoSearchDTO.getSortSistemasElectricos().isEmpty()) {
                for (AdmPliegoDTO pliego : pliegos) {
                    sortSistemasElectricos(pliego.getSistemasElectricos(), admPliegoSearchDTO.getSortSistemasElectricos(), admPliegoSearchDTO.getOrderSistemasElectricos());
                }
            }

            return new PageInfo<>(pliegos);
        } catch (Exception e) {
            logger.error("Error en el filtrado de pliegos", e);
            return new PageInfo<>();
        }
    }


    private String mapSortField(String sort) {
        switch (sort != null ? sort : "") {
            case "codPliego":
                return "P.COD_PLIEGO";
            case "nomPliego":
                return "P.NOM_PLIEGO";
            case "numSistemasElectricos":
                return "NUM_SISTEMAS_ELECTRICOS";
            default:
                return "P.COD_PLIEGO";
        }
    }
    private void sortSistemasElectricos(List<PliegoSiselecDTO> sistemasElectricos, String sortField, String order) {
        if (sistemasElectricos == null || sistemasElectricos.isEmpty()) {
            return;
        }

        Comparator<PliegoSiselecDTO> comparator;

        switch (sortField) {
            case "annioMes":
                comparator = Comparator.comparing(PliegoSiselecDTO::getAnnioMes);
                break;
            case "codPliego":
                comparator = Comparator.comparing(PliegoSiselecDTO::getCodPliego);
                break;
            case "codSisElec":
                comparator = Comparator.comparing(PliegoSiselecDTO::getCodSisElec);
                break;
            default:
                return;
        }

        if ("DESC".equalsIgnoreCase(order)) {
            comparator = comparator.reversed();
        }

        sistemasElectricos.sort(comparator);
    }


    private String validateOrder(String order) {
        if ("ASC".equalsIgnoreCase(order) || "DESC".equalsIgnoreCase(order)) {
            return order.toUpperCase();
        }
        return "ASC";
    }


}
