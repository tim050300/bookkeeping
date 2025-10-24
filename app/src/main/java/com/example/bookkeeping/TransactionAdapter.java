package com.example.bookkeeping;

// TransactionAdapter.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {
    private List<Transaction> transactions = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaction, parent, false);
        return new TransactionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction currentTransaction = transactions.get(position);
        holder.tvCategory.setText(currentTransaction.getCategory());
        holder.tvDescription.setText(currentTransaction.getDescription());

        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        holder.tvDate.setText(sdf.format(new Date(currentTransaction.getDate())));

        // 设置金额和颜色
        if (currentTransaction.getType().equals("income")) {
            holder.tvAmount.setText(String.format("+ ¥%.2f", currentTransaction.getAmount()));
            holder.tvAmount.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.income_green));
        } else {
            holder.tvAmount.setText(String.format("- ¥%.2f", currentTransaction.getAmount()));
            holder.tvAmount.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.expense_red));
        }
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
        notifyDataSetChanged();
    }

    public Transaction getTransactionAt(int position) {
        return transactions.get(position);
    }

    class TransactionViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCategory, tvDescription, tvDate, tvAmount;

        public TransactionViewHolder(View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tv_category);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvAmount = itemView.findViewById(R.id.tv_amount);

            itemView.setOnLongClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position);
                    return true;
                }
                return false;
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
