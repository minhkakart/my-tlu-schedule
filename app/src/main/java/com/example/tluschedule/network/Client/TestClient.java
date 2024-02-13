package com.example.tluschedule.network.Client;

import com.example.tluschedule.network.Services.TestService;
import com.example.tluschedule.network.Services.TluApiService;

public class TestClient extends BaseClient{
    private static final String BASE_URL = "https://sinhvien1.tlu.edu.vn:8098/education/";
    private static TestService tluApi = null;

    public static TestService getInstance() {
        if (tluApi == null) {
            tluApi = createService(TestService.class, BASE_URL);
        }
        return tluApi;
    }
}
