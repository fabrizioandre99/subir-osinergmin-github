package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCambio.TipoCambioFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCambio.TipoCambioResultDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCambio.TipoCambioSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmTipoCambioMapper;
import pe.gob.osinergmin.prie.admin.backend.services.impl.AdmTipoCambioServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AdmTipoCambioServiceImplTest {

    @InjectMocks
    private AdmTipoCambioServiceImpl admTipoCambioService;

    @Mock
    private AdmTipoCambioMapper admTipoCambioMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEliminarPorMonedaFuenteFecha_Existe() {
        when(admTipoCambioMapper.obtenerFecha("2023-10-10")).thenReturn(1);

        MessageDTO result = admTipoCambioService.eliminarPorMonedaFuenteFecha("2023-10-10");
        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Se eliminó correctamente.", result.getMessage());

        verify(admTipoCambioMapper, times(1)).deleteByPrimaryKey(eq("2023-10-10"));
    }

    @Test
    void testEliminarPorMonedaFuenteFecha_NoExiste() {
        when(admTipoCambioMapper.obtenerFecha("2023-10-10")).thenReturn(0);

        MessageDTO result = admTipoCambioService.eliminarPorMonedaFuenteFecha("2023-10-10");

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El Tipo de cambio no fue eliminado.", result.getMessage());
        verify(admTipoCambioMapper, never()).deleteByPrimaryKey(any());
    }

    @Test
    void testInsertarTipoCambio_Nuevo() throws Exception {
        TipoCambioFormDTO formDTO = new TipoCambioFormDTO();
        formDTO.setMoneda("USD");
        formDTO.setFuente("BCR");
        formDTO.setFecha("2023-10-10");
        formDTO.setValCompra("3.80");
        formDTO.setValVenta("3.85");


        when(admTipoCambioMapper.obtenerFecha("2023-10-10")).thenReturn(0);

        MessageDTO result = admTipoCambioService.insertarTipoCambio(formDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        verify(admTipoCambioMapper, times(1)).insertarNuevo(any());
    }

    @Test
    void testInsertarTipoCambio_Existente() throws Exception {
        TipoCambioFormDTO formDTO = new TipoCambioFormDTO();
        formDTO.setMoneda("USD");
        formDTO.setFuente("BCR");
        formDTO.setFecha("2023-10-10");

        when(admTipoCambioMapper.obtenerFecha("2023-10-10")).thenReturn(1);

        MessageDTO result = admTipoCambioService.insertarTipoCambio(formDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El tipo de cambio ya existe.", result.getMessage());
        verify(admTipoCambioMapper, never()).insertarNuevo(any());
    }

    @Test
    void testActualizarTipoCambio_Existe() throws Exception {
        TipoCambioFormDTO formDTO = new TipoCambioFormDTO();
        formDTO.setMoneda("USD");
        formDTO.setFuente("BCR");
        formDTO.setFecha("2023-10-10");
        formDTO.setValCompra("3.80");
        formDTO.setValVenta("3.85");


        when(admTipoCambioMapper.obtenerFecha("2023-10-10")).thenReturn(1);
        when(admTipoCambioMapper.actualizarNuevo(any())).thenReturn(1);

        MessageDTO result = admTipoCambioService.actualizarTipoCambio(formDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Tipo de cambio actualizado exitosamente.", result.getMessage());
        verify(admTipoCambioMapper, times(1)).actualizarNuevo(any());
    }

    @Test
    void testActualizarTipoCambio_NoExiste() throws Exception {
        TipoCambioFormDTO formDTO = new TipoCambioFormDTO();
        formDTO.setMoneda("USD");
        formDTO.setFuente("BCR");
        formDTO.setFecha("2023-10-10");

        when(admTipoCambioMapper.obtenerFecha("2023-10-10")).thenReturn(0);

        MessageDTO result = admTipoCambioService.actualizarTipoCambio(formDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("No se encontró una fecha asociada a esta fuente.", result.getMessage());
        verify(admTipoCambioMapper, never()).actualizarNuevo(any());
    }

    @Test
    void testListarTiposCambio() {
        List<TipoCambioResultDTO> tiposCambio = new ArrayList<>();
        tiposCambio.add(new TipoCambioResultDTO());

        when(admTipoCambioMapper.selectAll()).thenReturn(tiposCambio);

        List<TipoCambioResultDTO> result = admTipoCambioService.listarTiposCambio();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(admTipoCambioMapper, times(1)).selectAll();
    }

    @Test
    void testListarConFiltro() {
        TipoCambioSearchDTO searchDTO = new TipoCambioSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);
        searchDTO.setFecha("2023-10-10");

        List<TipoCambioResultDTO> tiposCambio = new ArrayList<>();
        tiposCambio.add(new TipoCambioResultDTO());

        when(admTipoCambioMapper.selectByFecha(any(TipoCambioSearchDTO.class))).thenReturn(tiposCambio);

        PageInfo<TipoCambioResultDTO> result = admTipoCambioService.listarConFiltro(searchDTO);

        assertNotNull(result);
        assertEquals(1, result.getList().size());
        verify(admTipoCambioMapper, times(1)).selectByFecha(any(TipoCambioSearchDTO.class));
    }
}
