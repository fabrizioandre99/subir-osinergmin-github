package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmProcSupervision;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procSupervision.AdmProcSupervisionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procSupervision.AdmProcSupervisionFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procSupervision.AdmProcSupervisionSearchDTO;

import java.util.List;

public interface AdmProcSupervisionService{

    MessageDTO deleteByPrimaryKey(String codProcSupervision);

    MessageDTO insert(AdmProcSupervisionFormDTO record);

    int insertSelective(AdmProcSupervision record);

    AdmProcSupervision selectByPrimaryKey(String codProcSupervision);

    int updateByPrimaryKeySelective(AdmProcSupervision record);

    MessageDTO updateByPrimaryKey(AdmProcSupervisionFormDTO record);

    List<AdmProcSupervisionDTO> selectAll();

    PageInfo<AdmProcSupervisionDTO> filtrar(AdmProcSupervisionSearchDTO admProcSupervisionSearchDTO);
}
