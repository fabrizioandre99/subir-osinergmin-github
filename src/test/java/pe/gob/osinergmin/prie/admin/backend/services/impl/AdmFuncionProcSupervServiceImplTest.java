package pe.gob.osinergmin.prie.admin.backend.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmFuncionProcSuperv;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.funcionProcSuperv.AdmFuncionProcSupervDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.funcionProcSuperv.AdmFuncionProcSupervFormDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmFuncionProcSupervMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AdmFuncionProcSupervServiceImplTest {

    @InjectMocks
    private AdmFuncionProcSupervServiceImpl admFuncionProcSupervService;

    @Mock
    private AdmFuncionProcSupervMapper admFuncionProcSupervMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteByPrimaryKey() {
        String codProcSupervision = "PS001";
        String codFuncionProcSuperv = "FS001";
        AdmFuncionProcSuperv funcion = new AdmFuncionProcSuperv();
        funcion.setCodProcSupervision(codProcSupervision);
        funcion.setCodFuncionProcSuperv(codFuncionProcSuperv);

        when(admFuncionProcSupervMapper.selectByPrimaryKey(codProcSupervision, codFuncionProcSuperv)).thenReturn(funcion);

        MessageDTO response = admFuncionProcSupervService.deleteByPrimaryKey(codProcSupervision, codFuncionProcSuperv);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("OK", response.getMessage());
        verify(admFuncionProcSupervMapper, times(1)).deleteByPrimaryKey(codProcSupervision, codFuncionProcSuperv);
    }

    @Test
    void testInsert() {
        AdmFuncionProcSupervFormDTO formDTO = new AdmFuncionProcSupervFormDTO();
        formDTO.setCodProcSupervision("PS001");
        formDTO.setCodFuncionProcSuperv("FS001");
        formDTO.setDscFuncionProcSuperv("Descripción de prueba");

        when(admFuncionProcSupervMapper.selectByPrimaryKey(formDTO.getCodProcSupervision(), formDTO.getCodFuncionProcSuperv())).thenReturn(null);
        when(admFuncionProcSupervMapper.existeCodFuncion(formDTO.getCodFuncionProcSuperv())).thenReturn(null);

        MessageDTO response = admFuncionProcSupervService.insert(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("OK", response.getMessage());
        verify(admFuncionProcSupervMapper, times(1)).insert(any(AdmFuncionProcSuperv.class));
    }

    @Test
    void testUpdateByPrimaryKey() {
        AdmFuncionProcSupervFormDTO formDTO = new AdmFuncionProcSupervFormDTO();
        formDTO.setCodProcSupervision("PS001");
        formDTO.setCodFuncionProcSuperv("FS001");
        formDTO.setDscFuncionProcSuperv("Descripción actualizada");

        AdmFuncionProcSuperv mockFuncion = new AdmFuncionProcSuperv();
        mockFuncion.setCodProcSupervision("PS001");
        mockFuncion.setCodFuncionProcSuperv("FS001");

        when(admFuncionProcSupervMapper.selectByPrimaryKey(formDTO.getCodProcSupervision(), formDTO.getCodFuncionProcSuperv()))
                .thenReturn(mockFuncion);

        MessageDTO response = admFuncionProcSupervService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("OK", response.getMessage());
        verify(admFuncionProcSupervMapper, times(1)).updateByPrimaryKey(any(AdmFuncionProcSuperv.class));
    }


    @Test
    void testSelectByPrimaryKey() {
        String codProcSupervision = "PS001";
        String codFuncionProcSuperv = "FS001";
        AdmFuncionProcSuperv funcion = new AdmFuncionProcSuperv();

        when(admFuncionProcSupervMapper.selectByPrimaryKey(codProcSupervision, codFuncionProcSuperv)).thenReturn(funcion);

        AdmFuncionProcSuperv response = admFuncionProcSupervService.selectByPrimaryKey(codProcSupervision, codFuncionProcSuperv);

        assertNotNull(response);
        verify(admFuncionProcSupervMapper, times(1)).selectByPrimaryKey(codProcSupervision, codFuncionProcSuperv);
    }

    @Test
    void testListar() {
        List<AdmFuncionProcSupervDTO> funciones = new ArrayList<>();
        funciones.add(new AdmFuncionProcSupervDTO());

        when(admFuncionProcSupervMapper.listar()).thenReturn(funciones);

        List<AdmFuncionProcSupervDTO> response = admFuncionProcSupervService.listar();

        assertNotNull(response);
        assertEquals(1, response.size());
        verify(admFuncionProcSupervMapper, times(1)).listar();
    }
}
