package com.cobosideas.lista.room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.cobosideas.lista.room.models.ItemRoom;

import java.util.List;

@Dao
public interface ItemDAO {
    @Insert
    void insert(ItemRoom... itemRooms);
    @Update
    void update(ItemRoom... itemRooms);
    @Delete
    void delete(ItemRoom itemRoom);

    @Query("SELECT * FROM items WHERE id = :id")
    ItemRoom getItemById(Long id);

    @Query("SELECT * FROM items")
    List<ItemRoom> getItems();
}
