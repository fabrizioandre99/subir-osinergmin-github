package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmGrupoGeneracion;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoGeneracion.AdmGrupoGeneracionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoGeneracion.AdmGrupoGeneracionFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoGeneracion.AdmGrupoGeneracionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmGrupoGeneracionMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmGrupoGeneracionService;

import java.util.Date;
import java.util.List;

@Service
public class AdmGrupoGeneracionServiceImpl implements AdmGrupoGeneracionService {

    @Autowired
    private AdmGrupoGeneracionMapper admGrupoGeneracionMapper;

    @Transactional
    @Override
    public MessageDTO deleteByPrimaryKey(String codGrupoGeneracion) {
        try {
            if (codGrupoGeneracion == null || codGrupoGeneracion.length() > 5) {
                return new MessageDTO(Constantes.ERROR, "El código de grupo de generación es obligatorio y debe tener máximo 5 caracteres.");
            }

            AdmGrupoGeneracion existe = admGrupoGeneracionMapper.selectByPrimaryKey(codGrupoGeneracion);
            if (existe != null) {
                admGrupoGeneracionMapper.deleteByPrimaryKey(codGrupoGeneracion);
                return new MessageDTO(Constantes.SUCCES, "Se eliminó el grupo de generación con éxito.");
            }

            return new MessageDTO(Constantes.ERROR, "No se encontró el grupo de generación para eliminar.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Transactional
    @Override
    public MessageDTO insert(AdmGrupoGeneracionFormDTO admGrupoGeneracionFormDTO) {
        try {
            String usuario = "Admin";
            if (admGrupoGeneracionFormDTO.getCodGrupoGeneracion() == null || admGrupoGeneracionFormDTO.getCodGrupoGeneracion().length() > 5) {
                return new MessageDTO(Constantes.ERROR, "El código de grupo de generación es obligatorio y debe tener máximo 5 caracteres.");
            }
            if (admGrupoGeneracionFormDTO.getNomGrupoGeneracion() != null && admGrupoGeneracionFormDTO.getNomGrupoGeneracion().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "El nombre del grupo de generación no puede exceder los 100 caracteres.");
            }
            if (admGrupoGeneracionFormDTO.getCodTipoCombustible() != null && admGrupoGeneracionFormDTO.getCodTipoCombustible().length() > 3) {
                return new MessageDTO(Constantes.ERROR, "El código de tipo de combustible debe tener máximo 3 caracteres.");
            }
            if (admGrupoGeneracionFormDTO.getEstado() == null || admGrupoGeneracionFormDTO.getEstado().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El estado del grupo de generación es obligatorio y debe ser un solo carácter.");
            }

            AdmGrupoGeneracion existe = admGrupoGeneracionMapper.selectByPrimaryKey(admGrupoGeneracionFormDTO.getCodGrupoGeneracion());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "Ya existe un grupo de generación con ese código.");
            }

            AdmGrupoGeneracion admGrupoGeneracion = new AdmGrupoGeneracion();
            admGrupoGeneracion.setCodGrupoGeneracion(admGrupoGeneracionFormDTO.getCodGrupoGeneracion());
            admGrupoGeneracion.setNomGrupoGeneracion(admGrupoGeneracionFormDTO.getNomGrupoGeneracion());
            admGrupoGeneracion.setCodCentralGeneracion(admGrupoGeneracionFormDTO.getCodCentralGeneracion());
            admGrupoGeneracion.setCodTipoCombustible(admGrupoGeneracionFormDTO.getCodTipoCombustible());
            admGrupoGeneracion.setEstado(admGrupoGeneracionFormDTO.getEstado());
            admGrupoGeneracion.setAdFecha(new Date());
            admGrupoGeneracion.setAdCodUsuario(usuario);
            admGrupoGeneracionMapper.insert(admGrupoGeneracion);

            return new MessageDTO(Constantes.SUCCES, "Se insertó de forma correcta el grupo de generación.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(AdmGrupoGeneracionFormDTO admGrupoGeneracionFormDTO) {
        try {
            String usuario = "Admin";
            if (admGrupoGeneracionFormDTO.getCodGrupoGeneracion() == null || admGrupoGeneracionFormDTO.getCodGrupoGeneracion().length() > 5) {
                return new MessageDTO(Constantes.ERROR, "El código de grupo de generación es obligatorio y debe tener máximo 5 caracteres.");
            }
            if (admGrupoGeneracionFormDTO.getNomGrupoGeneracion() != null && admGrupoGeneracionFormDTO.getNomGrupoGeneracion().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "El nombre del grupo de generación no puede exceder los 100 caracteres.");
            }
            if (admGrupoGeneracionFormDTO.getCodTipoCombustible() != null && admGrupoGeneracionFormDTO.getCodTipoCombustible().length() > 3) {
                return new MessageDTO(Constantes.ERROR, "El código de tipo de combustible debe tener máximo 3 caracteres.");
            }
            if (admGrupoGeneracionFormDTO.getEstado() == null || admGrupoGeneracionFormDTO.getEstado().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El estado del grupo de generación es obligatorio y debe ser un solo carácter.");
            }

            AdmGrupoGeneracion existe = admGrupoGeneracionMapper.selectByPrimaryKey(admGrupoGeneracionFormDTO.getCodGrupoGeneracion());
            if (existe == null) {
                return new MessageDTO(Constantes.ERROR, "No se encontró el grupo de generación para actualizar.");
            }

            AdmGrupoGeneracion admGrupoGeneracion = new AdmGrupoGeneracion();
            admGrupoGeneracion.setCodGrupoGeneracion(admGrupoGeneracionFormDTO.getCodGrupoGeneracion());
            admGrupoGeneracion.setNomGrupoGeneracion(admGrupoGeneracionFormDTO.getNomGrupoGeneracion());
            admGrupoGeneracion.setCodCentralGeneracion(admGrupoGeneracionFormDTO.getCodCentralGeneracion());
            admGrupoGeneracion.setCodTipoCombustible(admGrupoGeneracionFormDTO.getCodTipoCombustible());
            admGrupoGeneracion.setEstado(admGrupoGeneracionFormDTO.getEstado());
            admGrupoGeneracion.setAdFecha(new Date());
            admGrupoGeneracion.setAdCodUsuario(usuario);
            admGrupoGeneracionMapper.updateByPrimaryKey(admGrupoGeneracion);

            return new MessageDTO(Constantes.SUCCES, "Se actualizó de forma correcta el grupo de generación.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public List<AdmGrupoGeneracionDTO> selectAll() {
        return admGrupoGeneracionMapper.selectAll();
    }

    @Override
    public PageInfo<AdmGrupoGeneracionDTO> filtro(AdmGrupoGeneracionSearchDTO admGrupoGeneracionSearchDTO) {
        String sortField = mapSortField(admGrupoGeneracionSearchDTO.getSort());
        String sortOrder = validateOrder(admGrupoGeneracionSearchDTO.getOrder());

        PageHelper.startPage(admGrupoGeneracionSearchDTO.getPage(), admGrupoGeneracionSearchDTO.getSize());
        PageHelper.orderBy(sortField + " " + sortOrder);

        List<AdmGrupoGeneracionDTO> resultados = admGrupoGeneracionMapper.filtro(admGrupoGeneracionSearchDTO);
        return new PageInfo<>(resultados);
    }

    private String mapSortField(String sortField) {
        if (sortField == null || sortField.isEmpty()) {
            return "COD_GRUPO_GENERACION";
        }
        switch (sortField.toLowerCase()) {
            case "codgrupogeneracion":
                return "COD_GRUPO_GENERACION";
            case "nomgrupogeneracion":
                return "NOM_GRUPO_GENERACION";
            case "codtipocombustible":
                return "COD_TIPO_COMBUSTIBLE";
            case "codcentralgeneracion":
                return "COD_CENTRAL_GENERACION";
            case "estado":
                return "ESTADO";
            default:
                return "COD_GRUPO_GENERACION";
        }
    }

    private String validateOrder(String order) {
        return (order != null && order.equalsIgnoreCase("desc")) ? "DESC" : "ASC";
    }
}
