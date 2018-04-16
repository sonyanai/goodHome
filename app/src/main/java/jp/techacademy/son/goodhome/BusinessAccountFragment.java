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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


/**
 * Created by taiso on 2018/04/03.
 */

public class BusinessAccountFragment extends Fragment {
    public static final String TAG = "BusinessAccountFragment";

    TextView companyNameTextView;
    TextView addressTextView;
    TextView companyNumberTextView;
    TextView nameTextView;
    TextView totalEstimateTextView;
    TextView unwatchEstimateTextView;
    TextView thisPaymentTextView;
    TextView nextPaymentTextView;
    TextView prTextView;
    Button businessChangeButton;
    ImageView companyImageView;
    String Uid;
    DatabaseReference databaseReference;
    DatabaseReference userPathRef;
    private FirebaseUser user;



    ChildEventListener mEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(final DataSnapshot dataSnapshot, String s) {
            HashMap map = (HashMap) dataSnapshot.getValue();
            final String mUid = (String) map.get("mUid");
            final String companyName = (String) map.get("CompanyName");
            final String address = (String) map.get("Address");
            final String companyNumber = (String) map.get("CompanyNumber");
            final String name = (String) map.get("name");
            final String bitmapString = (String) map.get("BitmapString");
            final String totalEstimate = (String) map.get("TotalEstimate");
            final String unwatchEstimate = (String) map.get("UnwatchEstimate");
            final String thisPayment = (String) map.get("ThisPayment");
            final String nextPayment = (String) map.get("NextPayment");
            final String pr = (String) map.get("Pr");

            BusinessData post = new BusinessData(mUid, companyName,address,companyNumber,name,bitmapString,totalEstimate,unwatchEstimate,thisPayment,nextPayment,pr);

            if (post.getUid().equals(Uid)){
                nameTextView.setText(post.getName());
                companyNameTextView.setText(post.getCompanyName());
                //companyImageView.setImageBitmap(bitmapString);
                addressTextView.setText(post.getAddress());
                companyNumberTextView.setText(post.getCompanyNumber());
                totalEstimateTextView.setText(post.getTotalEstimate());
                unwatchEstimateTextView.setText(post.getUnwatchEstimate());
                thisPaymentTextView.setText(post.getThisPayment());
                nextPaymentTextView.setText(post.getNextPayment());
                prTextView.setText(post.getPr());
            }


        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        }
        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
        }
        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    };




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_businessaccount,container,false);

        companyImageView = (ImageView)v.findViewById(R.id.companyImageView);
        companyNameTextView = (TextView)v.findViewById(R.id.companyNameTextView);
        addressTextView = (TextView)v.findViewById(R.id.addressTextView);
        companyNumberTextView = (TextView)v.findViewById(R.id.companyNumberTextView);
        nameTextView = (TextView)v.findViewById(R.id.nameTextView);
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

        //メッセージから開いたとき
        Bundle bundle = getArguments();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (bundle!=null){
            Uid = bundle.getString("Uid");
            businessChangeButton.setVisibility(View.GONE);
        }else {
            Uid = user.getUid();
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();
        userPathRef = databaseReference.child(Const.BusinessPath);
        userPathRef.addChildEventListener(mEventListener);



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
