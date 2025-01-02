package pe.gob.osinergmin.prie.admin.backend.mapper;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmSunat;
import pe.gob.osinergmin.prie.admin.backend.dto.admUit.AdmUitDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.admUit.AdmUitSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sunat.AdmSunatDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sunat.AdmSunatFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sunat.AdmSunatSearchDTO;

import java.util.List;

public interface AdmSunatMapper {
    int deleteByPrimaryKey(String codSunat);

    int insert(AdmSunat record);

    int insertSelective(AdmSunat record);

    AdmSunat selectByPrimaryKey(String codSunat);

    int updateByPrimaryKeySelective(AdmSunat record);

    int updateByPrimaryKey(AdmSunat record);

    List<AdmSunatDTO> listar ();

    List<AdmSunatDTO> listarFiltro(AdmSunatSearchDTO searchDTO);
}