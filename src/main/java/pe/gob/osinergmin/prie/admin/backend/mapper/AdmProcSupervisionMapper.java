package pe.gob.osinergmin.prie.admin.backend.mapper;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmProcSupervision;
import pe.gob.osinergmin.prie.admin.backend.dto.procSupervision.AdmProcSupervisionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procSupervision.AdmProcSupervisionSearchDTO;

import java.util.List;

public interface AdmProcSupervisionMapper {
    int deleteByPrimaryKey(String codProcSupervision);

    int insert(AdmProcSupervision record);

    int insertSelective(AdmProcSupervision record);

    AdmProcSupervision selectByPrimaryKey(String codProcSupervision);

    int updateByPrimaryKeySelective(AdmProcSupervision record);

    int updateByPrimaryKey(AdmProcSupervision record);

    List<AdmProcSupervisionDTO> selectAll();

    List<AdmProcSupervisionDTO> filtrar(AdmProcSupervisionSearchDTO admProcSupervisionSearchDTO);
}