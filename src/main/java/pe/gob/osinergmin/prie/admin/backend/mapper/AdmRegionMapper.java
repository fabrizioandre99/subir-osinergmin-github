package pe.gob.osinergmin.prie.admin.backend.mapper;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmRegion;
import pe.gob.osinergmin.prie.admin.backend.dto.region.AdmRegionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.region.AdmRegionSearchDTO;

import java.util.List;

public interface AdmRegionMapper {
    int deleteByPrimaryKey(String codRegion);

    int insert(AdmRegion record);

    int insertSelective(AdmRegion record);

    AdmRegion selectByPrimaryKey(String codRegion);

    int updateByPrimaryKeySelective(AdmRegion record);

    int updateByPrimaryKey(AdmRegion record);

    List<AdmRegionDTO> selectAll();

    List<AdmRegionDTO> filtrar(AdmRegionSearchDTO admRegionSearchDTO);
}