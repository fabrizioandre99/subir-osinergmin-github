package pe.gob.osinergmin.prie.admin.backend.mapper;

import org.apache.ibatis.annotations.Param;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmProcEmpresa;
import pe.gob.osinergmin.prie.admin.backend.dto.empresa.AdmProcEmpresaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procEmpresa.AdmProcEmpresaFuncionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procEmpresa.AdmProcEmpresaResultDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procEmpresa.ProcEmpresaPorCodDTO;

import java.util.List;

public interface AdmProcEmpresaMapper {
    int deleteByPrimaryKey(Integer idProcEmpresa);

    int insert(AdmProcEmpresa record);

    int insertSelective(AdmProcEmpresa record);

    AdmProcEmpresa selectByPrimaryKey(Integer idProcEmpresa);

    int updateByPrimaryKeySelective(AdmProcEmpresaDTO record);


    int updateByPrimaryKey(AdmProcEmpresa record);

    List<AdmProcEmpresaDTO> selectAll();

    List<AdmProcEmpresaResultDTO> listarCreacionEmpresa();

    List<AdmProcEmpresaDTO> listarPorCodEmpresa(@Param("codEmpresa") String codEmpresa);

    List<AdmProcEmpresaFuncionDTO> listarFuncion(@Param("codProcSupervision") String codProcSupervision);

    List<AdmProcEmpresaDTO> listarPorCodEmpresaFunTipo(@Param("codEmpresa") String codEmpresa);

    void deleteByEmpresaAndProceso(@Param("codEmpresa") String codEmpresa,
                                   @Param("codProcSupervision") String codProcSupervision,
                                   @Param("codTipoEmpresa") String codTipoEmpresa,
                                   @Param("codFuncionProcSuperv") String codFuncionProcSuperv);

    int deleteByIdProcEmpresa (Integer idProcEmpresa);
}
