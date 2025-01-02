package pe.gob.osinergmin.prie.admin.backend.services;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmDevanado;
public interface AdmDevanadoService{

    int deleteByPrimaryKey(Integer idDevanado);

    int insert(AdmDevanado record);

    int insertSelective(AdmDevanado record);

    AdmDevanado selectByPrimaryKey(Integer idDevanado);

    int updateByPrimaryKeySelective(AdmDevanado record);

    int updateByPrimaryKey(AdmDevanado record);

}
