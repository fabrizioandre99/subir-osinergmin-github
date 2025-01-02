package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmSistemaTransmision;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoSistTrans.AdmTipoSistTransDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoSistTrans.AdmTipoSistTransFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoSistTrans.AdmTipoSistTransSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmTipoSistTransMapper;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTipoSistTrans;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmTransformadorMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTipoSistTransService;

import java.util.Date;
import java.util.List;

@Service
public class AdmTipoSistTransServiceImpl implements AdmTipoSistTransService{

    @Autowired
    private AdmTipoSistTransMapper admTipoSistTransMapper;

    @Autowired
    private AdmTransformadorMapper admTransformadorMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public MessageDTO deleteByPrimaryKey(Integer idTipoSisTrans) {
        try {
            if (idTipoSisTrans == null) {
                return new MessageDTO(Constantes.ERROR, "El ID del Tipo de Sistema de Transmisión no puede ser nulo.");
            }

            AdmTipoSistTrans existe = admTipoSistTransMapper.selectByPrimaryKey(idTipoSisTrans);
            if (existe != null) {
                admTransformadorMapper.deleteByIdTipoSisTrans(idTipoSisTrans);
                admTipoSistTransMapper.deleteByPrimaryKey(idTipoSisTrans);
                return new MessageDTO(Constantes.SUCCES, "Registro eliminado correctamente.");
            } else {
                return new MessageDTO(Constantes.ERROR, "El ID del Tipo de Sistema de Transmisión no existe.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Ocurrió un error al eliminar el registro.");
        }
    }


    @Transactional
    @Override
    public MessageDTO insert(AdmTipoSistTransFormDTO record) {
        try {
            AdmTipoSistTrans existe = admTipoSistTransMapper.selectByPrimaryKey(record.getIdTipoSisTrans());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "Ya existe el Tipo de Sistema de Transmisión.");
            }

            if (record.getCodTipoSisTrans() == null || record.getCodTipoSisTrans().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código del Tipo de Sistema de Transmisión no puede estar vacío.");
            }
            if (record.getTipSisTrans() == null || record.getTipSisTrans().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "La descripción del Tipo de Sistema de Transmisión no puede estar vacía.");
            }

            Integer maxId = admTipoSistTransMapper.selectMaxId();
            if (maxId == null) {
                maxId = 0;
            }
            Integer newId = maxId + 1;

            AdmTipoSistTrans admTipoSistTrans = new AdmTipoSistTrans();
            admTipoSistTrans.setIdTipoSisTrans(newId);
            admTipoSistTrans.setCodTipoSisTrans(record.getCodTipoSisTrans());
            admTipoSistTrans.setTipSisTrans(record.getTipSisTrans());
            admTipoSistTrans.setFechaCreacion(new Date());
            admTipoSistTrans.setUsuarioCreacion("admin");
            admTipoSistTrans.setTerminalCreacion(IpUtils.getClientIp(request));
            admTipoSistTrans.setEstado("1");

            admTipoSistTransMapper.insert(admTipoSistTrans);

            return new MessageDTO(Constantes.SUCCES, "Registro insertado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Ocurrió un error al insertar el registro.");
        }
    }



    @Override
    public int insertSelective(AdmTipoSistTrans record) {
        return admTipoSistTransMapper.insertSelective(record);
    }

    @Override
    public AdmTipoSistTrans selectByPrimaryKey(Integer idTipoSisTrans) {
        return admTipoSistTransMapper.selectByPrimaryKey(idTipoSisTrans);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmTipoSistTrans record) {
        return admTipoSistTransMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(AdmTipoSistTransFormDTO record) {
        try {
            AdmTipoSistTrans existe = admTipoSistTransMapper.selectByPrimaryKey(record.getIdTipoSisTrans());
            if (existe != null) {
                if (record.getCodTipoSisTrans() == null || record.getCodTipoSisTrans().trim().isEmpty()) {
                    return new MessageDTO(Constantes.ERROR, "El código del Tipo de Sistema de Transmisión no puede estar vacío.");
                }
                if (record.getTipSisTrans() == null || record.getTipSisTrans().trim().isEmpty()) {
                    return new MessageDTO(Constantes.ERROR, "La descripción del Tipo de Sistema de Transmisión no puede estar vacía.");
                }
                if (!record.getEstado().matches("1|0")) {
                    return new MessageDTO(Constantes.ERROR, "El estado debe ser '1' (activo) o '0' (inactivo).");
                }

                AdmTipoSistTrans admTipoSistTrans = new AdmTipoSistTrans();
                admTipoSistTrans.setIdTipoSisTrans(record.getIdTipoSisTrans());
                admTipoSistTrans.setCodTipoSisTrans(record.getCodTipoSisTrans());
                admTipoSistTrans.setTipSisTrans(record.getTipSisTrans());
                admTipoSistTrans.setFechaActualizacion(new Date());
                admTipoSistTrans.setUsuarioActualizacion("adminActualiza");
                admTipoSistTrans.setTerminalActualizacion(IpUtils.getClientIp(request));
                admTipoSistTrans.setEstado(record.getEstado());

                admTipoSistTransMapper.updateByPrimaryKey(admTipoSistTrans);

                return new MessageDTO(Constantes.SUCCES, "Registro actualizado correctamente.");
            } else {
                return new MessageDTO(Constantes.ERROR, "No se pudo actualizar, el registro no existe.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Ocurrió un error al actualizar el registro.");
        }
    }


    @Override
    public List<AdmTipoSistTransDTO> selectAll() {
        return admTipoSistTransMapper.selectAll();
    }

    @Override
    public PageInfo<AdmTipoSistTransDTO> filtrar(AdmTipoSistTransSearchDTO admTipoSistTransSearchDTO) {
        try {
            String sortField = mapSortField(admTipoSistTransSearchDTO.getSort());
            String orderDirection = validateOrder(admTipoSistTransSearchDTO.getOrder());
            String orderBy = sortField + " " + orderDirection;

            PageHelper.startPage(admTipoSistTransSearchDTO.getPage(), admTipoSistTransSearchDTO.getSize(), orderBy);
            List<AdmTipoSistTransDTO> tiposSistTrans = admTipoSistTransMapper.filtrar(admTipoSistTransSearchDTO);

            return new PageInfo<>(tiposSistTrans);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String mapSortField(String sort) {
        if (sort == null || sort.isEmpty()) {
            return "ID_TIPO_SIS_TRANS";
        }
        switch (sort) {
            case "idTipoSisTrans":
                return "ID_TIPO_SIS_TRANS";
            case "codTipoSisTrans":
                return "COD_TIPO_SIS_TRANS";
            case "tipSisTrans":
                return "TIP_SIS_TRANS";
            default:
                return "ID_TIPO_SIS_TRANS";
        }
    }

    private String validateOrder(String order) {
        if ("asc".equalsIgnoreCase(order) || "desc".equalsIgnoreCase(order)) {
            return order.toUpperCase();
        }
        return "ASC";
    }
}
