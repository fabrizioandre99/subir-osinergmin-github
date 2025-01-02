package pe.gob.osinergmin.prie.admin.backend.mapper;

import java.math.BigDecimal;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmSistemaElectricoDet;
import pe.gob.osinergmin.prie.admin.backend.dto.sistemaElectricoDet.SistemaElectricoDetDTO;

public interface AdmSistemaElectricoDetMapper {
    int deleteByPrimaryKey(Integer idSeDet);

    int insert(AdmSistemaElectricoDet record);

    int insertSelective(AdmSistemaElectricoDet record);

    AdmSistemaElectricoDet selectByPrimaryKey(Integer idSeDet);

    int updateByPrimaryKeySelective(AdmSistemaElectricoDet record);

    int updateByPrimaryKey(AdmSistemaElectricoDet record);

    int actualizarDos(SistemaElectricoDetDTO record);

    int insertarDos(SistemaElectricoDetDTO record);

    Integer selectMaxIdSeDet();

    int deleteByCodSisElec(String codSisElec);

}