package pe.gob.osinergmin.prie.admin.backend.common;

public class ForeignKeyRelation {
    private String childTable;
    private String childColumn;
    private String parentColumn;

    public ForeignKeyRelation(String childTable, String childColumn, String parentColumn) {
        this.childTable = childTable;
        this.childColumn = childColumn;
        this.parentColumn = parentColumn;
    }

    public String getChildTable() {
        return childTable;
    }

    public String getChildColumn() {
        return childColumn;
    }

    public String getParentColumn() {
        return parentColumn;
    }
}
