package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.CfgTablaAdm;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tabla.CfgTablaAdmDTO;

import java.util.List;
import java.util.Map;

public interface CfgTablaAdmService{

    int deleteByPrimaryKey(String codTabla);

    int insert(CfgTablaAdm record);

    int insertSelective(CfgTablaAdm record);

    CfgTablaAdm selectByPrimaryKey(String codTabla);

    int updateByPrimaryKeySelective(CfgTablaAdm record);

    int updateByPrimaryKey(CfgTablaAdm record);

    Map<String, Object> listarDatos(String codTabla);

    void insertarDatosDinamicos(String codTabla, Map<String, Object> parametros);

    void actualizarDatosDinamicos(String codTabla, Map<String, Object> parametros, Map<String, Object> primaryKeyValues);

    void eliminarDatosDinamicos(String codTabla, Map<String, Object> primaryKeyValues);

    List<CfgTablaAdmDTO> listarPorTabla();

    PageInfo<Map<String, Object>> listarConFiltro(String codTabla, Map<String, Object> filtros, PaginacionDTO paginacion);

    List<Map<String, Object>> obtenerCamposPorTabla(String codTabla);

}
