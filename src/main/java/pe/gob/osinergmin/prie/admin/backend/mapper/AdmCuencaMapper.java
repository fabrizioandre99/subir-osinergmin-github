package pe.gob.osinergmin.prie.admin.backend.mapper;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmCuenca;
import pe.gob.osinergmin.prie.admin.backend.dto.cuenca.AdmCuencaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.cuenca.AdmCuencaSearchDTO;

import java.util.List;

public interface AdmCuencaMapper {
    int deleteByPrimaryKey(String codCuenca);
    int insert(AdmCuenca record);
    int insertSelective(AdmCuenca record);
    AdmCuenca selectByPrimaryKey(String codCuenca);
    int updateByPrimaryKeySelective(AdmCuenca record);
    int updateByPrimaryKey(AdmCuenca record);
    List<AdmCuencaDTO> selectAll();
    List<AdmCuencaDTO> filtro(AdmCuencaSearchDTO admCuencaSearchDTO);
}