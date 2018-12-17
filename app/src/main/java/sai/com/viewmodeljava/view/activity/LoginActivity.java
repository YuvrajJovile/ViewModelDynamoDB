package sai.com.viewmodeljava.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.AWSStartupHandler;
import com.amazonaws.mobile.client.AWSStartupResult;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.Calendar;

import sai.com.viewmodeljava.R;
import sai.com.viewmodeljava.databinding.LoginActivityBinding;
import sai.com.viewmodeljava.dbDJOs.UsersTableDO;
import sai.com.viewmodeljava.view.iview.ILoginActivityView;
import sai.com.viewmodeljava.viewmodel.LoginViewModel;

public class LoginActivity extends BaseActivity<LoginActivityBinding> implements ILoginActivityView {

    private LoginActivityBinding mLoginActivityBinding;
    private LoginViewModel mLoginViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginViewModel = new LoginViewModel(this, this, mLoginActivityBinding);
        mLoginViewModel.onCreateViewModel(getIntent().getExtras());

    }


    @Override
    public int getLayoutId() {
        return R.layout.login_activity;
    }

    @Override
    public void setActivityViewModel(LoginActivityBinding activityDataBinding) {

        this.mLoginActivityBinding = activityDataBinding;
        mLoginActivityBinding.setLogin(mLoginViewModel);

    }


    @Override
    protected void onResume() {
        super.onResume();
        mLoginViewModel.onResumeViewModel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginViewModel.onDestroyViewModel();
    }



    @Override
    public void saveUserData(final String pUserName, final String pPassword) {
        showMessage(pUserName+" Saved in DB "+pPassword);
    }
}
