package com.example.bookkeeping;

// AddTransactionFragment.java
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class AddTransactionFragment extends Fragment {
    private TransactionViewModel transactionViewModel;
    private EditText etAmount, etDescription;
    private RadioGroup radioGroup;
    private Spinner spinnerCategory;
    private Button btnSave;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_transaction, container, false);

        etAmount = view.findViewById(R.id.et_amount);
        etDescription = view.findViewById(R.id.et_description);
        radioGroup = view.findViewById(R.id.radio_group);
        spinnerCategory = view.findViewById(R.id.spinner_category);
        btnSave = view.findViewById(R.id.btn_save);

        transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);

        // 默认选择支出
        ((RadioButton) radioGroup.findViewById(R.id.radio_expense)).setChecked(true);

        btnSave.setOnClickListener(v -> saveTransaction());

        return view;
    }

    private void saveTransaction() {
        String amountStr = etAmount.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String category = spinnerCategory.getSelectedItem().toString();

        // 验证输入
        if (amountStr.isEmpty()) {
            etAmount.setError("请输入金额");
            etAmount.requestFocus();
            return;
        }

        double amount = Double.parseDouble(amountStr);
        String type = radioGroup.getCheckedRadioButtonId() == R.id.radio_income ? "income" : "expense";
        long date = System.currentTimeMillis();

        // 创建并保存交易记录
        Transaction transaction = new Transaction(amount, type, category, description, date);
        transactionViewModel.insert(transaction);

        // 清空输入
        etAmount.setText("");
        etDescription.setText("");

        // 返回上一个页面
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }
}
