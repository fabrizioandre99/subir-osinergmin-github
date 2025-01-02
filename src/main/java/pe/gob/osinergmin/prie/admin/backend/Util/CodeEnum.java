package pe.gob.osinergmin.prie.admin.backend.Util;

public enum CodeEnum {
    SUCCESS(0),
    ERROR(1);

    private Integer statusCodeValue;

    private CodeEnum(Integer statusCodeValue) {
        this.statusCodeValue = statusCodeValue;
    }

    public Integer getStatusCodeValue() {
        return this.statusCodeValue;
    }
}
