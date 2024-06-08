package com.example.cookmoth_project.ui.mypage;

import static com.example.cookmoth_project.MainActivity.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cookmoth_project.LoginActivity;
import com.example.cookmoth_project.R;
import com.example.cookmoth_project.databinding.FragmentNotificationsBinding;

public class MyPageFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private TextView nickName;

    // ActivityResultLauncher 선언
    private final ActivityResultLauncher<Intent> loginActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // LoginActivity가 성공적으로 종료된 후 nickName을 업데이트
                    Toast.makeText(getActivity(), user.getNickName(), Toast.LENGTH_SHORT).show();
                    if (user != null) {
                        nickName.setText(user.getNickName());
                    }
                }
            }
    );
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MypageViewModel notificationsViewModel =
                new ViewModelProvider(this).get(MypageViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        nickName = (TextView) root.findViewById(R.id.nickName);




        if (user.getUserID().equals("")) {
            // LoginActivity를 시작
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            loginActivityLauncher.launch(intent);
        } else {
            nickName.setText(user.getNickName());
        }






        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}