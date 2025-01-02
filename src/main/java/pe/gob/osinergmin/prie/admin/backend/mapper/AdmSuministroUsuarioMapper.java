package pe.gob.osinergmin.prie.admin.backend.mapper;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmSuministroUsuario;
import pe.gob.osinergmin.prie.admin.backend.dto.suministroUsuario.AdmSuministroUsuarioDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.suministroUsuario.AdmSuministroUsuarioSearchDTO;

import java.util.List;

public interface AdmSuministroUsuarioMapper {
    int insert(AdmSuministroUsuario record);
    int insertSelective(AdmSuministroUsuario record);
    AdmSuministroUsuario selectByPrimaryKey(String codSuministroUsuario);
    int updateByPrimaryKey(AdmSuministroUsuario record);
    int deleteByPrimaryKey(String codSuministroUsuario);
    List<AdmSuministroUsuarioDTO> selectAll();
    List<AdmSuministroUsuarioDTO> filtrar(AdmSuministroUsuarioSearchDTO admSuministroUsuarioSearchDTO);
}
