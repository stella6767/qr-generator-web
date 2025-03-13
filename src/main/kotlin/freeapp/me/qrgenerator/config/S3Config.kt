package freeapp.me.qrgenerator.config

import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client


@Configuration
class S3Config {


    @Value("\${s3.accessKey}")
    private lateinit var accessKey:String

    @Value("\${s3.secretKey}")
    private lateinit var secretKey:String


    fun createAwsCredential(): AwsBasicCredentials {
        return  AwsBasicCredentials.create(accessKey, secretKey)
    }

    @Bean
    fun amazonS3Client(): S3Client {

        return S3Client.builder()
            .region(Region.AP_NORTHEAST_2)
            .credentialsProvider(StaticCredentialsProvider.create(createAwsCredential()))
            .build()
    }


}
