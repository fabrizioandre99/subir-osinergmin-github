package pe.gob.osinergmin.prie.admin.backend.services;

import org.apache.ibatis.annotations.Param;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmProcEmpresa;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.empresa.AdmProcEmpresaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procEmpresa.*;

import java.util.List;

public interface AdmProcEmpresaService{

    MessageDTO deleteByPrimaryKey(Integer idProcEmpresa);

    MessageDTO insert(AdmProcEmpresaFormDTO record);

    int insertSelective(AdmProcEmpresa record);

    AdmProcEmpresa selectByPrimaryKey(Integer idProcEmpresa);

//    int updateByPrimaryKeySelective(AdmProcEmpresa record);

    MessageDTO updateByPrimaryKey(AdmProcEmpresaFormDTO record);

    List<AdmProcEmpresaDTO> selectAll();

    List<AdmProcEmpresaResultDTO> listarCreacionEmpresa();

    List<AdmProcEmpresaDTO> listarPorCodEmpresa(String codEmpresa);

    List<AdmProcEmpresaFuncionDTO> listarFuncion (@Param("codProcSupervision") String codProcSupervision);

    List<AdmProcEmpresaDTO> listarPorCodEmpresaFunTipo(String codEmpresa);
}
