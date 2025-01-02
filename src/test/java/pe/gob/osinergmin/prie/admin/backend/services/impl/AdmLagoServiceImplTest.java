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
import pe.gob.osinergmin.prie.admin.backend.domain.AdmLago;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.lago.AdmLagoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.lago.AdmLagoFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.lago.AdmLagoSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmLagoMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdmLagoServiceImplTest {

    @Mock
    private AdmLagoMapper admLagoMapper;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private AdmLagoServiceImpl admLagoService;

    private AdmLagoFormDTO lagoFormDTO;
    private AdmLago lago;

    @BeforeEach
    void setUp() {
        lagoFormDTO = new AdmLagoFormDTO();
        lagoFormDTO.setCodLago("LAGO01");
        lagoFormDTO.setNomLago("Lago Titicaca");
        lagoFormDTO.setCodCoes("COES001");
        lagoFormDTO.setEstadoRegistro("A");

        lago = new AdmLago();
        lago.setCodLago("LAGO01");
        lago.setNomLago("Lago Titicaca");
        lago.setCodCoes("COES001");
        lago.setEstadoRegistro("A");
        lago.setFechaCreacion(new Date());
        lago.setUsuarioCreacion("admin");
    }

    @Test
    void testInsert_Success() {
        when(admLagoMapper.selectByPrimaryKey(anyString())).thenReturn(null);
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        MessageDTO result = admLagoService.insert(lagoFormDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Lago insertado correctamente", result.getMessage());
        verify(admLagoMapper, times(1)).insert(any(AdmLago.class));
    }

    @Test
    void testInsert_Failure_LagoAlreadyExists() {
        when(admLagoMapper.selectByPrimaryKey(anyString())).thenReturn(lago);

        MessageDTO result = admLagoService.insert(lagoFormDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El lago ya existe", result.getMessage());
    }


    @Test
    void testDeleteByPrimaryKey_Success() {
        when(admLagoMapper.selectByPrimaryKey(anyString())).thenReturn(lago);

        MessageDTO result = admLagoService.deleteByPrimaryKey("LAGO01");

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Lago eliminado con éxito", result.getMessage());
        verify(admLagoMapper, times(1)).deleteByPrimaryKey(anyString());
    }

    @Test
    void testDeleteByPrimaryKey_Failure_LagoNotFound() {
        when(admLagoMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admLagoService.deleteByPrimaryKey("LAGO01");

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("Lago no encontrado", result.getMessage());
    }

    @Test
    void testUpdateByPrimaryKey_Success() {
        when(admLagoMapper.selectByPrimaryKey(anyString())).thenReturn(lago);
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        MessageDTO result = admLagoService.updateByPrimaryKey(lagoFormDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Lago actualizado correctamente", result.getMessage());
        verify(admLagoMapper, times(1)).updateByPrimaryKey(any(AdmLago.class));
    }

    @Test
    void testUpdateByPrimaryKey_Failure_LagoNotFound() {
        when(admLagoMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admLagoService.updateByPrimaryKey(lagoFormDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("Lago no encontrado", result.getMessage());
    }

    @Test
    void testFiltrar_Success() {
        List<AdmLagoDTO> lagoList = new ArrayList<>();
        lagoList.add(new AdmLagoDTO());

        when(admLagoMapper.filtro(any(AdmLagoSearchDTO.class))).thenReturn(lagoList);

        AdmLagoSearchDTO searchDTO = new AdmLagoSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);

        PageInfo<AdmLagoDTO> result = admLagoService.filtro(searchDTO);

        assertNotNull(result);
        assertEquals(1, result.getList().size());
        verify(admLagoMapper, times(1)).filtro(any(AdmLagoSearchDTO.class));
    }

    @Test
    void testSelectAll_Success() {
        List<AdmLagoDTO> lagoList = new ArrayList<>();
        lagoList.add(new AdmLagoDTO());

        when(admLagoMapper.selectAll()).thenReturn(lagoList);

        List<AdmLagoDTO> result = admLagoService.selectAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(admLagoMapper, times(1)).selectAll();
    }

    @Test
    void testInsert_Failure_InvalidNomLago() {
        lagoFormDTO.setNomLago("Nombre de lago muy largo que excede los 100 caracteres permitidos para validación en el servicio AdmLagoServiceImpl para la inserción de lagos.");

        MessageDTO result = admLagoService.insert(lagoFormDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El nombre del lago no debe exceder 100 caracteres", result.getMessage());
    }
}
