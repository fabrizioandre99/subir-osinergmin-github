package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmSuministroUsuario;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.suministroUsuario.AdmSuministroUsuarioDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.suministroUsuario.AdmSuministroUsuarioFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.suministroUsuario.AdmSuministroUsuarioSearchDTO;

import java.util.List;

public interface AdmSuministroUsuarioService {
    MessageDTO insert(AdmSuministroUsuarioFormDTO record);
    int insertSelective(AdmSuministroUsuario record);
    MessageDTO updateByPrimaryKey(AdmSuministroUsuarioFormDTO record);
    MessageDTO deleteByPrimaryKey(String codSuministroUsuario);
    List<AdmSuministroUsuarioDTO> selectAll();
    PageInfo<AdmSuministroUsuarioDTO> filtrar(AdmSuministroUsuarioSearchDTO admSuministroUsuarioSearchDTO);
}
