package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmSunat;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sunat.AdmSunatDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sunat.AdmSunatFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sunat.AdmSunatSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmSunatMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmSunatService;

import java.util.Date;
import java.util.List;

@Service
public class AdmSunatServiceImpl implements AdmSunatService {

    @Autowired
    private AdmSunatMapper admSunatMapper;

    @Transactional
    @Override
    public MessageDTO deleteByPrimaryKey(String codSunat) {
        try {
            AdmSunat sunatExistente = admSunatMapper.selectByPrimaryKey(codSunat);
            if (sunatExistente == null) {
                return new MessageDTO(Constantes.ERROR, "El código SUNAT no existe. No se puede eliminar.");
            }
            admSunatMapper.deleteByPrimaryKey(codSunat);
            return new MessageDTO(Constantes.SUCCES, "Registro eliminado correctamente.");
        } catch (Exception e) {
            return new MessageDTO(Constantes.ERROR, "Ocurrió un error al eliminar el registro: " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public MessageDTO insert(AdmSunatFormDTO formDTO) {
        try {
            if (formDTO.getCodSunat() == null || formDTO.getCodSunat().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código SUNAT es obligatorio.");
            }
            if (formDTO.getCodSunat().length() > 6) {
                return new MessageDTO(Constantes.ERROR, "El código SUNAT no puede superar los 6 caracteres.");
            }

            AdmSunat sunatExistente = admSunatMapper.selectByPrimaryKey(formDTO.getCodSunat());
            if (sunatExistente != null) {
                return new MessageDTO(Constantes.ERROR, "El código SUNAT ya existe. Por favor, elige otro código.");
            }

            if (formDTO.getNomSunat() == null || formDTO.getNomSunat().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El nombre SUNAT es obligatorio.");
            }
            if (formDTO.getNomSunat().length() > 350) {
                return new MessageDTO(Constantes.ERROR, "El nombre SUNAT no puede superar los 350 caracteres.");
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

            AdmSunat admSunat = new AdmSunat();
            admSunat.setCodSunat(formDTO.getCodSunat());
            admSunat.setNomSunat(formDTO.getNomSunat());
            admSunat.setEstado(formDTO.getEstado());
            admSunat.setAdCodUsuario("admin");
            admSunat.setAdFecha(new Date());

            admSunatMapper.insert(admSunat);

            return new MessageDTO(Constantes.SUCCES, "Registro insertado correctamente.");
        } catch (Exception e) {
            return new MessageDTO(Constantes.ERROR, "Ocurrió un error al insertar el registro: " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(AdmSunatFormDTO formDTO) {
        try {
            if (formDTO.getCodSunat() == null || formDTO.getCodSunat().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código SUNAT es obligatorio.");
            }
            if (formDTO.getCodSunat().length() > 6) {
                return new MessageDTO(Constantes.ERROR, "El código SUNAT no puede superar los 6 caracteres.");
            }

            AdmSunat sunatExistente = admSunatMapper.selectByPrimaryKey(formDTO.getCodSunat());
            if (sunatExistente == null) {
                return new MessageDTO(Constantes.ERROR, "El código SUNAT no existe. No se puede actualizar.");
            }

            if (formDTO.getNomSunat() == null || formDTO.getNomSunat().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El nombre SUNAT es obligatorio.");
            }
            if (formDTO.getNomSunat().length() > 350) {
                return new MessageDTO(Constantes.ERROR, "El nombre SUNAT no puede superar los 350 caracteres.");
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

            AdmSunat admSunat = new AdmSunat();
            admSunat.setCodSunat(formDTO.getCodSunat());
            admSunat.setNomSunat(formDTO.getNomSunat());
            admSunat.setEstado(formDTO.getEstado());
            admSunat.setAdCodUsuario("admin");
            admSunat.setAdFecha(new Date());

            admSunatMapper.updateByPrimaryKey(admSunat);

            return new MessageDTO(Constantes.SUCCES, "Registro actualizado correctamente.");
        } catch (Exception e) {
            return new MessageDTO(Constantes.ERROR, "Ocurrió un error al actualizar el registro: " + e.getMessage());
        }
    }

    @Override
    public AdmSunat selectByPrimaryKey(String codSunat) {
        return admSunatMapper.selectByPrimaryKey(codSunat);
    }

    @Override
    public List<AdmSunatDTO> listar() {
        return List.of();
    }

    @Override
    public PageInfo<AdmSunatDTO> listarFiltro(AdmSunatSearchDTO searchDTO) {
        PageHelper.startPage(searchDTO.getPage(), searchDTO.getSize());
        return new PageInfo<>(admSunatMapper.listarFiltro(searchDTO));
    }

    @Override
    public int insertSelective(AdmSunat record) {
        throw new UnsupportedOperationException("Método no implementado.");
    }

    @Override
    public int updateByPrimaryKeySelective(AdmSunat record) {
        throw new UnsupportedOperationException("Método no implementado.");
    }
}
