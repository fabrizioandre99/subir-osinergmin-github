package pe.gob.osinergmin.prie.admin.backend.services.impl;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import pe.gob.osinergmin.prie.admin.backend.mapper.CfgCampoAdmMapper;
import pe.gob.osinergmin.prie.admin.backend.domain.CfgCampoAdm;
import pe.gob.osinergmin.prie.admin.backend.services.CfgCampoAdmService;

import java.util.List;

@Service
public class CfgCampoAdmServiceImpl implements CfgCampoAdmService{

    @Autowired
    private CfgCampoAdmMapper cfgCampoAdmMapper;

    @Override
    public int deleteByPrimaryKey(String codCampo,String codTabla) {
        return cfgCampoAdmMapper.deleteByPrimaryKey(codCampo,codTabla);
    }

    @Override
    public int insert(CfgCampoAdm record) {
        return cfgCampoAdmMapper.insert(record);
    }

    @Override
    public int insertSelective(CfgCampoAdm record) {
        return cfgCampoAdmMapper.insertSelective(record);
    }

    @Override
    public CfgCampoAdm selectByPrimaryKey(String codCampo,String codTabla) {
        return cfgCampoAdmMapper.selectByPrimaryKey(codCampo,codTabla);
    }

    @Override
    public int updateByPrimaryKeySelective(CfgCampoAdm record) {
        return cfgCampoAdmMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CfgCampoAdm record) {
        return cfgCampoAdmMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<CfgCampoAdm> getCamposByTabla(String codTabla) {
        return cfgCampoAdmMapper.getCamposByTabla(codTabla);
    }

}
