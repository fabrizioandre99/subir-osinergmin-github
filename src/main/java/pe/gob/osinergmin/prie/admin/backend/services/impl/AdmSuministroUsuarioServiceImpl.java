package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.suministroUsuario.AdmSuministroUsuarioDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.suministroUsuario.AdmSuministroUsuarioFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.suministroUsuario.AdmSuministroUsuarioSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmSuministroUsuarioMapper;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmSuministroUsuario;
import pe.gob.osinergmin.prie.admin.backend.services.AdmSuministroUsuarioService;

import java.util.List;

@Service
public class AdmSuministroUsuarioServiceImpl implements AdmSuministroUsuarioService {

    @Autowired
    private AdmSuministroUsuarioMapper admSuministroUsuarioMapper;

    @Autowired
    private HttpServletRequest request;

    @Transactional
    @Override
    public MessageDTO insert(AdmSuministroUsuarioFormDTO record) {
        try {
            if (record.getCodSuministroUsuario() == null || record.getCodSuministroUsuario().length() > 20) {
                return new MessageDTO(Constantes.ERROR, "El código de suministro usuario no puede tener más de 20 caracteres.");
            }
            if (record.getCodUsuarioLibre() != null && record.getCodUsuarioLibre().length() > 11) {
                return new MessageDTO(Constantes.ERROR, "El código de usuario libre no puede tener más de 11 caracteres.");
            }
            if (record.getNombreUsuarioLibre() != null && record.getNombreUsuarioLibre().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "El nombre de usuario libre no puede tener más de 100 caracteres.");
            }
            if (record.getDireccionSuministro() != null && record.getDireccionSuministro().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "La dirección de suministro no puede tener más de 100 caracteres.");
            }
            if (record.getNroSuministroEmp() != null && record.getNroSuministroEmp().length() > 12) {
                return new MessageDTO(Constantes.ERROR, "El número de suministro empresa no puede tener más de 12 caracteres.");
            }

            AdmSuministroUsuario existe = admSuministroUsuarioMapper.selectByPrimaryKey(record.getCodSuministroUsuario());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "El suministro usuario ya existe.");
            }

            AdmSuministroUsuario admSuministroUsuario = new AdmSuministroUsuario();
            admSuministroUsuario.setCodSuministroUsuario(record.getCodSuministroUsuario());
            admSuministroUsuario.setCodUsuarioLibre(record.getCodUsuarioLibre());
            admSuministroUsuario.setNombreUsuarioLibre(record.getNombreUsuarioLibre());
            admSuministroUsuario.setDireccionSuministro(record.getDireccionSuministro());
            admSuministroUsuario.setNroSuministroEmp(record.getNroSuministroEmp());
            admSuministroUsuario.setUbigeo(record.getUbigeo());
            admSuministroUsuario.setCodActividadEconomica(record.getCodActividadEconomica());
            admSuministroUsuario.setCodCoes(record.getCodCoes());
            admSuministroUsuario.setCodSuministroUsuarioAnt(record.getCodSuministroUsuarioAnt());
            admSuministroUsuario.setCodciiu(record.getCodciiu());
            admSuministroUsuario.setCodPtoSuministro(record.getCodPtoSuministro());
            admSuministroUsuario.setCodBrg(record.getCodBrg());
            admSuministroUsuario.setAreaDemanda(record.getAreaDemanda());
            admSuministroUsuario.setCodSisElectrico(record.getCodSisElectrico());
            admSuministroUsuarioMapper.insert(admSuministroUsuario);

            return new MessageDTO(Constantes.SUCCES, "Se insertó correctamente el suministro usuario.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }


    @Override
    public int insertSelective(AdmSuministroUsuario record) {
        return admSuministroUsuarioMapper.insertSelective(record);
    }

    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(AdmSuministroUsuarioFormDTO record) {
        try {
            if (record.getCodSuministroUsuario() == null || record.getCodSuministroUsuario().length() > 20) {
                return new MessageDTO(Constantes.ERROR, "El código de suministro usuario no puede tener más de 20 caracteres.");
            }
            if (record.getCodUsuarioLibre() != null && record.getCodUsuarioLibre().length() > 11) {
                return new MessageDTO(Constantes.ERROR, "El código de usuario libre no puede tener más de 11 caracteres.");
            }
            if (record.getNombreUsuarioLibre() != null && record.getNombreUsuarioLibre().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "El nombre de usuario libre no puede tener más de 100 caracteres.");
            }
            if (record.getDireccionSuministro() != null && record.getDireccionSuministro().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "La dirección de suministro no puede tener más de 100 caracteres.");
            }
            if (record.getNroSuministroEmp() != null && record.getNroSuministroEmp().length() > 12) {
                return new MessageDTO(Constantes.ERROR, "El número de suministro empresa no puede tener más de 12 caracteres.");
            }

            AdmSuministroUsuario existe = admSuministroUsuarioMapper.selectByPrimaryKey(record.getCodSuministroUsuario());
            if (existe != null) {
                AdmSuministroUsuario admSuministroUsuario = new AdmSuministroUsuario();
                admSuministroUsuario.setCodSuministroUsuario(record.getCodSuministroUsuario());
                admSuministroUsuario.setCodUsuarioLibre(record.getCodUsuarioLibre());
                admSuministroUsuario.setNombreUsuarioLibre(record.getNombreUsuarioLibre());
                admSuministroUsuario.setDireccionSuministro(record.getDireccionSuministro());
                admSuministroUsuario.setNroSuministroEmp(record.getNroSuministroEmp());
                admSuministroUsuario.setUbigeo(record.getUbigeo());
                admSuministroUsuario.setCodActividadEconomica(record.getCodActividadEconomica());
                admSuministroUsuario.setCodCoes(record.getCodCoes());
                admSuministroUsuario.setCodSuministroUsuarioAnt(record.getCodSuministroUsuarioAnt());
                admSuministroUsuario.setCodciiu(record.getCodciiu());
                admSuministroUsuario.setCodPtoSuministro(record.getCodPtoSuministro());
                admSuministroUsuario.setCodBrg(record.getCodBrg());
                admSuministroUsuario.setAreaDemanda(record.getAreaDemanda());
                admSuministroUsuario.setCodSisElectrico(record.getCodSisElectrico());

                admSuministroUsuarioMapper.updateByPrimaryKey(admSuministroUsuario);

                return new MessageDTO(Constantes.SUCCES, "Se actualizó correctamente el suministro usuario.");
            }

            return new MessageDTO(Constantes.ERROR, "No se encontró el suministro usuario para actualizar.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Transactional
    @Override
    public MessageDTO deleteByPrimaryKey(String codSuministroUsuario) {
        try {
            if (codSuministroUsuario == null || codSuministroUsuario.isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código de suministro usuario no puede estar vacío.");
            }
            if (codSuministroUsuario.length() > 20) {
                return new MessageDTO(Constantes.ERROR, "El código de suministro usuario no puede tener más de 20 caracteres.");
            }
            AdmSuministroUsuario existe = admSuministroUsuarioMapper.selectByPrimaryKey(codSuministroUsuario);
            if (existe != null) {
                admSuministroUsuarioMapper.deleteByPrimaryKey(codSuministroUsuario);
                return new MessageDTO(Constantes.SUCCES, "Se eliminó el suministro usuario.");
            }

            return new MessageDTO(Constantes.ERROR, "No se encontró el suministro usuario para eliminar.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public List<AdmSuministroUsuarioDTO> selectAll() {
        return admSuministroUsuarioMapper.selectAll();
    }


    @Override
    public PageInfo<AdmSuministroUsuarioDTO> filtrar(AdmSuministroUsuarioSearchDTO admSuministroUsuarioSearchDTO) {
        String sortField = mapSortField(admSuministroUsuarioSearchDTO.getSort());
        String order = (admSuministroUsuarioSearchDTO.getOrder() != null && !admSuministroUsuarioSearchDTO.getOrder().isEmpty())
                ? admSuministroUsuarioSearchDTO.getOrder()
                : "ASC";

        if (admSuministroUsuarioSearchDTO.getCodSuministroUsuario() != null) {
            admSuministroUsuarioSearchDTO.setCodSuministroUsuario(
                    admSuministroUsuarioSearchDTO.getCodSuministroUsuario().toUpperCase().replaceAll("[ÁÉÍÓÚ]", "AEIOU")
            );
        }
        if (admSuministroUsuarioSearchDTO.getNombreUsuarioLibre() != null) {
            admSuministroUsuarioSearchDTO.setNombreUsuarioLibre(
                    admSuministroUsuarioSearchDTO.getNombreUsuarioLibre().toUpperCase().replaceAll("[ÁÉÍÓÚ]", "AEIOU")
            );
        }
        if (admSuministroUsuarioSearchDTO.getCodUsuarioLibre() != null) {
            admSuministroUsuarioSearchDTO.setCodUsuarioLibre(
                    admSuministroUsuarioSearchDTO.getCodUsuarioLibre().toUpperCase().replaceAll("[ÁÉÍÓÚ]", "AEIOU")
            );
        }

        PageHelper.startPage(admSuministroUsuarioSearchDTO.getPage(), admSuministroUsuarioSearchDTO.getSize(), sortField + " " + order);
        return new PageInfo<>(admSuministroUsuarioMapper.filtrar(admSuministroUsuarioSearchDTO));
    }

    private String mapSortField(String sort) {
        if (sort == null || sort.isEmpty()) {
            return "COD_SUMINISTRO_USUARIO";
        }
        switch (sort) {
            case "codSuministroUsuario":
                return "COD_SUMINISTRO_USUARIO";
            case "codUsuarioLibre":
                return "COD_USUARIO_LIBRE";
            case "nombreUsuarioLibre":
                return "NOMBRE_USUARIO_LIBRE";
            case "codActividadEconomica":
                return "COD_ACTIVIDAD_ECONOMICA";
            default:
                return "COD_SUMINISTRO_USUARIO";
        }
    }

}
