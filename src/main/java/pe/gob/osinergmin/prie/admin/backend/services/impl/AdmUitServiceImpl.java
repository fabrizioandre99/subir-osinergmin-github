package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.admUit.AdmUitDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.admUit.AdmUitFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.admUit.AdmUitSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmUitMapper;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmUit;
import pe.gob.osinergmin.prie.admin.backend.services.AdmUitService;

import java.util.Date;
import java.util.List;

@Service
public class AdmUitServiceImpl implements AdmUitService {

    @Autowired
    private AdmUitMapper admUitMapper;

    @Override
    public MessageDTO deleteByPrimaryKey(String anioMes) {
       try {
           AdmUit existeUit = admUitMapper.selectByPrimaryKey(anioMes);
           if (existeUit != null) {
               admUitMapper.deleteByPrimaryKey(anioMes);
               return new MessageDTO(Constantes.SUCCES, "El periodo se eliminó correctamente.");
           }
           return new MessageDTO(Constantes.ERROR, "El periodo no existe");
       }catch (Exception e) {
           return new MessageDTO(Constantes.ERROR, e.getMessage());
       }
    }

    @Override
    public MessageDTO insert(AdmUitFormDTO formDTO) throws Exception {
        try {
            MessageDTO validationMessage = validateAnioMesAndValor(formDTO);
            if (validationMessage != null) {
                return validationMessage;
            }
            AdmUit existeUit = admUitMapper.selectByPrimaryKey(formDTO.getAnioMes());
            if (existeUit == null) {
                AdmUit admUitDTO = new AdmUit();
                admUitDTO.setAnioMes(formDTO.getAnioMes());
                admUitDTO.setValor(formDTO.getValor());
                admUitDTO.setAdFecha(new Date());
                admUitDTO.setAdCodUsuario("admin");
                admUitMapper.insert(admUitDTO);

                return new MessageDTO(Constantes.SUCCES, "OK");
            }
            return new MessageDTO(Constantes.ERROR, "Ya existe el periodo.");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public AdmUit selectByPrimaryKey(String anioMes) {
        return admUitMapper.selectByPrimaryKey(anioMes);
    }

    @Override
    public MessageDTO updateByPrimaryKey(AdmUitFormDTO formDTO) throws Exception {
        try {
            MessageDTO validationMessage = validateAnioMesAndValor(formDTO);
            if (validationMessage != null) {
                return validationMessage;
            }
            AdmUit existeUit = admUitMapper.selectByPrimaryKey(formDTO.getAnioMes());
            if (existeUit == null) {
                return new MessageDTO(Constantes.ERROR, "El periodo no existe.");
            }
            AdmUit admUitDTO = new AdmUit();
            admUitDTO.setAnioMes(formDTO.getAnioMes());
            admUitDTO.setValor(formDTO.getValor());
            admUitDTO.setAdFecha(new Date());
            admUitMapper.updateByPrimaryKey(admUitDTO);

            return new MessageDTO(Constantes.SUCCES, "OK");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<AdmUitDTO> selectAll() {
        return admUitMapper.selectAll();
    }

    @Override
    public PageInfo<AdmUitDTO> listarFiltro(AdmUitSearchDTO searchDTO) {
        String sortField = mapSortField(searchDTO.getSort());
        String orderDirection = validateOrder(searchDTO.getOrder());
        String orderBy = sortField + " " + orderDirection;
        PageHelper.startPage(searchDTO.getPage(), searchDTO.getSize(), orderBy);
        return new PageInfo<>(admUitMapper.listarFiltro(searchDTO));
    }

    private String mapSortField(String sort) {
        if (sort == null || sort.isEmpty()) {
            return "ANIO_MES";
        }
        switch (sort) {
            case "anioMes":
                return "ANIO_MES";
            case "valor":
                return "VALOR";
            default:
                return "ANIO_MES";
        }
    }

    private String validateOrder(String order) {
        if ("asc".equalsIgnoreCase(order) || "desc".equalsIgnoreCase(order)) {
            return order.toUpperCase();
        }
        return "ASC";
    }


    private MessageDTO validateAnioMesAndValor(AdmUitFormDTO formDTO) {
        if (formDTO.getAnioMes() == null || formDTO.getAnioMes().trim().isEmpty()) {
            return new MessageDTO(Constantes.ERROR, "El campo 'anioMes' es obligatorio.");
        }
        if (formDTO.getAnioMes().length() != 6) {
            return new MessageDTO(Constantes.ERROR, "El campo 'anioMes' debe tener exactamente 6 caracteres.");
        }
        if (formDTO.getValor() == null) {
            return new MessageDTO(Constantes.ERROR, "El campo 'valor' es obligatorio.");
        }
        return null;
    }
}
