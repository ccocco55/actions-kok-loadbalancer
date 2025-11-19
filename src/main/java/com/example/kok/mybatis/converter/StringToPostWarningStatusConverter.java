package com.example.kok.mybatis.converter;

import com.example.kok.enumeration.PostWarningStatus;
import io.micrometer.common.lang.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class StringToPostWarningStatusConverter implements Converter<String, PostWarningStatus> {
    @Override
    public PostWarningStatus convert(@NonNull String source) {
        Map<String, PostWarningStatus> statusMap=
                Stream.of(PostWarningStatus.values())
                        .collect(Collectors.toMap(PostWarningStatus::getValue, Function.identity()));

        return statusMap.get(source);
    }
}
