package com.example.tluschedule.filemanager;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.tluschedule.data.model.TLUs.studentCourse.Course;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileActions {

    private static final String FILE_PATH = "tluschedule";

    public static void createAndWriteFile(Context context, String fileName, String data) {
        // Tạo một đối tượng File từ tên tệp
        FileOutputStream fos = null;

        try {
            fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            // Mở luồng đầu ra đến tệp, MODE_PRIVATE sẽ tạo một tệp mới nếu nó không tồn tại

            // Ghi dữ liệu vào tệp
            fos.write(data.getBytes());
//            Toast.makeText(context, "Tạo file thành công", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("createfile", "createAndWriteFile: " + e.getMessage());
        } finally {
            // Đảm bảo luôn đóng luồng đầu ra sau khi sử dụng
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    Log.e("createfile", "createAndWriteFile: " + e.getMessage());
                }
            }
        }
    }
    public static String readFromFile(Context context, String fileName) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            fis = context.openFileInput(fileName);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            StringBuilder stringBuilder = new StringBuilder();
            String line;

            // Đọc từng dòng của tệp và thêm vào StringBuilder
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            return stringBuilder.toString();
        } catch (IOException e) {
            Log.e("readfile", "readFromFile: " + e.getMessage());
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
                Log.e("readfile", "readFromFile: " + e.getMessage());
            }
        }
    }

    public static List<Course> readJsonFile(Context context, String fileName) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            fis = context.openFileInput(fileName);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            Gson gson = new GsonBuilder().create();

            List<Course> courses = new ArrayList<>();
            String line;

            // Đọc từng dòng của tệp và thêm vào StringBuilder
            while ((line = br.readLine()) != null) {
                Course course = gson.fromJson(line, Course.class);
                courses.add(course);
            }

            return courses;

        } catch (IOException e) {
            Log.e("readfile", "readJsonFile: " + e.getMessage());
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
                Log.e("readfile", "readJsonFile: " + e.getMessage());
            }
        }

    }

}

