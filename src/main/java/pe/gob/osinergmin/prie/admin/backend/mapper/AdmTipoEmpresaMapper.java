package pe.gob.osinergmin.prie.admin.backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTipoEmpresa;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoEmpresa.TipoEmpresaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoEmpresa.TipoEmpresaSearchDTO;

import java.util.List;

@Mapper
public interface AdmTipoEmpresaMapper {
    int deleteByPrimaryKey(String codTipoEmpresa);

    int insert(AdmTipoEmpresa record);

    int insertSelective(AdmTipoEmpresa record);

    AdmTipoEmpresa selectByPrimaryKey(String codTipoEmpresa);

    int updateByPrimaryKeySelective(AdmTipoEmpresa record);

    int updateByPrimaryKey(AdmTipoEmpresa record);

    List<TipoEmpresaDTO> selectAll();

    List<TipoEmpresaDTO> listaFiltro(TipoEmpresaSearchDTO tipoEmpresaSearchDTO);
}