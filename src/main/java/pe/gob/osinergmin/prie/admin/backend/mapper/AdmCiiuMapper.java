package pe.gob.osinergmin.prie.admin.backend.mapper;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmCiiu;
import pe.gob.osinergmin.prie.admin.backend.dto.ciiu.AdmCiiuDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ciiu.AdmCiiuSearchDTO;

import java.util.List;

public interface AdmCiiuMapper {
    int deleteByPrimaryKey(String codCiiu);

    int insert(AdmCiiu record);

    int insertSelective(AdmCiiu record);

    AdmCiiu selectByPrimaryKey(String codCiiu);

    int updateByPrimaryKeySelective(AdmCiiu record);

    int updateByPrimaryKey(AdmCiiu record);

    List<AdmCiiuDTO> selectAll();

    List<AdmCiiuDTO> listarFiltro(AdmCiiuSearchDTO admCiiuSearchDTO);
}