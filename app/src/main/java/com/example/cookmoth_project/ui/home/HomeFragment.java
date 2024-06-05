package com.example.cookmoth_project.ui.home;

import static com.example.cookmoth_project.RecipeData.my_db;

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
import com.example.cookmoth_project.RECIPEDTO;
import com.example.cookmoth_project.RecipeData;
import com.example.cookmoth_project.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ListView list;
    private CustomList adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        list = binding.mainList;
        adapter = new CustomList(getActivity(),my_db);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = my_db.get(position).getTitle();
                Toast.makeText(getActivity(), title, Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    public class CustomList extends ArrayAdapter<String> {

        private final Activity context;
        private final List<RECIPEDTO> data;

        public CustomList(Activity context, List<RECIPEDTO> data) {
            super(context, R.layout.listitem );
            this.context = context;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }


        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.listitem, null, true);

            ImageView imageView = rowView.findViewById(R.id.image);
            TextView title = rowView.findViewById(R.id.title);
            TextView viewCnt = rowView.findViewById(R.id.viewCnt);
            TextView thumbCnt = rowView.findViewById(R.id.thumbCnt);

            ImageView viewImg = rowView.findViewById(R.id.viewImg);
            ImageView thumbImg = rowView.findViewById(R.id.thumbImg);
            ImageView heartImg = rowView.findViewById(R.id.heartImg);

            title.setText(my_db.get(position).getTitle());
            imageView.setImageResource(my_db.get(position).getImg());
            viewCnt.setText(my_db.get(position).getvCnt());
            thumbCnt.setText(my_db.get(position).gettCnt());

            heartImg.setImageResource(my_db.get(position).isIsLike() ? R.drawable.heart02 : R.drawable.heart01);


            //title.setText(titles.get(position));
            //imageView.setImageResource(images.get(position));
            //viewCnt.setText(viewCounters.get(position));
            //thumbCnt.setText(thumbCounters.get(position));

            viewImg.setImageResource(R.drawable.watching);
            thumbImg.setImageResource(R.drawable.thumbup);
            //heartImg.setImageResource(isLikes.get(position) ? R.drawable.heart02 : R.drawable.heart01);

            heartImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // isLike 값을 토글
                    boolean currentIsLike = my_db.get(position).isIsLike();
                    my_db.get(position).setLike(!currentIsLike);
                    if (!currentIsLike) {
                        Toast.makeText(getActivity(), "즐겨찾기에 추가되었습니다!!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "즐겨찾기에 제거되었습니다!!", Toast.LENGTH_SHORT).show();
                    }

                    // 이미지 리소스를 변경
                    heartImg.setImageResource(!currentIsLike ? R.drawable.heart02 : R.drawable.heart01);
                }
            });


            notifyDataSetChanged();

            return rowView;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
