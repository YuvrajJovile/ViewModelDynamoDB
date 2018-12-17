package sai.com.viewmodeljava.adapter.viewholder;

import sai.com.viewmodeljava.databinding.InflateContactsBinding;
import sai.com.viewmodeljava.model.ContactsData;
import sai.com.viewmodeljava.viewmodel.ContactsViewModel;

public class ContactsViewHolder extends BaseViewHolder<ContactsData, ContactsViewModel> {


    private InflateContactsBinding contactsBinding;

    public ContactsViewHolder(InflateContactsBinding contactsBinding) {
        super(contactsBinding.cardView);
        this.contactsBinding = contactsBinding;
    }

    @Override
    void populateData() {
        if (contactsBinding.getContacts() == null) {
            contactsBinding.setContacts(new ContactsViewModel(itemView.getContext(), data));
        } else {
            contactsBinding.getContacts().notifyContactsList(data);
        }
    }
}
