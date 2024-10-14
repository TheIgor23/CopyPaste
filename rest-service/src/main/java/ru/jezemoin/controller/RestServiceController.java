package ru.jezemoin.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.jezemoin.service.FileService;

import java.awt.*;

@RestController
public class RestServiceController {

    private final String PASTE = "/paste";

    private final FileService fileService;

    public RestServiceController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = PASTE, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String paste(@RequestParam("file") MultipartFile file) {
        return  String.format("size: %s", file.getSize());
    }

}
