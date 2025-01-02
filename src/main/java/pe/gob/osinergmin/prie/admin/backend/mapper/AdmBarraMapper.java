package pe.gob.osinergmin.prie.admin.backend.mapper;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmBarra;
import pe.gob.osinergmin.prie.admin.backend.dto.barra.AdmBarraDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.barra.AdmBarraListadoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.barra.AdmBarraResultDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.barra.AdmBarraSearchDTO;

import java.util.List;

public interface AdmBarraMapper {
    int deleteByPrimaryKey(String codBarra);

    int insert(AdmBarra record);

    int insertSelective(AdmBarra record);

    AdmBarra selectByPrimaryKey(String codBarra);

    int updateByPrimaryKeySelective(AdmBarra record);

    int updateByPrimaryKey(AdmBarra record);

    List<AdmBarraDTO> selectAll();

    List<AdmBarraDTO> filtrar(AdmBarraSearchDTO admBarraSearchDTO);

    List<AdmBarraResultDTO> listarBRG();

    int deleteByCodSubestacion (String codSubestacion);

    List<AdmBarraDTO> listarPorCodSubestacion(String subestacion);

    List<AdmBarraListadoDTO> selectByCodSubestacion(String codSubestacion);

}