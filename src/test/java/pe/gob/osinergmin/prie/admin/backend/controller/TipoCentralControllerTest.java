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
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCentral.AdmTipoCentralDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCentral.AdmTipoCentralFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCentral.AdmTipoCentralSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTipoCentralService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TipoCentralControllerTest {

    @InjectMocks
    private TipoCentralController tipoCentralController;

    @Mock
    private AdmTipoCentralService admTipoCentralService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        AdmTipoCentralFormdDTO formDTO = new AdmTipoCentralFormdDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Creado correctamente");

        when(admTipoCentralService.insert(any(AdmTipoCentralFormdDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = tipoCentralController.insert(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Creado correctamente", response.getDatas());
    }

    @Test
    void testUpdateByPrimaryKey() {
        AdmTipoCentralFormdDTO formDTO = new AdmTipoCentralFormdDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Actualizado correctamente");

        when(admTipoCentralService.updateByPrimaryKey(any(AdmTipoCentralFormdDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = tipoCentralController.updateByPrimaryKey(formDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Actualizado correctamente", response.getDatas());
    }

    @Test
    void testDeleteByPrimaryKey() {
        String codTipoCentral = "C001";
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Eliminado correctamente");

        when(admTipoCentralService.deleteByPrimaryKey(codTipoCentral)).thenReturn(messageDTO);

        ResponseEntity<String> response = tipoCentralController.deleteByPrimaryKey(codTipoCentral);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals("Eliminado correctamente", response.getDatas());
    }

    @Test
    void testSelectAll() {
        List<AdmTipoCentralDTO> tipoCentralList = new ArrayList<>();
        tipoCentralList.add(new AdmTipoCentralDTO());

        when(admTipoCentralService.selectAll()).thenReturn(tipoCentralList);

        ResponseEntity<List<AdmTipoCentralDTO>> response = tipoCentralController.selectAll();

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    void testListarTarifasFiltro() {
        AdmTipoCentralSearchDTO searchDTO = new AdmTipoCentralSearchDTO();
        PageInfo<AdmTipoCentralDTO> pageInfo = new PageInfo<>(new ArrayList<>());

        when(admTipoCentralService.filtrar(any(AdmTipoCentralSearchDTO.class))).thenReturn(pageInfo);

        ResponseEntity<PageInfo<AdmTipoCentralDTO>> response = tipoCentralController.listarTarifasFiltro(searchDTO);

        assertEquals(0, response.getStatusCodeValue());
        assertEquals(0, response.getDatas().getList().size());
    }
}
