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
import pe.gob.osinergmin.prie.admin.backend.dto.transformador.AdmTransformadorDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.transformador.AdmTransformadorFormActualizarDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.transformador.AdmTransformadorFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.transformador.AdmTransformadorSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTransformadorService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TransformadorControllerTest {

    @InjectMocks
    private TransformadorController transformadorController;

    @Mock
    private AdmTransformadorService transformadorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        AdmTransformadorFormDTO formDTO = new AdmTransformadorFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Creado correctamente");

        when(transformadorService.insert(any(AdmTransformadorFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = transformadorController.insert(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Creado correctamente", response.getDatas());
    }

    @Test
    void testUpdateByPrimaryKey() {
        AdmTransformadorFormActualizarDTO formDTO = new AdmTransformadorFormActualizarDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Actualizado correctamente");

        when(transformadorService.updateByPrimaryKey(any(AdmTransformadorFormActualizarDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = transformadorController.updateByPrimaryKey(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Actualizado correctamente", response.getDatas());
    }

    @Test
    void testEliminarPorId() {
        int idTransformador = 1;
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Eliminado correctamente");

        when(transformadorService.deleteByPrimaryKey(idTransformador)).thenReturn(messageDTO);

        ResponseEntity<String> response = transformadorController.eliminarPorId(idTransformador);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Eliminado correctamente", response.getDatas());
    }

    @Test
    void testSelectAll() {
        List<AdmTransformadorDTO> transformadores = new ArrayList<>();
        transformadores.add(new AdmTransformadorDTO());

        when(transformadorService.selectAll()).thenReturn(transformadores);

        ResponseEntity<List<AdmTransformadorDTO>> response = transformadorController.selectAll();

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    void testListarTarifasFiltro() {
        AdmTransformadorSearchDTO searchDTO = new AdmTransformadorSearchDTO();
        PageInfo<AdmTransformadorDTO> pageInfo = new PageInfo<>(new ArrayList<>());

        when(transformadorService.filtrar(any(AdmTransformadorSearchDTO.class))).thenReturn(pageInfo);

        ResponseEntity<PageInfo<AdmTransformadorDTO>> response = transformadorController.listarTarifasFiltro(searchDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(0, response.getDatas().getList().size());
    }
}
