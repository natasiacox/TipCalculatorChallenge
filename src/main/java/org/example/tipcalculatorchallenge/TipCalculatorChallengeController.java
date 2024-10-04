package org.example.tipcalculatorchallenge;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class TipCalculatorChallengeController {

    // formatters for currency and percentages
    private static final NumberFormat currency =
            NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent =
            NumberFormat.getPercentInstance();

    private BigDecimal tipPercentage = new BigDecimal("0.15"); // 15% default

    private BigDecimal amount;

        // GUI controls defined in FXML and used by the controller's code
        @FXML
        private TextField amountTextField;

        @FXML
        private Label tipPercentageLabel;

        @FXML
        private Slider tipPercentageSlider;

        @FXML
        private TextField tipTextField;

        @FXML
        private TextField totalTextField;

    // called by FXMLLoader to initialize the controller
public void initialize() {

    // 0-4 rounds down, 5-9 rounds up
   currency.setRoundingMode(RoundingMode.HALF_UP);

   amountTextField.textProperty().addListener(new ChangeListener<String>() {

       @Override

       public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {

           try {

               amount = new BigDecimal(amountTextField.getText());

           }

           catch (NumberFormatException ex) {

               amount = null;

           }

           tipCalculation();

       }

   });

    tipPercentageLabel.textProperty().bind(tipPercentageSlider.valueProperty().asString("%.0f%%"));

    tipPercentageSlider.valueProperty().addListener(new ChangeListener<Number>() {

       @Override

       public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {

           tipPercentage = BigDecimal.valueOf(newValue.intValue() / 100.0);

           tipCalculation();

       }
   }

   );

}

    private void tipCalculation() {

        if (amount != null) {

            BigDecimal tip = amount.multiply(tipPercentage);

            BigDecimal total = amount.add(tip);

            tipTextField.setText(currency.format(tipPercentage));

            totalTextField.setText(currency.format(total));
        }

        else {

            tipTextField.setText(currency.format(0));

            totalTextField.setText(currency.format(0));
        }

    }


    }
