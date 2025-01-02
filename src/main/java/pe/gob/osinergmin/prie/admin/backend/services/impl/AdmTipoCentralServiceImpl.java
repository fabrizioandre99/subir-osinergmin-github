package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTipoCentral;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCentral.AdmTipoCentralDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCentral.AdmTipoCentralFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCentral.AdmTipoCentralSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmTipoCentralMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmTipoCentralService;

import java.util.Date;
import java.util.List;

@Service
public class AdmTipoCentralServiceImpl implements AdmTipoCentralService {

    @Autowired
    private AdmTipoCentralMapper admTipoCentralMapper;

    @Transactional
    @Override
    public MessageDTO deleteByPrimaryKey(String codTipoCentral) {
        try {

            if (codTipoCentral == null || codTipoCentral.length() != 2) {
                return new MessageDTO(Constantes.ERROR, "El código de tipo central es obligatorio y debe tener exactamente 1 caracteres.");
            }

            AdmTipoCentral existe = admTipoCentralMapper.selectByPrimaryKey(codTipoCentral);
            if (existe != null) {
                admTipoCentralMapper.deleteByPrimaryKey(codTipoCentral);
                return new MessageDTO(Constantes.SUCCES, "Tipo central eliminado exitosamente");
            }

            return new MessageDTO(Constantes.ERROR, "Tipo central no existe");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Transactional
    @Override
    public MessageDTO insert(AdmTipoCentralFormdDTO record) {
        try {

            if (record.getCodTipoCentral() == null || record.getCodTipoCentral().length() < 1 || record.getCodTipoCentral().length() > 2){
                return new MessageDTO(Constantes.ERROR, "El código de tipo central es obligatorio y puede tener hasta 2 caracteres.");
            }
            if (record.getNomTipoCentral() != null && record.getNomTipoCentral().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "El nombre del tipo central no puede exceder los 50 caracteres.");
            }
            if (record.getEstado() == null || record.getEstado().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El estado del tipo central es obligatorio y debe ser un solo carácter.");
            }

            AdmTipoCentral existe = admTipoCentralMapper.selectByPrimaryKey(record.getCodTipoCentral());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "Tipo central ya existe");
            }

            AdmTipoCentral admTipoCentral = new AdmTipoCentral();
            admTipoCentral.setCodTipoCentral(record.getCodTipoCentral());
            admTipoCentral.setNomTipoCentral(record.getNomTipoCentral());
            admTipoCentral.setEstado(record.getEstado());
            admTipoCentral.setAdFecha(new Date());
            admTipoCentral.setAdCodUsuario("admin");
            admTipoCentralMapper.insert(admTipoCentral);

            return new MessageDTO(Constantes.SUCCES, "Tipo central insertado exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(AdmTipoCentralFormdDTO record) {
        try {
            if (record.getCodTipoCentral() == null || record.getCodTipoCentral().length() < 1 || record.getCodTipoCentral().length() > 2) {
                return new MessageDTO(Constantes.ERROR, "El código de tipo central es obligatorio y puede tener hasta 2 caracteres.");
            }
            if (record.getNomTipoCentral() != null && record.getNomTipoCentral().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "El nombre del tipo central no puede exceder los 50 caracteres.");
            }
            if (record.getEstado() == null || record.getEstado().length() != 1) {
                return new MessageDTO(Constantes.ERROR, "El estado del tipo central es obligatorio y debe ser un solo carácter.");
            }

            AdmTipoCentral existe = admTipoCentralMapper.selectByPrimaryKey(record.getCodTipoCentral());
            if (existe == null) {
                return new MessageDTO(Constantes.ERROR, "Tipo central no existe");
            }

            AdmTipoCentral admTipoCentral = new AdmTipoCentral();
            admTipoCentral.setCodTipoCentral(record.getCodTipoCentral());
            admTipoCentral.setNomTipoCentral(record.getNomTipoCentral());
            admTipoCentral.setEstado(record.getEstado());
            admTipoCentral.setAdFecha(new Date());
            admTipoCentral.setAdCodUsuario("admin");
            admTipoCentralMapper.updateByPrimaryKey(admTipoCentral);

            return new MessageDTO(Constantes.SUCCES, "Tipo central actualizado exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, e.getMessage());
        }
    }

    @Override
    public List<AdmTipoCentralDTO> selectAll() {
        return admTipoCentralMapper.selectAll();
    }

    @Override
    public PageInfo<AdmTipoCentralDTO> filtrar(AdmTipoCentralSearchDTO admTipoCentralSearchDTO) {
        String sortField = mapSortField(admTipoCentralSearchDTO.getSort());
        String sortOrder = validateOrder(admTipoCentralSearchDTO.getOrder());

        PageHelper.startPage(admTipoCentralSearchDTO.getPage(), admTipoCentralSearchDTO.getSize());
        PageHelper.orderBy(sortField + " " + sortOrder);

        return new PageInfo<>(admTipoCentralMapper.filtrar(admTipoCentralSearchDTO));
    }

    private String mapSortField(String sortField) {
        if (sortField == null || sortField.isEmpty()) {
            return "COD_TIPO_CENTRAL";
        }
        switch (sortField.toLowerCase()) {
            case "codtipocentral":
                return "COD_TIPO_CENTRAL";
            case "nomtipocentral":
                return "NOM_TIPO_CENTRAL";
            case "estado":
                return "ESTADO";
            default:
                return "COD_TIPO_CENTRAL";
        }
    }

    private String validateOrder(String order) {
        return (order != null && order.equalsIgnoreCase("desc")) ? "DESC" : "ASC";
    }

}
