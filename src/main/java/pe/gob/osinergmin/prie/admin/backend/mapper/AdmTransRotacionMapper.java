package pe.gob.osinergmin.prie.admin.backend.mapper;

import org.apache.ibatis.annotations.Param;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTransRotacion;
import pe.gob.osinergmin.prie.admin.backend.dto.transRotacion.TransRotacionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.transRotacion.TransRotacionInsertDTO;

import java.util.List;

public interface AdmTransRotacionMapper {
    int deleteByPrimaryKey(@Param("idTransfRotacion") Integer idTransfRotacion, @Param("usuarioCreacion") String usuarioCreacion);

    int insert(AdmTransRotacion record);

    int insertSelective(AdmTransRotacion record);

    AdmTransRotacion selectByPrimaryKey(@Param("idTransfRotacion") Integer idTransfRotacion, @Param("usuarioCreacion") String usuarioCreacion);

    int updateByPrimaryKeySelective(AdmTransRotacion record);

    int updateByPrimaryKey(AdmTransRotacion record);

    int deleteByTransformadorId(Integer idTransformador);

    int insertarNuevo(TransRotacionInsertDTO transRotacionInsertDTO);

    int actualizarNuevo(TransRotacionInsertDTO transRotacionInsertDTO);

    List<TransRotacionDTO> selectByTransformadorId(Integer idTransformador);

    Integer getNextId();
}