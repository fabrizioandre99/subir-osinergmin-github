package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.indEconomico.IndEconomicoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.indEconomico.IndEconomicoFormDTO;
import pe.gob.osinergmin.prie.admin.backend.services.IndEconomicoService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class IndEconomicoControllerTest {

    @InjectMocks
    private EconomicoController economicoController;

    @Mock
    private IndEconomicoService indEconomicoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCrearIndicador() {
        IndEconomicoFormDTO indEconomicoDTO = new IndEconomicoFormDTO();
        MessageDTO messageDTO = new MessageDTO("SUCCESS", "Indicador creado correctamente");

        when(indEconomicoService.insertarIndicador(any(IndEconomicoFormDTO.class))).thenReturn(messageDTO);

        MessageDTO response = economicoController.crearIndicador(indEconomicoDTO);

        assertEquals("SUCCESS", response.getStatus());
        assertEquals("Indicador creado correctamente", response.getMessage());
    }

    @Test
    public void testActualizarIndicador() {
        IndEconomicoFormDTO indEconomicoDTO = new IndEconomicoFormDTO();
        MessageDTO messageDTO = new MessageDTO("SUCCESS", "Indicador actualizado correctamente");

        when(indEconomicoService.actualizarIndicador(any(IndEconomicoFormDTO.class))).thenReturn(messageDTO);

        MessageDTO response = economicoController.actualizarIndicador(indEconomicoDTO);

        assertEquals("SUCCESS", response.getStatus());
        assertEquals("Indicador actualizado correctamente", response.getMessage());
    }

    @Test
    public void testEliminarPorCodIndicador() {
        MessageDTO messageDTO = new MessageDTO("SUCCESS", "Indicador eliminado correctamente");

        when(indEconomicoService.eliminarPorCodIndicador("123")).thenReturn(messageDTO);

        MessageDTO response = economicoController.eliminarPorCodIndicador("123");

        assertEquals("SUCCESS", response.getStatus());
        assertEquals("Indicador eliminado correctamente", response.getMessage());
    }

    @Test
    public void testListarIndicadores() {
        List<IndEconomicoDTO> indicadores = new ArrayList<>();
        indicadores.add(new IndEconomicoDTO());

        when(indEconomicoService.listarIndicadores()).thenReturn(indicadores);

        List<IndEconomicoDTO> result = economicoController.listarIndicadores();

        assertEquals(1, result.size());
    }

}
