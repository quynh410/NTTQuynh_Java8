package luyentapK23B.Validator;

public class Validator {
    public static boolean validateProductName(String name) {
        return name != null && name.length() >= 30 && name.length() <= 100;
    }

    public static boolean validatePrice(float price) {
        return price > 0;
    }

}
