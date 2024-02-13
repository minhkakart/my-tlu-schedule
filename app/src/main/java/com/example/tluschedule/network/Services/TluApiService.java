package com.example.tluschedule.network.Services;


import com.example.tluschedule.data.model.ReceiveToken;
import com.example.tluschedule.data.model.TLUs.semester.SemesterReceiver;
import com.example.tluschedule.data.model.TLUs.studentCourse.Course;
import com.example.tluschedule.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TluApiService {
    @Headers({
            "Content-Type: application/json"
    })
    @POST("oauth/token")
    Call<ReceiveToken> getToken(@Body User user);

    @GET("api/semester/1/100")
    Call<SemesterReceiver> getSemesterInfo(@Header("Authorization") String token);

    @GET("api/StudentCourseSubject/studentLoginUser/{id}")
    Call<List<Course>> getStudentCourseSubject(@Header("Authorization") String token, @Path("id") int id);

}
