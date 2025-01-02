package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoTarifa.TipoTarifaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoTarifa.TipoTarifaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoTarifa.TipoTarifaSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTipoTarifaService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class TipoTarifaControllerTest {

    @InjectMocks
    private TipoTarifaController tipoTarifaController;

    @Mock
    private AdmTipoTarifaService admTipoTarifaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarPorCodTarifa() {
        TipoTarifaDTO tipoTarifaDTO = new TipoTarifaDTO();
        tipoTarifaDTO.setCodtarifa("T123");

        when(admTipoTarifaService.buscarPorCodTarifa("T123")).thenReturn(tipoTarifaDTO);

        ResponseEntity<TipoTarifaDTO> response = tipoTarifaController.buscarPorCodTarifa("T123");

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("T123", response.getDatas().getCodtarifa());
    }

    @Test
    public void testCrearTarifa() {
        TipoTarifaFormDTO formDTO = new TipoTarifaFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Tarifa creada");

        when(admTipoTarifaService.insertarTarifa(any(TipoTarifaFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = tipoTarifaController.crearTarifa(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Tarifa creada", response.getDatas());
    }

    @Test
    public void testActualizarTipoTarifa() {
        TipoTarifaFormDTO formDTO = new TipoTarifaFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Tarifa actualizada");

        when(admTipoTarifaService.actualizarTipoTarifa(any(TipoTarifaFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = tipoTarifaController.actualizarTipoTarifa(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Tarifa actualizada", response.getDatas());
    }

    @Test
    public void testEliminarPorCodTarifa() {
        TipoTarifaDTO tipoTarifaDTO = new TipoTarifaDTO();
        tipoTarifaDTO.setCodtarifa("T123");
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Tarifa eliminada");

        when(admTipoTarifaService.eliminarPorCodTarifa("T123")).thenReturn(messageDTO);

        ResponseEntity<String> response = tipoTarifaController.eliminarPorCodTarifa(tipoTarifaDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Tarifa eliminada", response.getDatas());
    }

    @Test
    public void testListarTipoTarifas() {
        List<TipoTarifaDTO> tipoTarifas = new ArrayList<>();
        tipoTarifas.add(new TipoTarifaDTO());

        when(admTipoTarifaService.listarTipoTarifas()).thenReturn(tipoTarifas);

        ResponseEntity<List<TipoTarifaDTO>> response = tipoTarifaController.listarTipoTarifas();

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    public void testListarConFiltro() {
        TipoTarifaSearchDTO searchDTO = new TipoTarifaSearchDTO();
        PageInfo<TipoTarifaDTO> pageInfo = new PageInfo<>(new ArrayList<>());

        when(admTipoTarifaService.listarConFiltro(any(TipoTarifaSearchDTO.class))).thenReturn(pageInfo);

        ResponseEntity<PageInfo<TipoTarifaDTO>> response = tipoTarifaController.listarConFiltro(searchDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(0, response.getDatas().getList().size());
    }
}
