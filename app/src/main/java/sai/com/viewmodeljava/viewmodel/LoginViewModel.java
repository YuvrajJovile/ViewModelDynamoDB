package sai.com.viewmodeljava.viewmodel;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.AWSStartupHandler;
import com.amazonaws.mobile.client.AWSStartupResult;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.Calendar;

import sai.com.viewmodeljava.databinding.LoginActivityBinding;
import sai.com.viewmodeljava.dbDJOs.UsersTableDO;
import sai.com.viewmodeljava.view.iview.ILoginActivityView;

public class LoginViewModel extends BaseViewModel {

    private Context mContext;
    private ILoginActivityView mILoginActivityView;
    private LoginActivityBinding mLoginActivityBinding;
    private DynamoDBMapper mDynamoDBMapper;


    public LoginViewModel(Context pContext, ILoginActivityView pILoginActivityView, LoginActivityBinding pLoginActivityBinding) {
        super(pILoginActivityView);
        this.mContext = pContext;
        this.mILoginActivityView = pILoginActivityView;
        this.mLoginActivityBinding = pLoginActivityBinding;
    }

    @Override
    public void onCreateViewModel(Bundle bundle) {
        initializeAws();

        mLoginActivityBinding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLoginActivityBinding.username.getText().toString().trim().length() > 0 && mLoginActivityBinding.password.getText().toString().trim().length() > 0) {
                    saveUserData(mLoginActivityBinding.username.getText().toString().trim(), mLoginActivityBinding.password.getText().toString().trim());
                }
            }
        });
    }


    private void initializeAws() {
        AWSMobileClient.getInstance().initialize(mContext, new AWSStartupHandler() {
            @Override
            public void onComplete(AWSStartupResult awsStartupResult) {
                Log.d("YourMainActivity", "AWSMobileClient is instantiated and you are connected to AWS!");
            }
        }).execute();


        AWSCredentialsProvider credentialsProvider = AWSMobileClient.getInstance().getCredentialsProvider();
        AWSConfiguration configuration = AWSMobileClient.getInstance().getConfiguration();

        // Add code to instantiate a AmazonDynamoDBClient
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(credentialsProvider);

        this.mDynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(configuration)
                .build();
    }


    private void saveUserData(final String pUserName, final String pPassWord) {

        new SaveDataAsync(pUserName,pPassWord,"TS" + Calendar.getInstance().getTimeInMillis()).execute();


    }


    class SaveDataAsync extends AsyncTask<Void,Void,Void>{

        private String lUserName;
        private String lPassWord;
        private String lTimeStamp;

        public SaveDataAsync(String lUserName, String lPassWord, String lTimeStamp) {
            this.lUserName = lUserName;
            this.lPassWord = lPassWord;
            this.lTimeStamp = lTimeStamp;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            UsersTableDO lUsersTableDO = new UsersTableDO();
            lUsersTableDO.setUserName(lUserName);
            lUsersTableDO.setPassWord(lPassWord);
            lUsersTableDO.set_timeStamp(lTimeStamp);
            mDynamoDBMapper.save(lUsersTableDO);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            new ReadDataAsync(lUserName).execute();
            super.onPostExecute(aVoid);
        }
    }


    class ReadDataAsync extends AsyncTask<Void,Void,Void>{

        private String lUserName;
        private UsersTableDO lUsersTableDO;

        public ReadDataAsync(String lUserName) {
            this.lUserName = lUserName;

        }

        @Override
        protected Void doInBackground(Void... voids) {
            lUsersTableDO = mDynamoDBMapper.load(UsersTableDO.class, lUserName);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mILoginActivityView.saveUserData(lUsersTableDO.getUserName(), lUsersTableDO.getPassWord());
            super.onPostExecute(aVoid);
        }
    }
}
