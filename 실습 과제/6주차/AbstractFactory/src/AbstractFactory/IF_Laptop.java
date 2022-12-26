package AbstractFactory;

import AbstractProduct.IF_Display;
import AbstractProduct.IF_KeyBoard;

public interface IF_Laptop {
    IF_Display createDisplay();
    IF_KeyBoard createKeyBoard();
}
