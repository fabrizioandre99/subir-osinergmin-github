package pe.gob.osinergmin.prie.admin.backend.mapper;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmCoym;
import pe.gob.osinergmin.prie.admin.backend.dto.coym.AdmCoymDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.coym.AdmCoymSearchDTO;

import java.util.List;

public interface AdmCoymMapper {
    int deleteByPrimaryKey(String codCoym);

    int insert(AdmCoym record);

    int insertSelective(AdmCoym record);

    AdmCoym selectByPrimaryKey(String codCoym);

    int updateByPrimaryKeySelective(AdmCoym record);

    int updateByPrimaryKey(AdmCoym record);

    List<AdmCoymDTO> selectAll();

    List<AdmCoymDTO> filtrarCoym(AdmCoymSearchDTO admCoymSearchDTO);
}