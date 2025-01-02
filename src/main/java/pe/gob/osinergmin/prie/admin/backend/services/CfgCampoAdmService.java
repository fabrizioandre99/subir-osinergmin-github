package pe.gob.osinergmin.prie.admin.backend.services;

import org.apache.ibatis.annotations.Param;
import pe.gob.osinergmin.prie.admin.backend.domain.CfgCampoAdm;

import java.util.List;

public interface CfgCampoAdmService{

    int deleteByPrimaryKey(String codCampo,String codTabla);

    int insert(CfgCampoAdm record);

    int insertSelective(CfgCampoAdm record);

    CfgCampoAdm selectByPrimaryKey(String codCampo,String codTabla);

    int updateByPrimaryKeySelective(CfgCampoAdm record);

    int updateByPrimaryKey(CfgCampoAdm record);

    List<CfgCampoAdm> getCamposByTabla(String codTabla);
}
