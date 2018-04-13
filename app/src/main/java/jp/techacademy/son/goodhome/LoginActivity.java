package jp.techacademy.son.goodhome;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by taiso on 2018/04/03.
 */

public class LoginActivity extends AppCompatActivity {

    EditText mEmailEditText;
    EditText mPasswordEditText;
    EditText UserNameEditText;

    String UserName;
    String postalCode;
    String ageBuild;
    String type;
    String otherForm;
    String pro;
    String otherType;
    String place;
    String otherPlace;
    String budget;
    String age;
    String sex;
    String estimate;
    String flag;
    RadioGroup cbRadioGroup;
    RadioButton customerRadioButton;
    RadioButton businessRadioButton;



    FirebaseAuth mAuth;
    OnCompleteListener<AuthResult> mCreateAccountListener;
    OnCompleteListener<AuthResult> mLoginListener;
    DatabaseReference mDataBaseReference;
    DatabaseReference userRef;

    // アカウント作成時にフラグを立て、ログイン処理後に名前をFirebaseに保存する
    boolean mIsCreateAccount = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);






        mDataBaseReference = FirebaseDatabase.getInstance().getReference();

        // FirebaseAuthのオブジェクトを取得する
        mAuth = FirebaseAuth.getInstance();

        // アカウント作成処理のリスナー
        mCreateAccountListener = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {


                if (task.isSuccessful()) {
                    // 成功した場合
                    // ログインを行う
                    FirebaseUser user = mAuth.getCurrentUser();


                    if (mIsCreateAccount) {


                        String mUid = user.getUid();

                        UserName = UserNameEditText.getText().toString();
                        Map<String,String> data = new HashMap<String,String>();

                        postalCode ="0";
                        ageBuild ="0";
                        type ="0";
                        otherForm ="0";
                        pro ="0";
                        otherType ="0";
                        place ="0";
                        otherPlace ="0";
                        budget ="0";
                        age ="0";
                        sex ="0";
                        estimate ="0";





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

                        Map<String,Object> childUpdates = new HashMap<>();
                        childUpdates.put(mUid,data);





                        userRef.updateChildren(childUpdates);


                        // 表示名をPrefarenceに保存する
                        savePersonalData(mUid,UserName,postalCode,ageBuild,type,otherForm,pro,otherType,place,otherPlace,budget,age,sex,estimate,flag);


                        // アカウント作成の時は表示名をFirebaseに保存する
                        String email = mEmailEditText.getText().toString();
                        String password = mPasswordEditText.getText().toString();
                        login(email, password);



                    } else {
                        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                Map data = (Map) snapshot.getValue();
                                saveName((String)data.get("name"));
                            }
                            @Override
                            public void onCancelled(DatabaseError firebaseError) {
                            }
                        });
                    }

                } else {

                    // 失敗した場合
                    // エラーを表示する
                    View view = findViewById(android.R.id.content);
                    Snackbar.make(view, "アカウント作成に失敗しました", Snackbar.LENGTH_LONG).show();

                }
            }
        };

        // ログイン処理のリスナー
        mLoginListener = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    View view = findViewById(android.R.id.content);
                    Snackbar.make(view, "ログインに成功しました", Snackbar.LENGTH_LONG).show();


                    if (flag!=null){
                        Bundle bundle = new Bundle();

                        bundle.putString("data",flag);

                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }





                } else {
                    // 失敗した場合
                    // エラーを表示する
                    View view = findViewById(android.R.id.content);
                    Snackbar.make(view, "ログインに失敗しました", Snackbar.LENGTH_LONG).show();
                }
            }
        };

        // UIの準備
        setTitle("ログイン");

        mEmailEditText = (EditText) findViewById(R.id.emailText);
        mPasswordEditText = (EditText) findViewById(R.id.passwordText);
        UserNameEditText = (EditText) findViewById(R.id.UserNameEditText);

        cbRadioGroup = (RadioGroup)findViewById(R.id.cbRadioGroup);
        customerRadioButton = (RadioButton)findViewById(R.id.customerRadioButton);
        businessRadioButton = (RadioButton)findViewById(R.id.businessRadioButton);

        Button createButton = (Button) findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // キーボードが出てたら閉じる
                InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                int i = cbRadioGroup.getCheckedRadioButtonId();
                int c = customerRadioButton.getId();
                int b = businessRadioButton.getId();



                if (i == c){
                    userRef = mDataBaseReference.child(Const.CustomerPath);
                    flag = "customer";
                    String email = mEmailEditText.getText().toString();
                    String password = mPasswordEditText.getText().toString();
                    UserName = UserNameEditText.getText().toString();

                    if (email.length() != 0 && password.length() >= 6 && UserName.length() > 0) {
                        // ログイン時に表示名を保存するようにフラグを立てる
                        mIsCreateAccount = true;

                        createAccount(email, password);
                    } else {
                        // エラーを表示する
                        Snackbar.make(v, "正しく入力してください", Snackbar.LENGTH_LONG).show();
                    }

                }else if (i==b){
                    userRef = mDataBaseReference.child(Const.BusinessPath);
                    flag = "business";
                    String email = mEmailEditText.getText().toString();
                    String password = mPasswordEditText.getText().toString();
                    UserName = UserNameEditText.getText().toString();

                    if (email.length() != 0 && password.length() >= 6 && UserName.length() > 0) {
                        // ログイン時に表示名を保存するようにフラグを立てる
                        mIsCreateAccount = true;

                        createAccount(email, password);
                    } else {
                        // エラーを表示する
                        Snackbar.make(v, "正しく入力してください", Snackbar.LENGTH_LONG).show();
                    }

                }else{
                    Snackbar.make(v, "個人か企業を選択してください", Snackbar.LENGTH_LONG).show();

                }





            }
        });

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // キーボードが出てたら閉じる
                InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                int i = cbRadioGroup.getCheckedRadioButtonId();

                if (i > 0) {

                    String email = mEmailEditText.getText().toString();
                    String password = mPasswordEditText.getText().toString();


                    if (email.length() != 0 && password.length() >= 6) {
                        // フラグを落としておく
                        mIsCreateAccount = false;

                        login(email, password);
                    } else {
                        // エラーを表示する
                        Snackbar.make(v, "正しく入力してください", Snackbar.LENGTH_LONG).show();
                    }
                }else{
                    Snackbar.make(v, "個人か企業かを選択してください", Snackbar.LENGTH_LONG).show();
                }


            }
        });
    }

    private void createAccount(String email, String password) {

        // アカウントを作成する
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(mCreateAccountListener);
    }

    private void login(String email, String password) {

        // ログインする
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(mLoginListener);



    }

    private void saveName(String name) {
        // Preferenceに保存する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Const.NameKEY, name);
        editor.commit();
    }

    private void savePersonalData(String mUid,String name,String postalCode,String ageBuild,String type,String otherForm,String pro,String otherType,String place,String otherPlace,String budget,String age,String sex,String estimate,String flag) {
        // Preferenceに保存する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Const.mUidKEY, mUid);
        editor.putString(Const.NameKEY, name);
        editor.putString(Const.PostalCodeKEY, postalCode);
        editor.putString(Const.AgeBuildKEY, ageBuild);
        editor.putString(Const.TypeKEY, type);
        editor.putString(Const.OtherFormKEY, otherForm);
        editor.putString(Const.ProKEY, pro);
        editor.putString(Const.OtherTypeKEY, otherType);
        editor.putString(Const.PlaceKEY, place);
        editor.putString(Const.OtherPlaceKEY, otherPlace);
        editor.putString(Const.Budget, budget);
        editor.putString(Const.Age, age);
        editor.putString(Const.Sex, sex);
        editor.putString(Const.Estimate, estimate);
        editor.putString(Const.FlagKEY, flag);

        editor.commit();


    }


}

