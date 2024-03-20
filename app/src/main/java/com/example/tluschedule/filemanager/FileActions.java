package com.example.tluschedule.filemanager;

import android.content.Context;
import android.util.Log;

import com.example.tluschedule.supporter.converter.JsonConverter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class FileActions {

    /**
     * Ghi dữ liệu vào tệp hoặc tạo tệp mới nếu nó không tồn tại
     *
     * @param context  Context
     * @param fileName Tên tệp
     * @param data     Dữ liệu cần ghi vào tệp
     */
    public static void createOrReplaceFile(Context context, String fileName, String data) {
        // Tạo một đối tượng File từ tên tệp
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            // Mở luồng đầu ra đến tệp
            fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);

            // Ghi dữ liệu vào tệp
            bw.write(data);
            bw.flush();
        } catch (IOException e) {
            Log.e("create-file", "createAndWriteFile: " + e.getMessage());
        } finally {
            // Đảm bảo luôn đóng luồng đầu ra sau khi sử dụng
            try {
                if (bw != null) {
                    bw.close();
                }
                if (osw != null) {
                    osw.close();
                }
                if (fos != null) {
                    fos.close();
                }

            } catch (IOException e) {
                Log.e("create-file", "createAndWriteFile: " + e.getMessage());
            }
        }
    }

    /**
     * Đọc dữ liệu từ tệp và trả về 1 đối tượng
     *
     * @param context  Context
     * @param fileName Tên tệp
     * @param classOfT Lớp của đối tượng cần trả về
     */
    public static <T> T readSingleObjectFromFile(Context context, String fileName, Class<T> classOfT) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fis = context.openFileInput(fileName);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            String line;
            T object = null;

            // Đọc từng dòng của tệp và chuyển thành đối tượng
            if ((line = br.readLine()) != null) {
                object = JsonConverter.jsonStringToObject(line, classOfT);
            }

            return object;
        } catch (IOException e) {
            Log.e("read-file", "readFromFile: " + e.getMessage());
            return null;
        } finally {
            // Đảm bảo đóng tất cả luồng sau khi sử dụng
            try {
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                Log.e("read-file", "readFromFile: " + e.getMessage());
            }
        }
    }

    /**
     * Dùng để đọc dữ liệu từ tệp và trả về một List
     *
     * @param context  Context
     * @param fileName Tên tệp
     * @param classOfT Lớp của đối tượng cần trả về
     */
    public static <T> List<T> readListFromJsonFile(Context context, String fileName, Class<T> classOfT) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fis = context.openFileInput(fileName);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            StringBuilder data = new StringBuilder();
            String line;

            // Đọc từng dòng của tệp và thêm vào StringBuilder
            while ((line = br.readLine()) != null) {
                data.append(line);
            }

            return JsonConverter.jsonStringToList(data.toString(), classOfT);

        } catch (IOException e) {
            Log.e("read-file", "readJsonFile: " + e.getMessage());
            return null;
        } finally {
            // Đảm bảo đóng tất cả luồng sau khi sử dụng
            try {
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                Log.e("read-file", "readJsonFile: " + e.getMessage());
            }
        }
    }
}

