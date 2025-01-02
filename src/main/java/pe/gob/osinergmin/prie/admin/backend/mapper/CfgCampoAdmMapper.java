package pe.gob.osinergmin.prie.admin.backend.mapper;

import org.apache.ibatis.annotations.Param;
import pe.gob.osinergmin.prie.admin.backend.domain.CfgCampoAdm;

import java.util.List;
import java.util.Map;

public interface CfgCampoAdmMapper {
    int deleteByPrimaryKey(@Param("codCampo") String codCampo, @Param("codTabla") String codTabla);

    int insert(CfgCampoAdm record);

    int insertSelective(CfgCampoAdm record);

    CfgCampoAdm selectByPrimaryKey(@Param("codCampo") String codCampo, @Param("codTabla") String codTabla);

    int updateByPrimaryKeySelective(CfgCampoAdm record);

    int updateByPrimaryKey(CfgCampoAdm record);

    List<CfgCampoAdm> getCamposByTabla(@Param("codTabla") String codTabla);

    List<CfgCampoAdm> getObligatorios(@Param("codTabla") String codTabla);

    List<CfgCampoAdm> getPKs(@Param("codTabla") String codTabla);

    List<CfgCampoAdm> getCamposFiltrables(@Param("codTabla") String codTabla);

    //List<Map<String, Object>> getCamposByAllTabColumns(@Param("codTabla") String codTabla);
    List<Map<String, Object>> getCamposByAllTabColumns(@Param("owner") String owner, @Param("tableName") String tableName);

}