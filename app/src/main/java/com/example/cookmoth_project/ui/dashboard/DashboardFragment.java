package com.example.cookmoth_project.ui.dashboard;

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
import com.example.cookmoth_project.RecipeData;
import com.example.cookmoth_project.databinding.FragmentDashboardBinding;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private @NonNull FragmentDashboardBinding binding;
    private ListView list;
    private DashboardListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(requireActivity()).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        list = binding.likeList;

        // 필터링된 데이터 생성
        List<Integer> filteredImages = new ArrayList<>();
        List<String> filteredTitles = new ArrayList<>();
        List<String> filteredViewCounters = new ArrayList<>();
        List<String> filteredThumbCounters = new ArrayList<>();
        List<Boolean> filteredIsLikes = new ArrayList<>();

        for (int i = 0; i < RecipeData.getIsLikes().size(); i++) {
            if (RecipeData.getIsLikes().get(i)) {
                filteredImages.add(RecipeData.getImages().get(i));
                filteredTitles.add(RecipeData.getTitles().get(i));
                filteredViewCounters.add(RecipeData.getViewCounters().get(i));
                filteredThumbCounters.add(RecipeData.getThumbCounters().get(i));
                filteredIsLikes.add(RecipeData.getIsLikes().get(i));
            }
        }

        adapter = new DashboardListAdapter(requireActivity(), filteredImages, filteredTitles, filteredViewCounters, filteredThumbCounters, filteredIsLikes);
        list.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public class DashboardListAdapter extends ArrayAdapter<String> {

        private final Activity context;
        private List<Integer> images;
        private List<String> titles;
        private List<String> viewCounters;
        private List<String> thumbCounters;
        private List<Boolean> isLikes;

        public DashboardListAdapter(Activity context, List<Integer> images, List<String> titles, List<String> viewCounters, List<String> thumbCounters, List<Boolean> isLikes) {
            super(context, R.layout.listitem, titles);
            this.context = context;
            this.images = images;
            this.titles = titles;
            this.viewCounters = viewCounters;
            this.thumbCounters = thumbCounters;
            this.isLikes = isLikes;
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.listitem, null, true);

            ImageView imageView = rowView.findViewById(R.id.image);
            TextView title = rowView.findViewById(R.id.title);
            TextView viewCnt = rowView.findViewById(R.id.viewCnt);
            TextView thumbCnt = rowView.findViewById(R.id.thumbCnt);

            ImageView viewImg = rowView.findViewById(R.id.viewImg);
            ImageView thumbImg = rowView.findViewById(R.id.thumbImg);
            ImageView heartImg = rowView.findViewById(R.id.heartImg);

            title.setText(titles.get(position));
            imageView.setImageResource(images.get(position));
            viewCnt.setText(viewCounters.get(position));
            thumbCnt.setText(thumbCounters.get(position));

            viewImg.setImageResource(R.drawable.watching);
            thumbImg.setImageResource(R.drawable.thumbup);
            heartImg.setImageResource(R.drawable.heart02);

            heartImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // RecipeData에서 데이터 변경
                    RecipeData.unlikeRecipe(titles.get(position));

                    // 어댑터에서 데이터 제거
                    titles.remove(position);
                    images.remove(position);
                    viewCounters.remove(position);
                    thumbCounters.remove(position);
                    isLikes.remove(position);

                    Toast.makeText(context, "즐겨찾기에서 제거되었습니다!!", Toast.LENGTH_SHORT).show();

                    // 어댑터에 데이터 변경 알림
                    notifyDataSetChanged();
                }
            });

            return rowView;
        }
    }
}
