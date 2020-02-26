package com.yayak.moviecataloguesub2.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.yayak.moviecataloguesub2.MainActivity;
import com.yayak.moviecataloguesub2.R;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeLangFragment extends DialogFragment implements View.OnClickListener {
    private Button btnChoose, btnClose;
    private RadioGroup rgOptions;
    private RadioButton rbEn, rbIn;

    public ChangeLangFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_lang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnChoose = view.findViewById(R.id.btn_choose);
        btnChoose.setOnClickListener(this);
        btnClose = view.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(this);
        rgOptions = view.findViewById(R.id.rg_options);
        rbEn = view.findViewById(R.id.rb_en);
        rbIn = view.findViewById(R.id.rb_in);

        // first check the related radio button to the current language
        if (getResources().getConfiguration().locale.getLanguage().equalsIgnoreCase("en")) {
            rbEn.setChecked(true);
        } else if (getResources().getConfiguration().locale.getLanguage().equalsIgnoreCase("in")) {
            rbIn.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_close:
                getDialog().cancel();
                break;
            case R.id.btn_choose:
                int checkedRadioButtonId = rgOptions.getCheckedRadioButtonId();
                if (checkedRadioButtonId != -1) {
                    String lang = null;
                    switch (checkedRadioButtonId) {
                        case R.id.rb_en:
                            lang = "en";
                            break;
                        case R.id.rb_in:
                            lang = "in";
                            break;
                    }
                    setLocale(lang);
                    getDialog().dismiss();
                }
                break;
        }
    }

    private void setLocale(String lang) {

        Resources res = getResources();
        Configuration conf = res.getConfiguration();
        conf.locale = new Locale(lang);
        res.updateConfiguration(conf, res.getDisplayMetrics());
        // refresh current activity to show the updated languange
        Intent refresh = new Intent(getContext(), MainActivity.class);
        startActivity(refresh);
    }
}
