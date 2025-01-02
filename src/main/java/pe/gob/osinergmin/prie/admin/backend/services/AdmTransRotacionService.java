package pe.gob.osinergmin.prie.admin.backend.services;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmTransRotacion;
public interface AdmTransRotacionService{

    int deleteByPrimaryKey(Integer idTransfRotacion,String usuarioCreacion);

    int insert(AdmTransRotacion record);

    int insertSelective(AdmTransRotacion record);

    AdmTransRotacion selectByPrimaryKey(Integer idTransfRotacion,String usuarioCreacion);

    int updateByPrimaryKeySelective(AdmTransRotacion record);

    int updateByPrimaryKey(AdmTransRotacion record);

}
