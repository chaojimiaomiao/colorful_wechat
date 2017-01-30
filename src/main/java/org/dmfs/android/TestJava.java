package org.dmfs.android;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

/**
 * Created by zhaibingjie on 17/1/29.
 */

public class TestJava extends Activity {

    private void what() {
        TextView textView = new TextView(this);
        Toolbar toolbar = new Toolbar(this);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showDialog(view);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
