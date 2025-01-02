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
import pe.gob.osinergmin.prie.admin.backend.domain.AdmModoOperacion;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.modoOperacion.AdmModoOperacionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.modoOperacion.AdmModoOperacionFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.modoOperacion.AdmModoOperacionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmModoOperacionMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdmModoOperacionServiceImplTest {

    @Mock
    private AdmModoOperacionMapper admModoOperacionMapper;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private AdmModoOperacionServiceImpl admModoOperacionService;

    private AdmModoOperacionFormdDTO modoOperacionFormDTO;
    private AdmModoOperacion modoOperacion;

    @BeforeEach
    void setUp() {
        modoOperacionFormDTO = new AdmModoOperacionFormdDTO();
        modoOperacionFormDTO.setCodModoOperacion("MO1234");
        modoOperacionFormDTO.setDscModoOperacion("Modo Operación Prueba");
        modoOperacionFormDTO.setCodCoes("COES01");
        modoOperacionFormDTO.setEstadoRegistro("1");
        modoOperacionFormDTO.setCodModoOperacionAnt("MO1233");

        modoOperacion = new AdmModoOperacion();
        modoOperacion.setCodModoOperacion("MO1234");
        modoOperacion.setDscModoOperacion("Modo Operación Prueba");
        modoOperacion.setCodCoes("COES01");
        modoOperacion.setEstadoRegistro("1");
        modoOperacion.setCodModoOperacionAnt("MO1233");
    }

    @Test
    void testInsert_Success() {
        when(admModoOperacionMapper.selectByPrimaryKey(anyString())).thenReturn(null);
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        MessageDTO result = admModoOperacionService.insert(modoOperacionFormDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Modo de operación insertado correctamente", result.getMessage());
        verify(admModoOperacionMapper, times(1)).insert(any(AdmModoOperacion.class));
    }

    @Test
    void testInsert_Failure_ModoOperacionExists() {
        when(admModoOperacionMapper.selectByPrimaryKey(anyString())).thenReturn(modoOperacion);

        MessageDTO result = admModoOperacionService.insert(modoOperacionFormDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("Modo de operación ya existe", result.getMessage());
    }

    @Test
    void testUpdateByPrimaryKey_Success() {
        when(admModoOperacionMapper.selectByPrimaryKey(anyString())).thenReturn(modoOperacion);
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        MessageDTO result = admModoOperacionService.updateByPrimaryKey(modoOperacionFormDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Modo de operación actualizado correctamente", result.getMessage());
        verify(admModoOperacionMapper, times(1)).updateByPrimaryKey(any(AdmModoOperacion.class));
    }

    @Test
    void testUpdateByPrimaryKey_Failure_ModoOperacionNotFound() {
        when(admModoOperacionMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admModoOperacionService.updateByPrimaryKey(modoOperacionFormDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("Modo de operación no encontrado", result.getMessage());
    }

    @Test
    void testDeleteByPrimaryKey_Success() {
        when(admModoOperacionMapper.selectByPrimaryKey(anyString())).thenReturn(modoOperacion);

        MessageDTO result = admModoOperacionService.deleteByPrimaryKey("MO1234");

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Modo de operación eliminado con éxito", result.getMessage());
        verify(admModoOperacionMapper, times(1)).deleteByPrimaryKey(anyString());
    }

    @Test
    void testDeleteByPrimaryKey_Failure_ModoOperacionNotFound() {
        when(admModoOperacionMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admModoOperacionService.deleteByPrimaryKey("MO1234");

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("Modo de operación no encontrado", result.getMessage());
    }

    @Test
    void testFiltrar_Success() {
        List<AdmModoOperacionDTO> modoOperacionList = new ArrayList<>();
        modoOperacionList.add(new AdmModoOperacionDTO());

        when(admModoOperacionMapper.filtro(any(AdmModoOperacionSearchDTO.class))).thenReturn(modoOperacionList);

        AdmModoOperacionSearchDTO searchDTO = new AdmModoOperacionSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);

        PageInfo<AdmModoOperacionDTO> result = admModoOperacionService.filtro(searchDTO);

        assertNotNull(result);
        assertEquals(1, result.getList().size());
        verify(admModoOperacionMapper, times(1)).filtro(any(AdmModoOperacionSearchDTO.class));
    }

    @Test
    void testSelectAll_Success() {
        List<AdmModoOperacionDTO> modoOperacionList = new ArrayList<>();
        modoOperacionList.add(new AdmModoOperacionDTO());

        when(admModoOperacionMapper.selectAll()).thenReturn(modoOperacionList);

        List<AdmModoOperacionDTO> result = admModoOperacionService.selectAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(admModoOperacionMapper, times(1)).selectAll();
    }
}
