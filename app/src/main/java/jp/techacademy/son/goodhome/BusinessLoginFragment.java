package jp.techacademy.son.goodhome;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


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
    FirebaseUser user;
    DatabaseReference databaseReference;
    DatabaseReference businessPathRef;





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

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        businessPathRef = databaseReference.child(Const.BusinessPath);

        //ログインログアウトボタン
        //編集ボタン
        //データを取得して表示
        view.findViewById(R.id.okButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());

                String mUid = user.getUid();
                String companyName = CompanyNameEditText.getText().toString();
                String address = AddressEditText.getText().toString();
                String companyNumber = CompanyNumberEditText.getText().toString();
                String UserName = UserNameEditText.getText().toString();

                String sex = sp.getString(Const.SexKEY, "");
                String age = sp.getString(Const.AgeKEY, "");
                String bitmapString = sp.getString(Const.BitmapStringKEY, "");
                String totalEstimate = sp.getString(Const.TotalEstimateKEY, "");
                String unwatchEstimate = sp.getString(Const.UnwatchEstimateKEY, "");
                String thisPayment = sp.getString(Const.ThisPaymentKEY, "");
                String nextPayment = sp.getString(Const.NextPaymentKEY, "");
                String flag = sp.getString(Const.FlagKEY, "");


                Map<String,String> data = new HashMap<String,String>();

                data.put("mUid",mUid);
                data.put("CompanyName" ,companyName);
                data.put("Address" ,address);
                data.put("CompanyNumber" ,companyNumber);
                data.put("UserName",UserName);
                data.put("Sex" ,sex);
                data.put("Age" ,age);
                data.put("BitmapString" ,bitmapString);
                data.put("TotalEstimate" ,totalEstimate);
                data.put("UnwatchEstimate" ,unwatchEstimate);
                data.put("ThisPayment" ,thisPayment);
                data.put("NextPayment" ,nextPayment);
                data.put("flag" ,flag);

                businessPathRef.child(mUid).setValue(data);


//                Map<String,Object> childUpdates = new HashMap<>();
//                childUpdates.put(mUid,data);
            }
        });

    }

}
