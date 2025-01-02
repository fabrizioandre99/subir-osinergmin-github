package pe.gob.osinergmin.prie.admin.backend.services.impl;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import pe.gob.osinergmin.prie.admin.backend.mapper.AdmPliegoSiselecMapper;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmPliegoSiselec;
import pe.gob.osinergmin.prie.admin.backend.services.AdmPliegoSiselecService;
@Service
public class AdmPliegoSiselecServiceImpl implements AdmPliegoSiselecService{

    @Autowired
    private AdmPliegoSiselecMapper admPliegoSiselecMapper;

    @Override
    public int deleteByPrimaryKey(String annioMes,String codPliego,String codSisElec) {
        return admPliegoSiselecMapper.deleteByPrimaryKey(annioMes,codPliego,codSisElec);
    }

    @Override
    public int insert(AdmPliegoSiselec record) {
        return admPliegoSiselecMapper.insert(record);
    }

    @Override
    public int insertSelective(AdmPliegoSiselec record) {
        return admPliegoSiselecMapper.insertSelective(record);
    }

    @Override
    public AdmPliegoSiselec selectByPrimaryKey(String annioMes,String codPliego,String codSisElec) {
        return admPliegoSiselecMapper.selectByPrimaryKey(annioMes,codPliego,codSisElec);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmPliegoSiselec record) {
        return admPliegoSiselecMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AdmPliegoSiselec record) {
        return admPliegoSiselecMapper.updateByPrimaryKey(record);
    }

}
