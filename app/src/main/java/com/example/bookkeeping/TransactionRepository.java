package com.example.bookkeeping;

// TransactionRepository.java
import android.content.Context;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransactionRepository {
    private TransactionDao transactionDao;
    private LiveData<List<Transaction>> allTransactions;
    private LiveData<Double> totalIncome;
    private LiveData<Double> totalExpense;
    private ExecutorService executorService;

    public TransactionRepository(Context context) {
        AppDatabase db = AppDatabase.getDatabase(context);
        transactionDao = db.transactionDao();
        allTransactions = transactionDao.getAllTransactions();
        totalIncome = transactionDao.getTotalIncome();
        totalExpense = transactionDao.getTotalExpense();
        executorService = Executors.newSingleThreadExecutor();
    }

    // 获取所有交易记录
    public LiveData<List<Transaction>> getAllTransactions() {
        return allTransactions;
    }

    // 获取总收入
    public LiveData<Double> getTotalIncome() {
        return totalIncome;
    }

    // 获取总支出
    public LiveData<Double> getTotalExpense() {
        return totalExpense;
    }

    // 插入交易记录
    public void insert(Transaction transaction) {
        executorService.execute(() -> transactionDao.insert(transaction));
    }

    // 删除交易记录
    public void delete(long id) {
        executorService.execute(() -> transactionDao.delete(id));
    }
}
