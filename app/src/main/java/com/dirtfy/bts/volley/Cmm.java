package com.dirtfy.bts.volley;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

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
                    String cost = "\\0";

                    String type = details.getJSONObject("line").getJSONObject("vehicle").getString("type");
                    String name;

                    boolean booking = false;

                    boolean hasShortName = false;
                    for (Iterator<String> it = details.getJSONObject("line").keys(); it.hasNext(); ) {
                        String key = it.next();

                        if(key.equals("short_name"))
                            hasShortName = true;
                    }

                    if(type.equals("HEAVY_RAIL")) {
                        name = details.getString("trip_short_name");
                        booking = true;
                    }
                    else if(type.equals("BUS") && hasShortName) {
                        type = "CITY_BUS";
                        name = details.getJSONObject("line").getString("name") + " " +
                                details.getJSONObject("line").getString("short_name");
                    }
                    else if(type.equals("SUBWAY"))
                        name = details.getJSONObject("line").getString("name");
                    else {
                        name = details.getJSONObject("line").getString("name");
                        booking = true;
                    }

                    Transit newTransit = new Transit(arrivalStop, arrivalTime,
                            departureStop, departureTime, distance, duration, cost,
                            type, name, booking);
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
