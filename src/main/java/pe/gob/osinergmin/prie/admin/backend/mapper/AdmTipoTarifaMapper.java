package pe.gob.osinergmin.prie.admin.backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTipoTarifa;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoTarifa.TipoTarifaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoTarifa.TipoTarifaSearchDTO;

import java.util.List;

@Mapper
public interface AdmTipoTarifaMapper {
    int deleteByPrimaryKey(@Param("codtarifa") String codtarifa);

    int insert(AdmTipoTarifa record);

    AdmTipoTarifa selectByPrimaryKey(@Param("codtarifa") String codtarifa);

    int updateByPrimaryKey(AdmTipoTarifa admTipoTarifa);

    List<TipoTarifaDTO> listarConFiltro(TipoTarifaSearchDTO tipoTarifaSearchDTO);

    List<TipoTarifaDTO> listarIdTarifa();

    int existeCod(@Param("codtarifa") String codtarifa);

    TipoTarifaDTO buscarPorCodTarifa(String codtarifa);

}
