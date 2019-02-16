package com.selabg11.freshbazaar;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class cart extends AppCompatActivity {
private Button  viewcart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        viewcart=findViewById(R.id.ButtonViewCart);
        viewcart.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                          //  Intent i = new Intent(cart.this,Product.class);


                                           // startActivity(i);

                                        }
                                    }
        );
    }
}
