package pe.gob.osinergmin.prie.admin.backend.services;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmProcSectorTipico;
public interface AdmProcSectorTipicoService{

    int deleteByPrimaryKey(String codSectorTipico,String codProcSupervision);

    int insert(AdmProcSectorTipico record);

    int insertSelective(AdmProcSectorTipico record);

    AdmProcSectorTipico selectByPrimaryKey(String codSectorTipico,String codProcSupervision);

    int updateByPrimaryKeySelective(AdmProcSectorTipico record);

    int updateByPrimaryKey(AdmProcSectorTipico record);

}
