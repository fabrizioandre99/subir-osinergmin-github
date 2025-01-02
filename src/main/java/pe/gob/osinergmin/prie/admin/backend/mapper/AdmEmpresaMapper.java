package pe.gob.osinergmin.prie.admin.backend.mapper;

import org.apache.ibatis.annotations.Param;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmEmpresa;
import pe.gob.osinergmin.prie.admin.backend.dto.empresa.AdmEmpresaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.empresa.AdmEmpresaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.empresa.AdmEmpresaSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.empresa.AdmProcEmpresaDTO;

import java.util.List;

public interface AdmEmpresaMapper {
    int deleteByPrimaryKey(String codEmpresa);

    int insert(AdmEmpresa record);

    int insertSelective(AdmEmpresa record);

    AdmEmpresa selectByPrimaryKey(String codEmpresa);

    int updateByPrimaryKeySelective(AdmEmpresa record);

    int updateByPrimaryKey(AdmEmpresa record);

    List<AdmEmpresaDTO> selectAll();

    List<AdmEmpresaDTO> ListarFiltro(@Param("searchDTO") AdmEmpresaSearchDTO searchDTO);

    int existeRuc(String ruc);

    void insertEmpresa(AdmEmpresaFormDTO empresaFormDTO);

    void insertProcEmpresa(AdmProcEmpresaDTO proceso);

    int getNextIdProcEmpresa();

    AdmEmpresaDTO selectByCodEmpresa(@Param("codEmpresa") String codEmpresa);

    Integer obtenerMaximoIdProcEmpresa();

    AdmProcEmpresaDTO selectProcByCodEmpresaAndId(@Param("codEmpresa") String codEmpresa, @Param("idProcEmpresa") int idProcEmpresa);

    void updateProcEmpresa(AdmProcEmpresaDTO proceso);

    void updateByPrimaryKeySelectiveDos(AdmEmpresaFormDTO empresaFormDTO);

    AdmProcEmpresaDTO selectProcByCodEmpresaAndTipo(@Param("codEmpresa") String codEmpresa, @Param("codTipoEmpresa") String codTipoEmpresa);
}
