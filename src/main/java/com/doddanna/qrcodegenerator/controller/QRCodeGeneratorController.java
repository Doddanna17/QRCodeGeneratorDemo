package com.doddanna.qrcodegenerator.controller;

import com.doddanna.qrcodegenerator.utils.QRCodeGenerator;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
public class QRCodeGeneratorController {

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getQRCode(){
        Map<String,Object> response=new HashMap<>();

        String dataToEncodeOnQrCode= "MyCustomData";

        byte[] image = new byte[0];
        try {

            // Generate and Return Qr Code in Byte Array
            image = QRCodeGenerator.getQRCodeImage(dataToEncodeOnQrCode,250,250);

            // Generate and Save Qr Code Image in static/image folder
//            QRCodeGenerator.getQRCodeImage(github,250,250);

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        // Convert Byte Array into Base64 Encode String
        String qrcode = Base64.getEncoder().encodeToString(image);

        response.put("data",dataToEncodeOnQrCode);
        response.put("qrcode",qrcode);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{format}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getQRCode(@PathVariable(name = "format") BarcodeFormat format){
        Map<String,Object> response=new HashMap<>();

        String dataToEncodeOnQrCode= "MyCustomData";

        byte[] image = new byte[0];
        try {

            // Generate and Return Qr Code in Byte Array
            image = QRCodeGenerator.getQRCodeImage(dataToEncodeOnQrCode,250,250,format);

            // Generate and Save Qr Code Image in static/image folder
//            QRCodeGenerator.getQRCodeImage(github,250,250);

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        // Convert Byte Array into Base64 Encode String
        String qrcode = Base64.getEncoder().encodeToString(image);

        response.put("data",dataToEncodeOnQrCode);
        response.put("qrcode",qrcode);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{format}/file")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getQRCodeFile(@PathVariable BarcodeFormat format, HttpServletResponse httpServletResponse){
        Map<String,Object> response=new HashMap<>();
        String dataToEncodeOnQrCode= "MyCustomData";

        byte[] image = new byte[0];
        try {

            // Generate and Return Qr Code in Byte Array
            image = QRCodeGenerator.getQRCodeImageFile(dataToEncodeOnQrCode,250,250,format,httpServletResponse.getOutputStream());

            // Generate and Save Qr Code Image in static/image folder
//            QRCodeGenerator.getQRCodeImage(github,250,250);

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        // Convert Byte Array into Base64 Encode String
        String qrcode = Base64.getEncoder().encodeToString(image);

        response.put("data",dataToEncodeOnQrCode);
        response.put("qrcode",qrcode);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
