package ru.jezemoin.restservice.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.jezemoin.restservice.service.RestService;
import ru.jezemoin.validation.FileValid;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@Log4j2
@Validated
public class RestServiceController {

    private final String PASTE = "/paste";
    private final String GET = "/get/{fileName}";

    private final RestService restService;

    public RestServiceController(RestService restService) {
        this.restService = restService;
    }

    @PostMapping(value = PASTE, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String paste(@FileValid @RequestPart("file") MultipartFile file) {
        return  restService.uploadFile(file);
    }

    @GetMapping(GET)
    public void get(HttpServletResponse res, @PathVariable String fileName) throws IOException {

        try (InputStream in = restService.getFile(fileName)) {
            res.setHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
            res.setCharacterEncoding("UTF-8");

            IOUtils.copy(in, res.getOutputStream());
        }
        catch (IOException ex) {
            log.error("Controller | download | IOException : {}", ex.getMessage());
            throw ex;
        }
    }

}
