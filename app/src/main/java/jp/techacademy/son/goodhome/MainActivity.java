package jp.techacademy.son.goodhome;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {



    public SearchFragment fragmentSearch;
    public FavoriteFragment fragmentFavorite;
    public MessageFragment fragmentMessage;

    private FirebaseUser user;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.item_Search:
                    fragmentSearch = new SearchFragment();
                    transaction.replace(R.id.container, fragmentSearch);
                    transaction.commit();
                    return true;

                case R.id.item_Favorite:
                    fragmentFavorite = new FavoriteFragment();
                    transaction.replace(R.id.container, fragmentFavorite);
                    transaction.commit();
                    return true;

                case R.id.item_Message:
                    fragmentMessage = new MessageFragment();
                    transaction.replace(R.id.container, fragmentMessage);
                    transaction.commit();
                    return true;

            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Fragmentで最初の画面の設定をする
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Fragmentを作成します
        SearchFragment fragmentSearch = new SearchFragment();
        // コードからFragmentを追加
        // Fragmentの追加や削除といった変更を行う際は、Transactionを利用します
        // 新しく追加を行うのでaddを使用します
        // メソッドの1つ目の引数は対象のViewGroupのID、2つ目の引数は追加するfragment
        transaction.add(R.id.container, fragmentSearch);
        // 最後にcommitを使用することで変更を反映します
        transaction.commit();




        //BottomNavigationViewの定義して設置する
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        //リスナーのセット
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        user = FirebaseAuth.getInstance().getCurrentUser();
        switch (item.getItemId()){
            case R.id.loginButton:
                if(user==null) {
                    /*
                    String flag ="1";
                    Bundle bundle = new Bundle();
                    bundle.putString("flag", flag);
                    SearchFragment fragmentSearch = new SearchFragment();
                    fragmentSearch.setArguments(bundle);
                    transaction.replace(R.id.container, fragmentSearch);
                    transaction.commit();
                    */
                    intentLogin();
                }else{
                    View view = findViewById(android.R.id.content);
                    Snackbar.make(view, "ログイン中です", Snackbar.LENGTH_LONG).show();
                }
                break;
            case R.id.accountButton:
                if(user==null){
                    View view = findViewById(android.R.id.content);
                    Snackbar.make(view, "アカウントを作成してください", Snackbar.LENGTH_LONG).show();

                    String flag ="1";
                    Bundle bundle = new Bundle();
                    bundle.putString("flag", flag);
                    SearchFragment fragmentSearch = new SearchFragment();
                    fragmentSearch.setArguments(bundle);
                    transaction.replace(R.id.container, fragmentSearch);
                    transaction.commit();
                }else{
                    AccountFragment fragmentAccount = new AccountFragment();
                    transaction.replace(R.id.container, fragmentAccount);
                    transaction.commit();
                }
                break;
            case R.id.sortButton:
                SortFragment fragmentSort = new SortFragment();
                transaction.replace(R.id.container, fragmentSort);
                transaction.commit();
                break;
        }
        return false;
    }
    public void intentLogin(){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}
