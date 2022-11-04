package com.dirtfy.bts.Home.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dirtfy.bts.Home.MainActivity;
import com.dirtfy.bts.R;
import com.dirtfy.bts.directionsApi.RouteHelper;
import com.dirtfy.bts.routePage.RoutePageActivity;
import com.dirtfy.bts.volley.Cmm;
import com.dirtfy.bts.volley.Route;

import org.json.JSONException;
import org.json.JSONObject;

public class Home_one_way extends Fragment {
    private View view;
    private Button searchbtn;
    EditText start_et;
    EditText end_et;
    String depart;
    String arrival;
    RouteHelper routeHelper;
    JSONObject result;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_oneway, container, false);
        searchbtn = view.findViewById(R.id.search);
        start_et = view.findViewById(R.id.start_et);
        end_et = view.findViewById(R.id.end_et);
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                depart = start_et.getText().toString();
                arrival = end_et.getText().toString();
                routeHelper = new RouteHelper();
                MainActivity.routeArrayList.clear();
                try {
                    result = new JSONObject(routeHelper.sendClick(depart, arrival));
                    MainActivity.routeArrayList = Cmm.getRoutes(result);
                    for(int i = 0;i < MainActivity.routeArrayList.size();i++){
                        Route route = MainActivity.routeArrayList.get(i);
                        Log.d("Dirtfy_test", "\n#"+i+"\n"+route.toString());
                    }
                    Intent it = new Intent(getActivity(), RoutePageActivity.class);
                    startActivity(it);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        return view;
    }
}
