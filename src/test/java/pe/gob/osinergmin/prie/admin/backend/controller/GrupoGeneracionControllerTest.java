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
import pe.gob.osinergmin.prie.admin.backend.dto.grupoGeneracion.AdmGrupoGeneracionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoGeneracion.AdmGrupoGeneracionFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoGeneracion.AdmGrupoGeneracionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmGrupoGeneracionService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class GrupoGeneracionControllerTest {

    @InjectMocks
    private GrupoGeneracionController grupoGeneracionController;

    @Mock
    private AdmGrupoGeneracionService grupoGeneracionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        AdmGrupoGeneracionFormDTO formDTO = new AdmGrupoGeneracionFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Grupo de Generación creado correctamente");

        when(grupoGeneracionService.insert(any(AdmGrupoGeneracionFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = grupoGeneracionController.insert(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Grupo de Generación creado correctamente", response.getDatas());
    }

    @Test
    void testUpdateByPrimaryKey() {
        AdmGrupoGeneracionFormDTO formDTO = new AdmGrupoGeneracionFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Grupo de Generación actualizado correctamente");

        when(grupoGeneracionService.updateByPrimaryKey(any(AdmGrupoGeneracionFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = grupoGeneracionController.updateByPrimaryKey(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Grupo de Generación actualizado correctamente", response.getDatas());
    }

    @Test
    void testEliminarPorId() {
        String codGrupoGeneracion = "GRP001";
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Grupo de Generación eliminado correctamente");

        when(grupoGeneracionService.deleteByPrimaryKey(codGrupoGeneracion)).thenReturn(messageDTO);

        ResponseEntity<String> response = grupoGeneracionController.eliminarPorId(codGrupoGeneracion);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Grupo de Generación eliminado correctamente", response.getDatas());
    }

    @Test
    void testSelectAll() {
        List<AdmGrupoGeneracionDTO> grupoGeneracionList = new ArrayList<>();
        grupoGeneracionList.add(new AdmGrupoGeneracionDTO());

        when(grupoGeneracionService.selectAll()).thenReturn(grupoGeneracionList);

        ResponseEntity<List<AdmGrupoGeneracionDTO>> response = grupoGeneracionController.selectAll();

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    void testListarFiltro() {
        AdmGrupoGeneracionSearchDTO searchDTO = new AdmGrupoGeneracionSearchDTO();
        PageInfo<AdmGrupoGeneracionDTO> pageInfo = new PageInfo<>(new ArrayList<>());

        when(grupoGeneracionService.filtro(any(AdmGrupoGeneracionSearchDTO.class))).thenReturn(pageInfo);

        ResponseEntity<PageInfo<AdmGrupoGeneracionDTO>> response = grupoGeneracionController.listarFiltro(searchDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(0, response.getDatas().getList().size());
    }
}
