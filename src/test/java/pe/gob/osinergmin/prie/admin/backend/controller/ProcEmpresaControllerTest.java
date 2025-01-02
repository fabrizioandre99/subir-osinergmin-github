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
import pe.gob.osinergmin.prie.admin.backend.dto.procEmpresa.AdmProcEmpresaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procEmpresa.AdmProcEmpresaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procEmpresa.AdmProcEmpresaResultDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmProcEmpresaService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProcEmpresaControllerTest {

    @InjectMocks
    private ProcEmpresaController procEmpresaController;

    @Mock
    private AdmProcEmpresaService admProcEmpresaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        AdmProcEmpresaFormDTO formDTO = new AdmProcEmpresaFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Creado correctamente");

        when(admProcEmpresaService.insert(any(AdmProcEmpresaFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = procEmpresaController.insert(formDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Creado correctamente", response.getDatas());
    }

    @Test
    void testUpdateByPrimaryKey() {
        AdmProcEmpresaFormDTO formDTO = new AdmProcEmpresaFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Actualizado correctamente");

        when(admProcEmpresaService.updateByPrimaryKey(any(AdmProcEmpresaFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = procEmpresaController.updateByPrimaryKey(formDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Actualizado correctamente", response.getDatas());
    }

    @Test
    void testEliminarPorId() {
        Integer idProcEmpresa = 1;
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Eliminado correctamente");

        when(admProcEmpresaService.deleteByPrimaryKey(idProcEmpresa)).thenReturn(messageDTO);

        ResponseEntity<String> response = procEmpresaController.eliminarPorId(idProcEmpresa);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Eliminado correctamente", response.getDatas());
    }

//    @Test
//    void testSelectAll() {
//        List<AdmProcEmpresaDTO> procEmpresaList = new ArrayList<>();
//        procEmpresaList.add(new AdmProcEmpresaDTO());
//
//        when(admProcEmpresaService.selectAll()).thenReturn(procEmpresaList);
//
//        ResponseEntity<List<AdmProcEmpresaDTO>> response = procEmpresaController.selectAll();
//
//        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
//        assertEquals(1, response.getDatas().size());
//    }

    @Test
    void testListarCreacionEmpresa() {
        List<AdmProcEmpresaResultDTO> resultDTOList = new ArrayList<>();
        resultDTOList.add(new AdmProcEmpresaResultDTO());

        when(admProcEmpresaService.listarCreacionEmpresa()).thenReturn(resultDTOList);

        ResponseEntity<List<AdmProcEmpresaResultDTO>> response = procEmpresaController.listarCreacionEmpresa();

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }
}
