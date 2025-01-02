package pe.gob.osinergmin.prie.admin.backend.mapper;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmModoOperacion;
import pe.gob.osinergmin.prie.admin.backend.dto.modoOperacion.AdmModoOperacionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.modoOperacion.AdmModoOperacionSearchDTO;

import java.util.List;

public interface AdmModoOperacionMapper {
    int deleteByPrimaryKey(String codModoOperacion);
    int insert(AdmModoOperacion record);
    int insertSelective(AdmModoOperacion record);
    AdmModoOperacion selectByPrimaryKey(String codModoOperacion);
    int updateByPrimaryKeySelective(AdmModoOperacion record);
    int updateByPrimaryKey(AdmModoOperacion record);
    List<AdmModoOperacionDTO> selectAll();
    List<AdmModoOperacionDTO> filtro(AdmModoOperacionSearchDTO admModoOperacionSearchDTO);
}