package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.jsqlparser.expression.TryCastExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.CfgParametro;
import pe.gob.osinergmin.prie.admin.backend.domain.CfgTablaAdm;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.parametro.CfgParametroSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tablaMaestra.TablaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tablaMaestra.TablaMaestraDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tablaMaestra.TablaMaestraFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.CfgCampoAdmMapper;
import pe.gob.osinergmin.prie.admin.backend.mapper.CfgParametroMapper;
import pe.gob.osinergmin.prie.admin.backend.mapper.CfgTablaAdmMapper;
import pe.gob.osinergmin.prie.admin.backend.services.TablaMaestraService;

import java.util.*;

@Service
public class TablaMaestraServiceImpl implements TablaMaestraService {

    @Autowired
    private CfgTablaAdmMapper cfgTablaAdmMapper;

    @Autowired
    private CfgCampoAdmMapper cfgCampoAdmMapper;

    @Autowired
    private CfgParametroMapper cfgParametroMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TablaMaestraDTO> getLstTablaMaestra(String codGrupo) {
        return cfgParametroMapper.getParametrosByGrupo(codGrupo);
    }

    @Transactional
    @Override
    public MessageDTO insertarRegistro(TablaMaestraFormdDTO tablaMaestraFormdDTO, String codGrupo) {

        try {
            if (tablaMaestraFormdDTO.getCodParametro() == null || tablaMaestraFormdDTO.getCodParametro().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código del parámetro no puede estar vacío.");
            }

            if (tablaMaestraFormdDTO.getDescParametro() == null || tablaMaestraFormdDTO.getDescParametro().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "La descripción del parámetro no puede estar vacía.");
            }

            String codTabla = codGrupo;
            int existe = cfgTablaAdmMapper.existeTabla(codTabla);

            if (existe == 1) {

                CfgParametro cfgParametro = new CfgParametro();
                cfgParametro.setCodGrupo(codGrupo);
                cfgParametro.setCodParametro(tablaMaestraFormdDTO.getCodParametro());
                cfgParametro.setDescParametro(tablaMaestraFormdDTO.getDescParametro());
                cfgParametro.setValParametro("1");
                cfgParametroMapper.insertParametro(cfgParametro);
                return new MessageDTO(Constantes.SUCCES, "Se agregó de forma correcta.");
            } else {
                return new MessageDTO(Constantes.ERROR, "La tabla seleccionada no existe.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }


    @Override
    public MessageDTO updateRegistro(TablaMaestraFormdDTO tablaMaestraFormdDTO, String codGrupo) {
        try {
            if (tablaMaestraFormdDTO.getCodParametro() == null || tablaMaestraFormdDTO.getCodParametro().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código del parámetro no puede estar vacío.");
            }

            if (tablaMaestraFormdDTO.getDescParametro() == null || tablaMaestraFormdDTO.getDescParametro().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "La descripción del parámetro no puede estar vacía.");
            }

            String codTabla = codGrupo;
            int existe = cfgTablaAdmMapper.existeTabla(codTabla);

            if (existe == 1) {

                CfgParametro existeParametro = cfgParametroMapper.getParametro(codGrupo, tablaMaestraFormdDTO.getCodParametro());
                if (existeParametro != null) {

                    CfgParametro cfgParametro = new CfgParametro();
                    cfgParametro.setCodGrupo(codGrupo);
                    cfgParametro.setCodParametro(tablaMaestraFormdDTO.getCodParametro());
                    cfgParametro.setDescParametro(tablaMaestraFormdDTO.getDescParametro());
                    cfgParametro.setValParametro("1");

                    cfgParametroMapper.updateParametro(cfgParametro);
                    return new MessageDTO(Constantes.SUCCES, "Se agregó de forma correcta.");
                } else {
                    return new MessageDTO(Constantes.ERROR, "El parametro no existe");
                }

            } else {
                return new MessageDTO(Constantes.ERROR, "La tabla seleccionada no existe.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public MessageDTO deleteRegistro(String codGrupo, String codParametro) {
        try {
            CfgParametro existeParametro = cfgParametroMapper.getParametro(codGrupo, codParametro);
            if (existeParametro != null) {
                cfgParametroMapper.deleteByPrimaryKey(codGrupo, codParametro);
                return new MessageDTO(Constantes.SUCCES, "Se elimino correctamente el registro.");
            } else {
                return new MessageDTO(Constantes.ERROR, "El parametro no existe");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public PageInfo<TablaMaestraDTO> filtrar(CfgParametroSearchDTO cfgParametroSearchDTO, String codGrupo) {
        PageHelper.startPage(cfgParametroSearchDTO.getPage(), cfgParametroSearchDTO.getSize());
        return new PageInfo<>(cfgParametroMapper.filtrar(cfgParametroSearchDTO, codGrupo));
    }

    @Override
    public MessageDTO insetarTabla(TablaFormDTO tablaFormDTO) {
        try {
            int existe = cfgTablaAdmMapper.existeTabla(tablaFormDTO.getCodTabla());

            if (existe == 1) {
                return new MessageDTO(Constantes.ERROR, "La tabla seleccionada ya existe");
            }

            CfgTablaAdm cfgTablaAdm = new CfgTablaAdm();
            cfgTablaAdm.setCodTabla(tablaFormDTO.getCodTabla());
            cfgTablaAdm.setDescripcionTabla(tablaFormDTO.getDescripcionTabla());
            cfgTablaAdm.setEstadoTabla(tablaFormDTO.getEstadoTabla());
            cfgTablaAdm.setNombreCorto(tablaFormDTO.getNombreCorto());
            cfgTablaAdm.setFlagAuditoria(tablaFormDTO.getFlagAuditoria());
            cfgTablaAdmMapper.insert(cfgTablaAdm);

            return new MessageDTO(Constantes.SUCCES, "Se inserto correctamente la tabla");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public MessageDTO actualizarTabla(TablaFormDTO tablaFormDTO) {
        try {
            int existe = cfgTablaAdmMapper.existeTabla(tablaFormDTO.getCodTabla());

            if (existe == 0) {
                return new MessageDTO(Constantes.ERROR, "La tabla seleccionada no existe");
            }
            CfgTablaAdm cfgTablaAdm = new CfgTablaAdm();
            cfgTablaAdm.setCodTabla(tablaFormDTO.getCodTabla());
            cfgTablaAdm.setDescripcionTabla(tablaFormDTO.getDescripcionTabla());
            cfgTablaAdm.setEstadoTabla(tablaFormDTO.getEstadoTabla());
            cfgTablaAdm.setNombreCorto(tablaFormDTO.getNombreCorto());
            cfgTablaAdm.setFlagAuditoria(tablaFormDTO.getFlagAuditoria());
            cfgTablaAdmMapper.updateByPrimaryKey(cfgTablaAdm);

            return new MessageDTO(Constantes.SUCCES, "Se actualizado correctamente la tabla");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }

    }

    @Override
    public MessageDTO eliminarTabla(String codTabla) {
        try {
            int existe = cfgTablaAdmMapper.existeTabla(codTabla);

            if (existe == 1) {
                cfgTablaAdmMapper.deleteByPrimaryKey(codTabla);
                return new MessageDTO(Constantes.SUCCES, "Se eliminado correctamente la tabla");
            }
            return new MessageDTO(Constantes.ERROR, "La tabla seleccionada no existe");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }
}
