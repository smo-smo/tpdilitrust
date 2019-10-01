package com.smo.services;

import com.smo.utils.CryptoUtils;
import com.sun.jersey.api.NotFoundException;
import com.sun.jersey.core.header.FormDataContentDisposition;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.*;

import static com.smo.utils.Constants.*;

/**
 * Created by Siham on 30/09/2019.
 */
public class FileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

    public static Response uploadAndEnCryptFile(InputStream uploadedInputStream, FormDataContentDisposition fileDetail) {
        String uploadedFileLocation = DECRYPTED_FILES_FOLDER + (fileDetail != null ? fileDetail.getFileName() : "");
        try {
            CryptoUtils.encrypt(uploadedInputStream, new File(uploadedFileLocation));
            String output = "File uploaded to : " + uploadedFileLocation;
            return Response.ok(output).build();
        } catch (Exception e) {
            String output = "File not uploaded";
            return Response.status(500).entity(output).type("text/plain").build();
        }
    }

    public static Response decryptAndDownloadFile(@QueryParam("filename") String fileName) {
        Response response;
        try {
            if (StringUtil.isBlank(fileName)) throw new IllegalArgumentException();
            File inputFile = constructFile(fileName);
            if (!inputFile.exists()) throw new NotFoundException();

            File outputFile = constructFile("dec-" + fileName);
            CryptoUtils.decrypt(new FileInputStream(inputFile), outputFile);
            response = Response.ok(outputFile).header("Content-Disposition", "attachment; filename=" + "dec-" + fileName).build();
        } catch (NotFoundException e) {
            response = Response.status(404).entity("FILE NOT FOUND").type("text/plain").build();
        } catch (Exception e) {
            response = Response.status(500).entity("ERROR").type("text/plain").build();
        }
        return response;
    }

    public static Response uploadFile(InputStream uploadedInputStream, FormDataContentDisposition fileDetail) {
        String uploadedFileLocation = FILES_FOLDER + (fileDetail != null ? fileDetail.getFileName() : "");
        try {
            writeToFile(uploadedInputStream, uploadedFileLocation);
            String output = "File uploaded to : " + uploadedFileLocation;
            return Response.ok(output).build();
        } catch (Exception e) {
            String output = "File not uploaded";
            return Response.status(500).entity(output).type("text/plain").build();
        }
    }

    public static Response downloadFile(String fileName) {
        String fileLocation = FILES_FOLDER + fileName;
        File file = new File(FILES_FOLDER + fileName);
        if (!StringUtil.isBlank(fileName) && file.exists()) {
            return Response.ok(file).
                    header("Content-Disposition", "attachment; filename=" + file.getName())
                    .build();
        } else {
            LOGGER.error("FILE NOT FOUND: fileName: %s", fileName);
            return Response.status(404).
                    entity("FILE NOT FOUND: " + fileLocation).
                    type("text/plain").
                    build();
        }
    }

    private static File constructFile(String fileName) {
        String downloadedFileLocation = DECRYPTED_FILES_FOLDER + fileName;
        return new File(downloadedFileLocation);
    }

    private static void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) throws IOException {
        int read;
        final int BUFFER_LENGTH = 1024;
        final byte[] buffer = new byte[BUFFER_LENGTH];

        if (uploadedInputStream == null || uploadedFileLocation == null) throw new IllegalArgumentException();
        OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
        while ((read = uploadedInputStream.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        out.flush();
        out.close();
    }

}
