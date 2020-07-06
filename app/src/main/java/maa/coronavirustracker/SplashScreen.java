package maa.coronavirustracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import maa.coronavirustracker.UI.Main.IndiaAcivity;
import maa.coronavirustracker.UI.Main.MainActivity;

public class SplashScreen extends AppCompatActivity {

    ImageView love;
    private final int SPLASH_DISPLAY_TIMER = 5500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        love = findViewById(R.id.hearts);
        Glide.with(SplashScreen.this).load(R.drawable.hearts) .into(love);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashScreen.this, IndiaAcivity.class);
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        },SPLASH_DISPLAY_TIMER);


    }
}
