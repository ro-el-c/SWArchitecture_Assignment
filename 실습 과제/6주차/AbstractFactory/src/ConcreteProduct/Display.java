package ConcreteProduct;

import AbstractProduct.IF_Display;

public class Display implements IF_Display {
    private String factory;

    public Display(String factory) {
        this.factory = factory;
    }

    @Override
    public void display() {
        System.out.printf("%s display\n", this.factory);
    }
}
