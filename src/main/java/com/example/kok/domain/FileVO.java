package com.example.kok.domain;

import com.example.kok.audit.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper=true)
@EqualsAndHashCode(of="id", callSuper = false)
@SuperBuilder
public class FileVO extends Period{
    private Long id;
    private String fileOriginName;
    private String fileName;
    private String fileSize;
    private String filePath;
    private String fileContentType;
}
