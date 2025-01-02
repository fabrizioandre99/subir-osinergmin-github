package pe.gob.osinergmin.prie.admin.backend.mapper;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmTipoSistTrans;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoSistTrans.AdmTipoSistTransDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoSistTrans.AdmTipoSistTransSearchDTO;

import java.util.List;

public interface AdmTipoSistTransMapper {
    int deleteByPrimaryKey(Integer idTipoSisTrans);

    int insert(AdmTipoSistTrans record);

    int insertSelective(AdmTipoSistTrans record);

    AdmTipoSistTrans selectByPrimaryKey(Integer idTipoSisTrans);

    int updateByPrimaryKeySelective(AdmTipoSistTrans record);

    int updateByPrimaryKey(AdmTipoSistTrans record);

    List<AdmTipoSistTransDTO> selectAll();

    List<AdmTipoSistTransDTO> filtrar(AdmTipoSistTransSearchDTO admTipoSistTransSearchDTO);

    Integer selectMaxId();

    AdmTipoSistTrans validarExisteCodigo (String codTipoSisTrans);
}