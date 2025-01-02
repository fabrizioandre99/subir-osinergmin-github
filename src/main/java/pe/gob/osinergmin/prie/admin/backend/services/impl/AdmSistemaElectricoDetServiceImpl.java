package pe.gob.osinergmin.prie.admin.backend.services.impl;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmSistemaElectricoDetMapper;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmSistemaElectricoDet;
import pe.gob.osinergmin.prie.admin.backend.services.AdmSistemaElectricoDetService;
@Service
public class AdmSistemaElectricoDetServiceImpl implements AdmSistemaElectricoDetService{

    @Autowired
    private AdmSistemaElectricoDetMapper admSistemaElectricoDetMapper;

    @Override
    public int deleteByPrimaryKey(Integer idSeDet) {
        return admSistemaElectricoDetMapper.deleteByPrimaryKey(idSeDet);
    }

    @Override
    public int insert(AdmSistemaElectricoDet record) {
        return admSistemaElectricoDetMapper.insert(record);
    }

    @Override
    public int insertSelective(AdmSistemaElectricoDet record) {
        return admSistemaElectricoDetMapper.insertSelective(record);
    }

    @Override
    public AdmSistemaElectricoDet selectByPrimaryKey(Integer idSeDet) {
        return admSistemaElectricoDetMapper.selectByPrimaryKey(idSeDet);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmSistemaElectricoDet record) {
        return admSistemaElectricoDetMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AdmSistemaElectricoDet record) {
        return admSistemaElectricoDetMapper.updateByPrimaryKey(record);
    }

}
