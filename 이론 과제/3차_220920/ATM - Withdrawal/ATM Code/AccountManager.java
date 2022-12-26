
import java.util.*;

public class AccountManager {
    public AccountManager() {
    }

    Account customerAccount = new Account();

    /**
     * @param card_number
     */
    public void checkAccount(String card_number) {
        this.customerAccount = customerAccount.getAccountData(card_number);
    }

    /**
     * @param amount
     */
    public boolean compareBalance(long amount) {
        return customerAccount.getBalance() >= amount ? true : false;
    }

    /**
     * @param password
     */
    public boolean comparePassword(int password) {
        return customerAccount.getAccount_password() == password ? true : false;
    }

    /**
     * @param amount 
     * @param bank_id 
     * @param date 
     * @param time
     */
    public void recordLog(long amount, int bank_id, String date, String time) {
        // TODO implement here
    }

}