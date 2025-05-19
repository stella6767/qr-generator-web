package freeapp.me.qrgenerator.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import freeapp.me.qrgenerator.config.*
import freeapp.me.qrgenerator.util.addNoFromFilename
import freeapp.me.qrgenerator.util.customDelimiter
import freeapp.me.qrgenerator.util.generateRandomNumberString
import freeapp.me.qrgenerator.web.dto.*
import jakarta.persistence.EntityNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayOutputStream
import java.util.*


@Service
class QrService(
    private val mapper: ObjectMapper,
    private val s3Service: S3Service,
) {

    private val log = LoggerFactory.getLogger(QrService::class.java)

    private val inValidCharacters =
        setOf('\\', '{', '^', '}', '%', '`', ']', '\"', '>', '[', '~', '<', '#', '|')


    fun generateStaticQRCodeByType(
        type: QrGeneratorType,
        qrReqDto: HashMap<String, Any>,
        file: MultipartFile?
    ): String {
        return when (type) {
            QrGeneratorType.LINK, QrGeneratorType.TEXT -> {
                val textReqDto =
                    mapper.convertValue(qrReqDto, TextReqDto::class.java)
                generateStaticQRCode(textReqDto.text)
            }

            QrGeneratorType.WIFI -> {
                val wifiDto =
                    mapper.convertValue(qrReqDto, WifiReqDto::class.java)
                val qrValue = "WIFI:T:${wifiDto.encryption};S:${wifiDto.ssid};P:${wifiDto.password};;"
                generateStaticQRCode(qrValue)
            }

            QrGeneratorType.VCARD -> {
                val vCardDto =
                    mapper.convertValue(qrReqDto, VCardReqDto::class.java)
                val vCard = """
                    BEGIN:VCARD
                    VERSION:3.0
                    FN:${vCardDto.firstName}  ${vCardDto.lastName}
                    TEL;TYPE=CELL:${vCardDto.phoneNumber}                   
                    END:VCARD
                """.trimIndent()
                generateStaticQRCode(vCard)
            }

            QrGeneratorType.TEL -> {
                val callDto =
                    mapper.convertValue(qrReqDto, CallReqDto::class.java)
                val qrValue = "tel:${callDto.countryCode}${callDto.phoneNumber}"
                println(qrValue)
                generateStaticQRCode(qrValue)
            }

            QrGeneratorType.PDF -> {
                generateDynamicQRCode(file!!)
            }
        }

    }


    fun generateStaticQRCode(
        qrValue: String,
        width: Int = 300,
        height: Int = 300
    ): String {

        val qrCodeWriter = QRCodeWriter()

        val hintMap: MutableMap<EncodeHintType, Any> = HashMap()
        hintMap[EncodeHintType.MARGIN] = 0
        hintMap[EncodeHintType.CHARACTER_SET] = "UTF-8"

        val bitMatrix =
            qrCodeWriter.encode(qrValue, BarcodeFormat.QR_CODE, width, height, hintMap)

        val outputStream = ByteArrayOutputStream()

        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream)

        val base64Image =
            Base64.getEncoder().encodeToString(outputStream.toByteArray())

        return base64Image
    }


    fun generateDynamicQRCode(
        file: MultipartFile
    ): String {

//        if (file.size ) {
//
//        }

        val dynamicUrl =
            s3Service.putObject(file)
        return generateStaticQRCode(dynamicUrl)
    }



    fun initiateUpload(
        principalDetails: UserPrincipal,
        initialUploadReqDto: InitialUploadReqDto,
    ): InitialUploadDto {

        var filename = initialUploadReqDto.filename

        if (filename.any { it in inValidCharacters }) {
            throw IllegalArgumentException("filename has invalid characters")
        }

        filename = if (filename.contains(customDelimiter)) {
            filename.replace(customDelimiter, "")
        } else filename

        val no = generateRandomNumberString()
        val createdFileName = addNoFromFilename(filename, no)

        val initiateUpload =
            s3Service.initiateUpload("test", createdFileName)


        return initiateUpload
    }


    fun getUploadSignedUrl(
        s3UploadSignedUrlDto: S3UploadSignedUrlDto
    ): S3UploadSignedUrlResDto {

        val uploadSignedUrl = s3Service.getUploadSignedUrl(s3UploadSignedUrlDto)

        return S3UploadSignedUrlResDto(
            partNumber = s3UploadSignedUrlDto.partNumber,
            uploadSignedUrl
        )
    }


    @Transactional
    fun completeUpload(s3UploadCompleteDto: S3UploadCompleteDto): S3UploadResultDto {

        val completeUpload =
            s3Service.completeUpload(s3UploadCompleteDto)
        
        return completeUpload
    }


    @Transactional
    fun abortUpload(s3UploadAbortDto: S3UploadAbortDto) {
        s3Service.abortUpload(s3UploadAbortDto)
    }




}
