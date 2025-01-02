package pe.gob.osinergmin.prie.admin.backend.services.impl;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmBarraSiselecAlim;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmBarraSiselecAlimMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmBarraSiselecAlimService;
@Service
public class AdmBarraSiselecAlimServiceImpl implements AdmBarraSiselecAlimService{

    @Autowired
    private AdmBarraSiselecAlimMapper admBarraSiselecAlimMapper;

    @Override
    public int deleteByPrimaryKey(String codSisElec,String codBarra) {
        return admBarraSiselecAlimMapper.deleteByPrimaryKey(codSisElec,codBarra);
    }

    @Override
    public int insert(AdmBarraSiselecAlim record) {
        return admBarraSiselecAlimMapper.insert(record);
    }

    @Override
    public int insertSelective(AdmBarraSiselecAlim record) {
        return admBarraSiselecAlimMapper.insertSelective(record);
    }

    @Override
    public AdmBarraSiselecAlim selectByPrimaryKey(String codSisElec,String codBarra) {
        return admBarraSiselecAlimMapper.selectByPrimaryKey(codSisElec,codBarra);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmBarraSiselecAlim record) {
        return admBarraSiselecAlimMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AdmBarraSiselecAlim record) {
        return admBarraSiselecAlimMapper.updateByPrimaryKey(record);
    }

}
