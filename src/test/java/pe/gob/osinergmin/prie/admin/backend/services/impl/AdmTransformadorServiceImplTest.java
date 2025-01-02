package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTipoSistTrans;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTransformador;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.transformador.*;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmTipoSistTransMapper;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmTransformadorMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class AdmTransformadorServiceImplTest {

    @InjectMocks
    private AdmTransformadorServiceImpl admTransformadorService;

    @Mock
    private AdmTransformadorMapper admTransformadorMapper;

    @Mock
    private AdmTipoSistTransMapper admTipoSistTransMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testUpdateByPrimaryKey_NoExiste() {
        AdmTransformadorFormActualizarDTO formDTO = new AdmTransformadorFormActualizarDTO();
        formDTO.setIdTransformador(1);

        when(admTransformadorMapper.selectByPrimaryKey(1)).thenReturn(null);

        MessageDTO response = admTransformadorService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("El transformador con el ID proporcionado no existe.", response.getMessage());
        verify(admTransformadorMapper, never()).updateByPrimaryKey(any(AdmTransformador.class));
    }

    @Test
    void testDeleteByPrimaryKey_NoExiste() {
        when(admTransformadorMapper.selectByPrimaryKey(1)).thenReturn(null);

        MessageDTO response = admTransformadorService.deleteByPrimaryKey(1);

        assertEquals(Constantes.ERROR, response.getStatus());
        assertEquals("El transformador con el ID proporcionado no existe.", response.getMessage());
        verify(admTransformadorMapper, never()).deleteByPrimaryKey(1);
    }

    @Test
    void testFiltrar_Exitoso() {
        AdmTransformadorSearchDTO searchDTO = new AdmTransformadorSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);
        List<AdmTransformadorDTO> resultList = new ArrayList<>();

        when(admTransformadorMapper.filtrar(any(AdmTransformadorSearchDTO.class))).thenReturn(resultList);

        PageInfo<AdmTransformadorDTO> response = admTransformadorService.filtrar(searchDTO);

        assertNotNull(response);
        assertEquals(0, response.getList().size());
    }
}
