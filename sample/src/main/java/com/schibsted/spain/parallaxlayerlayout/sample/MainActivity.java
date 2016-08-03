package com.schibsted.spain.parallaxlayerlayout.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import com.schibsted.spain.parallaxlayerlayout.AnimatedTranslationUpdater;
import com.schibsted.spain.parallaxlayerlayout.ParallaxLayerLayout;
import com.schibsted.spain.parallaxlayerlayout.SensorTranslationUpdater;

public class MainActivity extends AppCompatActivity {

  private ParallaxLayerLayout parallaxLayout;
  private SensorTranslationUpdater translationUpdater;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_ovni);
    //setContentView(R.layout.activity_main_squares);

    parallaxLayout = (ParallaxLayerLayout) findViewById(R.id.parallax);
    RadioGroup updaterGroup = (RadioGroup) findViewById(R.id.updater_group);

    translationUpdater = new SensorTranslationUpdater(this);

    //noinspection ConstantConditions
    updaterGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.updater_sensor_button) {
          parallaxLayout.setTranslationUpdater(translationUpdater);
        } else if (checkedId == R.id.updater_auto_button) {
          parallaxLayout.setTranslationUpdater(new AnimatedTranslationUpdater(0.5f));
        }
      }
    });

    updaterGroup.check(R.id.updater_sensor_button);

    // Resets orientation when clicked
    parallaxLayout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        translationUpdater.reset();
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    translationUpdater.registerSensorManager();
  }

  @Override
  protected void onPause() {
    super.onPause();
    translationUpdater.unregisterSensorManager();
  }
}
