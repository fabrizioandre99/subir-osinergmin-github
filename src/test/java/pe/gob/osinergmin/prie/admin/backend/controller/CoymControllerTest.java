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
import pe.gob.osinergmin.prie.admin.backend.dto.coym.AdmCoymDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.coym.AdmCoymFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.coym.AdmCoymSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmCoymService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CoymControllerTest {

    @InjectMocks
    private CoymController coymController;

    @Mock
    private AdmCoymService coymService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        AdmCoymFormDTO formDTO = new AdmCoymFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Creado correctamente");

        when(coymService.insert(any(AdmCoymFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = coymController.insert(formDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Creado correctamente", response.getDatas());
    }

    @Test
    void testUpdateByPrimaryKey() {
        AdmCoymFormDTO formDTO = new AdmCoymFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Actualizado correctamente");

        when(coymService.updateByPrimaryKey(any(AdmCoymFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = coymController.updateByPrimaryKey(formDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Actualizado correctamente", response.getDatas());
    }

    @Test
    void testEliminarPorId() {
        String codCoym = "CY001";
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Eliminado correctamente");

        when(coymService.deleteByPrimaryKey(codCoym)).thenReturn(messageDTO);

        ResponseEntity<String> response = coymController.eliminarPorId(codCoym);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Eliminado correctamente", response.getDatas());
    }

    @Test
    void testSelectAll() {
        List<AdmCoymDTO> coymList = new ArrayList<>();
        coymList.add(new AdmCoymDTO());

        when(coymService.selectAll()).thenReturn(coymList);

        ResponseEntity<List<AdmCoymDTO>> response = coymController.selectAll();

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    void testListarTarifasFiltro() {
        AdmCoymSearchDTO searchDTO = new AdmCoymSearchDTO();
        PageInfo<AdmCoymDTO> pageInfo = new PageInfo<>();
        pageInfo.setList(new ArrayList<>());

        when(coymService.filtrarCoym(any(AdmCoymSearchDTO.class))).thenReturn(pageInfo);

        ResponseEntity<PageInfo<AdmCoymDTO>> response = coymController.listarTarifasFiltro(searchDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals(0, response.getDatas().getList().size());
    }
}
