package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.areaDemanda.AdmAreaDemandaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.areaDemanda.AdmAreaDemandaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.areaDemanda.AdmAreaDemandaSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmAreaDemandaMapper;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmAreaDemanda;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmDevanadoMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmAreaDemandaService;
import pe.gob.osinergmin.prie.admin.backend.services.AdmDevanadoService;

@Service
public class AdmAreaDemandaServiceImpl implements AdmAreaDemandaService {

    @Autowired
    private AdmAreaDemandaMapper admAreaDemandaMapper;

    @Autowired
    private AdmDevanadoMapper admDevanadoMapper;

    @Autowired
    private HttpServletRequest request;

    @Transactional
    @Override
    public MessageDTO deleteByPrimaryKey(Integer areaDemanda) {
        try {
            if (areaDemanda == null) {
                return new MessageDTO(Constantes.ERROR, "El valor del área demanda es requerido.");
            }

            AdmAreaDemanda existe = admAreaDemandaMapper.selectByPrimaryKey(areaDemanda);
            if (existe == null) {
                return new MessageDTO(Constantes.ERROR, "El área demanda no existe. No se puede eliminar.");
            }
            admDevanadoMapper.borrarPorAreaDemanda(areaDemanda);
            admAreaDemandaMapper.deleteByPrimaryKey(areaDemanda);
            return new MessageDTO(Constantes.SUCCES, "Área demanda eliminada exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Error al eliminar el área demanda: " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public MessageDTO insert(AdmAreaDemandaFormDTO record) {
        try {
            String usuario = "Admin";
            if (record.getAreaDemanda() == null) {
                return new MessageDTO(Constantes.ERROR, "El valor del área demanda es requerido.");
            }
            if (record.getDscAreaDemanda() == null || record.getDscAreaDemanda().trim().isEmpty() || record.getDscAreaDemanda().length() > 20) {
                return new MessageDTO(Constantes.ERROR, "La descripción del área demanda es requerida y no debe exceder 20 caracteres.");
            }
            if (record.getEstado() == null || record.getEstado().trim().isEmpty() || record.getEstado().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El estado del área demanda es requerido y debe tener 1 carácter.");
            }

            AdmAreaDemanda existe = admAreaDemandaMapper.selectByPrimaryKey(record.getAreaDemanda());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "El código del área demanda ya existe.");
            }

            AdmAreaDemanda areaDemanda = new AdmAreaDemanda();
            areaDemanda.setAreaDemanda(record.getAreaDemanda());
            areaDemanda.setDscAreaDemanda(record.getDscAreaDemanda());
            areaDemanda.setEstado(record.getEstado());
            areaDemanda.setNoConjunto(record.getNoConjunto());

            areaDemanda.setAdCodUsuario(usuario);
            areaDemanda.setAdFecha(new Date());
            areaDemanda.setIpCreacion(IpUtils.getClientIp(request));

            admAreaDemandaMapper.insert(areaDemanda);
            return new MessageDTO(Constantes.SUCCES, "Área demanda creada exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Error al crear el área demanda: " + e.getMessage());
        }
    }

    @Override
    public int insertSelective(AdmAreaDemanda record) {
        return admAreaDemandaMapper.insertSelective(record);
    }

    @Override
    public AdmAreaDemanda selectByPrimaryKey(Integer areaDemanda) {
        return admAreaDemandaMapper.selectByPrimaryKey(areaDemanda);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmAreaDemanda record) {
        return admAreaDemandaMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(AdmAreaDemandaFormDTO record) {
        try {
            String usuario = "Admin";
            if (record.getAreaDemanda() == null) {
                return new MessageDTO(Constantes.ERROR, "El valor del área demanda es requerido.");
            }
            if (record.getDscAreaDemanda() == null || record.getDscAreaDemanda().trim().isEmpty() || record.getDscAreaDemanda().length() > 20) {
                return new MessageDTO(Constantes.ERROR, "La descripción del área demanda es requerida y no debe exceder 20 caracteres.");
            }
            if (record.getEstado() == null || record.getEstado().trim().isEmpty() || record.getEstado().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El estado del área demanda es requerido y debe tener 1 carácter.");
            }

            AdmAreaDemanda existe = admAreaDemandaMapper.selectByPrimaryKey(record.getAreaDemanda());
            if (existe != null) {
                AdmAreaDemanda areaDemanda = new AdmAreaDemanda();
                areaDemanda.setAreaDemanda(record.getAreaDemanda());
                areaDemanda.setDscAreaDemanda(record.getDscAreaDemanda());
                areaDemanda.setEstado(record.getEstado());
                areaDemanda.setNoConjunto(record.getNoConjunto());

                areaDemanda.setUsActualizacion(usuario);
                areaDemanda.setFeActualizacion(new Date());
                areaDemanda.setIpActualizacion(IpUtils.getClientIp(request));

                admAreaDemandaMapper.updateByPrimaryKey(areaDemanda);
                return new MessageDTO(Constantes.SUCCES, "Área demanda actualizada exitosamente.");
            } else {
                return new MessageDTO(Constantes.ERROR, "No se pudo actualizar el área demanda porque no existe.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Error al actualizar el área demanda: " + e.getMessage());
        }
    }

    @Override
    public List<AdmAreaDemandaDTO> listarAreas() {
        return admAreaDemandaMapper.listarAreas();
    }

    @Override
    public PageInfo<AdmAreaDemandaDTO> filtrar(AdmAreaDemandaSearchDTO admAreaDemandaSearchDTO) {
        try {
            String sortField = mapSortField(
                    admAreaDemandaSearchDTO.getSort() != null && !admAreaDemandaSearchDTO.getSort().isEmpty()
                            ? admAreaDemandaSearchDTO.getSort()
                            : "AREA_DEMANDA"
            );

            String order = validateOrder(
                    admAreaDemandaSearchDTO.getOrder() != null && !admAreaDemandaSearchDTO.getOrder().isEmpty()
                            ? admAreaDemandaSearchDTO.getOrder()
                            : "ASC"
            );

            PageHelper.startPage(admAreaDemandaSearchDTO.getPage(), admAreaDemandaSearchDTO.getSize(), sortField + " " + order);

            List<AdmAreaDemandaDTO> resultList = admAreaDemandaMapper.filtrar(admAreaDemandaSearchDTO);
            return new PageInfo<>(resultList);
        } catch (Exception e) {
            e.printStackTrace();
            return new PageInfo<>();
        }
    }

    private String mapSortField(String sort) {
        switch (sort) {
            case "areaDemanda":
                return "AREA_DEMANDA";
            case "dscAreaDemanda":
                return "DSC_AREA_DEMANDA";
            case "estado":
                return "ESTADO";
            case "noConjunto":
                return "NO_CONJUNTO";
            default:
                return "AREA_DEMANDA";
        }
    }

    private String validateOrder(String order) {
        if ("ASC".equalsIgnoreCase(order) || "DESC".equalsIgnoreCase(order)) {
            return order.toUpperCase();
        }
        return "ASC";
    }
}
