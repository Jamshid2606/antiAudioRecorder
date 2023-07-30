package com.example.antiaudiorecorder;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {
    private MediaRecorder mRecorder;
    public static final int REQUEST_AUDIO_PERMISSION_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mRecorder = new MediaRecorder();
    }

    public boolean CheckPermissions() {
        // this method is used to check permission
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        return result1 == PackageManager.PERMISSION_GRANTED;
    }
    private void RequestPermissions() {
        // this method is used to request the
        // permission for audio recording and storage.
        ActivityCompat.requestPermissions(this, new String[]{RECORD_AUDIO}, REQUEST_AUDIO_PERMISSION_CODE);
    }
    public void pauseRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (CheckPermissions()){
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            try {
                mRecorder.prepare();
            } catch (IOException e) {
                Log.e("TAG", "prepare() failed");
            }
            pauseRecording();
        }else {
            RequestPermissions();
        }

//        boolean isMicFree = true;
//        MediaRecorder recorder = new MediaRecorder();
//        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
//        recorder.setOutputFile("/dev/null");
//        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
//        try {
//            recorder.start();
//        } catch (IllegalStateException e) {
//            Log.e("MediaRecorder", "start() failed: MIC is busy");
//            isMicFree = false;
//        }
//        if (isMicFree) {
//            Log.e("MediaRecorder", "start() successful: MIC is free");
//        }
//        recorder.stop();
//        recorder.release();
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        // Here you should do the same check with MediaRecorder.
        // And you can be sure that user does not
        // start audio recording through notifications.
        // Or user stops recording through notifications.
    }

}