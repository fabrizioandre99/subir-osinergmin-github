package pe.gob.osinergmin.prie.admin.backend.mapper;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmTipoCentral;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCentral.AdmTipoCentralDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCentral.AdmTipoCentralSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTipoCentralService;

import java.util.List;

public interface AdmTipoCentralMapper {
    int deleteByPrimaryKey(String codTipoCentral);
    int insert(AdmTipoCentral record);
    int insertSelective(AdmTipoCentral record);
    AdmTipoCentral selectByPrimaryKey(String codTipoCentral);
    int updateByPrimaryKeySelective(AdmTipoCentral record);
    int updateByPrimaryKey(AdmTipoCentral record);
    List<AdmTipoCentralDTO> selectAll();
    List<AdmTipoCentralDTO> filtrar(AdmTipoCentralSearchDTO admTipoCentralSearchDTO);
}