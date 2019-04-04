package javabrain.org.expenditure.api;

import android.animation.ValueAnimator;
import android.widget.TextView;

import javabrain.org.expenditure.persistence.Global;

/**
 * Created by Fernando García on 04/04/2019.
 */

public class Animator {

    public static void numberAnimation(double value, final TextView textView) {
        ValueAnimator animator = ValueAnimator.ofInt(0,(int) Math.round(value));
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView.setText(animation.getAnimatedValue().toString());
            }
        });
        animator.start();
    }

}
