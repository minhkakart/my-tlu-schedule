package com.example.tluschedule.ui.drl;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tluschedule.R;
import com.example.tluschedule.config.ConstantValues;
import com.example.tluschedule.data.models.ReceiveToken;
import com.example.tluschedule.data.models.UserLoginData;
import com.example.tluschedule.data.models.tluModels.drl.DrlDisplay;
import com.example.tluschedule.data.models.tluModels.drl.DrlModel;
import com.example.tluschedule.data.models.tluModels.drl.SchoolYearBehaviorMark;
import com.example.tluschedule.data.models.tluModels.drl.SemesterMark;
import com.example.tluschedule.filemanager.FileActions;
import com.example.tluschedule.network.clients.TLUClient;
import com.example.tluschedule.network.service.TluApiService;
import com.example.tluschedule.supporter.reducer.CallbackReduce;
import com.example.tluschedule.ui.login.LoginSupporter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class DrlActivity extends AppCompatActivity {

    private RecyclerView rvDrl;
    private DrlAdapter drlAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_drl);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        UserLoginData currUserLoginData = FileActions.readSingleObjectFromFile(this, ConstantValues.LOGGED_USER_FILE_NAME, UserLoginData.class);

        if (currUserLoginData == null) {
            Toast.makeText(this, "No user logged in.", Toast.LENGTH_SHORT).show();
        } else {
            rvDrl = findViewById(R.id.rvDrl);
            drlAdapter = new DrlAdapter();
            rvDrl.setAdapter(drlAdapter);
            rvDrl.setLayoutManager(new LinearLayoutManager(this));
            progressBar = findViewById(R.id.progress);
            progressBar.setVisibility(ProgressBar.VISIBLE);
            new LoginSupporter(currUserLoginData) {

                @Override
                public void onLoginSuccess(ReceiveToken token) {
                    String accessToken = token.getAccess_token();
                    getDrl(accessToken);
                }

                @Override
                public void onLoginFailed(String message) {

                }
            };
        }

    }

    private void getDrl(String accessToken) {
        TluApiService service = TLUClient.getInstance();
        Call<DrlModel> call = service.getDrl("Bearer " + accessToken);
        call.enqueue(new CallbackReduce<DrlModel>() {
            @Override
            public void onFinished(@NonNull Call<DrlModel> call, @Nullable Response<DrlModel> response, @Nullable Throwable t) {
                if (response != null) {
                    DrlModel drlModel = response.body();
                    List<DrlDisplay> drlDisplays = new ArrayList<>();
                    for (SchoolYearBehaviorMark schoolYearBehaviorMark : drlModel.getSchoolYearBehaviorMarks()) {
                        for (SemesterMark semesterMark :
                                schoolYearBehaviorMark.getSemesterMarks()) {
                            DrlDisplay drlDisplay = new DrlDisplay();
                            drlDisplay.setYear("//");
                            drlDisplay.setSemester(semesterMark.getSemester().getSemesterCode());
                            drlDisplay.setDrl(String.valueOf(semesterMark.getMark()));
                            drlDisplay.setSort(semesterMark.getSort());
                            drlDisplays.add(drlDisplay);
                        }
                        DrlDisplay drlDisplay = new DrlDisplay();
                        drlDisplay.setYear(schoolYearBehaviorMark.getSchoolYear().getName());
                        drlDisplay.setSemester("Cả năm");
                        drlDisplay.setDrl(String.valueOf(schoolYearBehaviorMark.getMark()));
                        drlDisplay.setSort(schoolYearBehaviorMark.getSort());
                        drlDisplays.add(drlDisplay);
                    }
                    DrlDisplay drlDisplay = new DrlDisplay();
                    drlDisplay.setYear("Toàn khóa");
                    drlDisplay.setSemester("");
                    drlDisplay.setDrl(String.valueOf(drlModel.getMark()));
                    drlDisplay.setSort(drlModel.getSort());
                    drlDisplays.add(drlDisplay);
                    drlAdapter.update(drlDisplays);
                } else {
                    Toast.makeText(DrlActivity.this, "Get failed", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(ProgressBar.GONE);
            }
        });
    }
}