package com.makeinfo.flowerpi.vm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.makeinfo.flowerpi.API.GitHubService;
import com.makeinfo.flowerpi.BR;
import com.makeinfo.flowerpi.databinding.ActivityMainBinding;
import com.makeinfo.flowerpi.model.User;
import com.makeinfo.flowerpi.net.ServiceGenerator;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Andy on 1/25/2016.
 */
public class MainViewModel extends BaseObservable {


    private String text;
    private boolean pb;
    private ActivityMainBinding binding;
    private GitHubService git;
    private View.OnClickListener btnClickListener;

    public MainViewModel(ActivityMainBinding binding) {
        this.binding = binding;
        init();
    }

    private void init() {
        git = ServiceGenerator.createService(GitHubService.class);

        this.btnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPb(true);
                Call call = git.getUser(binding.username.getText().toString());
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Response<User> response) {
                        User model = response.body();

                        if (model == null) {
                            //404 or the response cannot be converted to User.
                            ResponseBody responseBody = response.errorBody();
                            if (responseBody != null) {
                                try {
                                    setText("responseBody = " + responseBody.string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                setText("responseBody  = null");
                            }
                        } else {
                            //200
                            setText("Github Name :" + model.getName() + "\nWebsite :" + model.getBlog() + "\nCompany Name :" + model.getCompany());
                        }
                        setPb(false);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        setText("t = " + t.getMessage());
                        setPb(false);
                    }
                });
            }
        };
    }

    public View.OnClickListener getBtnClickListener() {
        return btnClickListener;
    }

    @Bindable
    public String getText() {
        return text;
    }

    @Bindable
    public boolean isPb() {
        return pb;
    }

    public void setPb(boolean pb) {
        this.pb = pb;
        notifyPropertyChanged(BR.pb);
    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(BR.text);
    }
}
