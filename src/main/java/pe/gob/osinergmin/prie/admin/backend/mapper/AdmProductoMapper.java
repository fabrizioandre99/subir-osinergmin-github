package pe.gob.osinergmin.prie.admin.backend.mapper;

import pe.gob.osinergmin.prie.admin.backend.domain.AdmProducto;
import pe.gob.osinergmin.prie.admin.backend.dto.producto.AdmProductoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.producto.AdmProductoSearchDTO;

import java.util.List;

public interface AdmProductoMapper {
    int insert(AdmProducto record);
    int insertSelective(AdmProducto record);
    AdmProducto selectByPrimaryKey(String codProducto);
    int updateByPrimaryKey(AdmProducto record);
    int deleteByPrimaryKey(String codProducto);
    List<AdmProductoDTO> selectAll();
    List<AdmProductoDTO> filtrar(AdmProductoSearchDTO admProductoSearchDTO);
}