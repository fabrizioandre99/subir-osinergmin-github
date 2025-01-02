package pe.gob.osinergmin.prie.admin.backend.mapper;

import org.apache.ibatis.annotations.Param;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTransformador;
import pe.gob.osinergmin.prie.admin.backend.dto.transformador.AdmTransformadorDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.transformador.AdmTransformadorInsertarDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.transformador.AdmTransformadorSearchDTO;

import java.util.List;

public interface AdmTransformadorMapper {
    int deleteByPrimaryKey(Integer idTransformador);

    int insert(AdmTransformador record);

    int insertSelective(AdmTransformador record);

    AdmTransformador selectByPrimaryKey(Integer idTransformador);

    int updateByPrimaryKeySelective(AdmTransformador record);

    int updateByPrimaryKey(AdmTransformador record);

    List<AdmTransformadorDTO> selectAll();

    List<AdmTransformadorDTO> filtrar(AdmTransformadorSearchDTO admTransformadorSearchDTO);

    int insertarFechasString(AdmTransformadorInsertarDTO record);

    int actualizarFechasString(AdmTransformadorInsertarDTO record);

    int deleteByCodEmpresa(@Param("codEmpresa") String codEmpresa);

    int countByCodEmpresa(@Param("codEmpresa") String codEmpresa);

    int deleteByIdTipoSisTrans(@Param("idTipoSisTrans") Integer idTipoSisTrans);

    Integer getNextId();

}