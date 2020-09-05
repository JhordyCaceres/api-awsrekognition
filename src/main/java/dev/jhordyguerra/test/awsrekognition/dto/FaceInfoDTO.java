package dev.jhordyguerra.test.awsrekognition.dto;

import java.util.HashMap;

import software.amazon.awssdk.services.rekognition.model.FaceDetail;

public class FaceInfoDTO {
    private boolean face = false;
    private HashMap<String, Float> emotions = new HashMap<>();

    public FaceInfoDTO() {
    }

    public FaceInfoDTO(FaceDetail detail) {
        face = detail.confidence() > 5;
        detail.emotions().forEach((e) -> {
            emotions.put(e.type().name(), e.confidence());
        });
    }

    public boolean isFace() {
        return face;
    }

    public void setFace(boolean face) {
        this.face = face;
    }

    public HashMap<String, Float> getEmotions() {
        return emotions;
    }

    public void setEmotions(HashMap<String, Float> emotions) {
        this.emotions = emotions;
    }

}
