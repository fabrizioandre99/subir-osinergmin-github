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
import pe.gob.osinergmin.prie.admin.backend.dto.tarifa.TarifaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tarifa.TarifaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tarifa.TarifaResultDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tarifa.TarifaSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTarifaService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TarifaControllerTest {

    @InjectMocks
    private TarifaController tarifaController;

    @Mock
    private AdmTarifaService admTarifaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDetalleTarifa() throws Exception {
        TarifaDTO tarifaDTO = new TarifaDTO();
        tarifaDTO.setIdTarifa(1);

        when(admTarifaService.detalleTarifa(1)).thenReturn(tarifaDTO);

        ResponseEntity<TarifaDTO> response = tarifaController.detalleTarifa(1);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(1, response.getDatas().getIdTarifa());
    }

    @Test
    public void testCrearTarifa() throws Exception {
        TarifaFormDTO formDTO = new TarifaFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Tarifa creada");

        when(admTarifaService.insertarTarifa(any(TarifaFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = tarifaController.crearTarifa(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Tarifa creada", response.getDatas());
    }

    @Test
    public void testActualizarTarifa() throws Exception {
        TarifaFormDTO formDTO = new TarifaFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Tarifa actualizada");

        when(admTarifaService.actualizarTarifa(any(TarifaFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = tarifaController.actualizarTarifa(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Tarifa actualizada", response.getDatas());
    }

    @Test
    public void testEliminarPorId() throws Exception {
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Eliminado correctamente");

        when(admTarifaService.eliminarPorId(1)).thenReturn(messageDTO);

        ResponseEntity<String> response = tarifaController.eliminarPorId(1);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Eliminado correctamente", response.getDatas());
    }

    @Test
    public void testListarTarifas() throws Exception {
        List<TarifaDTO> tarifas = new ArrayList<>();
        tarifas.add(new TarifaDTO());

        when(admTarifaService.listarTarifas()).thenReturn(tarifas);

        ResponseEntity<List<TarifaDTO>> response = tarifaController.listarTarifas();

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    public void testListarTarifasFiltro() throws Exception {
        TarifaSearchDTO searchDTO = new TarifaSearchDTO();
        PageInfo<TarifaDTO> pageInfo = new PageInfo<>(new ArrayList<>());

        when(admTarifaService.listarTarifasFiltro(any(TarifaSearchDTO.class))).thenReturn(pageInfo);

        ResponseEntity<PageInfo<TarifaDTO>> response = tarifaController.listarTarifasFiltro(searchDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(0, response.getDatas().getList().size());
    }

    @Test
    public void testListarTiposTarifaConcat() throws Exception {
        List<TarifaResultDTO> tarifas = new ArrayList<>();
        tarifas.add(new TarifaResultDTO());

        when(admTarifaService.listarTiposTarifaConcat()).thenReturn(tarifas);

        ResponseEntity<List<TarifaResultDTO>> response = tarifaController.listarTiposTarifaConcat();

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    public void testActualizarTarifaSelective() throws Exception {
        TarifaDTO tarifaDTO = new TarifaDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Actualizado correctamente");

        when(admTarifaService.actualizarTarifaSelective(any(TarifaDTO.class))).thenReturn(messageDTO);

        MessageDTO response = tarifaController.actualizarTarifaSelective(tarifaDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("Actualizado correctamente", response.getMessage());
    }
}
