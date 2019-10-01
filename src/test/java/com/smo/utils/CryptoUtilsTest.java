package com.smo.utils;

import org.junit.Test;

import java.io.File;
import java.io.InputStream;

public class CryptoUtilsTest {

    @Test(expected = IllegalArgumentException.class)
    public void decryptWithNoDataShouldReturnException() throws Exception {
        //Given
        InputStream inputStream = null;
        File file = null;

        //When
        CryptoUtils.decrypt(inputStream, file);
    }

    @Test(expected = IllegalArgumentException.class)
    public void encryptWithNoDataShouldReturnException() throws Exception {
        //Given
        InputStream inputStream = null;
        File file = null;

        //When
        CryptoUtils.encrypt(inputStream, file);
    }

}
