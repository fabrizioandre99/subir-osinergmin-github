package pe.gob.osinergmin.prie.admin.backend.common;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringListTypeHandler extends BaseTypeHandler<List<String>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, String.join(",", parameter));
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String columnValue = rs.getString(columnName);
        return parseStringToList(columnValue);
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String columnValue = rs.getString(columnIndex);
        return parseStringToList(columnValue);
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String columnValue = cs.getString(columnIndex);
        return parseStringToList(columnValue);
    }

    private List<String> parseStringToList(String columnValue) {
        if (columnValue == null || columnValue.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.asList(columnValue.replace("[", "").replace("]", "").replace("\"", "").split(","));
    }
}
