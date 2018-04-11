package jp.techacademy.son.goodhome;

import android.os.Bundle;
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



        view.findViewById(R.id.reformButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListFragment fragmentList = new ListFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.container,fragmentList,ListFragment.TAG)
                        .commit();

            }
        });

        view.findViewById(R.id.reformButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListFragment fragmentList = new ListFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.container,fragmentList,ListFragment.TAG)
                        .commit();
            }
        });


    }

}
