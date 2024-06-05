package com.example.cookmoth_project.ui.dashboard;

import static com.example.cookmoth_project.RecipeData.my_db;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment; // androidx 패키지 사용
import androidx.lifecycle.ViewModelProvider;

import com.example.cookmoth_project.R;
import com.example.cookmoth_project.RECIPEDTO;
import com.example.cookmoth_project.RecipeData;
import com.example.cookmoth_project.databinding.FragmentDashboardBinding;
import com.example.cookmoth_project.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private @NonNull FragmentDashboardBinding binding;
    private ListView list;
    private CustomList adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(requireActivity()).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        list = binding.likeList;
        adapter = new CustomList(getActivity(),my_db);
        list.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public class CustomList extends ArrayAdapter<String> {

        private final Activity context;
        private final List<RECIPEDTO> data = new ArrayList<>();

        public CustomList(Activity context, List<RECIPEDTO> data) {
            super(context, R.layout.listitem );
            this.context = context;
            for (RECIPEDTO i : data){
                if(i.isIsLike()){
                    this.data.add(i);
                }
            }
        }

        @Override
        public int getCount() {
            return this.data.size();
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

            title.setText(this.data.get(position).getTitle());
            imageView.setImageResource(this.data.get(position).getImg());
            viewCnt.setText(this.data.get(position).getvCnt());
            thumbCnt.setText(this.data.get(position).gettCnt());

            heartImg.setImageResource(this.data.get(position).isIsLike() ? R.drawable.heart02 : R.drawable.heart01);


            //title.setText(titles.get(position));
            //imageView.setImageResource(images.get(position));
            //viewCnt.setText(viewCounters.get(position));
            //thumbCnt.setText(thumbCounters.get(position));

            viewImg.setImageResource(R.drawable.watching);
            thumbImg.setImageResource(R.drawable.thumbup);
            //heartImg.setImageResource(isLikes.get(position) ? R.drawable.heart02 : R.drawable.heart01);

            heartImg.setOnClickListener(new View.OnClickListener() {
                String name = data.get(position).getTitle();
                @Override
                public void onClick(View v) {
                    for (RECIPEDTO i :my_db){
                        if (i.getTitle().equals(name)){
                            i.setLike(false);
                        }
                    }

                    data.remove(position);
                    Toast.makeText(getActivity(), "즐겨찾기에서 제거 되었습니다!!", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();

                }
            });

            return rowView;
        }
    }




}
