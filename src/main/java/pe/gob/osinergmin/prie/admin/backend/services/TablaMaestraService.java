package pe.gob.osinergmin.prie.admin.backend.services;

import com.github.pagehelper.PageInfo;
import pe.gob.osinergmin.prie.admin.backend.domain.*;
import org.json.JSONException;
import org.json.JSONObject;

import org.json.JSONArray;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.parametro.CfgParametroSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tablaMaestra.TablaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tablaMaestra.TablaMaestraDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tablaMaestra.TablaMaestraFormdDTO;

import java.text.ParseException;
import java.util.List;

public interface TablaMaestraService {

    List<TablaMaestraDTO> getLstTablaMaestra(String codGrupo);

    MessageDTO insertarRegistro(TablaMaestraFormdDTO tablaMaestraFormdDTO, String codGrupo);

    MessageDTO updateRegistro(TablaMaestraFormdDTO tablaMaestraFormdDTO, String codGrupo);

    MessageDTO deleteRegistro(String codGrupo, String codParametro);

    PageInfo<TablaMaestraDTO> filtrar(CfgParametroSearchDTO cfgParametroSearchDTO, String codGrupo);

    MessageDTO insetarTabla (TablaFormDTO tablaFormDTO);

    MessageDTO actualizarTabla (TablaFormDTO tablaFormDTO);

    MessageDTO eliminarTabla (String codTabla);
}
