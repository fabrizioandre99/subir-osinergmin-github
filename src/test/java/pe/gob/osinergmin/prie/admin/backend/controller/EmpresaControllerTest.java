package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.Util.CodeEnum;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.empresa.AdmEmpresaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.empresa.AdmEmpresaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.empresa.AdmEmpresaSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmEmpresaService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class EmpresaControllerTest {

    @InjectMocks
    private EmpresaController empresaController;

    @Mock
    private AdmEmpresaService admEmpresaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() throws Exception {
        AdmEmpresaFormDTO formDTO = new AdmEmpresaFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Creado correctamente");

        when(admEmpresaService.insert(any(AdmEmpresaFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = empresaController.insert(formDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Creado correctamente", response.getDatas());
    }

    @Test
    void testUpdateByPrimaryKey() throws Exception {
        AdmEmpresaFormDTO formDTO = new AdmEmpresaFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Actualizado correctamente");

        when(admEmpresaService.updateByPrimaryKey(any(AdmEmpresaFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = empresaController.updateByPrimaryKey(formDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Actualizado correctamente", response.getDatas());
    }

    @Test
    void testDeleteByPrimaryKey() throws Exception {
        String codEmpresa = "EMP001";
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Eliminado correctamente");

        when(admEmpresaService.deleteByPrimaryKey(codEmpresa)).thenReturn(messageDTO);

        ResponseEntity<String> response = empresaController.deleteByPrimaryKey(codEmpresa);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Eliminado correctamente", response.getDatas());
    }

    @Test
    void testSelectAll() throws Exception {
        List<AdmEmpresaDTO> empresaList = new ArrayList<>();
        empresaList.add(new AdmEmpresaDTO());

        when(admEmpresaService.selectAll()).thenReturn(empresaList);

        ResponseEntity<List<AdmEmpresaDTO>> response = empresaController.selectAll();

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

}
