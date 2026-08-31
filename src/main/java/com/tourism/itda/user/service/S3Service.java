package com.tourism.itda.user.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;
import java.util.UUID;

@Service
public class S3Service {

    private final S3Presigner presigner;
    private final String bucket;
    private final String region;

    public S3Service(S3Presigner presigner,
                     @Value("${aws.s3.bucket}") String bucket,
                     @Value("${aws.region}") String region) {
        this.presigner = presigner;
        this.bucket = bucket;
        this.region = region;
    }

    public String generatePresignedUrl(Long userId, String contentType) {
        String key = "avatars/" + userId + "/" + UUID.randomUUID();

        PutObjectRequest putRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .contentType(contentType)
                .build();

        PresignedPutObjectRequest presigned = presigner.presignPutObject(
                PutObjectPresignRequest.builder()
                        .signatureDuration(Duration.ofMinutes(10))
                        .putObjectRequest(putRequest)
                        .build());

        return presigned.url().toString();
    }

    public String getPublicUrl(String presignedUrl) {
        // presigned URL에서 쿼리파라미터 제거해 퍼블릭 URL 반환
        String url = presignedUrl.split("\\?")[0];
        return url;
    }
}
