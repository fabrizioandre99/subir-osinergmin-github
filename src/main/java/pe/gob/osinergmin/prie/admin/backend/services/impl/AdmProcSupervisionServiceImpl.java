package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmFuncionProcSuperv;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmProcEmpresa;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmProcSupervision;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.funcionProcSuperv.FuncionFormdDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procSupervision.AdmProcSupervisionDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procSupervision.AdmProcSupervisionFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.procSupervision.AdmProcSupervisionSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmFuncionProcSupervMapper;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmProcSectorTipicoMapper;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmProcSupervisionMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmProcSupervisionService;

import java.util.Date;
import java.util.List;

@Service
public class AdmProcSupervisionServiceImpl implements AdmProcSupervisionService{

    @Autowired
    private AdmProcSupervisionMapper admProcSupervisionMapper;

    @Autowired
    private AdmFuncionProcSupervMapper admFuncionProcSupervMapper;

    @Autowired
    private AdmProcSectorTipicoMapper admProcSectorTipicoMapper;


    @Override
    public MessageDTO deleteByPrimaryKey(String codProcSupervision) {
        try {
            AdmProcSupervision existe = admProcSupervisionMapper.selectByPrimaryKey(codProcSupervision);
            if (existe != null) {
                admFuncionProcSupervMapper.eliminar(codProcSupervision);
                admProcSectorTipicoMapper.borrarPorCodProcesoSupervisor(codProcSupervision);
                admProcSupervisionMapper.deleteByPrimaryKey(codProcSupervision);
                return new MessageDTO(Constantes.SUCCES, "OK");
            } else {
                return new MessageDTO(Constantes.ERROR, "El Código de la supervisión no existe");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    @Override
    public MessageDTO insert(AdmProcSupervisionFormDTO record) {
        try {

            if (record.getCodProcSupervision() == null || record.getCodProcSupervision().length() < 1 || record.getCodProcSupervision().length() > 6) {
                return new MessageDTO(Constantes.ERROR, "El código de supervisión debe tener entre 1 y 6 caracteres.");
            }

            if (record.getNomProcSupervision() != null && record.getNomProcSupervision().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "El nombre de supervisión no debe exceder los 100 caracteres.");
            }

            if (record.getAbrevProcSupervision() != null && record.getAbrevProcSupervision().length() > 20) {
                return new MessageDTO(Constantes.ERROR, "La abreviatura de supervisión no debe exceder los 20 caracteres.");
            }

            AdmProcSupervision existe = admProcSupervisionMapper.selectByPrimaryKey(record.getCodProcSupervision());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "Ya existe el proceso Supervisor");
            } else {
                AdmProcSupervision admProcSupervision = new AdmProcSupervision();
                admProcSupervision.setCodProcSupervision(record.getCodProcSupervision());
                admProcSupervision.setNomProcSupervision(record.getNomProcSupervision());
                admProcSupervision.setAbrevProcSupervision(record.getAbrevProcSupervision());
                admProcSupervision.setCoModulo(record.getCoModulo());
                admProcSupervision.setAdFecha(new Date());
                admProcSupervision.setEstado(record.getEstado());
                admProcSupervisionMapper.insert(admProcSupervision);

                if (record.getFunciones() != null && !record.getFunciones().isEmpty()) {
                    for (FuncionFormdDTO funcion : record.getFunciones()) {
                        AdmFuncionProcSuperv admFuncionProcSuperv = new AdmFuncionProcSuperv();
                        admFuncionProcSuperv.setCodProcSupervision(record.getCodProcSupervision());
                        admFuncionProcSuperv.setCodFuncionProcSuperv(funcion.getCodFuncionProcSuperv());
                        admFuncionProcSuperv.setDscFuncionProcSuperv(funcion.getDscFuncionProcSuperv());
                        admFuncionProcSuperv.setAdCodUsuario("admin");
                        admFuncionProcSuperv.setAdFecha(new Date());
                        admFuncionProcSupervMapper.insert(admFuncionProcSuperv);
                    }
                }

                return new MessageDTO(Constantes.SUCCES, "OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    @Override
    public int insertSelective(AdmProcSupervision record) {
        return admProcSupervisionMapper.insertSelective(record);
    }

    @Override
    public AdmProcSupervision selectByPrimaryKey(String codProcSupervision) {
        return admProcSupervisionMapper.selectByPrimaryKey(codProcSupervision);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmProcSupervision record) {
        return admProcSupervisionMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    @Override
    public MessageDTO updateByPrimaryKey(AdmProcSupervisionFormDTO record) {
        try {
            if (record.getCodProcSupervision() == null || record.getCodProcSupervision().length() < 1 || record.getCodProcSupervision().length() > 6) {
                return new MessageDTO(Constantes.ERROR, "El código de supervisión debe tener entre 1 y 6 caracteres.");
            }

            if (record.getNomProcSupervision() != null && record.getNomProcSupervision().length() > 100) {
                return new MessageDTO(Constantes.ERROR, "El nombre de supervisión no debe exceder los 100 caracteres.");
            }

            if (record.getAbrevProcSupervision() != null && record.getAbrevProcSupervision().length() > 20) {
                return new MessageDTO(Constantes.ERROR, "La abreviatura de supervisión no debe exceder los 20 caracteres.");
            }

            AdmProcSupervision existe = admProcSupervisionMapper.selectByPrimaryKey(record.getCodProcSupervision());

            if (existe != null) {
                AdmProcSupervision admProcSupervision = new AdmProcSupervision();
                admProcSupervision.setCodProcSupervision(record.getCodProcSupervision());
                admProcSupervision.setNomProcSupervision(record.getNomProcSupervision());
                admProcSupervision.setCoModulo(record.getCoModulo());
                admProcSupervision.setAbrevProcSupervision(record.getAbrevProcSupervision());
                admProcSupervision.setAdFecha(new Date());
                admProcSupervision.setEstado(record.getEstado());
                admProcSupervisionMapper.updateByPrimaryKey(admProcSupervision);
                admFuncionProcSupervMapper.eliminar(record.getCodProcSupervision());

                if (record.getFunciones() != null && !record.getFunciones().isEmpty()) {
                    for (FuncionFormdDTO funcion : record.getFunciones()) {
                        AdmFuncionProcSuperv admFuncionProcSuperv = new AdmFuncionProcSuperv();
                        admFuncionProcSuperv.setCodProcSupervision(record.getCodProcSupervision());
                        admFuncionProcSuperv.setCodFuncionProcSuperv(funcion.getCodFuncionProcSuperv());
                        admFuncionProcSuperv.setDscFuncionProcSuperv(funcion.getDscFuncionProcSuperv());
                        admFuncionProcSuperv.setAdCodUsuario("usuario_actual");
                        admFuncionProcSuperv.setAdFecha(new Date());
                        admFuncionProcSupervMapper.insert(admFuncionProcSuperv);
                    }
                }

                return new MessageDTO(Constantes.SUCCES, "OK");
            } else {
                return new MessageDTO(Constantes.ERROR, "No se Actualizó");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    @Override
    public List<AdmProcSupervisionDTO> selectAll() {
        return admProcSupervisionMapper.selectAll();
    }

    @Override
    public PageInfo<AdmProcSupervisionDTO> filtrar(AdmProcSupervisionSearchDTO admProcSupervisionSearchDTO) {
        String sortField = mapSortField(admProcSupervisionSearchDTO.getSort());
        String order = validateOrder(admProcSupervisionSearchDTO.getOrder());

        if (sortField.isEmpty()) {
            sortField = "COD_PROC_SUPERVISION";
        }
        if (order.isEmpty()) {
            order = "ASC";
        }

        String orderBy = sortField + " " + order;
        PageHelper.startPage(admProcSupervisionSearchDTO.getPage(), admProcSupervisionSearchDTO.getSize(), orderBy);

        return new PageInfo<>(admProcSupervisionMapper.filtrar(admProcSupervisionSearchDTO));
    }

    private String mapSortField(String sort) {
        if (sort == null || sort.isEmpty()) {
            return "COD_PROC_SUPERVISION";
        }
        switch (sort) {
            case "codProcSupervision":
                return "COD_PROC_SUPERVISION";
            case "nomProcSupervision":
                return "NOM_PROC_SUPERVISION";
            case "abrevProcSupervision":
                return "ABREV_PROC_SUPERVISION";
            case "coModulo":
                return "CO_MODULO";
            default:
                return "COD_PROC_SUPERVISION";
        }
    }

    private String validateOrder(String order) {
        if ("asc".equalsIgnoreCase(order) || "desc".equalsIgnoreCase(order)) {
            return order.toUpperCase();
        }
        return "ASC";
    }

}
