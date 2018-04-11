package jp.techacademy.son.goodhome;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * Created by taiso on 2018/04/03.
 */

public class ListFragment extends Fragment {
    public static final String TAG = "ListFragment";

    ListView listView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_list,container,false);

        listView = (ListView)v.findViewById(R.id.listView);

        return v;
    }

    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
