package pe.gob.osinergmin.prie.admin.backend.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmProcEmpresa;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procEmpresa.AdmProcEmpresaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procEmpresa.AdmProcEmpresaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procEmpresa.AdmProcEmpresaResultDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmProcEmpresaMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class AdmProcEmpresaServiceImplTest {

    @InjectMocks
    private AdmProcEmpresaServiceImpl admProcEmpresaService;

    @Mock
    private AdmProcEmpresaMapper admProcEmpresaMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        AdmProcEmpresaFormDTO formDTO = new AdmProcEmpresaFormDTO();
        formDTO.setIdProcEmpresa(1);
        formDTO.setCodEmpresa("EMP001");
        formDTO.setCodTipoEmpresa("TE001");
        formDTO.setCodProcSupervision("PS001");
        formDTO.setCodFuncionProcSuperv("FS001");

        when(admProcEmpresaMapper.selectByPrimaryKey(formDTO.getIdProcEmpresa())).thenReturn(null);

        MessageDTO response = admProcEmpresaService.insert(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("OK", response.getMessage());
        verify(admProcEmpresaMapper, times(1)).insert(any(AdmProcEmpresa.class));
    }

    @Test
    void testInsert_AlreadyExists() {
        AdmProcEmpresaFormDTO formDTO = new AdmProcEmpresaFormDTO();
        formDTO.setIdProcEmpresa(1);

        when(admProcEmpresaMapper.selectByPrimaryKey(formDTO.getIdProcEmpresa())).thenReturn(new AdmProcEmpresa());

        MessageDTO response = admProcEmpresaService.insert(formDTO);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("Ya existe el proceso Empresa", response.getMessage());
        verify(admProcEmpresaMapper, never()).insert(any(AdmProcEmpresa.class));
    }

    @Test
    void testUpdateByPrimaryKey() {
        AdmProcEmpresaFormDTO formDTO = new AdmProcEmpresaFormDTO();
        formDTO.setIdProcEmpresa(1);
        formDTO.setCodEmpresa("EMP001");
        formDTO.setCodTipoEmpresa("TE001");
        formDTO.setCodProcSupervision("PS001");
        formDTO.setCodFuncionProcSuperv("FS001");

        when(admProcEmpresaMapper.selectByPrimaryKey(formDTO.getIdProcEmpresa())).thenReturn(new AdmProcEmpresa());

        MessageDTO response = admProcEmpresaService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("OK", response.getMessage());
        verify(admProcEmpresaMapper, times(1)).updateByPrimaryKey(any(AdmProcEmpresa.class));
    }

    @Test
    void testUpdateByPrimaryKey_NotFound() {
        AdmProcEmpresaFormDTO formDTO = new AdmProcEmpresaFormDTO();
        formDTO.setIdProcEmpresa(1);

        when(admProcEmpresaMapper.selectByPrimaryKey(formDTO.getIdProcEmpresa())).thenReturn(null);

        MessageDTO response = admProcEmpresaService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("No se Actualizo", response.getMessage());
        verify(admProcEmpresaMapper, never()).updateByPrimaryKey(any(AdmProcEmpresa.class));
    }

    @Test
    void testDeleteByPrimaryKey() {
        int idProcEmpresa = 1;

        when(admProcEmpresaMapper.selectByPrimaryKey(idProcEmpresa)).thenReturn(new AdmProcEmpresa());

        MessageDTO response = admProcEmpresaService.deleteByPrimaryKey(idProcEmpresa);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("OK", response.getMessage());
        verify(admProcEmpresaMapper, times(1)).deleteByPrimaryKey(idProcEmpresa);
    }

    @Test
    void testDeleteByPrimaryKey_NotFound() {
        int idProcEmpresa = 1;

        when(admProcEmpresaMapper.selectByPrimaryKey(idProcEmpresa)).thenReturn(null);

        MessageDTO response = admProcEmpresaService.deleteByPrimaryKey(idProcEmpresa);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("El Codigo de la empresa no existe", response.getMessage());
        verify(admProcEmpresaMapper, never()).deleteByPrimaryKey(idProcEmpresa);
    }

//    @Test
//    void testSelectAll() {
//        List<AdmProcEmpresaDTO> lista = new ArrayList<>();
//        lista.add(new AdmProcEmpresaDTO());
//
//        when(admProcEmpresaMapper.selectAll()).thenReturn(lista);
//
//        List<AdmProcEmpresaDTO> response = admProcEmpresaService.selectAll();
//
//        assertNotNull(response);
//        assertEquals(1, response.size());
//        verify(admProcEmpresaMapper, times(1)).selectAll();
//    }

    @Test
    void testListarCreacionEmpresa() {
        List<AdmProcEmpresaResultDTO> lista = new ArrayList<>();
        lista.add(new AdmProcEmpresaResultDTO());

        when(admProcEmpresaMapper.listarCreacionEmpresa()).thenReturn(lista);

        List<AdmProcEmpresaResultDTO> response = admProcEmpresaService.listarCreacionEmpresa();

        assertNotNull(response);
        assertEquals(1, response.size());
        verify(admProcEmpresaMapper, times(1)).listarCreacionEmpresa();
    }
}
