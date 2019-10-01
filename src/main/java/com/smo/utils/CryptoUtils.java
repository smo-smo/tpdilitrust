package com.smo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

import javax.crypto.*;

/**
 * Created by Siham on 30/09/2019.
 */
public class CryptoUtils {
    private static final String TRANSFORMATION = "AES";
    private static final Logger LOGGER = LoggerFactory.getLogger(CryptoUtils.class);

    public static void encrypt(InputStream inputStream, File outputFile)
            throws Exception {
        doCrypto(Cipher.ENCRYPT_MODE, inputStream, outputFile);
    }

    public static void decrypt(InputStream inputStream, File outputFile)
            throws Exception {
        doCrypto(Cipher.DECRYPT_MODE, inputStream, outputFile);
    }

    private static void doCrypto(int cipherMode, InputStream inputStream,
                                 File outputFile) throws Exception {
        if (inputStream == null || outputFile == null) throw new IllegalArgumentException();
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(cipherMode, KeyGenerator.getInstance("AES").generateKey());

        OutputStream outputStream = new FileOutputStream(outputFile);
        byte[] buf = new byte[1024];
        CipherInputStream cis = new CipherInputStream(inputStream, cipher);
        int numRead;
        try {
            while ((numRead = cis.read(buf)) >= 0) {
                outputStream.write(buf, 0, numRead);
            }
        } catch (Exception e) {
            LOGGER.error("error" + e);
        }
        cis.close();
        inputStream.close();
        outputStream.close();
    }
}