package pe.gob.osinergmin.prie.admin.backend.services;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmActividadEconomica;
import pe.gob.osinergmin.prie.admin.backend.dto.actividadEconomica.AdmActividadEconomicaDTO;

import java.util.List;

public interface AdmActividadEconomicaService{

    int insert(AdmActividadEconomica record);

    int insertSelective(AdmActividadEconomica record);

    List<AdmActividadEconomicaDTO> selectAll();

}
