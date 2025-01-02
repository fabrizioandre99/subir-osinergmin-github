package pe.gob.osinergmin.prie.admin.backend.mapper;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmDevanado;
import pe.gob.osinergmin.prie.admin.backend.dto.devanado.DevanadoDTO;

import java.util.List;

public interface AdmDevanadoMapper {
    int deleteByPrimaryKey(Integer idDevanado);

    int insert(AdmDevanado record);

    int insertSelective(AdmDevanado record);

    AdmDevanado selectByPrimaryKey(Integer idDevanado);

    int updateByPrimaryKeySelective(AdmDevanado record);

    int updateByPrimaryKey(AdmDevanado record);

    int deleteByTransformadorId(Integer idTransformador);

    List<DevanadoDTO> selectByTransformadorId(Integer idTransformador);

    int borrarPorAreaDemanda (Integer areaDemanda);

    Integer getNextId();
}