package com.example.tluschedule.ui.lichthi;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tluschedule.R;
import com.example.tluschedule.config.ConstantValues;
import com.example.tluschedule.data.models.ReceiveToken;
import com.example.tluschedule.data.models.UserLoginData;
import com.example.tluschedule.data.models.tluModels.semester.SemesterContent;
import com.example.tluschedule.data.models.tluModels.semester.SemesterReceiver;
import com.example.tluschedule.data.models.tluModels.semester.SemesterRegisterPeriod;
import com.example.tluschedule.filemanager.FileActions;
import com.example.tluschedule.network.clients.TLUClient;
import com.example.tluschedule.network.service.TluApiService;
import com.example.tluschedule.supporter.reducer.CallbackReduce;
import com.example.tluschedule.ui.login.LoginSupporter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class LichThiActivity extends AppCompatActivity {

    Spinner spHocKy, spDotThi;
    TluApiService service;
    Context ctx;
    int hocKyId, dotThiId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lich_thi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ctx = this.getApplicationContext();

        service = TLUClient.getInstance();

        spHocKy = findViewById(R.id.sp_hoc_ky);
        spDotThi = findViewById(R.id.sp_dot_thi);

        UserLoginData loggedUser = FileActions.readSingleObjectFromFile(this, ConstantValues.LOGGED_USER_FILE_NAME, UserLoginData.class);

        new LoginSupporter(loggedUser) {

            @Override

            public void onLoginSuccess(ReceiveToken token) {
                getSemesterInfo(token);
            }

            @Override
            public void onLoginFailed(String message) {
                Toast.makeText(ctx, "Login failed!", Toast.LENGTH_SHORT).show();
            }
        };

    }

    void getSemesterInfo(ReceiveToken token) {
        Call<SemesterReceiver> semesterReceiverCall = service.getSemesterInfo("Bearer " + token.getAccess_token());
        semesterReceiverCall.enqueue(new CallbackReduce<SemesterReceiver>() {
            @Override
            public void onFinished(@NonNull Call<SemesterReceiver> call, @Nullable Response<SemesterReceiver> response, @Nullable Throwable t) {
                assert response != null;
                if (response.isSuccessful()) {
                    SemesterReceiver semesterReceiver = response.body();
                    Map<String, SemesterContent> hocKyList = new HashMap<>();
                    for (SemesterContent hocKy : semesterReceiver.getContent()) {
                        hocKyList.put(hocKy.getSemesterCode(), hocKy);
                    }
                    ArrayAdapter<String> hocKyAdapter = new ArrayAdapter<>(LichThiActivity.this, android.R.layout.simple_spinner_item, hocKyList.keySet().toArray(new String[0]));
                    hocKyAdapter.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);
                    spHocKy.setAdapter(hocKyAdapter);
                    spHocKy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                            Toast.makeText(ctx, "Item selected position: ", Toast.LENGTH_SHORT).show();
                            SemesterContent hocKyDaChon = hocKyList.get(spHocKy.getSelectedItem().toString());
                            assert hocKyDaChon != null;
                            hocKyId = hocKyDaChon.getId();

                            List<SemesterRegisterPeriod> semesterRegisterPeriods = hocKyDaChon.getSemesterRegisterPeriods();

                            Map<String, Integer> dotThiList = new HashMap<>();
                            for (SemesterRegisterPeriod dotThi : semesterRegisterPeriods) {
                                dotThiList.put(dotThi.getName(), dotThi.getId());
                            }
                            List<String> dotThiNames = new ArrayList<>(dotThiList.keySet());
                            ArrayAdapter<String> dotThiAdapter = new ArrayAdapter<>(LichThiActivity.this, android.R.layout.simple_spinner_item, dotThiNames);
                            dotThiAdapter.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);
                            spDotThi.setAdapter(dotThiAdapter);

                            spDotThi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    dotThiId = dotThiList.get(spDotThi.getSelectedItem().toString());
                                    layLichThi(hocKyId, dotThiId);
                                    Toast.makeText(ctx, "dot thi Item selected position: " + dotThiList.get(spDotThi.getSelectedItem().toString()), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
        });
    }

    void layLichThi(int hocKyId, int dotThiId) {
        Toast.makeText(ctx, "lich thi api: https://sinhvien1.tlu.edu.vn:8098/education/api/semestersubjectexamroom/getListRoomByStudentByLoginUser/" + hocKyId + "/" + dotThiId + "/1", Toast.LENGTH_SHORT).show();
    }

}