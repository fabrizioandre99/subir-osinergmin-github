package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmGrupoCombustible;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoCombustible.AdmGrupoCombustibleDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoCombustible.AdmGrupoCombustibleFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoCombustible.AdmGrupoCombustibleSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmGrupoCombustibleMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmGrupoCombustibleService;

import java.util.Date;
import java.util.List;

@Service
public class AdmGrupoCombustibleServiceImpl implements AdmGrupoCombustibleService{

    @Autowired
    private AdmGrupoCombustibleMapper admGrupoCombustibleMapper;

    @Transactional
    @Override
    public MessageDTO deleteByPrimaryKey(String codGrupoCombustible) {
        try {
            AdmGrupoCombustible existe = admGrupoCombustibleMapper.selectByPrimaryKey(codGrupoCombustible);
            if (existe != null) {
                admGrupoCombustibleMapper.deleteByPrimaryKey(codGrupoCombustible);
                return new MessageDTO(Constantes.SUCCES,"Grupo combustible eliminado exitosamente");
            }else {
                return new MessageDTO(Constantes.ERROR,"Grupo combustible no existe");
            }
        }catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    @Override
    public MessageDTO insert(AdmGrupoCombustibleFormdDTO record) {
        try {
            String usuario = "Admin";
            if (record.getCodGrupoCombustible() == null || record.getCodGrupoCombustible().length() > 2) {
                return new MessageDTO(Constantes.ERROR, "El código no debe tener más de 2 caracteres");
            }
            if (record.getNomGrupoCombustible() != null && record.getNomGrupoCombustible().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "El nombre no debe exceder 100 caracteres");
            }
            if (record.getEstado() == null || record.getEstado().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El estado debe tener 1 carácter");
            }

            AdmGrupoCombustible existe = admGrupoCombustibleMapper.selectByPrimaryKey(record.getCodGrupoCombustible());
            if (existe == null) {
                AdmGrupoCombustible admGrupoCombustible = new AdmGrupoCombustible();
                admGrupoCombustible.setCodGrupoCombustible(record.getCodGrupoCombustible());
                admGrupoCombustible.setNomGrupoCombustible(record.getNomGrupoCombustible());
                admGrupoCombustible.setEstado(record.getEstado());
                admGrupoCombustible.setAdFecha(new Date());
                admGrupoCombustible.setAdCodUsuario(usuario);
                admGrupoCombustibleMapper.insert(admGrupoCombustible);
                return new MessageDTO(Constantes.SUCCES, "Grupo combustible insertado exitosamente");
            } else {
                return new MessageDTO(Constantes.ERROR, "Grupo combustible ya existe");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public int insertSelective(AdmGrupoCombustible record) {
        return admGrupoCombustibleMapper.insertSelective(record);
    }

    @Override
    public AdmGrupoCombustible selectByPrimaryKey(String codGrupoCombustible) {
        return admGrupoCombustibleMapper.selectByPrimaryKey(codGrupoCombustible);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmGrupoCombustible record) {
        return admGrupoCombustibleMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(AdmGrupoCombustibleFormdDTO record) {
        try {
            String usuario = "Admin";
            if (record.getCodGrupoCombustible() == null || record.getCodGrupoCombustible().length() > 2) {
                return new MessageDTO(Constantes.ERROR, "El código no debe tener más de 2 caracteres");
            }
            if (record.getNomGrupoCombustible() != null && record.getNomGrupoCombustible().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "El nombre no debe exceder 100 caracteres");
            }
            if (record.getEstado() == null || record.getEstado().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El estado debe tener 1 carácter");
            }

            AdmGrupoCombustible existe = admGrupoCombustibleMapper.selectByPrimaryKey(record.getCodGrupoCombustible());
            if (existe != null) {
                AdmGrupoCombustible admGrupoCombustible = new AdmGrupoCombustible();
                admGrupoCombustible.setCodGrupoCombustible(record.getCodGrupoCombustible());
                admGrupoCombustible.setNomGrupoCombustible(record.getNomGrupoCombustible());
                admGrupoCombustible.setEstado(record.getEstado());
                admGrupoCombustible.setAdFecha(new Date());
                admGrupoCombustible.setAdCodUsuario(usuario);
                admGrupoCombustibleMapper.updateByPrimaryKey(admGrupoCombustible);
                return new MessageDTO(Constantes.SUCCES, "Grupo combustible actualizado exitosamente");
            } else {
                return new MessageDTO(Constantes.ERROR, "Grupo combustible no existe");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }


    @Override
    public List<AdmGrupoCombustibleDTO> selectAll() {
        return admGrupoCombustibleMapper.selectAll();
    }

    @Override
    public PageInfo<AdmGrupoCombustibleDTO> filtrar(AdmGrupoCombustibleSearchDTO searchDTO) {
        try {
            String sortField = mapSortField(
                    searchDTO.getSort() != null && !searchDTO.getSort().isEmpty() ? searchDTO.getSort() : "codGrupoCombustible"
            );

            String order = validateOrder(
                    searchDTO.getOrder() != null && !searchDTO.getOrder().isEmpty() ? searchDTO.getOrder() : "ASC"
            );

            PageHelper.startPage(searchDTO.getPage(), searchDTO.getSize(), sortField + " " + order);

            List<AdmGrupoCombustibleDTO> resultList = admGrupoCombustibleMapper.filtrar(searchDTO);
            return new PageInfo<>(resultList);
        } catch (Exception e) {
            e.printStackTrace();
            return new PageInfo<>();
        }
    }


    private String mapSortField(String sort) {
        if ("codGrupoCombustible".equals(sort)) {
            return "COD_GRUPO_COMBUSTIBLE";
        } else if ("nomGrupoCombustible".equals(sort)) {
            return "NOM_GRUPO_COMBUSTIBLE";
        } else if ("estado".equals(sort)) {
            return "ESTADO";
        }
        return "COD_GRUPO_COMBUSTIBLE";
    }

    private String validateOrder(String order) {
        if ("ASC".equalsIgnoreCase(order) || "DESC".equalsIgnoreCase(order)) {
            return order.toUpperCase();
        }
        return "ASC";
    }
}
