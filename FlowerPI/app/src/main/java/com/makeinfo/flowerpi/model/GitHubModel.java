package com.makeinfo.flowerpi.model;

import com.makeinfo.flowerpi.API.GitHubService;
import com.makeinfo.flowerpi.bean.User;
import com.makeinfo.flowerpi.net.ServiceGenerator;
import com.makeinfo.flowerpi.vm.MainViewModel;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Andy on 1/25/2016.
 */
public class GitHubModel {
    private GitHubService git;
    private MainViewModel viewModel;


    public GitHubModel(MainViewModel viewModel) {
        this.viewModel = viewModel;
        this.git =  ServiceGenerator.createService(GitHubService.class);

    }

   public void getUser(String username) {
        //binding.username.getText().toString()
        Call call = git.getUser(username);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response) {
                User model = response.body();

                if (model == null) {
                    //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        try {
                            viewModel.setText("responseBody = " + responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        viewModel.setText("responseBody  = null");
                    }
                } else {
                    //200
                    viewModel.setText("Github Name :" + model.getName() + "\nWebsite :" + model.getBlog() + "\nCompany Name :" + model.getCompany());
                }
                viewModel.setPb(false);
            }

            @Override
            public void onFailure(Throwable t) {
                viewModel.setText("t = " + t.getMessage());
                viewModel.setPb(false);
            }
        });
    }

}
