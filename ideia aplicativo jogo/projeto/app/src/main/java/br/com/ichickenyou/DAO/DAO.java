package br.com.ichickenyou.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;
import br.com.ichickenyou.model.Item;
import android.arch.persistence.room.Query;
import java.util.List;

public class DAO {

    public interface ItemDAO
    {
        @Insert
        public void insert(Item ... resultados);

        @Update
        public void update(Item ... resultados);

        @Delete
        public void delete(Item ... resultado);

        @Query("SELECT * FROM resultados")
        public List<Item> getItems();

        @Query("SELECT * FROM resultados WHERE id_resultado = :id")
        public Item getItemById(Long id);
    }

}
