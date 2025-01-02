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
import pe.gob.osinergmin.prie.admin.backend.dto.lago.AdmLagoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.lago.AdmLagoFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.lago.AdmLagoSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmLagoService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class LagoControllerTest {

    @InjectMocks
    private LagoController lagoController;

    @Mock
    private AdmLagoService lagoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        AdmLagoFormDTO formDTO = new AdmLagoFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Lago creado correctamente");

        when(lagoService.insert(any(AdmLagoFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = lagoController.insert(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Lago creado correctamente", response.getDatas());
    }

    @Test
    void testUpdateByPrimaryKey() {
        AdmLagoFormDTO formDTO = new AdmLagoFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Lago actualizado correctamente");

        when(lagoService.updateByPrimaryKey(any(AdmLagoFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = lagoController.updateByPrimaryKey(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Lago actualizado correctamente", response.getDatas());
    }

    @Test
    void testEliminarPorId() {
        String codLago = "LAGO001";
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Lago eliminado correctamente");

        when(lagoService.deleteByPrimaryKey(codLago)).thenReturn(messageDTO);

        ResponseEntity<String> response = lagoController.eliminarPorId(codLago);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Lago eliminado correctamente", response.getDatas());
    }

    @Test
    void testSelectAll() {
        List<AdmLagoDTO> lagoList = new ArrayList<>();
        lagoList.add(new AdmLagoDTO());

        when(lagoService.selectAll()).thenReturn(lagoList);

        ResponseEntity<List<AdmLagoDTO>> response = lagoController.selectAll();

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    void testListarFiltro() {
        AdmLagoSearchDTO searchDTO = new AdmLagoSearchDTO();
        PageInfo<AdmLagoDTO> pageInfo = new PageInfo<>(new ArrayList<>());

        when(lagoService.filtro(any(AdmLagoSearchDTO.class))).thenReturn(pageInfo);

        ResponseEntity<PageInfo<AdmLagoDTO>> response = lagoController.listarFiltro(searchDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(0, response.getDatas().getList().size());
    }
}
