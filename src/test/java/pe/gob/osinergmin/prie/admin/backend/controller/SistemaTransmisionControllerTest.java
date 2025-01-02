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
import pe.gob.osinergmin.prie.admin.backend.dto.sistemaTransmision.AdmSistemaTransmisionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sistemaTransmision.AdmSistemaTransmisionFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sistemaTransmision.AdmSistemaTransmisionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmSistemaTransmisionService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SistemaTransmisionControllerTest {

    @InjectMocks
    private SistemaTransmisionController sistemaTransmisionController;

    @Mock
    private AdmSistemaTransmisionService admSistemaTransmisionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        AdmSistemaTransmisionFormdDTO formDTO = new AdmSistemaTransmisionFormdDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Sistema de transmisión creado correctamente");

        when(admSistemaTransmisionService.insert(any(AdmSistemaTransmisionFormdDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = sistemaTransmisionController.insert(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Sistema de transmisión creado correctamente", response.getDatas());
    }

    @Test
    void testUpdateByPrimaryKey() {
        AdmSistemaTransmisionFormdDTO formDTO = new AdmSistemaTransmisionFormdDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Sistema de transmisión actualizado correctamente");

        when(admSistemaTransmisionService.updateByPrimaryKey(any(AdmSistemaTransmisionFormdDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = sistemaTransmisionController.updateByPrimaryKey(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Sistema de transmisión actualizado correctamente", response.getDatas());
    }

    @Test
    void testEliminarPorId() {
        Integer idSisTrans = 1;
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Sistema de transmisión eliminado correctamente");

        when(admSistemaTransmisionService.deleteByPrimaryKey(idSisTrans)).thenReturn(messageDTO);

        ResponseEntity<String> response = sistemaTransmisionController.eliminarPorId(idSisTrans);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Sistema de transmisión eliminado correctamente", response.getDatas());
    }

    @Test
    void testSelectAll() {
        List<AdmSistemaTransmisionDTO> sistemaTransmisionList = new ArrayList<>();
        sistemaTransmisionList.add(new AdmSistemaTransmisionDTO());

        when(admSistemaTransmisionService.selectAll()).thenReturn(sistemaTransmisionList);

        ResponseEntity<List<AdmSistemaTransmisionDTO>> response = sistemaTransmisionController.selectAll();

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    void testListarTarifasFiltro() {
        AdmSistemaTransmisionSearchDTO searchDTO = new AdmSistemaTransmisionSearchDTO();
        PageInfo<AdmSistemaTransmisionDTO> pageInfo = new PageInfo<>(new ArrayList<>());

        when(admSistemaTransmisionService.filrar(any(AdmSistemaTransmisionSearchDTO.class))).thenReturn(pageInfo);

        ResponseEntity<PageInfo<AdmSistemaTransmisionDTO>> response = sistemaTransmisionController.listarTarifasFiltro(searchDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(0, response.getDatas().getList().size());
    }
}
