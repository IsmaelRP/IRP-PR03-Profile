package es.iessaladillo.ismaelraqi.pr03.utils;

import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import androidx.test.espresso.matcher.BoundedMatcher;

/**
 * Utility class for test matchers.
 */
// DO NOT TOUCH
public class Matchers {

    private Matchers() {
    }

    public static Matcher<View> withBoldTypeface(final boolean isBold) {
        return new BoundedMatcher<View, TextView>(TextView.class) {
            @Override
            public boolean matchesSafely(TextView textView) {
                return isBold == textView.getTypeface().isBold();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with bold typeface: ");
            }
        };
    }

}