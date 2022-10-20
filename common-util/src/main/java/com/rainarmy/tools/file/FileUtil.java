package com.rainarmy.tools.file;

import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtil {
    public String getFileMd5(File file) throws IOException {
        return DigestUtils.md5DigestAsHex(new FileInputStream(file));
    }

    public String getFileMd5(String path) throws IOException {
        return DigestUtils.md5DigestAsHex(new FileInputStream(path));
    }
}
