package pe.gob.osinergmin.prie.admin.backend.mapper;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmCentralGeneracion;
import pe.gob.osinergmin.prie.admin.backend.dto.centralGeneracion.AdmCentralGeneracionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.centralGeneracion.AdmCentralGeneracionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoGeneracion.AdmGrupoGeneracionDTO;

import java.util.List;

public interface AdmCentralGeneracionMapper {
    int deleteByPrimaryKey(String codCentralGeneracion);
    int insert(AdmCentralGeneracion record);
    int insertSelective(AdmCentralGeneracion record);
    AdmCentralGeneracion selectByPrimaryKey(String codCentralGeneracion);
    int updateByPrimaryKeySelective(AdmCentralGeneracion record);
    int updateByPrimaryKey(AdmCentralGeneracion record);
    List<AdmCentralGeneracionDTO> selectAll();
    List<AdmCentralGeneracionDTO> filtro(AdmCentralGeneracionSearchDTO admCentralGeneracionSearchDTO);
    List<AdmCentralGeneracionDTO> selectByCodEmpresa(String codEmpresa);
}