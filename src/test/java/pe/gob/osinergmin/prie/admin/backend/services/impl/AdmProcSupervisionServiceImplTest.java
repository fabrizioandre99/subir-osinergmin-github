package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmProcSupervision;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.funcionProcSuperv.FuncionFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procSupervision.AdmProcSupervisionFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procSupervision.AdmProcSupervisionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procSupervision.AdmProcSupervisionDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmFuncionProcSupervMapper;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmProcSectorTipicoMapper;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmProcSupervisionMapper;
import pe.gob.osinergmin.prie.admin.backend.services.impl.AdmProcSupervisionServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdmProcSupervisionServiceImplTest {

    @Mock
    private AdmProcSupervisionMapper admProcSupervisionMapper;

    @Mock
    private AdmFuncionProcSupervMapper admFuncionProcSupervMapper;

    @Mock
    private AdmProcSectorTipicoMapper admProcSectorTipicoMapper;

    @InjectMocks
    private AdmProcSupervisionServiceImpl admProcSupervisionService;

    private AdmProcSupervisionFormDTO formDTO;

    @BeforeEach
    void setUp() {
        formDTO = new AdmProcSupervisionFormDTO();
        formDTO.setCodProcSupervision("SUP001");
        formDTO.setNomProcSupervision("Supervision Test");
        formDTO.setAbrevProcSupervision("SUP");
    }

    @Test
    void testInsert_Success() {
        when(admProcSupervisionMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admProcSupervisionService.insert(formDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("OK", result.getMessage());
        verify(admProcSupervisionMapper, times(1)).insert(any(AdmProcSupervision.class));
    }

//    @Test
//    void testInsert_Failure_CodProcSupervisionLength() {
//        formDTO.setCodProcSupervision("SUP");
//
//        MessageDTO result = admProcSupervisionService.insert(formDTO);
//
//        assertEquals(Constantes.SUCCES, result.getStatus());
//        assertEquals("El código de supervisión debe tener exactamente 6 caracteres.", result.getMessage());
//    }

    @Test
    void testInsert_Failure_NameTooLong() {
        formDTO.setNomProcSupervision("A".repeat(101));

        MessageDTO result = admProcSupervisionService.insert(formDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El nombre de supervisión no debe exceder los 100 caracteres.", result.getMessage());
    }

    @Test
    void testInsert_Failure_AbrevTooLong() {
        formDTO.setAbrevProcSupervision("A".repeat(21));

        MessageDTO result = admProcSupervisionService.insert(formDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("La abreviatura de supervisión no debe exceder los 20 caracteres.", result.getMessage());
    }

    @Test
    void testDeleteByPrimaryKey_Success() {
        when(admProcSupervisionMapper.selectByPrimaryKey(anyString())).thenReturn(new AdmProcSupervision());

        MessageDTO result = admProcSupervisionService.deleteByPrimaryKey("SUP001");

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("OK", result.getMessage());
        verify(admProcSupervisionMapper, times(1)).deleteByPrimaryKey("SUP001");
        verify(admProcSectorTipicoMapper, times(1)).borrarPorCodProcesoSupervisor("SUP001");
        verify(admFuncionProcSupervMapper, times(1)).eliminar("SUP001");
    }

    @Test
    void testDeleteByPrimaryKey_Failure_NotFound() {
        when(admProcSupervisionMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admProcSupervisionService.deleteByPrimaryKey("SUP001");

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El Código de la supervisión no existe", result.getMessage());
        verify(admProcSupervisionMapper, never()).deleteByPrimaryKey("SUP001");
        verify(admFuncionProcSupervMapper, never()).eliminar("SUP001");
    }

    @Test
    void testUpdateByPrimaryKey_Success() {
        when(admProcSupervisionMapper.selectByPrimaryKey(anyString())).thenReturn(new AdmProcSupervision());

        MessageDTO result = admProcSupervisionService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("OK", result.getMessage());
        verify(admProcSupervisionMapper, times(1)).updateByPrimaryKey(any(AdmProcSupervision.class));
    }

//    @Test
//    void testUpdateByPrimaryKey_Failure_NotFound() {
//        when(admProcSupervisionMapper.selectByPrimaryKey(anyString())).thenReturn(null);
//
//        MessageDTO result = admProcSupervisionService.updateByPrimaryKey(formDTO);
//
//        assertEquals(Constantes.ERROR, result.getStatus());
//        assertEquals("No se Actualizo", result.getMessage());
//    }

    @Test
    void testFiltrar_InvalidOrder() {
        AdmProcSupervisionSearchDTO searchDTO = new AdmProcSupervisionSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);
        searchDTO.setSort("codProcSupervision");
        searchDTO.setOrder("invalid");

        List<AdmProcSupervisionDTO> resultList = new ArrayList<>();
        when(admProcSupervisionMapper.filtrar(any(AdmProcSupervisionSearchDTO.class))).thenReturn(resultList);

        PageInfo<AdmProcSupervisionDTO> result = admProcSupervisionService.filtrar(searchDTO);

        assertNotNull(result);
        assertEquals(0, result.getList().size());
        verify(admProcSupervisionMapper, times(1)).filtrar(any(AdmProcSupervisionSearchDTO.class));
    }
}
