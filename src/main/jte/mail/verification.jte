@import freeapp.me.qrgenerator.web.dto.EmailBodyDto
@param  EmailBodyDto emailBody

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>인증 코드</title>
</head>
<body style="font-family: 'Helvetica Neue', Arial, sans-serif; margin: 0; padding: 0; background-color: #f5f5f5; color: #333333;">
<table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
        <td align="center" style="padding: 20px 0;">
            <table cellpadding="0" cellspacing="0" border="0" width="600" style="max-width: 600px; background-color: #ffffff; border-collapse: collapse;">
                <!-- 헤더 -->
                <tr>
                    <td align="center" bgcolor="#000000" style="padding: 20px; color: #ffffff; font-weight: bold; font-size: 20px;">
                        QR Generator
                    </td>
                </tr>

                <!-- 본문 -->
                <tr>
                    <td style="padding: 40px 30px;">
                        <p style="font-size: 16px; line-height: 1.5; margin: 0 0 20px;">아래 인증 코드를 로그인 화면에 입력하여 계정 인증을 완료해 주세요.</p>

                        <div style="font-family: monospace; font-size: 32px; font-weight: 700; letter-spacing: 4px; color: #000000; padding: 20px 0; text-align: center; margin: 0 0 20px;">
                            ${emailBody.getVerificationCode()}
                        </div>

                        <p style="font-size: 14px; color: #777777; margin-top: 30px;">
                            이 인증 코드는 ${emailBody.getValidityMinutes()}분 동안만 유효합니다.
                        </p>

                        <div style="font-size: 14px; color: #777777; border-top: 1px solid #eaeaea; padding-top: 20px; margin-top: 40px;">
                            <p style="margin: 0 0 10px;">본인이 요청하지 않았다면, 누군가 실수로 귀하의 이메일 주소를 입력했을 수 있습니다. 이 경우 아무 조치도 취하지 않으셔도 됩니다.</p>
                        </div>
                    </td>
                </tr>

                <!-- 푸터 -->
                <tr>
                    <td bgcolor="#f7f7f7" style="padding: 20px 30px; color: #777777; font-size: 12px; line-height: 1.5;">
                        <p style="margin: 0;">본 이메일은 발신 전용이므로 회신하실 수 없습니다.</p>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>
