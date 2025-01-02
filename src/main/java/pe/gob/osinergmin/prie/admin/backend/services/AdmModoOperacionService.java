package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmModoOperacion;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.modoOperacion.AdmModoOperacionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.modoOperacion.AdmModoOperacionFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.modoOperacion.AdmModoOperacionSearchDTO;

import java.util.List;

public interface AdmModoOperacionService{

    MessageDTO deleteByPrimaryKey(String codModoOperacion);

    MessageDTO insert(AdmModoOperacionFormdDTO admModoOperacionFormdDTO);

    int insertSelective(AdmModoOperacion record);

    AdmModoOperacion selectByPrimaryKey(String codModoOperacion);

    int updateByPrimaryKeySelective(AdmModoOperacion record);

    MessageDTO updateByPrimaryKey(AdmModoOperacionFormdDTO admModoOperacionFormdDTO);

    List<AdmModoOperacionDTO> selectAll();

    PageInfo<AdmModoOperacionDTO> filtro(AdmModoOperacionSearchDTO admModoOperacionSearchDTO);

}
