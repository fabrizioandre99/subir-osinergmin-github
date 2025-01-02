package pe.gob.osinergmin.prie.admin.backend.mapper;

import java.math.BigDecimal;
import java.util.List;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmAreaDemanda;
import pe.gob.osinergmin.prie.admin.backend.dto.areaDemanda.AdmAreaDemandaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.areaDemanda.AdmAreaDemandaSearchDTO;

public interface AdmAreaDemandaMapper {
    int deleteByPrimaryKey(Integer areaDemanda);

    int insert(AdmAreaDemanda record);

    int insertSelective(AdmAreaDemanda record);

    AdmAreaDemanda selectByPrimaryKey(Integer areaDemanda);

    int updateByPrimaryKeySelective(AdmAreaDemanda record);

    int updateByPrimaryKey(AdmAreaDemanda record);

    List<AdmAreaDemandaDTO> listarAreas();

    List<AdmAreaDemandaDTO> filtrar(AdmAreaDemandaSearchDTO admAreaDemandaSearchDTO);
}