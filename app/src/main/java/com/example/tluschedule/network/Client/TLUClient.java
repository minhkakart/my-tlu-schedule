package com.example.tluschedule.network.Client;


import com.example.tluschedule.network.Services.TluApiService;

public class TLUClient extends BaseClient {
    private static final String BASE_URL = "https://sinhvien1.tlu.edu.vn:8098/education/";
    private static final String BACKUP_URL = "https://sinhvien4.tlu.edu.vn:8098/education/";
    private static TluApiService tluApi = null;

    public static TluApiService getInstance() {
        if (tluApi == null) {
            tluApi = createService(TluApiService.class, BASE_URL);
        }
        return tluApi;
    }
    public static TluApiService getBackupInstance() {
        if (tluApi == null) {
            return createService(TluApiService.class, BACKUP_URL);
        }
        return tluApi;
    }
}
