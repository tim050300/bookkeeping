package com.example.bookkeeping;
// TransactionDao.java
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface TransactionDao {
    @Insert
    void insert(Transaction transaction);

    @Query("SELECT * FROM transactions ORDER BY date DESC")
    LiveData<List<Transaction>> getAllTransactions();

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'income'")
    LiveData<Double> getTotalIncome();

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'expense'")
    LiveData<Double> getTotalExpense();

    @Query("DELETE FROM transactions WHERE id = :id")
    void delete(long id);
}
