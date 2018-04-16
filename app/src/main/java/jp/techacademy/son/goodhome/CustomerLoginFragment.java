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
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;
    CheckBox checkBox5;
    CheckBox checkBox6;
    CheckBox checkBox7;
    CheckBox checkBox8;
    CheckBox checkBox9;
    CheckBox checkBox10;
    CheckBox checkBox11;
    CheckBox checkBox12;
    CheckBox checkBox13;
    CheckBox checkBox14;
    CheckBox checkBox15;
    CheckBox checkBox16;
    CheckBox checkBox17;


    EditText otherReformEditText;
    Spinner budgedSpinner;
    Spinner ageSpinner;
    RadioGroup radioGroup;
    RadioButton manRadioButton;
    RadioButton womanRadioButton;
    EditText requestEditText;
    Button accountButton;
    String form = "";
    String key;
    DatabaseReference databaseReference;
    DatabaseReference customerPathRef;
    String sex;
    FirebaseUser user;
    int proId;
    int ageId;
    String check1="";
    String check2="";
    String check3="";
    String check4="";
    String check5="";
    String check6="";
    String check7="";
    String check8="";
    String check9="";
    String check10="";
    String check11="";
    String check12="";
    String check13="";
    String check14="";
    String check15="";
    String check16="";
    String check17="";


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
        checkBox1 = (CheckBox)v.findViewById(R.id.reformCheckBox1);
        checkBox2 = (CheckBox)v.findViewById(R.id.reformCheckBox2);
        checkBox3 = (CheckBox)v.findViewById(R.id.reformCheckBox3);
        checkBox4 = (CheckBox)v.findViewById(R.id.reformCheckBox4);
        checkBox5 = (CheckBox)v.findViewById(R.id.reformCheckBox5);
        checkBox6 = (CheckBox)v.findViewById(R.id.reformCheckBox6);
        checkBox7 = (CheckBox)v.findViewById(R.id.reformCheckBox7);
        checkBox8 = (CheckBox)v.findViewById(R.id.reformCheckBox8);
        checkBox9 = (CheckBox)v.findViewById(R.id.reformCheckBox9);
        checkBox10 = (CheckBox)v.findViewById(R.id.reformCheckBox10);
        checkBox11 = (CheckBox)v.findViewById(R.id.reformCheckBox11);
        checkBox12 = (CheckBox)v.findViewById(R.id.reformCheckBox12);
        checkBox13 = (CheckBox)v.findViewById(R.id.reformCheckBox13);
        checkBox14 = (CheckBox)v.findViewById(R.id.reformCheckBox14);
        checkBox15 = (CheckBox)v.findViewById(R.id.reformCheckBox15);
        checkBox16 = (CheckBox)v.findViewById(R.id.reformCheckBox16);
        checkBox17 = (CheckBox)v.findViewById(R.id.reformCheckBox17);

        otherReformEditText = (EditText)v.findViewById(R.id.otherReformEditText);
        ageSpinner = (Spinner)v.findViewById(R.id.ageSpinner);
        budgedSpinner = (Spinner)v.findViewById(R.id.budgetSpinner);
        radioGroup = (RadioGroup)v.findViewById(R.id.radioGroup);
        manRadioButton = (RadioButton)v.findViewById(R.id.manRadioButton);
        womanRadioButton =(RadioButton)v.findViewById(R.id.womanRadioButton);


        requestEditText = (EditText)v.findViewById(R.id.requestEditText);
        accountButton = (Button)v.findViewById(R.id.accountButton);


        return v;
    }

    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        customerPathRef = databaseReference.child(Const.CustomerPath);
        user = FirebaseAuth.getInstance().getCurrentUser();


        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());

        String postalCode = sp.getString(Const.PostalCodeKEY, "");

        String form = sp.getString(Const.FormKEY, "");
        String otherForm = sp.getString(Const.OtherFormKEY, "");
        String pro = sp.getString(Const.ProKEY, "");
        String otherPro = sp.getString(Const.OtherProKEY, "");
        String place = sp.getString(Const.PlaceKEY, "");
        String otherPlace = sp.getString(Const.OtherPlaceKEY,"");
        String budget = sp.getString(Const.BudgetKEY, "");
        String age = sp.getString(Const.AgeKEY, "");
        String sex = sp.getString(Const.SexKEY, "");
        String request = sp.getString(Const.RequestKEY, "");







        //郵便番号
        PostalCodeEditText.setText(postalCode);
        //築年数
        ageBuildEditText.setText(age);
        //ラジオグループこんな感じ
        if (form.equals("一戸建て")){
            detachedRadioButton.setChecked(true);
        }else if (form.equals("集合住宅")){
            complexRadioButton.setChecked(true);
        }else if (form.equals("店舗")) {
            storeRadioButton.setChecked(true);
        }else if (form.equals("その他")){
            otherRadioButton.setChecked(true);
        }


        //その他の物件の種類
        formEditText.setText(form);
        //構造スピナー
        if (pro.equals("木造")){
            proId = 0;
        }else if (pro.equals("軽量鉄骨造(プレハブ)")){
            proId = 1;
        }else if (pro.equals("重量鉄骨造")){
            proId = 2;
        }else if (pro.equals("鉄筋コンクリート造")){
            proId = 3;
        }else if (pro.equals("ログハウス")){
            proId = 4;
        }else if (pro.equals("その他")){
            proId = 5;
        }
        propertySpinner.setSelection(proId);

        //その他の物件の構造
        typesEditText.setText(form);



        //チェックボックスリフォーム箇所
        if(place.indexOf("風呂")>0){
            checkBox1.setChecked(true);
        }
        if(place.indexOf("浴室")>0){
            checkBox2.setChecked(true);
        }
        if(place.indexOf("トイレ")>0){
            checkBox3.setChecked(true);
        }
        if(place.indexOf("キッチン")>0){
            checkBox4.setChecked(true);
        }
        if(place.indexOf("洗面所")>0){
            checkBox5.setChecked(true);
        }
        if(place.indexOf("外壁")>0){
            checkBox6.setChecked(true);
        }
        if(place.indexOf("屋根")>0){
            checkBox7.setChecked(true);
        }
        if(place.indexOf("庭")>0){
            checkBox8.setChecked(true);
        }
        if(place.indexOf("ベランダ・バルコニー")>0){
            checkBox9.setChecked(true);
        }
        if(place.indexOf("リビング")>0){
            checkBox10.setChecked(true);
        }
        if(place.indexOf("ダイニング")>0){
            checkBox11.setChecked(true);
        }
        if(place.indexOf("洋室・和室")>0){
            checkBox12.setChecked(true);
        }
        if(place.indexOf("玄関")>0){
            checkBox13.setChecked(true);
        }
        if(place.indexOf("廊下")>0){
            checkBox14.setChecked(true);
        }
        if(place.indexOf("階段")>0){
            checkBox15.setChecked(true);
        }
        if(place.indexOf("リノベーション")>0){
            checkBox16.setChecked(true);
        }
        if(place.indexOf("その他")>0){
            checkBox17.setChecked(true);
        }



        //その他のリフォーム箇所
        otherReformEditText.setText(otherPlace);




        //予算スピナー
        if (budget.equals("～20万")){
            budgedSpinner.setSelection(0);
        }else if (budget.equals("21万～40万")){
            budgedSpinner.setSelection(1);
        }else if (budget.equals("41万～60万")){
            budgedSpinner.setSelection(2);
        }else if (budget.equals("61万～80万")){
            budgedSpinner.setSelection(3);
        }else if (budget.equals("81万～100万")){
            budgedSpinner.setSelection(4);
        }else if (budget.equals("100万～150万")){
            budgedSpinner.setSelection(5);
        }else if (budget.equals("150万～200万")){
            budgedSpinner.setSelection(6);
        }else if (budget.equals("200万～")){
            budgedSpinner.setSelection(7);
        }

        //budgedSpinner.setSelection(budgetId);



        //年齢スピナー

        if (age.equals("10代")){
            ageId= 0;
        }else if (age.equals("20代")){
            ageId= 1;
        }else if (age.equals("30代")){
            ageId= 2;
        }else if (age.equals("40代")){
            ageId= 3;
        }else if (age.equals("50代")){
            ageId= 4;
        }else if (age.equals("60代")){
            ageId= 5;
        }else if (age.equals("70代～")){
            ageId= 6;
        }

        ageSpinner.setSelection(ageId);


        //性別ラジオボタン
        if (sex.equals("男性")){
            manRadioButton.setChecked(true);
        }else if (sex.equals("女性")){
            womanRadioButton.setChecked(true);
        }

        //要求
        requestEditText.setText(request);





        view.findViewById(R.id.accountButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(v);

                //bundleでflagみたいなのあげてそれならpreferenceから値を取得してアカウントを表示
                CustomerAccountFragment fragmentCustomerAccount = new CustomerAccountFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.container,fragmentCustomerAccount,CustomerAccountFragment.TAG)
                        .commit();

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
            form = "一戸建て";
        }else if (t==c){
            form = "集合住宅";
        }else if (t==s){
            form = "店舗";
        }else{
            form = "その他";
        }
        //その他の時のeditText
        String otherForm =formEditText.getText().toString();

        String pro = (String)propertySpinner.getSelectedItem();

        //その他の時
        String otherPro = typesEditText.getText().toString();

        //リフォームの個所
        if (checkBox1.isChecked()){
            check1 ="風呂　";
        }
        if (checkBox2.isChecked()){
            check2 ="浴室　";
        }
        if (checkBox3.isChecked()){
            check3 ="トイレ　";
        }
        if (checkBox4.isChecked()){
            check4 ="キッチン　";
        }
        if (checkBox5.isChecked()){
            check5 ="洗面所　";
        }
        if (checkBox6.isChecked()){
            check6 ="外壁　";
        }
        if (checkBox7.isChecked()){
            check7 ="屋根　";
        }
        if (checkBox8.isChecked()){
            check8 ="庭　";
        }
        if (checkBox9.isChecked()){
            check9 ="ベランダ・バルコニー　";
        }
        if (checkBox10.isChecked()){
            check10 ="リビング　";
        }
        if (checkBox11.isChecked()){
            check11 ="ダイニング　";
        }
        if (checkBox12.isChecked()){
            check12 ="洋室・和室　";
        }
        if (checkBox13.isChecked()){
            check13 ="玄関　";
        }
        if (checkBox14.isChecked()){
            check14 ="廊下　";
        }
        if (checkBox15.isChecked()){
            check15 ="階段　";
        }
        if (checkBox16.isChecked()){
            check16 ="リノベーション　";
        }
        if (checkBox17.isChecked()){
            check17 ="その他　";
        }

        String place =check1+check2+check3+check4+check5+check6+check7+check8+check9+check10+check11+check12+check13+check14+check15+check16+check17;




        //その他のリフォーム箇所
        String otherPlace = otherReformEditText.getText().toString();

        //予算任意
        String budget = (String)budgedSpinner.getSelectedItem();

        //年齢任意
        String age = (String)ageSpinner.getSelectedItem();


        //性別任意
        int i = radioGroup.getCheckedRadioButtonId();
        int m =manRadioButton.getId();

        if (m == i){
            sex = "男性";
            //2131296487
        }else{
            sex = "女性";
            //2131296376
        }

        //要望
        String request = requestEditText.getText().toString();
        String estimate ="0";



        //任意の時はその他かつ入力済み　または　その他なし　のときokみたいな感じで作り直し
//        if (postalCode.length()>1){
//            if (ageBuild.length()>0){
//                if (type.length()>1){
//                    if (pro.length()>1){
//                        if (place.length()>0){
//                            if (place.indexOf("その他")<0){//その他選んだ時の処理
//
//                                     if (request.length()>0){

                                         String mUid = user.getUid();
                                         SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
                                         String flag = sp.getString(Const.FlagKEY, "");
                                         String name = sp.getString(Const.NameKEY,"");

                                         Map<String, String> data = new HashMap<String, String>();
                                         data.put("mUid",mUid);
                                         data.put("UserName",name);
                                         data.put("postalCode" ,postalCode);
                                         data.put("ageBuild" ,ageBuild);
                                         data.put("form" , form);
                                         data.put("otherForm" ,otherForm);
                                         data.put("pro" ,pro);
                                         data.put("otherPro" ,otherPro);
                                         data.put("place" ,place);
                                         data.put("otherPlace" ,otherPlace);
                                         data.put("budget" ,budget);
                                         data.put("age" ,age);
                                         data.put("sex" ,sex);
                                         data.put("estimate" ,estimate);
                                         data.put("request" ,request);
                                         data.put("flag" ,flag);

//                              Map<String, Object> childUpdates = new HashMap<>();
//                              childUpdates.put(mUid, data);
//                              customerPathRef.updateChildren(childUpdates);
                                         customerPathRef.child(mUid).setValue(data);
                                         //MainActivityに目印送ってその時MessageFragmentにする
                                         // プリファレンスにも保存
                                         savePersonalData(mUid,name,postalCode,ageBuild,form,otherForm,pro,otherPro,place,otherPlace,budget,age,sex,estimate,request,flag);







//                                     }else {
//                                         Snackbar.make(v, "要望を入力してください", Snackbar.LENGTH_LONG).show();
//                                     }
//                            }else {
//                                if (otherPlace.length()<1){
//                                    Snackbar.make(v, "リフォーム箇所を入力してください", Snackbar.LENGTH_LONG).show();
//                                }
//                            }
//                        }else {
//                            Snackbar.make(v, "リフォーム箇所を選択してください", Snackbar.LENGTH_LONG).show();
//
//                        }
//                    }else {
//                        Snackbar.make(v, "物件の構造を選択してください", Snackbar.LENGTH_LONG).show();
//                    }
//                }else {
//                    if (otherForm.length()<1){
//                        Snackbar.make(v, "物件の種類を選択してください", Snackbar.LENGTH_LONG).show();
//                    }
//                }
//            }else {
//                Snackbar.make(v, "築年数を入力してください", Snackbar.LENGTH_LONG).show();
//            }
//        }else {
//            Snackbar.make(v, "郵便番号を入力してください", Snackbar.LENGTH_LONG).show();
//        }



    }
    private void savePersonalData(String mUid,String name,String postalCode,String ageBuild,String form,String otherForm,String pro,String otherPro,String place,String otherPlace,String budget,String age,String sex,String estimate,String request,String flag) {
        // Preferenceに保存する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Const.mUidKEY, mUid);
        editor.putString(Const.NameKEY, name);
        editor.putString(Const.PostalCodeKEY, postalCode);
        editor.putString(Const.AgeBuildKEY, ageBuild);
        editor.putString(Const.FormKEY, form);
        editor.putString(Const.OtherFormKEY, otherForm);
        editor.putString(Const.ProKEY, pro);
        editor.putString(Const.OtherProKEY, otherPro);
        editor.putString(Const.PlaceKEY, place);
        editor.putString(Const.OtherPlaceKEY, otherPlace);
        editor.putString(Const.BudgetKEY, budget);
        editor.putString(Const.AgeKEY, age);
        editor.putString(Const.SexKEY, sex);
        editor.putString(Const.EstimateKEY, estimate);
        editor.putString(Const.RequestKEY, request);
        editor.putString(Const.FlagKEY, flag);


        editor.commit();


    }

}
