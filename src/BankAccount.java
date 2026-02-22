import java.math.BigDecimal;
import java.util.UUID;

public class BankAccount {
    private BigDecimal balance;
    private final UUID id;

    public BankAccount(BigDecimal balance, UUID id) {
        this.balance = balance;
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public synchronized void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Сумма должна быть больше 0!");
        }
        balance = balance.add(amount);

    }

    public synchronized void withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0 || amount.compareTo(balance) > 0) {
            throw new IllegalArgumentException("Сумма вывода не должна превышать баланс" +
                    " и должна быть положительной");
        }
        balance = balance.subtract(amount);
    }

    public synchronized BigDecimal getBalance() {
        return balance;
    }
}
