package pe.gob.osinergmin.prie.admin.backend.services.impl;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmProcSectorTipico;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmProcSectorTipicoMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmProcSectorTipicoService;
@Service
public class AdmProcSectorTipicoServiceImpl implements AdmProcSectorTipicoService{

    @Autowired
    private AdmProcSectorTipicoMapper admProcSectorTipicoMapper;

    @Override
    public int deleteByPrimaryKey(String codSectorTipico,String codProcSupervision) {
        return admProcSectorTipicoMapper.deleteByPrimaryKey(codSectorTipico,codProcSupervision);
    }

    @Override
    public int insert(AdmProcSectorTipico record) {
        return admProcSectorTipicoMapper.insert(record);
    }

    @Override
    public int insertSelective(AdmProcSectorTipico record) {
        return admProcSectorTipicoMapper.insertSelective(record);
    }

    @Override
    public AdmProcSectorTipico selectByPrimaryKey(String codSectorTipico,String codProcSupervision) {
        return admProcSectorTipicoMapper.selectByPrimaryKey(codSectorTipico,codProcSupervision);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmProcSectorTipico record) {
        return admProcSectorTipicoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AdmProcSectorTipico record) {
        return admProcSectorTipicoMapper.updateByPrimaryKey(record);
    }

}
