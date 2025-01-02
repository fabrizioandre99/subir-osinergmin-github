package pe.gob.osinergmin.prie.admin.backend.mapper;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmLago;
import pe.gob.osinergmin.prie.admin.backend.dto.lago.AdmLagoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.lago.AdmLagoSearchDTO;

import java.util.List;

public interface AdmLagoMapper {
    int deleteByPrimaryKey(String codLago);
    int insert(AdmLago record);
    int insertSelective(AdmLago record);
    AdmLago selectByPrimaryKey(String codLago);
    int updateByPrimaryKeySelective(AdmLago record);
    int updateByPrimaryKey(AdmLago record);
    List<AdmLagoDTO> selectAll();
    List<AdmLagoDTO> filtro(AdmLagoSearchDTO admLagoSearchDTO);
}