package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTipoTarifa;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoTarifa.TipoTarifaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoTarifa.TipoTarifaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoTarifa.TipoTarifaSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmTarifaMapper;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmTipoTarifaMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTipoTarifaService;

import java.util.Date;
import java.util.List;

@Service
public class AdmTipoTarifaServiceImpl implements AdmTipoTarifaService {

    @Autowired
    private AdmTipoTarifaMapper admTipoTarifaMapper;

    @Autowired
    private AdmTarifaMapper admTarifaMapper;

    @Transactional
    @Override
    public MessageDTO eliminarPorCodTarifa(String codtarifa) {
        try {

            AdmTipoTarifa tarifaExistente = admTipoTarifaMapper.selectByPrimaryKey(codtarifa);
            if (tarifaExistente == null) {
                return new MessageDTO(Constantes.ERROR, "El código de la tarifa no existe. No se puede eliminar.");
            }

            admTarifaMapper.eliminarTarifasPorTipo(codtarifa);
            admTipoTarifaMapper.deleteByPrimaryKey(codtarifa);

            return new MessageDTO(Constantes.SUCCES, "Tarifa y tarifas asociadas eliminadas correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar la tarifa y las tarifas asociadas: " + e.getMessage());
        }
    }


    @Transactional
    @Override
    public MessageDTO insertarTarifa(TipoTarifaFormDTO formDTO) {
        try {
            if (formDTO.getCodtarifa() == null || formDTO.getCodtarifa().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código de tarifa es obligatorio.");
            }
            if (formDTO.getCodtarifa().length() > 10) {
                return new MessageDTO(Constantes.ERROR, "El código de tarifa no puede superar los 10 caracteres.");
            }

            AdmTipoTarifa tarifaExistente = admTipoTarifaMapper.selectByPrimaryKey(formDTO.getCodtarifa());
            if (tarifaExistente != null) {
                return new MessageDTO(Constantes.ERROR, "El código de la tarifa ya existe.");
            }

            if (formDTO.getDescripcion() != null && formDTO.getDescripcion().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "La descripción no puede superar los 50 caracteres.");
            }

            if (formDTO.getEstado() == null || formDTO.getEstado().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El estado es obligatorio.");
            }
            if (formDTO.getEstado().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El estado debe tener exactamente 1 carácter.");
            }
            if (!formDTO.getEstado().equals("1") && !formDTO.getEstado().equals("0")) {
                return new MessageDTO(Constantes.ERROR, "El estado debe ser '1' (activo) o '0' (inactivo).");
            }

            AdmTipoTarifa nuevaTarifa = new AdmTipoTarifa();
            nuevaTarifa.setCodtarifa(formDTO.getCodtarifa());
            nuevaTarifa.setDescripcion(formDTO.getDescripcion());
            nuevaTarifa.setEstado(formDTO.getEstado());
            nuevaTarifa.setAdCodUsuario("admin");
            nuevaTarifa.setAdFecha(new Date());
            admTipoTarifaMapper.insert(nuevaTarifa);

            return new MessageDTO(Constantes.SUCCES, "Tarifa insertada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    @Override
    public TipoTarifaDTO buscarPorCodTarifa(String codtarifa) {
        try {
            return admTipoTarifaMapper.buscarPorCodTarifa(codtarifa);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    @Override
    public MessageDTO actualizarTipoTarifa(TipoTarifaFormDTO formDTO) {
        try {
            if (formDTO.getCodtarifa() == null || formDTO.getCodtarifa().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código de tarifa es obligatorio.");
            }
            if (formDTO.getCodtarifa().length() > 10) {
                return new MessageDTO(Constantes.ERROR, "El código de tarifa no puede superar los 10 caracteres.");
            }

            int valorCodExiste = admTipoTarifaMapper.existeCod(formDTO.getCodtarifa());
            if (valorCodExiste < 1) {
                return new MessageDTO(Constantes.ERROR, "El código de la tarifa no existe. No se puede actualizar.");
            }

            if (formDTO.getDescripcion() != null && formDTO.getDescripcion().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "La descripción no puede superar los 50 caracteres.");
            }

            if (formDTO.getEstado() == null || formDTO.getEstado().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El estado es obligatorio.");
            }
            if (formDTO.getEstado().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El estado debe tener exactamente 1 carácter.");
            }
            if (!formDTO.getEstado().equals("1") && !formDTO.getEstado().equals("0")) {
                return new MessageDTO(Constantes.ERROR, "El estado debe ser '1' (activo) o '0' (inactivo).");
            }

            AdmTipoTarifa admTipoTarifa = new AdmTipoTarifa();
            admTipoTarifa.setCodtarifa(formDTO.getCodtarifa());
            admTipoTarifa.setDescripcion(formDTO.getDescripcion());
            admTipoTarifa.setEstado(formDTO.getEstado());
            admTipoTarifa.setAdFecha(new Date());
            admTipoTarifaMapper.updateByPrimaryKey(admTipoTarifa);

            return new MessageDTO(Constantes.SUCCES, "Tarifa actualizada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<TipoTarifaDTO> listarTipoTarifas() {
        return admTipoTarifaMapper.listarIdTarifa();
    }

    @Transactional
    @Override
    public PageInfo<TipoTarifaDTO> listarConFiltro(TipoTarifaSearchDTO tipoTarifaSearchDTO) {
        PageHelper.startPage(tipoTarifaSearchDTO.getPage(), tipoTarifaSearchDTO.getSize());
        return new PageInfo<>(admTipoTarifaMapper.listarConFiltro(tipoTarifaSearchDTO));
    }
}
