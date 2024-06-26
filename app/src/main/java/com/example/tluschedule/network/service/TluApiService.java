package com.example.tluschedule.network.service;


import com.example.tluschedule.data.models.JsonModelBase;
import com.example.tluschedule.data.models.ReceiveToken;
import com.example.tluschedule.data.models.tluModels.drl.DrlModel;
import com.example.tluschedule.data.models.tluModels.lichThi.LichThi;
import com.example.tluschedule.data.models.tluModels.userinfo.CurrentUser;
import com.example.tluschedule.data.models.tluModels.semester.SemesterReceiver;
import com.example.tluschedule.data.models.tluModels.studentCourse.Course;
import com.example.tluschedule.data.models.UserLoginData;

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
    Call<ReceiveToken> getToken(@Body UserLoginData userLoginData);

    @GET("api/semester/1/100")
    Call<SemesterReceiver> getSemesterInfo(@Header("Authorization") String token);

    @GET("api/StudentCourseSubject/studentLoginUser/{id}")
    Call<List<Course>> getStudentCourseSubject(@Header("Authorization") String token, @Path("id") int id);

    @GET("api/registerperiod/find/{id}")
    Call<List<? extends JsonModelBase>> layDotThiTheoKy(@Header("Authorization") String token, @Path("id") int id);

    @GET("api/users/getCurrentUser")
    Call<CurrentUser> getCurrentUser(@Header("Authorization") String token);

    @GET()
    void getSomething(@Header("Authorization") String token);

    @GET("api/semestersubjectexamroom/getListRoomByStudentByLoginUser/{hocki}/{dotthi}/1")
    Call<List<LichThi>> getLichThi(@Header("Authorization") String token, @Path("hocki") int hocki, @Path("dotthi") int dotthi);

    @GET("api/student_semester_behavior_mark/viewStudentBehaviorMarkByLoginUser")
    Call<DrlModel> getDrl(@Header("Authorization") String token);

}
