package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.IndEconomico;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.indEconomico.IndEconomicoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.indEconomico.IndEconomicoFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.indEconomico.IndEconomicoSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.IndEconomicoMapper;
import pe.gob.osinergmin.prie.admin.backend.services.IndEconomicoService;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Date;
import java.util.List;

@Service
public class IndEconomicoServiceImpl implements IndEconomicoService {

    @Autowired
    private IndEconomicoMapper indEconomicoMapper;

    @Transactional
    @Override
    public MessageDTO eliminarPorCodIndicador(String codIndicador) {
        if (indEconomicoMapper.deleteByCodIndicador(codIndicador) > 0) {
            return new MessageDTO(Constantes.SUCCES, "Indicador eliminado exitosamente.");
        }
        return new MessageDTO(Constantes.ERROR, "No se pudo eliminar el indicador.");
    }

    @Transactional
    @Override
    public MessageDTO insertarIndicador(IndEconomicoFormDTO indEconomicoDTO) {
        try {
            if (indEconomicoDTO.getCodIndicador() == null || indEconomicoDTO.getCodIndicador().length() < 1 || indEconomicoDTO.getCodIndicador().length() > 10) {
                return new MessageDTO(Constantes.ERROR, "El código del indicador debe tener entre 1 y 10 caracteres.");
            }

            if (indEconomicoDTO.getAnnioMes() == null || indEconomicoDTO.getAnnioMes().length() != 6) {
                return new MessageDTO(Constantes.ERROR, "El año y mes deben tener exactamente 6 caracteres en el formato 'YYYYMM'.");
            }

            if (indEconomicoDTO.getEstado() == null) {
                return new MessageDTO(Constantes.ERROR, "El estado no puede ser nulo.");
            }

            IndEconomico indicadorExistente = indEconomicoMapper.selectByCodIndicador(indEconomicoDTO.getCodIndicador());
            if (indicadorExistente != null) {
                return new MessageDTO(Constantes.ERROR, "El código del indicador ya existe.");
            }

            IndEconomico nuevoIndicador = new IndEconomico();
            nuevoIndicador.setCodIndicador(indEconomicoDTO.getCodIndicador());
            nuevoIndicador.setValIndicador(indEconomicoDTO.getValIndicador());
            nuevoIndicador.setEstado(indEconomicoDTO.getEstado());
            nuevoIndicador.setAdCodUsuario("admin");
            nuevoIndicador.setAnnioMes(indEconomicoDTO.getAnnioMes());
            nuevoIndicador.setAdFecha(new Date());

            if (indEconomicoMapper.insert(nuevoIndicador) > 0) {
                return new MessageDTO(Constantes.SUCCES, "Indicador creado exitosamente.");
            }
            return new MessageDTO(Constantes.ERROR, "No se pudo crear el indicador.");
        } catch (DataIntegrityViolationException e) {
            return new MessageDTO(Constantes.ERROR, "Error de integridad de datos: " + e.getMessage());
        }
    }


    @Transactional
    @Override
    public MessageDTO actualizarIndicador(IndEconomicoFormDTO indEconomicoDTO) {
        try {

            if (indEconomicoDTO.getCodIndicador() == null || indEconomicoDTO.getCodIndicador().length() < 1 || indEconomicoDTO.getCodIndicador().length() > 10) {
                return new MessageDTO(Constantes.ERROR, "El código del indicador debe tener entre 1 y 10 caracteres.");
            }

            if (indEconomicoDTO.getAnnioMes() == null || indEconomicoDTO.getAnnioMes().length() != 6) {
                return new MessageDTO(Constantes.ERROR, "El año y mes deben tener exactamente 6 caracteres en el formato 'YYYYMM'.");
            }

            if (indEconomicoDTO.getEstado() == null) {
                return new MessageDTO(Constantes.ERROR, "El estado no puede ser nulo.");
            }

            int valorCodExiste = indEconomicoMapper.existeCod(indEconomicoDTO.getCodIndicador());
            if (valorCodExiste < 1) {
                return new MessageDTO(Constantes.ERROR, "El código del indicador no existe. No se puede actualizar.");
            }

            IndEconomico admIndEconomico = new IndEconomico();
            admIndEconomico.setCodIndicador(indEconomicoDTO.getCodIndicador());
            admIndEconomico.setValIndicador(indEconomicoDTO.getValIndicador());
            admIndEconomico.setEstado(indEconomicoDTO.getEstado());
            admIndEconomico.setAdCodUsuario("admin");
            admIndEconomico.setAnnioMes(indEconomicoDTO.getAnnioMes());
            admIndEconomico.setAdFecha(new Date());

            int filasActualizadas = indEconomicoMapper.updateByPrimaryKey(admIndEconomico);

            if (filasActualizadas > 0) {
                return new MessageDTO(Constantes.SUCCES, "Indicador actualizado exitosamente.");
            } else {
                return new MessageDTO(Constantes.ERROR, "No se pudo actualizar el indicador. Verifique los datos proporcionados.");
            }
        } catch (DataIntegrityViolationException e) {
            return new MessageDTO(Constantes.ERROR, "Error de integridad de datos: " + e.getMessage());
        } catch (Exception e) {
            return new MessageDTO(Constantes.ERROR, "Error inesperado al actualizar el indicador: " + e.getMessage());
        }
    }

    @Override
    public List<IndEconomicoDTO> listarIndicadores() {
        return indEconomicoMapper.listarIdEconomico();
    }

    @Transactional
    @Override
    public PageInfo<IndEconomicoDTO> listarConFiltro(IndEconomicoSearchDTO indEconomicoSearchDTO) {
        String sortField = mapSortField(indEconomicoSearchDTO.getSort());
        String orderDirection = validateOrder(indEconomicoSearchDTO.getOrder());
        String orderBy = sortField + " " + orderDirection;
        PageHelper.startPage(indEconomicoSearchDTO.getPage(), indEconomicoSearchDTO.getSize(), orderBy);
        return new PageInfo<>(indEconomicoMapper.listarConFiltro(indEconomicoSearchDTO));
    }

    private String mapSortField(String sort) {
        if (sort == null || sort.isEmpty()) {
            return "ANNIO_MES";
        }
        switch (sort) {
            case "annioMes":
                return "ANNIO_MES";
            case "codIndicador":
                return "COD_INDICADOR";
            case "valIndicador":
                return "VAL_INDICADOR";
            case "estado":
                return "ESTADO";
            default:
                return "ANNIO_MES";
        }
    }

    private String validateOrder(String order) {
        if ("asc".equalsIgnoreCase(order) || "desc".equalsIgnoreCase(order)) {
            return order.toUpperCase();
        }
        return "ASC";
    }
}
