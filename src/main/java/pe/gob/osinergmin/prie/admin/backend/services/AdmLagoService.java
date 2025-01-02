package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmLago;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.lago.AdmLagoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.lago.AdmLagoFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.lago.AdmLagoSearchDTO;

import java.util.List;

public interface AdmLagoService{

    MessageDTO deleteByPrimaryKey(String codLago);
    MessageDTO insert(AdmLagoFormDTO admLagoFormDTO);
    int insertSelective(AdmLago record);
    AdmLago selectByPrimaryKey(String codLago);
    int updateByPrimaryKeySelective(AdmLago record);
    MessageDTO updateByPrimaryKey(AdmLagoFormDTO admLagoFormDTO);
    List<AdmLagoDTO> selectAll();
    PageInfo<AdmLagoDTO> filtro(AdmLagoSearchDTO admLagoSearchDTO);

}
