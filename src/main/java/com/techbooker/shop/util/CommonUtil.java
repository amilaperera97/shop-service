package com.techbooker.shop.util;

import com.google.common.base.Joiner;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.techbooker.shop.conf.ShopServiceProperties;
import com.techbooker.shop.dto.QrCodeType;
import com.techbooker.shop.exception.custom.QRCodeGeneratorException;
import com.techbooker.shop.util.contance.CharSet;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@Component
public class CommonUtil {

    private final ShopServiceProperties shopServiceProperties;
    private final MessageSource messageSource;

    public String generateQrCode(QrCodeType qrCodeType, Map<String, String> data) {
        String qrData = convertMapToString(data);
        String path = shopServiceProperties.getQrFilePath() + com.techbooker.sm.util.CommonUtil.uuid() + ".png";
        try {
            createQR(qrData, path, CharSet.UTF8, shopServiceProperties.getQrHeight(), shopServiceProperties.getQrWidth());
            return path;
        } catch (WriterException we) {
            log.error("QR gen error while writing : {}", we.getMessage());
        } catch (IOException ie) {
            log.error("QR gen error IO : {}", ie.getMessage());
        }
        throw new QRCodeGeneratorException(messageSource.getMessage("data.qr.violation.exception", new Object[0], Locale.ENGLISH));
    }

    // Function to create the QR code
    private static void createQR(String data, String path, String charset, int height, int width)
            throws WriterException, IOException {

        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(data.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, width, height);

        MatrixToImageWriter.writeToPath(
                matrix,
                path.substring(path.lastIndexOf('.') + 1),
                Path.of(path));
    }

    public static String convertMapToString(Map<?, ?> map) {
        return Joiner.on(",").withKeyValueSeparator("=").join(map);
    }
}
