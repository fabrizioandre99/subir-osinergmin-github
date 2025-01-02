package pe.gob.osinergmin.prie.admin.backend.controller;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.gob.osinergmin.prie.admin.backend.Util.CodeEnum;
import pe.gob.osinergmin.prie.admin.backend.Util.ResponseEntity;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ubigeo.AdmUbigeoFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ubigeo.UbigeoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ubigeo.UbigeoSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmUbigeoService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UbigeoControllerTest {

    @InjectMocks
    private UbigeoController ubigeoController;

    @Mock
    private AdmUbigeoService ubigeoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarDepartamento() {
        List<UbigeoDTO> departamentos = new ArrayList<>();
        departamentos.add(new UbigeoDTO());

        when(ubigeoService.listarDepartamento()).thenReturn(departamentos);

        List<UbigeoDTO> response = ubigeoController.listarDepartamento();

        assertEquals(1, response.size());
    }

    @Test
    void testListarProvincias() {
        String codUbigeo = "01";
        List<UbigeoDTO> provincias = new ArrayList<>();
        provincias.add(new UbigeoDTO());

        when(ubigeoService.listarProvincia(codUbigeo)).thenReturn(provincias);

        ResponseEntity<List<UbigeoDTO>> response = ubigeoController.listarProvincias(codUbigeo);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    void testListarDistritos() {
        String codProvincia = "0101";
        List<UbigeoDTO> distritos = new ArrayList<>();
        distritos.add(new UbigeoDTO());

        when(ubigeoService.listarDistrito(codProvincia)).thenReturn(distritos);

        ResponseEntity<List<UbigeoDTO>> response = ubigeoController.listarDistrito(codProvincia);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    void testInsert() throws Exception {
        AdmUbigeoFormDTO formDTO = new AdmUbigeoFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Creado correctamente");

        when(ubigeoService.insert(any(AdmUbigeoFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = ubigeoController.insert(formDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Creado correctamente", response.getDatas());
    }

    @Test
    void testUpdateByPrimaryKey() throws Exception {
        AdmUbigeoFormDTO formDTO = new AdmUbigeoFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Actualizado correctamente");

        when(ubigeoService.updateByPrimaryKey(any(AdmUbigeoFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = ubigeoController.updateByPrimaryKey(formDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Actualizado correctamente", response.getDatas());
    }

    @Test
    void testEliminarPorId() throws Exception {
        String codUbigeo = "010101";
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Eliminado correctamente");

        when(ubigeoService.deleteByPrimaryKey(codUbigeo)).thenReturn(messageDTO);

        ResponseEntity<String> response = ubigeoController.eliminarPorId(codUbigeo);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Eliminado correctamente", response.getDatas());
    }

    @Test
    void testSelectAll() throws Exception {
        List<UbigeoDTO> ubigeoList = new ArrayList<>();
        ubigeoList.add(new UbigeoDTO());

        when(ubigeoService.listaAll()).thenReturn(ubigeoList);

        ResponseEntity<List<UbigeoDTO>> response = ubigeoController.selectAll();

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    void testListarFiltro() throws Exception {
        UbigeoSearchDTO searchDTO = new UbigeoSearchDTO();
        PageInfo<UbigeoDTO> pageInfo = new PageInfo<>(new ArrayList<>());

        when(ubigeoService.listaFiltro(any(UbigeoSearchDTO.class))).thenReturn(pageInfo);

        ResponseEntity<PageInfo<UbigeoDTO>> response = ubigeoController.listarFiltro(searchDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals(0, response.getDatas().getList().size());
    }
}
