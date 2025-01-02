package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmGrupoGeneracion;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoGeneracion.AdmGrupoGeneracionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoGeneracion.AdmGrupoGeneracionFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoGeneracion.AdmGrupoGeneracionSearchDTO;

import java.util.List;

public interface AdmGrupoGeneracionService{

    MessageDTO deleteByPrimaryKey(String codGrupoGeneracion);
    MessageDTO insert(AdmGrupoGeneracionFormDTO admGrupoGeneracionFormDTO);
    MessageDTO updateByPrimaryKey(AdmGrupoGeneracionFormDTO admGrupoGeneracionFormDTO);
    List<AdmGrupoGeneracionDTO> selectAll();
    PageInfo<AdmGrupoGeneracionDTO> filtro(AdmGrupoGeneracionSearchDTO admGrupoGeneracionSearchDTO);
}
