import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentBank {
    private final Map<UUID, BankAccount> accountMap = new ConcurrentHashMap<>();

    public synchronized BankAccount createAccount(BigDecimal balance) {
        UUID id = UUID.randomUUID();
        BankAccount account = new BankAccount(balance, id);
        accountMap.put(id, account);
        return account;
    }

    public synchronized void transfer(BankAccount from, BankAccount to, BigDecimal amount) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Аккаунты не найдены");
        }
        if (from == to) {
            return;
        }
//        BankAccount first = from;
//        BankAccount second = to;

        BankAccount first;
        BankAccount second;

        int compare = from.getId().compareTo(to.getId());
        if ( compare < 0) {
            first = from;
            second = to;
        } else {
            first = to;
            second = from;
        }

        synchronized (first) {
            synchronized (second) {
                if (from.getBalance().compareTo(amount) < 0) {
                    throw new IllegalArgumentException("Недостоточно средств!");
                }
                from.withdraw(amount);
                to.deposit(amount);
            }
        }

    }

    public synchronized BigDecimal getTotalBalance() {
        BigDecimal totalBalance = BigDecimal.ZERO;
        for (BankAccount account : accountMap.values()) {
            totalBalance =totalBalance.add(account.getBalance());

        }
        return totalBalance;
    }
}
