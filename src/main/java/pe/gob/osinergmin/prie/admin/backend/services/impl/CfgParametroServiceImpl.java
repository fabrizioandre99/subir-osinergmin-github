package pe.gob.osinergmin.prie.admin.backend.services.impl;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import pe.gob.osinergmin.prie.admin.backend.domain.CfgParametro;
import pe.gob.osinergmin.prie.admin.backend.mapper.CfgParametroMapper;
import pe.gob.osinergmin.prie.admin.backend.services.CfgParametroService;
@Service
public class CfgParametroServiceImpl implements CfgParametroService{

    @Autowired
    private CfgParametroMapper cfgParametroMapper;

    @Override
    public int deleteByPrimaryKey(String codGrupo,String codParametro) {
        return cfgParametroMapper.deleteByPrimaryKey(codGrupo,codParametro);
    }

    @Override
    public int insert(CfgParametro record) {
        return cfgParametroMapper.insert(record);
    }

    @Override
    public int insertSelective(CfgParametro record) {
        return cfgParametroMapper.insertSelective(record);
    }

    @Override
    public CfgParametro selectByPrimaryKey(String codGrupo,String codParametro) {
        return cfgParametroMapper.selectByPrimaryKey(codGrupo,codParametro);
    }

    @Override
    public int updateByPrimaryKeySelective(CfgParametro record) {
        return cfgParametroMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CfgParametro record) {
        return cfgParametroMapper.updateByPrimaryKey(record);
    }

}
