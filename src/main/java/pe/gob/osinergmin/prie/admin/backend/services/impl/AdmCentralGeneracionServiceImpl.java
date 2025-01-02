package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmGrupoGeneracion;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.centralGeneracion.AdmCentralGeneracionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.centralGeneracion.AdmCentralGeneracionFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.centralGeneracion.AdmCentralGeneracionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoGeneracion.AdmGrupoGeneracionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoGeneracion.AdmGrupoGeneracionFormDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmCentralGeneracionMapper;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmCentralGeneracion;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmGrupoGeneracionMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmCentralGeneracionService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdmCentralGeneracionServiceImpl implements AdmCentralGeneracionService{

    @Autowired
    private AdmCentralGeneracionMapper admCentralGeneracionMapper;


    @Autowired
    private AdmGrupoGeneracionMapper admGrupoGeneracionMapper;

    @Transactional
    @Override
    public MessageDTO deleteByPrimaryKey(String codCentralGeneracion) {
        try {
            if (codCentralGeneracion == null || codCentralGeneracion.length() > 5) {
                return new MessageDTO(Constantes.ERROR, "El código de central de generación es obligatorio y debe tener máximo 5 caracteres.");
            }

            AdmCentralGeneracion existe = admCentralGeneracionMapper.selectByPrimaryKey(codCentralGeneracion);
            if (existe == null) {
                return new MessageDTO(Constantes.ERROR, "No se encontró la central de generación para eliminar.");
            }
            admGrupoGeneracionMapper.eliminarCodCentral(codCentralGeneracion);
            admCentralGeneracionMapper.deleteByPrimaryKey(codCentralGeneracion);
            return new MessageDTO(Constantes.SUCCES, "Central de generación eliminada correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public MessageDTO insert(AdmCentralGeneracionFormdDTO admCentralGeneracionFormdDTO) {
        try {
            String usuario = "Admin";
            if (admCentralGeneracionFormdDTO.getCodCentralGeneracion() == null || admCentralGeneracionFormdDTO.getCodCentralGeneracion().length() > 5) {
                return new MessageDTO(Constantes.ERROR, "El código de central de generación es obligatorio y debe tener máximo 5 caracteres.");
            }
            if (admCentralGeneracionFormdDTO.getNomCentralGeneracion() != null && admCentralGeneracionFormdDTO.getNomCentralGeneracion().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "El nombre de la central de generación no puede exceder los 100 caracteres.");
            }
            if (admCentralGeneracionFormdDTO.getCodTipoCentral() != null && admCentralGeneracionFormdDTO.getCodTipoCentral().length() > 2) {
                return new MessageDTO(Constantes.ERROR, "El código de tipo de central debe tener máximo 2 caracteres.");
            }
            if (admCentralGeneracionFormdDTO.getCodEmpresa() != null && admCentralGeneracionFormdDTO.getCodEmpresa().length() > 4) {
                return new MessageDTO(Constantes.ERROR, "El código de empresa debe tener máximo 4 caracteres.");
            }
            if (admCentralGeneracionFormdDTO.getCodUbigeo() != null && admCentralGeneracionFormdDTO.getCodUbigeo().length() > 6) {
                return new MessageDTO(Constantes.ERROR, "El código de ubigeo debe tener máximo 6 caracteres.");
            }
            if (admCentralGeneracionFormdDTO.getEstado() == null || admCentralGeneracionFormdDTO.getEstado().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El estado es obligatorio y debe ser un solo carácter.");
            }

            AdmCentralGeneracion existe = admCentralGeneracionMapper.selectByPrimaryKey(admCentralGeneracionFormdDTO.getCodCentralGeneracion());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "Ya existe una central de generación con ese código.");
            }

            AdmCentralGeneracion admCentralGeneracion = new AdmCentralGeneracion();
            admCentralGeneracion.setCodCentralGeneracion(admCentralGeneracionFormdDTO.getCodCentralGeneracion());
            admCentralGeneracion.setNomCentralGeneracion(admCentralGeneracionFormdDTO.getNomCentralGeneracion());
            admCentralGeneracion.setCodTipoCentral(admCentralGeneracionFormdDTO.getCodTipoCentral());
            admCentralGeneracion.setCodEmpresa(admCentralGeneracionFormdDTO.getCodEmpresa());
            admCentralGeneracion.setCodUbigeo(admCentralGeneracionFormdDTO.getCodUbigeo());
            admCentralGeneracion.setEstado(admCentralGeneracionFormdDTO.getEstado());
            admCentralGeneracion.setTipo(admCentralGeneracionFormdDTO.getTipo());
            admCentralGeneracion.setAdFecha(new Date());
            admCentralGeneracion.setAdCodUsuario(usuario);

            List<AdmGrupoGeneracionFormDTO> gruposGeneracion = admCentralGeneracionFormdDTO.getGrupoGeneracion();
            if (gruposGeneracion != null && !gruposGeneracion.isEmpty()) {
                for (AdmGrupoGeneracionFormDTO grupoGeneracionFormDTO : gruposGeneracion) {

                    AdmGrupoGeneracion existingGrupo = admGrupoGeneracionMapper.selectByPrimaryKey(grupoGeneracionFormDTO.getCodGrupoGeneracion());
                    if (existingGrupo != null) {
                        return new MessageDTO(Constantes.ERROR, "El código de grupo de generación ya existe, no se puede insertar duplicado.");
                    }

                    if (grupoGeneracionFormDTO.getCodGrupoGeneracion() == null || grupoGeneracionFormDTO.getCodGrupoGeneracion().length() > 5) {
                        return new MessageDTO(Constantes.ERROR, "El código de grupo de generación es obligatorio y debe tener máximo 5 caracteres.");
                    }
                    if (grupoGeneracionFormDTO.getNomGrupoGeneracion() != null && grupoGeneracionFormDTO.getNomGrupoGeneracion().length() > 100) {
                        return new MessageDTO(Constantes.ERROR, "El nombre del grupo de generación no puede exceder los 100 caracteres.");
                    }
                    if (grupoGeneracionFormDTO.getCodTipoCombustible() != null && grupoGeneracionFormDTO.getCodTipoCombustible().length() > 3) {
                        return new MessageDTO(Constantes.ERROR, "El código de tipo de combustible debe tener máximo 3 caracteres.");
                    }
                    if (grupoGeneracionFormDTO.getEstado() == null || grupoGeneracionFormDTO.getEstado().length() != 1) {
                        return new MessageDTO(Constantes.ERROR, "El estado del grupo de generación es obligatorio y debe ser un solo carácter.");
                    }

                    AdmGrupoGeneracion admGrupoGeneracion = new AdmGrupoGeneracion();
                    admGrupoGeneracion.setCodGrupoGeneracion(grupoGeneracionFormDTO.getCodGrupoGeneracion());
                    admGrupoGeneracion.setNomGrupoGeneracion(grupoGeneracionFormDTO.getNomGrupoGeneracion());
                    admGrupoGeneracion.setCodCentralGeneracion(admCentralGeneracionFormdDTO.getCodCentralGeneracion());
                    admGrupoGeneracion.setCodTipoCombustible(grupoGeneracionFormDTO.getCodTipoCombustible());
                    admGrupoGeneracion.setEstado(grupoGeneracionFormDTO.getEstado());
                    admGrupoGeneracion.setAdFecha(new Date());
                    admGrupoGeneracion.setAdCodUsuario(usuario);

                    if ("1".equals(grupoGeneracionFormDTO.getEstado())) {
                        admGrupoGeneracion.setFecAlta(new Date());
                    } else if ("0".equals(grupoGeneracionFormDTO.getEstado())) {
                        admGrupoGeneracion.setFecBaja(new Date());
                    }

                    admGrupoGeneracionMapper.insert(admGrupoGeneracion);
                }
            }
            admCentralGeneracionMapper.insert(admCentralGeneracion);
            return new MessageDTO(Constantes.SUCCES, "Central de generación insertada correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }


    @Override
    public int insertSelective(AdmCentralGeneracion record) {
        return admCentralGeneracionMapper.insertSelective(record);
    }

    @Override
    public AdmCentralGeneracion selectByPrimaryKey(String codCentralGeneracion) {
        return admCentralGeneracionMapper.selectByPrimaryKey(codCentralGeneracion);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmCentralGeneracion record) {
        return admCentralGeneracionMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public MessageDTO updateByPrimaryKey(AdmCentralGeneracionFormdDTO admCentralGeneracionFormdDTO) {
        try {
            String usuario = "Admin";
            if (admCentralGeneracionFormdDTO.getCodCentralGeneracion() == null || admCentralGeneracionFormdDTO.getCodCentralGeneracion().length() > 5) {
                return new MessageDTO(Constantes.ERROR, "El código de central de generación es obligatorio y debe tener máximo 5 caracteres.");
            }
            if (admCentralGeneracionFormdDTO.getNomCentralGeneracion() != null && admCentralGeneracionFormdDTO.getNomCentralGeneracion().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "El nombre de la central de generación no puede exceder los 100 caracteres.");
            }
            if (admCentralGeneracionFormdDTO.getCodTipoCentral() != null && admCentralGeneracionFormdDTO.getCodTipoCentral().length() > 2) {
                return new MessageDTO(Constantes.ERROR, "El código de tipo de central debe tener máximo 2 caracteres.");
            }
            if (admCentralGeneracionFormdDTO.getCodEmpresa() != null && admCentralGeneracionFormdDTO.getCodEmpresa().length() > 4) {
                return new MessageDTO(Constantes.ERROR, "El código de empresa debe tener máximo 4 caracteres.");
            }
            if (admCentralGeneracionFormdDTO.getCodUbigeo() != null && admCentralGeneracionFormdDTO.getCodUbigeo().length() > 6) {
                return new MessageDTO(Constantes.ERROR, "El código de ubigeo debe tener máximo 6 caracteres.");
            }
            if (admCentralGeneracionFormdDTO.getEstado() == null || admCentralGeneracionFormdDTO.getEstado().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El estado es obligatorio y debe ser un solo carácter.");
            }

            AdmCentralGeneracion existe = admCentralGeneracionMapper.selectByPrimaryKey(admCentralGeneracionFormdDTO.getCodCentralGeneracion());
            if (existe == null) {
                return new MessageDTO(Constantes.ERROR, "No se encontró la central de generación para actualizar.");
            }

            AdmCentralGeneracion admCentralGeneracion = new AdmCentralGeneracion();
            admCentralGeneracion.setCodCentralGeneracion(admCentralGeneracionFormdDTO.getCodCentralGeneracion());
            admCentralGeneracion.setNomCentralGeneracion(admCentralGeneracionFormdDTO.getNomCentralGeneracion());
            admCentralGeneracion.setCodTipoCentral(admCentralGeneracionFormdDTO.getCodTipoCentral());
            admCentralGeneracion.setCodEmpresa(admCentralGeneracionFormdDTO.getCodEmpresa());
            admCentralGeneracion.setCodUbigeo(admCentralGeneracionFormdDTO.getCodUbigeo());
            admCentralGeneracion.setEstado(admCentralGeneracionFormdDTO.getEstado());
            admCentralGeneracion.setTipo(admCentralGeneracionFormdDTO.getTipo());
            admCentralGeneracion.setAdFecha(new Date());
            admCentralGeneracion.setAdCodUsuario(usuario);
            admCentralGeneracionMapper.updateByPrimaryKey(admCentralGeneracion);
            admGrupoGeneracionMapper.eliminarCodCentral(admCentralGeneracionFormdDTO.getCodCentralGeneracion());

            List<AdmGrupoGeneracionFormDTO> gruposGeneracion = admCentralGeneracionFormdDTO.getGrupoGeneracion();
            if (gruposGeneracion != null && !gruposGeneracion.isEmpty()) {
                for (AdmGrupoGeneracionFormDTO grupoGeneracionFormDTO : gruposGeneracion) {
                    if (grupoGeneracionFormDTO.getCodGrupoGeneracion() == null || grupoGeneracionFormDTO.getCodGrupoGeneracion().length() > 5) {
                        return new MessageDTO(Constantes.ERROR, "El código de grupo de generación es obligatorio y debe tener máximo 5 caracteres.");
                    }
                    if (grupoGeneracionFormDTO.getNomGrupoGeneracion() != null && grupoGeneracionFormDTO.getNomGrupoGeneracion().length() > 100) {
                        return new MessageDTO(Constantes.ERROR, "El nombre del grupo de generación no puede exceder los 100 caracteres.");
                    }
                    if (grupoGeneracionFormDTO.getCodTipoCombustible() != null && grupoGeneracionFormDTO.getCodTipoCombustible().length() > 3) {
                        return new MessageDTO(Constantes.ERROR, "El código de tipo de combustible debe tener máximo 3 caracteres.");
                    }
                    if (grupoGeneracionFormDTO.getEstado() == null || grupoGeneracionFormDTO.getEstado().length() != 1) {
                        return new MessageDTO(Constantes.ERROR, "El estado del grupo de generación es obligatorio y debe ser un solo carácter.");
                    }

                    AdmGrupoGeneracion admGrupoGeneracion = new AdmGrupoGeneracion();
                    admGrupoGeneracion.setCodGrupoGeneracion(grupoGeneracionFormDTO.getCodGrupoGeneracion());
                    admGrupoGeneracion.setNomGrupoGeneracion(grupoGeneracionFormDTO.getNomGrupoGeneracion());
                    admGrupoGeneracion.setCodCentralGeneracion(admCentralGeneracionFormdDTO.getCodCentralGeneracion());
                    admGrupoGeneracion.setCodTipoCombustible(grupoGeneracionFormDTO.getCodTipoCombustible());
                    admGrupoGeneracion.setEstado(grupoGeneracionFormDTO.getEstado());
                    admGrupoGeneracion.setAdFecha(new Date());
                    admGrupoGeneracion.setAdCodUsuario(usuario);

                    if ("1".equals(grupoGeneracionFormDTO.getEstado())) {
                        admGrupoGeneracion.setFecAlta(new Date());
                    } else if ("0".equals(grupoGeneracionFormDTO.getEstado())) {
                        admGrupoGeneracion.setFecBaja(new Date());
                    }

                    admGrupoGeneracionMapper.insert(admGrupoGeneracion);
                }
            }

            return new MessageDTO(Constantes.SUCCES, "Se actualizó la central de generación y sus grupos asociados correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public List<AdmCentralGeneracionDTO> selectAll() {
        return admCentralGeneracionMapper.selectAll();
    }

    @Override
    public PageInfo<AdmCentralGeneracionDTO> filtro(AdmCentralGeneracionSearchDTO searchDTO) {

        String sortField = mapSortField(searchDTO.getSort());
        String sortOrder = validateOrder(searchDTO.getOrder());

        PageHelper.startPage(searchDTO.getPage(), searchDTO.getSize());
        PageHelper.orderBy(sortField + " " + sortOrder);

        List<AdmCentralGeneracionDTO> resultados = admCentralGeneracionMapper.filtro(searchDTO);

        List<String> codCentralGeneracionList = resultados.stream()
                .map(AdmCentralGeneracionDTO::getCodCentralGeneracion)
                .collect(Collectors.toList());

        if (!codCentralGeneracionList.isEmpty()) {
            List<AdmGrupoGeneracionDTO> allGrupoGeneracionList = admGrupoGeneracionMapper.selectByCodCentralGeneracionList(codCentralGeneracionList);

            Map<String, List<AdmGrupoGeneracionDTO>> grupoGeneracionMap = allGrupoGeneracionList.stream()
                    .collect(Collectors.groupingBy(AdmGrupoGeneracionDTO::getCodCentralGeneracion));

            for (AdmCentralGeneracionDTO centralGeneracion : resultados) {
                String codCentralGeneracion = centralGeneracion.getCodCentralGeneracion();
                List<AdmGrupoGeneracionDTO> grupoGeneracionList = grupoGeneracionMap.getOrDefault(codCentralGeneracion, new ArrayList<>());
                centralGeneracion.setGrupoGeneracion(grupoGeneracionList);
            }
        }

        return new PageInfo<>(resultados);
    }

    private String mapSortField(String sortField) {
        if (sortField == null || sortField.isEmpty()) {
            return "COD_CENTRAL_GENERACION";
        }
        switch (sortField.toLowerCase()) {
            case "codcentralgeneracion":
                return "COD_CENTRAL_GENERACION";
            case "nomcentralgeneracion":
                return "NOM_CENTRAL_GENERACION";
            case "codtipocentral":
                return "COD_TIPO_CENTRAL";
            case "codempresa":
                return "COD_EMPRESA";
            case "codubigeo":
                return "COD_UBIGEO";
            case "estado":
                return "ESTADO";
            case "tipo":
                return "TIPO";
            default:
                return "COD_CENTRAL_GENERACION";
        }
    }

    private String validateOrder(String order) {
        return (order != null && order.equalsIgnoreCase("desc")) ? "desc" : "asc";
    }

}
