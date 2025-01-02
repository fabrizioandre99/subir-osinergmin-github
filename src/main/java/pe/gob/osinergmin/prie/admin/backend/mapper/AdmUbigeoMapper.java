package pe.gob.osinergmin.prie.admin.backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmUbigeo;
import pe.gob.osinergmin.prie.admin.backend.dto.ubigeo.UbigeoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ubigeo.UbigeoProvDeptDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ubigeo.UbigeoSearchDTO;

import java.util.List;

@Mapper
public interface AdmUbigeoMapper {
    int deleteByPrimaryKey(String codUbigeo);

    int insert(AdmUbigeo record);

    int insertSelective(AdmUbigeo record);

    AdmUbigeo selectByPrimaryKey(String codUbigeo);

    int updateByPrimaryKeySelective(AdmUbigeo record);

    int updateByPrimaryKey(AdmUbigeo record);

    List<UbigeoDTO> listarDepartamento();

    List<UbigeoDTO> listarProvincia(@Param("codUbigeo") String codUbigeo);

    List<UbigeoDTO> listarDistrito(@Param("codUbigeo") String codUbigeo);

    List<UbigeoDTO> listaAll();

    List<UbigeoDTO> listaFiltro(UbigeoSearchDTO ubigeoSearchDTO);

    List<UbigeoProvDeptDTO> obtenerProvDeptPorDistrito(@Param("codUbigeo") String codUbigeo);
}