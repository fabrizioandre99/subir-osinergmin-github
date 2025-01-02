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
import pe.gob.osinergmin.prie.admin.backend.dto.admUit.AdmUitDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.admUit.AdmUitFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.admUit.AdmUitSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmUitService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AdmUitControllerTest {

    @InjectMocks
    private UitController admUitController;

    @Mock
    private AdmUitService admUitService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsert() throws Exception {
        AdmUitFormDTO formDTO = new AdmUitFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Creado correctamente");

        when(admUitService.insert(any(AdmUitFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = admUitController.insert(formDTO);

        // Ajusta el valor esperado al que está configurado en el servicio
        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Creado correctamente", response.getDatas());
    }

    @Test
    public void testUpdateByPrimaryKey() throws Exception {
        AdmUitFormDTO formDTO = new AdmUitFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Actualizado correctamente");

        when(admUitService.updateByPrimaryKey(any(AdmUitFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = admUitController.updateByPrimaryKey(formDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Actualizado correctamente", response.getDatas());
    }

    @Test
    public void testSelectAll() throws Exception {
        List<AdmUitDTO> uitList = new ArrayList<>();
        uitList.add(new AdmUitDTO());

        when(admUitService.selectAll()).thenReturn(uitList);

        ResponseEntity<List<AdmUitDTO>> response = admUitController.selectAll();

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    public void testListarFiltro() throws Exception {
        AdmUitSearchDTO searchDTO = new AdmUitSearchDTO();
        PageInfo<AdmUitDTO> pageInfo = new PageInfo<>(new ArrayList<>());

        when(admUitService.listarFiltro(any(AdmUitSearchDTO.class))).thenReturn(pageInfo);

        ResponseEntity<PageInfo<AdmUitDTO>> response = admUitController.listarTarifasFiltro(searchDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals(0, response.getDatas().getList().size());
    }
}
