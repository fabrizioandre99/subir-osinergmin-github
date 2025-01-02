package pe.gob.osinergmin.prie.admin.backend.services;

import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCambio.TipoCambioDTO;
import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCambio.TipoCambioFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCambio.TipoCambioResultDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCambio.TipoCambioSearchDTO;

import java.util.List;

public interface AdmTipoCambioService {

    MessageDTO insertarTipoCambio(TipoCambioFormDTO tipoCambioFormDTO) throws Exception;

    MessageDTO actualizarTipoCambio(TipoCambioFormDTO tipoCambioFormDTO) throws Exception;

    MessageDTO eliminarPorMonedaFuenteFecha(String fecha);

    List<TipoCambioResultDTO> listarTiposCambio();

    PageInfo<TipoCambioResultDTO> listarConFiltro(TipoCambioSearchDTO tipoCambioSearchDTO);


}
