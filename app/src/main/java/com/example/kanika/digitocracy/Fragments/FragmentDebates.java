package com.example.kanika.digitocracy.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.kanika.digitocracy.APIResponse.Aboutus.Aboutus;
import com.example.kanika.digitocracy.APIResponse.BlogList.BlogListResponse;
import com.example.kanika.digitocracy.APIResponse.DebatesList.DebateList;
import com.example.kanika.digitocracy.APIResponse.DebatesList.Debates;
import com.example.kanika.digitocracy.APISHelper.API;
import com.example.kanika.digitocracy.APISHelper.APIS;
import com.example.kanika.digitocracy.Adapter.DebatesAdapter;
import com.example.kanika.digitocracy.R;
import com.example.kanika.digitocracy.extra.RecyclerViewMargin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDebates extends Fragment {

    RecyclerView debates_recyclerView;
    DebatesAdapter adapter;
    List<DebateList>debateList;
    SharedPreferences Loginpref;
    int offset = 0;



    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_debates,container,false);

    debates_recyclerView=view.findViewById(R.id.debates_recyclerview);
    LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
    debates_recyclerView.setLayoutManager(layoutManager);

        LinearLayout.LayoutParams paramstlyn=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        debates_recyclerView.setLayoutParams(paramstlyn);

        RecyclerViewMargin decoration = new RecyclerViewMargin(10, 2);
        debates_recyclerView.addItemDecoration(decoration);

        Loginpref = getContext().getSharedPreferences("LoginStatus",Context.MODE_PRIVATE);
        API apiService = APIS.getRetrofit().create(API.class);
        retrofit2.Call<Debates> call=apiService.debates_list(Loginpref.getString("user_id", ""), offset,Loginpref.getString("token",""));


        call.enqueue(new Callback<Debates>() {
            @Override
            public void onResponse(retrofit2.Call<Debates> call, Response<Debates> response) {


                Gson gson = new GsonBuilder().create();
                JsonObject myCustomArray = gson.toJsonTree(response.body()).getAsJsonObject();
                Log.e("recycle","msg"+myCustomArray);

                Debates debates=response.body();
                List<com.example.kanika.digitocracy.APIResponse.DebatesList.Response> list = debates.getResponse();
                for (int i = 0 ;i< list.size();i++){
                    com.example.kanika.digitocracy.APIResponse.DebatesList.Response response1=list.get(i);
                    Log.e("Debates","respo"+response1);
                    if (response1.getStatus().equals("true")){
                        Log.e("response","response"+response1);
                        offset = response1.getOffset();
                        List<DebateList>debateLists=response1.getDebateList();
                        adapter = new DebatesAdapter(debateLists,getContext());
                        debates_recyclerView.setAdapter(adapter);
                        Log.e("_Offset",String.valueOf(offset));
                    }else {
                        Toast.makeText(getContext(),"invlid",Toast.LENGTH_LONG).show();
                    }
                }


            }

            @Override
            public void onFailure(retrofit2.Call<Debates> call, Throwable t) {

                Toast.makeText(getContext(),"debates not display",Toast.LENGTH_LONG).show();
            }
        });



        return view;
    }
}
