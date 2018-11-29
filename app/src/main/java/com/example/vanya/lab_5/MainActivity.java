package com.example.vanya.lab_5;


import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import java.io.IOException;
import java.security.Policy;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ToggleButton toggleButton;
    private android.hardware.Camera camera;
    private android.hardware.Camera.Parameters parameters;


    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        camera = android.hardware.Camera.open();
        toggleButton = findViewById(R.id.toggleButton);
        //toggleButton.getAutofillValue();
        toggleButton.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (toggleButton.isChecked()) {
                    flashControl(true);
                }
                if (!toggleButton.isChecked()) {
                    flashControl(false);
                }
            }
        });
    }

   public void flashControl(boolean state){
       if (camera != null) {
           camera.release();
       }
       try {
           camera = android.hardware.Camera.open();
           parameters = camera.getParameters();
           if (state == true) {
               parameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_TORCH);
               camera.setParameters(parameters);
               camera.startPreview();

           } else {
               parameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_OFF);
               camera.setParameters(parameters);
           }
       } catch (RuntimeException e) {
       }
   }


}


