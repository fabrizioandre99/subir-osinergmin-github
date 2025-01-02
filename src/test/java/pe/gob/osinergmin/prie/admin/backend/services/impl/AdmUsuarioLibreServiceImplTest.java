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
import pe.gob.osinergmin.prie.admin.backend.domain.AdmUsuarioLibre;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.usuarioLibre.AdmUsuarioLibreDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.usuarioLibre.AdmUsuarioLibreFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.usuarioLibre.AdmUsuarioLibreSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmUsuarioLibreMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdmUsuarioLibreServiceImplTest {

    @Mock
    private AdmUsuarioLibreMapper admUsuarioLibreMapper;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private AdmUsuarioLibreServiceImpl admUsuarioLibreService;

    private AdmUsuarioLibreFormDTO usuarioLibreFormDTO;
    private AdmUsuarioLibre usuarioLibre;

    @BeforeEach
    void setUp() {
        usuarioLibreFormDTO = new AdmUsuarioLibreFormDTO();
        usuarioLibreFormDTO.setCodUsuarioLibre("USR001");
        usuarioLibreFormDTO.setRazonSocial("Usuario de Prueba");
        usuarioLibreFormDTO.setDireccion("Dirección de Prueba");
        usuarioLibreFormDTO.setTelefono("123456789");

        usuarioLibre = new AdmUsuarioLibre();
        usuarioLibre.setCodUsuarioLibre("USR001");
        usuarioLibre.setRazonSocial("Usuario de Prueba");
        usuarioLibre.setDireccion("Dirección de Prueba");
        usuarioLibre.setTelefono("123456789");
    }

    @Test
    void testInsert_Success() {
        when(admUsuarioLibreMapper.selectByPrimaryKey(anyString())).thenReturn(null);
        when(admUsuarioLibreMapper.insert(any(AdmUsuarioLibre.class))).thenReturn(1);

        MessageDTO result = admUsuarioLibreService.insert(usuarioLibreFormDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Se insertó correctamente el usuario libre.", result.getMessage());
        verify(admUsuarioLibreMapper, times(1)).insert(any(AdmUsuarioLibre.class));
    }

    @Test
    void testInsert_Failure_UsuarioExists() {
        when(admUsuarioLibreMapper.selectByPrimaryKey(anyString())).thenReturn(usuarioLibre);

        MessageDTO result = admUsuarioLibreService.insert(usuarioLibreFormDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("Ya existe.", result.getMessage());
    }

    @Test
    void testUpdateByPrimaryKey_Success() {
        when(admUsuarioLibreMapper.selectByPrimaryKey(anyString())).thenReturn(usuarioLibre);
        when(admUsuarioLibreMapper.updateByPrimaryKey(any(AdmUsuarioLibre.class))).thenReturn(1);

        MessageDTO result = admUsuarioLibreService.updateByPrimaryKey(usuarioLibreFormDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Se actualizó correctamente el usuario libre.", result.getMessage());
        verify(admUsuarioLibreMapper, times(1)).updateByPrimaryKey(any(AdmUsuarioLibre.class));
    }

    @Test
    void testUpdateByPrimaryKey_Failure_UsuarioNotFound() {
        when(admUsuarioLibreMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admUsuarioLibreService.updateByPrimaryKey(usuarioLibreFormDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("No se encontró el usuario libre para actualizar.", result.getMessage());
    }

    @Test
    void testDeleteByPrimaryKey_Success() {
        when(admUsuarioLibreMapper.selectByPrimaryKey(anyString())).thenReturn(usuarioLibre);
        when(admUsuarioLibreMapper.deleteByPrimaryKey(anyString())).thenReturn(1);

        MessageDTO result = admUsuarioLibreService.deleteByPrimaryKey("USR001");

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Se eliminó el usuario libre", result.getMessage());
        verify(admUsuarioLibreMapper, times(1)).deleteByPrimaryKey(anyString());
    }

    @Test
    void testDeleteByPrimaryKey_Failure_UsuarioNotFound() {
        when(admUsuarioLibreMapper.selectByPrimaryKey(anyString())).thenReturn(null);

        MessageDTO result = admUsuarioLibreService.deleteByPrimaryKey("USR001");

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("No se encontró el usuario libre para eliminar", result.getMessage());
    }

    @Test
    void testFiltrar_Success() {
        List<AdmUsuarioLibreDTO> usuarios = new ArrayList<>();
        usuarios.add(new AdmUsuarioLibreDTO());

        when(admUsuarioLibreMapper.filtrar(any(AdmUsuarioLibreSearchDTO.class))).thenReturn(usuarios);

        AdmUsuarioLibreSearchDTO searchDTO = new AdmUsuarioLibreSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);

        PageInfo<AdmUsuarioLibreDTO> result = admUsuarioLibreService.filtrar(searchDTO);

        assertNotNull(result);
        assertEquals(1, result.getList().size());
    }
}
