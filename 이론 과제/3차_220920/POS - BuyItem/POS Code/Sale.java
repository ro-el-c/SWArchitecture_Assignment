
import java.util.*;

public class Sale {

    public Sale() {
        // this.date = 현재 날짜
        // this.time = 현재 시각
    }

    public String date;
    public String time;
    private long totalPrice;

    private ItemManager itemManager = new ItemManager();
    private Store store = itemManager.getStore();
    private Payment payment = new Payment();

    /**
     * @param barcode
     */
    public void enterItem(int barcode) {
        this.itemManager.searchItemData(barcode);
    }

    public void endSale() {
        this.itemManager.updateStockAndBalance();
    }

    public void makePayment() {
        this.payment.makePayment(this.store.getTotalPrice());
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}