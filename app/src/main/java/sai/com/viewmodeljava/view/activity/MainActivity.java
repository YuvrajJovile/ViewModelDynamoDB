package sai.com.viewmodeljava.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import sai.com.viewmodeljava.R;
import sai.com.viewmodeljava.adapter.ContactsAdapter;
import sai.com.viewmodeljava.databinding.MainActivityBinding;
import sai.com.viewmodeljava.model.ContactsData;
import sai.com.viewmodeljava.view.iview.IMainActivityView;
import sai.com.viewmodeljava.viewmodel.MainActivityViewModel;

public class MainActivity extends BaseActivity<MainActivityBinding> implements IMainActivityView {

    private MainActivityBinding activityDataBinding;
    private MainActivityViewModel mLauncherViewModel;
    private ContactsAdapter mContactsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLauncherViewModel = new MainActivityViewModel(this, this, activityDataBinding);
        mLauncherViewModel.onCreateViewModel(getIntent().getExtras());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLauncherViewModel.onDestroyViewModel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLauncherViewModel.onResumeViewModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_activity;
    }

    @Override
    public void setActivityViewModel(MainActivityBinding activityDataBinding) {
        this.activityDataBinding = activityDataBinding;
        activityDataBinding.setMovies(mLauncherViewModel);
        activityDataBinding.rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void contactsListSync(List<ContactsData> data) {
        if (mContactsAdapter == null) {
            mContactsAdapter = new ContactsAdapter(data);
            activityDataBinding.rvContacts.setAdapter(mContactsAdapter);
        } else {
            mContactsAdapter.notifyDataSetChanged();
        }
    }
}
