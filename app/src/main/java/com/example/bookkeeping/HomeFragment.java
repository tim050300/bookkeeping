package com.example.bookkeeping;

// HomeFragment.java
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class HomeFragment extends Fragment {
    private TransactionViewModel transactionViewModel;
    private TextView tvIncome, tvExpense, tvBalance;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tvIncome = view.findViewById(R.id.tv_income);
        tvExpense = view.findViewById(R.id.tv_expense);
        tvBalance = view.findViewById(R.id.tv_balance);

        transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);

        // 观察收入变化
        transactionViewModel.getTotalIncome().observe(getViewLifecycleOwner(), income -> {
            double incomeValue = income != null ? income : 0;
            tvIncome.setText(String.format("+ ¥%.2f", incomeValue));

            // 更新余额
            updateBalance();
        });

        // 观察支出变化
        transactionViewModel.getTotalExpense().observe(getViewLifecycleOwner(), expense -> {
            double expenseValue = expense != null ? expense : 0;
            tvExpense.setText(String.format("- ¥%.2f", expenseValue));

            // 更新余额
            updateBalance();
        });

        return view;
    }

    // 计算并更新余额
    private void updateBalance() {
        Double income = transactionViewModel.getTotalIncome().getValue();
        Double expense = transactionViewModel.getTotalExpense().getValue();

        double incomeValue = income != null ? income : 0;
        double expenseValue = expense != null ? expense : 0;
        double balance = incomeValue - expenseValue;

        tvBalance.setText(String.format("¥%.2f", balance));
    }
}
