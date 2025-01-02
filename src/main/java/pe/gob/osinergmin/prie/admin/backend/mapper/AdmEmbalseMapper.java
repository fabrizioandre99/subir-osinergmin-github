package pe.gob.osinergmin.prie.admin.backend.mapper;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmEmbalse;
import pe.gob.osinergmin.prie.admin.backend.dto.embalse.AdmEmbalseDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.embalse.AdmEmbalseSearchDTO;

import java.util.List;


public interface AdmEmbalseMapper {
    int deleteByPrimaryKey(String codEmbalse);
    int insert(AdmEmbalse record);
    int insertSelective(AdmEmbalse record);
    AdmEmbalse selectByPrimaryKey(String codEmbalse);
    int updateByPrimaryKeySelective(AdmEmbalse record);
    int updateByPrimaryKey(AdmEmbalse record);
    List<AdmEmbalseDTO> selectAll();
    List<AdmEmbalseDTO> filtro(AdmEmbalseSearchDTO admEmbalseSearchDTO);
}