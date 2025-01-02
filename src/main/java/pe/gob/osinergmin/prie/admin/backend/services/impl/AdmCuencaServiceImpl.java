package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmCentralGeneracion;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.cuenca.AdmCuencaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.cuenca.AdmCuencaFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.cuenca.AdmCuencaSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmCuencaMapper;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmCuenca;
import pe.gob.osinergmin.prie.admin.backend.services.AdmCuencaService;

import java.util.Date;
import java.util.List;

@Service
public class AdmCuencaServiceImpl implements AdmCuencaService{

    @Autowired
    private AdmCuencaMapper admCuencaMapper;

    @Autowired
    private HttpServletRequest request;

    @Transactional
    @Override
    public MessageDTO deleteByPrimaryKey(String codCuenca) {
        try {
            AdmCuenca existe = admCuencaMapper.selectByPrimaryKey(codCuenca);
            if (existe != null) {
                admCuencaMapper.deleteByPrimaryKey(codCuenca);
                return new MessageDTO(Constantes.SUCCES, "Cuenca eliminada con éxito");
            }
            return new MessageDTO(Constantes.ERROR, "Cuenca no encontrada");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Transactional
    @Override
    public MessageDTO insert(AdmCuencaFormdDTO admCuencaFormdDTO) {
        try {
            String usuario = "Admin";
            if (admCuencaFormdDTO.getCodCuenca() == null || admCuencaFormdDTO.getCodCuenca().length() > 6) {
                return new MessageDTO(Constantes.ERROR, "El código de la cuenca no debe exceder 6 caracteres");
            }

            if (admCuencaFormdDTO.getNomCuenca() != null && admCuencaFormdDTO.getNomCuenca().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "El nombre de la cuenca no debe exceder 100 caracteres");
            }
            if (admCuencaFormdDTO.getCodCoes() != null && admCuencaFormdDTO.getCodCoes().length() > 10) {
                return new MessageDTO(Constantes.ERROR, "El código COES no debe exceder 10 caracteres");
            }
            if (admCuencaFormdDTO.getEstadoRegistro() == null || admCuencaFormdDTO.getEstadoRegistro().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El estado de registro debe tener 1 carácter");
            }

            AdmCuenca existe = admCuencaMapper.selectByPrimaryKey(admCuencaFormdDTO.getCodCuenca());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "La cuenca ya existe");
            }

            AdmCuenca admCuenca = new AdmCuenca();
            admCuenca.setCodCuenca(admCuencaFormdDTO.getCodCuenca());
            admCuenca.setNomCuenca(admCuencaFormdDTO.getNomCuenca());
            admCuenca.setCodCoes(admCuencaFormdDTO.getCodCoes());
            admCuenca.setEstadoRegistro(admCuencaFormdDTO.getEstadoRegistro());
            admCuenca.setCodCuencaAnt(admCuencaFormdDTO.getCodCuencaAnt());

            admCuenca.setUsuarioCreacion(usuario);
            admCuenca.setTerminalCreacion(IpUtils.getClientIp(request));
            admCuenca.setFechaCreacion(new Date());

            admCuencaMapper.insert(admCuenca);
            return new MessageDTO(Constantes.SUCCES, "Cuenca insertada correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }


    @Override
    public int insertSelective(AdmCuenca record) {
        return admCuencaMapper.insertSelective(record);
    }

    @Override
    public AdmCuenca selectByPrimaryKey(String codCuenca) {
        return admCuencaMapper.selectByPrimaryKey(codCuenca);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmCuenca record) {
        return admCuencaMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(AdmCuencaFormdDTO admCuencaFormdDTO) {
        try {
            String usuario = "Admin";
            if (admCuencaFormdDTO.getCodCuenca() == null || admCuencaFormdDTO.getCodCuenca().length() > 6) {
                return new MessageDTO(Constantes.ERROR, "El código de la cuenca no debe exceder 6 caracteres");
            }
            if (admCuencaFormdDTO.getNomCuenca() != null && admCuencaFormdDTO.getNomCuenca().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "El nombre de la cuenca no debe exceder 100 caracteres");
            }
            if (admCuencaFormdDTO.getCodCoes() != null && admCuencaFormdDTO.getCodCoes().length() > 10) {
                return new MessageDTO(Constantes.ERROR, "El código COES no debe exceder 10 caracteres");
            }
            if (admCuencaFormdDTO.getEstadoRegistro() == null || admCuencaFormdDTO.getEstadoRegistro().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El estado de registro debe tener 1 carácter");
            }

            AdmCuenca existe = admCuencaMapper.selectByPrimaryKey(admCuencaFormdDTO.getCodCuenca());
            if (existe != null) {
                AdmCuenca admCuenca = new AdmCuenca();
                admCuenca.setCodCuenca(admCuencaFormdDTO.getCodCuenca());
                admCuenca.setNomCuenca(admCuencaFormdDTO.getNomCuenca());
                admCuenca.setCodCoes(admCuencaFormdDTO.getCodCoes());
                admCuenca.setEstadoRegistro(admCuencaFormdDTO.getEstadoRegistro());
                admCuenca.setCodCuencaAnt(admCuencaFormdDTO.getCodCuencaAnt());

                admCuenca.setUsuarioActualizacion(usuario);
                admCuenca.setTerminalActualizacion(IpUtils.getClientIp(request));
                admCuenca.setFechaActualizacion(new Date());

                admCuencaMapper.updateByPrimaryKey(admCuenca);
                return new MessageDTO(Constantes.SUCCES, "Cuenca actualizada correctamente");
            }

            return new MessageDTO(Constantes.ERROR, "Cuenca no encontrada");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public List<AdmCuencaDTO> selectAll() {
        return admCuencaMapper.selectAll();
    }

    @Override
    public PageInfo<AdmCuencaDTO> filtro(AdmCuencaSearchDTO admCuencaSearchDTO) {
        try {
            String sortField = mapSortField(
                    admCuencaSearchDTO.getSort() != null && !admCuencaSearchDTO.getSort().isEmpty()
                            ? admCuencaSearchDTO.getSort()
                            : "codCuenca"
            );

            String order = validateOrder(
                    admCuencaSearchDTO.getOrder() != null && !admCuencaSearchDTO.getOrder().isEmpty()
                            ? admCuencaSearchDTO.getOrder()
                            : "ASC"
            );

            if (sortField != null && !sortField.isEmpty() && order != null && !order.isEmpty()) {
                PageHelper.startPage(admCuencaSearchDTO.getPage(), admCuencaSearchDTO.getSize(), sortField + " " + order);
            } else {
                PageHelper.startPage(admCuencaSearchDTO.getPage(), admCuencaSearchDTO.getSize());
            }

            List<AdmCuencaDTO> resultList = admCuencaMapper.filtro(admCuencaSearchDTO);
            return new PageInfo<>(resultList);
        } catch (Exception e) {
            e.printStackTrace();
            return new PageInfo<>();
        }
    }

    private String mapSortField(String sort) {
        switch (sort) {
            case "codCuenca": return "COD_CUENCA";
            case "nomCuenca": return "NOM_CUENCA";
            case "codCoes": return "COD_COES";
            case "estadoRegistro": return "ESTADO_REGISTRO";
            case "codCuencaAnt": return "COD_CUENCA_ANT";
            default: return "COD_CUENCA";
        }
    }

    private String validateOrder(String order) {
        return ("ASC".equalsIgnoreCase(order) || "DESC".equalsIgnoreCase(order)) ? order.toUpperCase() : "ASC";
    }

}
