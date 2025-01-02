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
import pe.gob.osinergmin.prie.admin.backend.dto.funcionProcSuperv.AdmFuncionProcSupervDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.funcionProcSuperv.AdmFuncionProcSupervFormDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmFuncionProcSupervService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class FuncionProcSupervControllerTest {

    @InjectMocks
    private FuncionProcSupervController funcionProcSupervController;

    @Mock
    private AdmFuncionProcSupervService funcionProcSupervService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        AdmFuncionProcSupervFormDTO formDTO = new AdmFuncionProcSupervFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Creado correctamente");

        when(funcionProcSupervService.insert(any(AdmFuncionProcSupervFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = funcionProcSupervController.insert(formDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Creado correctamente", response.getDatas());
    }

    @Test
    void testUpdateByPrimaryKey() {
        AdmFuncionProcSupervFormDTO formDTO = new AdmFuncionProcSupervFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Actualizado correctamente");

        when(funcionProcSupervService.updateByPrimaryKey(any(AdmFuncionProcSupervFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = funcionProcSupervController.updateByPrimaryKey(formDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Actualizado correctamente", response.getDatas());
    }

    @Test
    void testEliminarPorId() {
        String codProcSupervision = "PS001";
        String codFuncionProcSuperv = "FS001";
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Eliminado correctamente");

        when(funcionProcSupervService.deleteByPrimaryKey(codProcSupervision, codFuncionProcSuperv)).thenReturn(messageDTO);

        ResponseEntity<String> response = funcionProcSupervController.eliminarPorId(codProcSupervision, codFuncionProcSuperv);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Eliminado correctamente", response.getDatas());
    }

    @Test
    void testListar() {
        List<AdmFuncionProcSupervDTO> funcionProcSupervList = new ArrayList<>();
        funcionProcSupervList.add(new AdmFuncionProcSupervDTO());

        when(funcionProcSupervService.listar()).thenReturn(funcionProcSupervList);

        ResponseEntity<List<AdmFuncionProcSupervDTO>> response = funcionProcSupervController.listar();

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }
}
