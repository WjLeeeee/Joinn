package com.example.joinn.mypagefragment;

import static com.google.android.gms.auth.api.signin.GoogleSignIn.getClient;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.joinn.R;
import com.example.joinn.sign.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;


public class MyPageFragment extends Fragment {

    private CircleImageView imguser;
    private TextView txtNickname, leveltxt;
    private DatabaseReference databaseRef;

    Button logoutBtn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_page, container, false);
        imguser = view.findViewById(R.id.imguser);
        txtNickname = view.findViewById(R.id.txtNickname);
        leveltxt = view.findViewById(R.id.leveltxt);

        // 파이어베이스의 realtime database를 참조합니다.
        databaseRef = FirebaseDatabase.getInstance().getReference();

        // 현재 로그인한 사용자의 uid를 가져옵니다.
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // realtime database에서 현재 사용자의 드라이버 레벨을 가져와서 leveltxt에 설정합니다.
        databaseRef.child("users").child(uid).child("드라이버 레벨").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String level = snapshot.getValue(String.class);
                leveltxt.setText(level);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // 데이터 로드에 실패했을 경우 처리할 내용을 여기에 작성합니다.
            }
        });

        // 현재 사용자의 닉네임을 가져와서 txtNickname에 설정합니다.
        databaseRef.child("users").child(uid).child("닉네임").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nickname = snapshot.getValue(String.class);
                txtNickname.setText(nickname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // 데이터 로드에 실패했을 경우 처리할 내용을 여기에 작성합니다.
            }
        });

        // 현재 사용자의 프로필 이미지를 가져와서 imguser에 설정합니다.
        databaseRef.child("users").child(uid).child("photoUrl").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String photoUrl = snapshot.getValue(String.class);

                if (photoUrl != null) {
                    // Glide를 사용하여 이미지를 불러옵니다.
                    Glide.with(getContext()).load(photoUrl).into(imguser);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // 데이터 로드에 실패했을 경우 처리할 내용을 여기에 작성합니다.
            }
        });

        return view;

    }

    private FirebaseAuth mAuth;

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Fragment 내의 뷰를 참조합니다.
        Button logoutBtn = view.findViewById(R.id.logoutBtn);
        Button revokeBtn = view.findViewById(R.id.revokeBtn);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        revokeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revokeAccess();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    private void revokeAccess() {
        mAuth.getCurrentUser().delete();
    }

}