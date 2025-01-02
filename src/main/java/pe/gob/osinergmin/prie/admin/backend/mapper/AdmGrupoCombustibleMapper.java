package pe.gob.osinergmin.prie.admin.backend.mapper;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmGrupoCombustible;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoCombustible.AdmGrupoCombustibleDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoCombustible.AdmGrupoCombustibleSearchDTO;

import java.util.List;

public interface AdmGrupoCombustibleMapper {
    int deleteByPrimaryKey(String codGrupoCombustible);
    int insert(AdmGrupoCombustible record);
    int insertSelective(AdmGrupoCombustible record);
    AdmGrupoCombustible selectByPrimaryKey(String codGrupoCombustible);
    int updateByPrimaryKeySelective(AdmGrupoCombustible record);
    int updateByPrimaryKey(AdmGrupoCombustible record);
    List<AdmGrupoCombustibleDTO> selectAll();
    List<AdmGrupoCombustibleDTO> filtrar(AdmGrupoCombustibleSearchDTO admGrupoCombustibleSearchDTO);
}