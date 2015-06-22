package uk.co.myexample.jamescoggan.api;

import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/api/Animations.java
 * <p/>
 * Description: Animation functions
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class Animations {
    public static void bounceImage(ImageView imageView) {
        Animation scale = new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(500);
        AnimationSet animSet = new AnimationSet(true);
        animSet.setFillEnabled(true);
        animSet.addAnimation(scale);
        imageView.startAnimation(animSet);
    }

    public static void rotateImage(ImageView imageView) {
        RotateAnimation anim = new RotateAnimation(0f, 350f, 15f, 15f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(700);
        imageView.startAnimation(anim);
    }
}
