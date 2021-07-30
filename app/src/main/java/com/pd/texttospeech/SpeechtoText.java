package com.pd.texttospeech;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SpeechtoText extends AppCompatActivity {

    private final int REQ_CODE = 124;
    TextView tv;
    ImageView imv_speak;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_speechto_text );

        tv = (TextView)findViewById( R.id.tv );
        imv_speak = (ImageView)findViewById( R.id.imgv );

        imv_speak.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent( RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra( RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM );
                i.putExtra( RecognizerIntent.EXTRA_PROMPT, "Please Speak" );
                try {
                startActivityForResult( i, REQ_CODE );
                }catch (ActivityNotFoundException a){
                    Toast.makeText( SpeechtoText.this, "Sorry! Your device is not supporting. please check", Toast.LENGTH_LONG).show();
                }
            }
        } );


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

    switch (requestCode){
        case REQ_CODE:{
            if (resultCode== RESULT_OK && data!=null){
                ArrayList<String> obj = data.getStringArrayListExtra( RecognizerIntent.EXTRA_RESULTS );
                tv.setText( obj.get( 0 ));
            }
        }
    }

    }
}


