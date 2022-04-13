package com.tt.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tt.domain.ImageDTO;
import com.tt.service.ImageService;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Slf4j
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Value("${file.upload.directory}") // application.properties의 변수
    private String uploadPath;

    @PostMapping("/uploadAjax")
    @ResponseBody
    public ResponseEntity<ImageDTO> uploadFile(MultipartFile uploadFile) { // 배열을 활용하면 동시에 여러개의 파일 정보를
                                                                           // 처리할 수 있음
        ImageDTO image = new ImageDTO();

        // 이미지 파일만 업로드 가능
        if (uploadFile.getContentType().startsWith("image") == false) {
            log.warn("this file is not image type");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        // 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
        String originalName = uploadFile.getOriginalFilename();
        String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

        log.info("fileName: " + fileName);

        // 날짜 폴더 생성
        String folderPath = makeFolder();

        // UUID
        String uuid = UUID.randomUUID().toString();

        // 저장할 파일 이름 중간에 "_"를 이용해서 구분
        String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
        Path savePath = Paths.get(saveName);

        try {
            // 원본 파일 저장
            uploadFile.transferTo(savePath);

            // 섬네일 생성
            String thumbnailSaveName = uploadPath + File.separator + folderPath + File.separator + "s_" + uuid + "_"
                    + fileName;

            // 섬네일 파일 이름은 중간에 s_로 시작하도록
            File thumbnailFile = new File(thumbnailSaveName);
            // 섬네일 생성
            Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 100, 100);

            // ImageURL 생성

            image.setIdx(uuid);
            image.setFolderPath(folderPath);
            image.setOriginalName(originalName);
            image.setFileName(fileName);
            image.setSaveName(saveName);
            image.setSavePath(savePath);
            image.setImageURL(getImageURL(folderPath, uuid, fileName));
            image.setThumbnailURL(getThumbnailURL(folderPath, uuid, fileName));

            System.out.println(image.getIdx());
            System.out.println(image.getFolderPath());
            System.out.println(image.getOriginalName());
            System.out.println(image.getFileName());
            System.out.println(image.getSaveName());
            System.out.println(image.getSavePath());

            System.out.println(image.getImageURL());
            System.out.println(image.getThumbnailURL());

            uploadImage(image);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    private String makeFolder() {

        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace("//", File.separator);

        // make folder -------
        File uploadPathFolder = new File(uploadPath, folderPath);

        if (uploadPathFolder.exists() == false) {
            uploadPathFolder.mkdirs();
        }
        return folderPath;
    }

    private String getImageURL(String folderPath, String uuid, String fileName) {
        try {
            return URLEncoder.encode(folderPath + "/" + uuid + "_" + fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String getThumbnailURL(String folderPath, String uuid, String fileName) {
        try {
            return URLEncoder.encode(folderPath + "/s_" + uuid + "_" + fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void uploadImage(ImageDTO params) {
        try {
            boolean isUploaded = imageService.uploadImage(params);

            if (isUploaded == false) {
                log.info("등록 실패");
            }
        } catch (DataAccessException e) {
            log.info("등록 실패2");

        } catch (Exception e) {
            log.info("등록 실패3");
        }
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName) {

        ResponseEntity<byte[]> result = null;

        try {
            String srcFileName = URLDecoder.decode(fileName, "UTF-8");

            log.info("fileName: " + srcFileName);

            File file = new File(uploadPath + File.separator + srcFileName);

            log.info("file: " + file);

            HttpHeaders header = new HttpHeaders();

            // MIME타입 처리
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            // 파일 데이터 처리
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @PostMapping("/removeFile")
    public ResponseEntity<Boolean> removeFile(String fileName) {

        String srcFileName = null;
        try {
            srcFileName = URLDecoder.decode(fileName, "UTF-8");
            File file = new File(uploadPath + File.separator + srcFileName);
            boolean result = file.delete();

            File thumbnail = new File(file.getParent(), "s_" + file.getName());

            result = thumbnail.delete();

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
