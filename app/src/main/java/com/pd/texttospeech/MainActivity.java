package com.pd.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech textToSpeech;
    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        editText = (EditText)findViewById( R.id.edt );
        button = (Button)findViewById( R.id.btn );
        textToSpeech = new TextToSpeech( getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

            textToSpeech.setLanguage( Locale.US );

            }
        } );

        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             String tospeak = editText.getText().toString();
                Toast.makeText( MainActivity.this, tospeak , Toast.LENGTH_SHORT ).show();
                textToSpeech.speak( tospeak,TextToSpeech.QUEUE_FLUSH,null );
            }
        } );
    }

    @Override
    protected void onPause() {
        if (textToSpeech!=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }
}