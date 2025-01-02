package pe.gob.osinergmin.prie.admin.backend.mapper;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmTipoCombustible;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCombustible.AdmTipoCombustibleDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCombustible.AdmTipoCombustibleSearchDTO;

import java.util.List;

public interface AdmTipoCombustibleMapper {
    int deleteByPrimaryKey(String codTipoCombustible);
    int insert(AdmTipoCombustible record);
    int insertSelective(AdmTipoCombustible record);
    AdmTipoCombustible selectByPrimaryKey(String codTipoCombustible);
    int updateByPrimaryKeySelective(AdmTipoCombustible record);
    int updateByPrimaryKey(AdmTipoCombustible record);
    List<AdmTipoCombustibleDTO> listartodo();
    List<AdmTipoCombustibleDTO> filtrarTipoCombustible(AdmTipoCombustibleSearchDTO admTipoCombustibleSearchDTO);
}