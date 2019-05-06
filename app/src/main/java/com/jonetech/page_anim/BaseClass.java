package com.jonetech.page_anim;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

public class BaseClass extends AppCompatActivity {

    //Constant used to specify the type of transistion been used
    public static final String TRANSITION_TYPE = "Transition Type";

    // Constants for shared element transitions
    private static final String ANDROID_TRANSITION = "switchAndroid";
    private static final String BLUE_TRANSITION = "switchBlue";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the transition type from the intent and set it.
        if (getIntent().hasExtra(TRANSITION_TYPE)) {

            switch (getIntent().getStringExtra(TRANSITION_TYPE)) {

                case "Explode":
                    getWindow().setEnterTransition(new Explode());
                    break;
                case "Slide":
                    getWindow().setEnterTransition(new Slide());
                    break;

                case "Fade":
                    getWindow().setEnterTransition(new Fade());
                    break;
                default:
                    break;
            }


        }
    }


    /**
     * Explode the acticity with an Explode animation
     *
     * @param context   the application context
     * @param imageView The image view that was clicked
     */
    protected void explodeTransition(final Context context, ImageView imageView) {

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Relauch the the activity with the transistion information

                Intent intent = new Intent(context, context.getClass());
                intent.putExtra(TRANSITION_TYPE, "Explode");
                getWindow().setExitTransition(new Explode());
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());

            }
        });

    }

    /**
     * This method relaunches the application with Fade animation
     *
     * @param context   The application context
     * @param imageView The image view that was clicked
     */
    protected void fadeTransition(final Context context, ImageView imageView) {

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, context.getClass());

                intent.putExtra(TRANSITION_TYPE, "Fade");
                getWindow().setExitTransition(new Fade());
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());
            }
        });

    }

    /**
     * Animate an Image View 360 degree and back
     *
     * @param imageView The image to be rotated
     */

    protected void rotateTransition(ImageView imageView) {

        // Create the object animator with desired options
        final ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, View.ROTATION, 0f, 360f);
        animator.setDuration(1000);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(1);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the animation

                animator.start();
            }
        });

    }

    /**
     * Create a shared transition element between activities with two
     * common elements
     *
     * @param androidImage Android image that sets off the animation when clicked
     * @param otherImage The image view that will swap the android image view
     * @param intent The intent containing the current and the target activity
     * @param context The context of the application
     */

    protected void switchAnimations(final ImageView androidImage, final ImageView otherImage, final Intent intent, final Context context) {

        androidImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Set the transition details and start the activity
                ActivityOptions mActivityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity) context, Pair.create((View) androidImage, ANDROID_TRANSITION), Pair.create((View) otherImage, BLUE_TRANSITION));

                startActivity(intent, mActivityOptions.toBundle());
            }
        });

    }
}
