package com.cobosideas.lista;

import android.content.Context;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.cobosideas.lista.room.core.CoreDataBase;
import com.cobosideas.lista.room.models.ItemRoom;
import com.cobosideas.lista.viewmodel.MainViewModel;

import java.security.acl.Owner;
import java.util.List;

public class subMainActivityLista extends MainActivityLista {
    Context context;
    public subMainActivityLista(Context context){
        this.context = context;
    }

}
