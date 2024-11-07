package com.example.scroll_view_test;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private NestedScrollView scrollView;
    private View contentView;
    private Rect rect = new Rect();
    private boolean isKeyboardShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = findViewById(R.id.scrollView); // Replace with your ScrollView/NestedScrollView ID
        contentView = findViewById(R.id.contentView); // Replace with your content view ID

        scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int heightDiff = contentView.getRootView().getHeight() - contentView.getHeight();
                contentView.getWindowVisibleDisplayFrame(rect);
                int statusBarHeight = rect.top;

                if (heightDiff > statusBarHeight) {
                    if (!isKeyboardShown) {
                        scrollView.post(() -> {
                            scrollView.smoothScrollTo(0, contentView.getBottom());
                        });
                        isKeyboardShown = true;
                    }
                } else {
                    if (isKeyboardShown) {
                        scrollView.post(() -> {
                            scrollView.smoothScrollTo(0, 0);
                        });
                        isKeyboardShown = false;
                    }
                }
            }
        });


    }
}