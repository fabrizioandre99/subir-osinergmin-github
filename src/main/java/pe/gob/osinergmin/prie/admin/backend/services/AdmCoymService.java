package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmCoym;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.coym.AdmCoymDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.coym.AdmCoymFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.coym.AdmCoymSearchDTO;

import java.util.List;

public interface AdmCoymService{

    MessageDTO deleteByPrimaryKey(String codCoym);

    MessageDTO insert(AdmCoymFormDTO record);

    int insertSelective(AdmCoym record);

    AdmCoym selectByPrimaryKey(String codCoym);

    int updateByPrimaryKeySelective(AdmCoym record);

    MessageDTO updateByPrimaryKey(AdmCoymFormDTO record);

    List<AdmCoymDTO> selectAll();

    PageInfo<AdmCoymDTO> filtrarCoym(AdmCoymSearchDTO admCoymSearchDTO);

}
