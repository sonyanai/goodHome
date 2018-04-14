package jp.techacademy.son.goodhome;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


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
    String type = "";
    String key;
    DatabaseReference databaseReference;
    DatabaseReference customerPathRef;
    String sex;
    FirebaseUser user;


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

        databaseReference = FirebaseDatabase.getInstance().getReference();
        customerPathRef = databaseReference.child(Const.CustomerPath);
        user = FirebaseAuth.getInstance().getCurrentUser();


        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());

        String postalCode = sp.getString(Const.PostalCodeKEY, "");
        String age = sp.getString(Const.AgeKEY, "");
        String form = sp.getString(Const.OtherFormKEY, "");
        String type = sp.getString(Const.OtherTypeKEY, "");
        String reform = sp.getString(Const.OtherPlaceKEY, "");
        String request = sp.getString(Const.RequestKEY, "");
        //String  = sp.getString(Const., "");



        //郵便番号
        PostalCodeEditText.setText(postalCode);
        //築年数
        ageBuildEditText.setText(age);
        //ラジオグループ
        //物件の種類
        formEditText.setText(form);
        //スピナー
        //物件の構造
        typesEditText.setText(type);
        //チェックボックス
        //リフォーム箇所
        reformEditText.setText(reform);
        //予算スピナー
        //性別ラジオボタン
        //要求
        requestEditText.setText(request);


        取得して表示プリファレンスでも保存businessloginでも



        view.findViewById(R.id.chatButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(v);

                //bundleでflagみたいなのあげてそれならpreferenceから値を取得してリストを表示
                //

            }
        });



    }
    public void save(View v){
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
            type = "一戸建て";
        }else if (t==c){
            type = "集合住宅";
        }else if (t==s){
            type = "店舗";
        }else{
            type = "その他";
        }
        //その他の時のeditText
        String otherForm =formEditText.getText().toString();

        String pro = (String)propertySpinner.getSelectedItem();

        //その他の時
        String otherType = typesEditText.getText().toString();

        //リフォームの個所checkbox増やすときここだよ


        String place = "0";















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
            sex = "男性";
        }else{
            sex = "女性";
        }

        //要望
        String request = requestEditText.getText().toString();
        String estimate ="0";


        //spinner,radio,check,editがnullのとき落ちないように

        //必須が入ってるか
        if (postalCode.length()>1){
            if (ageBuild.length()>0){
                if (type.length()>1){
                    if (pro.length()>1){
                        //if (リフォームの場所があるかないか){
                        //if (その他選んだ時の処理){
                        //if (otherPlace.length()<1){
                            //Snackbar.make(v, "物件の種類を選択してください", Snackbar.LENGTH_LONG).show();
                        //}

                        //}else {
                          if (request.length()>0){

                              String mUid = user.getUid();
                              SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
                              String flag = sp.getString(Const.FlagKEY, "");
                              String UserName = sp.getString(Const.NameKEY,"");


                              Map<String, String> data = new HashMap<String, String>();
                              data.put("mUid",mUid);
                              data.put("UserName",UserName);
                              data.put("postalCode" ,postalCode);
                              data.put("ageBuild" ,ageBuild);
                              data.put("type" ,type);
                              data.put("otherForm" ,otherForm);
                              data.put("pro" ,pro);
                              data.put("otherType" ,otherType);
                              data.put("place" ,place);
                              data.put("otherPlace" ,otherPlace);
                              data.put("budget" ,budget);
                              data.put("age" ,age);
                              data.put("sex" ,sex);
                              data.put("estimate" ,estimate);
                              data.put("flag" ,flag);
//                              Map<String, Object> childUpdates = new HashMap<>();
//                              childUpdates.put(mUid, data);
//                              customerPathRef.updateChildren(childUpdates);
                              customerPathRef.child(mUid).setValue(data);

                              //MainActivityに目印送ってその時MessageFragmentにする










                              //LoginActivity activity = (LoginActivity)getActivity();
                              //activity.intentActivity();
                          }else {
                              Snackbar.make(v, "要望を入力してください", Snackbar.LENGTH_LONG).show();
                          }
                        //}

                        //}
                    }else {
                        if (otherType.length()<1){
                            Snackbar.make(v, "物件の構造を選択してください", Snackbar.LENGTH_LONG).show();
                        }
                    }
                }else {
                    if (otherForm.length()<1){
                        Snackbar.make(v, "物件の種類を選択してください", Snackbar.LENGTH_LONG).show();
                    }
                }
            }else {
                Snackbar.make(v, "築年数を入力してください", Snackbar.LENGTH_LONG).show();
            }
        }else {
            Snackbar.make(v, "郵便番号を入力してください", Snackbar.LENGTH_LONG).show();
        }



    }

}
