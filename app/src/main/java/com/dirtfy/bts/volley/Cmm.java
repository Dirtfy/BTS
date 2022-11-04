package com.dirtfy.bts.volley;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Cmm {

    private RQ rq;

    public Cmm(Context context){
        rq = new RQ(context);
    }

    public static ArrayList<Route> getRoutes(JSONObject response){
        ArrayList<Route> routeList = new ArrayList<>();

        try{
            JSONArray routesArray = (JSONArray) response.getJSONArray("routes");

            for(int i = 0;i < routesArray.length();i++){
                ArrayList<Transit> transitList = new ArrayList<>();
                JSONObject route = (JSONObject) routesArray.get(i);
                JSONArray stepsArray = (JSONArray) route.getJSONArray("legs").
                        getJSONObject(0).getJSONArray("steps");

                for(int j = 0;j < stepsArray.length();j++){
                    JSONObject unit = stepsArray.getJSONObject(j);
                    String mode = unit.getString("travel_mode");

                    if(!mode.equals("TRANSIT")) continue;

                    JSONObject details = (JSONObject) unit.getJSONObject("transit_details");

                    String arrivalStop = details.getJSONObject("arrival_stop").getString("name");
                    String arrivalTime = details.getJSONObject("arrival_time").getString("text");

                    String departureStop = details.getJSONObject("departure_stop").getString("name");
                    String departureTime = details.getJSONObject("departure_time").getString("text");

                    String distance = unit.getJSONObject("distance").getString("text");
                    String duration = unit.getJSONObject("duration").getString("text");

                    String type = details.getJSONObject("line").getJSONObject("vehicle").getString("type");
                    String name;

                    if(type.equals("HEAVY_RAIL"))
                        name = details.getString("trip_short_name");
                    else
                        name = details.getJSONObject("line").getString("name");

                    Transit newTransit = new Transit(arrivalStop, arrivalTime, departureStop, departureTime, distance, duration, type, name);
                    transitList.add(newTransit);
                }

                routeList.add(new Route(transitList));
            }
        } catch (JSONException e){
            e.printStackTrace();
        }

        return routeList;
    }

}
