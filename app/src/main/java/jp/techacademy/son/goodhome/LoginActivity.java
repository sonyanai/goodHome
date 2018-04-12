package jp.techacademy.son.goodhome;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

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


        //loginKeyかcのとき客用bのとき企業用SearchFragmentから受け取る
        //企業用の時reform or rebuild or どっちもかでreformに保存rebuildに保存両方に保存

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String key = sp.getString(Const.KEY, "");

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (key.equals("c")) {
            //客の方
            CustomerLoginFragment fragmentCustomerLogin = new CustomerLoginFragment();
            transaction.replace(R.id.container, fragmentCustomerLogin);
            transaction.commit();
        } else {
            //ビジネスの方
            BusinessLoginFragment fragmentBusinessLogin = new BusinessLoginFragment();
            transaction.replace(R.id.container, fragmentBusinessLogin);
            transaction.commit();
        }


    }
}