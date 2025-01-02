package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTipoSistTrans;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoSistTrans.*;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmTipoSistTransMapper;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmTransformadorMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class AdmTipoSistTransServiceImplTest {

    @InjectMocks
    private AdmTipoSistTransServiceImpl admTipoSistTransService;

    @Mock
    private AdmTipoSistTransMapper admTipoSistTransMapper;

    @Mock
    private AdmTransformadorMapper admTransformadorMapper;

    @Mock
    private HttpServletRequest request;

    @Mock
    private IpUtils ipUtils;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteByPrimaryKey_Success() {
        Integer idTipoSisTrans = 1;

        // Simula la existencia del registro en el mapper
        AdmTipoSistTrans tipoSisTrans = new AdmTipoSistTrans();
        when(admTipoSistTransMapper.selectByPrimaryKey(idTipoSisTrans)).thenReturn(tipoSisTrans);

        // Simula la ejecución de métodos void en los mappers
        doAnswer(invocation -> null).when(admTransformadorMapper).deleteByIdTipoSisTrans(idTipoSisTrans);
        doAnswer(invocation -> null).when(admTipoSistTransMapper).deleteByPrimaryKey(idTipoSisTrans);

        // Llama al método deleteByPrimaryKey del servicio
        MessageDTO response = admTipoSistTransService.deleteByPrimaryKey(idTipoSisTrans);

        // Verifica la respuesta
        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("Registro eliminado correctamente.", response.getMessage());

        // Verifica que los métodos se llamaron una vez
        verify(admTransformadorMapper, times(1)).deleteByIdTipoSisTrans(idTipoSisTrans);
        verify(admTipoSistTransMapper, times(1)).deleteByPrimaryKey(idTipoSisTrans);
    }

    @Test
    void testDeleteByPrimaryKey_NotFound() {
        Integer idTipoSisTrans = 1;
        when(admTipoSistTransMapper.selectByPrimaryKey(idTipoSisTrans)).thenReturn(null);

        MessageDTO response = admTipoSistTransService.deleteByPrimaryKey(idTipoSisTrans);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("El ID del Tipo de Sistema de Transmisión no existe.", response.getMessage());
        verify(admTipoSistTransMapper, never()).deleteByPrimaryKey(idTipoSisTrans);
    }

    @Test
    void testDeleteByPrimaryKey_NullId() {
        MessageDTO response = admTipoSistTransService.deleteByPrimaryKey(null);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("El ID del Tipo de Sistema de Transmisión no puede ser nulo.", response.getMessage());
        verify(admTipoSistTransMapper, never()).deleteByPrimaryKey(any());
    }

    @Test
    void testInsert_Success() {
        AdmTipoSistTransFormDTO formDTO = new AdmTipoSistTransFormDTO();
        formDTO.setIdTipoSisTrans(1);
        formDTO.setCodTipoSisTrans("COD001");
        formDTO.setTipSisTrans("Tipo Sistema Transmisión");

        when(admTipoSistTransMapper.selectByPrimaryKey(formDTO.getIdTipoSisTrans())).thenReturn(null);
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        MessageDTO response = admTipoSistTransService.insert(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("Registro insertado correctamente.", response.getMessage());
        verify(admTipoSistTransMapper, times(1)).insert(any(AdmTipoSistTrans.class));
    }

    @Test
    void testInsert_AlreadyExists() {
        AdmTipoSistTransFormDTO formDTO = new AdmTipoSistTransFormDTO();
        formDTO.setIdTipoSisTrans(1);

        when(admTipoSistTransMapper.selectByPrimaryKey(formDTO.getIdTipoSisTrans())).thenReturn(new AdmTipoSistTrans());

        MessageDTO response = admTipoSistTransService.insert(formDTO);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("Ya existe el Tipo de Sistema de Transmisión.", response.getMessage());
        verify(admTipoSistTransMapper, never()).insert(any(AdmTipoSistTrans.class));
    }

    @Test
    void testInsert_MissingCodTipoSisTrans() {
        AdmTipoSistTransFormDTO formDTO = new AdmTipoSistTransFormDTO();
        formDTO.setIdTipoSisTrans(1);
        formDTO.setTipSisTrans("Tipo Sistema Transmisión");

        when(admTipoSistTransMapper.selectByPrimaryKey(formDTO.getIdTipoSisTrans())).thenReturn(null);

        MessageDTO response = admTipoSistTransService.insert(formDTO);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("El código del Tipo de Sistema de Transmisión no puede estar vacío.", response.getMessage());
    }

    @Test
    void testInsert_MissingTipSisTrans() {
        AdmTipoSistTransFormDTO formDTO = new AdmTipoSistTransFormDTO();
        formDTO.setIdTipoSisTrans(1);
        formDTO.setCodTipoSisTrans("COD001");

        when(admTipoSistTransMapper.selectByPrimaryKey(formDTO.getIdTipoSisTrans())).thenReturn(null);

        MessageDTO response = admTipoSistTransService.insert(formDTO);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("La descripción del Tipo de Sistema de Transmisión no puede estar vacía.", response.getMessage());
    }

    @Test
    void testUpdateByPrimaryKey_Success() {
        AdmTipoSistTransFormDTO formDTO = new AdmTipoSistTransFormDTO();
        formDTO.setIdTipoSisTrans(1);
        formDTO.setCodTipoSisTrans("COD001");
        formDTO.setTipSisTrans("Tipo Sistema Transmisión Actualizado");
        formDTO.setEstado("1");

        when(admTipoSistTransMapper.selectByPrimaryKey(formDTO.getIdTipoSisTrans())).thenReturn(new AdmTipoSistTrans());
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        MessageDTO response = admTipoSistTransService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("Registro actualizado correctamente.", response.getMessage());
        verify(admTipoSistTransMapper, times(1)).updateByPrimaryKey(any(AdmTipoSistTrans.class));
    }

    @Test
    void testUpdateByPrimaryKey_NotFound() {
        AdmTipoSistTransFormDTO formDTO = new AdmTipoSistTransFormDTO();
        formDTO.setIdTipoSisTrans(1);

        when(admTipoSistTransMapper.selectByPrimaryKey(formDTO.getIdTipoSisTrans())).thenReturn(null);

        MessageDTO response = admTipoSistTransService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("No se pudo actualizar, el registro no existe.", response.getMessage());
        verify(admTipoSistTransMapper, never()).updateByPrimaryKey(any(AdmTipoSistTrans.class));
    }

    @Test
    void testUpdateByPrimaryKey_InvalidEstado() {
        AdmTipoSistTransFormDTO formDTO = new AdmTipoSistTransFormDTO();
        formDTO.setIdTipoSisTrans(1);
        formDTO.setCodTipoSisTrans("COD001");
        formDTO.setTipSisTrans("Tipo Sistema Transmisión");
        formDTO.setEstado("2");

        when(admTipoSistTransMapper.selectByPrimaryKey(formDTO.getIdTipoSisTrans())).thenReturn(new AdmTipoSistTrans());

        MessageDTO response = admTipoSistTransService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("El estado debe ser '1' (activo) o '0' (inactivo).", response.getMessage());
    }

    @Test
    void testSelectAll() {
        List<AdmTipoSistTransDTO> list = new ArrayList<>();
        list.add(new AdmTipoSistTransDTO());

        when(admTipoSistTransMapper.selectAll()).thenReturn(list);

        List<AdmTipoSistTransDTO> response = admTipoSistTransService.selectAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        verify(admTipoSistTransMapper, times(1)).selectAll();
    }

    @Test
    void testFiltrar() {
        AdmTipoSistTransSearchDTO searchDTO = new AdmTipoSistTransSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);

        List<AdmTipoSistTransDTO> list = new ArrayList<>();
        PageInfo<AdmTipoSistTransDTO> pageInfo = new PageInfo<>(list);

        when(admTipoSistTransMapper.filtrar(any(AdmTipoSistTransSearchDTO.class))).thenReturn(list);

        PageInfo<AdmTipoSistTransDTO> response = admTipoSistTransService.filtrar(searchDTO);

        assertNotNull(response);
        assertEquals(0, response.getList().size());
        verify(admTipoSistTransMapper, times(1)).filtrar(any(AdmTipoSistTransSearchDTO.class));
    }
}
