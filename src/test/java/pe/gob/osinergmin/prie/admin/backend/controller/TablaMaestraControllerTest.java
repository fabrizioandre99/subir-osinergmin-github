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
import pe.gob.osinergmin.prie.admin.backend.dto.parametro.CfgParametroSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tablaMaestra.TablaMaestraDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tablaMaestra.TablaMaestraFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.services.TablaMaestraService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TablaMaestraControllerTest {

    @InjectMocks
    private TablaMaestraController tablaMaestraController;

    @Mock
    private TablaMaestraService tablaMaestraService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertarRegistro() {
        TablaMaestraFormdDTO formDTO = new TablaMaestraFormdDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Registro creado correctamente");

        when(tablaMaestraService.insertarRegistro(any(TablaMaestraFormdDTO.class), any(String.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = tablaMaestraController.insertarRegistro(formDTO, "GRP001");

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Registro creado correctamente", response.getDatas());
    }

    @Test
    void testActualizar() {
        TablaMaestraFormdDTO formDTO = new TablaMaestraFormdDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Registro actualizado correctamente");

        when(tablaMaestraService.updateRegistro(any(TablaMaestraFormdDTO.class), any(String.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = tablaMaestraController.actualizar(formDTO, "GRP001");

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Registro actualizado correctamente", response.getDatas());
    }

    @Test
    void testDeleteRegistro() {
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Registro eliminado correctamente");

        when(tablaMaestraService.deleteRegistro(any(String.class), any(String.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = tablaMaestraController.deleteRegistro("GRP001", "PARAM001");

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Registro eliminado correctamente", response.getDatas());
    }

    @Test
    void testListarRegistros() {
        List<TablaMaestraDTO> registros = new ArrayList<>();
        registros.add(new TablaMaestraDTO());

        when(tablaMaestraService.getLstTablaMaestra(any(String.class))).thenReturn(registros);

        ResponseEntity<List<TablaMaestraDTO>> response = tablaMaestraController.listarRegistros("GRP001");

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    void testFiltrar() {
        CfgParametroSearchDTO searchDTO = new CfgParametroSearchDTO();
        PageInfo<TablaMaestraDTO> pageInfo = new PageInfo<>(new ArrayList<>());

        when(tablaMaestraService.filtrar(any(CfgParametroSearchDTO.class), any(String.class))).thenReturn(pageInfo);

        ResponseEntity<PageInfo<TablaMaestraDTO>> response = tablaMaestraController.filtrar(searchDTO, "GRP001");

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(0, response.getDatas().getList().size());
    }
}
