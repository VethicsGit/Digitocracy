package com.example.kanika.digitocracy.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kanika.digitocracy.APIResponse.PollCategoryList.PollCategoryList;
import com.example.kanika.digitocracy.APISHelper.API;
import com.example.kanika.digitocracy.APISHelper.APIS;
import com.example.kanika.digitocracy.PollCategories;
import com.example.kanika.digitocracy.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_PollCategories extends Fragment {

    GridView pollCategoryList;
    SharedPreferences LoginPref;
    TextView title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_poll_categories,container,false);
        pollCategoryList = v.findViewById(R.id.pollCategoryList);
//       new PollCategories().setTitle("Home");


        LoginPref = getContext().getSharedPreferences("LoginStatus", Context.MODE_PRIVATE);

        API apiService = APIS.getRetrofit().create(API.class);
        Call<PollCategoryList> call1 = apiService.Poll_category_list(LoginPref.getString("user_id", ""), LoginPref.getString("token", ""));
        call1.enqueue(new Callback<PollCategoryList>() {
            @Override
            public void onResponse(@NonNull Call<PollCategoryList> call, @NonNull Response<PollCategoryList> response) {
                PollCategoryList resPoll = response.body();
                List<com.example.kanika.digitocracy.APIResponse.PollCategoryList.Response> resList = resPoll.getResponse();
                for (int i = 0; i < resList.size(); i++) {
                    com.example.kanika.digitocracy.APIResponse.PollCategoryList.Response response1 = resList.get(i);
                    List<com.example.kanika.digitocracy.APIResponse.PollCategoryList.PollCategoryList_> pollList = response1.getPollCategoryList();

                    PollCategoryAdapter adapter = new PollCategoryAdapter(pollList, getContext());
                    pollCategoryList.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<PollCategoryList> call, Throwable t) {

            }
        });
        return v;
    }
}
class PollCategoryAdapter extends BaseAdapter {

    List<com.example.kanika.digitocracy.APIResponse.PollCategoryList.PollCategoryList_> pollList;
    Context context;

    public PollCategoryAdapter(List<com.example.kanika.digitocracy.APIResponse.PollCategoryList.PollCategoryList_> pollList, Context context) {
        this.pollList = pollList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return pollList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(R.layout.poll_category_list_layout, viewGroup, false);

        ImageView image = v.findViewById(R.id.poll_cate_list_img);
        TextView txt = v.findViewById(R.id.poll_cate_list_txt);
        TextView txt_id = v.findViewById(R.id.poll_cate_list_txtid);

        com.example.kanika.digitocracy.APIResponse.PollCategoryList.PollCategoryList_ poll = pollList.get(i);
        Glide.with(context).load(poll.getPollCategoryIcon()).into(image);
        txt.setText(poll.getPollCategoryTitle());
        txt_id.setText(poll.getPollCategoryId());

        return v;
    }
}
