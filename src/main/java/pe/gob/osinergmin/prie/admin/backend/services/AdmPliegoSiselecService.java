package pe.gob.osinergmin.prie.admin.backend.services;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmPliegoSiselec;
public interface AdmPliegoSiselecService{

    int deleteByPrimaryKey(String annioMes,String codPliego,String codSisElec);

    int insert(AdmPliegoSiselec record);

    int insertSelective(AdmPliegoSiselec record);

    AdmPliegoSiselec selectByPrimaryKey(String annioMes,String codPliego,String codSisElec);

    int updateByPrimaryKeySelective(AdmPliegoSiselec record);

    int updateByPrimaryKey(AdmPliegoSiselec record);

}
