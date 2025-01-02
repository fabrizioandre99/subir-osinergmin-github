package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmSistemaElectrico;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sisElectrico.AdmSistemaElectricoActualizarDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sisElectrico.AdmSistemaElectricoCrearDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sisElectrico.AdmSistemaElectricoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sisElectrico.AdmSistemaElectricoSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sistemaElectricoDet.SistemaElectricoDetResultDTO;

import java.util.List;

public interface AdmSistemaElectricoService{

    MessageDTO deleteByPrimaryKey(String codSisElec);
    MessageDTO insert(AdmSistemaElectricoCrearDTO record);
    int insertSelective(AdmSistemaElectrico record);
    AdmSistemaElectrico selectByPrimaryKey(String codSisElec);
    int updateByPrimaryKeySelective(AdmSistemaElectrico record);
    MessageDTO updateByPrimaryKey(AdmSistemaElectricoActualizarDTO record);
    List<AdmSistemaElectricoDTO> selectAll();
    PageInfo<AdmSistemaElectricoDTO> filtrar(AdmSistemaElectricoSearchDTO admSistemaElectricoSearchDTO );
}
