package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmPliego;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.pliego.AdmPliegoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.pliego.AdmPliegoSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.pliego.PliegoActualizarDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.pliego.PliegoCrearDTO;

import java.util.List;

public interface AdmPliegoService{

    MessageDTO deleteByPrimaryKey(String codPliego);
    MessageDTO insert(PliegoCrearDTO pliegoCrearDTO);
    int insertSelective(AdmPliego record);
    AdmPliego selectByPrimaryKey(String codPliego);
    int updateByPrimaryKeySelective(AdmPliego record);
    MessageDTO updateByPrimaryKey(PliegoActualizarDTO pliegoActualizarDTO);
    List<AdmPliegoDTO> selectAllAdmPliegos();
    PageInfo<AdmPliegoDTO> filtro(AdmPliegoSearchDTO admPliegoSearchDTO);
}
