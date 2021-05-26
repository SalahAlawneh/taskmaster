package com.salah.taskmaster;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDao {
    @Insert(onConflict = REPLACE)
    void insert(Task task);

    @Delete
    void delete(Task task);

    @Delete
    void reset(List<Task> tasks);

    @Query("UPDATE task SET title = :sText WHERE ID = :sID")
    void update(int sID, String sText);

    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Query("DELETE FROM task")
    public void nukeTable();

}
