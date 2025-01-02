package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmModuloSupervision;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.moduloSupervision.AdmModuloSupervisionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.moduloSupervision.AdmModuloSupervisionFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.moduloSupervision.AdmModuloSupervisionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmModuloSupervisionMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmModuloSupervisionService;

import java.util.Date;
import java.util.List;

@Service
public class AdmModuloSupervisionServiceImpl implements AdmModuloSupervisionService {

    @Autowired
    private AdmModuloSupervisionMapper admModuloSupervisionMapper;

    @Autowired
    private HttpServletRequest request;

    @Transactional
    @Override
    public MessageDTO insert(AdmModuloSupervisionFormDTO record) {
        try {
            if (record.getCoModulo() == null || record.getCoModulo().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código del módulo no puede estar vacío.");
            }
            if (record.getCoModulo().length() > 8) {
                return new MessageDTO(Constantes.ERROR, "El código del módulo no puede tener más de 8 caracteres.");
            }
            if (record.getDeModulo() == null || record.getDeModulo().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "La descripción del módulo no puede estar vacía.");
            }
            if (record.getDeModulo().length() > 350) {
                return new MessageDTO(Constantes.ERROR, "La descripción del módulo no puede tener más de 350 caracteres.");
            }
            if (record.getDeCorta() != null && record.getDeCorta().length() > 30) {
                return new MessageDTO(Constantes.ERROR, "La descripción corta no puede tener más de 30 caracteres.");
            }
            if (record.getEstado() == null || record.getEstado().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El estado no puede estar vacío.");
            }
            if (record.getEstado().length() > 1) {
                return new MessageDTO(Constantes.ERROR, "El estado no puede tener más de 1 caracter.");
            }

            AdmModuloSupervision existe = admModuloSupervisionMapper.selectByPrimaryKey(record.getCoModulo());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "El módulo ya existe.");
            }

            AdmModuloSupervision admModuloSupervision = new AdmModuloSupervision();
            admModuloSupervision.setCoModulo(record.getCoModulo());
            admModuloSupervision.setDeModulo(record.getDeModulo());
            admModuloSupervision.setDeCorta(record.getDeCorta());
            admModuloSupervision.setEstado(record.getEstado());
            admModuloSupervision.setUsCreacion("admin");
            admModuloSupervision.setFeCreacion(new Date());
            admModuloSupervision.setTerminalCreacion(IpUtils.getClientIp(request));

            admModuloSupervisionMapper.insert(admModuloSupervision);

            return new MessageDTO(Constantes.SUCCES, "Módulo insertado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }
    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(AdmModuloSupervisionFormDTO record) {
        try {
            if (record.getCoModulo() == null || record.getCoModulo().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código del módulo no puede estar vacío.");
            }
            if (record.getCoModulo().length() > 8) {
                return new MessageDTO(Constantes.ERROR, "El código del módulo no puede tener más de 8 caracteres.");
            }
            if (record.getDeModulo() != null && record.getDeModulo().length() > 350) {
                return new MessageDTO(Constantes.ERROR, "La descripción del módulo no puede tener más de 350 caracteres.");
            }
            if (record.getDeCorta() != null && record.getDeCorta().length() > 30) {
                return new MessageDTO(Constantes.ERROR, "La descripción corta no puede tener más de 30 caracteres.");
            }
            if (record.getEstado() != null && record.getEstado().length() > 1) {
                return new MessageDTO(Constantes.ERROR, "El estado no puede tener más de 1 caracter.");
            }

            AdmModuloSupervision existe = admModuloSupervisionMapper.selectByPrimaryKey(record.getCoModulo());
            if (existe == null) {
                return new MessageDTO(Constantes.ERROR, "No se encontró el módulo para actualizar.");
            }

            AdmModuloSupervision admModuloSupervision = new AdmModuloSupervision();
            admModuloSupervision.setCoModulo(record.getCoModulo());
            admModuloSupervision.setDeModulo(record.getDeModulo());
            admModuloSupervision.setDeCorta(record.getDeCorta());
            admModuloSupervision.setEstado(record.getEstado());
            admModuloSupervision.setUsActualizacion("admin");
            admModuloSupervision.setFeActualizacion(new Date());
            admModuloSupervision.setTerminalActualizacion(IpUtils.getClientIp(request));
            admModuloSupervisionMapper.updateByPrimaryKeySelective(admModuloSupervision);

            return new MessageDTO(Constantes.SUCCES, "Módulo actualizado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Transactional
    @Override
    public MessageDTO deleteByPrimaryKey(String coModulo) {
        try {
            if (coModulo == null || coModulo.isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código del módulo no puede estar vacío.");
            }
            if (coModulo.length() > 8) {
                return new MessageDTO(Constantes.ERROR, "El código del módulo no puede tener más de 8 caracteres.");
            }

            AdmModuloSupervision existe = admModuloSupervisionMapper.selectByPrimaryKey(coModulo);
            if (existe == null) {
                return new MessageDTO(Constantes.ERROR, "No se encontró el módulo para eliminar.");
            }

            admModuloSupervisionMapper.deleteByPrimaryKey(coModulo);
            return new MessageDTO(Constantes.SUCCES, "Módulo eliminado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public AdmModuloSupervision selectByPrimaryKey(String coModulo) {
        return admModuloSupervisionMapper.selectByPrimaryKey(coModulo);
    }

    @Override
    public List<AdmModuloSupervisionDTO> selectAll() {
        return admModuloSupervisionMapper.selectAll();
    }

    @Override
    public PageInfo<AdmModuloSupervisionDTO> filtrar(AdmModuloSupervisionSearchDTO searchDTO) {
        String sortField = mapSortField(searchDTO.getSort());
        String sortOrder = validateOrder(searchDTO.getOrder());

        PageHelper.startPage(searchDTO.getPage(), searchDTO.getSize());
        PageHelper.orderBy(sortField + " " + sortOrder);

        List<AdmModuloSupervisionDTO> resultados = admModuloSupervisionMapper.filtrar(searchDTO);
        return new PageInfo<>(resultados);
    }

    private String mapSortField(String sortField) {
        if (sortField == null || sortField.isEmpty()) {
            return "CO_MODULO";
        }
        switch (sortField.toLowerCase()) {
            case "comodulo":
                return "CO_MODULO";
            case "demodulo":
                return "DE_MODULO";
            case "decorta":
                return "DE_CORTA";
            case "estado":
                return "ESTADO";
            default:
                return "CO_MODULO";
        }
    }

    private String validateOrder(String order) {
        return (order != null && order.equalsIgnoreCase("desc")) ? "desc" : "asc";
    }
}
