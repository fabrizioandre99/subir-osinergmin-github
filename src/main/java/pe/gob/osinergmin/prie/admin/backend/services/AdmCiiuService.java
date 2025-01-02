package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmCiiu;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ciiu.AdmCiiuDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ciiu.AdmCiiuFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ciiu.AdmCiiuSearchDTO;

import java.util.List;

public interface AdmCiiuService{

    MessageDTO deleteByPrimaryKey(String codCiiu);

    MessageDTO insert(AdmCiiuFormDTO record);

    int insertSelective(AdmCiiu record);

    AdmCiiu selectByPrimaryKey(String codCiiu);

    int updateByPrimaryKeySelective(AdmCiiu record);

    MessageDTO updateByPrimaryKey(AdmCiiuFormDTO record);

    List<AdmCiiuDTO> selectAll();

    PageInfo<AdmCiiuDTO> listarFiltro(AdmCiiuSearchDTO admCiiuSearchDTO);
}
