package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.consumirIdependiente.AdmConsumidorIndependienteDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.consumirIdependiente.AdmConsumidorIndependienteFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.consumirIdependiente.AdmConsumidorIndependienteSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmConsumidorIndependienteMapper;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmConsumidorIndependiente;
import pe.gob.osinergmin.prie.admin.backend.services.AdmConsumidorIndependienteService;

import java.util.Date;
import java.util.List;

@Service
public class AdmConsumidorIndependienteServiceImpl implements AdmConsumidorIndependienteService {

    @Autowired
    private AdmConsumidorIndependienteMapper admConsumidorIndependienteMapper;

    @Autowired
    private HttpServletRequest request;


    @Override
    public MessageDTO insert(AdmConsumidorIndependienteFormdDTO record) {
        try {
            String usuario = "Admin";
            if (record.getCodConsumidorInde() == null || record.getCodConsumidorInde().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código del consumidor independiente no puede ser nulo o vacío");
            }
            if (!record.getCodConsumidorInde().matches("\\d+")) {
                return new MessageDTO(Constantes.ERROR, "El código del consumidor independiente solo puede contener números");
            }
            if (record.getCodConsumidorInde().length() > 11) {
                return new MessageDTO(Constantes.ERROR, "El código del consumidor independiente no puede tener más de 11 caracteres");
            }
            if (record.getRazonSocial() != null && record.getRazonSocial().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "La razón social no puede tener más de 100 caracteres");
            }
            if (record.getDireccion() != null && record.getDireccion().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "La dirección no puede tener más de 100 caracteres");
            }
            if (record.getTelefono() != null && record.getTelefono().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "El teléfono no puede tener más de 50 caracteres");
            }
            if (record.getNombreRepLegal() != null && record.getNombreRepLegal().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "El nombre del representante legal no puede tener más de 50 caracteres");
            }
            if (record.getCargoRepLeg() != null && record.getCargoRepLeg().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "El cargo del representante legal no puede tener más de 50 caracteres");
            }
            if (record.getTelefonoRepLeg() != null && record.getTelefonoRepLeg().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "El teléfono del representante legal no puede tener más de 50 caracteres");
            }
            if (record.getCorreoRepLeg() != null && record.getCorreoRepLeg().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "El correo del representante legal no puede tener más de 50 caracteres");
            }

            AdmConsumidorIndependiente existe = admConsumidorIndependienteMapper.selectByPrimaryKey(record.getCodConsumidorInde());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "El consumidor independiente ya existe");
            }

            AdmConsumidorIndependiente admConsumidorIndependiente = new AdmConsumidorIndependiente();
            admConsumidorIndependiente.setCodConsumidorInde(record.getCodConsumidorInde());
            admConsumidorIndependiente.setRazonSocial(record.getRazonSocial());
            admConsumidorIndependiente.setDireccion(record.getDireccion());
            admConsumidorIndependiente.setTelefono(record.getTelefono());
            admConsumidorIndependiente.setNombreRepLegal(record.getNombreRepLegal());
            admConsumidorIndependiente.setCargoRepLeg(record.getCargoRepLeg());
            admConsumidorIndependiente.setTelefonoRepLeg(record.getTelefonoRepLeg());
            admConsumidorIndependiente.setCorreoRepLeg(record.getCorreoRepLeg());

            admConsumidorIndependiente.setUsuarioCreacion(usuario);
            admConsumidorIndependiente.setFechaCreacion(new Date());
            admConsumidorIndependiente.setTerminalCreacion(IpUtils.getClientIp(request));

            admConsumidorIndependienteMapper.insert(admConsumidorIndependiente);
            return new MessageDTO(Constantes.SUCCES, "Se insertó correctamente el consumidor independiente");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public int insertSelective(AdmConsumidorIndependiente record) {
        return admConsumidorIndependienteMapper.insertSelective(record);
    }

    @Override
    public MessageDTO updateByPrimaryKey(AdmConsumidorIndependienteFormdDTO record) {
        try {
            String usuario = "Admin";
            if (record.getCodConsumidorInde() == null || record.getCodConsumidorInde().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código del consumidor independiente no puede ser nulo o vacío");
            }
            if (record.getCodConsumidorInde().length() > 11) {
                return new MessageDTO(Constantes.ERROR, "El código del consumidor independiente no puede tener más de 11 caracteres");
            }

            AdmConsumidorIndependiente existe = admConsumidorIndependienteMapper.selectByPrimaryKey(record.getCodConsumidorInde());
            if (existe == null) {
                return new MessageDTO(Constantes.ERROR, "No se encontró el consumidor independiente para actualizar");
            }
            if (record.getRazonSocial() != null && record.getRazonSocial().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "La razón social no puede tener más de 100 caracteres");
            }
            if (record.getDireccion() != null && record.getDireccion().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "La dirección no puede tener más de 100 caracteres");
            }
            if (record.getTelefono() != null && record.getTelefono().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "El teléfono no puede tener más de 50 caracteres");
            }
            if (record.getNombreRepLegal() != null && record.getNombreRepLegal().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "El nombre del representante legal no puede tener más de 50 caracteres");
            }
            if (record.getCargoRepLeg() != null && record.getCargoRepLeg().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "El cargo del representante legal no puede tener más de 50 caracteres");
            }
            if (record.getTelefonoRepLeg() != null && record.getTelefonoRepLeg().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "El teléfono del representante legal no puede tener más de 50 caracteres");
            }
            if (record.getCorreoRepLeg() != null && record.getCorreoRepLeg().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "El correo del representante legal no puede tener más de 50 caracteres");
            }

            AdmConsumidorIndependiente admConsumidorIndependiente = new AdmConsumidorIndependiente();
            admConsumidorIndependiente.setCodConsumidorInde(record.getCodConsumidorInde());
            admConsumidorIndependiente.setRazonSocial(record.getRazonSocial());
            admConsumidorIndependiente.setDireccion(record.getDireccion());
            admConsumidorIndependiente.setTelefono(record.getTelefono());
            admConsumidorIndependiente.setNombreRepLegal(record.getNombreRepLegal());
            admConsumidorIndependiente.setCargoRepLeg(record.getCargoRepLeg());
            admConsumidorIndependiente.setTelefonoRepLeg(record.getTelefonoRepLeg());
            admConsumidorIndependiente.setCorreoRepLeg(record.getCorreoRepLeg());
            admConsumidorIndependiente.setUsuarioActualizacion(usuario);
            admConsumidorIndependiente.setFechaActualizacion(new Date());
            admConsumidorIndependiente.setTerminalActualizacion(IpUtils.getClientIp(request));

            admConsumidorIndependienteMapper.updateByPrimaryKey(admConsumidorIndependiente);

            return new MessageDTO(Constantes.SUCCES, "Se actualizó correctamente el consumidor independiente");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }


    @Override
    public MessageDTO deleteByPrimaryKey(String codConsumidorInde) {
        try {
            AdmConsumidorIndependiente existe = admConsumidorIndependienteMapper.selectByPrimaryKey(codConsumidorInde);
            if (existe != null) {
                admConsumidorIndependienteMapper.deleteByPrimaryKey(existe.getCodConsumidorInde());
                return new MessageDTO(Constantes.SUCCES, "Se elimino el consumidor idependiente");
            }
            return new MessageDTO(Constantes.ERROR, "no se elimino el consumidor idependiente");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public List<AdmConsumidorIndependienteDTO> selectAll() {
        return admConsumidorIndependienteMapper.selectAll();
    }

    @Override
    public PageInfo<AdmConsumidorIndependienteDTO> filtrar(AdmConsumidorIndependienteSearchDTO admConsumidorIndependienteSearchDTO) {
        String sortField = mapSortField(admConsumidorIndependienteSearchDTO.getSort());
        String order = validateOrder(admConsumidorIndependienteSearchDTO.getOrder());

        if (sortField == null || sortField.isEmpty()) {
            sortField = "COD_CONSUMIDOR_INDE";
        }

        if (order == null || order.isEmpty()) {
            order = "ASC";
        }

        String orderBy = sortField + " " + order;
        PageHelper.startPage(admConsumidorIndependienteSearchDTO.getPage(), admConsumidorIndependienteSearchDTO.getSize(), orderBy);
        return new PageInfo<>(admConsumidorIndependienteMapper.filtrar(admConsumidorIndependienteSearchDTO));
    }


    private String mapSortField(String sort) {
        if (sort == null || sort.isEmpty()) {
            return "COD_CONSUMIDOR_INDE";
        }
        switch (sort) {
            case "codConsumidorInde":
                return "COD_CONSUMIDOR_INDE";
            case "razonSocial":
                return "RAZON_SOCIAL";
            case "telefono":
                return "TELEFONO";
            default:
                return "COD_CONSUMIDOR_INDE";
        }
    }

    private String validateOrder(String order) {
        if ("asc".equalsIgnoreCase(order) || "desc".equalsIgnoreCase(order)) {
            return order.toUpperCase();
        }
        return "ASC";
    }
}
