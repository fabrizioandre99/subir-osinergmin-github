package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoEmpresa.TipoEmpresaDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoEmpresa.TipoEmpresaFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoEmpresa.TipoEmpresaSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmTipoEmpresaMapper;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTipoEmpresa;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTipoEmpresaService;

import java.util.Date;
import java.util.List;

@Service
public class AdmTipoEmpresaServiceImpl implements AdmTipoEmpresaService {

    @Autowired
    private AdmTipoEmpresaMapper admTipoEmpresaMapper;

    @Transactional
    @Override
    public MessageDTO deleteByPrimaryKey(String codTipoEmpresa) {
        try {
            AdmTipoEmpresa existe = admTipoEmpresaMapper.selectByPrimaryKey(codTipoEmpresa);
            if (existe != null) {
                admTipoEmpresaMapper.deleteByPrimaryKey(codTipoEmpresa);
                return new MessageDTO(Constantes.SUCCES, "OK");
            } else {
                return new MessageDTO(Constantes.ERROR, "No se Elimino el Tipo de empresa");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    @Override
    public MessageDTO insert(TipoEmpresaFormDTO tipoEmpresaFormDTO) {

        try {
            AdmTipoEmpresa existe = admTipoEmpresaMapper.selectByPrimaryKey(tipoEmpresaFormDTO.getCodTipoEmpresa());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "Ya existe el Codigo Empresa");
            } else {
                AdmTipoEmpresa admTipoEmpresa = new AdmTipoEmpresa();
                admTipoEmpresa.setCodTipoEmpresa(tipoEmpresaFormDTO.getCodTipoEmpresa());
                admTipoEmpresa.setDscTipoEmpresa(tipoEmpresaFormDTO.getDscTipoEmpresa());
                admTipoEmpresa.setAdFecha(new Date());
                admTipoEmpresa.setEstado(tipoEmpresaFormDTO.getEstado());
                admTipoEmpresaMapper.insert(admTipoEmpresa);
                return new MessageDTO(Constantes.SUCCES, "OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    @Override
    public int insertSelective(AdmTipoEmpresa record) {
        return admTipoEmpresaMapper.insertSelective(record);
    }

    @Override
    public AdmTipoEmpresa selectByPrimaryKey(String codTipoEmpresa) {
        return admTipoEmpresaMapper.selectByPrimaryKey(codTipoEmpresa);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmTipoEmpresa record) {
        return admTipoEmpresaMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(TipoEmpresaFormDTO tipoEmpresaFormDTO) {
        try {
            AdmTipoEmpresa existe = admTipoEmpresaMapper.selectByPrimaryKey(tipoEmpresaFormDTO.getCodTipoEmpresa());

            if (existe != null) {
                AdmTipoEmpresa admTipoEmpresa = new AdmTipoEmpresa();
                admTipoEmpresa.setCodTipoEmpresa(tipoEmpresaFormDTO.getCodTipoEmpresa());
                admTipoEmpresa.setDscTipoEmpresa(tipoEmpresaFormDTO.getDscTipoEmpresa());
                admTipoEmpresa.setEstado(tipoEmpresaFormDTO.getEstado());
                admTipoEmpresa.setAdFecha(new Date());
                admTipoEmpresaMapper.updateByPrimaryKey(admTipoEmpresa);
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
    public List<TipoEmpresaDTO> selectAll() {
        try {
            return admTipoEmpresaMapper.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public PageInfo<TipoEmpresaDTO> ListaFiltro(TipoEmpresaSearchDTO tipoEmpresaSearchDTO) {
        try {
            String sortField = mapSortField(tipoEmpresaSearchDTO.getSort());
            String orderDirection = validateOrder(tipoEmpresaSearchDTO.getOrder());
            String orderBy = sortField + " " + orderDirection;

            PageHelper.startPage(tipoEmpresaSearchDTO.getPage(), tipoEmpresaSearchDTO.getSize(), orderBy);
            List<TipoEmpresaDTO> tipoEmpresas = admTipoEmpresaMapper.listaFiltro(tipoEmpresaSearchDTO);

            return new PageInfo<>(tipoEmpresas);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String mapSortField(String sort) {
        if (sort == null || sort.isEmpty()) {
            return "COD_TIPO_EMPRESA";
        }
        switch (sort) {
            case "codTipoEmpresa":
                return "COD_TIPO_EMPRESA";
            case "dscTipoEmpresa":
                return "DSC_TIPO_EMPRESA";
            case "estado":
                return "ESTADO";
            default:
                return "COD_TIPO_EMPRESA";
        }
    }

    private String validateOrder(String order) {
        if ("asc".equalsIgnoreCase(order) || "desc".equalsIgnoreCase(order)) {
            return order.toUpperCase();
        }
        return "ASC";
    }

}
