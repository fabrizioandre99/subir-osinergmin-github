package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmSunat;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sunat.AdmSunatDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sunat.AdmSunatFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sunat.AdmSunatSearchDTO;

import java.util.List;

public interface AdmSunatService{

    MessageDTO deleteByPrimaryKey(String codSunat);

    MessageDTO insert(AdmSunatFormDTO record);

    int insertSelective(AdmSunat record);

    AdmSunat selectByPrimaryKey(String codSunat);

    int updateByPrimaryKeySelective(AdmSunat record);

    MessageDTO updateByPrimaryKey(AdmSunatFormDTO record);

    List<AdmSunatDTO> listar ();

    PageInfo<AdmSunatDTO> listarFiltro(AdmSunatSearchDTO searchDTO);
}
