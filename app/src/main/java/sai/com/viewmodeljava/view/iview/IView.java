package sai.com.viewmodeljava.view.iview;


import sai.com.viewmodeljava.viewmodel.IViewModel;

public interface IView {
    void bindViewModel(IViewModel iViewModel);

    void showProgressbar();

    void dismissProgressbar();

    void showNetworkMessage();

    boolean hasNetworkConnection();
}
