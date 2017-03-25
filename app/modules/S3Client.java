package modules;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import play.Configuration;
import play.Logger;
import play.inject.ApplicationLifecycle;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.CompletableFuture;

@Singleton
public final class S3Client {
    @Inject
    public S3Client(final ApplicationLifecycle lifecycle, final Configuration configuration) {
        final String accessKey = configuration.getString("aws.s3.authKey",
                configuration.getString("aws.authKey")
        );
        final String secretKey = configuration.getString(
                "aws.s3.authSecret",
                configuration.getString("aws.authSecret")
        );

        final String endPoint = configuration.getString("aws.s3.endPoint");
        final String signingRegion = configuration.getString("aws.s3.signingRegion");
        final boolean withPathStyle = configuration.getBoolean("aws.s3.withPathStyle", false);
        PlayS3.bucketName = configuration.getString("aws.s3.bucketName");
        PlayS3.publicUrl = configuration.getString("aws.s3.publicUrl", "/");
        if (!PlayS3.publicUrl.endsWith("/")) {
            PlayS3.publicUrl += "/";
        }

        if (accessKey == null || secretKey == null || PlayS3.bucketName == null) {
            throw new RuntimeException("S3Module is not properly configured");
        }

        PlayS3.amazonS3 = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSCredentialsProvider() {
                    @Override
                    public AWSCredentials getCredentials() {
                        return new BasicAWSCredentials(accessKey, secretKey);
                    }

                    @Override
                    public void refresh() {
                        // Not used with basic AWS credentials
                    }
                })
                .withPathStyleAccessEnabled(withPathStyle)
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(endPoint, signingRegion)
                )
                .build();
        try {
            PlayS3.amazonS3.createBucket(PlayS3.bucketName);
        } catch (final AmazonS3Exception e) {
            if (e.getErrorCode().compareTo("BucketAlreadyOwnedByYou") != 0
                    && e.getErrorCode().compareTo("AccessDenied") != 0) {
                throw e;
            }
        } finally {
            Logger.info("Using modules.PlayS3 Bucket: " + PlayS3.bucketName);
        }

        lifecycle.addStopHook(() -> CompletableFuture.completedFuture(null));
    }
}