package dev.jhordyguerra.test.awsrekognition.enpoint;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.jhordyguerra.test.awsrekognition.dto.FaceInfoDTO;
import dev.jhordyguerra.test.awsrekognition.services.DetectLabels;

@RestController
@CrossOrigin
public class Rekognition {
    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    DetectLabels labels;

    @GetMapping(value = "/")
    public List<FaceInfoDTO> getMethodName() {
        final List<FaceInfoDTO> lista = labels.detectFacesInImage("/home/jhordycaceres/Pictures/image1.jpg");
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return lista;
    }

    @PostMapping(value = "/detect")
    public List<FaceInfoDTO> analyzeFace(@RequestParam("image") MultipartFile image) {
        List<FaceInfoDTO> lista = new ArrayList<>();
        if (image != null && !image.isEmpty()) {
            lista = labels.detectFacesInImage("/home/jhordycaceres/Pictures/image1.jpg");

            try (InputStream stream = image.getInputStream()) {
                lista = labels.detectFacesInImage2(image.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }

            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
        return lista;
    }
}