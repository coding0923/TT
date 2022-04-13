package com.tt.domain;

import java.nio.file.Path;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDTO {

    private String idx;

    private String folderPath;

    private String originalName;

    private String fileName;

    private String saveName;

    private Path savePath;

    private String deleteYn;

    private LocalDateTime insertTime;

    private String imageURL;

    private String ThumbnailURL;
}
