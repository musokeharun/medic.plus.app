package com.bms.medicplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bms.medicplus.adapters.BoardingAdapter;
import com.bms.medicplus.models.Boarding;
import com.bms.medicplus.auth.Register;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class OnBoarding extends AppCompatActivity {
    private BoardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicator;
    private MaterialButton buttonOnboardingAction;
    private Button skipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        layoutOnboardingIndicator = findViewById(R.id.layoutOnboardingIndicators);
        buttonOnboardingAction = findViewById(R.id.buttonOnBoardingAction);
        skipButton = findViewById(R.id.skipButton);

        setBoardingItem();

        ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setOnBoardingIndicator();
        setCurrentOnBoardingIndicators(0);

        skipButton.setOnClickListener(view -> {
            skipToNextActivity();
        });

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnBoardingIndicators(position);
            }
        });
        buttonOnboardingAction.setOnClickListener(view -> {
            if (onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()) {
                onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
            } else {
                skipToNextActivity();
            }
        });
    }

    private void skipToNextActivity() {
        //TODO set newUser to false through sharedPrefernces;
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(OnBoarding.this);
        startActivity(new Intent(getApplicationContext(), Register.class));
        finish();
    }

    private void setOnBoardingIndicator() {
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(), R.drawable.onboarding_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicator.addView(indicators[i]);
        }
    }

    private void setCurrentOnBoardingIndicators(int index) {
        int childCount = layoutOnboardingIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutOnboardingIndicator.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_active));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_inactive));
            }
        }

        int expectedIndex = onboardingAdapter.getItemCount() - 1;
        if (index == expectedIndex) {
            buttonOnboardingAction.setText(R.string.start_text);
        } else {
            buttonOnboardingAction.setText(R.string.next_text);
        }
    }

    private void setBoardingItem() {
        List<Boarding> onBoardingItems = new ArrayList<>();

        Boarding itemFastFood = new Boarding();
        itemFastFood.setTitle("Welcome to MedicPlus");
        itemFastFood.setDescription("All services are integrated at MedicPlus.");
        itemFastFood.setImage(R.drawable.allinone);

        Boarding itemPayOnline = new Boarding();
        itemPayOnline.setTitle("One click for emergency handling");
        itemPayOnline.setDescription("Ensuring quality service via messaging,voice and video");
        itemPayOnline.setImage(R.drawable.emergency);

        Boarding itemEatTogether = new Boarding();
        itemEatTogether.setTitle("Ask the expert");
        itemEatTogether.setDescription("Consult your problems with the specialists and get connected");
        itemEatTogether.setImage(R.drawable.expert);

        onBoardingItems.add(itemFastFood);
        onBoardingItems.add(itemPayOnline);
        onBoardingItems.add(itemEatTogether);
        onboardingAdapter = new BoardingAdapter(onBoardingItems);
    }
}

