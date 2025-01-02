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
import pe.gob.osinergmin.prie.admin.backend.dto.barra.AdmBarraDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.barra.AdmBarraFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.barra.AdmBarraSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmBarraService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BarraControllerTest {

    @InjectMocks
    private BarraController barraController;

    @Mock
    private AdmBarraService admBarraService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() throws Exception {
        AdmBarraFormDTO formDTO = new AdmBarraFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Barra creada exitosamente.");

        when(admBarraService.insert(any(AdmBarraFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = barraController.insert(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Barra creada exitosamente.", response.getDatas());
    }


//    @Test
//    void testUpdateByPrimaryKey() throws Exception {
//        AdmBarraFormDTO formDTO = new AdmBarraFormDTO();
//        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Barra actualizada exitosamente.");
//
//        when(admBarraService.updateByPrimaryKey(any(AdmBarraFormDTO.class))).thenReturn(messageDTO);
//
//        ResponseEntity<String> response = barraController.updateByPrimaryKey(formDTO);
//
//        assertEquals(0, response.getStatusCodeValue());
//        assertEquals("Barra actualizada exitosamente.", response.getDatas());
//    }

    @Test
    void testEliminarPorId() throws Exception {
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Barra eliminada exitosamente.");

        when(admBarraService.deleteByPrimaryKey("BAR001")).thenReturn(messageDTO);

        ResponseEntity<String> response = barraController.eliminarPorId("BAR001");

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Barra eliminada exitosamente.", response.getDatas());
    }


    @Test
    void testSelectAll() throws Exception {
        List<AdmBarraDTO> barras = new ArrayList<>();
        barras.add(new AdmBarraDTO());

        when(admBarraService.selectAll()).thenReturn(barras);

        ResponseEntity<List<AdmBarraDTO>> response = barraController.selectAll();

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    void testFiltrar() throws Exception {
        AdmBarraSearchDTO searchDTO = new AdmBarraSearchDTO();
        PageInfo<AdmBarraDTO> pageInfo = new PageInfo<>(new ArrayList<>());

        when(admBarraService.filtrar(any(AdmBarraSearchDTO.class))).thenReturn(pageInfo);

        ResponseEntity<PageInfo<AdmBarraDTO>> response = barraController.filtrar(searchDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(0, response.getDatas().getList().size());
    }
}