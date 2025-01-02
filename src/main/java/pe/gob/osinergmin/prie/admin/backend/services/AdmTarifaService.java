package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTarifa;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tarifa.TarifaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tarifa.TarifaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tarifa.TarifaResultDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tarifa.TarifaSearchDTO;

import java.util.List;

public interface AdmTarifaService{

    MessageDTO eliminarPorId(Integer idTarifa);

    MessageDTO insertarTarifa(TarifaFormDTO formDTO) throws Exception;

    TarifaDTO detalleTarifa(Integer idTarifa);

    MessageDTO actualizarTarifaSelective(TarifaDTO tarifaDTO);

    MessageDTO actualizarTarifa(TarifaFormDTO formDTO) throws Exception;

    List<TarifaDTO> listarTarifas();

    PageInfo<TarifaDTO> listarTarifasFiltro(TarifaSearchDTO searchDTO);

    List<TarifaResultDTO> listarTiposTarifaConcat();
}
