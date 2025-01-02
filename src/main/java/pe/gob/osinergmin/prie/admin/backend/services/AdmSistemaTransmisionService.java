package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmSistemaTransmision;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sistemaTransmision.AdmSistemaTransmisionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sistemaTransmision.AdmSistemaTransmisionFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sistemaTransmision.AdmSistemaTransmisionSearchDTO;

import java.util.List;

public interface AdmSistemaTransmisionService{

    MessageDTO deleteByPrimaryKey(Integer idSisTrans);

    MessageDTO insert(AdmSistemaTransmisionFormdDTO record);

    int insertSelective(AdmSistemaTransmision record);

    AdmSistemaTransmision selectByPrimaryKey(Integer idSisTrans);

    int updateByPrimaryKeySelective(AdmSistemaTransmision record);

    MessageDTO updateByPrimaryKey(AdmSistemaTransmisionFormdDTO record);

    List<AdmSistemaTransmisionDTO> selectAll();

    PageInfo<AdmSistemaTransmisionDTO> filrar(AdmSistemaTransmisionSearchDTO admSistemaTransmisionSearchDTO);

}
