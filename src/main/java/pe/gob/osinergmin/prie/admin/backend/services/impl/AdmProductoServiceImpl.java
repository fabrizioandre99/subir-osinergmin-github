package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.producto.AdmProductoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.producto.AdmProductoFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.producto.AdmProductoSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmProductoMapper;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmProducto;
import pe.gob.osinergmin.prie.admin.backend.services.AdmProductoService;

import java.util.Date;
import java.util.List;

@Service
public class AdmProductoServiceImpl implements AdmProductoService {

    @Autowired
    private AdmProductoMapper admProductoMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public MessageDTO insert(AdmProductoFormdDTO admProductoFormdDTO) {
        try {
            if (admProductoFormdDTO.getCodProducto() == null || admProductoFormdDTO.getCodProducto().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código del producto no puede ser nulo, vacío o solo contener espacios");
            }
            if (admProductoFormdDTO.getCodProducto().length() > 15) {
                return new MessageDTO(Constantes.ERROR, "El código del producto no puede tener más de 15 caracteres");
            }
            if (admProductoFormdDTO.getDscProducto() != null && admProductoFormdDTO.getDscProducto().length() > 200) {
                return new MessageDTO(Constantes.ERROR, "La descripción del producto no puede tener más de 200 caracteres");
            }

            if (admProductoFormdDTO.getEstado() == null || admProductoFormdDTO.getEstado().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El estado no puede ser nulo, vacío o solo contener espacios");
            }
            if (admProductoFormdDTO.getEstado().length() > 1) {
                return new MessageDTO(Constantes.ERROR, "El estado solo puede tener 1 caracter");
            }

            AdmProducto existe = admProductoMapper.selectByPrimaryKey(admProductoFormdDTO.getCodProducto());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "Ya existe un producto con el mismo código");
            }

            AdmProducto admProducto = new AdmProducto();
            admProducto.setCodProducto(admProductoFormdDTO.getCodProducto());
            admProducto.setDscProducto(admProductoFormdDTO.getDscProducto());
            admProducto.setEstado(admProductoFormdDTO.getEstado());

            admProducto.setFechaCreacion(new Date());
            admProducto.setUsuarioCreacion("admin");
            admProducto.setTerminalCreacion(IpUtils.getClientIp(request));
            admProductoMapper.insert(admProducto);

            return new MessageDTO(Constantes.SUCCES, "Se insertó correctamente el producto");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public int insertSelective(AdmProducto record) {
        return 0;
    }

    @Override
    public MessageDTO updateByPrimaryKey(AdmProductoFormdDTO admProductoFormdDTO) {
        try {
            if (admProductoFormdDTO.getCodProducto() == null || admProductoFormdDTO.getCodProducto().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código del producto no puede ser nulo o vacío");
            }
            if (admProductoFormdDTO.getCodProducto().length() > 15) {
                return new MessageDTO(Constantes.ERROR, "El código del producto no puede tener más de 15 caracteres");
            }

            if (admProductoFormdDTO.getDscProducto() != null && admProductoFormdDTO.getDscProducto().length() > 200) {
                return new MessageDTO(Constantes.ERROR, "La descripción del producto no puede tener más de 200 caracteres");
            }

            if (admProductoFormdDTO.getEstado() == null || admProductoFormdDTO.getEstado().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El estado no puede ser nulo o vacío");
            }
            if (admProductoFormdDTO.getEstado().length() > 1) {
                return new MessageDTO(Constantes.ERROR, "El estado solo puede tener 1 caracter");
            }

            AdmProducto existe = admProductoMapper.selectByPrimaryKey(admProductoFormdDTO.getCodProducto());
            if (existe == null) {
                return new MessageDTO(Constantes.ERROR, "No se encontró el producto para actualizar");
            }

            AdmProducto admProducto = new AdmProducto();
            admProducto.setCodProducto(admProductoFormdDTO.getCodProducto());
            admProducto.setDscProducto(admProductoFormdDTO.getDscProducto());
            admProducto.setEstado(admProductoFormdDTO.getEstado());

            admProducto.setFechaActualizacion(new Date());
            admProducto.setUsuarioActualizacion("admin");
            admProducto.setTerminalActualizacion(IpUtils.getClientIp(request));
            admProductoMapper.updateByPrimaryKey(admProducto);

            return new MessageDTO(Constantes.SUCCES, "Se actualizó correctamente el producto");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public MessageDTO deleteByPrimaryKey(String codProducto) {
        try {
            if (codProducto == null || codProducto.isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código del producto no puede ser nulo o vacío");
            }
            if (codProducto.length() > 15) {
                return new MessageDTO(Constantes.ERROR, "El código del producto no puede tener más de 15 caracteres");
            }
            AdmProducto existe = admProductoMapper.selectByPrimaryKey(codProducto);
            if (existe == null) {
                return new MessageDTO(Constantes.ERROR, "No se encontró el producto para eliminar");
            }
            admProductoMapper.deleteByPrimaryKey(codProducto);

            return new MessageDTO(Constantes.SUCCES, "Se eliminó el producto");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public List<AdmProductoDTO> selectAll() {
        return admProductoMapper.selectAll();
    }

    @Override
    public PageInfo<AdmProductoDTO> filtrar(AdmProductoSearchDTO admProductoSearchDTO) {
        String sort = admProductoSearchDTO.getSort() == null || admProductoSearchDTO.getSort().isEmpty() ? "COD_PRODUCTO" : admProductoSearchDTO.getSort();
        String order = admProductoSearchDTO.getOrder() == null || admProductoSearchDTO.getOrder().isEmpty() ? "ASC" : admProductoSearchDTO.getOrder();

        String sortField = mapSortField(sort);
        String validatedOrder = validateOrder(order);

        String orderBy = sortField + " " + validatedOrder;
        PageHelper.startPage(admProductoSearchDTO.getPage(), admProductoSearchDTO.getSize(), orderBy);

        return new PageInfo<>(admProductoMapper.filtrar(admProductoSearchDTO));
    }

    private String mapSortField(String sort) {
        if (sort == null || sort.isEmpty()) {
            return "COD_PRODUCTO";
        }
        switch (sort) {
            case "codProducto":
                return "COD_PRODUCTO";
            case "dscProducto":
                return "DSC_PRODUCTO";
            case "estado":
                return "ESTADO";
            default:
                return "COD_PRODUCTO";
        }
    }

    private String validateOrder(String order) {
        if ("asc".equalsIgnoreCase(order) || "desc".equalsIgnoreCase(order)) {
            return order.toUpperCase();
        }
        return "ASC";
    }


}
