package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmRegion;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.region.AdmRegionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.region.AdmRegionFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.region.AdmRegionSearchDTO;

import java.util.List;

public interface AdmRegionService{

    MessageDTO deleteByPrimaryKey(String codRegion);

    MessageDTO insert(AdmRegionFormDTO record);

    int insertSelective(AdmRegion record);

    AdmRegion selectByPrimaryKey(String codRegion);

    int updateByPrimaryKeySelective(AdmRegion record);

    MessageDTO updateByPrimaryKey(AdmRegionFormDTO record);

    List<AdmRegionDTO> selectAll();

    PageInfo<AdmRegionDTO> filtrar(AdmRegionSearchDTO admRegionSearchDTO);

}
