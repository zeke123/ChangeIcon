package com.zhoujian.changeicon;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity
{


    private ComponentName defaultComponentName;
    private ComponentName newYearComponentName;
    private ComponentName springFestivalComponentName;
    private PackageManager mPackageManager;

    @InjectView(R.id.new_year)
    Button mNewYear;
    @InjectView(R.id.spring_festival)
    Button mSpringFestival;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        clickEvent();

        defaultComponentName = getComponentName();
        newYearComponentName = new ComponentName(getBaseContext(),"com.zhoujian.changeicon.NewYear");
        springFestivalComponentName = new ComponentName(getBaseContext(),"com.zhoujian.changeicon.SpringFestival");
        mPackageManager = getApplicationContext().getPackageManager();
    }

    private void clickEvent()
    {
        mNewYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeIconNewYear(v);
            }
        });

        mSpringFestival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeIconSpringFestival(v);
            }
        });
    }

    public void changeIconNewYear(View view) {
        disableComponent(defaultComponentName);
        disableComponent(springFestivalComponentName);
        enableComponent(newYearComponentName);
    }

    public void changeIconSpringFestival(View view) {
        disableComponent(defaultComponentName);
        disableComponent(newYearComponentName);
        enableComponent(springFestivalComponentName);
    }


    private void enableComponent(ComponentName componentName)
    {
        mPackageManager.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    private void disableComponent(ComponentName componentName)
    {
        mPackageManager.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}
