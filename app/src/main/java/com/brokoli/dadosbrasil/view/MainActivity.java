package com.brokoli.dadosbrasil.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.brokoli.dadosbrasil.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, DesmatamentoAmazoniaFragment.newInstance()).commit();
    }
}
