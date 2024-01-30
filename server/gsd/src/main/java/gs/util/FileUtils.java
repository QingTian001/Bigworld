package gs.util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileUtils {
    // 删除目录
    public static boolean deleteDirectory(File dir) {
        if (dir.exists() && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDirectory(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        return dir.delete();
    }
    
    // 清除目录下的文件夹或文件
    public static boolean cleanDirectory(File dir) {
        if (dir.exists() && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                deleteDirectory(new File(dir, children[i]));
            }
        }
        
        return true;
    }

    public static void createParentIfNotExist(String filename) {
        File parent = new File(filename).getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
    }

    public static File createPathIfNotExist(String filepath) {
        File path = new File(filepath);
        if (path != null && !path.exists()) {
            path.mkdirs();
        }
        return path;
    }
    
    public static File createFileAlways(String filename) throws IOException {
        File file = new File(filename);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        return file;
    }
    
    public static File createIfNotExist(String filename) throws IOException {
        File file = new File(filename);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }
    
    public static List<String> readLines(String filename) throws IOException {
        return readLines(new File(filename));
    }
    
    public static List<String> readLines(File file) throws IOException {
        List<String> lines = new ArrayList<>();
        try (FileInputStream stream = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
            BufferedReader bufferReader = new BufferedReader(reader)) {
            lines = bufferReader.lines().map(a -> a).collect(Collectors.toList());
        }
        return lines;
    }
    
    public static byte[] readBinaryFile(String filename) throws IOException {
        File file = new File(filename);
        return readBinaryFile(file, 0, (int)file.length());
    }
    
    public static byte[] readBinaryFile(String filename, int offset, int length)
            throws IOException {
        return readBinaryFile(new File(filename), offset, length);
    }

    public static byte[] readBinaryFile(File file, int offset, int length)
            throws IOException {
        try (FileInputStream inStream = new FileInputStream(file)) {
            inStream.skip(offset);

            int left = (int) Math.max(0, file.length() - offset);
            byte[] bytes = new byte[Math.min(left, (int)length)];
            if (inStream.read(bytes, offset, bytes.length) == bytes.length) {
                return bytes;
            }
        }

        return new byte[0];
    }


    public static void writeFile(String filename, String content) throws IOException {
        writeFile(filename, content, "UTF-8");
    }

    public static void writeFile(String filename, String content, String charset) throws IOException {
        createIfNotExist(filename);

        try (FileOutputStream stream = new FileOutputStream (filename, true);
             OutputStreamWriter osWriter = new OutputStreamWriter (stream, charset);
             BufferedWriter fileWriter = new BufferedWriter (osWriter)) {
            fileWriter.append(content);
            fileWriter.flush();
        }
    }

    public static void writeBinaryFile(String filename, long offset, byte[] bytes) 
            throws IOException {
        createIfNotExist(filename);
        try (RandomAccessFile outStream = new RandomAccessFile(filename, "rw")) {
            outStream.seek(offset);
            outStream.write(bytes);
        }
    }
    
    public static void copyFile(InputStream input, File dst) 
            throws IOException {
        try (FileOutputStream out = new FileOutputStream(dst)) {
            
            int len;
            byte[] buffer = new byte[1024];
            while ((len = input.read(buffer)) > -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (IOException e) {
            throw e;
        }
    }

    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            return file.delete();
        } else {
            return false;
        }
    }
    
    public static long getFileSize(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            return file.length();
        } else {
            return 0;
        }
    }

    public static File[] listFiles(String filename) {
        return listFiles(new File(filename));
    }

    public static File[] listFiles(File file) {
        File[] files = file.listFiles();
        return files != null ? files : new File[]{};
    }

    public static Map<Long, String> getTimeFileList(String path) {
        Map<Long, String> map = new HashMap<>(); 
        File dir = new File(path);
        if (dir.exists() && dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                map.put(Long.valueOf(file.getName()), String.format("%s%s", path, file.getName()));
            }
        }
        return map;
    }
    
    public static String getSimpleName(String path) {
        return path.substring(0, path.lastIndexOf("."));
    }
    
    public static String getFileExt(String path) {
        return path.substring(path.lastIndexOf(".")+1, path.length());
    }
    
    public static String getFileName(String path) {
        int index = path.lastIndexOf("\\");
        if (index >= 0) {
            path = path.substring(index + "\\".length());
        }
        
        index = path.lastIndexOf("/");
        if (index >= 0) {
            path = path.substring(index + "/".length());
        }
        index = path.lastIndexOf("?");
        if (index >= 0) {
            path = path.substring(0, index);
        }
        return path;
    }
}
