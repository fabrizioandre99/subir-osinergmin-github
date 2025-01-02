package pe.gob.osinergmin.prie.admin.backend.services;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmBarraSiselecAlim;
public interface AdmBarraSiselecAlimService{

    int deleteByPrimaryKey(String codSisElec,String codBarra);

    int insert(AdmBarraSiselecAlim record);

    int insertSelective(AdmBarraSiselecAlim record);

    AdmBarraSiselecAlim selectByPrimaryKey(String codSisElec,String codBarra);

    int updateByPrimaryKeySelective(AdmBarraSiselecAlim record);

    int updateByPrimaryKey(AdmBarraSiselecAlim record);

}
