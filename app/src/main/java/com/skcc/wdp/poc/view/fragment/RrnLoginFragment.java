package com.skcc.wdp.poc.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import com.skcc.wdp.poc.R;

public class RrnLoginFragment extends BaseFragment {

    AppCompatEditText rrn_input;
    @Override
    public int getLayoutResID() {
        return R.layout.fragment_input_rrn_login;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rrn_input=view.findViewById(R.id.et_rrn_input);
        Button btnOK=view.findViewById(R.id.btn_rrn_ok);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rrn_input.getText().equals("") && rrn_input.getText()==null){
                   //주민등록번호 미입력시 진행되는 코드
                    Toast.makeText(getContext(),"주민등록번호를 입력해주세요!", Toast.LENGTH_SHORT).show();

                }else if(rrn_input.getText().equals("12345")){
                    //주민등록번호 일치시 작성할 코드
                    
                }
            }
        });

    }
}
