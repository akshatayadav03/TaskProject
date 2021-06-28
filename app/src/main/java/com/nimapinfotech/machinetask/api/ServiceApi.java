package com.nimapinfotech.machinetask.api;


import com.nimapinfotech.machinetask.BuildConfig;
import com.nimapinfotech.machinetask.model.DataResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface  ServiceApi {
    @GET(BuildConfig.TEST_DATA)
    Call<DataResponse> getDataResponse();
}
