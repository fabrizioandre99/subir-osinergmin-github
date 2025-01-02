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
import pe.gob.osinergmin.prie.admin.backend.domain.AdmCuenca;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.cuenca.AdmCuencaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.cuenca.AdmCuencaFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.cuenca.AdmCuencaSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmCuencaMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdmCuencaServiceImplTest {

    @Mock
    private AdmCuencaMapper admCuencaMapper;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private AdmCuencaServiceImpl admCuencaService;

    private AdmCuencaFormdDTO cuencaFormDTO;
    private AdmCuenca cuenca;

    @BeforeEach
    void setUp() {
        cuencaFormDTO = new AdmCuencaFormdDTO();
        cuencaFormDTO.setCodCuenca("CUEN01");
        cuencaFormDTO.setNomCuenca("Cuenca Test");
        cuencaFormDTO.setCodCoes("COES123");
        cuencaFormDTO.setEstadoRegistro("A");

        cuenca = new AdmCuenca();
        cuenca.setCodCuenca("CUEN01");
        cuenca.setNomCuenca("Cuenca Test");
        cuenca.setCodCoes("COES123");
        cuenca.setEstadoRegistro("A");
        cuenca.setFechaCreacion(new Date());
        cuenca.setUsuarioCreacion("admin");
    }

    @Test
    void testInsert_Success() {
        when(admCuencaMapper.selectByPrimaryKey(anyString())).thenReturn(null);
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        MessageDTO result = admCuencaService.insert(cuencaFormDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Cuenca insertada correctamente", result.getMessage());
        verify(admCuencaMapper, times(1)).insert(any(AdmCuenca.class));
    }

    @Test
    void testInsert_Failure_CuencaAlreadyExists() {
        when(admCuencaMapper.selectByPrimaryKey(anyString())).thenReturn(cuenca);

        MessageDTO result = admCuencaService.insert(cuencaFormDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("La cuenca ya existe", result.getMessage());
    }


    @Test
    void testDeleteByPrimaryKey_Success() {
        when(admCuencaMapper.selectByPrimaryKey(anyString())).thenReturn(cuenca);

        MessageDTO result = admCuencaService.deleteByPrimaryKey("CUEN01");

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Cuenca eliminada con éxito", result.getMessage());
        verify(admCuencaMapper, times(1)).deleteByPrimaryKey(anyString());
    }

    @Test
    void testDeleteByPrimaryKey_Failure_CuencaNotFound() {
        when(admCuencaMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admCuencaService.deleteByPrimaryKey("CUEN01");

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("Cuenca no encontrada", result.getMessage());
    }

    @Test
    void testUpdateByPrimaryKey_Success() {
        when(admCuencaMapper.selectByPrimaryKey(anyString())).thenReturn(cuenca);
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        MessageDTO result = admCuencaService.updateByPrimaryKey(cuencaFormDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Cuenca actualizada correctamente", result.getMessage());
        verify(admCuencaMapper, times(1)).updateByPrimaryKey(any(AdmCuenca.class));
    }

    @Test
    void testUpdateByPrimaryKey_Failure_CuencaNotFound() {
        when(admCuencaMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admCuencaService.updateByPrimaryKey(cuencaFormDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("Cuenca no encontrada", result.getMessage());
    }

    @Test
    void testFiltrar_Success() {
        List<AdmCuencaDTO> cuencaList = new ArrayList<>();
        cuencaList.add(new AdmCuencaDTO());

        when(admCuencaMapper.filtro(any(AdmCuencaSearchDTO.class))).thenReturn(cuencaList);

        AdmCuencaSearchDTO searchDTO = new AdmCuencaSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);

        PageInfo<AdmCuencaDTO> result = admCuencaService.filtro(searchDTO);

        assertNotNull(result);
        assertEquals(1, result.getList().size());
        verify(admCuencaMapper, times(1)).filtro(any(AdmCuencaSearchDTO.class));
    }

    @Test
    void testSelectAll_Success() {
        List<AdmCuencaDTO> cuencaList = new ArrayList<>();
        cuencaList.add(new AdmCuencaDTO());

        when(admCuencaMapper.selectAll()).thenReturn(cuencaList);

        List<AdmCuencaDTO> result = admCuencaService.selectAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(admCuencaMapper, times(1)).selectAll();
    }

    @Test
    void testInsert_Failure_InvalidNomCuenca() {
        cuencaFormDTO.setNomCuenca("Nombre muy largo que excede los 100 caracteres permitido por la validación interna del servicio AdmCuencaServiceImpl para la inserción de cuencas");

        MessageDTO result = admCuencaService.insert(cuencaFormDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El nombre de la cuenca no debe exceder 100 caracteres", result.getMessage());
    }
}
