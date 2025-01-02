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
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCambio.TipoCambioDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCambio.TipoCambioFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCambio.TipoCambioResultDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCambio.TipoCambioSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTipoCambioService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class TipoCambioControllerTest {

    @InjectMocks
    private TipoCambioController tipoCambioController;

    @Mock
    private AdmTipoCambioService admTipoCambioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCrearTipoCambio() throws Exception {
        TipoCambioFormDTO tipoCambioFormDTO = new TipoCambioFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Tipo de cambio creado");

        when(admTipoCambioService.insertarTipoCambio(any(TipoCambioFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = tipoCambioController.crearTipoCambio(tipoCambioFormDTO);

        assertEquals(0, response.getStatusCodeValue()); // Asegúrate que este valor sea correcto según tu lógica
        assertEquals("Tipo de cambio creado", response.getDatas());
    }

    @Test
    public void testActualizarTipoCambio() throws Exception {
        TipoCambioFormDTO tipoCambioFormDTO = new TipoCambioFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Tipo de cambio actualizado");

        when(admTipoCambioService.actualizarTipoCambio(any(TipoCambioFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = tipoCambioController.actualizarTipoCambio(tipoCambioFormDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Tipo de cambio actualizado", response.getDatas());
    }

    @Test
    public void testListarTiposCambio() {
        List<TipoCambioResultDTO> tiposCambio = new ArrayList<>();
        tiposCambio.add(new TipoCambioResultDTO());

        when(admTipoCambioService.listarTiposCambio()).thenReturn(tiposCambio);

        ResponseEntity<List<TipoCambioResultDTO>> response = tipoCambioController.listarTiposCambio();

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    public void testListarConFiltro() {
        TipoCambioSearchDTO searchDTO = new TipoCambioSearchDTO();
        PageInfo<TipoCambioResultDTO> pageInfo = new PageInfo<>(new ArrayList<>());

        when(admTipoCambioService.listarConFiltro(any(TipoCambioSearchDTO.class))).thenReturn(pageInfo);

        ResponseEntity<PageInfo<TipoCambioResultDTO>> response = tipoCambioController.listarConFiltro(searchDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(0, response.getDatas().getList().size());
    }
}
