package pe.gob.osinergmin.prie.admin.backend.services;

import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.indEconomico.IndEconomicoDTO;
import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.dto.indEconomico.IndEconomicoFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.indEconomico.IndEconomicoSearchDTO;

import java.util.List;

public interface IndEconomicoService {

    MessageDTO insertarIndicador(IndEconomicoFormDTO indEconomicoDTO);

    MessageDTO actualizarIndicador(IndEconomicoFormDTO indEconomicoDTO);

    MessageDTO eliminarPorCodIndicador(String codIndicador);

    List<IndEconomicoDTO> listarIndicadores();

    PageInfo<IndEconomicoDTO> listarConFiltro(IndEconomicoSearchDTO indEconomicoSearchDTO);
}
