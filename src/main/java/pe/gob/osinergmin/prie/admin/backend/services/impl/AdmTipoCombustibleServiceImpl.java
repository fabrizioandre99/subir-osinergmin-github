package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTipoCombustible;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCombustible.AdmTipoCombustibleDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCombustible.AdmTipoCombustibleFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCombustible.AdmTipoCombustibleSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmTipoCombustibleMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTipoCombustibleService;

import java.util.Date;
import java.util.List;

@Service
public class AdmTipoCombustibleServiceImpl implements AdmTipoCombustibleService {

    @Autowired
    private AdmTipoCombustibleMapper admTipoCombustibleMapper;

    @Transactional
    @Override
    public MessageDTO deleteByPrimaryKey(String codTipoCombustible) {
        try {
            if (codTipoCombustible == null || codTipoCombustible.length() > 3) {
                return new MessageDTO(Constantes.ERROR, "El código de tipo de combustible es obligatorio y debe tener hasta 3 caracteres.");
            }

            AdmTipoCombustible existe = admTipoCombustibleMapper.selectByPrimaryKey(codTipoCombustible);
            if (existe == null) {
                return new MessageDTO(Constantes.ERROR, "No existe el tipo de combustible.");
            }

            admTipoCombustibleMapper.deleteByPrimaryKey(codTipoCombustible);
            return new MessageDTO(Constantes.SUCCES, "El tipo de combustible se eliminó correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Transactional
    @Override
    public MessageDTO insert(AdmTipoCombustibleFormDTO admTipoCombustibleFormDTO) {
        try {
            if (admTipoCombustibleFormDTO.getCodTipoCombustible() == null || admTipoCombustibleFormDTO.getCodTipoCombustible().length() > 3) {
                return new MessageDTO(Constantes.ERROR, "El código de tipo de combustible es obligatorio y debe tener hasta 3 caracteres.");
            }
            if (admTipoCombustibleFormDTO.getNomTipoCombustible() != null && admTipoCombustibleFormDTO.getNomTipoCombustible().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "El nombre del tipo de combustible no puede exceder los 100 caracteres.");
            }
            if (admTipoCombustibleFormDTO.getEstado() == null || admTipoCombustibleFormDTO.getEstado().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El estado del tipo de combustible es obligatorio y debe ser un solo carácter.");
            }

            AdmTipoCombustible existe = admTipoCombustibleMapper.selectByPrimaryKey(admTipoCombustibleFormDTO.getCodTipoCombustible());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "El tipo de combustible ya existe.");
            }

            AdmTipoCombustible admTipoCombustible = new AdmTipoCombustible();
            admTipoCombustible.setCodTipoCombustible(admTipoCombustibleFormDTO.getCodTipoCombustible());
            admTipoCombustible.setNomTipoCombustible(admTipoCombustibleFormDTO.getNomTipoCombustible());
            admTipoCombustible.setCodGrupoCombustible(admTipoCombustibleFormDTO.getCodGrupoCombustible());
            admTipoCombustible.setEstado(admTipoCombustibleFormDTO.getEstado());
            admTipoCombustible.setAdFecha(new Date());
            admTipoCombustible.setAdCodUsuario("Admin");

            int valorAgregado = admTipoCombustibleMapper.insert(admTipoCombustible);
            if (valorAgregado > 0) {
                return new MessageDTO(Constantes.SUCCES, "El tipo de combustible se insertó correctamente.");
            }
            return new MessageDTO(Constantes.ERROR, "No se pudo insertar el tipo de combustible.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(AdmTipoCombustibleFormDTO admTipoCombustibleFormDTO) {
        try {
            if (admTipoCombustibleFormDTO.getCodTipoCombustible() == null || admTipoCombustibleFormDTO.getCodTipoCombustible().length() > 3) {
                return new MessageDTO(Constantes.ERROR, "El código de tipo de combustible es obligatorio y debe tener hasta 3 caracteres.");
            }
            if (admTipoCombustibleFormDTO.getNomTipoCombustible() != null && admTipoCombustibleFormDTO.getNomTipoCombustible().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "El nombre del tipo de combustible no puede exceder los 100 caracteres.");
            }
            if (admTipoCombustibleFormDTO.getEstado() == null || admTipoCombustibleFormDTO.getEstado().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El estado del tipo de combustible es obligatorio y debe ser un solo carácter.");
            }

            AdmTipoCombustible existe = admTipoCombustibleMapper.selectByPrimaryKey(admTipoCombustibleFormDTO.getCodTipoCombustible());
            if (existe == null) {
                return new MessageDTO(Constantes.ERROR, "El tipo de combustible no existe.");
            }

            AdmTipoCombustible admTipoCombustible = new AdmTipoCombustible();
            admTipoCombustible.setCodTipoCombustible(admTipoCombustibleFormDTO.getCodTipoCombustible());
            admTipoCombustible.setNomTipoCombustible(admTipoCombustibleFormDTO.getNomTipoCombustible());
            admTipoCombustible.setCodGrupoCombustible(admTipoCombustibleFormDTO.getCodGrupoCombustible());
            admTipoCombustible.setEstado(admTipoCombustibleFormDTO.getEstado());
            admTipoCombustible.setAdFecha(new Date());
            admTipoCombustible.setAdCodUsuario("Admin");

            int valorAgregado = admTipoCombustibleMapper.updateByPrimaryKey(admTipoCombustible);
            if (valorAgregado > 0) {
                return new MessageDTO(Constantes.SUCCES, "El tipo de combustible se actualizó correctamente.");
            }
            return new MessageDTO(Constantes.ERROR, "No se pudo actualizar el tipo de combustible.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public List<AdmTipoCombustibleDTO> listartodo() {
        return admTipoCombustibleMapper.listartodo();
    }

    @Override
    public PageInfo<AdmTipoCombustibleDTO> filtrarTipoCombustible(AdmTipoCombustibleSearchDTO admTipoCombustibleSearchDTO) {
        String sortField = mapSortField(admTipoCombustibleSearchDTO.getSort());
        String sortOrder = validateOrder(admTipoCombustibleSearchDTO.getOrder());

        PageHelper.startPage(admTipoCombustibleSearchDTO.getPage(), admTipoCombustibleSearchDTO.getSize());
        if (sortField != null && !sortField.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
            PageHelper.orderBy(sortField + " " + sortOrder);
        }

        return new PageInfo<>(admTipoCombustibleMapper.filtrarTipoCombustible(admTipoCombustibleSearchDTO));
    }


    private String mapSortField(String sortField) {
        if (sortField == null || sortField.isEmpty()) {
            return "COD_TIPO_COMBUSTIBLE";
        }
        switch (sortField.toLowerCase()) {
            case "codtipocombustible":
                return "COD_TIPO_COMBUSTIBLE";
            case "nomtipocombustible":
                return "NOM_TIPO_COMBUSTIBLE";
            case "estado":
                return "ESTADO";
            default:
                return "COD_TIPO_COMBUSTIBLE";
        }
    }

    private String validateOrder(String order) {
        return (order != null && order.equalsIgnoreCase("desc")) ? "DESC" : "ASC";
    }
}
