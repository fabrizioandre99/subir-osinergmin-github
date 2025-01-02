package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmGrupoGeneracion;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.lago.AdmLagoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.lago.AdmLagoFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.lago.AdmLagoSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmLagoMapper;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmLago;
import pe.gob.osinergmin.prie.admin.backend.services.AdmLagoService;

import java.util.Date;
import java.util.List;

@Service
public class AdmLagoServiceImpl implements AdmLagoService{

    @Autowired
    private AdmLagoMapper admLagoMapper;

    @Autowired
    private HttpServletRequest request;

    @Transactional
    @Override
    public MessageDTO deleteByPrimaryKey(String codLago) {
        try {
            AdmLago existe = admLagoMapper.selectByPrimaryKey(codLago);

            if (existe != null) {
                admLagoMapper.deleteByPrimaryKey(codLago);
                return new MessageDTO(Constantes.SUCCES, "Lago eliminado con éxito");
            }
            return new MessageDTO(Constantes.ERROR, "Lago no encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Transactional
    @Override
    public MessageDTO insert(AdmLagoFormDTO admLagoFormDTO) {
        try {
            if (admLagoFormDTO.getCodLago() == null || admLagoFormDTO.getCodLago().length() > 6) {
                return new MessageDTO(Constantes.ERROR, "El código del lago debe tener hasta 6 caracteres");
            }

            if (admLagoFormDTO.getNomLago() != null && admLagoFormDTO.getNomLago().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "El nombre del lago no debe exceder 100 caracteres");
            }
            if (admLagoFormDTO.getCodCoes() != null && admLagoFormDTO.getCodCoes().length() > 10) {
                return new MessageDTO(Constantes.ERROR, "El código COES no debe exceder 10 caracteres");
            }
            if (admLagoFormDTO.getEstadoRegistro() == null || admLagoFormDTO.getEstadoRegistro().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El estado de registro debe tener 1 carácter");
            }

            AdmLago existe = admLagoMapper.selectByPrimaryKey(admLagoFormDTO.getCodLago());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "El lago ya existe");
            }

            AdmLago admLago = new AdmLago();
            admLago.setCodLago(admLagoFormDTO.getCodLago());
            admLago.setNomLago(admLagoFormDTO.getNomLago());
            admLago.setCodCoes(admLagoFormDTO.getCodCoes());
            admLago.setCodLagoAnt(admLagoFormDTO.getCodLagoAnt());
            admLago.setEstadoRegistro(admLagoFormDTO.getEstadoRegistro());

            admLago.setUsuarioCreacion("Admin");
            admLago.setTerminalCreacion(IpUtils.getClientIp(request));
            admLago.setFechaCreacion(new Date());
            admLagoMapper.insert(admLago);

            return new MessageDTO(Constantes.SUCCES, "Lago insertado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public int insertSelective(AdmLago record) {
        return admLagoMapper.insertSelective(record);
    }

    @Override
    public AdmLago selectByPrimaryKey(String codLago) {
        return admLagoMapper.selectByPrimaryKey(codLago);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmLago record) {
        return admLagoMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(AdmLagoFormDTO admLagoFormDTO) {
        try {
            if (admLagoFormDTO.getCodLago() == null || admLagoFormDTO.getCodLago().length() > 6) {
                return new MessageDTO(Constantes.ERROR, "El código del lago debe tener hasta 6 caracteres");
            }

            if (admLagoFormDTO.getNomLago() != null && admLagoFormDTO.getNomLago().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "El nombre del lago no debe exceder 100 caracteres");
            }
            if (admLagoFormDTO.getCodCoes() != null && admLagoFormDTO.getCodCoes().length() > 10) {
                return new MessageDTO(Constantes.ERROR, "El código COES no debe exceder 10 caracteres");
            }
            if (admLagoFormDTO.getEstadoRegistro() == null || admLagoFormDTO.getEstadoRegistro().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El estado de registro debe tener 1 carácter");
            }

            AdmLago existe = admLagoMapper.selectByPrimaryKey(admLagoFormDTO.getCodLago());
            if (existe != null) {
                AdmLago admLago = new AdmLago();
                admLago.setCodLago(admLagoFormDTO.getCodLago());
                admLago.setNomLago(admLagoFormDTO.getNomLago());
                admLago.setCodCoes(admLagoFormDTO.getCodCoes());
                admLago.setCodLagoAnt(admLagoFormDTO.getCodLagoAnt());
                admLago.setEstadoRegistro(admLagoFormDTO.getEstadoRegistro());

                admLago.setUsuarioActualizacion("Admin");
                admLago.setTerminalActualizacion(IpUtils.getClientIp(request));
                admLago.setFechaActualizacion(new Date());
                admLagoMapper.updateByPrimaryKey(admLago);

                return new MessageDTO(Constantes.SUCCES, "Lago actualizado correctamente");
            }
            return new MessageDTO(Constantes.ERROR, "Lago no encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public List<AdmLagoDTO> selectAll() {
        return admLagoMapper.selectAll();
    }

    @Override
    public PageInfo<AdmLagoDTO> filtro(AdmLagoSearchDTO admLagoSearchDTO) {
        try {
            String sortField = mapSortField(
                    admLagoSearchDTO.getSort() != null && !admLagoSearchDTO.getSort().isEmpty()
                            ? admLagoSearchDTO.getSort()
                            : "codLago"
            );

            String order = validateOrder(
                    admLagoSearchDTO.getOrder() != null && !admLagoSearchDTO.getOrder().isEmpty()
                            ? admLagoSearchDTO.getOrder()
                            : "ASC"
            );

            PageHelper.startPage(admLagoSearchDTO.getPage(), admLagoSearchDTO.getSize(), sortField + " " + order);

            List<AdmLagoDTO> resultList = admLagoMapper.filtro(admLagoSearchDTO);
            return new PageInfo<>(resultList);
        } catch (Exception e) {
            e.printStackTrace();
            return new PageInfo<>();
        }
    }

    private String mapSortField(String sort) {
        switch (sort) {
            case "codLago":
                return "COD_LAGO";
            case "nomLago":
                return "NOM_LAGO";
            case "codCoes":
                return "COD_COES";
            case "estadoRegistro":
                return "ESTADO_REGISTRO";
            case "codLagoAnt":
                return "COD_LAGO_ANT";
            default:
                return "COD_LAGO";
        }
    }

    private String validateOrder(String order) {
        if ("ASC".equalsIgnoreCase(order) || "DESC".equalsIgnoreCase(order)) {
            return order.toUpperCase();
        }
        return "ASC";
    }


}
