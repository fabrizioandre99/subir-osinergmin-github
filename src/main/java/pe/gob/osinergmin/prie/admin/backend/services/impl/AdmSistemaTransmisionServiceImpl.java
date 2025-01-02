package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.dto.sistemaTransmision.AdmSistemaTransmisionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sistemaTransmision.AdmSistemaTransmisionFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmSistemaTransmision;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sistemaTransmision.AdmSistemaTransmisionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmSistemaTransmisionMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmSistemaTransmisionService;

import java.util.Date;
import java.util.List;

@Service
public class AdmSistemaTransmisionServiceImpl implements AdmSistemaTransmisionService{

    @Autowired
    private AdmSistemaTransmisionMapper admSistemaTransmisionMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public MessageDTO deleteByPrimaryKey(Integer idSisTrans) {
        try {
            AdmSistemaTransmision existe = admSistemaTransmisionMapper.selectByPrimaryKey(idSisTrans);
            if (existe != null) {
                admSistemaTransmisionMapper.deleteByPrimaryKey(idSisTrans);
                return new MessageDTO(Constantes.SUCCES, "OK");
            } else {
                return new MessageDTO(Constantes.ERROR, "El id sistema transmicion no existe");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    @Override
    public MessageDTO insert(AdmSistemaTransmisionFormdDTO record) {
        try {
            if (record.getCodSisTrans() == null || record.getCodSisTrans().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código del sistema de transmisión no puede estar vacío.");
            }
            if (record.getNomSisTrans() == null || record.getNomSisTrans().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El nombre del sistema de transmisión no puede estar vacío.");
            }

            Integer maxId = admSistemaTransmisionMapper.selectMaxId();
            if (maxId == null) {
                maxId = 0;
            }
            Integer newId = maxId + 1;

            AdmSistemaTransmision existe = admSistemaTransmisionMapper.validarExisteCodigo(record.getCodSisTrans());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "El codigo de sistema transmisión ya existe");
            }

            AdmSistemaTransmision admSistemaTransmision = new AdmSistemaTransmision();
            admSistemaTransmision.setIdSisTrans(newId);
            admSistemaTransmision.setCodSisTrans(record.getCodSisTrans());
            admSistemaTransmision.setNomSisTrans(record.getNomSisTrans());
            admSistemaTransmision.setFechaCreacion(new Date());
            admSistemaTransmision.setUsuarioCreacion("admin");
            admSistemaTransmision.setTerminalCreacion(IpUtils.getClientIp(request));
            admSistemaTransmision.setEstado("1");

            admSistemaTransmisionMapper.insert(admSistemaTransmision);

            return new MessageDTO(Constantes.SUCCES, "Registro insertado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Ocurrió un error al insertar el registro.");
        }
    }



    @Override
    public int insertSelective(AdmSistemaTransmision record) {
        return admSistemaTransmisionMapper.insertSelective(record);
    }

    @Override
    public AdmSistemaTransmision selectByPrimaryKey(Integer idSisTrans) {
        return admSistemaTransmisionMapper.selectByPrimaryKey(idSisTrans);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmSistemaTransmision record) {
        return admSistemaTransmisionMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(AdmSistemaTransmisionFormdDTO record) {
        try {
            if (record.getIdSisTrans() == null) {
                return new MessageDTO(Constantes.ERROR, "El ID del sistema de transmisión no puede ser nulo.");
            }

            AdmSistemaTransmision existe = admSistemaTransmisionMapper.selectByPrimaryKey(record.getIdSisTrans());
            if (existe == null) {
                return new MessageDTO(Constantes.ERROR, "No se pudo actualizar, el registro no existe.");
            }

            if (record.getCodSisTrans() == null || record.getCodSisTrans().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código del sistema de transmisión no puede estar vacío.");
            }
            if (record.getNomSisTrans() == null || record.getNomSisTrans().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El nombre del sistema de transmisión no puede estar vacío.");
            }

            AdmSistemaTransmision admSistemaTransmision = new AdmSistemaTransmision();
            admSistemaTransmision.setIdSisTrans(record.getIdSisTrans());
            admSistemaTransmision.setCodSisTrans(record.getCodSisTrans());
            admSistemaTransmision.setNomSisTrans(record.getNomSisTrans());
            admSistemaTransmision.setFechaActualizacion(new Date());
            admSistemaTransmision.setEstado(record.getEstado());
            admSistemaTransmision.setTerminalActualizacion(IpUtils.getClientIp(request));
            admSistemaTransmision.setUsuarioCreacion("adminActualiza");

            admSistemaTransmisionMapper.updateByPrimaryKey(admSistemaTransmision);

            return new MessageDTO(Constantes.SUCCES, "Registro actualizado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Ocurrió un error al actualizar el registro.");
        }
    }


    @Override
    public List<AdmSistemaTransmisionDTO> selectAll() {
        return admSistemaTransmisionMapper.selectAll();
    }

    @Override
    public PageInfo<AdmSistemaTransmisionDTO> filrar(AdmSistemaTransmisionSearchDTO admSistemaTransmisionSearchDTO) {
        try {
            String sortField = mapSortField(admSistemaTransmisionSearchDTO.getSort());
            String orderDirection = validateOrder(admSistemaTransmisionSearchDTO.getOrder());
            String orderBy = sortField + " " + orderDirection;

            PageHelper.startPage(admSistemaTransmisionSearchDTO.getPage(), admSistemaTransmisionSearchDTO.getSize(), orderBy);
            List<AdmSistemaTransmisionDTO> sistemasTransmision = admSistemaTransmisionMapper.filrar(admSistemaTransmisionSearchDTO);

            return new PageInfo<>(sistemasTransmision);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String mapSortField(String sort) {
        if (sort == null || sort.isEmpty()) {
            return "ID_SIS_TRANS";
        }
        switch (sort) {
            case "idSisTrans":
                return "ID_SIS_TRANS";
            case "codSisTrans":
                return "COD_SIS_TRANS";
            case "nomSisTrans":
                return "NOM_SIS_TRANS";
            default:
                return "ID_SIS_TRANS";
        }
    }

    private String validateOrder(String order) {
        if ("asc".equalsIgnoreCase(order) || "desc".equalsIgnoreCase(order)) {
            return order.toUpperCase();
        }
        return "ASC";
    }

}
