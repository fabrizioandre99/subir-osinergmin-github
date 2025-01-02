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
import pe.gob.osinergmin.prie.admin.backend.dto.tipoEmpresa.TipoEmpresaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoEmpresa.TipoEmpresaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoEmpresa.TipoEmpresaSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTipoEmpresaService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TipoEmpresaControllerTest {

    @InjectMocks
    private TipoEmpresaController tipoEmpresaController;

    @Mock
    private AdmTipoEmpresaService admTipoEmpresaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert()   {
        TipoEmpresaFormDTO formDTO = new TipoEmpresaFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Creado correctamente");

        when(admTipoEmpresaService.insert(any(TipoEmpresaFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = tipoEmpresaController.insert(formDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Creado correctamente", response.getDatas());
    }

    @Test
    void testUpdateByPrimaryKey()   {
        TipoEmpresaFormDTO formDTO = new TipoEmpresaFormDTO();
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Actualizado correctamente");

        when(admTipoEmpresaService.updateByPrimaryKey(any(TipoEmpresaFormDTO.class))).thenReturn(messageDTO);

        ResponseEntity<String> response = tipoEmpresaController.updateByPrimaryKey(formDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Actualizado correctamente", response.getDatas());
    }

    @Test
    void testEliminarPorId()   {
        String codTipoEmpresa = "T001";
        MessageDTO messageDTO = new MessageDTO(Constantes.SUCCES, "Eliminado correctamente");

        when(admTipoEmpresaService.deleteByPrimaryKey(codTipoEmpresa)).thenReturn(messageDTO);

        ResponseEntity<String> response = tipoEmpresaController.eliminarPorId(codTipoEmpresa);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals("Eliminado correctamente", response.getDatas());
    }

    @Test
    void testSelectAll()   {
        List<TipoEmpresaDTO> tipoEmpresaList = new ArrayList<>();
        tipoEmpresaList.add(new TipoEmpresaDTO());

        when(admTipoEmpresaService.selectAll()).thenReturn(tipoEmpresaList);

        ResponseEntity<List<TipoEmpresaDTO>> response = tipoEmpresaController.selectAll();

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals(1, response.getDatas().size());
    }

    @Test
    void testListarTarifasFiltro()   {
        TipoEmpresaSearchDTO searchDTO = new TipoEmpresaSearchDTO();
        PageInfo<TipoEmpresaDTO> pageInfo = new PageInfo<>(new ArrayList<>());

        when(admTipoEmpresaService.ListaFiltro(any(TipoEmpresaSearchDTO.class))).thenReturn(pageInfo);

        ResponseEntity<PageInfo<TipoEmpresaDTO>> response = tipoEmpresaController.listarTarifasFiltro(searchDTO);

        assertEquals(CodeEnum.SUCCESS.getStatusCodeValue(), response.getStatusCodeValue());
        assertEquals(0, response.getDatas().getList().size());
    }
}
