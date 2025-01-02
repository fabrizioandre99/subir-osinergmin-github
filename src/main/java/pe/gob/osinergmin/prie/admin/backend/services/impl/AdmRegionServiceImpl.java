package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmRegion;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmUbigeo;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.region.AdmRegionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.region.AdmRegionFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.region.AdmRegionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmRegionMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmRegionService;

import java.util.Date;
import java.util.List;

@Service
public class AdmRegionServiceImpl implements AdmRegionService{

    @Autowired
    private AdmRegionMapper admRegionMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public MessageDTO deleteByPrimaryKey(String codRegion) {
        try {
            if (codRegion == null || codRegion.trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código de la región no puede estar vacío");
            }
            if (codRegion.length() > 2) {
                return new MessageDTO(Constantes.ERROR, "El código de la región debe tener un máximo de 2 caracteres");
            }
            AdmRegion existe = admRegionMapper.selectByPrimaryKey(codRegion);
            if (existe == null) {
                return new MessageDTO(Constantes.ERROR, "La región no existe, no se puede eliminar");
            }
            admRegionMapper.deleteByPrimaryKey(codRegion);
            return new MessageDTO(Constantes.SUCCES, "Región eliminada correctamente");

        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Error al eliminar la región: " + e.getMessage());
        }
    }


    @Transactional
    @Override
    public MessageDTO insert(AdmRegionFormDTO record) {
        try {
            if (record.getCodRegion() == null || record.getCodRegion().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código de la región no puede estar vacío");
            }
            if (record.getNomRegion() == null || record.getNomRegion().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El nombre de la región no puede estar vacío");
            }
            if (record.getCodRegion().length() > 2) {
                return new MessageDTO(Constantes.ERROR, "El código de la región debe tener un máximo de 2 caracteres");
            }
            AdmRegion existe = admRegionMapper.selectByPrimaryKey(record.getCodRegion());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "Ya existe la región con el código proporcionado");
            }
            AdmRegion admRegion = new AdmRegion();
            admRegion.setCodRegion(record.getCodRegion());
            admRegion.setNomRegion(record.getNomRegion());
            admRegion.setUsuarioCreacion("admin");
            admRegion.setEstadoRegistro("1");
            admRegion.setFechaCreacion(new Date());
            admRegion.setTerminalCreacion(IpUtils.getClientIp(request));
            admRegionMapper.insert(admRegion);
            return new MessageDTO(Constantes.SUCCES, "Región insertada correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Error al insertar la región: " + e.getMessage());
        }
    }

    @Override
    public int insertSelective(AdmRegion record) {
        return admRegionMapper.insertSelective(record);
    }

    @Override
    public AdmRegion selectByPrimaryKey(String codRegion) {
        return admRegionMapper.selectByPrimaryKey(codRegion);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmRegion record) {
        return admRegionMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(AdmRegionFormDTO record) {
        try {
            if (record.getCodRegion() == null || record.getCodRegion().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código de la región no puede estar vacío");
            }
            if (record.getNomRegion() == null || record.getNomRegion().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El nombre de la región no puede estar vacío");
            }
            if (record.getCodRegion().length() > 2) {
                return new MessageDTO(Constantes.ERROR, "El código de la región debe tener un máximo de 2 caracteres");
            }
            AdmRegion existe = admRegionMapper.selectByPrimaryKey(record.getCodRegion());
            if (existe == null) {
                return new MessageDTO(Constantes.ERROR, "La región no existe, no se puede actualizar");
            }
            existe.setNomRegion(record.getNomRegion());
            existe.setUsuarioCreacion("admin");
            existe.setEstadoRegistro(record.getEstadoRegistro());
            existe.setFechaActualizacion(new Date());
            existe.setTerminalActualizacion(IpUtils.getClientIp(request));
            admRegionMapper.updateByPrimaryKey(existe);
            return new MessageDTO(Constantes.SUCCES, "Región actualizada correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Error al actualizar la región: " + e.getMessage());
        }
    }

    @Override
    public List<AdmRegionDTO> selectAll() {
        return admRegionMapper.selectAll();
    }

    @Override
    public PageInfo<AdmRegionDTO> filtrar(AdmRegionSearchDTO admRegionSearchDTO) {
        try {
            PageHelper.startPage(admRegionSearchDTO.getPage(), admRegionSearchDTO.getSize());
            return new PageInfo<>(admRegionMapper.filtrar(admRegionSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
