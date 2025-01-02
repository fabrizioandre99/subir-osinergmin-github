package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmEmbalse;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmGrupoGeneracion;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.embalse.AdmEmbalseDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.embalse.AdmEmbalseFomrDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.embalse.AdmEmbalseSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmEmbalseMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmEmbalseService;

import java.util.Date;
import java.util.List;

@Service
public class AdmEmbalseServiceImpl implements AdmEmbalseService{

    @Autowired
    private AdmEmbalseMapper admEmbalseMapper;

    @Autowired
    private HttpServletRequest request;

    @Transactional
    @Override
    public MessageDTO deleteByPrimaryKey(String codEmbalse) {
        try {
            AdmEmbalse existe = admEmbalseMapper.selectByPrimaryKey(codEmbalse);

            if (existe != null) {
                admEmbalseMapper.deleteByPrimaryKey(codEmbalse);
                return new MessageDTO(Constantes.SUCCES, "Embalse eliminado con éxito");
            }
            return new MessageDTO(Constantes.ERROR, "Embalse no encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Transactional
    @Override
    public MessageDTO insert(AdmEmbalseFomrDTO admEmbalseFomrDTO) {
        try {
            String usuario = "Admin";
            if (admEmbalseFomrDTO.getCodEmbalse() == null || admEmbalseFomrDTO.getCodEmbalse().length() > 6) {
                return new MessageDTO(Constantes.ERROR, "El código del embalse debe tener hasta 6 caracteres");
            }

            if (admEmbalseFomrDTO.getNomEmbalse() != null && admEmbalseFomrDTO.getNomEmbalse().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "El nombre del embalse no debe exceder 100 caracteres");
            }
            if (admEmbalseFomrDTO.getCodCoes() != null && admEmbalseFomrDTO.getCodCoes().length() > 10) {
                return new MessageDTO(Constantes.ERROR, "El código COES no debe exceder 10 caracteres");
            }
            if (admEmbalseFomrDTO.getEstadoRegistro() == null || admEmbalseFomrDTO.getEstadoRegistro().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El estado de registro debe tener 1 carácter");
            }

            AdmEmbalse existe = admEmbalseMapper.selectByPrimaryKey(admEmbalseFomrDTO.getCodEmbalse());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "El embalse ya existe");
            }

            AdmEmbalse admEmbalse = new AdmEmbalse();
            admEmbalse.setCodEmbalse(admEmbalseFomrDTO.getCodEmbalse());
            admEmbalse.setNomEmbalse(admEmbalseFomrDTO.getNomEmbalse());
            admEmbalse.setCodCoes(admEmbalseFomrDTO.getCodCoes());
            admEmbalse.setEstadoRegistro(admEmbalseFomrDTO.getEstadoRegistro());
            admEmbalse.setCodEmbalseAnt(admEmbalseFomrDTO.getCodEmbalseAnt());

            admEmbalse.setUsuarioCreacion(usuario);
            admEmbalse.setTerminalCreacion(IpUtils.getClientIp(request));
            admEmbalse.setFechaCreacion(new Date());
            admEmbalseMapper.insert(admEmbalse);

            return new MessageDTO(Constantes.SUCCES, "Embalse insertado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public int insertSelective(AdmEmbalse record) {
        return admEmbalseMapper.insertSelective(record);
    }

    @Override
    public AdmEmbalse selectByPrimaryKey(String codEmbalse) {
        return admEmbalseMapper.selectByPrimaryKey(codEmbalse);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmEmbalse record) {
        return admEmbalseMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(AdmEmbalseFomrDTO admEmbalseFomrDTO) {
        try {
            String usuario = "Admin";
            if (admEmbalseFomrDTO.getCodEmbalse() == null || admEmbalseFomrDTO.getCodEmbalse().length() > 6) {
                return new MessageDTO(Constantes.ERROR, "El código del embalse debe tener hasta 6 caracteres");
            }

            if (admEmbalseFomrDTO.getNomEmbalse() != null && admEmbalseFomrDTO.getNomEmbalse().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "El nombre del embalse no debe exceder 100 caracteres");
            }
            if (admEmbalseFomrDTO.getCodCoes() != null && admEmbalseFomrDTO.getCodCoes().length() > 10) {
                return new MessageDTO(Constantes.ERROR, "El código COES no debe exceder 10 caracteres");
            }
            if (admEmbalseFomrDTO.getEstadoRegistro() == null || admEmbalseFomrDTO.getEstadoRegistro().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El estado de registro debe tener 1 carácter");
            }

            AdmEmbalse existe = admEmbalseMapper.selectByPrimaryKey(admEmbalseFomrDTO.getCodEmbalse());
            if (existe != null) {
                AdmEmbalse admEmbalse = new AdmEmbalse();
                admEmbalse.setCodEmbalse(admEmbalseFomrDTO.getCodEmbalse());
                admEmbalse.setNomEmbalse(admEmbalseFomrDTO.getNomEmbalse());
                admEmbalse.setCodCoes(admEmbalseFomrDTO.getCodCoes());
                admEmbalse.setEstadoRegistro(admEmbalseFomrDTO.getEstadoRegistro());
                admEmbalse.setCodEmbalseAnt(admEmbalseFomrDTO.getCodEmbalseAnt());

                admEmbalse.setUsuarioActualizacion(usuario);
                admEmbalse.setTerminalActualizacion(IpUtils.getClientIp(request));
                admEmbalse.setFechaActualizacion(new Date());
                admEmbalseMapper.updateByPrimaryKey(admEmbalse);

                return new MessageDTO(Constantes.SUCCES, "Embalse actualizado correctamente");
            }

            return new MessageDTO(Constantes.ERROR, "Embalse no encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public List<AdmEmbalseDTO> selectAll() {
        return admEmbalseMapper.selectAll();
    }

    @Override
    public PageInfo<AdmEmbalseDTO> filtro(AdmEmbalseSearchDTO admEmbalseSearchDTO) {
        try {
            String sortField = mapSortField(
                    admEmbalseSearchDTO.getSort() != null && !admEmbalseSearchDTO.getSort().isEmpty()
                            ? admEmbalseSearchDTO.getSort()
                            : "codEmbalse"
            );

            String order = validateOrder(
                    admEmbalseSearchDTO.getOrder() != null && !admEmbalseSearchDTO.getOrder().isEmpty()
                            ? admEmbalseSearchDTO.getOrder()
                            : "ASC"
            );

            PageHelper.startPage(admEmbalseSearchDTO.getPage(), admEmbalseSearchDTO.getSize(), sortField + " " + order);

            List<AdmEmbalseDTO> resultList = admEmbalseMapper.filtro(admEmbalseSearchDTO);
            return new PageInfo<>(resultList);
        } catch (Exception e) {
            e.printStackTrace();
            return new PageInfo<>();
        }
    }

    private String mapSortField(String sort) {
        switch (sort) {
            case "codEmbalse":
                return "COD_EMBALSE";
            case "nomEmbalse":
                return "NOM_EMBALSE";
            case "codCoes":
                return "COD_COES";
            case "estadoRegistro":
                return "ESTADO_REGISTRO";
            case "codEmbalseAnt":
                return "COD_EMBALSE_ANT";
            default:
                return "COD_EMBALSE";
        }
    }

    private String validateOrder(String order) {
        if ("ASC".equalsIgnoreCase(order) || "DESC".equalsIgnoreCase(order)) {
            return order.toUpperCase();
        }
        return "ASC";
    }

}
