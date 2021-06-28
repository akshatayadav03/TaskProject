package com.nimapinfotech.machinetask.viewmodels;

import android.util.Log;
import com.nimapinfotech.machinetask.model.DataResponse;
import com.nimapinfotech.machinetask.model.Record;

import androidx.lifecycle.MutableLiveData;
public class Servicemodule {
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<String> from_date = new MutableLiveData<>();
    public MutableLiveData<String> to_date = new MutableLiveData<>();
    public MutableLiveData<DataResponse> dataResponseMutableLiveData = new MutableLiveData<DataResponse>();
    public MutableLiveData<Record> recordMutableLiveData = new MutableLiveData<>();



}
