package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmUbigeo;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ubigeo.AdmUbigeoFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ubigeo.UbigeoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ubigeo.UbigeoSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmUbigeoMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class AdmUbigeoServiceImplTest {

    @InjectMocks
    private AdmUbigeoServiceImpl admUbigeoService;

    @Mock
    private AdmUbigeoMapper admUbigeoMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteByPrimaryKey() {
        String codUbigeo = "010101";
        AdmUbigeo ubigeo = new AdmUbigeo();
        when(admUbigeoMapper.selectByPrimaryKey(codUbigeo)).thenReturn(ubigeo);

        MessageDTO response = admUbigeoService.deleteByPrimaryKey(codUbigeo);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("OK", response.getMessage());
        verify(admUbigeoMapper, times(1)).deleteByPrimaryKey(codUbigeo);
    }

    @Test
    public void testInsert() {
        AdmUbigeoFormDTO formDTO = new AdmUbigeoFormDTO();
        formDTO.setCodUbigeo("010101");
        formDTO.setNomUbigeo("Test");

        when(admUbigeoMapper.selectByPrimaryKey(formDTO.getCodUbigeo())).thenReturn(null);

        MessageDTO response = admUbigeoService.insert(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("OK", response.getMessage());
        verify(admUbigeoMapper, times(1)).insert(any(AdmUbigeo.class));
    }

    @Test
    public void testUpdateByPrimaryKey() {
        AdmUbigeoFormDTO formDTO = new AdmUbigeoFormDTO();
        formDTO.setCodUbigeo("010101");
        formDTO.setNomUbigeo("Test");

        when(admUbigeoMapper.selectByPrimaryKey(formDTO.getCodUbigeo())).thenReturn(new AdmUbigeo());

        MessageDTO response = admUbigeoService.updateByPrimaryKey(formDTO);

        assertEquals(Constantes.SUCCES, response.getStatus());
        assertEquals("OK", response.getMessage());
        verify(admUbigeoMapper, times(1)).updateByPrimaryKey(any(AdmUbigeo.class));
    }

    @Test
    public void testListarDepartamento() {
        List<UbigeoDTO> departamentos = new ArrayList<>();
        departamentos.add(new UbigeoDTO());

        when(admUbigeoMapper.listarDepartamento()).thenReturn(departamentos);

        List<UbigeoDTO> response = admUbigeoService.listarDepartamento();

        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    public void testListarProvincia() {
        String codUbigeo = "0101";
        List<UbigeoDTO> provincias = new ArrayList<>();
        provincias.add(new UbigeoDTO());

        when(admUbigeoMapper.listarProvincia(codUbigeo)).thenReturn(provincias);

        List<UbigeoDTO> response = admUbigeoService.listarProvincia(codUbigeo);

        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    public void testListarDistrito() {
        String codProvincia = "010101";
        List<UbigeoDTO> distritos = new ArrayList<>();
        distritos.add(new UbigeoDTO());

        when(admUbigeoMapper.listarDistrito(codProvincia)).thenReturn(distritos);

        List<UbigeoDTO> response = admUbigeoService.listarDistrito(codProvincia);

        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    public void testListaAll() {
        List<UbigeoDTO> ubigeos = new ArrayList<>();
        ubigeos.add(new UbigeoDTO());

        when(admUbigeoMapper.listaAll()).thenReturn(ubigeos);

        List<UbigeoDTO> response = admUbigeoService.listaAll();

        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    public void testListaFiltro() {
        UbigeoSearchDTO searchDTO = new UbigeoSearchDTO();
        searchDTO.setPage(1);
        searchDTO.setSize(10);
        List<UbigeoDTO> ubigeos = new ArrayList<>();

        when(admUbigeoMapper.listaFiltro(any(UbigeoSearchDTO.class))).thenReturn(ubigeos);

        PageInfo<UbigeoDTO> response = admUbigeoService.listaFiltro(searchDTO);

        assertNotNull(response);
        assertEquals(0, response.getList().size());
    }
}
