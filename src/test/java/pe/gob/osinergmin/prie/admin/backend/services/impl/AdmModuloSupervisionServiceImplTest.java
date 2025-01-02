package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmModuloSupervision;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.moduloSupervision.AdmModuloSupervisionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.moduloSupervision.AdmModuloSupervisionFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.moduloSupervision.AdmModuloSupervisionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmModuloSupervisionMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdmModuloSupervisionServiceImplTest {

    @Mock
    private AdmModuloSupervisionMapper admModuloSupervisionMapper;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private AdmModuloSupervisionServiceImpl admModuloSupervisionService;

    private AdmModuloSupervisionFormDTO moduloFormDTO;
    private AdmModuloSupervision modulo;

    @BeforeEach
    void setUp() {
        moduloFormDTO = new AdmModuloSupervisionFormDTO();
        moduloFormDTO.setCoModulo("MOD001");
        moduloFormDTO.setDeModulo("Módulo de Prueba");
        moduloFormDTO.setDeCorta("Prueba");
        moduloFormDTO.setEstado("A");

        modulo = new AdmModuloSupervision();
        modulo.setCoModulo("MOD001");
        modulo.setDeModulo("Módulo de Prueba");
        modulo.setDeCorta("Prueba");
        modulo.setEstado("A");
    }

    @Test
    void testInsert_Success() {
        when(admModuloSupervisionMapper.selectByPrimaryKey(anyString())).thenReturn(null);
        when(admModuloSupervisionMapper.insert(any(AdmModuloSupervision.class))).thenReturn(1);
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        MessageDTO result = admModuloSupervisionService.insert(moduloFormDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Módulo insertado correctamente.", result.getMessage());
        verify(admModuloSupervisionMapper, times(1)).insert(any(AdmModuloSupervision.class));
    }

    @Test
    void testInsert_Failure_ModuloExists() {
        when(admModuloSupervisionMapper.selectByPrimaryKey(anyString())).thenReturn(modulo);

        MessageDTO result = admModuloSupervisionService.insert(moduloFormDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El módulo ya existe.", result.getMessage());
    }

    @Test
    void testUpdateByPrimaryKey_Success() {
        when(admModuloSupervisionMapper.selectByPrimaryKey(anyString())).thenReturn(modulo);
        when(admModuloSupervisionMapper.updateByPrimaryKeySelective(any(AdmModuloSupervision.class))).thenReturn(1);
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        MessageDTO result = admModuloSupervisionService.updateByPrimaryKey(moduloFormDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Módulo actualizado correctamente.", result.getMessage());
        verify(admModuloSupervisionMapper, times(1)).updateByPrimaryKeySelective(any(AdmModuloSupervision.class));
    }

    @Test
    void testUpdateByPrimaryKey_Failure_ModuloNotFound() {
        when(admModuloSupervisionMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admModuloSupervisionService.updateByPrimaryKey(moduloFormDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("No se encontró el módulo para actualizar.", result.getMessage());
    }

    @Test
    void testDeleteByPrimaryKey_Success() {
        when(admModuloSupervisionMapper.selectByPrimaryKey(anyString())).thenReturn(modulo);
        when(admModuloSupervisionMapper.deleteByPrimaryKey(anyString())).thenReturn(1);

        MessageDTO result = admModuloSupervisionService.deleteByPrimaryKey("MOD001");

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Módulo eliminado correctamente.", result.getMessage());
        verify(admModuloSupervisionMapper, times(1)).deleteByPrimaryKey(anyString());
    }

    @Test
    void testDeleteByPrimaryKey_Failure_ModuloNotFound() {
        when(admModuloSupervisionMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admModuloSupervisionService.deleteByPrimaryKey("MOD001");

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("No se encontró el módulo para eliminar.", result.getMessage());
    }

    @Test
    void testFiltrar_Success() {
        List<AdmModuloSupervisionDTO> modulos = new ArrayList<>();
        modulos.add(new AdmModuloSupervisionDTO());

        when(admModuloSupervisionMapper.filtrar(any(AdmModuloSupervisionSearchDTO.class))).thenReturn(modulos);

        AdmModuloSupervisionSearchDTO searchDTO = new AdmModuloSupervisionSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);

        PageInfo<AdmModuloSupervisionDTO> result = admModuloSupervisionService.filtrar(searchDTO);

        assertNotNull(result);
        assertEquals(1, result.getList().size());
    }
}
