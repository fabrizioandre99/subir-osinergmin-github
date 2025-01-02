package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTipoEmpresa;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoEmpresa.TipoEmpresaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoEmpresa.TipoEmpresaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoEmpresa.TipoEmpresaSearchDTO;

import java.util.List;

public interface AdmTipoEmpresaService{

    MessageDTO deleteByPrimaryKey(String codTipoEmpresa);

    MessageDTO insert(TipoEmpresaFormDTO tipoEmpresaFormDTO);

    int insertSelective(AdmTipoEmpresa record);

    AdmTipoEmpresa selectByPrimaryKey(String codTipoEmpresa);

    int updateByPrimaryKeySelective(AdmTipoEmpresa record);

    MessageDTO updateByPrimaryKey(TipoEmpresaFormDTO tipoEmpresaFormDTO);

    List<TipoEmpresaDTO> selectAll();

    PageInfo<TipoEmpresaDTO> ListaFiltro(TipoEmpresaSearchDTO tipoEmpresaSearchDTO);
}
