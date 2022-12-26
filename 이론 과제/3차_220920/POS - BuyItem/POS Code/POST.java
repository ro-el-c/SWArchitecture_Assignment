
import java.util.*;

public class POST {

    public POST() {
    }

    private Sale sale = new Sale();


    /**
     * @param barcode
     */
    public void enterItem(int barcode) {
        this.sale.enterItem(barcode);
    }

    public void endSale() {
        this.sale.endSale();
    }

    public void makePayment() { // cashier input: card
        this.sale.makePayment();
    }

}