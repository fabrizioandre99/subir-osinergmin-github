package pe.gob.osinergmin.prie.admin.backend.mapper;

import org.apache.ibatis.annotations.Param;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmBarraSiselecAlim;

public interface AdmBarraSiselecAlimMapper {
    int deleteByPrimaryKey(@Param("codSisElec") String codSisElec, @Param("codBarra") String codBarra);

    int insert(AdmBarraSiselecAlim record);

    int insertSelective(AdmBarraSiselecAlim record);

    AdmBarraSiselecAlim selectByPrimaryKey(@Param("codSisElec") String codSisElec, @Param("codBarra") String codBarra);

    int updateByPrimaryKeySelective(AdmBarraSiselecAlim record);

    int updateByPrimaryKey(AdmBarraSiselecAlim record);

    int borrarPorBarra (String codBarra);

    int borrarPorCodSisElec (String codSisElec);
}