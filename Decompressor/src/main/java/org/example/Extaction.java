package org.example;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;

public class Extaction {
    public static String extractXML(String zipPath, String outputDir) throws Exception {
        ZipFile zipFile = new ZipFile(new File(zipPath));
        String xmlFilePath = null;
        Enumeration<ZipArchiveEntry> entries = zipFile.getEntries();
        while (entries.hasMoreElements()) {
            ZipArchiveEntry entry = entries.nextElement();

            if (!entry.isDirectory() && entry.getName().endsWith(".xml")) {
                File outputFile = new File(outputDir, entry.getName());


                outputFile.getParentFile().mkdirs();

                try (InputStream inputStream = zipFile.getInputStream(entry);
                     FileOutputStream outputStream = new FileOutputStream(outputFile)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = inputStream.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, length);
                    }
                }
                xmlFilePath = outputFile.getAbsolutePath();
            }
        }

        zipFile.close();
        return xmlFilePath;
    }

}
