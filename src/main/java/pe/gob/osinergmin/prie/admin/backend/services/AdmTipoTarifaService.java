package pe.gob.osinergmin.prie.admin.backend.services;

import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoTarifa.TipoTarifaDTO;
import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoTarifa.TipoTarifaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoTarifa.TipoTarifaSearchDTO;

import java.util.List;

public interface AdmTipoTarifaService {
    TipoTarifaDTO buscarPorCodTarifa(String codtarifa);

    MessageDTO insertarTarifa(TipoTarifaFormDTO formDTO);

    MessageDTO actualizarTipoTarifa(TipoTarifaFormDTO formDTO);

    MessageDTO eliminarPorCodTarifa(String codtarifa);

    List<TipoTarifaDTO> listarTipoTarifas();

    PageInfo<TipoTarifaDTO> listarConFiltro(TipoTarifaSearchDTO tipoTarifaSearchDTO);
}
