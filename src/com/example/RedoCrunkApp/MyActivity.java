package com.example.RedoCrunkApp;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */




    //view variables
    final Button newDrink[] = new Button[100];
    LinearLayout drinkSelectionContainer;
    EditText name;
    EditText vol;
    EditText perc;
    EditText pri;
    AutoCompleteTextView quant;

    //counters
    int numOfDrinks = 0;
    int selectedForm = 0;


    //array to hold all Form objects.
    List<Form> formList = new ArrayList<Form>();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        drinkSelectionContainer = (LinearLayout) findViewById(R.id.drinkSelectContainer);




        name = (EditText) findViewById(R.id.etName);
        vol = (EditText) findViewById(R.id.etVol);
        perc = (EditText) findViewById(R.id.etPerc);
        pri = (EditText) findViewById(R.id.etPrice);
        quant = (AutoCompleteTextView) findViewById(R.id.etQuantity);
//        Button saveDrink = (Button) findViewById(R.id.bSaveForm);

          Button nextDrink = (Button) findViewById(R.id.bNextDrink);
        Button crunkOut = (Button) findViewById(R.id.bCalc);

        ///////////////////////// next drink method

        addNewDrinkButton();



        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                formAdd();

            }

            @Override
            public void afterTextChanged(Editable editable) {

//                mNames.add(selectedForm, name.getText().toString());
//                newDrink[selectedForm].setText(name.getText().toString());

            }
        });
        nextDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewDrinkButton();
            }

        });

    }






    //listener for drink select buttons. when button is clicked, it finds out which one it was,
    //and then displays the correct values.
    public View.OnClickListener drinkSelectListener = new View.OnClickListener() {
        public void onClick(View v) {

            selectedForm = v.getId();
            Form form = formList.get(selectedForm);
            Button v1 = (Button) findViewById(selectedForm);
            v1.setText(" " + selectedForm);
            // Do something depending on the value of the tag
            Log.v("My Logs:", "Select Drink " + selectedForm + " button pressed.");
            Log.v("My Logs:", "Drink " + selectedForm + " name: " + form.getName());


            formDisplay(form);


        }
    };


    private void addNewDrinkButton() {
        formClear();
        formAdd();

        numOfDrinks++;

        selectedForm = numOfDrinks;
        formAdd();

        Log.v("My Logs:", "Next Drink button pressed.");


        newDrink[numOfDrinks] = new Button(getBaseContext());
        newDrink[numOfDrinks].setOnClickListener(drinkSelectListener);
        newDrink[numOfDrinks].setId(numOfDrinks);


        newDrink[numOfDrinks].setText("" + selectedForm);
        newDrink[numOfDrinks].setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        // name.setText("Drink " + (numOfDrinks+1));

        drinkSelectionContainer.addView(newDrink[numOfDrinks]);



        //TODO: save form values, clear edit texts, and create new variables for edittexts.    
    }


    void formAdd(){

        Form tempForm = new Form();

        tempForm.setId(selectedForm);
        tempForm.setName(name.getText().toString());
        tempForm.setVolume(vol.getText().toString());
        tempForm.setPercentage(perc.getText().toString());
        tempForm.setPrice(pri.getText().toString());
        tempForm.setQuantity(quant.getText().toString());

        formList.add(selectedForm, tempForm);

    }

    void formDisplay(Form form){

        name.setText(form.getName());
        vol.setText(form.getVolume());
        perc.setText(form.getPercentage());
        pri.setText(form.getPrice());
        quant.setText(form.getQuantity());


    }


    void formClear(){

        name.setText("");
        vol.setText("");
        perc.setText("");
        pri.setText("");
        quant.setText("");


    }
}
