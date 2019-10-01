package com.smo.controllers;

import com.smo.views.FileView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Siham on 30/09/2019.
 */
@Path("/index")
@Produces(MediaType.TEXT_HTML)
public class IndexController {

    @GET
    public FileView uploadFile() {
        return new FileView();
    }


}

