package pe.gob.osinergmin.prie.admin.backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTarifa;
import pe.gob.osinergmin.prie.admin.backend.dto.tarifa.TarifaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tarifa.TarifaResultDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tarifa.TarifaSearchDTO;

import java.util.List;

@Mapper
public interface AdmTarifaMapper {

    int deleteByPrimaryKey(Integer idTarifa);

    int insert(AdmTarifa record);

    int insertSelective(AdmTarifa record);

    AdmTarifa selectByPrimaryKey(Integer idTarifa);

    int updateByPrimaryKeySelective(AdmTarifa record);

    int updateByPrimaryKey(AdmTarifa record);

    List<TarifaDTO> listarTarifas();

    List<TarifaDTO> listarTarifasFiltro(TarifaSearchDTO searchDTO);

    TarifaDTO detalleTarifa(Integer idTarifa);

    List<TarifaResultDTO> listarTiposTarifaConcat(@Param("estado") String estado);

    int eliminarTarifasPorTipo(@Param("codTarifa") String codTarifa);
}
