package com.smo.services;

import com.sun.jersey.core.header.FormDataContentDisposition;

import static org.junit.Assert.*;

import org.junit.Test;

import javax.ws.rs.core.Response;
import java.io.InputStream;

public class FileServiceTest {

    @Test
    public void uploadAndEnCryptFileWithNoDataShouldReturnError() {
        //Given
        InputStream inputStream = null;
        FormDataContentDisposition formDataContentDisposition = null;
        int expectedStatus = 500;

        //When
        Response response = FileService.uploadAndEnCryptFile(inputStream, formDataContentDisposition);

        //Then
        assertNotNull(response);
        assertEquals(expectedStatus, response.getStatus());
    }

    @Test
    public void decryptAndDownloadFileWithNoDataShouldReturnError() {
        //Given
        String fileName = null;
        int expectedStatus = 500;
        //When
        Response response = FileService.decryptAndDownloadFile(fileName);

        //Then
        assertNotNull(response);
        assertEquals(expectedStatus, response.getStatus());
    }

    @Test
    public void uploadFileWithNoDataShouldReturnError() {
        //Given
        InputStream inputStream = null;
        FormDataContentDisposition formDataContentDisposition = null;
        int expectedStatus = 500;

        //When
        Response response = FileService.uploadFile(inputStream, formDataContentDisposition);

        //Then
        assertNotNull(response);
        assertEquals(expectedStatus, response.getStatus());
    }

    @Test
    public void downloadFileWithNoDataShouldReturnError() {
        //Given
        String fileName = null;
        int expectedStatus = 404;

        //When
        Response response = FileService.downloadFile(fileName);

        //Then
        assertNotNull(response);
        assertEquals(expectedStatus, response.getStatus());
    }
}