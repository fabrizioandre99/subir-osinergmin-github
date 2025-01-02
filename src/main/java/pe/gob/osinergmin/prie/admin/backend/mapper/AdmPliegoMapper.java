package pe.gob.osinergmin.prie.admin.backend.mapper;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmPliego;
import pe.gob.osinergmin.prie.admin.backend.dto.pliego.AdmPliegoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.pliego.AdmPliegoSearchDTO;

import java.util.List;

public interface AdmPliegoMapper {
    int deleteByPrimaryKey(String codPliego);
    int insert(AdmPliego record);
    int insertSelective(AdmPliego record);
    AdmPliego selectByPrimaryKey(String codPliego);
    int updateByPrimaryKeySelective(AdmPliego record);
    int updateByPrimaryKey(AdmPliego record);
    List<AdmPliegoDTO> selectAllAdmPliegos();
    List<AdmPliegoDTO> filtro(AdmPliegoSearchDTO searchDTO);
}