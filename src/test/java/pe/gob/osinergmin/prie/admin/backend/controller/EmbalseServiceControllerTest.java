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
import pe.gob.osinergmin.prie.admin.backend.dto.embalse.AdmEmbalseDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.embalse.AdmEmbalseFomrDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.embalse.AdmEmbalseSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmEmbalseService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class EmbalseServiceControllerTest {

    @InjectMocks
    private EmbalseServiceController embalseServiceController;

    @Mock
    private AdmEmbalseService embalseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        AdmEmbalseFomrDTO formDTO = new AdmEmbalseFomrDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Embalse creado correctamente");

        when(embalseService.insert(any(AdmEmbalseFomrDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = embalseServiceController.insert(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Embalse creado correctamente", response.getDatas());
    }

    @Test
    void testUpdateByPrimaryKey() {
        AdmEmbalseFomrDTO formDTO = new AdmEmbalseFomrDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Embalse actualizado correctamente");

        when(embalseService.updateByPrimaryKey(any(AdmEmbalseFomrDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = embalseServiceController.updateByPrimaryKey(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Embalse actualizado correctamente", response.getDatas());
    }

    @Test
    void testEliminarPorId() {
        String codEmbalse = "EMB001";
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Embalse eliminado correctamente");

        when(embalseService.deleteByPrimaryKey(codEmbalse)).thenReturn(messageDTO);

        ResponseEntity<String> response = embalseServiceController.eliminarPorId(codEmbalse);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Embalse eliminado correctamente", response.getDatas());
    }

    @Test
    void testSelectAll() {
        List<AdmEmbalseDTO> embalseList = new ArrayList<>();
        embalseList.add(new AdmEmbalseDTO());

        when(embalseService.selectAll()).thenReturn(embalseList);

        ResponseEntity<List<AdmEmbalseDTO>> response = embalseServiceController.selectAll();

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    void testListarFiltro() {
        AdmEmbalseSearchDTO searchDTO = new AdmEmbalseSearchDTO();
        PageInfo<AdmEmbalseDTO> pageInfo = new PageInfo<>(new ArrayList<>());

        when(embalseService.filtro(any(AdmEmbalseSearchDTO.class))).thenReturn(pageInfo);

        ResponseEntity<PageInfo<AdmEmbalseDTO>> response = embalseServiceController.listarFiltro(searchDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(0, response.getDatas().getList().size());
    }
}
