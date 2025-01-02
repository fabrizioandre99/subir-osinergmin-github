package pe.gob.osinergmin.prie.admin.backend.services;

import java.math.BigDecimal;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmSistemaElectricoDet;
public interface AdmSistemaElectricoDetService{

    int deleteByPrimaryKey(Integer idSeDet);

    int insert(AdmSistemaElectricoDet record);

    int insertSelective(AdmSistemaElectricoDet record);

    AdmSistemaElectricoDet selectByPrimaryKey(Integer idSeDet);

    int updateByPrimaryKeySelective(AdmSistemaElectricoDet record);

    int updateByPrimaryKey(AdmSistemaElectricoDet record);

}
