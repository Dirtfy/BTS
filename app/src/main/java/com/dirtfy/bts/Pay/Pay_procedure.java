package com.dirtfy.bts.Pay;

import static com.dirtfy.bts.Home.MainActivity.routeArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dirtfy.bts.Home.MainActivity;
import com.dirtfy.bts.Pay.Adapter.RecyclerViewAdapter;
import com.dirtfy.bts.R;
import com.dirtfy.bts.routePage.RoutePageActivity;

public class Pay_procedure extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    Context context;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        context = this;
        recyclerView = findViewById(R.id.activity_my_post1_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        Intent it = getIntent();
        int Number = it.getIntExtra("number",0);
        adapter = new RecyclerViewAdapter(routeArrayList.get(Number),context);
        recyclerView.setAdapter(adapter);

        button = findViewById(R.id.pay_button);
        button.setOnClickListener(view -> {
            AlertDialog.Builder dlg = new AlertDialog.Builder(Pay_procedure.this);
            dlg.setTitle("결제 완료"); //제목
            dlg.setMessage("결제가 완료 되었습니다."); // 메시지
//                버튼 클릭시 동작
            dlg.setPositiveButton("확인",new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which) {
                    //토스트 메시지
                    dialog.dismiss();
                    RoutePageActivity.routePageActivity.finish();
                    finish();

                }
            });
            dlg.show();
        });
    }
}
