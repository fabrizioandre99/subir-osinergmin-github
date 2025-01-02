package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmSistemaTransmision;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sistemaTransmision.AdmSistemaTransmisionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sistemaTransmision.AdmSistemaTransmisionFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.sistemaTransmision.AdmSistemaTransmisionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmSistemaTransmisionMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AdmSistemaTransmisionServiceImplTest {

    @InjectMocks
    private AdmSistemaTransmisionServiceImpl admSistemaTransmisionService;

    @Mock
    private AdmSistemaTransmisionMapper admSistemaTransmisionMapper;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteByPrimaryKey_Existe() {
        when(admSistemaTransmisionMapper.selectByPrimaryKey(1)).thenReturn(new AdmSistemaTransmision());

        MessageDTO response = admSistemaTransmisionService.deleteByPrimaryKey(1);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("OK", response.getMessage());
        verify(admSistemaTransmisionMapper, times(1)).deleteByPrimaryKey(1);
    }

    @Test
    void testDeleteByPrimaryKey_NoExiste() {
        when(admSistemaTransmisionMapper.selectByPrimaryKey(1)).thenReturn(null);

        MessageDTO response = admSistemaTransmisionService.deleteByPrimaryKey(1);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("El id sistema transmicion no existe", response.getMessage());
    }

    @Test
    void testInsert_Exitoso() {
        AdmSistemaTransmisionFormdDTO formDTO = new AdmSistemaTransmisionFormdDTO();
        formDTO.setCodSisTrans("SIS001");
        formDTO.setNomSisTrans("Sistema Transmisión 1");

        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        MessageDTO response = admSistemaTransmisionService.insert(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("Registro insertado correctamente.", response.getMessage());
        verify(admSistemaTransmisionMapper, times(1)).insert(any(AdmSistemaTransmision.class));
    }

    @Test
    void testInsert_ErrorCodigoVacio() {
        AdmSistemaTransmisionFormdDTO formDTO = new AdmSistemaTransmisionFormdDTO();
        formDTO.setCodSisTrans("");

        MessageDTO response = admSistemaTransmisionService.insert(formDTO);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("El código del sistema de transmisión no puede estar vacío.", response.getMessage());
    }

    @Test
    void testUpdateByPrimaryKey_Exitoso() {
        AdmSistemaTransmisionFormdDTO formDTO = new AdmSistemaTransmisionFormdDTO();
        formDTO.setIdSisTrans(1);
        formDTO.setCodSisTrans("SIS001");
        formDTO.setNomSisTrans("Sistema Transmisión Actualizado");

        when(admSistemaTransmisionMapper.selectByPrimaryKey(1)).thenReturn(new AdmSistemaTransmision());

        MessageDTO response = admSistemaTransmisionService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("Registro actualizado correctamente.", response.getMessage());
        verify(admSistemaTransmisionMapper, times(1)).updateByPrimaryKey(any(AdmSistemaTransmision.class));
    }

    @Test
    void testUpdateByPrimaryKey_NoExiste() {
        AdmSistemaTransmisionFormdDTO formDTO = new AdmSistemaTransmisionFormdDTO();
        formDTO.setIdSisTrans(1);

        when(admSistemaTransmisionMapper.selectByPrimaryKey(1)).thenReturn(null);

        MessageDTO response = admSistemaTransmisionService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("No se pudo actualizar, el registro no existe.", response.getMessage());
    }

    @Test
    void testSelectAll() {
        List<AdmSistemaTransmisionDTO> sistemas = new ArrayList<>();
        sistemas.add(new AdmSistemaTransmisionDTO());

        when(admSistemaTransmisionMapper.selectAll()).thenReturn(sistemas);

        List<AdmSistemaTransmisionDTO> response = admSistemaTransmisionService.selectAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        verify(admSistemaTransmisionMapper, times(1)).selectAll();
    }

    @Test
    void testFiltrar() {
        AdmSistemaTransmisionSearchDTO searchDTO = new AdmSistemaTransmisionSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);

        List<AdmSistemaTransmisionDTO> sistemas = new ArrayList<>();
        PageInfo<AdmSistemaTransmisionDTO> pageInfo = new PageInfo<>(sistemas);

        when(admSistemaTransmisionMapper.filrar(any(AdmSistemaTransmisionSearchDTO.class))).thenReturn(sistemas);

        PageInfo<AdmSistemaTransmisionDTO> response = admSistemaTransmisionService.filrar(searchDTO);

        assertNotNull(response);
        assertEquals(0, response.getList().size());
        verify(admSistemaTransmisionMapper, times(1)).filrar(any(AdmSistemaTransmisionSearchDTO.class));
    }
}
