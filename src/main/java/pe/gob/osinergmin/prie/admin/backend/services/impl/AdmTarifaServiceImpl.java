package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTarifa;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tarifa.TarifaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tarifa.TarifaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tarifa.TarifaResultDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tarifa.TarifaSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmTarifaMapper;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmTipoTarifaMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTarifaService;

import java.util.Date;
import java.util.List;

@Service
public class AdmTarifaServiceImpl implements AdmTarifaService {

    @Autowired
    private AdmTarifaMapper admTarifaMapper;

    @Autowired
    private AdmTipoTarifaMapper admTipoTarifaMapper;

    @Override
    @Transactional
    public MessageDTO eliminarPorId(Integer idTarifa) {
        try {
            if (idTarifa == null || idTarifa < 0) {
                return new MessageDTO(Constantes.ERROR, "El ID de la tarifa debe ser un número positivo.");
            }

            AdmTarifa tarifaExistente = admTarifaMapper.selectByPrimaryKey(idTarifa);
            if (tarifaExistente == null) {
                return new MessageDTO(Constantes.ERROR, "El ID de la tarifa no existe. No se puede eliminar.");
            }

            admTarifaMapper.deleteByPrimaryKey(idTarifa);
            return new MessageDTO(Constantes.SUCCES, "Tarifa eliminada correctamente.");
        } catch (Exception e) {
            return new MessageDTO(Constantes.ERROR, "Ocurrió un error al eliminar la tarifa: " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public MessageDTO insertarTarifa(TarifaFormDTO formDTO) {
        try {
            if (formDTO.getIdTarifa() == null || formDTO.getIdTarifa().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El ID de la tarifa es obligatorio.");
            }

            try {
                Integer idTarifa = Integer.parseInt(formDTO.getIdTarifa());
                if (idTarifa < 0) {
                    return new MessageDTO(Constantes.ERROR, "El ID de la tarifa debe ser un número positivo.");
                }
                if (String.valueOf(idTarifa).length() > 3) {
                    return new MessageDTO(Constantes.ERROR, "El ID de la tarifa debe tener hasta 3 dígitos.");
                }
                formDTO.setIdTarifa(String.valueOf(idTarifa));
            } catch (NumberFormatException e) {
                return new MessageDTO(Constantes.ERROR, "El ID de la tarifa debe ser un número.");
            }

            AdmTarifa tarifaExistente = admTarifaMapper.selectByPrimaryKey(Integer.parseInt(formDTO.getIdTarifa()));
            if (tarifaExistente != null) {
                return new MessageDTO(Constantes.ERROR, "El ID de la tarifa ya existe. Por favor, elige otro ID.");
            }

            int existeCodTarifa = admTipoTarifaMapper.existeCod(formDTO.getCodigTarifa());
            if (existeCodTarifa == 0) {
                return new MessageDTO(Constantes.ERROR, "El código de tarifa no existe. Verifica el valor ingresado.");
            }

            if (formDTO.getCodigTarifa() == null || formDTO.getCodigTarifa().length() > 10) {
                return new MessageDTO(Constantes.ERROR, "El código de tarifa no es válido. Debe tener hasta 10 caracteres.");
            }

            if (formDTO.getSecuencia() == null || formDTO.getSecuencia() > 999) {
                return new MessageDTO(Constantes.ERROR, "El valor de la secuencia no puede tener más de 3 dígitos enteros.");
            }

            if (formDTO.getNombretarifa() == null || formDTO.getNombretarifa().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "El nombre de la tarifa no puede superar los 100 caracteres.");
            }

            if (formDTO.getTipoMedicion() == null || formDTO.getTipoMedicion().length() != 2) {
                return new MessageDTO(Constantes.ERROR, "El tipo de medición debe tener exactamente 2 caracteres.");
            }

            try {
                int estado = Integer.parseInt(formDTO.getEstado());
                if (estado != 1 && estado != 0) {
                    return new MessageDTO(Constantes.ERROR, "El estado debe ser 1 o 0.");
                }
            } catch (NumberFormatException e) {
                return new MessageDTO(Constantes.ERROR, "El estado debe ser un número válido.");
            }

            AdmTarifa admTarifa = new AdmTarifa();
            admTarifa.setIdTarifa(Integer.parseInt(formDTO.getIdTarifa()));
            admTarifa.setCodTarifa(formDTO.getCodigTarifa());
            admTarifa.setNomTarifa(formDTO.getNombretarifa());
            admTarifa.setSecuencia(formDTO.getSecuencia());
            admTarifa.setTipoMedicion(formDTO.getTipoMedicion());
            admTarifa.setEstado(formDTO.getEstado());
            admTarifa.setAdFecha(new Date());

            admTarifaMapper.insertSelective(admTarifa);
            return new MessageDTO(Constantes.SUCCES, "Tarifa insertada correctamente.");
        } catch (Exception e) {
            return new MessageDTO(Constantes.ERROR, "Ocurrió un error al insertar la tarifa: " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public MessageDTO actualizarTarifa(TarifaFormDTO formDTO) {
        try {
            if (formDTO.getIdTarifa() == null || formDTO.getIdTarifa().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El ID de la tarifa es obligatorio.");
            }

            try {
                Integer idTarifa = Integer.parseInt(formDTO.getIdTarifa());
                if (idTarifa < 0) {
                    return new MessageDTO(Constantes.ERROR, "El ID de la tarifa debe ser un número positivo.");
                }
                if (String.valueOf(idTarifa).length() > 3) {
                    return new MessageDTO(Constantes.ERROR, "El ID de la tarifa debe tener hasta 3 dígitos.");
                }
                formDTO.setIdTarifa(String.valueOf(idTarifa));
            } catch (NumberFormatException e) {
                return new MessageDTO(Constantes.ERROR, "El ID de la tarifa debe ser un número.");
            }

            AdmTarifa tarifaExistente = admTarifaMapper.selectByPrimaryKey(Integer.parseInt(formDTO.getIdTarifa()));
            if (tarifaExistente == null) {
                return new MessageDTO(Constantes.ERROR, "El ID de la tarifa no existe. No se puede actualizar.");
            }

            int existeCodTarifa = admTipoTarifaMapper.existeCod(formDTO.getCodigTarifa());
            if (existeCodTarifa == 0) {
                return new MessageDTO(Constantes.ERROR, "El código de tarifa no existe. Verifica el valor ingresado.");
            }

            if (formDTO.getCodigTarifa() == null || formDTO.getCodigTarifa().length() > 10) {
                return new MessageDTO(Constantes.ERROR, "El código de tarifa no es válido. Debe tener hasta 10 caracteres.");
            }

            if (formDTO.getSecuencia() == null || formDTO.getSecuencia() > 999) {
                return new MessageDTO(Constantes.ERROR, "El valor de la secuencia no puede tener más de 3 dígitos enteros.");
            }

            if (formDTO.getNombretarifa() == null || formDTO.getNombretarifa().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "El nombre de la tarifa no puede superar los 100 caracteres.");
            }

            if (formDTO.getTipoMedicion() == null || formDTO.getTipoMedicion().length() != 2) {
                return new MessageDTO(Constantes.ERROR, "El tipo de medición debe tener exactamente 2 caracteres.");
            }

            try {
                int estado = Integer.parseInt(formDTO.getEstado());
                if (estado != 1 && estado != 0) {
                    return new MessageDTO(Constantes.ERROR, "El estado debe ser 1 o 0.");
                }
            } catch (NumberFormatException e) {
                return new MessageDTO(Constantes.ERROR, "El estado debe ser un número válido.");
            }

            AdmTarifa admTarifa = new AdmTarifa();
            admTarifa.setIdTarifa(Integer.parseInt(formDTO.getIdTarifa()));
            admTarifa.setCodTarifa(formDTO.getCodigTarifa());
            admTarifa.setNomTarifa(formDTO.getNombretarifa());
            admTarifa.setSecuencia(formDTO.getSecuencia());
            admTarifa.setTipoMedicion(formDTO.getTipoMedicion());
            admTarifa.setEstado(formDTO.getEstado());
            admTarifa.setAdFecha(new Date());

            admTarifaMapper.updateByPrimaryKey(admTarifa);
            return new MessageDTO(Constantes.SUCCES, "Tarifa actualizada correctamente.");
        } catch (Exception e) {
            return new MessageDTO(Constantes.ERROR, "Ocurrió un error al actualizar la tarifa: " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public TarifaDTO detalleTarifa(Integer idTarifa) {
        try {
            return admTarifaMapper.detalleTarifa(idTarifa);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public MessageDTO actualizarTarifaSelective(TarifaDTO tarifaDTO) {
        return null;
    }

    @Override
    public List<TarifaDTO> listarTarifas() {
        return admTarifaMapper.listarTarifas();
    }

    @Override
    public PageInfo<TarifaDTO> listarTarifasFiltro(TarifaSearchDTO searchDTO) {
        String orderBy = mapSortField(searchDTO.getSort()) + " " + (searchDTO.getOrder() != null ? searchDTO.getOrder() : "asc");
        PageHelper.startPage(searchDTO.getPage(), searchDTO.getSize(), orderBy);
        return new PageInfo<>(admTarifaMapper.listarTarifasFiltro(searchDTO));
    }


    @Override
    public List<TarifaResultDTO> listarTiposTarifaConcat() {
        try {
            return admTarifaMapper.listarTiposTarifaConcat("1");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String mapSortField(String sort) {
        switch (sort) {
            case "idTarifa":
                return "t.ID_TARIFA";
            case "codTarifa":
                return "t.COD_TARIFA";
            case "nomTarifa":
                return "t.NOM_TARIFA";
            case "secuencia":
                return "t.SECUENCIA";
            case "tipoMedicion":
                return "t.TIPO_MEDICION";
            case "estado":
                return "t.ESTADO";
            case "descripcion":
                return "tt.DESCRIPCION";
            default:
                return "t.ID_TARIFA";
        }
    }

}
