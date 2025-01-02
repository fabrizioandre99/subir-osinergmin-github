package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTipoCentral;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCentral.AdmTipoCentralDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCentral.AdmTipoCentralFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCentral.AdmTipoCentralSearchDTO;

import java.util.List;

public interface AdmTipoCentralService{

    MessageDTO deleteByPrimaryKey(String codTipoCentral);
    MessageDTO insert(AdmTipoCentralFormdDTO record);
    MessageDTO updateByPrimaryKey(AdmTipoCentralFormdDTO record);
    List<AdmTipoCentralDTO> selectAll();
    PageInfo<AdmTipoCentralDTO> filtrar(AdmTipoCentralSearchDTO admTipoCentralSearchDTO);
}
