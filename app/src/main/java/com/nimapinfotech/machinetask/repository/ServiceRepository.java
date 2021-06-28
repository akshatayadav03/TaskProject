package com.nimapinfotech.machinetask.repository;

import android.util.Log;

import com.nimapinfotech.machinetask.api.ServiceApi;
import com.nimapinfotech.machinetask.model.DataResponse;
import com.nimapinfotech.machinetask.utils.RetrofitClientInstance;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceRepository {
    public static MutableLiveData<DataResponse> getData() {
        ServiceApi serviceApi = RetrofitClientInstance.getRetrofitInstance().create(ServiceApi.class);
        final MutableLiveData<DataResponse>responseMutableLiveData =new MutableLiveData<>();
        Call<DataResponse> dataResponseCall =serviceApi.getDataResponse();
        final DataResponse dataResponse =new DataResponse();
        dataResponseCall.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.isSuccessful()) {
                    DataResponse data = response.body();
                    responseMutableLiveData.postValue(data);
                    Log.d("Res",data.getMessage());
                }
                else {
                    dataResponse.setMessage(response.message());
                    dataResponse.setStatus(response.code());
                }

            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                dataResponse.setMessage("Please Check Internet Connection");
                dataResponse.setStatus(400);
                responseMutableLiveData.setValue(dataResponse);
            }
        });

        return responseMutableLiveData;

    }
}
