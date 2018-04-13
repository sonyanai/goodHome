package jp.techacademy.son.goodhome;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by taiso on 2018/04/03.
 */

public class SearchFragment extends Fragment {
    public static final String TAG = "SearchFragment";

    Button reformButton;
    Button rebuildButton;
    String flag = "0";





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_search,container,false);

        reformButton = (Button)v.findViewById(R.id.reformButton);
        rebuildButton = (Button)v.findViewById(R.id.rebuildButton);


        return v;
    }

    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);






        //インテントで値取得ログインに行く前に
        Bundle bundle = getArguments();
        if (bundle!=null){
            String aaa = bundle.getString("flag");
            flag = aaa;
        }

        //flagが0なら最初のサーチ画面1ならログインする前に客か起業かを選択させる

        if(flag.equals("1")){
            reformButton.setText("個人様");
            rebuildButton.setText("企業様");
        }





        view.findViewById(R.id.reformButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flag.equals("0")){
                    ListFragment fragmentList = new ListFragment();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container,fragmentList,ListFragment.TAG)
                            .commit();
                }else{
                    //AccountFragment fragmentAccount = new AccountFragment();
                    //getFragmentManager().beginTransaction()
                    //        .replace(R.id.container,fragmentAccount,AccountFragment.TAG)
                    //        .commit();
                    //インデントで番号飛ばすそれを取得してどっちのレイアウトにするか切り替える
                    //こっちは客
                    String loginKey = "c";
                    saveKey(loginKey);
                    MainActivity activity = (MainActivity)getActivity();
                    activity.intentLogin();

                }



            }
        });

        view.findViewById(R.id.rebuildButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flag.equals("0")){
                    ListFragment fragmentList = new ListFragment();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container,fragmentList,ListFragment.TAG)
                            .commit();
                }else{
                    //AccountFragment fragmentAccount = new AccountFragment();
                    //getFragmentManager().beginTransaction()
                    //        .replace(R.id.container,fragmentAccount,AccountFragment.TAG)
                    //        .commit();

                    //インデントで番号飛ばすそれを取得してどっちのレイアウトにするか切り替える
                    //こっちは企業
                    String loginKey = "b";
                    saveKey(loginKey);
                    MainActivity activity = (MainActivity)getActivity();
                    activity.intentLogin();

                }


            }
        });


    }

    private void saveKey(String key) {
        // Preferenceに保存する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Const.KEY, key);
        editor.commit();
    }


}
