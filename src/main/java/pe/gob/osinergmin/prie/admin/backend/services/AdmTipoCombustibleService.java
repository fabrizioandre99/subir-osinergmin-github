package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTipoCombustible;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCombustible.AdmTipoCombustibleDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCombustible.AdmTipoCombustibleFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCombustible.AdmTipoCombustibleSearchDTO;

import java.util.List;

public interface AdmTipoCombustibleService{

    MessageDTO deleteByPrimaryKey(String codTipoCombustible);
    MessageDTO insert(AdmTipoCombustibleFormDTO admTipoCombustibleFormDTO);
    MessageDTO updateByPrimaryKey(AdmTipoCombustibleFormDTO admTipoCombustibleFormDTO);
    List<AdmTipoCombustibleDTO> listartodo();
    PageInfo<AdmTipoCombustibleDTO> filtrarTipoCombustible(AdmTipoCombustibleSearchDTO admTipoCombustibleSearchDTO);

}
