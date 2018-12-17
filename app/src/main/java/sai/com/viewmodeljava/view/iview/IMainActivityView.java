package sai.com.viewmodeljava.view.iview;


import java.util.List;

import sai.com.viewmodeljava.model.ContactsData;

public interface IMainActivityView extends IView {
    void contactsListSync(List<ContactsData> data);
}
