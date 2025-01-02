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
import pe.gob.osinergmin.prie.admin.backend.dto.tipoSistTrans.AdmTipoSistTransDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoSistTrans.AdmTipoSistTransFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoSistTrans.AdmTipoSistTransSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTipoSistTransService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TipoSistTransControllerTest {

    @InjectMocks
    private TipoSistTransController tipoSistTransController;

    @Mock
    private AdmTipoSistTransService admTipoSistTransService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        AdmTipoSistTransFormDTO formDTO = new AdmTipoSistTransFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Creado correctamente");

        when(admTipoSistTransService.insert(any(AdmTipoSistTransFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = tipoSistTransController.insert(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Creado correctamente", response.getDatas());
    }

    @Test
    void testUpdateByPrimaryKey() {
        AdmTipoSistTransFormDTO formDTO = new AdmTipoSistTransFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Actualizado correctamente");

        when(admTipoSistTransService.updateByPrimaryKey(any(AdmTipoSistTransFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = tipoSistTransController.updateByPrimaryKey(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Actualizado correctamente", response.getDatas());
    }

    @Test
    void testEliminarPorId() {
        int idTipoSisTrans = 1;
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Eliminado correctamente");

        when(admTipoSistTransService.deleteByPrimaryKey(idTipoSisTrans)).thenReturn(messageDTO);

        ResponseEntity<String> response = tipoSistTransController.eliminarPorId(idTipoSisTrans);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Eliminado correctamente", response.getDatas());
    }

    @Test
    void testSelectAll() {
        List<AdmTipoSistTransDTO> tipoSistTransList = new ArrayList<>();
        tipoSistTransList.add(new AdmTipoSistTransDTO());

        when(admTipoSistTransService.selectAll()).thenReturn(tipoSistTransList);

        ResponseEntity<List<AdmTipoSistTransDTO>> response = tipoSistTransController.selectAll();

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    void testListarTarifasFiltro() {
        AdmTipoSistTransSearchDTO searchDTO = new AdmTipoSistTransSearchDTO();
        PageInfo<AdmTipoSistTransDTO> pageInfo = new PageInfo<>(new ArrayList<>());

        when(admTipoSistTransService.filtrar(any(AdmTipoSistTransSearchDTO.class))).thenReturn(pageInfo);

        ResponseEntity<PageInfo<AdmTipoSistTransDTO>> response = tipoSistTransController.listarTarifasFiltro(searchDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(0, response.getDatas().getList().size());
    }
}
