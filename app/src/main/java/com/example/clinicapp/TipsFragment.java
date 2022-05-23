package com.example.clinicapp;

import android.app.FragmentManager;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.Random;

public class TipsFragment extends Fragment {
    private TextView tip1_TV;
    private TextView nextTip1_TV;
    private TextView tip2_TV;
    private TextView nextTip2_TV;
    private TextView tip3_TV;
    private TextView nextTip3_TV;
    private FrameLayout tip_FrameLayout;
    private ViewFlipper flipper;
    private LinearLayout tip1_LL;
    private LinearLayout tip2_LL;
    private LinearLayout tip3_LL;

    public TipsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tips, container, false);

        String arr1[] = {"Limit sugary drinks", "Eat nuts and seeds", "Avoid ultra-processed foods", "Don’t fear coffee", "Eat fatty fish", "Get enough sleep", "Feed your gut bacteria", "Stay hydrated", "Don’t eat heavily charred meats", "Avoid bright lights before sleep"};
        String arr2[] = {"Avoid artificial trans fats", "Lift heavy weights", " Limit refined carbs", "Minimize your sugar intake", "Use extra virgin olive oil", "Don’t smoke or use drugs", "Get moving", "Eat adequate protein", "Eat plenty of fruits and vegetables", "Take vitamin D if you’re deficient"};
        String arr3[] = {"Drink enough water", "Do Not Put Toxic Things Into Your Body", "Avoid Excess Stress", "Meditate", "Eat whole eggs", "Avoid restrictive diets", "Get rid of excess belly fat", "Occasionally track your food intake", "Nurture your social relationships", "Use plenty of herbs and spices"};

        Random random = new Random();
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        int z = random.nextInt(10);

        tip1_TV = v.findViewById(R.id.tip1_TV);
        nextTip1_TV = v.findViewById(R.id.nextTip1_TV);
        tip2_TV = v.findViewById(R.id.tip2_TV);
        nextTip2_TV = v.findViewById(R.id.nextTip2_TV);
        tip3_TV = v.findViewById(R.id.tip3_TV);
        nextTip3_TV = v.findViewById(R.id.nextTip3_TV);
        tip_FrameLayout = v.findViewById(R.id.tip_FrameLayout);
        flipper = v.findViewById(R.id.flipper);

        tip3_LL = v.findViewById(R.id.tip3_LL);
        tip2_LL = v.findViewById(R.id.tip2_LL);
        tip1_LL = v.findViewById(R.id.tip1_LL);

        tip3_TV.setText(arr1[x]);
        tip2_TV.setText(arr2[y]);
        tip1_TV.setText(arr3[z]);

        // flipper.addView(cd_tip3);

        nextTip3_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tip_FrameLayout.removeView(tip3_LL);
                // flipper.addView(tip3_LL);
            }
        });

        nextTip2_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tip_FrameLayout.removeView(tip2_LL);

            }
        });

        nextTip1_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tip_FrameLayout.removeView(tip1_LL);

            }
        });

        return v;
    }
}