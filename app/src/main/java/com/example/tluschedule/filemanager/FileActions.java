package com.example.tluschedule.filemanager;

import android.content.Context;
import android.util.Log;

import com.example.tluschedule.supporter.converter.JsonConverter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileActions {

    public static void createAndWriteFile(Context context, String fileName, String data) {
        // Tạo một đối tượng File từ tên tệp
        FileOutputStream fos = null;

        try {
            // Mở luồng đầu ra đến tệp, MODE_PRIVATE sẽ tạo một tệp mới nếu nó không tồn tại
            fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);

            // Ghi dữ liệu vào tệp
            fos.write(data.getBytes());
        } catch (IOException e) {
            Log.e("create-file", "createAndWriteFile: " + e.getMessage());
        } finally {
            // Đảm bảo luôn đóng luồng đầu ra sau khi sử dụng
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    Log.e("create-file", "createAndWriteFile: " + e.getMessage());
                }
            }
        }
    }

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

            // Đọc từng dòng của tệp và thêm vào StringBuilder
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

    public static <T> List<T> readListFromJsonFile(Context context, String fileName, Class<T> classOfT) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            fis = context.openFileInput(fileName);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            List<T> list = new ArrayList<>();
            String line;

            // Đọc từng dòng của tệp và thêm vào StringBuilder
            while ((line = br.readLine()) != null) {
                T course = JsonConverter.jsonStringToObject(line, classOfT);
                list.add(course);
            }

            return list;

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

