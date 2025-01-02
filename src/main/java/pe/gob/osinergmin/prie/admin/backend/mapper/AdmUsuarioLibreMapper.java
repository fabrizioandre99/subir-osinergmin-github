package pe.gob.osinergmin.prie.admin.backend.mapper;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmUsuarioLibre;
import pe.gob.osinergmin.prie.admin.backend.dto.usuarioLibre.AdmUsuarioLibreDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.usuarioLibre.AdmUsuarioLibreSearchDTO;

import java.util.List;

public interface AdmUsuarioLibreMapper {
    int insert(AdmUsuarioLibre record);
    int insertSelective(AdmUsuarioLibre record);
    AdmUsuarioLibre selectByPrimaryKey(String codUsuarioLibre);
    int updateByPrimaryKey(AdmUsuarioLibre record);
    int deleteByPrimaryKey(String codUsuarioLibre);
    List<AdmUsuarioLibreDTO> selectAll();
    List<AdmUsuarioLibreDTO> filtrar(AdmUsuarioLibreSearchDTO admUsuarioLibreSearchDTO);
}
