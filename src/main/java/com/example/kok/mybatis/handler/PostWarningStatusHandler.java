package com.example.kok.mybatis.handler;

import com.example.kok.enumeration.PostWarningStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;

@MappedTypes(PostWarningStatus.class)
public class PostWarningStatusHandler implements TypeHandler<PostWarningStatus> {
    @Override
    public void setParameter(PreparedStatement ps, int i, PostWarningStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter.getValue(), Types.OTHER);
    }

    @Override
    public PostWarningStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        return switch (rs.getString(columnIndex)) {
            case "delete" -> PostWarningStatus.DELETE;
            case "hold" -> PostWarningStatus.HOLD;
                case "wait" -> PostWarningStatus.WAIT;
            default -> null;
        };
    }

    @Override
    public PostWarningStatus getResult(ResultSet rs, String columnName) throws SQLException {
        return switch (rs.getString(columnName)) {
            case "delete" -> PostWarningStatus.DELETE;
            case "hold" -> PostWarningStatus.HOLD;
            case "wait" -> PostWarningStatus.WAIT;
            default -> null;
        };
    }

    @Override
    public PostWarningStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return switch (cs.getString(columnIndex)) {
            case "delete" -> PostWarningStatus.DELETE;
            case "hold" -> PostWarningStatus.HOLD;
            case "wait" -> PostWarningStatus.WAIT;
            default -> null;
        };
    }
}
