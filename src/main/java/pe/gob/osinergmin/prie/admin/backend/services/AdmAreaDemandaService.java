package pe.gob.osinergmin.prie.admin.backend.services;

import java.math.BigDecimal;
import java.util.List;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmAreaDemanda;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.areaDemanda.AdmAreaDemandaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.areaDemanda.AdmAreaDemandaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.areaDemanda.AdmAreaDemandaSearchDTO;

public interface AdmAreaDemandaService {

    MessageDTO deleteByPrimaryKey(Integer areaDemanda);

    MessageDTO insert(AdmAreaDemandaFormDTO record);

    int insertSelective(AdmAreaDemanda record);

    AdmAreaDemanda selectByPrimaryKey(Integer areaDemanda);

    int updateByPrimaryKeySelective(AdmAreaDemanda record);

    MessageDTO updateByPrimaryKey(AdmAreaDemandaFormDTO record);

    List<AdmAreaDemandaDTO> listarAreas();

    PageInfo<AdmAreaDemandaDTO> filtrar(AdmAreaDemandaSearchDTO admAreaDemandaSearchDTO);
}
