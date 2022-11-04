package com.dirtfy.bts.directionsApi;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class RouteHelper {
    /**
     생략
     **/

    /****************************** Directions API 관련 변수 *******************************/
    private static final String API_KEY="AIzaSyBpo4FSYivi0coGmtN4WNdzYgIMMbnObXU";
    //private static final String API_KEY="AIzaSyC4RTL3YHMRA7fiKo3bItXzc8bp_w1NJVQ";
    private LinearLayout method_container; // TextView를 동적 생성하기 위함
    private String str_url = null; // EditText의 값과 원래의 URL을 합쳐 검색 URL을 만들어 저장
    private String step = null; // 한 루트에 여러 개의 단계가 존재하므로 한 단계를 저장하기 위한 변수
    private String entire_step = null; // 여러 개의 단계를 하나의 단계로 합쳐 저장하기 위한 변수

    private int list_len = 0; // 배열의 동적 생성을 위한 변수

    public class Task extends AsyncTask<String, Void, String> {

        private String str, receiveMsg;

        @Override
        protected String doInBackground(String... params) {
            URL url = null;
            try {
                url = new URL(str_url); // str_url로 연결

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //url.openConnection()을 이용해서, HTTP Connection을 열고 호출

                if (conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString(); // JSON 파일을 String으로 바꿔 receiveMsg 변수에 저장

                    reader.close();
                } else {
                    Log.i("통신 결과", conn.getResponseCode() + "에러");
                }
            } catch (MalformedURLException e) { // 예외 처리
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return receiveMsg;
        }
    }

    public String sendClick(String depart, String arrival) { // Button 클릭 시 실행되는 함수

        /*method_container = findViewById(R.id.method_container);
        EditText dep_loc = findViewById(R.id.depart_loc);
        EditText arr_loc = findViewById(R.id.arrive_loc);

        String depart = dep_loc.getText().toString(); // EditText를 String으로 변환
        String arrival = arr_loc.getText().toString(); // EditText를 String으로 변환*/

        str_url = "https://maps.googleapis.com/maps/api/directions/json?" +
                "origin=" + depart + "&destination=" + arrival + "&mode=transit&departure_time=now&alternatives=true&language=Korean&key=" + API_KEY;
        // origin은 출발지, destination은 도착지를 설정해준다.
        // mode는 대중교통 모드인 transit과 걷는 모드인 walking, 차를 이용하는 driving 등이 있다.
        // departure_time은 출발하는 시간으로 now를 통해 현재로 설정해준다.
        // alternatives=true를 통해 여러 개의 방법들을 알아낼 수 있다.

        String resultText = "값이 없음";
        //int list_len = 0;

        try {
            resultText = new Task().execute().get(); // URL 연결하는 함수를 호출한 후 반환
            Log.d("WeGlonD", "resultText = " + resultText);

            JSONObject jsonObject = new JSONObject(resultText);
            String routes = jsonObject.getString("routes");
            Log.d("WeGlonD", "routes = "+routes);
            JSONArray routesArray = new JSONArray(routes);
            JSONObject subJsonObject = routesArray.getJSONObject(0);

            String legs = subJsonObject.getString("legs");
            JSONArray LegArray = new JSONArray(legs);
            Log.d("WeGlonD", "legs = "+legs);
            JSONObject legJsonObject = LegArray.getJSONObject(0);

            String steps = legJsonObject.getString("steps");
            JSONArray stepsArray = new JSONArray(steps);
            Log.d("WeGlonD", "steps = "+steps);
            list_len = stepsArray.length(); // stepsArray의 길이를 list_len 변수에 저장

            String[] getInstructions = new String[list_len];
            // Array 길이를 가지는 동적 배열을 생성
            String[] arrival_name = new String[list_len];
            String[] depart_name = new String[list_len];
            String[] getHeadsign = new String[list_len];
            String[] getBusNo = new String[list_len];

            for (int i = 0; i < list_len; i++) { // 리스트의 길이만큼 반복
                JSONObject stepsObject = stepsArray.getJSONObject(i);
                getInstructions[i] = stepsObject.getString("html_instructions");
                // stepsArray에서 키값이 html_instructions인 value를 배열에 저장
                String[] Check = getInstructions[i].split(" ");
                // 현재의 단계가 버스나 지하철 같은 대중교통을 이용하는지 확인하기 위해
                // html_instructions의 value를 split 함수를 이용해 공백을 기준으로 잘라 배열에 저장
                String TransitCheck = Check[0];
                // html_instructions의 value에서 제일 처음 공백 전에 버스인지 지하철인지 나오므로
                // 배열의 0번째 요소를 TransitCheck 변수에 저장

                if (TransitCheck.equals("Bus") || TransitCheck.equals("Subway")
                        || TransitCheck.equals("train") || TransitCheck.equals("rail")
                        || TransitCheck.equals("버스") || TransitCheck.equals("지하철")) {

                    String transit_details = stepsObject.getString("transit_details");
                    JSONObject transitObject = new JSONObject(transit_details);

                    String arrival_stop = transitObject.getString("arrival_stop");
                    JSONObject arrivalObject = new JSONObject(arrival_stop);
                    arrival_name[i] = arrivalObject.getString("name");

                    String depart_stop = transitObject.getString("departure_stop");
                    JSONObject departObject = new JSONObject(depart_stop);
                    depart_name[i] = departObject.getString("name");

                    getHeadsign[i] = transitObject.getString("headsign");

                    String line = transitObject.getString("line");
                    JSONObject lineObject = new JSONObject(line);
                    getBusNo[i] = lineObject.getString("short_name");

                }

                /*step = getInstructions[i] +
                        "\n걸리는 시간 : " + getDuration[i] +
                        "\n" + depart_name[i] + " 승차" +
                        "\n" + arrival_name[i] + " 하차" +
                        "\n + " + getHeadsign[i] + "방향" +
                        "\n버스 번호 : " + getBusNo[i] + "\n\n";*/

                /*if (entire_step == null) {
                    entire_step = step;
                } else { // 각 단계를 하나의 단계로 만들기 위해 entire_step에 더해준다.
                    entire_step += step;
                }*/
            }

            //tv.setText(resultText);

            /*title_view("출발 : " + depart); // 동적 배열 생성 함수로 사용자에게 정보 표시
            method_view(entire_step);
            title_view("도착 : " + arrival + "\n");*/

        } catch(InterruptedException e) { // 예외 처리
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resultText;

    }
}