package com.example.retro_cinema.qr_code.controller;

import com.example.retro_cinema.seat_details.model.SeatDetails;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.tomcat.util.codec.binary.Base64;


import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;


public class QRController {
    public static String generateQRCode(SeatDetails seatDetails) {
        String qrCodePath = "/Users/lehuy/Documents/IMPORTANT/retro_cinema/src/main/resources/static/qr_code/";
        String qrCodeName = qrCodePath + seatDetails.getId() + seatDetails.getAccountUser().getId() + seatDetails.getScreenings().getId() + seatDetails.getSeats().getId() + "-QRCODE.png";
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix;
        try {
            bitMatrix = qrCodeWriter.encode(
                    "Movie: " + seatDetails.getScreenings().getMovie().getMovieName() + "\n" +
                            "Date: " + seatDetails.getScreenings().getDateMovie() + "\n" +
                            "Start-time: " + seatDetails.getScreenings().getShowTimes().getStartTime() + "\n" +
                            "Room: " + seatDetails.getScreenings().getShowTimes().getRooms().getRoomName() + "\n" +
                            "Seat: " + seatDetails.getSeats().getSeatName() + "\n"
                    , BarcodeFormat.QR_CODE, 100, 100);

            Path path = FileSystems.getDefault().getPath(qrCodeName);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            System.out.println("QR code generated successfully at: " + qrCodeName);
            byte[] imageBytes = Files.readAllBytes(path);
            String base64Image = Base64.encodeBase64String(imageBytes);
            String srcAttribute = "data:image/png;base64," + base64Image;
            System.out.println(base64Image);
            System.out.println(srcAttribute);
            return srcAttribute;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
        return "Miss src attribute";
    }
}

