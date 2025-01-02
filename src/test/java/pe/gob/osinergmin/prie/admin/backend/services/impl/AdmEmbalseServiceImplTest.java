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
import pe.gob.osinergmin.prie.admin.backend.domain.AdmEmbalse;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.embalse.AdmEmbalseDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.embalse.AdmEmbalseFomrDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.embalse.AdmEmbalseSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmEmbalseMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdmEmbalseServiceImplTest {

    @Mock
    private AdmEmbalseMapper admEmbalseMapper;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private AdmEmbalseServiceImpl admEmbalseService;

    private AdmEmbalseFomrDTO embalseFormDTO;
    private AdmEmbalse embalse;

    @BeforeEach
    void setUp() {
        embalseFormDTO = new AdmEmbalseFomrDTO();
        embalseFormDTO.setCodEmbalse("EMB001");
        embalseFormDTO.setNomEmbalse("Embalse Titicaca");
        embalseFormDTO.setCodCoes("COES001");
        embalseFormDTO.setEstadoRegistro("A");

        embalse = new AdmEmbalse();
        embalse.setCodEmbalse("EMB001");
        embalse.setNomEmbalse("Embalse Titicaca");
        embalse.setCodCoes("COES001");
        embalse.setEstadoRegistro("A");
        embalse.setFechaCreacion(new Date());
        embalse.setUsuarioCreacion("admin");
    }

    @Test
    void testInsert_Success() {
        when(admEmbalseMapper.selectByPrimaryKey(anyString())).thenReturn(null);
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        MessageDTO result = admEmbalseService.insert(embalseFormDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Embalse insertado correctamente", result.getMessage());
        verify(admEmbalseMapper, times(1)).insert(any(AdmEmbalse.class));
    }

    @Test
    void testInsert_Failure_EmbalseAlreadyExists() {
        when(admEmbalseMapper.selectByPrimaryKey(anyString())).thenReturn(embalse);

        MessageDTO result = admEmbalseService.insert(embalseFormDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El embalse ya existe", result.getMessage());
    }



    @Test
    void testDeleteByPrimaryKey_Success() {
        when(admEmbalseMapper.selectByPrimaryKey(anyString())).thenReturn(embalse);

        MessageDTO result = admEmbalseService.deleteByPrimaryKey("EMB001");

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Embalse eliminado con éxito", result.getMessage());
        verify(admEmbalseMapper, times(1)).deleteByPrimaryKey(anyString());
    }

    @Test
    void testDeleteByPrimaryKey_Failure_EmbalseNotFound() {
        when(admEmbalseMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admEmbalseService.deleteByPrimaryKey("EMB001");

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("Embalse no encontrado", result.getMessage());
    }

    @Test
    void testUpdateByPrimaryKey_Success() {
        when(admEmbalseMapper.selectByPrimaryKey(anyString())).thenReturn(embalse);
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        MessageDTO result = admEmbalseService.updateByPrimaryKey(embalseFormDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Embalse actualizado correctamente", result.getMessage());
        verify(admEmbalseMapper, times(1)).updateByPrimaryKey(any(AdmEmbalse.class));
    }

    @Test
    void testUpdateByPrimaryKey_Failure_EmbalseNotFound() {
        when(admEmbalseMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admEmbalseService.updateByPrimaryKey(embalseFormDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("Embalse no encontrado", result.getMessage());
    }

    @Test
    void testFiltrar_Success() {
        List<AdmEmbalseDTO> embalseList = new ArrayList<>();
        embalseList.add(new AdmEmbalseDTO());

        when(admEmbalseMapper.filtro(any(AdmEmbalseSearchDTO.class))).thenReturn(embalseList);

        AdmEmbalseSearchDTO searchDTO = new AdmEmbalseSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);

        PageInfo<AdmEmbalseDTO> result = admEmbalseService.filtro(searchDTO);

        assertNotNull(result);
        assertEquals(1, result.getList().size());
        verify(admEmbalseMapper, times(1)).filtro(any(AdmEmbalseSearchDTO.class));
    }

    @Test
    void testSelectAll_Success() {
        List<AdmEmbalseDTO> embalseList = new ArrayList<>();
        embalseList.add(new AdmEmbalseDTO());

        when(admEmbalseMapper.selectAll()).thenReturn(embalseList);

        List<AdmEmbalseDTO> result = admEmbalseService.selectAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(admEmbalseMapper, times(1)).selectAll();
    }

    @Test
    void testInsert_Failure_InvalidNomEmbalse() {
        embalseFormDTO.setNomEmbalse("Nombre de embalse muy largo que excede los 100 caracteres permitidos para validación en el servicio AdmEmbalseServiceImpl para la inserción de embalses.");

        MessageDTO result = admEmbalseService.insert(embalseFormDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El nombre del embalse no debe exceder 100 caracteres", result.getMessage());
    }
}
