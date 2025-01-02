package pe.gob.osinergmin.prie.admin.backend.mapper;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmConsumidorIndependiente;
import pe.gob.osinergmin.prie.admin.backend.dto.consumirIdependiente.AdmConsumidorIndependienteDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.consumirIdependiente.AdmConsumidorIndependienteSearchDTO;

import java.util.List;


public interface AdmConsumidorIndependienteMapper {
    int insert(AdmConsumidorIndependiente record);
    int insertSelective(AdmConsumidorIndependiente record);
    AdmConsumidorIndependiente selectByPrimaryKey(String codConsumidorInde);
    int updateByPrimaryKey(AdmConsumidorIndependiente record);
    int deleteByPrimaryKey(String codConsumidorInde);
    List<AdmConsumidorIndependienteDTO> selectAll();
    List<AdmConsumidorIndependienteDTO> filtrar(AdmConsumidorIndependienteSearchDTO admConsumidorIndependienteSearchDTO);
}