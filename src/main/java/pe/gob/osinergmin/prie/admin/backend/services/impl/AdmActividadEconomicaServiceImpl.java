package pe.gob.osinergmin.prie.admin.backend.services.impl;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import pe.gob.osinergmin.prie.admin.backend.dto.actividadEconomica.AdmActividadEconomicaDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmActividadEconomicaMapper;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmActividadEconomica;
import pe.gob.osinergmin.prie.admin.backend.services.AdmActividadEconomicaService;

import java.util.List;

@Service
public class AdmActividadEconomicaServiceImpl implements AdmActividadEconomicaService{

    @Autowired
    private AdmActividadEconomicaMapper admActividadEconomicaMapper;

    @Override
    public int insert(AdmActividadEconomica record) {
        return admActividadEconomicaMapper.insert(record);
    }

    @Override
    public int insertSelective(AdmActividadEconomica record) {
        return admActividadEconomicaMapper.insertSelective(record);
    }

    @Override
    public List<AdmActividadEconomicaDTO> selectAll() {
        return admActividadEconomicaMapper.selectAll();
    }

}
