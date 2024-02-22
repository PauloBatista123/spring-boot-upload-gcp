package com.gcp.SpringGCP.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Slf4j
public class ImageUtils {

    public static File convertFile(MultipartFile file){
        try{

            if(file.getOriginalFilename() == null){
                throw new BadRequestException("Original name is null");
            }

            File convetedFile = new File(file.getOriginalFilename());
            FileOutputStream outputStream = new FileOutputStream(convetedFile);
            outputStream.write(file.getBytes());
            outputStream.close();

            log.info("Arquivo convertido: {}", convetedFile);

            return convetedFile;

        }catch (Exception e){
            throw new Error("Ocorreu um erro ao tentar converter o arquivo");
        }
    }
}
