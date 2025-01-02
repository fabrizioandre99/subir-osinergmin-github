package pe.gob.osinergmin.prie.admin.backend.mapper;

import org.apache.ibatis.annotations.Param;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmPliegoSiselec;
import pe.gob.osinergmin.prie.admin.backend.dto.pliegoSiselec.PliegoSiselecDTO;

import java.util.List;

public interface AdmPliegoSiselecMapper {
    int deleteByPrimaryKey(@Param("annioMes") String annioMes, @Param("codPliego") String codPliego, @Param("codSisElec") String codSisElec);

    int insert(AdmPliegoSiselec record);

    int insertSelective(AdmPliegoSiselec record);

    AdmPliegoSiselec selectByPrimaryKey(@Param("annioMes") String annioMes, @Param("codPliego") String codPliego, @Param("codSisElec") String codSisElec);

    int updateByPrimaryKeySelective(AdmPliegoSiselec record);

    int updateByPrimaryKey(AdmPliegoSiselec record);

    int deleteByCodPliego(@Param("codPliego") String codPliego);

    int deleteByCodSisElec(@Param("codSisElec") String codSisElec);

    List<PliegoSiselecDTO> getSistemasElectricosByCodPliego(String codPliego);

}