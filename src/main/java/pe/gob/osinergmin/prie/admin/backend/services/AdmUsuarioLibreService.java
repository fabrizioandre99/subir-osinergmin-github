package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmUsuarioLibre;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.usuarioLibre.AdmUsuarioLibreDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.usuarioLibre.AdmUsuarioLibreFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.usuarioLibre.AdmUsuarioLibreSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.usuarioLibre.AdmUsuarioLibreValidarRUC;

import java.util.List;

public interface AdmUsuarioLibreService {
    MessageDTO insert(AdmUsuarioLibreFormDTO record);
    int insertSelective(AdmUsuarioLibre record);
    MessageDTO updateByPrimaryKey(AdmUsuarioLibreFormDTO record);
    MessageDTO deleteByPrimaryKey(String codUsuarioLibre);
    List<AdmUsuarioLibreDTO> selectAll();
    PageInfo<AdmUsuarioLibreDTO> filtrar(AdmUsuarioLibreSearchDTO admUsuarioLibreSearchDTO);
    AdmUsuarioLibreValidarRUC validarSUNAT(String codUsuarioLibre) throws Exception;
}
