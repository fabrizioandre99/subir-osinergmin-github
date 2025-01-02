package pe.gob.osinergmin.prie.admin.backend.mapper;

import org.apache.ibatis.annotations.Param;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmFuncionProcSuperv;
import pe.gob.osinergmin.prie.admin.backend.dto.funcionProcSuperv.AdmFuncionProcSupervDTO;

import java.util.List;

public interface AdmFuncionProcSupervMapper {
    int deleteByPrimaryKey(@Param("codProcSupervision") String codProcSupervision, @Param("codFuncionProcSuperv") String codFuncionProcSuperv);

    int insert(AdmFuncionProcSuperv record);

    int insertSelective(AdmFuncionProcSuperv record);

    AdmFuncionProcSuperv selectByPrimaryKey(@Param("codProcSupervision") String codProcSupervision, @Param("codFuncionProcSuperv") String codFuncionProcSuperv);

    int updateByPrimaryKeySelective(AdmFuncionProcSuperv record);

    int updateByPrimaryKey(AdmFuncionProcSuperv record);

    List<AdmFuncionProcSupervDTO> listar();

    String existeCodFuncion(@Param("codFuncionProcSuperv") String codFuncionProcSuperv);

    int eliminar (@Param("codProcSupervision") String codProcSupervision);
}