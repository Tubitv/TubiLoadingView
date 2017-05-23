package com.tubitv.ui.demo;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tubitv.ui.TubiLoadingView;
import com.tubitv.ui.demo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    DemoObservable observable;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        observable = new DemoObservable();
        binding.setObs(observable);

        binding.toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tubiLoadingView2.toggle();
            }
        });

        binding.toggle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                observable.setPlay(!observable.isPlay());
                return true;
            }
        });
    }

}
