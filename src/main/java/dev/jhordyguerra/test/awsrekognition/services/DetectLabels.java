package dev.jhordyguerra.test.awsrekognition.services;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.jhordyguerra.test.awsrekognition.dto.FaceInfoDTO;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.Attribute;
import software.amazon.awssdk.services.rekognition.model.DetectFacesRequest;
import software.amazon.awssdk.services.rekognition.model.DetectFacesResponse;
import software.amazon.awssdk.services.rekognition.model.FaceDetail;
import software.amazon.awssdk.services.rekognition.model.Image;

@Service
public class DetectLabels {

    public List<FaceInfoDTO> detectFacesInImage(String sourceImage) {
        List<FaceInfoDTO> listFaces = new ArrayList<>();
        RekognitionClient client = RekognitionClient.builder().region(Region.US_EAST_1).build();

        List<FaceDetail> faceDetails = new ArrayList<>();
        try (InputStream sourceStream = new FileInputStream(sourceImage)) {
            SdkBytes sourceBytes = SdkBytes.fromInputStream(sourceStream);
            Image souImage = Image.builder().bytes(sourceBytes).build();
            DetectFacesRequest facesRequest = DetectFacesRequest.builder().attributes(Attribute.ALL).image(souImage)
                    .build();
            DetectFacesResponse facesResponse = client.detectFaces(facesRequest);
            faceDetails = facesResponse.faceDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (FaceDetail faceDetail : faceDetails) {
            listFaces.add(new FaceInfoDTO(faceDetail));
        }

        return listFaces;
    }

    public List<FaceInfoDTO> detectFacesInImage2(InputStream sourceImage) {
        List<FaceInfoDTO> listFaces = new ArrayList<>();
        RekognitionClient client = RekognitionClient.builder().region(Region.US_EAST_1).build();

        List<FaceDetail> faceDetails = new ArrayList<>();
        try (InputStream sourceStream = sourceImage) {
            SdkBytes sourceBytes = SdkBytes.fromInputStream(sourceStream);
            Image souImage = Image.builder().bytes(sourceBytes).build();
            DetectFacesRequest facesRequest = DetectFacesRequest.builder().attributes(Attribute.ALL).image(souImage)
                    .build();
            DetectFacesResponse facesResponse = client.detectFaces(facesRequest);
            faceDetails = facesResponse.faceDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (FaceDetail faceDetail : faceDetails) {
            listFaces.add(new FaceInfoDTO(faceDetail));
        }

        return listFaces;
    }
}