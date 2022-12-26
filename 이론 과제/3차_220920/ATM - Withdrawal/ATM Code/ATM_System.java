public class ATM_System {
    private int atm_id;
    private int bank_id; // ATM가 현재 존재하는 은행의 고유 번호가 ATM 기기 내에 저장되어 있음

    public ATM_System() {
        this.atm_id = 12; // 예시
        this.bank_id = 34; // 예시
    }

    public void clickWithdrawal() {
    }

    private WithdrawalHandler withdrawalHandler = new WithdrawalHandler();

    /**
     * @param card_number
     */
    public void inputAccount(String card_number) {
        withdrawalHandler.isExistAccount(card_number);
    }

    /**
     * @param amount
     */
    public void inputAmount(long amount) {
        withdrawalHandler.checkAmount(amount);
    }

    /**
     * @param password
     */
    public void inputPassword(int password) {
        withdrawalHandler.checkPassword(password);
    }

    public int getAtm_id() {
        return atm_id;
    }

    public int getBank_id() {
        return bank_id;
    }

    public WithdrawalHandler getWithdrawalHandler() {
        return withdrawalHandler;
    }

    public void setAtm_id(int atm_id) {
        this.atm_id = atm_id;
    }

    public void setBank_id(int bank_id) {
        this.bank_id = bank_id;
    }

    public void setWithdrawalHandler(WithdrawalHandler withdrawalHandler) {
        this.withdrawalHandler = withdrawalHandler;
    }
}