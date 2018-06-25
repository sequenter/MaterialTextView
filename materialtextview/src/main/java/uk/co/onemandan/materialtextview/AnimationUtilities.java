package uk.co.onemandan.materialtextview;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

class AnimationUtilities {
    static void animFadeText(final TextView textView, final CharSequence text){
        Animation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(textView.getContext().getResources().getInteger(android.R.integer.config_shortAnimTime));
        anim.setRepeatCount(1);
        anim.setRepeatMode(Animation.REVERSE);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                textView.setText(text);
            }
        });

        textView.startAnimation(anim);
    }
}
