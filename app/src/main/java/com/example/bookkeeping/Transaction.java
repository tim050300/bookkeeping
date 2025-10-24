package com.example.bookkeeping;

// Transaction.java
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "transactions")
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private double amount; // 金额
    private String type; // "income" 或 "expense"
    private String category; // 分类
    private String description; // 描述
    private long date; // 时间戳

    public Transaction(double amount, String type, String category, String description, long date) {
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    // Getters and Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public long getDate() { return date; }
    public void setDate(long date) { this.date = date; }
}
