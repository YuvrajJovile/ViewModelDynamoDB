package sai.com.viewmodeljava.webservice;


import java.util.List;

import retrofit2.http.GET;
import rx.Observable;
import sai.com.viewmodeljava.model.ContactsData;

public interface ApiInterface {

    @GET("json/contacts.json")
    Observable<List<ContactsData>> performListContacts();

}
