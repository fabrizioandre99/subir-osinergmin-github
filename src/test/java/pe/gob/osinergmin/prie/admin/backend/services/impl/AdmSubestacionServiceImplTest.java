package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmBarra;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmSubestacion;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.barra.AdmBarraFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.subestacion.AdmSubestacionActualizarFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.subestacion.AdmSubestacionFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.subestacion.AdmSubestacionListadoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.subestacion.AdmSubestacionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmBarraMapper;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmSubestacionMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AdmSubestacionServiceImplTest {

    @InjectMocks
    private AdmSubestacionServiceImpl admSubestacionService;

    @Mock
    private AdmSubestacionMapper admSubestacionMapper;

    @Mock
    private AdmBarraMapper admBarraMapper;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteByPrimaryKey_Exitoso() {
        when(admSubestacionMapper.selectByPrimaryKey(anyString())).thenReturn(new AdmSubestacion());

        MessageDTO response = admSubestacionService.deleteByPrimaryKey("SUB001");

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("Subestación eliminada correctamente.", response.getMessage());
        verify(admSubestacionMapper, times(1)).deleteByPrimaryKey("SUB001");
    }

    @Test
    void testDeleteByPrimaryKey_NoExiste() {
        when(admSubestacionMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO response = admSubestacionService.deleteByPrimaryKey("SUB001");

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("La subestación no existe, no se puede eliminar.", response.getMessage());
    }

    @Test
    void testInsert_Exitoso() {
        AdmSubestacionFormDTO formDTO = new AdmSubestacionFormDTO();
        formDTO.setCodSubestacion("SUB001");
        formDTO.setNomSubestacion("Subestación 1");
        formDTO.setEstado("1");
        formDTO.setCodUbigeo("12345");

        when(request.getRemoteAddr()).thenReturn("127.0.0.1");
        when(admSubestacionMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO response = admSubestacionService.insert(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("Subestación creada exitosamente.", response.getMessage());
        verify(admSubestacionMapper, times(1)).insert(any(AdmSubestacion.class));
    }


    @Test
    void testUpdateByPrimaryKey_Exitoso() {
        AdmSubestacionActualizarFormDTO formDTO = new AdmSubestacionActualizarFormDTO();
        formDTO.setCodSubestacion("SUB001");
        formDTO.setNomSubestacion("Subestación Actualizada");
        formDTO.setEstado("1");

        when(admSubestacionMapper.selectByPrimaryKey(anyString())).thenReturn(new AdmSubestacion());
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        MessageDTO response = admSubestacionService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("Subestación y barras actualizadas exitosamente.", response.getMessage());
        verify(admSubestacionMapper, times(1)).updateByPrimaryKey(any(AdmSubestacion.class));
    }

    @Test
    void testUpdateByPrimaryKey_NoExiste() {
        AdmSubestacionActualizarFormDTO formDTO = new AdmSubestacionActualizarFormDTO();
        formDTO.setCodSubestacion("SUB001");

        when(admSubestacionMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO response = admSubestacionService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("La subestación no existe.", response.getMessage());
    }

    @Test
    void testListarSubestaciones() {
        List<AdmSubestacionListadoDTO> subestaciones = new ArrayList<>();
        subestaciones.add(new AdmSubestacionListadoDTO());

        when(admSubestacionMapper.listarSubestaciones()).thenReturn(subestaciones);

        List<AdmSubestacionListadoDTO> response = admSubestacionService.listarSubestaciones();

        assertNotNull(response);
        assertEquals(1, response.size());
        verify(admSubestacionMapper, times(1)).listarSubestaciones();
    }

    @Test
    void testListarFiltro() {
        AdmSubestacionSearchDTO searchDTO = new AdmSubestacionSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);

        List<AdmSubestacionListadoDTO> subestaciones = new ArrayList<>();
        PageInfo<AdmSubestacionListadoDTO> pageInfo = new PageInfo<>(subestaciones);

        when(admSubestacionMapper.listarFiltro(any(AdmSubestacionSearchDTO.class))).thenReturn(subestaciones);

        PageInfo<AdmSubestacionListadoDTO> response = admSubestacionService.listarFiltro(searchDTO);

        assertNotNull(response);
        assertEquals(0, response.getList().size());
        verify(admSubestacionMapper, times(1)).listarFiltro(any(AdmSubestacionSearchDTO.class));
    }
}
