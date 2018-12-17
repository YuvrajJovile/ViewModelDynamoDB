package sai.com.viewmodeljava.adapter;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;



import java.util.List;

import sai.com.viewmodeljava.R;
import sai.com.viewmodeljava.adapter.viewholder.ContactsViewHolder;
import sai.com.viewmodeljava.databinding.InflateContactsBinding;
import sai.com.viewmodeljava.model.ContactsData;


public class ContactsAdapter extends BaseRecyclerAdapter<ContactsData, ContactsViewHolder> {

    private InflateContactsBinding contactsBinding;

    public ContactsAdapter(List<ContactsData> data) {
        super(data);
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        contactsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.inflate_contacts, parent, false);
        return new ContactsViewHolder(contactsBinding);
    }
}
