package jp.techacademy.son.goodhome;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by taiso on 2018/04/03.
 */

public class ListFragment extends Fragment {
    public static final String TAG = "ListFragment";

    ListView mListView;
    DatabaseReference databaseReference;
    DatabaseReference userPathRef;
    public ArrayList<BusinessData> businessDataArrayList;
    private BusinessDataArrayListAdapter mAdapter;





    ChildEventListener bEventListener = new ChildEventListener() {
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
            final String totalEvaluation = (String) map.get("TotalEvaluation");
            final String moneyEvaluation = (String) map.get("MoneyEvaluation");
            final String industry = (String)map.get("Industry");
            final String pr = (String) map.get("Pr");

            BusinessData post = new BusinessData(mUid, companyName,address,companyNumber,name,bitmapString,totalEstimate,unwatchEstimate,thisPayment,nextPayment,totalEvaluation,moneyEvaluation,industry,pr);
            businessDataArrayList.add(post);


            mAdapter.setBusinessDataArrayList(businessDataArrayList);
            mListView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();

//            if (gyousyu.equals("reform")){
//                if(post.getGyousyu.equals("reform")){
//                    list.add(post);
//                }
//            }else if (gyousyu.equals("kaitai")){
//                if (post.getGyousyu.equals("kaitai")){
//                    list.add(post);
//                }
//            }else if ()
//

            //ListViewについか


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
        View v = inflater.inflate(R.layout.fragment_list,container,false);

        mListView = (ListView)v.findViewById(R.id.listView);
        businessDataArrayList = new ArrayList<BusinessData>();
        mAdapter = new BusinessDataArrayListAdapter(this.getActivity(), R.layout.new_list);

        return v;
    }

    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle!=null){
            String flag = bundle.getString("flag");//searchfragmentからきた
        }


        databaseReference = FirebaseDatabase.getInstance().getReference();
        userPathRef = databaseReference.child(Const.BusinessPath);





        userPathRef.addChildEventListener(bEventListener);



    }

}
