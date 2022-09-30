package com.example.hydrocalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getCanonicalName();
    EditText editOn;
    EditText editOff;
    EditText editMid;
    Button btnFetchData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.referViews();
    }

    private void referViews() {
        this.editOn = findViewById(R.id.edit_on_peak);
        this.editOff = findViewById(R.id.edit_off_peak);
        this.editMid = findViewById(R.id.edit_mid_peak);
        this.btnFetchData = findViewById(R.id.btn_cal_hydro);
        this.btnFetchData.setOnClickListener(this::onClick);
    }


    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.btn_cal_hydro: {
                    fetchData();
                    }
                }
            }

    }


    public void fetchData(){
        float [] a=new float [4];


        if (this.editOn.getText().toString().isEmpty()) {
            this.editOn.setError("Please fill-up On-Peak usage!");
        }else{
            a[0] = Float.parseFloat(this.editOn.getText().toString());

        }
        if (this.editOff.getText().toString().isEmpty()) {
            this.editOff.setError("Please fill-up Off-Peak usage!!");
        }else{
            a[1] = Float.parseFloat(this.editOff.getText().toString());

        }
        if (this.editMid.getText().toString().isEmpty()) {
            this.editMid.setError("Please fill-up Mid-Peak usage!");
        }else{
            a[2] = Float.parseFloat(this.editMid.getText().toString());

        }
        Log.d(TAG, "fetchData: \n num1 "+ a[0]+ "fetchData: \n num2 "+ a[1]+ "fetchData: \n num3 "+a[2]);

        //On-peak usage
        float on_charge1= (float) (a[0]*0.132);
        String stringDouble1= Float.toString(on_charge1);
        TextView textView = findViewById(R.id.tv_charge1);
        textView.setText("On-peak charges    "+ "$ "+stringDouble1);

        //Off Peak usage
        float off_charge2= (float) (a[1]*0.065);
        String stringDouble2= Float.toString(off_charge2);
        TextView textView2 = findViewById(R.id.tv_charge2);
        textView2.setText("Off-peak charges   "+ "$ "+stringDouble2);

        //Mid-peak usage
        float mid_charge3= (float) (a[2]*0.094);
        String stringDouble3=Float.toString(mid_charge3);
        TextView textView3 = findViewById(R.id.tv_charge3);
        textView3.setText("Mid-peak charges   "+ "$ "+stringDouble3);

        //Total Consumption Charge(on-peak charge+off peak charge+mid peak charge)
        float total_consumption_charge4=Math.round(on_charge1 + off_charge2 + mid_charge3);
        String stringDouble4= Float.toString(total_consumption_charge4);
        TextView textView4 = findViewById(R.id.hyd_charge);
        textView4.setText("$ "+stringDouble4);

        // HST 13% of Total Consumption
        float hst_charge5= (float) (0.13*total_consumption_charge4);
        String stringDouble5= Float.toString(hst_charge5);
        TextView textView5 = findViewById(R.id.tv_hst);
        textView5.setText("HST                           "+ "   $ "+stringDouble5);

        //Rebate 8% of total consumption
        float rebate_charge6= (float) (0.08*total_consumption_charge4);
        String stringDouble6= Float.toString(rebate_charge6);
        TextView textView6 = findViewById(R.id.tv_provincial_rebate);
        textView6.setText("Provincial Rebate  "+"$ "+"-"+stringDouble6);

        //Total regulatory charge
        float total_regulatory_charge7= Math.round(hst_charge5 - rebate_charge6);
        String stringDouble7= Float.toString(total_regulatory_charge7);
        TextView textView7 = findViewById(R.id.tv_regTotal);
        textView7.setText("$ "+stringDouble7);

        // Total Amount = Total Consumption+ Total regulatory charge
        float charge8= Math.round(total_consumption_charge4 + total_regulatory_charge7);
        String stringDouble8= Float.toString(charge8);
        TextView textView8 = findViewById(R.id.tv_totAmount);
        textView8.setText("$ "+stringDouble8);
    }
}
