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
import pe.gob.osinergmin.prie.admin.backend.domain.AdmSuministroUsuario;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.suministroUsuario.AdmSuministroUsuarioDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.suministroUsuario.AdmSuministroUsuarioFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.suministroUsuario.AdmSuministroUsuarioSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmSuministroUsuarioMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdmSuministroUsuarioServiceImplTest {

    @Mock
    private AdmSuministroUsuarioMapper admSuministroUsuarioMapper;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private AdmSuministroUsuarioServiceImpl admSuministroUsuarioService;

    private AdmSuministroUsuarioFormDTO suministroUsuarioFormDTO;
    private AdmSuministroUsuario admSuministroUsuario;

    @BeforeEach
    void setUp() {
        suministroUsuarioFormDTO = new AdmSuministroUsuarioFormDTO();
        suministroUsuarioFormDTO.setCodSuministroUsuario("SUM001");
        suministroUsuarioFormDTO.setCodUsuarioLibre("USR001");
        suministroUsuarioFormDTO.setNombreUsuarioLibre("Usuario Libre");
        suministroUsuarioFormDTO.setDireccionSuministro("Av. Ejemplo 123");
        suministroUsuarioFormDTO.setNroSuministroEmp("EMP001");

        admSuministroUsuario = new AdmSuministroUsuario();
        admSuministroUsuario.setCodSuministroUsuario("SUM001");
        admSuministroUsuario.setCodUsuarioLibre("USR001");
        admSuministroUsuario.setNombreUsuarioLibre("Usuario Libre");
        admSuministroUsuario.setDireccionSuministro("Av. Ejemplo 123");
        admSuministroUsuario.setNroSuministroEmp("EMP001");
    }

    @Test
    void testInsert_Success() {
        when(admSuministroUsuarioMapper.selectByPrimaryKey(anyString())).thenReturn(null);
        when(admSuministroUsuarioMapper.insert(any(AdmSuministroUsuario.class))).thenReturn(1);

        MessageDTO result = admSuministroUsuarioService.insert(suministroUsuarioFormDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Se insertó correctamente el suministro usuario.", result.getMessage());
        verify(admSuministroUsuarioMapper, times(1)).insert(any(AdmSuministroUsuario.class));
    }

    @Test
    void testInsert_Failure_SuministroAlreadyExists() {
        when(admSuministroUsuarioMapper.selectByPrimaryKey(anyString())).thenReturn(admSuministroUsuario);

        MessageDTO result = admSuministroUsuarioService.insert(suministroUsuarioFormDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El suministro usuario ya existe.", result.getMessage());
    }

    @Test
    void testUpdateByPrimaryKey_Success() {
        when(admSuministroUsuarioMapper.selectByPrimaryKey(anyString())).thenReturn(admSuministroUsuario);
        when(admSuministroUsuarioMapper.updateByPrimaryKey(any(AdmSuministroUsuario.class))).thenReturn(1);

        MessageDTO result = admSuministroUsuarioService.updateByPrimaryKey(suministroUsuarioFormDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Se actualizó correctamente el suministro usuario.", result.getMessage());
        verify(admSuministroUsuarioMapper, times(1)).updateByPrimaryKey(any(AdmSuministroUsuario.class));
    }

    @Test
    void testUpdateByPrimaryKey_Failure_SuministroDoesNotExist() {
        when(admSuministroUsuarioMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admSuministroUsuarioService.updateByPrimaryKey(suministroUsuarioFormDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("No se encontró el suministro usuario para actualizar.", result.getMessage());
    }

    @Test
    void testDeleteByPrimaryKey_Success() {
        when(admSuministroUsuarioMapper.selectByPrimaryKey(anyString())).thenReturn(admSuministroUsuario);
        when(admSuministroUsuarioMapper.deleteByPrimaryKey(anyString())).thenReturn(1);

        MessageDTO result = admSuministroUsuarioService.deleteByPrimaryKey("SUM001");

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Se eliminó el suministro usuario.", result.getMessage());
        verify(admSuministroUsuarioMapper, times(1)).deleteByPrimaryKey(anyString());
    }

    @Test
    void testDeleteByPrimaryKey_Failure_SuministroDoesNotExist() {
        when(admSuministroUsuarioMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admSuministroUsuarioService.deleteByPrimaryKey("SUM001");

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("No se encontró el suministro usuario para eliminar.", result.getMessage());
    }

    @Test
    void testFiltrar_Success() {
        List<AdmSuministroUsuarioDTO> list = new ArrayList<>();
        list.add(new AdmSuministroUsuarioDTO());

        when(admSuministroUsuarioMapper.filtrar(any(AdmSuministroUsuarioSearchDTO.class))).thenReturn(list);

        AdmSuministroUsuarioSearchDTO searchDTO = new AdmSuministroUsuarioSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);

        PageInfo<AdmSuministroUsuarioDTO> result = admSuministroUsuarioService.filtrar(searchDTO);

        assertNotNull(result);
        assertEquals(1, result.getList().size());
    }
}
