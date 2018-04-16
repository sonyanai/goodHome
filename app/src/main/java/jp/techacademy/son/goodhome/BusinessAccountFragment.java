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
import android.widget.TextView;


/**
 * Created by taiso on 2018/04/03.
 */

public class BusinessAccountFragment extends Fragment {
    public static final String TAG = "BusinessAccountFragment";

    TextView companyNameTextView;
    TextView addressTextView;
    TextView companyNumberTextView;
    TextView nameTextView;
    TextView sexTextView;
    TextView ageTextView;
    TextView totalEstimateTextView;
    TextView unwatchEstimateTextView;
    TextView thisPaymentTextView;
    TextView nextPaymentTextView;
    TextView prTextView;
    Button businessChangeButton;
    String companyName;
    String address;
    String companyNumber;
    ImageView companyImageView;
    String name;
    String sex;
    String age;
    String bitmapString;
    String totalEstimate;
    String unwatchEstimate;
    String thisPayment;
    String nextPayment;
    String pr;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_businessaccount,container,false);

        companyImageView = (ImageView)v.findViewById(R.id.companyImageView);
        companyNameTextView = (TextView)v.findViewById(R.id.companyNameTextView);
        addressTextView = (TextView)v.findViewById(R.id.addressTextView);
        companyNumberTextView = (TextView)v.findViewById(R.id.companyNameTextView);
        nameTextView = (TextView)v.findViewById(R.id.nameTextView);
        sexTextView = (TextView)v.findViewById(R.id.sexTextView);
        ageTextView = (TextView)v.findViewById(R.id.ageTextView);
        totalEstimateTextView = (TextView)v.findViewById(R.id.totalEstimateTextView);
        unwatchEstimateTextView = (TextView)v.findViewById(R.id.unwatchEstimateTextView);
        thisPaymentTextView = (TextView)v.findViewById(R.id.thisPaymentTextView);
        nextPaymentTextView = (TextView)v.findViewById(R.id.nextPaymentTextView);
        prTextView = (TextView)v.findViewById(R.id.prTextView);




        businessChangeButton = (Button)v.findViewById(R.id.businessChangeButton);

        return v;
    }

    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());

        companyName = sp.getString(Const.CompanyNameKEY,"");
        address = sp.getString(Const.AddressKEY, "");
        companyNumber = sp.getString(Const.CompanyNumberKEY, "");


        companyNameTextView.setText(companyName);
        //companyImageView.setImageBitmap(bitmapString);
        addressTextView.setText(address);
        companyNameTextView.setText(companyNumber);
        nameTextView.setText(name);
        sexTextView.setText(sex);
        ageTextView.setText(age);
        totalEstimateTextView.setText(totalEstimate);
        unwatchEstimateTextView.setText(unwatchEstimate);
        thisPaymentTextView.setText(thisPayment);
        nextPaymentTextView.setText(nextPayment);
        prTextView.setText(pr);


        view.findViewById(R.id.businessChangeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BusinessLoginFragment fragmentBusinessLogin = new BusinessLoginFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.container,fragmentBusinessLogin,BusinessLoginFragment.TAG)
                        .commit();

            }
        });


    }

}
