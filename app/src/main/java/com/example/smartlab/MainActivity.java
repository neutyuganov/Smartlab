package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ViewPager mSlideViewPager;
    LinearLayout mDotLayout;
    TextView btn_skip;

    ImageView[] dots;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_skip = findViewById(R.id.buttonSkip);

        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getitem(0) < 2)
                    mSlideViewPager.setCurrentItem(getitem(1),true);
                else {

                    Intent i = new Intent(MainActivity.this,MainActivity2.class);
                    startActivity(i);
                    finish();

                }

            }
        });


        mSlideViewPager = (ViewPager) findViewById(R.id.onboardViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.linearLayout);

        viewPagerAdapter = new ViewPagerAdapter(this);
        mSlideViewPager.setAdapter(viewPagerAdapter);

        setUpindicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);
    }

    public void setUpindicator(int position){

        dots = new ImageView[3];
        mDotLayout.removeAllViews();

        for (int i = 0 ; i < dots.length ; i++){

            dots[i] = new ImageView(this);
            dots[i].setImageResource(R.drawable.indicator_inactive);
            mDotLayout.addView(dots[i]);
        }
        dots[position].setImageResource(R.drawable.indicator_active);

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            setUpindicator(position);

            if (position == 2){

                btn_skip.setText("Завершить");

            }else {

                btn_skip.setText("Пропустить");

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getitem(int i){

        return mSlideViewPager.getCurrentItem() + i;
    }

}