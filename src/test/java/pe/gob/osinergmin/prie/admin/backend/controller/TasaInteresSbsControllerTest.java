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
import pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres.TasaIntereDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres.TasaInteresFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres.TasaInteresResult;
import pe.gob.osinergmin.prie.admin.backend.dto.tasaInteres.TasaInteresSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTasaInteresSbsService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TasaInteresSbsControllerTest {

    @InjectMocks
    private TasaInteresSbsController tasaInteresSbsController;

    @Mock
    private AdmTasaInteresSbsService admTasaInteresSbsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() throws Exception {
        TasaInteresFormDTO formDTO = new TasaInteresFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Creado correctamente");

        when(admTasaInteresSbsService.insert(any(TasaInteresFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = tasaInteresSbsController.insert(formDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Creado correctamente", response.getDatas());
    }

    @Test
    void testUpdateTasaDiaria() throws Exception {
        TasaInteresFormDTO formDTO = new TasaInteresFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Actualizado correctamente");

        when(admTasaInteresSbsService.updateTasaDiaria(any(TasaInteresFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = tasaInteresSbsController.updateTasaDiaria(formDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Actualizado correctamente", response.getDatas());
    }

    @Test
    void testDeleteByTasaDiaria() throws Exception {
        TasaInteresFormDTO formDTO = new TasaInteresFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Eliminado correctamente");

        when(admTasaInteresSbsService.deleteByTasaDiaria(any(TasaInteresFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = tasaInteresSbsController.deleteByTasaDiaria(formDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Eliminado correctamente", response.getDatas());
    }

    @Test
    void testListarTasaInteres() throws Exception {
        List<TasaIntereDTO> tasaInteresList = new ArrayList<>();
        tasaInteresList.add(new TasaIntereDTO());

        when(admTasaInteresSbsService.listarTasaInteres()).thenReturn(tasaInteresList);

        ResponseEntity<List<TasaIntereDTO>> response = tasaInteresSbsController.listarTasaInteres();

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    void testListarConFiltroEstado() throws Exception {
        TasaInteresSearchDTO searchDTO = new TasaInteresSearchDTO();
        PageInfo<TasaInteresResult> pageInfo = new PageInfo<>(new ArrayList<>());

        when(admTasaInteresSbsService.listarConFiltroEstado(any(TasaInteresSearchDTO.class))).thenReturn(pageInfo);

        ResponseEntity<PageInfo<TasaInteresResult>> response = tasaInteresSbsController.listarConFiltroEstado(searchDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals(0, response.getDatas().getList().size());
    }
}
