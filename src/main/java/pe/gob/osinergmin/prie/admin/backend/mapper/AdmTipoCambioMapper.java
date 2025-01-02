package pe.gob.osinergmin.prie.admin.backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pe.gob.osinergmin.prie.admin.backend.domain.AdmTipoCambio;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCambio.TipoCambioCrearActDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCambio.TipoCambioDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCambio.TipoCambioResultDTO;
import pe.gob.osinergmin.prie.admin.backend.dto.tipoCambio.TipoCambioSearchDTO;

import java.util.Date;
import java.util.List;

@Mapper
public interface AdmTipoCambioMapper {
    int deleteByPrimaryKey(@Param("fecha") String fecha);

    int insert(AdmTipoCambio record);

    AdmTipoCambio selectByPrimaryKey(@Param("moneda") String moneda, @Param("fuente") String fuente, @Param("fecha") String fecha);

    int updateByPrimaryKey(AdmTipoCambio record);

    List<TipoCambioResultDTO> selectAll();

    List<TipoCambioResultDTO> selectByFecha(TipoCambioSearchDTO tipoCambioSearchDTO);

    int existe ( @Param("fuente") String fuente);

    int obtenerFecha(@Param("fecha") String fecha);

    int insertarNuevo (TipoCambioCrearActDTO tipoCambioCrearActDTO);

    int actualizarNuevo (TipoCambioCrearActDTO tipoCambioCrearActDTO);
}
