package com.example.kok.mybatis.handler;

import com.example.kok.enumeration.Provider;
import com.example.kok.enumeration.Status;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;

@MappedTypes(Provider.class)
public class ProviderHandler implements TypeHandler<Provider> {
    @Override
    public void setParameter(PreparedStatement ps, int i, Provider parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter.getValue(), Types.OTHER);
    }

    @Override
    public Provider getResult(ResultSet rs, int columnIndex) throws SQLException {
        return switch (rs.getString(columnIndex)) {
            case "google" -> Provider.GOOGLE;
            case "kakao" -> Provider.KAKAO;
            case "naver" -> Provider.NAVER;
            case "kok" -> Provider.KOK;
            default -> null;
        };
    }

    @Override
    public Provider getResult(ResultSet rs, String columnName) throws SQLException {
        return switch (rs.getString(columnName)) {
            case "google" -> Provider.GOOGLE;
            case "kakao" -> Provider.KAKAO;
            case "naver" -> Provider.NAVER;
            case "kok" -> Provider.KOK;
            default -> null;
        };
    }

    @Override
    public Provider getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return switch (cs.getString(columnIndex)) {
            case "google" -> Provider.GOOGLE;
            case "kakao" -> Provider.KAKAO;
            case "naver" -> Provider.NAVER;
            case "kok" -> Provider.KOK;
            default -> null;
        };
    }
}
