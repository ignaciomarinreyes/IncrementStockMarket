package app.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {

    private String totalEurosCurrent;
    private String numberStocks;
    private String priceDolarOneStockCurrent;
    private String currentIncrement;
    private String targetIncrement;
    private TextView resultIncrement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button calculateIncrement = findViewById(R.id.calculateIncrement);
        Button clear = findViewById(R.id.clear);
        resultIncrement =  findViewById(R.id.resultIncrement);
        calculateIncrement.setOnClickListener(view -> {
            loadData();
            if(validate()) {
                calculate();
            }
        });
        clear.setOnClickListener(view -> {
            clear();
        });
    }

    private void clear() {
        ((EditText) findViewById(R.id.totalEurosCurrent)).setText("");
        ((EditText) findViewById(R.id.numberStocks)).setText("");
        ((EditText) findViewById(R.id.priceDolarOneStockCurrent)).setText("");
        ((EditText) findViewById(R.id.currentIncrement)).setText("");
        ((EditText) findViewById(R.id.targetIncrement)).setText("");
        resultIncrement.setText("");
    }

    private void calculate() {
        Double totalEurosCurrentD = Double.parseDouble(totalEurosCurrent);
        Double numberStocksD = Double.parseDouble(numberStocks);
        Double priceEuroOneStockCurrentD = totalEurosCurrentD / numberStocksD;
        Double priceDolarOneStockCurrentD = Double.parseDouble(priceDolarOneStockCurrent);
        Double exchangeRate = priceDolarOneStockCurrentD / priceEuroOneStockCurrentD;
        Double currentIncrementD = 100 + Double.parseDouble(currentIncrement);
        Double targetIncrementD = 100 + Double.parseDouble(targetIncrement);
        Double priceEuroOneStockTargetD = (targetIncrementD * priceEuroOneStockCurrentD) / currentIncrementD;
        Double priceDolarOneStockTargetD = exchangeRate * priceEuroOneStockTargetD;
        resultIncrement.setText(priceDolarOneStockTargetD.toString());
    }

    private void loadData(){
        totalEurosCurrent = ((EditText) findViewById(R.id.totalEurosCurrent)).getText().toString();
        numberStocks = ((EditText) findViewById(R.id.numberStocks)).getText().toString();
        priceDolarOneStockCurrent = ((EditText) findViewById(R.id.priceDolarOneStockCurrent)).getText().toString();
        currentIncrement = ((EditText) findViewById(R.id.currentIncrement)).getText().toString();
        targetIncrement = ((EditText) findViewById(R.id.targetIncrement)).getText().toString();
    }

    private boolean validate() {
        return !totalEurosCurrent.isEmpty() && !numberStocks.isEmpty() &&
                !priceDolarOneStockCurrent.isEmpty() && !currentIncrement.isEmpty() &&
                !targetIncrement.isEmpty();
    }
}