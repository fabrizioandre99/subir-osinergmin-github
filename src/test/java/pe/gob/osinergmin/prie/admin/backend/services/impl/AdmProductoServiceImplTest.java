package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmProducto;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.producto.AdmProductoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.producto.AdmProductoFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.producto.AdmProductoSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmProductoMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdmProductoServiceImplTest {

    @Mock
    private AdmProductoMapper admProductoMapper;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private AdmProductoServiceImpl admProductoService;

    private AdmProductoFormdDTO productoFormDTO;
    private AdmProducto producto;

    @BeforeEach
    void setUp() {
        productoFormDTO = new AdmProductoFormdDTO();
        productoFormDTO.setCodProducto("PROD001");
        productoFormDTO.setDscProducto("Producto de prueba");
        productoFormDTO.setEstado("A");

        producto = new AdmProducto();
        producto.setCodProducto("PROD001");
        producto.setDscProducto("Producto de prueba");
        producto.setEstado("A");
    }

    @Test
    void testInsert_Success() {
        when(admProductoMapper.selectByPrimaryKey(anyString())).thenReturn(null);
        when(admProductoMapper.insert(any(AdmProducto.class))).thenReturn(1);
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        MessageDTO result = admProductoService.insert(productoFormDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Se insertó correctamente el producto", result.getMessage());
        verify(admProductoMapper, times(1)).insert(any(AdmProducto.class));
    }

    @Test
    void testInsert_Failure_ProductoExists() {
        when(admProductoMapper.selectByPrimaryKey(anyString())).thenReturn(producto);

        MessageDTO result = admProductoService.insert(productoFormDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("Ya existe un producto con el mismo código", result.getMessage());
    }

    @Test
    void testUpdateByPrimaryKey_Success() {
        when(admProductoMapper.selectByPrimaryKey(anyString())).thenReturn(producto);
        when(admProductoMapper.updateByPrimaryKey(any(AdmProducto.class))).thenReturn(1);
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        MessageDTO result = admProductoService.updateByPrimaryKey(productoFormDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Se actualizó correctamente el producto", result.getMessage());
        verify(admProductoMapper, times(1)).updateByPrimaryKey(any(AdmProducto.class));
    }

    @Test
    void testUpdateByPrimaryKey_Failure_ProductoNotFound() {
        when(admProductoMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admProductoService.updateByPrimaryKey(productoFormDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("No se encontró el producto para actualizar", result.getMessage());
    }

    @Test
    void testDeleteByPrimaryKey_Success() {
        when(admProductoMapper.selectByPrimaryKey(anyString())).thenReturn(producto);
        when(admProductoMapper.deleteByPrimaryKey(anyString())).thenReturn(1);

        MessageDTO result = admProductoService.deleteByPrimaryKey("PROD001");

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Se eliminó el producto", result.getMessage());
        verify(admProductoMapper, times(1)).deleteByPrimaryKey(anyString());
    }

    @Test
    void testDeleteByPrimaryKey_Failure_ProductoNotFound() {
        when(admProductoMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admProductoService.deleteByPrimaryKey("PROD001");

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("No se encontró el producto para eliminar", result.getMessage());
    }

    @Test
    void testFiltrar_Success() {
        List<AdmProductoDTO> productos = new ArrayList<>();
        productos.add(new AdmProductoDTO());

        when(admProductoMapper.filtrar(any(AdmProductoSearchDTO.class))).thenReturn(productos);

        AdmProductoSearchDTO searchDTO = new AdmProductoSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);

        PageInfo<AdmProductoDTO> result = admProductoService.filtrar(searchDTO);

        assertNotNull(result);
        assertEquals(1, result.getList().size());
    }
}
