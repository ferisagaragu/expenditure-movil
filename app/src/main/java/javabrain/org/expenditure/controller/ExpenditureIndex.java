package javabrain.org.expenditure.controller;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import javabrain.org.expenditure.R;

public class ExpenditureIndex extends AppCompatActivity {

    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure_index);
        textView2 = (TextView) findViewById(R.id.textView2);
        startCountAnimation();
    }

    private void startCountAnimation() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 600);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView2.setText(animation.getAnimatedValue().toString());
            }
        });
        animator.start();
    }
}
