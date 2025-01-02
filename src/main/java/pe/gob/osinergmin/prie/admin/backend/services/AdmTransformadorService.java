package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTransformador;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.transformador.AdmTransformadorDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.transformador.AdmTransformadorFormActualizarDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.transformador.AdmTransformadorFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.transformador.AdmTransformadorSearchDTO;

import java.util.List;

public interface AdmTransformadorService{

    MessageDTO deleteByPrimaryKey(Integer idTransformador);

    MessageDTO insert(AdmTransformadorFormDTO record);

    int insertSelective(AdmTransformador record);

    AdmTransformador selectByPrimaryKey(Integer idTransformador);

    int updateByPrimaryKeySelective(AdmTransformador record);

    MessageDTO updateByPrimaryKey(AdmTransformadorFormActualizarDTO record);

    List<AdmTransformadorDTO> selectAll();

    PageInfo<AdmTransformadorDTO> filtrar(AdmTransformadorSearchDTO admTransformadorSearchDTO);
}
