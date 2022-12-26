package ConcreteProduct;

import AbstractProduct.IF_KeyBoard;

public class KeyBoard implements IF_KeyBoard {
    private String factory;

    public KeyBoard(String factory) {
        this.factory = factory;
    }

    @Override
    public void keyboard() {
        System.out.printf("%s keyboard\n", this.factory);
    }
}
