package com.example.joinn.communityfragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.joinn.R;

public class CommunityFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, container, false);

        Button enrollButton = view.findViewById(R.id.enrol_btn);
        enrollButton.setVisibility(View.VISIBLE);
        enrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼을 클릭하면 실행되는 코드
            }
        });

        // 나머지 코드

        return view;
    }

}