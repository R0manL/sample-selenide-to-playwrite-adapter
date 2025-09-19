package com.superit.smart.qa.utils;

import com.superit.smart.qa.core.playwright.WebElm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.commons.io.FilenameUtils.EXTENSION_SEPARATOR;


/**
 * Created by R0manL.
 */

@Slf4j
public class FileUtils {
    private static final String EXCEL_FILE_EXTENSION = "xlsx";
    private static final String CSV_DELIMITER = ";";

    private static final String smartbox_DIR_NAME = "smartbox";
    private static final String STORYBOOK_DIR_NAME = "storybook";
    private static final String COLLAPP_DIR_NAME = "collapp";


    private FileUtils() {
        // NONE
    }

    @NotNull
    public static File downloadExcelFileByClickingAt(WebElm btn) {
        return downloadFileByClickingAt(btn, EXCEL_FILE_EXTENSION);
    }

    @NotNull
    public static File downloadFileByClickingAt(WebElm btn, @NotNull String fileExt) {
        log.info("Downloading '*." + fileExt + "' file.");

        return btn.download();
    }

    @NotNull
    public static Path getAbsolutePathForFileInResourceDir(@NotNull Path relPath) {
        log.debug("Getting absolute path to resources dir from related: '{}'", relPath);

        if(!relPath.isAbsolute()) {
            URL url = FileUtils.class.getClassLoader().getResource(relPath.toString());
            Objects.requireNonNull(url, "Can't convert related path: '" + relPath + "' to absolute.");
            try {
                log.debug("Absolute path is: '{}'", url);
                return Paths.get(url.toURI());
            } catch (URISyntaxException e) {
                throw new IllegalStateException("Can't get URI from: " + url + ", Error: " + e.getMessage());
            }
        } else {
            log.warn("Path has already absolute. Skip getting.");
        }

        return relPath;

    }

    /**
     * OS independent absolute file path builder for files located in resource directory.
     * E.g. (myFile.csv, collapp) > "collapp/myFile.csv" - for Linux/Mac or "collapp\myFile.csv" - for Windows.
     * @param first - child dir name in resource dir where file is located.
     * @param more - sequence of child directories in resource dir.
     *                   E.g. for file: "[project dir]/src/test/resources/collapp/TC49714_tenant_upload_with_wrong_PF.xlsx"
     *                   pass: (collapp, TC49714_tenant_upload_with_wrong_PF.xlsx)
     *
     * @return OS specific absolute path to the file.
     */
    @NotNull
    public static Path getAbsolutePathForFileInResourceDir(@NotNull String first, @NotNull String... more) {
        Path relPath = Paths.get(first, more);
        return getAbsolutePathForFileInResourceDir(relPath);
    }


    public static Path generateNewFileNameWithCurrentDateSuffix(Path path) {
        String filePath = path.toString();
        int lastDotIndex = filePath.lastIndexOf(EXTENSION_SEPARATOR);
        String result = filePath.substring(0, lastDotIndex ) + StringUtils.generateUniqueTextBasedOnCurrentDate("_") + filePath.substring(lastDotIndex);

        return Path.of(result);
    }

    public static List<List<String>> read2DArrayFromCsvFile(Path relFilePath) {
        log.debug("Read two dimensional array from csv file: {}", relFilePath);

        Path absPath = getAbsolutePathForFileInResourceDir(relFilePath);
        List<List<String>> result = new ArrayList<>();

        String line;
        try(BufferedReader br = new BufferedReader(new FileReader(absPath.toFile()))) {
            while ((line = br.readLine()) != null) {
                result.add(Arrays.stream(line.split(CSV_DELIMITER)).collect(Collectors.toList()));
            }
        } catch (IOException e) {
           throw new IllegalStateException("Can't read from csv file: " + relFilePath + ".\nError:" + e.getMessage());
        }

        return result;
    }

    public static Path getsmartboxStorybookResourceAbsFilePath(@NotNull String fileName) {
        return getAbsolutePathForFileInResourceDir(smartbox_DIR_NAME, STORYBOOK_DIR_NAME, fileName);
    }

    public static Path getCollappResourceAbsFilePath(@NotNull String fileName) {
        return getAbsolutePathForFileInResourceDir(COLLAPP_DIR_NAME, fileName);
    }

    public static String encodeFileToBase64(File file) {
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            return Base64.encodeBase64String(fileContent);
        } catch (IOException e) {
            throw new IllegalStateException("Could not read file: " + file, e);
        }
    }

    public static Optional<String> getFileExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public static void writeToFile(@NotNull String data, Path absFilePath) {
        FileWriter fWriter;
        BufferedWriter writer;

        try {
            fWriter = new FileWriter(absFilePath.toString());
            writer = new BufferedWriter(fWriter);
            writer.write(data);
            writer.newLine(); //this is not actually needed for html files - can make your code more readable though
            writer.close(); //make sure you close the writer object
        } catch (Exception e) {
            log.error("Can't write data into file: {}", absFilePath);
        }
    }

    /**
     * Method delete all files with specified name, ignoring extention.
     * @param name - file name to delete
     * @param dir - directory that contains files.
     */
    public static void deleteAllFilesWith(@NotNull String name, Path dir) {
        try (Stream<Path> files = Files.list(dir)) {
            files.forEach(file -> {
                String fileName = file.getFileName().toString();
                if (fileName.startsWith(name) && fileName.lastIndexOf('.') > 0) {
                    try {
                        Files.delete(file);
                        log.debug("Delete trace: {}", file);
                    } catch (IOException e) {
                        log.error("Can't delete trace file: {}.\nError: {}", file, e.getMessage());
                    }
                }
            });
        } catch (IOException e) {
            log.error("Can't read trace file: {}.\nError: {}", name, e.getMessage());
        }
    }
}
