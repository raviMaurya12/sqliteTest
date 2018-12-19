package com.example.lenovo.sqlitetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnViewData;
    private EditText mtitle,resName,Type,Price,Supplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtitle = (EditText) findViewById(R.id.title);
        resName=(EditText)findViewById( R.id.reName ) ;
        Type=(EditText)findViewById( R.id.type ) ;
        Price=(EditText)findViewById( R.id.price ) ;
        Supplier=(EditText)findViewById( R.id.supplier ) ;

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnViewData = (Button) findViewById(R.id.btnView);
        mDatabaseHelper = new DatabaseHelper(this,"FOOD.DB",null,1);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle = mtitle.getText().toString();
                String newRes=resName.getText().toString();
                String newType =Type.getText().toString();
                int newPrice = Integer.parseInt( Price.getText().toString() );
                String newSupplier = Supplier.getText().toString();
                if (mtitle.length() != 0) {
                    AddData(newTitle,newRes,newType,newPrice,newSupplier);
                    mtitle.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });

    }

    public void AddData(String title,String restaurant,String Type,Integer Price,String Supplier) {
        boolean insertData = mDatabaseHelper.addData(title,restaurant,Type,Price,Supplier);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
