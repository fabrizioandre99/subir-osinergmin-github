package pe.gob.osinergmin.prie.admin.backend.mapper;

import org.apache.ibatis.annotations.Param;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmSistemaTransmision;
import pe.gob.osinergmin.prie.admin.backend.dto.sistemaTransmision.AdmSistemaTransmisionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sistemaTransmision.AdmSistemaTransmisionSearchDTO;

import java.util.List;

public interface AdmSistemaTransmisionMapper {
    int deleteByPrimaryKey(Integer idSisTrans);

    int insert(AdmSistemaTransmision record);

    int insertSelective(AdmSistemaTransmision record);

    AdmSistemaTransmision selectByPrimaryKey(Integer idSisTrans);

    int updateByPrimaryKeySelective(AdmSistemaTransmision record);

    int updateByPrimaryKey(AdmSistemaTransmision record);

    List<AdmSistemaTransmisionDTO> selectAll();

    List<AdmSistemaTransmisionDTO> filrar(AdmSistemaTransmisionSearchDTO admSistemaTransmisionSearchDTO);

    Integer selectMaxId();

    AdmSistemaTransmision validarExisteCodigo(String codSisTrans);
}