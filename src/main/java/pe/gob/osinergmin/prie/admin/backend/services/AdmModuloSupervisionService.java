package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmModuloSupervision;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.moduloSupervision.AdmModuloSupervisionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.moduloSupervision.AdmModuloSupervisionFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.moduloSupervision.AdmModuloSupervisionSearchDTO;

import java.util.List;

public interface AdmModuloSupervisionService {

    MessageDTO insert(AdmModuloSupervisionFormDTO record);
    MessageDTO updateByPrimaryKey(AdmModuloSupervisionFormDTO record);
    MessageDTO deleteByPrimaryKey(String coModulo);
    AdmModuloSupervision selectByPrimaryKey(String coModulo);
    List<AdmModuloSupervisionDTO> selectAll();
    PageInfo<AdmModuloSupervisionDTO> filtrar(AdmModuloSupervisionSearchDTO searchDTO);
}
