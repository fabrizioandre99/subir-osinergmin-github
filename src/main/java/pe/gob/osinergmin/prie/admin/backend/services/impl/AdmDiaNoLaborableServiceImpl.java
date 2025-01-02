package pe.gob.osinergmin.prie.admin.backend.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import pe.gob.osinergmin.prie.admin.backend.common.Constantes;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmCiiu;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmDiaNoLaborable;
import pe.gob.osinergmin.prie.admin.backend.dto.MessageDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.diaNoLaborable.AdmDiaNoLaborableFormDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.diaNoLaborable.DiaNoLaborableDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.diaNoLaborable.DiaNoLaborableMapperDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.diaNoLaborable.DiaNoLaborableSearchDTO;
import pe.gob.osinergmin.prie.admin.backend.mapper.AdmDiaNoLaborableMapper;
import pe.gob.osinergmin.prie.admin.backend.services.AdmDiaNoLaborableService;
@Service
public class AdmDiaNoLaborableServiceImpl implements AdmDiaNoLaborableService{

    @Autowired
    private AdmDiaNoLaborableMapper admDiaNoLaborableMapper;

    @Override
    public MessageDTO deleteByPrimaryKey(String fecha) {
        try {
            DiaNoLaborableMapperDTO existe = admDiaNoLaborableMapper.buscarFechaString(fecha);
            if (existe != null) {
                admDiaNoLaborableMapper.eliminarString(fecha);
                return new MessageDTO(Constantes.SUCCES, "OK");
            } else {
                return new MessageDTO(Constantes.ERROR, "No se elimino el CIIU");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public MessageDTO insert(AdmDiaNoLaborableFormDTO record) {
        try {
            DiaNoLaborableMapperDTO existe = admDiaNoLaborableMapper.buscarFechaString(record.getFecha());
            if (existe != null) {
                return new MessageDTO(Constantes.ERROR, "Ya existe el ese dia no laborable");
            } else {
                DiaNoLaborableMapperDTO admDiaNoLaborable = new DiaNoLaborableMapperDTO();
                admDiaNoLaborable.setFecha(record.getFecha());
                admDiaNoLaborable.setTipo(record.getTipo());
                admDiaNoLaborable.setMotivo(record.getMotivo());
                admDiaNoLaborable.setAdFecha(new Date());
                admDiaNoLaborable.setEstado("1");
                admDiaNoLaborableMapper.insertarNuevo(admDiaNoLaborable);

                return new MessageDTO(Constantes.SUCCES, "OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int insertSelective(AdmDiaNoLaborable record) {
        return admDiaNoLaborableMapper.insertSelective(record);
    }

    @Override
    public AdmDiaNoLaborable selectByPrimaryKey(Date fecha) {
        return admDiaNoLaborableMapper.selectByPrimaryKey(fecha);
    }

    @Override
    public int updateByPrimaryKeySelective(AdmDiaNoLaborable record) {
        return admDiaNoLaborableMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public MessageDTO updateByPrimaryKey(AdmDiaNoLaborableFormDTO record) {
        try {
            DiaNoLaborableMapperDTO existe = admDiaNoLaborableMapper.buscarFechaString(record.getFecha());

            if (existe != null) {
                DiaNoLaborableMapperDTO admDiaNoLaborable = new DiaNoLaborableMapperDTO();
                admDiaNoLaborable.setFecha(record.getFecha());
                admDiaNoLaborable.setTipo(record.getTipo());
                admDiaNoLaborable.setMotivo(record.getMotivo());
                admDiaNoLaborable.setAdFecha(new Date());
                admDiaNoLaborable.setEstado(record.getEstado());
                admDiaNoLaborableMapper.actualizarNuevo(admDiaNoLaborable);

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
    public List<DiaNoLaborableDTO> enlistarTodos() {
        return admDiaNoLaborableMapper.enlistarTodos();
    }

    @Override
    public PageInfo<DiaNoLaborableDTO> filtrar(DiaNoLaborableSearchDTO diaNoLaborableSearchDTO) {
        try {
            String sortField = mapSortField(diaNoLaborableSearchDTO.getSort());
            String orderDirection = validateOrder(diaNoLaborableSearchDTO.getOrder());
            String orderBy = sortField + " " + orderDirection;

            PageHelper.startPage(diaNoLaborableSearchDTO.getPage(), diaNoLaborableSearchDTO.getSize(), orderBy);
            List<DiaNoLaborableDTO> diasNoLaborables = admDiaNoLaborableMapper.filtrar(diaNoLaborableSearchDTO);

            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");

            diasNoLaborables.forEach(diaNoLaborable -> {
                if (diaNoLaborable.getFecha() != null) {
                    try {
                        Date date = inputFormat.parse(diaNoLaborable.getFecha());
                        String formattedDate = outputFormat.format(date);
                        diaNoLaborable.setFecha(formattedDate);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            return new PageInfo<>(diasNoLaborables);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String mapSortField(String sort) {
        if (sort == null || sort.isEmpty()) {
            return "FECHA";
        }
        switch (sort) {
            case "fecha":
                return "FECHA";
            case "tipo":
                return "TIPO";
            case "motivo":
                return "MOTIVO";
            case "estado":
                return "ESTADO";
            default:
                return "FECHA";
        }
    }

    private String validateOrder(String order) {
        if ("asc".equalsIgnoreCase(order) || "desc".equalsIgnoreCase(order)) {
            return order.toUpperCase();
        }
        return "ASC";
    }

}
