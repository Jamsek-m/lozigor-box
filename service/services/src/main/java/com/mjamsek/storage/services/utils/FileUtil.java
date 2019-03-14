package com.mjamsek.storage.services.utils;

import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtil {
    
    private static Tika tika = new Tika();
    
    public static void saveFileToDisk(InputStream fileInputStream, String storagePath, String filename) throws IOException {
        java.nio.file.Path destination = Paths.get(storagePath).resolve(filename);
        Files.copy(fileInputStream, destination, StandardCopyOption.REPLACE_EXISTING);
    }
    
    public static void cleanupFileOnDisk(String storagePath, String filename) {
        try {
            java.nio.file.Path fileLocation = Paths.get(storagePath).resolve(filename);
            if (Files.exists(fileLocation)) {
                Files.delete(fileLocation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getMimeType(InputStream inputStream) {
        try {
            return tika.detect(inputStream);
        } catch (IOException e) {
            return "";
        }
    }
    
    public static long getFileSize(String storagePath, String filename) {
        java.nio.file.Path fileLocation = Paths.get(storagePath).resolve(filename);
        File file = new File(fileLocation.toString());
        return file.length();
    }
    
    public static String getFileExtension(String filename) {
        String[] splittedFilename = filename.split("\\.");
        return splittedFilename[splittedFilename.length - 1];
    }
    
    public static byte[] getFileByteArray(String storagePath, String filename) throws IOException {
        java.nio.file.Path fileLocation = Paths.get(storagePath).resolve(filename);
        return Files.readAllBytes(fileLocation);
    }
    
}
