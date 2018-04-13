package jp.techacademy.son.goodhome;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * Created by taiso on 2018/04/03.
 */

public class CustomerLoginFragment extends Fragment {
    public static final String TAG = "CustomerLoginFragment";

    EditText PostalCodeEditText;
    EditText ageBuildEditText;
    Spinner propertySpinner;
    RadioGroup typesRadioGroup;
    RadioButton detachedRadioButton;
    RadioButton complexRadioButton;
    RadioButton storeRadioButton;
    RadioButton otherRadioButton;
    EditText formEditText;
    EditText typesEditText;
    //CheckBox
    EditText reformEditText;
    Spinner budgedSpinner;
    Spinner ageSpinner;
    RadioGroup radioGroup;
    RadioButton manRadioButton;
    EditText requestEditText;
    Button searchButton;
    Button chatButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_customerlogin,container,false);

        PostalCodeEditText = (EditText)v.findViewById(R.id.PostalCodeEditText);
        ageBuildEditText = (EditText)v.findViewById(R.id.ageBuildEditText);
        typesRadioGroup = (RadioGroup)v.findViewById(R.id.typesRadioGroup);
        detachedRadioButton = (RadioButton)v.findViewById(R.id.detachedRadioButton);
        complexRadioButton = (RadioButton)v.findViewById(R.id.complexRadioButton);
        storeRadioButton = (RadioButton)v.findViewById(R.id.storeRadioButton);
        otherRadioButton = (RadioButton)v.findViewById(R.id.otherRadioButton);
        formEditText = (EditText)v.findViewById(R.id.formEditText);
        propertySpinner = (Spinner)v.findViewById(R.id.propertySpinner);
        typesEditText = (EditText)v.findViewById(R.id.typesEditText);
        //CheckBox
        reformEditText = (EditText)v.findViewById(R.id.reformEditText);
        ageSpinner = (Spinner)v.findViewById(R.id.ageSpinner);
        budgedSpinner = (Spinner)v.findViewById(R.id.budgetSpinner);
        radioGroup = (RadioGroup)v.findViewById(R.id.radioGroup);
        manRadioButton = (RadioButton)v.findViewById(R.id.manRadioButton);

        requestEditText = (EditText)v.findViewById(R.id.requestEditText);
        searchButton = (Button)v.findViewById(R.id.searchButton);
        chatButton = (Button)v.findViewById(R.id.chatButton);


        return v;
    }

    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        view.findViewById(R.id.searchButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                //郵便番号
                String postalCode = PostalCodeEditText.getText().toString();

                //築年数
                String ageBuild = ageBuildEditText.getText().toString();

                //物件の種類
                int t = typesRadioGroup.getCheckedRadioButtonId();
                int d = detachedRadioButton.getId();
                int c = complexRadioButton.getId();
                int s = storeRadioButton.getId();
                int o = otherRadioButton.getId();
                if (t==d){
                    String type = "一戸建て";
                }else if (t==c){
                    String type = "集合住宅";
                }else if (t==s){
                    String type = "店舗";
                }else{
                    String type = "その他";
                }
                //その他の時のeditText
                String otherForm =formEditText.getText().toString();

                //その他の時
                String otherType = typesEditText.getText().toString();

                //リフォームの個所checkbox

                //その他のリフォーム箇所
                String otherPlace = reformEditText.getText().toString();

                //予算任意
                String budget = (String)budgedSpinner.getSelectedItem();

                //年齢任意
                String age = (String)ageSpinner.getSelectedItem();


                //性別任意
                int i = radioGroup.getCheckedRadioButtonId();
                int m =manRadioButton.getId();

                if (m == i){
                    String sex = "男性";
                }else{
                    String sex = "女性";
                }

                //要望
                String request = requestEditText.getText().toString();


                //spinner,radio,check,editがnullのとき落ちないように


            }
        });

        view.findViewById(R.id.chatButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





            }
        });



    }

}
