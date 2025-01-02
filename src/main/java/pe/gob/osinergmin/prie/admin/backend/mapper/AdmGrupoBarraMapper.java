package pe.gob.osinergmin.prie.admin.backend.mapper;

import org.apache.ibatis.annotations.Param;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmGrupoBarra;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoBarra.AdmGrupoBarraSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoBarra.AdmGrupoBarraformDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoBarra.GrupoBarraFlatDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoBarra.GrupoBarraResponseDTO;

import java.util.List;
import java.util.Map;

public interface AdmGrupoBarraMapper {
    int deleteByPrimaryKey(@Param("codBarraGrupo") String codBarraGrupo, @Param("codBarra") String codBarra);

    int insert(AdmGrupoBarra record);

    int insertSelective(AdmGrupoBarra record);

    AdmGrupoBarra selectByPrimaryKey(@Param("codBarraGrupo") String codBarraGrupo);

    int updateByPrimaryKeySelective(AdmGrupoBarra record);

    int updateByPrimaryKey(AdmGrupoBarra record);

    int insertGrupoBarra(Map<String, Object> params);

    List<String> selectCodBarrasByGrupo(@Param("codBarraGrupo") String codBarraGrupo);

    int deleteCodBarra(@Param("codBarraGrupo") String codBarraGrupo);

    int updateCodBarra(Map<String, Object> params);

    List<AdmGrupoBarra> selectAllGrupos();

    List<GrupoBarraResponseDTO> listarGruposDeBarras();

    List<GrupoBarraFlatDTO> filtrar(AdmGrupoBarraSearchDTO admGrupoBarraSearchDTO);

    int countByCodBarraGrupo(@Param("codBarraGrupo") String codBarraGrupo);

    int updateEstadoGrupo(@Param("codBarraGrupo") String codBarraGrupo, @Param("estado") String estado);

}