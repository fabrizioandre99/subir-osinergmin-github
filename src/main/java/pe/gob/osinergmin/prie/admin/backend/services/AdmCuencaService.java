package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmCuenca;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.cuenca.AdmCuencaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.cuenca.AdmCuencaFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.cuenca.AdmCuencaSearchDTO;

import java.util.List;

public interface AdmCuencaService{

    MessageDTO deleteByPrimaryKey(String codCuenca);
    MessageDTO insert(AdmCuencaFormdDTO admCuencaFormdDTO);
    int insertSelective(AdmCuenca record);
    AdmCuenca selectByPrimaryKey(String codCuenca);
    int updateByPrimaryKeySelective(AdmCuenca record);
    MessageDTO updateByPrimaryKey(AdmCuencaFormdDTO admCuencaFormdDTO);
    List<AdmCuencaDTO> selectAll();
    PageInfo<AdmCuencaDTO> filtro(AdmCuencaSearchDTO admCuencaSearchDTO);

}
