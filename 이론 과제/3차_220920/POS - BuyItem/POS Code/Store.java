
import java.util.*;

public class Store {

    public Store() {
        this.itemList = new ArrayList<>();
        this.totalPrice = 0;
    }

    private List<ItemData> itemList;
    public long totalPrice;

    public void addItem(ItemData itemData) {
        this.itemList.add(itemData);
        this.totalPrice += itemData.getPrice();
    }

    public List<ItemData> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemData> itemList) {
        this.itemList = itemList;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }
}