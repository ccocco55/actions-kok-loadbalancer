package com.example.kok.mybatis.converter;

import com.example.kok.enumeration.UserRole;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class StringToUserRoleConverter implements Converter<String, UserRole> {
    @Override
    public UserRole convert(@NonNull String source) {
        Map<String, UserRole> userRoleMap =
                Stream.of(UserRole.values())
                        .collect(Collectors.toMap(UserRole::getValue, Function.identity()));

        return userRoleMap.get(source);
    }
}
