package pe.gob.osinergmin.prie.admin.backend.common;

import java.util.*;

public class Constantes {
    public static final String ERROR = "0";
    public static final String SUCCES = "1";

    public static final Map<String, List<ForeignKeyRelation>> TABLE_RELATIONS;

    static {
        Map<String, List<ForeignKeyRelation>> relations = new HashMap<>();

        relations.put("ADM_TIPO_TARIFA", Arrays.asList(
                new ForeignKeyRelation("ADM_TARIFA", "COD_TARIFA", "CODTARIFA")
        ));

        TABLE_RELATIONS = Collections.unmodifiableMap(relations);
    }

    private Constantes() {
    }

}
