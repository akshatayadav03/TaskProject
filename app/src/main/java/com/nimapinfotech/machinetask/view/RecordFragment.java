package com.nimapinfotech.machinetask.view;

import android.database.DatabaseUtils;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nimapinfotech.machinetask.R;
import com.nimapinfotech.machinetask.adapter.ViewAdapter;
import com.nimapinfotech.machinetask.connectivity.ConnectivityReceiver;
import com.nimapinfotech.machinetask.database.Database;
import com.nimapinfotech.machinetask.databinding.FragmentRecordBinding;
import com.nimapinfotech.machinetask.model.Data;
import com.nimapinfotech.machinetask.model.Record;
import com.nimapinfotech.machinetask.offline.Adapter;
import com.nimapinfotech.machinetask.offline.ImagesResponse;
import com.nimapinfotech.machinetask.repository.ServiceRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecordFragment extends Fragment {
  Database database;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecordFragment newInstance(String param1, String param2) {
        RecordFragment fragment = new RecordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentRecordBinding fragmentRecordBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_record,container,false);
        fragmentRecordBinding.progressBar.setVisibility(View.VISIBLE);
        ServiceRepository.getData().observeForever(dataResponse -> {
            boolean isConnected = ConnectivityReceiver.isConnected();
            Log.d("isConnected",isConnected+" ");
            if (isConnected == true){
                Data data = dataResponse.getData();
                ArrayList<Record> recordArrayList = (ArrayList<Record>) data.getRecords();
                fragmentRecordBinding.progressBar.setVisibility(View.GONE);
                ViewAdapter viewAdapter = new ViewAdapter(getContext(),recordArrayList);
                fragmentRecordBinding.setViewAdapter(viewAdapter);
                fragmentRecordBinding.recyclerview.setVisibility(View.VISIBLE);
                fragmentRecordBinding.viewPager.setVisibility(View.GONE);
            }
            else {
                Log.d("isConnected",isConnected+" ");
                database = Database.getInstance(getContext());
                List<ImagesResponse> imagesResponseList = new ArrayList<>();
                imagesResponseList.addAll(database.getImagePath());
                Adapter adapter = new Adapter(getContext(),imagesResponseList);
                fragmentRecordBinding.viewPager.setAdapter(adapter);
                fragmentRecordBinding.progressBar.setVisibility(View.GONE);
                fragmentRecordBinding.viewPager.setVisibility(View.VISIBLE);
                fragmentRecordBinding.recyclerview.setVisibility(View.GONE);

            }

        });
        return fragmentRecordBinding.getRoot();
    }



}