package pe.gob.osinergmin.prie.admin.backend.mapper;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmSistemaElectrico;
import pe.gob.osinergmin.prie.admin.backend.dto.sisElectrico.AdmSistemaElectricoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sisElectrico.AdmSistemaElectricoSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sisElectrico.SistemaElectricoInsertDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sistemaElectricoDet.SistemaElectricoDetResultDTO;

import java.util.List;

public interface AdmSistemaElectricoMapper {
    int deleteByPrimaryKey(String codSisElec);
    int insert(SistemaElectricoInsertDTO record);
    int insertSelective(AdmSistemaElectrico record);
    AdmSistemaElectrico selectByPrimaryKey(String codSisElec);
    int updateByPrimaryKeySelective(AdmSistemaElectrico record);
    int updateByPrimaryKey(AdmSistemaElectrico record);
    int actualizarDos(SistemaElectricoInsertDTO record);
    List<AdmSistemaElectricoDTO> selectAll();
    List<AdmSistemaElectricoDTO> filtrar(AdmSistemaElectricoSearchDTO admSistemaElectricoSearchDTO );
}