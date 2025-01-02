package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmEmbalse;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.embalse.AdmEmbalseDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.embalse.AdmEmbalseFomrDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.embalse.AdmEmbalseSearchDTO;

import java.util.List;

public interface AdmEmbalseService{

    MessageDTO deleteByPrimaryKey(String codEmbalse);
    MessageDTO insert(AdmEmbalseFomrDTO admEmbalseFomrDTO);
    int insertSelective(AdmEmbalse record);
    AdmEmbalse selectByPrimaryKey(String codEmbalse);
    int updateByPrimaryKeySelective(AdmEmbalse record);
    MessageDTO updateByPrimaryKey(AdmEmbalseFomrDTO admEmbalseFomrDTO);
    List<AdmEmbalseDTO> selectAll();
    PageInfo<AdmEmbalseDTO> filtro(AdmEmbalseSearchDTO admEmbalseSearchDTO);

}
