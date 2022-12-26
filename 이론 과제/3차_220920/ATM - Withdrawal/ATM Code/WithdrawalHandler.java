
import java.util.*;

public class WithdrawalHandler {
    private int bank_id;


    public WithdrawalHandler() {
    }

    private AccountVerifier accountVerifier = new AccountVerifier();

    /**
     * @param card_number
     */
    public void isExistAccount(String card_number) {
        accountVerifier.isExistAccount(card_number);
    }

    /**
     * @param amount
     */
    public void checkAmount(long amount) {
        if(accountVerifier.compareAmount(amount))
            System.out.println("출금 가능");
        else
            System.out.println("잔액 부족");
    }

    /**
     * @param password
     */
    public void checkPassword(int password) {
        if(accountVerifier.comparePassword(password))
            System.out.println("출금 가능");
        else
            System.out.println("잔액 부족");
    }

    public void recordToAccount() {
        // TODO implement here
        accountVerifier.recordToAccount();

        /*
        *
         * @param amount
         * @param bank_id
         * @param date
         * @param time
         *
         * 4가지를 받아오는 방법을 생각하지 못해 코드 작성을 못하였습니다.
         * 출금하려고 했던 금액 amount 변수에 저장
         * 출금한 순간의 날짜와 시간 각각 date, time 변수에 저장
        * */
    }

}