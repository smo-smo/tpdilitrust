package com.smo.controllers;

import com.smo.services.FileService;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by Siham on 30/09/2019.
 */
@Path("/files")
public class FileController {

    @POST
    @Path("/upload-and-encrypt")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Response uploadAndEnCryptFile(@FormDataParam("file") InputStream uploadedInputStream,
                                         @FormDataParam("file") FormDataContentDisposition fileDetail) {
        return FileService.uploadAndEnCryptFile(uploadedInputStream, fileDetail);
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
                               @FormDataParam("file") FormDataContentDisposition fileDetail) {
        return FileService.uploadFile(uploadedInputStream, fileDetail);
    }

    @GET
    @Path("/download-encrypted-file")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response decryptAndDownloadFile(@QueryParam("filename") String fileName) {
        return FileService.decryptAndDownloadFile(fileName);
    }

    @GET
    @Path("/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFile(@QueryParam("filename") String fileName) {
        return FileService.downloadFile(fileName);
    }


}

