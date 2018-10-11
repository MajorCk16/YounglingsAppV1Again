package com.example.younglings.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    String link="";
    ProgressDialog progressDialog;

    void login(final String username, String password){



        firebaseAuth.signInWithEmailAndPassword(username,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                finish();
                                Intent intent= new Intent(MainActivity.this,Dash.class);
                                intent.putExtra("User",username);
                                startActivity(intent);
                            }



                    }
                });



    }

    public   void nav(){
        String url = link;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        ImageView imgInsta,imgfb;
        TextView reg;
        final EditText edtUser,edtPassword;
        CardView cdLogin;

        firebaseAuth= FirebaseAuth.getInstance();

        cdLogin=(CardView)(findViewById(R.id.cdLogin));
        imgfb=(ImageView)(findViewById(R.id.imgfb));
        imgInsta=(ImageView)(findViewById(R.id.imginsta));
        reg= (TextView)(findViewById(R.id.tvReg)) ;
        edtUser=(EditText)(findViewById(R.id.edtUsername));
        edtPassword=(EditText)(findViewById(R.id.edtUsername));

        cdLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            String username= edtUser.getText().toString();
            String password= edtPassword.getText().toString();

                if (TextUtils.isEmpty(password)&&(TextUtils.isEmpty(username))){
                    Log.i("Length check","empty txt");
                   Toast toast= Toast.makeText(MainActivity.this,"Password or Email Empty",Toast.LENGTH_SHORT);
                  toast.setGravity(Gravity.CENTER,10,10);
                }
                    Log.i("Check","login executed");
                    login(username,password);


                    }
        });

        imgfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                link=link="https://www.facebook.com/YounglingsAfrica";
                nav();

            }
        });

        imgInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                link=link="https://www.instagram.com/younglings.africa";
                nav();

            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                link="https://www.younglings.capetown/apply";
                nav();

            }
        });

    }
}
