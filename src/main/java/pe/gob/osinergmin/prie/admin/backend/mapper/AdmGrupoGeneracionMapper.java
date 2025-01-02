package pe.gob.osinergmin.prie.admin.backend.mapper;

import org.apache.ibatis.annotations.Param;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmGrupoGeneracion;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoGeneracion.AdmGrupoGeneracionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoGeneracion.AdmGrupoGeneracionSearchDTO;

import java.util.List;

public interface AdmGrupoGeneracionMapper {
    int deleteByPrimaryKey(String codGrupoGeneracion);
    int insert(AdmGrupoGeneracion record);
    int insertSelective(AdmGrupoGeneracion record);
    AdmGrupoGeneracion selectByPrimaryKey(String codGrupoGeneracion);
    int updateByPrimaryKeySelective(AdmGrupoGeneracion record);
    int updateByPrimaryKey(AdmGrupoGeneracion record);
    List<AdmGrupoGeneracionDTO> selectAll();
    List<AdmGrupoGeneracionDTO> filtro(AdmGrupoGeneracionSearchDTO admGrupoGeneracionSearchDTO);
    int eliminarCodCentral(String codCentralGeneracion);
    List<AdmGrupoGeneracionDTO> selectByCodCentralGeneracionList(@Param("codCentralGeneracionList") List<String> codCentralGeneracionList);
}