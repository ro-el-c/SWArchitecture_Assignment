
import java.util.*;

public class ItemManager {

    public ItemManager() {
    }

    private ItemData itemData;


    private ItemSpecification itemSpecification = new ItemSpecification();
    private Store store = new Store();
    /**
     * @param barcode
     */
    public void searchItemData(int barcode) {
        this.itemData = this.itemSpecification.searchWithBarcode(barcode);
        this.addItem(this.itemData);
    }

    /**
     * @param itemData
     */
    public void addItem(ItemData itemData) {
        this.store.addItem(itemData);
    }

    public void updateStockAndBalance() {
        // Market DB 에서 결제 내역에 대하여 재고 및 시세 수정
    }

    public ItemSpecification getItemSpecification() {
        return itemSpecification;
    }

    public void setItemSpecification(ItemSpecification itemSpecification) {
        this.itemSpecification = itemSpecification;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public ItemData getItemData() {
        return itemData;
    }

    public void setItemData(ItemData itemData) {
        this.itemData = itemData;
    }
}