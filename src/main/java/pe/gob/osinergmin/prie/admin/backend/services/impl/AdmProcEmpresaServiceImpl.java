package pe.gob.osinergmin.prie.admin.backend.services.impl;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmEmpresa;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmProcEmpresa;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTipoEmpresa;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procEmpresa.*;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmProcEmpresaMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmProcEmpresaService;
import pe.gob.osinergmin.prie.admin.backend.dto.empresa.AdmProcEmpresaDTO;

import java.net.InetAddress;
import java.util.Date;
import java.util.List;

@Service
public class AdmProcEmpresaServiceImpl implements AdmProcEmpresaService{

    @Autowired
    private AdmProcEmpresaMapper admProcEmpresaMapper;

    @Override
    public MessageDTO deleteByPrimaryKey(Integer idProcEmpresa) {
        try {
            AdmProcEmpresa existe = admProcEmpresaMapper.selectByPrimaryKey(idProcEmpresa);
            if (existe != null) {
                admProcEmpresaMapper.deleteByPrimaryKey(idProcEmpresa);
                return new MessageDTO(Constantes.SUCCES, "OK");
            } else {
                return new MessageDTO(Constantes.ERROR, "El Codigo de la empresa no existe");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public MessageDTO insert(AdmProcEmpresaFormDTO record) {
        try {
            AdmProcEmpresa existe = admProcEmpresaMapper.selectByPrimaryKey(record.getIdProcEmpresa());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "Ya existe el proceso Empresa");
            } else {
                AdmProcEmpresa admProcEmpresa = new AdmProcEmpresa();
                admProcEmpresa.setIdProcEmpresa(record.getIdProcEmpresa());
                admProcEmpresa.setCodEmpresa(record.getCodEmpresa());
                admProcEmpresa.setCodTipoEmpresa(record.getCodTipoEmpresa());
                admProcEmpresa.setCodProcSupervision(record.getCodProcSupervision());
                admProcEmpresa.setCodFuncionProcSuperv(record.getCodFuncionProcSuperv());
                admProcEmpresa.setAdFecha(new Date());
                admProcEmpresaMapper.insert(admProcEmpresa);

                return new MessageDTO(Constantes.SUCCES, "OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int insertSelective(AdmProcEmpresa record) {
        return admProcEmpresaMapper.insertSelective(record);
    }

    @Override
    public AdmProcEmpresa selectByPrimaryKey(Integer idProcEmpresa) {
        return admProcEmpresaMapper.selectByPrimaryKey(idProcEmpresa);
    }

//    @Override
//    public int updateByPrimaryKeySelective(AdmProcEmpresa record) {
//        return admProcEmpresaMapper.updateByPrimaryKeySelective(record);
//    }

    @Override
    public MessageDTO updateByPrimaryKey(AdmProcEmpresaFormDTO record) {
        try {
            AdmProcEmpresa existe = admProcEmpresaMapper.selectByPrimaryKey(record.getIdProcEmpresa());

            if (existe != null) {
                AdmProcEmpresa admProcEmpresa = new AdmProcEmpresa();
                admProcEmpresa.setIdProcEmpresa(record.getIdProcEmpresa());
                admProcEmpresa.setCodEmpresa(record.getCodEmpresa());
                admProcEmpresa.setCodTipoEmpresa(record.getCodTipoEmpresa());
                admProcEmpresa.setCodProcSupervision(record.getCodProcSupervision());
                admProcEmpresa.setCodFuncionProcSuperv(record.getCodFuncionProcSuperv());
                admProcEmpresa.setAdFecha(new Date());
                admProcEmpresaMapper.updateByPrimaryKey(admProcEmpresa);
                return new MessageDTO(Constantes.SUCCES, "OK");
            } else {
                return new MessageDTO(Constantes.ERROR, "No se Actualizo");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<AdmProcEmpresaDTO> selectAll() {
        try {
            return admProcEmpresaMapper.selectAll();
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<AdmProcEmpresaResultDTO> listarCreacionEmpresa() {
        try {
            return admProcEmpresaMapper.listarCreacionEmpresa();
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<AdmProcEmpresaDTO> listarPorCodEmpresa(String codEmpresa) {
        return admProcEmpresaMapper.listarPorCodEmpresa(codEmpresa);
    }

    @Override
    public List<AdmProcEmpresaFuncionDTO> listarFuncion(String codProcSupervision) {
        return admProcEmpresaMapper.listarFuncion(codProcSupervision);
    }

    @Override
    public List<AdmProcEmpresaDTO> listarPorCodEmpresaFunTipo(String codEmpresa) {
        return admProcEmpresaMapper.listarPorCodEmpresaFunTipo(codEmpresa);
    }

}
