package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmCentralGeneracion;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.centralGeneracion.AdmCentralGeneracionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.centralGeneracion.AdmCentralGeneracionFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.centralGeneracion.AdmCentralGeneracionSearchDTO;

import java.util.List;

public interface AdmCentralGeneracionService{

    MessageDTO deleteByPrimaryKey(String codCentralGeneracion);
    MessageDTO insert(AdmCentralGeneracionFormdDTO admCentralGeneracionFormdDTO);
    int insertSelective(AdmCentralGeneracion record);
    AdmCentralGeneracion selectByPrimaryKey(String codCentralGeneracion);
    int updateByPrimaryKeySelective(AdmCentralGeneracion record);
    MessageDTO updateByPrimaryKey(AdmCentralGeneracionFormdDTO admCentralGeneracionFormdDTO);
    List<AdmCentralGeneracionDTO> selectAll();
    PageInfo<AdmCentralGeneracionDTO> filtro(AdmCentralGeneracionSearchDTO admCentralGeneracionSearchDTO);
}
