package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmProducto;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.producto.AdmProductoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.producto.AdmProductoFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.producto.AdmProductoSearchDTO;

import java.util.List;

public interface AdmProductoService{

    MessageDTO insert(AdmProductoFormdDTO admProductoFormdDTO);

    int insertSelective(AdmProducto record);

    MessageDTO updateByPrimaryKey(AdmProductoFormdDTO admProductoFormdDTO);

    MessageDTO deleteByPrimaryKey(String codProducto);

    List<AdmProductoDTO> selectAll();

    PageInfo<AdmProductoDTO> filtrar(AdmProductoSearchDTO admProductoSearchDTO);
}
