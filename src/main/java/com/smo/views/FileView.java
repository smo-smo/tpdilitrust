package com.smo.views;

import io.dropwizard.views.View;

/**
 * Created by Siham on 30/09/2019.
 */
public class FileView extends View {

    public FileView() {
        super("/views/file.mustache");
    }
}
