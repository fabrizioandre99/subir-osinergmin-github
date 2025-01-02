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
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.barra.AdmBarraDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.barra.AdmBarraFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.barra.AdmBarraSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmBarraMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AdmBarraServiceImplTest {

    @InjectMocks
    private AdmBarraServiceImpl admBarraService;

    @Mock
    private AdmBarraMapper admBarraMapper;

    @Mock
    private HttpServletRequest request;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteByPrimaryKey_Exitoso() {
        when(admBarraMapper.selectByPrimaryKey(anyString())).thenReturn(new AdmBarra());

        MessageDTO result = admBarraService.deleteByPrimaryKey("B001");

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Barra eliminada exitosamente.", result.getMessage());
        verify(admBarraMapper, times(1)).deleteByPrimaryKey(anyString());
    }

    @Test
    void testDeleteByPrimaryKey_NoExiste() {
        when(admBarraMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admBarraService.deleteByPrimaryKey("B001");

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El código de barra no existe. No se puede eliminar.", result.getMessage());
    }

    @Test
    void testDeleteByPrimaryKey_CodigoVacio() {
        MessageDTO result = admBarraService.deleteByPrimaryKey("");

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El código de la barra es requerido.", result.getMessage());
    }


    @Test
    void testInsert_YaExiste() {
        AdmBarraFormDTO formDTO = new AdmBarraFormDTO();
        formDTO.setCodBarra("B001");
        formDTO.setNomBarra("Barra 1");
        formDTO.setEstado("1");

        when(admBarraMapper.selectByPrimaryKey(anyString())).thenReturn(new AdmBarra());

        MessageDTO result = admBarraService.insert(formDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("La barra con el código proporcionado ya existe.", result.getMessage());
    }

    @Test
    void testInsert_CodigoVacio() {
        AdmBarraFormDTO formDTO = new AdmBarraFormDTO();
        formDTO.setNomBarra("Barra 1");
        formDTO.setEstado("1");

        MessageDTO result = admBarraService.insert(formDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El código de la barra es requerido.", result.getMessage());
    }

    @Test
    void testInsert_EstadoInvalido() {
        AdmBarraFormDTO formDTO = new AdmBarraFormDTO();
        formDTO.setCodBarra("B001");
        formDTO.setNomBarra("Barra 1");
        formDTO.setEstado("2");

        MessageDTO result = admBarraService.insert(formDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El estado debe ser '1' (Habilitado) o '0' (Deshabilitado).", result.getMessage());
    }


//    @Test
//    void testUpdateByPrimaryKey_NoExiste() {
//        AdmBarraFormDTO formDTO = new AdmBarraFormDTO();
//        formDTO.setCodBarra("B001");
//        formDTO.setNomBarra("Barra Actualizada");
//        formDTO.setEstado("1");
//
//        when(admBarraMapper.selectByPrimaryKey(anyString())).thenReturn(null);
//
//        MessageDTO result = admBarraService.updateByPrimaryKey(formDTO);
//
//        assertEquals(Constantes.ERROR, result.getStatus());
//        assertEquals("El código de barra no existe. No se puede actualizar.", result.getMessage());
//    }
//
//    @Test
//    void testUpdateByPrimaryKey_CodigoVacio() {
//        AdmBarraFormDTO formDTO = new AdmBarraFormDTO();
//        formDTO.setNomBarra("Barra Actualizada");
//        formDTO.setEstado("1");
//
//        MessageDTO result = admBarraService.updateByPrimaryKey(formDTO);
//
//        assertEquals(Constantes.ERROR, result.getStatus());
//        assertEquals("El código de la barra es requerido.", result.getMessage());
//    }

    @Test
    void testSelectByPrimaryKey() {
        String codBarra = "B001";
        AdmBarra barra = new AdmBarra();
        when(admBarraMapper.selectByPrimaryKey(codBarra)).thenReturn(barra);

        AdmBarra result = admBarraService.selectByPrimaryKey(codBarra);

        assertNotNull(result);
        verify(admBarraMapper, times(1)).selectByPrimaryKey(codBarra);
    }

    @Test
    void testSelectAll() {
        List<AdmBarraDTO> barraList = new ArrayList<>();
        when(admBarraMapper.selectAll()).thenReturn(barraList);

        List<AdmBarraDTO> result = admBarraService.selectAll();

        assertNotNull(result);
        assertEquals(barraList, result);
        verify(admBarraMapper, times(1)).selectAll();
    }

    @Test
    void testFiltrar() {
        AdmBarraSearchDTO searchDTO = new AdmBarraSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);

        List<AdmBarraDTO> barraList = new ArrayList<>();
        PageInfo<AdmBarraDTO> pageInfo = new PageInfo<>(barraList);
        when(admBarraMapper.filtrar(any(AdmBarraSearchDTO.class))).thenReturn(barraList);

        PageInfo<AdmBarraDTO> result = admBarraService.filtrar(searchDTO);

        assertNotNull(result);
        assertEquals(barraList, result.getList());
        verify(admBarraMapper, times(1)).filtrar(any(AdmBarraSearchDTO.class));
    }
}
