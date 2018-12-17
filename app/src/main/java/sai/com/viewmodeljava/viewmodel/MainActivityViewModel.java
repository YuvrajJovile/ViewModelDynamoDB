package sai.com.viewmodeljava.viewmodel;


import android.content.Context;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sai.com.viewmodeljava.databinding.MainActivityBinding;
import sai.com.viewmodeljava.model.ContactsData;
import sai.com.viewmodeljava.view.iview.IMainActivityView;
import sai.com.viewmodeljava.webservice.ApiClient;
import sai.com.viewmodeljava.webservice.ApiInterface;


public class MainActivityViewModel extends BaseViewModel {

    private Context mContext;
    private Subscription mSubscription;
    private MainActivityBinding activityDataBinding;
    private IMainActivityView iMainActivityView;
    private List<ContactsData> mContactsList;

    public MainActivityViewModel(Context mContext, IMainActivityView iMainActivityView, MainActivityBinding activityDataBinding) {
        super(iMainActivityView);
        this.mContext = mContext;
        this.iMainActivityView = iMainActivityView;
        this.activityDataBinding = activityDataBinding;
    }

    @Override
    public void onCreateViewModel(Bundle bundle) {
        apiCallToGetContacts();
    }

    private void apiCallToGetContacts() {
        if (iMainActivityView.hasNetworkConnection()) {

            //iMainActivityView.showProgressbar();
            activityDataBinding.shimmerViewContainer.startShimmer();
            activityDataBinding.shimmerViewContainer.setVisibility(View.VISIBLE);
            mSubscription = ApiClient.getClient().create(ApiInterface.class).performListContacts()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<ContactsData>>() {
                        @Override
                        public void onCompleted() {
                            activityDataBinding.shimmerViewContainer.stopShimmer();
                            activityDataBinding.shimmerViewContainer.setVisibility(View.GONE);
                            //iMainActivityView.dismissProgressbar();
                            if (iMainActivityView != null) {
                                iMainActivityView.contactsListSync(mContactsList);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            //iMainActivityView.dismissProgressbar();
                            activityDataBinding.shimmerViewContainer.stopShimmer();
                            activityDataBinding.shimmerViewContainer.setVisibility(View.GONE);
                        }

                        @Override
                        public void onNext(List<ContactsData> contactsData) {
                            //iMainActivityView.dismissProgressbar();
                            activityDataBinding.shimmerViewContainer.stopShimmer();
                            activityDataBinding.shimmerViewContainer.setVisibility(View.GONE);
                            if (!contactsData.isEmpty()) {
                                MainActivityViewModel.this.mContactsList = contactsData;
                            }
                        }
                    });
        } else {
            iMainActivityView.showNetworkMessage();
        }
    }

    @Override
    public void onDestroyViewModel() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void onResumeViewModel() {
        apiCallToGetContacts();
    }
}
