package pe.gob.osinergmin.prie.admin.backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pe.gob.osinergmin.prie.admin.backend.domain.IndEconomico;
import pe.gob.osinergmin.prie.admin.backend.dto.indEconomico.IndEconomicoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.indEconomico.IndEconomicoSearchDTO;

import java.util.List;

@Mapper
public interface IndEconomicoMapper {
    int deleteByCodIndicador(@Param("codIndicador") String codIndicador);

    int insert(IndEconomico record);

    IndEconomico selectByCodIndicador(@Param("codIndicador") String codIndicador);

    int updateByPrimaryKey(IndEconomico indEconomico);

    List<IndEconomicoDTO> listarConFiltro(IndEconomicoSearchDTO indEconomicoSearchDTO);

    List<IndEconomicoDTO> listarIdEconomico();

    int existeCod(@Param("codIndicador") String codIndicador);
}
