package pe.gob.osinergmin.prie.admin.backend.services.impl;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmDevanado;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmDevanadoMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmDevanadoService;
@Service
public class AdmDevanadoServiceImpl implements AdmDevanadoService{

    @Autowired
    private AdmDevanadoMapper admDevanadoMapper;

    @Override
    public int deleteByPrimaryKey(Integer idDevanado) {
        return admDevanadoMapper.deleteByPrimaryKey(idDevanado);
    }

    @Override
    public int insert(AdmDevanado record) {
        return admDevanadoMapper.insert(record);
    }

    @Override
    public int insertSelective(AdmDevanado record) {
        return admDevanadoMapper.insertSelective(record);
    }

    @Override
    public AdmDevanado selectByPrimaryKey(Integer idDevanado) {
        return admDevanadoMapper.selectByPrimaryKey(idDevanado);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmDevanado record) {
        return admDevanadoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AdmDevanado record) {
        return admDevanadoMapper.updateByPrimaryKey(record);
    }

}
