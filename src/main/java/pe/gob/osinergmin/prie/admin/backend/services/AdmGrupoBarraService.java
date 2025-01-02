package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmGrupoBarra;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoBarra.AdmGrupoBarraSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoBarra.AdmGrupoBarraformDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoBarra.GrupoBarraResponseDTO;

import java.util.List;

public interface AdmGrupoBarraService{

    int deleteByPrimaryKey(String codBarraGrupo,String codBarra);

    int insert(AdmGrupoBarra record);

    int insertSelective(AdmGrupoBarra record);

    int updateByPrimaryKeySelective(AdmGrupoBarra record);

    int updateByPrimaryKey(AdmGrupoBarra record);

    MessageDTO insertGrupoBarra(AdmGrupoBarraformDTO admGrupoBarraformDTO);

    MessageDTO actualizarGrupoBarra(AdmGrupoBarraformDTO admGrupoBarraformDTO);

    MessageDTO eliminarGrupoBarra(String codBarra);

    List<GrupoBarraResponseDTO> listarGruposDeBarras();

    PageInfo<GrupoBarraResponseDTO> filtrar(AdmGrupoBarraSearchDTO admGrupoBarraSearchDTO);
}
