package com.htdweb.utils;

import org.springframework.stereotype.Component;
//import org.apache.commons.lang.StringUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class UploadFileUtils {

    public static void writeOrUpdate(String path, byte[] bytes) {
//        path = "C://home/office" + path;
        path = "C://Users/Admin/Documents/JavaWeb/building/src/main/resources/static" + path;
        File file = new File(StringUtils.substringBeforeLast(path, "/"));

        if (!file.exists()) {
            file.mkdir();
        }
        try (FileOutputStream fop = new FileOutputStream(path)) {
            fop.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
