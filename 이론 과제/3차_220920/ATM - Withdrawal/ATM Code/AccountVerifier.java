
import java.util.*;

public class AccountVerifier {
    public AccountVerifier() {
    }

    private AccountManager accountManager = new AccountManager();

    /**
     * @param card_number
     */
    public void isExistAccount(String card_number) {
        accountManager.checkAccount(card_number);
    }

    /**
     * @param amount
     */
    public boolean compareAmount(long amount) {
        return accountManager.compareBalance(amount);
    }

    public void recordToAccount() {
        // TODO implement here
    }

    /**
     * @param password
     */
    public boolean comparePassword(int password) {
        return accountManager.comparePassword(password);

    }

}