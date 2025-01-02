package pe.gob.osinergmin.prie.admin.backend.mapper;

import org.apache.ibatis.annotations.Param;
import pe.gob.osinergmin.prie.admin.backend.domain.CfgParametro;
import pe.gob.osinergmin.prie.admin.backend.dto.parametro.CfgParametroSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tablaMaestra.TablaMaestraDTO;

import java.util.List;

public interface CfgParametroMapper {
    int deleteByPrimaryKey(@Param("codGrupo") String codGrupo, @Param("codParametro") String codParametro);

    int insert(CfgParametro record);

    int insertSelective(CfgParametro record);

    CfgParametro selectByPrimaryKey(@Param("codGrupo") String codGrupo, @Param("codParametro") String codParametro);

    int updateByPrimaryKeySelective(CfgParametro record);

    int updateByPrimaryKey(CfgParametro record);

    List<TablaMaestraDTO> getParametrosByGrupo(@Param("codGrupo") String codGrupo);

    CfgParametro getParametro(@Param("codGrupo") String codGrupo, @Param("codParametro") String codParametro);

    void insertParametro(CfgParametro parametro);

    void updateParametro(CfgParametro parametro);

    void deleteParametro(@Param("codGrupo") String codGrupo, @Param("codParametro") String codParametro);

    List<TablaMaestraDTO> filtrar(@Param("cfgParametroSearchDTO") CfgParametroSearchDTO cfgParametroSearchDTO, @Param("codGrupo") String codGrupo);
}