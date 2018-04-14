package jp.techacademy.son.goodhome;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


/**
 * Created by taiso on 2018/04/03.
 */

public class BusinessLoginFragment extends Fragment {
    public static final String TAG = "BusinessLoginFragment";

    EditText CompanyNameEditText;
    EditText AddressEditText;
    EditText CompanyNumberEditText;
    EditText UserNameEditText;
    ImageView CompanyImageView;
    Button okButton;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_businesslogin,container,false);

        CompanyNameEditText = (EditText)v.findViewById(R.id.CompanyNameEditText);
        AddressEditText = (EditText)v.findViewById(R.id.AddressEditText);
        CompanyNumberEditText = (EditText)v.findViewById(R.id.CompanyNumberEditText);
        UserNameEditText = (EditText)v.findViewById(R.id.UserNameEditText);
        CompanyImageView = (ImageView)v.findViewById(R.id.CompanyImageView);
        okButton = (Button)v.findViewById(R.id.okButton);


        return v;
    }

    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //ログインログアウトボタン
        //編集ボタン
        //データを取得して表示

    }

}
