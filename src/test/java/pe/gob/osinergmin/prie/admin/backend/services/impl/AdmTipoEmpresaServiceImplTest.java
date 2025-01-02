package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoEmpresa.TipoEmpresaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoEmpresa.TipoEmpresaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoEmpresa.TipoEmpresaSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmTipoEmpresaMapper;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTipoEmpresa;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AdmTipoEmpresaServiceImplTest {

    @Mock
    private AdmTipoEmpresaMapper admTipoEmpresaMapper;

    @InjectMocks
    private AdmTipoEmpresaServiceImpl admTipoEmpresaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteByPrimaryKey_Exitoso() {
        when(admTipoEmpresaMapper.selectByPrimaryKey(anyString())).thenReturn(new AdmTipoEmpresa());
        when(admTipoEmpresaMapper.deleteByPrimaryKey(anyString())).thenReturn(1);

        MessageDTO result = admTipoEmpresaService.deleteByPrimaryKey("EMP001");

        assertEquals("1", result.getStatus());
        verify(admTipoEmpresaMapper, times(1)).deleteByPrimaryKey(anyString());
    }

    @Test
    public void testDeleteByPrimaryKey_NoExiste() {
        when(admTipoEmpresaMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admTipoEmpresaService.deleteByPrimaryKey("EMP001");

        assertEquals("0", result.getStatus());
        assertEquals("No se Elimino el Tipo de empresa", result.getMessage());
    }

    @Test
    public void testInsert_Exitoso() {
        when(admTipoEmpresaMapper.selectByPrimaryKey(anyString())).thenReturn(null);
        when(admTipoEmpresaMapper.insert(any(AdmTipoEmpresa.class))).thenReturn(1);

        TipoEmpresaFormDTO formDTO = new TipoEmpresaFormDTO();
        formDTO.setCodTipoEmpresa("EMP002");
        formDTO.setDscTipoEmpresa("Empresa Test");

        MessageDTO result = admTipoEmpresaService.insert(formDTO);

        assertEquals("1", result.getStatus());
        verify(admTipoEmpresaMapper, times(1)).insert(any(AdmTipoEmpresa.class));
    }

    @Test
    public void testInsert_YaExiste() {
        when(admTipoEmpresaMapper.selectByPrimaryKey(anyString())).thenReturn(new AdmTipoEmpresa());

        TipoEmpresaFormDTO formDTO = new TipoEmpresaFormDTO();
        formDTO.setCodTipoEmpresa("EMP002");
        formDTO.setDscTipoEmpresa("Empresa Test");

        MessageDTO result = admTipoEmpresaService.insert(formDTO);

        assertEquals("0", result.getStatus());
        assertEquals("Ya existe el Codigo Empresa", result.getMessage());
    }

    @Test
    public void testUpdateByPrimaryKey_Exitoso() {
        when(admTipoEmpresaMapper.selectByPrimaryKey(anyString())).thenReturn(new AdmTipoEmpresa());
        when(admTipoEmpresaMapper.updateByPrimaryKey(any(AdmTipoEmpresa.class))).thenReturn(1);

        TipoEmpresaFormDTO formDTO = new TipoEmpresaFormDTO();
        formDTO.setCodTipoEmpresa("EMP002");
        formDTO.setDscTipoEmpresa("Empresa Actualizada");
        formDTO.setEstado("1");

        MessageDTO result = admTipoEmpresaService.updateByPrimaryKey(formDTO);

        assertEquals("1", result.getStatus());
        verify(admTipoEmpresaMapper, times(1)).updateByPrimaryKey(any(AdmTipoEmpresa.class));
    }

    @Test
    public void testUpdateByPrimaryKey_NoExiste() {
        when(admTipoEmpresaMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        TipoEmpresaFormDTO formDTO = new TipoEmpresaFormDTO();
        formDTO.setCodTipoEmpresa("EMP002");
        formDTO.setDscTipoEmpresa("Empresa Actualizada");
        formDTO.setEstado("1");

        MessageDTO result = admTipoEmpresaService.updateByPrimaryKey(formDTO);

        assertEquals("0", result.getStatus());
        assertEquals("No se Actualizo el Tipo de empresa", result.getMessage());
    }

    @Test
    public void testSelectAll() {
        List<TipoEmpresaDTO> empresas = new ArrayList<>();
        when(admTipoEmpresaMapper.selectAll()).thenReturn(empresas);

        List<TipoEmpresaDTO> result = admTipoEmpresaService.selectAll();

        assertEquals(empresas, result);
        verify(admTipoEmpresaMapper, times(1)).selectAll();
    }

    @Test
    public void testListaFiltro() {
        TipoEmpresaSearchDTO searchDTO = new TipoEmpresaSearchDTO("EMP001", "Empresa Test");
        searchDTO.setPage(1);
        searchDTO.setSize(10);

        List<TipoEmpresaDTO> empresas = new ArrayList<>();
        when(admTipoEmpresaMapper.listaFiltro(searchDTO)).thenReturn(empresas);

        PageInfo<TipoEmpresaDTO> result = admTipoEmpresaService.ListaFiltro(searchDTO);

        assertNotNull(result);
        assertEquals(empresas, result.getList());
        verify(admTipoEmpresaMapper, times(1)).listaFiltro(searchDTO);
    }
}
