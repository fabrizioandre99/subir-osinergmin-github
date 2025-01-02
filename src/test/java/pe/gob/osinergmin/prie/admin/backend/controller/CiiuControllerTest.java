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
import pe.gob.osinergmin.prie.admin.backend.dto.ciiu.AdmCiiuDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ciiu.AdmCiiuFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ciiu.AdmCiiuSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmCiiuService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CiiuControllerTest {

    @InjectMocks
    private CiiuController ciiuController;

    @Mock
    private AdmCiiuService admCiiuService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert(){
        AdmCiiuFormDTO formDTO = new AdmCiiuFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Creado correctamente");

        when(admCiiuService.insert(any(AdmCiiuFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = ciiuController.insert(formDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Creado correctamente", response.getDatas());
    }

    @Test
    void testUpdateByPrimaryKey() {
        AdmCiiuFormDTO formDTO = new AdmCiiuFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Actualizado correctamente");

        when(admCiiuService.updateByPrimaryKey(any(AdmCiiuFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = ciiuController.updateByPrimaryKey(formDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Actualizado correctamente", response.getDatas());
    }

    @Test
    void testEliminarPorId() {
        String codCiiu = "123";
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Eliminado correctamente");

        when(admCiiuService.deleteByPrimaryKey(codCiiu)).thenReturn(messageDTO);

        ResponseEntity<String> response = ciiuController.eliminarPorId(codCiiu);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Eliminado correctamente", response.getDatas());
    }

    @Test
    void testSelectAll(){
        List<AdmCiiuDTO> ciiuList = new ArrayList<>();
        ciiuList.add(new AdmCiiuDTO());

        when(admCiiuService.selectAll()).thenReturn(ciiuList);

        ResponseEntity<List<AdmCiiuDTO>> response = ciiuController.selectAll();

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    void testListarFiltro(){
        AdmCiiuSearchDTO searchDTO = new AdmCiiuSearchDTO();
        PageInfo<AdmCiiuDTO> pageInfo = new PageInfo<>(new ArrayList<>());

        when(admCiiuService.listarFiltro(any(AdmCiiuSearchDTO.class))).thenReturn(pageInfo);

        ResponseEntity<PageInfo<AdmCiiuDTO>> response = ciiuController.listarFiltro(searchDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals(0, response.getDatas().getList().size());
    }
}
