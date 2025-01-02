package pe.gob.osinergmin.prie.admin.backend.services;

import pe.gob.osinergmin.prie.admin.backend.domain.CfgParametro;
public interface CfgParametroService{

    int deleteByPrimaryKey(String codGrupo,String codParametro);

    int insert(CfgParametro record);

    int insertSelective(CfgParametro record);

    CfgParametro selectByPrimaryKey(String codGrupo,String codParametro);

    int updateByPrimaryKeySelective(CfgParametro record);

    int updateByPrimaryKey(CfgParametro record);

}
