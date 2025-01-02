package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmEmpresa;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.empresa.AdmEmpresaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.empresa.AdmEmpresaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.empresa.AdmEmpresaSearchDTO;

import java.net.UnknownHostException;
import java.util.List;

public interface AdmEmpresaService {

    MessageDTO deleteByPrimaryKey(String codEmpresa);

    MessageDTO insert(AdmEmpresaFormDTO admEmpresaFormDTO) throws UnknownHostException;

    int insertSelective(AdmEmpresa record);

    AdmEmpresa selectByPrimaryKey(String codEmpresa);

    int updateByPrimaryKeySelective(AdmEmpresa record);

    MessageDTO updateByPrimaryKey(AdmEmpresaFormDTO admEmpresaFormDTO) throws UnknownHostException;

    List<AdmEmpresaDTO> selectAll();

    PageInfo<AdmEmpresaDTO> listarFiltro(AdmEmpresaSearchDTO searchDTO);

    MessageDTO insertNuevo(AdmEmpresaFormDTO empresaFormDTO);

    MessageDTO updateNuevo(AdmEmpresaFormDTO empresaFormDTO);

}
