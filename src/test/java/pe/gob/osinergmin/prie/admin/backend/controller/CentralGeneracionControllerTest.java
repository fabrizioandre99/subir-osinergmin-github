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
import pe.gob.osinergmin.prie.admin.backend.dto.centralGeneracion.AdmCentralGeneracionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.centralGeneracion.AdmCentralGeneracionFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.centralGeneracion.AdmCentralGeneracionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmCentralGeneracionService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CentralGeneracionControllerTest {

    @InjectMocks
    private CentralGeneracionController centralGeneracionController;

    @Mock
    private AdmCentralGeneracionService admCentralGeneracionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        AdmCentralGeneracionFormdDTO formDTO = new AdmCentralGeneracionFormdDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Central de Generación creada correctamente");

        when(admCentralGeneracionService.insert(any(AdmCentralGeneracionFormdDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = centralGeneracionController.insert(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Central de Generación creada correctamente", response.getDatas());
    }

    @Test
    void testUpdateByPrimaryKey() {
        AdmCentralGeneracionFormdDTO formDTO = new AdmCentralGeneracionFormdDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Central de Generación actualizada correctamente");

        when(admCentralGeneracionService.updateByPrimaryKey(any(AdmCentralGeneracionFormdDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = centralGeneracionController.updateByPrimaryKey(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Central de Generación actualizada correctamente", response.getDatas());
    }

    @Test
    void testEliminarPorId() {
        String codCentralGeneracion = "CENT001";
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Central de Generación eliminada correctamente");

        when(admCentralGeneracionService.deleteByPrimaryKey(codCentralGeneracion)).thenReturn(messageDTO);

        ResponseEntity<String> response = centralGeneracionController.eliminarPorId(codCentralGeneracion);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Central de Generación eliminada correctamente", response.getDatas());
    }

    @Test
    void testSelectAll() {
        List<AdmCentralGeneracionDTO> centralesGeneracion = new ArrayList<>();
        centralesGeneracion.add(new AdmCentralGeneracionDTO());

        when(admCentralGeneracionService.selectAll()).thenReturn(centralesGeneracion);

        ResponseEntity<List<AdmCentralGeneracionDTO>> response = centralGeneracionController.selectAll();

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    void testListarFiltro() {
        AdmCentralGeneracionSearchDTO searchDTO = new AdmCentralGeneracionSearchDTO();
        PageInfo<AdmCentralGeneracionDTO> pageInfo = new PageInfo<>(new ArrayList<>());

        when(admCentralGeneracionService.filtro(any(AdmCentralGeneracionSearchDTO.class))).thenReturn(pageInfo);

        ResponseEntity<PageInfo<AdmCentralGeneracionDTO>> response = centralGeneracionController.listarFiltro(searchDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(0, response.getDatas().getList().size());
    }
}
