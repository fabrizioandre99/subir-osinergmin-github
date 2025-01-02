package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmUbigeo;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ubigeo.AdmUbigeoFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ubigeo.UbigeoDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ubigeo.UbigeoProvDeptDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ubigeo.UbigeoSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmUbigeoMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmUbigeoService;

import java.util.Date;
import java.util.List;

@Service
public class AdmUbigeoServiceImpl implements AdmUbigeoService{

    @Autowired
    private AdmUbigeoMapper admUbigeoMapper;

    @Override
    public MessageDTO deleteByPrimaryKey(String codUbigeo) {
        try {
            AdmUbigeo existe = admUbigeoMapper.selectByPrimaryKey(codUbigeo);
            if (existe != null) {
                admUbigeoMapper.deleteByPrimaryKey(codUbigeo);
                return new MessageDTO(Constantes.SUCCES, "OK");
            } else {
                return new MessageDTO(Constantes.ERROR, "No se elimino el Ubigeo");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public MessageDTO insert(AdmUbigeoFormDTO record) {
        try {
            AdmUbigeo existe = admUbigeoMapper.selectByPrimaryKey(record.getCodUbigeo());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "Ya existe el Codigo Ubigeo");
            } else {
                AdmUbigeo admUbigeo = new AdmUbigeo();
                admUbigeo.setCodUbigeo(record.getCodUbigeo());
                admUbigeo.setNomUbigeo(record.getNomUbigeo());
                admUbigeo.setEstado(record.getEstado());
                admUbigeo.setAdFecha(new Date());
                admUbigeoMapper.insert(admUbigeo);

                return new MessageDTO(Constantes.SUCCES, "OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    @Override
    public int insertSelective(AdmUbigeo record) {
        return admUbigeoMapper.insertSelective(record);
    }

    @Override
    public AdmUbigeo selectByPrimaryKey(String codUbigeo) {
        return admUbigeoMapper.selectByPrimaryKey(codUbigeo);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmUbigeo record) {
        return admUbigeoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public MessageDTO updateByPrimaryKey(AdmUbigeoFormDTO record) {
        try {
            AdmUbigeo existe = admUbigeoMapper.selectByPrimaryKey(record.getCodUbigeo());

            if (existe != null) {
                AdmUbigeo admUbigeo = new AdmUbigeo();
                admUbigeo.setCodUbigeo(record.getCodUbigeo());
                admUbigeo.setNomUbigeo(record.getNomUbigeo());
                admUbigeo.setEstado(record.getEstado());
                admUbigeo.setAdFecha(new Date());
                admUbigeoMapper.updateByPrimaryKey(admUbigeo);

                return new MessageDTO(Constantes.SUCCES, "OK");
            } else {
                return new MessageDTO(Constantes.ERROR, "No se Actualizo el Tipo de empresa");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<UbigeoDTO> listarDepartamento() {
        return admUbigeoMapper.listarDepartamento();
    }

    @Override
    public List<UbigeoDTO> listarProvincia(String codUbigeo) {
        return admUbigeoMapper.listarProvincia(codUbigeo);
    }

    @Override
    public List<UbigeoDTO> listarDistrito(String codProvincia) {
        return admUbigeoMapper.listarDistrito(codProvincia);
    }

    @Override
    public List<UbigeoDTO> listaAll() {
        return admUbigeoMapper.listaAll();
    }

    @Override
    public PageInfo<UbigeoDTO> listaFiltro(UbigeoSearchDTO ubigeoSearchDTO) {
        try {
            String sortField = mapSortField(ubigeoSearchDTO.getSort());
            String orderDirection = validateOrder(ubigeoSearchDTO.getOrder());
            String orderBy = sortField + " " + orderDirection;

            PageHelper.startPage(ubigeoSearchDTO.getPage(), ubigeoSearchDTO.getSize(), orderBy);
            List<UbigeoDTO> ubigeos = admUbigeoMapper.listaFiltro(ubigeoSearchDTO);

            return new PageInfo<>(ubigeos);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String mapSortField(String sort) {
        if (sort == null || sort.isEmpty()) {
            return "COD_UBIGEO";
        }
        switch (sort) {
            case "codUbigeo":
                return "COD_UBIGEO";
            case "nomUbigeo":
                return "NOM_UBIGEO";
            case "estado":
                return "ESTADO";
            default:
                return "COD_UBIGEO";
        }
    }

    private String validateOrder(String order) {
        if ("asc".equalsIgnoreCase(order) || "desc".equalsIgnoreCase(order)) {
            return order.toUpperCase();
        }
        return "ASC";
    }

    @Override
    public UbigeoProvDeptDTO obtenerProvDeptPorDistrito(String codUbigeo) {
        List<UbigeoProvDeptDTO> result = admUbigeoMapper.obtenerProvDeptPorDistrito(codUbigeo);
        return result.isEmpty() ? null : result.get(0);
    }

}
