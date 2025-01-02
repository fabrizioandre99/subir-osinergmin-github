package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTipoSistTrans;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoSistTrans.AdmTipoSistTransDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoSistTrans.AdmTipoSistTransFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoSistTrans.AdmTipoSistTransSearchDTO;

import java.util.List;

public interface AdmTipoSistTransService{

    MessageDTO deleteByPrimaryKey(Integer idTipoSisTrans);

    MessageDTO insert(AdmTipoSistTransFormDTO record);

    int insertSelective(AdmTipoSistTrans record);

    AdmTipoSistTrans selectByPrimaryKey(Integer idTipoSisTrans);

    int updateByPrimaryKeySelective(AdmTipoSistTrans record);

    MessageDTO updateByPrimaryKey(AdmTipoSistTransFormDTO record);

    List<AdmTipoSistTransDTO> selectAll();

    PageInfo<AdmTipoSistTransDTO> filtrar(AdmTipoSistTransSearchDTO admTipoSistTransSearchDTO);

}
