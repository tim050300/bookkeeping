package com.example.bookkeeping;

// TransactionViewModel.java
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class TransactionViewModel extends AndroidViewModel {
    private TransactionRepository repository;
    private LiveData<List<Transaction>> allTransactions;
    private LiveData<Double> totalIncome;
    private LiveData<Double> totalExpense;

    public TransactionViewModel(@NonNull Application application) {
        super(application);
        repository = new TransactionRepository(application);
        allTransactions = repository.getAllTransactions();
        totalIncome = repository.getTotalIncome();
        totalExpense = repository.getTotalExpense();
    }

    public LiveData<List<Transaction>> getAllTransactions() { return allTransactions; }
    public LiveData<Double> getTotalIncome() { return totalIncome; }
    public LiveData<Double> getTotalExpense() { return totalExpense; }
    public void insert(Transaction transaction) { repository.insert(transaction); }
    public void delete(long id) { repository.delete(id); }
}
