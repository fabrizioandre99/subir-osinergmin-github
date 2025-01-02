package pe.gob.osinergmin.prie.admin.backend.services;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmFuncionProcSuperv;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.funcionProcSuperv.AdmFuncionProcSupervDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.funcionProcSuperv.AdmFuncionProcSupervFormDTO;

import java.util.List;

public interface AdmFuncionProcSupervService{

    MessageDTO deleteByPrimaryKey(String codProcSupervision, String codFuncionProcSuperv);

    MessageDTO insert(AdmFuncionProcSupervFormDTO record);

    int insertSelective(AdmFuncionProcSuperv record);

    AdmFuncionProcSuperv selectByPrimaryKey(String codProcSupervision,String codFuncionProcSuperv);

    int updateByPrimaryKeySelective(AdmFuncionProcSuperv record);

    MessageDTO updateByPrimaryKey(AdmFuncionProcSupervFormDTO record);

    List<AdmFuncionProcSupervDTO> listar();

}
