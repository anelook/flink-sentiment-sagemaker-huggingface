package org.example;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sagemakerruntime.SageMakerRuntimeClient;
import software.amazon.awssdk.services.sagemakerruntime.model.InvokeEndpointRequest;
import software.amazon.awssdk.services.sagemakerruntime.model.InvokeEndpointResponse;

import java.nio.charset.Charset;

public class SentimentAnalysis {
    public static String getPrediction(String text) {
        System.out.println(text);

        String endpointName = "distilbert-base-sentiment";
        String contentType = "application/x-text";

        Region region = Region.US_EAST_1;
        AwsBasicCredentials credentials =
                AwsBasicCredentials.create(
                        Variables.AWS_SECRET_KEY_ID,
                        Variables.AWS_SECRET_ACCESS_KEY);
        SageMakerRuntimeClient runtimeClient = SageMakerRuntimeClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(region)
                .build();

        InvokeEndpointRequest endpointRequest = InvokeEndpointRequest.builder()
                .endpointName(endpointName)
                .contentType(contentType)
                .body(SdkBytes.fromString(text, Charset.defaultCharset()))
                .build();
        InvokeEndpointResponse response = runtimeClient.invokeEndpoint(endpointRequest);
        String responseBody = response.body().asString(Charset.defaultCharset());
        runtimeClient.close();
        return responseBody;
    }
}
