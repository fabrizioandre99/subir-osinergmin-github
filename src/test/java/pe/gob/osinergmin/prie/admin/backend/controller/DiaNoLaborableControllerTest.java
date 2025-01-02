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
import pe.gob.osinergmin.prie.admin.backend.dto.diaNoLaborable.AdmDiaNoLaborableFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.diaNoLaborable.DiaNoLaborableDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.diaNoLaborable.DiaNoLaborableSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmDiaNoLaborableService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class DiaNoLaborableControllerTest {

    @InjectMocks
    private DiaNoLaborableController diaNoLaborableController;

    @Mock
    private AdmDiaNoLaborableService admDiaNoLaborableService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() throws Exception {
        AdmDiaNoLaborableFormDTO formDTO = new AdmDiaNoLaborableFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Creado correctamente");

        when(admDiaNoLaborableService.insert(any(AdmDiaNoLaborableFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = diaNoLaborableController.insert(formDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Creado correctamente", response.getDatas());
    }

    @Test
    void testUpdateByPrimaryKey() throws Exception {
        AdmDiaNoLaborableFormDTO formDTO = new AdmDiaNoLaborableFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Actualizado correctamente");

        when(admDiaNoLaborableService.updateByPrimaryKey(any(AdmDiaNoLaborableFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = diaNoLaborableController.updateByPrimaryKey(formDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Actualizado correctamente", response.getDatas());
    }

    @Test
    void testEliminarPorId() throws Exception {
        String fecha = "2024-09-15";
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Eliminado correctamente");

        when(admDiaNoLaborableService.deleteByPrimaryKey(fecha)).thenReturn(messageDTO);

        ResponseEntity<String> response = diaNoLaborableController.eliminarPorId(fecha);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Eliminado correctamente", response.getDatas());
    }

    @Test
    void testEnlistarTodos() throws Exception {
        List<DiaNoLaborableDTO> diaNoLaborableList = new ArrayList<>();
        diaNoLaborableList.add(new DiaNoLaborableDTO());

        when(admDiaNoLaborableService.enlistarTodos()).thenReturn(diaNoLaborableList);

        ResponseEntity<List<DiaNoLaborableDTO>> response = diaNoLaborableController.enlistarTodos();

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    void testFiltrar() throws Exception {
        DiaNoLaborableSearchDTO searchDTO = new DiaNoLaborableSearchDTO();
        PageInfo<DiaNoLaborableDTO> pageInfo = new PageInfo<>(new ArrayList<>());

        when(admDiaNoLaborableService.filtrar(any(DiaNoLaborableSearchDTO.class))).thenReturn(pageInfo);

        ResponseEntity<PageInfo<DiaNoLaborableDTO>> response = diaNoLaborableController.filtrar(searchDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals(0, response.getDatas().getList().size());
    }
}
