package com.example.kok.mybatis.converter;

import com.example.kok.enumeration.Provider;
import com.example.kok.enumeration.UserRole;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class StringToProvider implements Converter<String, Provider> {
    @Override
    public Provider convert(@NonNull String source) {
        Map<String, Provider> providerMap =
                Stream.of(Provider.values())
                        .collect(Collectors.toMap(Provider::getValue, Function.identity()));

        return providerMap.get(source);
    }
}
