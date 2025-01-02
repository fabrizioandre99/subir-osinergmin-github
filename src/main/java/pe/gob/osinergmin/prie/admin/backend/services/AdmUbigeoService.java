package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmUbigeo;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ubigeo.AdmUbigeoFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ubigeo.UbigeoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ubigeo.UbigeoProvDeptDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ubigeo.UbigeoSearchDTO;

import java.util.List;

public interface AdmUbigeoService{

    MessageDTO deleteByPrimaryKey(String codUbigeo);

    MessageDTO insert(AdmUbigeoFormDTO record);

    int insertSelective(AdmUbigeo record);

    AdmUbigeo selectByPrimaryKey(String codUbigeo);

    int updateByPrimaryKeySelective(AdmUbigeo record);

    MessageDTO updateByPrimaryKey(AdmUbigeoFormDTO record);

    List<UbigeoDTO> listarDepartamento();

    List<UbigeoDTO> listarProvincia(String codUbigeo);

    List<UbigeoDTO> listarDistrito(String codProvincia);

    List<UbigeoDTO> listaAll();

    PageInfo<UbigeoDTO> listaFiltro(UbigeoSearchDTO ubigeoSearchDTO);

    UbigeoProvDeptDTO obtenerProvDeptPorDistrito(String codUbigeo);
}
