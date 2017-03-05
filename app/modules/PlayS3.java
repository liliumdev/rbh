package modules;

import com.amazonaws.services.s3.AmazonS3;

public final class PlayS3 {

    static AmazonS3 amazonS3;
    static String bucketName;
    static String publicUrl;

    public static AmazonS3 getAmazonS3() {
        return PlayS3.amazonS3;
    }

    public static String getBucketName() {
        return PlayS3.bucketName;
    }

    public static String getPublicUrl() {
        return PlayS3.publicUrl;
    }

    public static boolean isReady() {
        return amazonS3 != null;
    }
}