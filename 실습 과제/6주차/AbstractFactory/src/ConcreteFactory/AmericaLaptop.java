package ConcreteFactory;

import AbstractFactory.IF_Laptop;
import AbstractProduct.IF_Display;
import AbstractProduct.IF_KeyBoard;
import ConcreteProduct.Display;
import ConcreteProduct.KeyBoard;

public class AmericaLaptop implements IF_Laptop {
    private String america = "America";

    @Override
    public IF_Display createDisplay() {
        return new Display(this.america);
    }

    @Override
    public IF_KeyBoard createKeyBoard() {
        return new KeyBoard(this.america);
    }
}
