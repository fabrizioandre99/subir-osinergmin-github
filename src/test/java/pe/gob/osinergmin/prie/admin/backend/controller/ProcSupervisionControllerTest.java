package pe.gob.osinergmin.prie.admin.backend.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.Util.CodeEnum;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procSupervision.AdmProcSupervisionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procSupervision.AdmProcSupervisionFormDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmProcSupervisionService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProcSupervisionControllerTest {

    @InjectMocks
    private ProcSupervisionController procSupervisionController;

    @Mock
    private AdmProcSupervisionService procSupervisionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        AdmProcSupervisionFormDTO formDTO = new AdmProcSupervisionFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Creado correctamente");

        when(procSupervisionService.insert(any(AdmProcSupervisionFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = procSupervisionController.insert(formDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Creado correctamente", response.getDatas());
    }

    @Test
    void testUpdateByPrimaryKey() {
        AdmProcSupervisionFormDTO formDTO = new AdmProcSupervisionFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Actualizado correctamente");

        when(procSupervisionService.updateByPrimaryKey(any(AdmProcSupervisionFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = procSupervisionController.updateByPrimaryKey(formDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Actualizado correctamente", response.getDatas());
    }

    @Test
    void testEliminarPorId() {
        String codProcSupervision = "SUP001";
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Eliminado correctamente");

        when(procSupervisionService.deleteByPrimaryKey(codProcSupervision)).thenReturn(messageDTO);

        ResponseEntity<String> response = procSupervisionController.eliminarPorId(codProcSupervision);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Eliminado correctamente", response.getDatas());
    }

    @Test
    void testSelectAll() {
        List<AdmProcSupervisionDTO> procSupervisionList = new ArrayList<>();
        procSupervisionList.add(new AdmProcSupervisionDTO());

        when(procSupervisionService.selectAll()).thenReturn(procSupervisionList);

        ResponseEntity<List<AdmProcSupervisionDTO>> response = procSupervisionController.selectAll();

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }
}
