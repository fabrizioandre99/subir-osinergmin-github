package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmEmpresa;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.centralGeneracion.AdmCentralGeneracionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.empresa.AdmEmpresaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.empresa.AdmEmpresaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.empresa.AdmEmpresaSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.*;

import java.net.InetAddress;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AdmEmpresaServiceImplTest {

    @InjectMocks
    private AdmEmpresaServiceImpl admEmpresaService;

    @Mock
    private AdmEmpresaMapper admEmpresaMapper;

    @Mock
    private AdmTransformadorMapper admTransformadorMapper;

    @Mock
    private AdmProcEmpresaMapper admProcEmpresaMapper;

    @Mock
    private AdmCentralGeneracionMapper admCentralGeneracionMapper;

    @Mock
    private AdmGrupoGeneracionMapper admGrupoGeneracionMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteByPrimaryKey_WithCentralsAndGroups() {
        String codEmpresa = "E123";
        AdmEmpresa empresa = new AdmEmpresa();
        when(admEmpresaMapper.selectByPrimaryKey(codEmpresa)).thenReturn(empresa);
        when(admTransformadorMapper.countByCodEmpresa(codEmpresa)).thenReturn(0);
        when(admProcEmpresaMapper.listarPorCodEmpresa(codEmpresa)).thenReturn(Collections.emptyList());

        AdmCentralGeneracionDTO central1 = new AdmCentralGeneracionDTO();
        central1.setCodCentralGeneracion("C001");
        AdmCentralGeneracionDTO central2 = new AdmCentralGeneracionDTO();
        central2.setCodCentralGeneracion("C002");
        List<AdmCentralGeneracionDTO> centrales = Arrays.asList(central1, central2);

        when(admCentralGeneracionMapper.selectByCodEmpresa(codEmpresa)).thenReturn(centrales);

        when(admGrupoGeneracionMapper.eliminarCodCentral("C001")).thenReturn(1);
        when(admGrupoGeneracionMapper.eliminarCodCentral("C002")).thenReturn(1);

        when(admCentralGeneracionMapper.deleteByPrimaryKey("C001")).thenReturn(1);
        when(admCentralGeneracionMapper.deleteByPrimaryKey("C002")).thenReturn(1);

        MessageDTO response = admEmpresaService.deleteByPrimaryKey(codEmpresa);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("Empresa eliminada correctamente", response.getMessage());

        verify(admEmpresaMapper, times(1)).deleteByPrimaryKey(codEmpresa);

        verify(admTransformadorMapper, times(1)).countByCodEmpresa(codEmpresa);
        verify(admTransformadorMapper, never()).deleteByCodEmpresa(anyString());

        verify(admProcEmpresaMapper, times(1)).listarPorCodEmpresa(codEmpresa);
        verify(admProcEmpresaMapper, never()).deleteByIdProcEmpresa(anyInt());

        verify(admCentralGeneracionMapper, times(1)).selectByCodEmpresa(codEmpresa);
        verify(admGrupoGeneracionMapper, times(1)).eliminarCodCentral("C001");
        verify(admGrupoGeneracionMapper, times(1)).eliminarCodCentral("C002");
        verify(admCentralGeneracionMapper, times(1)).deleteByPrimaryKey("C001");
        verify(admCentralGeneracionMapper, times(1)).deleteByPrimaryKey("C002");
    }


    @Test
    void testInsert() throws Exception {
        AdmEmpresaFormDTO formDTO = new AdmEmpresaFormDTO();
        formDTO.setCodEmpresa("E123");
        formDTO.setRuc("12345678901");

        when(admEmpresaMapper.selectByPrimaryKey(formDTO.getCodEmpresa())).thenReturn(null);
        when(admEmpresaMapper.existeRuc(formDTO.getRuc())).thenReturn(0);

        MessageDTO response = admEmpresaService.insert(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("OK", response.getMessage());
        verify(admEmpresaMapper, times(1)).insert(any(AdmEmpresa.class));
    }

    @Test
    void testUpdateByPrimaryKey() throws Exception {
        AdmEmpresaFormDTO formDTO = new AdmEmpresaFormDTO();
        formDTO.setCodEmpresa("E123");

        when(admEmpresaMapper.selectByPrimaryKey(formDTO.getCodEmpresa())).thenReturn(new AdmEmpresa());

        MessageDTO response = admEmpresaService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("OK", response.getMessage());
        verify(admEmpresaMapper, times(1)).updateByPrimaryKey(any(AdmEmpresa.class));
    }

    @Test
    void testSelectAll() {
        List<AdmEmpresaDTO> empresas = new ArrayList<>();
        empresas.add(new AdmEmpresaDTO());

        when(admEmpresaMapper.selectAll()).thenReturn(empresas);

        List<AdmEmpresaDTO> response = admEmpresaService.selectAll();

        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    void testListarFiltro() {
        AdmEmpresaSearchDTO searchDTO = new AdmEmpresaSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);
        List<AdmEmpresaDTO> empresaList = new ArrayList<>();

        when(admEmpresaMapper.ListarFiltro(any(AdmEmpresaSearchDTO.class))).thenReturn(empresaList);

        PageInfo<AdmEmpresaDTO> response = admEmpresaService.listarFiltro(searchDTO);

        assertNotNull(response);
        assertEquals(0, response.getList().size());
    }
}
