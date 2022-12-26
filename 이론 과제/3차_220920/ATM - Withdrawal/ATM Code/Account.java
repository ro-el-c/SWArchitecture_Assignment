public class Account {

    public Account() {
    }

    public Account(int account_id, int account_password) {
        this.account_id = account_id;
        this.account_password = account_password;
        this.balance = 0;
        this.card_number = "1234-2432-4685-3133"; // 예시
        this.usedDate = "2022-10-03"; // 예시
        this.usedTime = "02-30-00"; // 예시
        this.usedBank_id = 11; //예시
    }

    private int account_id;
    private int account_password;
    private int balance;
    private String card_number;
    private String usedDate;
    private String usedTime;
    private int usedBank_id;


    private Account customerAccount = new Account();

    /**
     * @param card_number
     */
    public Account getAccountData(String card_number) {
        try{
            this.customerAccount = // DB에서 Account Data 가져오기;
            return this.customerAccount;
        }
        // Data 찾을 수 없으면 오류 반환
    }


    public int getAccount_id() {
        return account_id;
    }

    public int getAccount_password() {
        return account_password;
    }

    public int getBalance() {
        return balance;
    }

    public String getCard_number() {
        return card_number;
    }

    public String getUsedDate() {
        return usedDate;
    }

    public String getUsedTime() {
        return usedTime;
    }

    public int getUsedBank_id() {
        return usedBank_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public void setAccount_password(int account_password) {
        this.account_password = account_password;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public void setUsedDate(String usedDate) {
        this.usedDate = usedDate;
    }

    public void setUsedTime(String usedTime) {
        this.usedTime = usedTime;
    }

    public void setUsedBank_id(int usedBank_id) {
        this.usedBank_id = usedBank_id;
    }
}