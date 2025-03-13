package freeapp.me.qrgenerator.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.GetUrlRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest


@Service
class S3Service(
    private val s3Client: S3Client,
) {

    private val log = LoggerFactory.getLogger(S3Service::class.java)

    @Value("\${s3.bucket}")
    private lateinit var bucket: String

    @Value("\${s3.url}")
    private lateinit var staticUrl: String

    private val s3Utilities = s3Client.utilities()
    fun putObject(multipartFile: MultipartFile): String {

        if (multipartFile.isEmpty) {
            throw IllegalArgumentException("empty file")
        }

        val fileName =
            generateRandomNumberString() + "_" + multipartFile.originalFilename

        val objectRequest = PutObjectRequest.builder()
            .bucket(bucket)
            .key(fileName)
            .contentType(multipartFile.contentType)
            .build()

        val objectResponse = s3Client.putObject(
            objectRequest,
            RequestBody.fromInputStream(multipartFile.inputStream, multipartFile.size)
        )

        val getUrlRequest = GetUrlRequest.builder()
            .bucket(bucket).key(fileName)
            .build()

        //val url = s3Utilities.getUrl(getUrlRequest)

        return "$staticUrl/$fileName"
    }


    private fun generateRandomNumberString(): String {
        return (1..10)
            .map { (0..9).random() }
            .joinToString("")
    }


}
