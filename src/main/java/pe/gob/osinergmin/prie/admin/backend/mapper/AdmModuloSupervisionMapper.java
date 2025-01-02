package pe.gob.osinergmin.prie.admin.backend.mapper;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmModuloSupervision;
import pe.gob.osinergmin.prie.admin.backend.dto.moduloSupervision.AdmModuloSupervisionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.moduloSupervision.AdmModuloSupervisionSearchDTO;

import java.util.List;

public interface AdmModuloSupervisionMapper {
    int deleteByPrimaryKey(String coModulo);
    int insert(AdmModuloSupervision record);
    int insertSelective(AdmModuloSupervision record);
    AdmModuloSupervision selectByPrimaryKey(String coModulo);
    int updateByPrimaryKeySelective(AdmModuloSupervision record);
    int updateByPrimaryKey(AdmModuloSupervision record);
    List<AdmModuloSupervisionDTO> selectAll();
    List<AdmModuloSupervisionDTO> filtrar(AdmModuloSupervisionSearchDTO searchDTO);
}