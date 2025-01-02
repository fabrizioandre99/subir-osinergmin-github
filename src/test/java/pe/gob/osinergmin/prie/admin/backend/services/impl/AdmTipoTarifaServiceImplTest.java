package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTipoTarifa;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoTarifa.TipoTarifaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoTarifa.TipoTarifaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoTarifa.TipoTarifaSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmTipoTarifaMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AdmTipoTarifaServiceImplTest {

    @Mock
    private AdmTipoTarifaMapper admTipoTarifaMapper;

    @InjectMocks
    private AdmTipoTarifaServiceImpl admTipoTarifaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testEliminarPorCodTarifa_NoExistente() {
        when(admTipoTarifaMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admTipoTarifaService.eliminarPorCodTarifa("BT8");

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El código de la tarifa no existe. No se puede eliminar.", result.getMessage());
    }

    @Test
    public void testInsertarTarifa_Exitoso() {
        when(admTipoTarifaMapper.selectByPrimaryKey(anyString())).thenReturn(null);
        when(admTipoTarifaMapper.insert(any(AdmTipoTarifa.class))).thenReturn(1);

        TipoTarifaFormDTO formDTO = new TipoTarifaFormDTO();
        formDTO.setCodtarifa("BT8");
        formDTO.setDescripcion("Tarifa No Residencial");
        formDTO.setEstado("1");

        MessageDTO result = admTipoTarifaService.insertarTarifa(formDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        verify(admTipoTarifaMapper, times(1)).insert(any(AdmTipoTarifa.class));
    }

    @Test
    public void testInsertarTarifa_YaExiste() {
        when(admTipoTarifaMapper.selectByPrimaryKey(anyString())).thenReturn(new AdmTipoTarifa());

        TipoTarifaFormDTO formDTO = new TipoTarifaFormDTO();
        formDTO.setCodtarifa("BT8");

        MessageDTO result = admTipoTarifaService.insertarTarifa(formDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El código de la tarifa ya existe.", result.getMessage());
    }

    @Test
    public void testBuscarPorCodTarifa_Exitoso() {
        when(admTipoTarifaMapper.buscarPorCodTarifa(anyString())).thenReturn(new TipoTarifaDTO());

        TipoTarifaDTO result = admTipoTarifaService.buscarPorCodTarifa("BT8");

        assertNotNull(result);
        verify(admTipoTarifaMapper, times(1)).buscarPorCodTarifa(anyString());
    }

    @Test
    public void testActualizarTipoTarifa_Exitoso() {
        when(admTipoTarifaMapper.existeCod(anyString())).thenReturn(1);
        when(admTipoTarifaMapper.updateByPrimaryKey(any(AdmTipoTarifa.class))).thenReturn(1);

        TipoTarifaFormDTO formDTO = new TipoTarifaFormDTO();
        formDTO.setCodtarifa("BT8");
        formDTO.setDescripcion("Tarifa No Residencial");
        formDTO.setEstado("1");

        MessageDTO result = admTipoTarifaService.actualizarTipoTarifa(formDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        verify(admTipoTarifaMapper, times(1)).updateByPrimaryKey(any(AdmTipoTarifa.class));
    }

    @Test
    public void testActualizarTipoTarifa_NoExistente() {
        when(admTipoTarifaMapper.existeCod(anyString())).thenReturn(0);

        TipoTarifaFormDTO formDTO = new TipoTarifaFormDTO();
        formDTO.setCodtarifa("BT8");

        MessageDTO result = admTipoTarifaService.actualizarTipoTarifa(formDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El código de la tarifa no existe. No se puede actualizar.", result.getMessage());
    }

    @Test
    public void testListarTipoTarifas() {
        List<TipoTarifaDTO> lista = new ArrayList<>();
        when(admTipoTarifaMapper.listarIdTarifa()).thenReturn(lista);

        List<TipoTarifaDTO> result = admTipoTarifaService.listarTipoTarifas();

        assertEquals(lista, result);
        verify(admTipoTarifaMapper, times(1)).listarIdTarifa();
    }

    @Test
    public void testListarConFiltro() {
        List<TipoTarifaDTO> lista = new ArrayList<>();
        when(admTipoTarifaMapper.listarConFiltro(any(TipoTarifaSearchDTO.class))).thenReturn(lista);

        TipoTarifaSearchDTO searchDTO = new TipoTarifaSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);

        PageInfo<TipoTarifaDTO> result = admTipoTarifaService.listarConFiltro(searchDTO);

        assertEquals(lista, result.getList());
        verify(admTipoTarifaMapper, times(1)).listarConFiltro(any(TipoTarifaSearchDTO.class));
    }
}
