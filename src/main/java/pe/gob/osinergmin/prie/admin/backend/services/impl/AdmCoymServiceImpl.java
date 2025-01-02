package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.common.IpUtils;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmUbigeo;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.coym.AdmCoymDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.coym.AdmCoymFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.coym.AdmCoymSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmCoymMapper;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmCoym;
import pe.gob.osinergmin.prie.admin.backend.services.AdmCoymService;

import java.util.Date;
import java.util.List;

@Service
public class AdmCoymServiceImpl implements AdmCoymService{

    @Autowired
    private AdmCoymMapper admCoymMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public MessageDTO deleteByPrimaryKey(String codCoym) {
        try {
            AdmCoym existe = admCoymMapper.selectByPrimaryKey(codCoym);
            if (existe != null) {
                admCoymMapper.deleteByPrimaryKey(codCoym);
                return new MessageDTO(Constantes.SUCCES, "OK");
            } else {
                return new MessageDTO(Constantes.ERROR, "No se elimino el Ubigeo");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    @Override
    public MessageDTO insert(AdmCoymFormDTO record) {
        try {
            String usuario = "Admin";
            if (record.getCodCoym() == null || record.getCodCoym().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código de Costo y Mantenimiento no puede estar vacío");
            }
            if (record.getNomCoym() == null || record.getNomCoym().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El nombre de Costo y Mantenimiento no puede estar vacío");
            }
            if (record.getCodCoym().length() > 10) {
                return new MessageDTO(Constantes.ERROR, "El código de Costo y Mantenimiento debe tener un máximo de 10 caracteres");
            }
            if (record.getNomCoym().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "El nombre de Costo y Mantenimiento debe tener un máximo de 50 caracteres");
            }
            AdmCoym existe = admCoymMapper.selectByPrimaryKey(record.getCodCoym());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "Ya existe el código de Costo y Mantenimiento");
            }

            AdmCoym admCoym = new AdmCoym();
            admCoym.setCodCoym(record.getCodCoym());
            admCoym.setNomCoym(record.getNomCoym());
            admCoym.setEstadoRegistro("1");
            admCoym.setFechaCreacion(new Date());
            admCoym.setTerminalCreacion(IpUtils.getClientIp(request));
            admCoym.setUsuarioCreacion(usuario);
            admCoymMapper.insert(admCoym);

            return new MessageDTO(Constantes.SUCCES, "Registro insertado correctamente");

        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Error al insertar el registro: " + e.getMessage());
        }
    }


    @Override
    public int insertSelective(AdmCoym record) {
        return admCoymMapper.insertSelective(record);
    }

    @Override
    public AdmCoym selectByPrimaryKey(String codCoym) {
        return admCoymMapper.selectByPrimaryKey(codCoym);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmCoym record) {
        return admCoymMapper.updateByPrimaryKeySelective(record);
    }


    @Override
    public MessageDTO updateByPrimaryKey(AdmCoymFormDTO record) {
        try {
            String usuario = "Admin";
            if (record.getCodCoym() == null || record.getCodCoym().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El código de Costo y Mantenimiento no puede estar vacío");
            }
            if (record.getNomCoym() == null || record.getNomCoym().trim().isEmpty()) {
                return new MessageDTO(Constantes.ERROR, "El nombre de Costo y Mantenimiento no puede estar vacío");
            }
            if (record.getCodCoym().length() > 10) {
                return new MessageDTO(Constantes.ERROR, "El código de Costo y Mantenimiento debe tener un máximo de 10 caracteres");
            }
            if (record.getNomCoym().length() > 50) {
                return new MessageDTO(Constantes.ERROR, "El nombre de Costo y Mantenimiento debe tener un máximo de 50 caracteres");
            }

            AdmCoym existe = admCoymMapper.selectByPrimaryKey(record.getCodCoym());
            if (existe == null) {
                return new MessageDTO(Constantes.ERROR, "La región no existe, no se puede actualizar");
            }

            existe.setNomCoym(record.getNomCoym());
            existe.setEstadoRegistro("1");
            existe.setFechaActualizacion(new Date());
            existe.setTerminalActualizacion(IpUtils.getClientIp(request));
            existe.setUsuarioActualizacion(usuario);
            admCoymMapper.updateByPrimaryKey(existe);

            return new MessageDTO(Constantes.SUCCES, "Registro actualizado correctamente");

        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(Constantes.ERROR, "Error al actualizar el registro: " + e.getMessage());
        }
    }


    @Override
    public List<AdmCoymDTO> selectAll() {
        return admCoymMapper.selectAll();
    }

    @Override
    public PageInfo<AdmCoymDTO> filtrarCoym(AdmCoymSearchDTO admCoymSearchDTO) {
        try {
            PageHelper.startPage(admCoymSearchDTO.getPage(), admCoymSearchDTO.getSize());
            return new PageInfo<>(admCoymMapper.filtrarCoym(admCoymSearchDTO));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
