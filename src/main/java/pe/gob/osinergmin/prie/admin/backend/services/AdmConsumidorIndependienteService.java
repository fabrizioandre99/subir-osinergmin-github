package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmConsumidorIndependiente;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.consumirIdependiente.AdmConsumidorIndependienteDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.consumirIdependiente.AdmConsumidorIndependienteFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.consumirIdependiente.AdmConsumidorIndependienteSearchDTO;

import java.util.List;

public interface AdmConsumidorIndependienteService{

    MessageDTO insert(AdmConsumidorIndependienteFormdDTO record);
    int insertSelective(AdmConsumidorIndependiente record);
    MessageDTO updateByPrimaryKey(AdmConsumidorIndependienteFormdDTO record);
    MessageDTO deleteByPrimaryKey(String codConsumidorInde);
    List<AdmConsumidorIndependienteDTO> selectAll();
    PageInfo<AdmConsumidorIndependienteDTO> filtrar(AdmConsumidorIndependienteSearchDTO admConsumidorIndependienteSearchDTO);

}
