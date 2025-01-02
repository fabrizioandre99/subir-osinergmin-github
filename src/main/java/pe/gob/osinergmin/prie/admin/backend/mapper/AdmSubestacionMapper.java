package pe.gob.osinergmin.prie.admin.backend.mapper;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmSubestacion;
import pe.gob.osinergmin.prie.admin.backend.dto.subestacion.AdmSubestacionListadoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.subestacion.AdmSubestacionSearchDTO;

import java.util.List;

public interface AdmSubestacionMapper {
    int deleteByPrimaryKey(String codSubestacion);

    int insert(AdmSubestacion record);

    int insertSelective(AdmSubestacion record);

    AdmSubestacion selectByPrimaryKey(String codSubestacion);

    int updateByPrimaryKeySelective(AdmSubestacion record);

    int updateByPrimaryKey(AdmSubestacion record);

    List<AdmSubestacionListadoDTO> listarSubestaciones();

    List<AdmSubestacionListadoDTO> listarFiltro(AdmSubestacionSearchDTO searchDTO);
}