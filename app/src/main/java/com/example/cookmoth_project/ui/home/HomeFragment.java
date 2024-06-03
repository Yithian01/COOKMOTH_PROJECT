package com.example.cookmoth_project.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cookmoth_project.R;
import com.example.cookmoth_project.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ListView list;
    private Integer[] images = {
            R.drawable.pic01,
            R.drawable.pic02,
            R.drawable.pic03,
            R.drawable.pic04,
            R.drawable.pic05,
            R.drawable.pic06,
            R.drawable.pic07,
            R.drawable.pic08,
            R.drawable.pic09,
            R.drawable.pic10,
    };
    private String[] titles = {
            "엄마손맛 계란말이",
            "환상의 닭날개구이 레시피",
            "금요일은 매콤한 닭볽음탕!!",
            "술한잔에는 핵붉닭볶음",
            "비빔국수 레시피",
            "파닭파닭!!",
            "일본식 오꼬노미야끼@@!",
            "독일 가정식 햄 만들기",
            "가정식 후라이드 치킨 비법",
            "아이들이 좋아하는 치킨 만들기"
    };
    private String[] viewCounter = {
            "13",
            "45",
            "11",
            "53",
            "78",
            "22",
            "21",
            "77",
            "67",
            "82"
    };

    private String[] thumbCounter = {
            "9",
            "19",
            "2",
            "40",
            "22",
            "11",
            "3",
            "54",
            "32",
            "66"
    };

    private boolean[] isLike = {
            false,
            false,
            true,
            false,
            false,
            true,
            false,
            false,
            false,
            false
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        list = binding.mainList; // ListView를 바인딩을 통해 참조합니다
        CustomList adapter = new CustomList(getActivity());
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), titles[position], Toast.LENGTH_SHORT).show();

            }
        });

        return root;
    }

    public class CustomList extends ArrayAdapter<String> {

        private final Activity context;

        public CustomList(Activity context){
            super(context, R.layout.listitem, titles);
            this.context = context;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.listitem, null, true);
            ImageView imageView = rowView.findViewById(R.id.image);
            TextView title = rowView.findViewById(R.id.title);
            TextView viewCnt = rowView.findViewById(R.id.viewCnt);
            TextView thumbCnt = rowView.findViewById(R.id.thumbCnt);

            ImageView viewImg = rowView.findViewById(R.id.viewImg);
            ImageView thumbImg = rowView.findViewById(R.id.thumbImg);
            ImageView heartImg = rowView.findViewById(R.id.heartImg);

            title.setText(titles[position]);
            imageView.setImageResource(images[position]);
            viewCnt.setText(viewCounter[position]);
            thumbCnt.setText(thumbCounter[position]);

            viewImg.setImageResource(R.drawable.watching);
            thumbImg.setImageResource(R.drawable.thumbup);
            heartImg.setImageResource(isLike[position] ? R.drawable.heart02 : R.drawable.heart01);

            heartImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // isLike 값을 토글
                    isLike[position] = !isLike[position];
                    if(isLike[position]){
                        Toast.makeText(getActivity(), "즐겨찾기에 추가되었습니다!!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "즐겨찾기에 제거되었습니다!!", Toast.LENGTH_SHORT).show();
                    }

                    // 이미지 리소스를 변경
                    heartImg.setImageResource(isLike[position] ? R.drawable.heart02 : R.drawable.heart01);
                }
            });


            return rowView;



        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
