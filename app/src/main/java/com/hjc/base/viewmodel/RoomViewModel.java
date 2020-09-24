package com.hjc.base.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.hjc.base.bean.db.DBInstance;
import com.hjc.base.bean.db.Person;
import com.hjc.baselib.viewmodel.CommonViewModel;

import java.util.List;

public class RoomViewModel extends CommonViewModel {

    private MutableLiveData<List<Person>> personData = new MutableLiveData<>();

    public RoomViewModel(@NonNull Application application) {
        super(application);
    }

    public void insert() {
        Person person = new Person("张三", 18);
        DBInstance.getInstance(getApplication()).getPersonDao().insert(person);
    }

    public void query() {
        List<Person> personList = DBInstance.getInstance(getApplication()).getPersonDao().getAll();
        personData.setValue(personList);
    }

    public void update() {
        Person person = new Person("李四", 24);
        person.setId(1);
        DBInstance.getInstance(getApplication()).getPersonDao().update(person);
    }

    public void delete() {
        DBInstance.getInstance(getApplication()).getPersonDao().deleteAll();
    }

    public MutableLiveData<List<Person>> getPersonData() {
        return personData;
    }
}
