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
import pe.gob.osinergmin.prie.admin.backend.domain.AdmGrupoBarra;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.barra.CodBarraDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoBarra.AdmGrupoBarraformDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoBarra.AdmGrupoBarraSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoBarra.GrupoBarraResponseDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmGrupoBarraMapper;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdmGrupoBarraServiceImplTest {

    @Mock
    private AdmGrupoBarraMapper admGrupoBarraMapper;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private AdmGrupoBarraServiceImpl admGrupoBarraService;

    private AdmGrupoBarraformDTO grupoBarraformDTO;
    private CodBarraDTO codBarraDTO;

    @BeforeEach
    void setUp() {
        grupoBarraformDTO = new AdmGrupoBarraformDTO();
        grupoBarraformDTO.setCodBarraGrupo("GR001");
        grupoBarraformDTO.setEstado("1");

        codBarraDTO = new CodBarraDTO();
        codBarraDTO.setCodBarra("CB001");
        grupoBarraformDTO.setCodBarras(Collections.singletonList(codBarraDTO));
    }

    @Test
    void testInsertGrupoBarra_Success() {
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        MessageDTO result = admGrupoBarraService.insertGrupoBarra(grupoBarraformDTO);

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Grupo de Barra creado correctamente.", result.getMessage());
        verify(admGrupoBarraMapper, times(1)).insertGrupoBarra(any(Map.class));
    }

    @Test
    void testInsertGrupoBarra_Failure_MissingCodBarraGrupo() {
        grupoBarraformDTO.setCodBarraGrupo(null);

        MessageDTO result = admGrupoBarraService.insertGrupoBarra(grupoBarraformDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El código del grupo de barra es requerido.", result.getMessage());
    }

    @Test
    void testInsertGrupoBarra_Failure_InvalidEstado() {
        grupoBarraformDTO.setEstado("5");

        MessageDTO result = admGrupoBarraService.insertGrupoBarra(grupoBarraformDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El estado debe ser '1' (Habilitado) o '0' (Deshabilitado).", result.getMessage());
    }

    @Test
    void testInsertGrupoBarra_Failure_NoCodBarras() {
        grupoBarraformDTO.setCodBarras(null);

        MessageDTO result = admGrupoBarraService.insertGrupoBarra(grupoBarraformDTO);

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("Se requiere al menos un código de barra.", result.getMessage());
    }

//    @Test
//    void testActualizarGrupoBarra_Success() {
//        when(admGrupoBarraMapper.deleteCodBarra(anyString())).thenReturn(1);
//        when(request.getRemoteAddr()).thenReturn("127.0.0.1");
//
//        MessageDTO result = admGrupoBarraService.actualizarGrupoBarra(grupoBarraformDTO);
//
//        assertEquals(Constantes.SUCCES, result.getStatus());
//        assertEquals("Grupo de Barra actualizado correctamente.", result.getMessage());
//        verify(admGrupoBarraMapper, times(1)).insertGrupoBarra(any(Map.class));
//    }

    @Test
    void testEliminarGrupoBarra_Success() {
        when(admGrupoBarraMapper.deleteCodBarra(anyString())).thenReturn(1);

        MessageDTO result = admGrupoBarraService.eliminarGrupoBarra("GR001");

        assertEquals(Constantes.SUCCES, result.getStatus());
        assertEquals("Grupo de Barra eliminado correctamente.", result.getMessage());
    }

    @Test
    void testEliminarGrupoBarra_Failure_NotFound() {
        when(admGrupoBarraMapper.deleteCodBarra(anyString())).thenReturn(0);

        MessageDTO result = admGrupoBarraService.eliminarGrupoBarra("GR001");

        assertEquals(Constantes.ERROR, result.getStatus());
        assertEquals("El Grupo de Barra no existe", result.getMessage());
    }

//    @Test
//    void testFiltrar_Success() {
//        AdmGrupoBarraSearchDTO searchDTO = new AdmGrupoBarraSearchDTO();
//        searchDTO.setPage(1);
//        searchDTO.setSize(10);
//
//        List<GrupoBarraResponseDTO> resultList = new ArrayList<>();
//        resultList.add(new GrupoBarraResponseDTO());
//
//        when(admGrupoBarraMapper.filtrar(any(AdmGrupoBarraSearchDTO.class))).thenReturn(resultList);
//
//        PageInfo<GrupoBarraResponseDTO> result = admGrupoBarraService.filtrar(searchDTO);
//
//        assertNotNull(result);
//        assertEquals(1, result.getList().size());
//        verify(admGrupoBarraMapper, times(1)).filtrar(any(AdmGrupoBarraSearchDTO.class));
//    }
}
