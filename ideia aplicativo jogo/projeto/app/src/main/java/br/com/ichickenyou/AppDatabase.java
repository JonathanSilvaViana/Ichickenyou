package br.com.ichickenyou;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import br.com.ichickenyou.model.Item;
import br.com.ichickenyou.DAO.DAO;

@Database(entities = {Item.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase{

    public abstract DAO getItemDAO();

}
