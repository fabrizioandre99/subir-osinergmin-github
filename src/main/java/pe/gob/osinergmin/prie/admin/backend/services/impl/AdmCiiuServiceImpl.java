package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmUbigeo;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ciiu.AdmCiiuDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ciiu.AdmCiiuFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.ciiu.AdmCiiuSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmCiiuMapper;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmCiiu;
import pe.gob.osinergmin.prie.admin.backend.services.AdmCiiuService;

import java.util.Date;
import java.util.List;

@Service
public class AdmCiiuServiceImpl implements AdmCiiuService{

    @Autowired
    private AdmCiiuMapper admCiiuMapper;

    @Override
    public MessageDTO deleteByPrimaryKey(String codCiiu) {
        try {
            AdmCiiu existe = admCiiuMapper.selectByPrimaryKey(codCiiu);
            if (existe != null) {
                admCiiuMapper.deleteByPrimaryKey(codCiiu);
                return new MessageDTO(Constantes.SUCCES, "OK");
            } else {
                return new MessageDTO(Constantes.ERROR, "No se elimino el CIIU");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public MessageDTO insert(AdmCiiuFormDTO record) {
        try {
            AdmCiiu existe = admCiiuMapper.selectByPrimaryKey(record.getCodCiiu());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "Ya existe el Codigo CIIU");
            } else {
                AdmCiiu admCiiu = new AdmCiiu();
                admCiiu.setCodCiiu(record.getCodCiiu());
                admCiiu.setCodActividadEconomica(record.getCodActividadEconomica());
                admCiiu.setCiiu(record.getCiiu());
                admCiiu.setAdFecha(new Date());

                admCiiuMapper.insert(admCiiu);

                return new MessageDTO(Constantes.SUCCES, "OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int insertSelective(AdmCiiu record) {
        return admCiiuMapper.insertSelective(record);
    }

    @Override
    public AdmCiiu selectByPrimaryKey(String codCiiu) {
        return admCiiuMapper.selectByPrimaryKey(codCiiu);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmCiiu record) {
        return admCiiuMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(AdmCiiuFormDTO record) {
        try {
            AdmCiiu existe = admCiiuMapper.selectByPrimaryKey(record.getCodCiiu());

            if (existe != null) {
                AdmCiiu admCiiu = new AdmCiiu();
                admCiiu.setCodCiiu(record.getCodCiiu());
                admCiiu.setCodActividadEconomica(record.getCodActividadEconomica());
                admCiiu.setCiiu(record.getCiiu());
                admCiiu.setAdFecha(new Date());
                admCiiuMapper.updateByPrimaryKey(admCiiu);

                return new MessageDTO(Constantes.SUCCES, "OK");
            } else {
                return new MessageDTO(Constantes.ERROR, "No se encontró el CIIU para actualizar");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    @Override
    public List<AdmCiiuDTO> selectAll() {
        try {
            return admCiiuMapper.selectAll();
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public PageInfo<AdmCiiuDTO> listarFiltro(AdmCiiuSearchDTO admCiiuSearchDTO) {
        try {

            String sortField = mapSortField(admCiiuSearchDTO.getSort());
            String orderDirection = validateOrder(admCiiuSearchDTO.getOrder());
            String orderBy = sortField + " " + orderDirection;

            PageHelper.startPage(admCiiuSearchDTO.getPage(), admCiiuSearchDTO.getSize(), orderBy);
            List<AdmCiiuDTO> ciius = admCiiuMapper.listarFiltro(admCiiuSearchDTO);

            return new PageInfo<>(ciius);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    private String mapSortField(String sort) {
        if (sort == null || sort.isEmpty()) {
            return "COD_CIIU";
        }
        switch (sort) {
            case "codCiiu":
                return "COD_CIIU";
            case "ciiu":
                return "CIIU";
            case "codActividadEconomica":
                return "COD_ACTIVIDAD_ECONOMICA";
            default:
                return "COD_CIIU";
        }
    }

    private String validateOrder(String order) {
        if ("asc".equalsIgnoreCase(order) || "desc".equalsIgnoreCase(order)) {
            return order.toUpperCase();
        }
        return "ASC";
    }

}
