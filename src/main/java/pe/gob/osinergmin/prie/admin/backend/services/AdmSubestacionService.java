package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmSubestacion;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.subestacion.AdmSubestacionActualizarFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.subestacion.AdmSubestacionListadoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.subestacion.AdmSubestacionFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.subestacion.AdmSubestacionSearchDTO;

import java.util.List;

public interface AdmSubestacionService{

    MessageDTO deleteByPrimaryKey(String codSubestacion);

    MessageDTO insert(AdmSubestacionFormDTO admSubestacionFormDTO);

    int insertSelective(AdmSubestacion record);

    AdmSubestacion selectByPrimaryKey(String codSubestacion);

    int updateByPrimaryKeySelective(AdmSubestacion record);

    MessageDTO updateByPrimaryKey(AdmSubestacionActualizarFormDTO admSubestacionFormDTO);

    List<AdmSubestacionListadoDTO> listarSubestaciones();

    PageInfo<AdmSubestacionListadoDTO> listarFiltro(AdmSubestacionSearchDTO searchDTO);
}
