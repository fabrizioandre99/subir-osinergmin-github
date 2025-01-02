package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmBarra;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.barra.*;

import java.util.List;

public interface AdmBarraService{

    MessageDTO deleteByPrimaryKey(String codBarra);

    MessageDTO insert(AdmBarraFormDTO record);

    int insertSelective(AdmBarra record);

    AdmBarra selectByPrimaryKey(String codBarra);

    int updateByPrimaryKeySelective(AdmBarra record);

    MessageDTO updateByPrimaryKey(AdmBarraActualizarDTO record);

    List<AdmBarraDTO> selectAll();

    PageInfo<AdmBarraDTO> filtrar(AdmBarraSearchDTO admBarraSearchDTO);

    List<AdmBarraResultDTO> listarBRG();

    List<AdmBarraDTO> listarPorCodSubestacion(String subestacion);
}
