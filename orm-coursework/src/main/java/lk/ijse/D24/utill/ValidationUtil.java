package lk.ijse.D24.utill;

import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {
    public static final Pattern namePattern = Pattern.compile("^[a-zA-Z '.-]{3,}$");
    public static final Pattern contactPattern = Pattern.compile("^(071|072|073|074|070|075|076|077|078|079)[-]?[0-9]{7}$");
    public static final Pattern pricePattern = Pattern.compile("^()[0-9]{2,7}$");
    public static final Pattern studentIdPattern = Pattern.compile("^(S)[0-9]{4}$");
    public static final Pattern addressPattern = Pattern.compile("^[A-z0-9/ ]{3,30}$");
    public static final Pattern passwordPattern = Pattern.compile("^[A-z ]{3,40}$");

    public static void setFocus(TextField textField, Pattern pattern) {
        textField.setOnKeyReleased(keyEvent -> {
            Matcher matcher = pattern.matcher(textField.getText());

            if (textField.getText().isEmpty() || textField.getText().isBlank() || !matcher.matches() ){
                textField.setStyle(String.valueOf(Paint.valueOf("red")));
                textField.setStyle(String.valueOf(Paint.valueOf("red")));
            }else {
                textField.setStyle(String.valueOf(Paint.valueOf("blue")));
                textField.setStyle(String.valueOf(Paint.valueOf("blue")));
            }

        });
    }

}