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
import pe.gob.osinergmin.prie.admin.backend.dto.grupoBarra.AdmGrupoBarraSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoBarra.AdmGrupoBarraformDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoBarra.GrupoBarraResponseDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmGrupoBarraService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AdmGrupoBarraControllerTest {

    @InjectMocks
    private AdmGrupoBarraController admGrupoBarraController;

    @Mock
    private AdmGrupoBarraService grupoBarraService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertGrupoBarra() {
        AdmGrupoBarraformDTO formDTO = new AdmGrupoBarraformDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Grupo de barra creado correctamente");

        when(grupoBarraService.insertGrupoBarra(any(AdmGrupoBarraformDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = admGrupoBarraController.insertGrupoBarra(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Grupo de barra creado correctamente", response.getDatas());
    }

    @Test
    void testActualizarGrupoBarra() {
        AdmGrupoBarraformDTO formDTO = new AdmGrupoBarraformDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Grupo de barra actualizado correctamente");

        when(grupoBarraService.actualizarGrupoBarra(any(AdmGrupoBarraformDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = admGrupoBarraController.actualizarGrupoBarra(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Grupo de barra actualizado correctamente", response.getDatas());
    }

    @Test
    void testEliminarPorCod() {
        String codBarra = "B001";
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Grupo de barra eliminado correctamente");

        when(grupoBarraService.eliminarGrupoBarra(codBarra)).thenReturn(messageDTO);

        ResponseEntity<String> response = admGrupoBarraController.eliminarPorCod(codBarra);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Grupo de barra eliminado correctamente", response.getDatas());
    }

    @Test
    void testListarGruposDeBarras() {
        List<GrupoBarraResponseDTO> gruposDeBarras = new ArrayList<>();
        gruposDeBarras.add(new GrupoBarraResponseDTO());

        when(grupoBarraService.listarGruposDeBarras()).thenReturn(gruposDeBarras);

        List<GrupoBarraResponseDTO> response = admGrupoBarraController.listarGruposDeBarras();

        assertEquals(1, response.size());
    }

    @Test
    void testFiltrarGruposDeBarras() {
        AdmGrupoBarraSearchDTO searchDTO = new AdmGrupoBarraSearchDTO();
        PageInfo<GrupoBarraResponseDTO> pageInfo = new PageInfo<>(new ArrayList<>());

        when(grupoBarraService.filtrar(any(AdmGrupoBarraSearchDTO.class))).thenReturn(pageInfo);

        PageInfo<GrupoBarraResponseDTO> response = admGrupoBarraController.filtrarGruposDeBarras(searchDTO);

        assertEquals(0, response.getList().size());
    }
}
