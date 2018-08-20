package com.example.kanika.digitocracy.Polling;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kanika.digitocracy.APIResponse.PollList_Response.PollList;
import com.example.kanika.digitocracy.APIResponse.PollList_Response.PollListResponse;
import com.example.kanika.digitocracy.APISHelper.API;
import com.example.kanika.digitocracy.APISHelper.APIS;
import com.example.kanika.digitocracy.Adapter.PollClosedAdapter;
import com.example.kanika.digitocracy.Adapter.PollLiveAdapter;
import com.example.kanika.digitocracy.R;
import com.example.kanika.digitocracy.extra.PaginationScrollListener;
import com.example.kanika.digitocracy.extra.RecyclerViewMargin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Polling_closed_list_fragment extends Fragment {

    View view;
    RecyclerView polling_recyclar_view;
    SwipeRefreshLayout swipe_container_polling;

    private boolean isLoading = false;
    int offset = 0;
    PollClosedAdapter adapter;
    SharedPreferences LoginPref;

    int pollId;

    @SuppressLint("ValidFragment")
    public Polling_closed_list_fragment(int pollId) {
        this.pollId = pollId;
    }

    public Polling_closed_list_fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_polling_list,container,false);
        swipe_container_polling=view.findViewById(R.id.swipe_container_polling);
        polling_recyclar_view=view.findViewById(R.id.polling_recyclar_view);

        final LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        polling_recyclar_view.setLayoutManager(manager);
        RecyclerViewMargin decoration = new RecyclerViewMargin(10, 1);
        polling_recyclar_view.addItemDecoration(decoration);






        if (offset==0) {
            LoadFirstItem();
        }

        swipe_container_polling = view.findViewById(R.id.swipe_container_polling);
        swipe_container_polling.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                offset=0;
                LoadFirstItem();
            }
        });

        polling_recyclar_view.addOnScrollListener(new PaginationScrollListener(manager,getContext()) {
            @Override
            protected void loadMoreItems() {
                ServiceCall();
                isLoading=true;

            }

            @Override
            public int getTotalPageCount() {
                return 0;
            }

            @Override
            public boolean isLastPage() {
                return false;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
        LoginPref = getContext().getSharedPreferences("LoginStatus", Context.MODE_PRIVATE);

        return view;
    }

    public void LoadFirstItem(){
        LoginPref = getContext().getSharedPreferences("LoginStatus", Context.MODE_PRIVATE);
        final ProgressDialog mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        API apiService = APIS.getRetrofit().create(API.class);
        Call<PollListResponse> call1 = apiService.Poll_list(LoginPref.getString("user_id", ""),offset,"past" , pollId, LoginPref.getString("token", ""));

        call1.enqueue(new Callback<PollListResponse>() {
            @Override
            public void onResponse(@NonNull Call<PollListResponse> call, @NonNull Response<PollListResponse> response) {
                PollListResponse blogListResponse = response.body();
                List<com.example.kanika.digitocracy.APIResponse.PollList_Response.Response> resList = blogListResponse.getResponse();

                Gson gson = new GsonBuilder().create();
                JsonObject myCustomArray = gson.toJsonTree(blogListResponse).getAsJsonObject();
                Log.e("_poll_Closed",myCustomArray.toString());
                for (int i = 0; i < resList.size(); i++) {
                    com.example.kanika.digitocracy.APIResponse.PollList_Response.Response re = resList.get(i);
                    if (re.getStatus().equals("true")) {
                        offset = re.getOffset();
                        List<PollList> blogLists = re.getPollList();
                        adapter=new PollClosedAdapter(blogLists,getContext());
                        polling_recyclar_view.setAdapter(adapter);
                        mProgressDialog.dismiss();
                        Log.e("_Offset",String.valueOf(offset));
                        isLoading=false;
                        swipe_container_polling.setRefreshing(false);
                    }else {
                        mProgressDialog.dismiss();
                        Toast.makeText(getContext(), "No more Polls", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<PollListResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Something broken in the way.", Toast.LENGTH_SHORT).show();
                mProgressDialog.dismiss();
            }
        });
    }

    public void ServiceCall() {

        LoginPref = getContext().getSharedPreferences("LoginStatus", Context.MODE_PRIVATE);
        final ProgressDialog mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        API apiService = APIS.getRetrofit().create(API.class);
        Call<PollListResponse> call1 = apiService.Poll_list(LoginPref.getString("user_id", ""),offset,"past" , pollId, LoginPref.getString("token", ""));

        call1.enqueue(new Callback<PollListResponse>() {
            @Override
            public void onResponse(Call<PollListResponse> call, Response<PollListResponse> response) {
                PollListResponse blogListResponse = response.body();

                Gson gson = new GsonBuilder().create();
                JsonObject myCustomArray = gson.toJsonTree(blogListResponse).getAsJsonObject();
                Log.e("_poll_Closed",myCustomArray.toString());

                List<com.example.kanika.digitocracy.APIResponse.PollList_Response.Response> resList = blogListResponse.getResponse();
                for (int i = 0; i < resList.size(); i++) {
                    com.example.kanika.digitocracy.APIResponse.PollList_Response.Response re = resList.get(i);
                    if (re.getStatus().equals("true")) {
                        offset = re.getOffset();
                        List<PollList> blogLists = re.getPollList();
                        for (int x = 0; x < blogLists.size(); x++)
                            adapter.add(blogLists.get(x));
                        adapter.notifyDataSetChanged();
                        mProgressDialog.dismiss();
                        Log.e("_Offset",String.valueOf(offset));
                        isLoading=false;
                    }else {
                        mProgressDialog.dismiss();
                        Toast.makeText(getContext(), "No more Polls", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<PollListResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Something broken in the way.", Toast.LENGTH_SHORT).show();
                mProgressDialog.dismiss();
            }
        });
    }


}
