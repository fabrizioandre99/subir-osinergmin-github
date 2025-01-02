package pe.gob.osinergmin.prie.admin.backend.services.impl;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmTransRotacion;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmTransRotacionMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTransRotacionService;
@Service
public class AdmTransRotacionServiceImpl implements AdmTransRotacionService{

    @Autowired
    private AdmTransRotacionMapper admTransRotacionMapper;

    @Override
    public int deleteByPrimaryKey(Integer idTransfRotacion,String usuarioCreacion) {
        return admTransRotacionMapper.deleteByPrimaryKey(idTransfRotacion,usuarioCreacion);
    }

    @Override
    public int insert(AdmTransRotacion record) {
        return admTransRotacionMapper.insert(record);
    }

    @Override
    public int insertSelective(AdmTransRotacion record) {
        return admTransRotacionMapper.insertSelective(record);
    }

    @Override
    public AdmTransRotacion selectByPrimaryKey(Integer idTransfRotacion,String usuarioCreacion) {
        return admTransRotacionMapper.selectByPrimaryKey(idTransfRotacion,usuarioCreacion);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmTransRotacion record) {
        return admTransRotacionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AdmTransRotacion record) {
        return admTransRotacionMapper.updateByPrimaryKey(record);
    }

}
