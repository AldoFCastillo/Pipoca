package com.digitalhouse.a0819cpmoacn01arce_01.view.fragment;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn01arce_01.R;
import com.digitalhouse.a0819cpmoacn01arce_01.controller.ExtrasController;
import com.digitalhouse.a0819cpmoacn01arce_01.model.Extra;
import com.digitalhouse.a0819cpmoacn01arce_01.model.Genre;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.Genres;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.SerieDetalles;
import com.digitalhouse.a0819cpmoacn01arce_01.utils.ResultListener;
import com.digitalhouse.a0819cpmoacn01arce_01.utils.ShakeDetector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExtrasFragment extends Fragment {

    @BindView(R.id.extrasTitle)
    TextView textTitle;
    @BindView(R.id.imageExtra)
    ImageView imageView;
    private List<Genre> genres = new ArrayList<>();
    String generos = "";
    private Boolean pedido = false;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    public ExtrasFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_extras, container, false);
        ButterKnife.bind(this, view);
        Shake();
        return view;
    }

    public interface notificadorExtras {
        void enviarNotificacionExtras();
    }

    public void Shake() {
        mSensorManager = (SensorManager) Objects.requireNonNull(getActivity()).getSystemService(Context.SENSOR_SERVICE);
        assert mSensorManager != null;
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                pedido = true;
                ExtrasController extrasController = new ExtrasController();
                extrasController.getExtrasController(new ResultListener<Extra>() {
                    @Override
                    public void onFinish(Extra result) {
                        pedido = false;
                        textTitle.setText(result.getTitle());
                        Glide.with(ExtrasFragment.this).load(result.getPosterPath()).into(imageView);
                    }
                });
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}
