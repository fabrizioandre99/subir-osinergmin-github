package pe.gob.osinergmin.prie.admin.backend.services.impl;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmProcEmpresa;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.funcionProcSuperv.AdmFuncionProcSupervDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.funcionProcSuperv.AdmFuncionProcSupervFormDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmFuncionProcSupervMapper;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmFuncionProcSuperv;
import pe.gob.osinergmin.prie.admin.backend.services.AdmFuncionProcSupervService;

import java.util.Date;
import java.util.List;

@Service
public class AdmFuncionProcSupervServiceImpl implements AdmFuncionProcSupervService{

    @Autowired
    private AdmFuncionProcSupervMapper admFuncionProcSupervMapper;

    @Override
    public MessageDTO deleteByPrimaryKey(String codProcSupervision, String codFuncionProcSuperv) {
        try {
            AdmFuncionProcSuperv existe = admFuncionProcSupervMapper.selectByPrimaryKey(codProcSupervision, codFuncionProcSuperv);

            if (existe != null && existe.getCodProcSupervision().equals(codProcSupervision)
                    && existe.getCodFuncionProcSuperv().equals(codFuncionProcSuperv)) {
                admFuncionProcSupervMapper.deleteByPrimaryKey(codProcSupervision, codFuncionProcSuperv);
                return new MessageDTO(Constantes.SUCCES, "OK");
            } else {
                return new MessageDTO(Constantes.ERROR, "No se pudo eliminar la función supervisor, los valores no coinciden.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    @Override
    public MessageDTO insert(AdmFuncionProcSupervFormDTO record) {
        try {
            AdmFuncionProcSuperv existe = admFuncionProcSupervMapper.selectByPrimaryKey(record.getCodProcSupervision(), record.getCodFuncionProcSuperv());
            String existeCodFuncion = admFuncionProcSupervMapper.existeCodFuncion(record.getCodFuncionProcSuperv());

            if (existe != null && existeCodFuncion != null) {
                return new MessageDTO(Constantes.ERROR, "Ya existe la función proceso supervisor y el código de función.");
            }else {
                AdmFuncionProcSuperv admFuncionProcSuperv = new AdmFuncionProcSuperv();
                admFuncionProcSuperv.setCodProcSupervision(record.getCodProcSupervision());
                admFuncionProcSuperv.setDscFuncionProcSuperv(record.getDscFuncionProcSuperv());
                admFuncionProcSuperv.setCodFuncionProcSuperv(record.getCodFuncionProcSuperv());
                admFuncionProcSuperv.setAdFecha(new Date());

                admFuncionProcSupervMapper.insert(admFuncionProcSuperv);

                return new MessageDTO(Constantes.SUCCES, "OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }



    @Override
    public int insertSelective(AdmFuncionProcSuperv record) {
        return admFuncionProcSupervMapper.insertSelective(record);
    }

    @Override
    public AdmFuncionProcSuperv selectByPrimaryKey(String codProcSupervision,String codFuncionProcSuperv) {
        return admFuncionProcSupervMapper.selectByPrimaryKey(codProcSupervision,codFuncionProcSuperv);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmFuncionProcSuperv record) {
        return admFuncionProcSupervMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public MessageDTO updateByPrimaryKey(AdmFuncionProcSupervFormDTO record) {
        try {
            AdmFuncionProcSuperv existe = admFuncionProcSupervMapper.selectByPrimaryKey(record.getCodProcSupervision(), record.getCodFuncionProcSuperv());

            if (existe != null &&
                    record.getCodProcSupervision() != null &&
                    record.getCodFuncionProcSuperv() != null &&
                    record.getCodProcSupervision().equals(existe.getCodProcSupervision()) &&
                    record.getCodFuncionProcSuperv().equals(existe.getCodFuncionProcSuperv())) {

                AdmFuncionProcSuperv admFuncionProcSuperv = new AdmFuncionProcSuperv();
                admFuncionProcSuperv.setCodProcSupervision(record.getCodProcSupervision());
                admFuncionProcSuperv.setDscFuncionProcSuperv(record.getDscFuncionProcSuperv());
                admFuncionProcSuperv.setCodFuncionProcSuperv(record.getCodFuncionProcSuperv());
                admFuncionProcSuperv.setAdFecha(new Date());

                admFuncionProcSupervMapper.updateByPrimaryKey(admFuncionProcSuperv);

                return new MessageDTO(Constantes.SUCCES, "OK");
            } else {
                return new MessageDTO(Constantes.ERROR, "No se actualizó, el registro no coincide.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    @Override
    public List<AdmFuncionProcSupervDTO> listar() {
        try {
            return admFuncionProcSupervMapper.listar();
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
