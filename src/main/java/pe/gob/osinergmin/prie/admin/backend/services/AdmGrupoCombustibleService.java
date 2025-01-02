package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmGrupoCombustible;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoCombustible.AdmGrupoCombustibleDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoCombustible.AdmGrupoCombustibleFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoCombustible.AdmGrupoCombustibleSearchDTO;

import java.util.List;

public interface AdmGrupoCombustibleService{

    MessageDTO deleteByPrimaryKey(String codGrupoCombustible);
    MessageDTO insert(AdmGrupoCombustibleFormdDTO record);
    int insertSelective(AdmGrupoCombustible record);
    AdmGrupoCombustible selectByPrimaryKey(String codGrupoCombustible);
    int updateByPrimaryKeySelective(AdmGrupoCombustible record);
    MessageDTO updateByPrimaryKey(AdmGrupoCombustibleFormdDTO record);
    List<AdmGrupoCombustibleDTO> selectAll();
    PageInfo<AdmGrupoCombustibleDTO> filtrar(AdmGrupoCombustibleSearchDTO admGrupoCombustibleSearchDTO);

}
