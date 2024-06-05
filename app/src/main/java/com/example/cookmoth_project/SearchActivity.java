package com.example.cookmoth_project;

import static com.example.cookmoth_project.RecipeData.my_db;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cookmoth_project.ui.dashboard.DashboardFragment;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ListView list;
    private CustomList adapter;
    private ImageView back_icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // 액션바 설정
        getSupportActionBar().hide();


        SearchView searchView = (SearchView) findViewById(R.id.searchView);


        list = (ListView) findViewById(R.id.searchList);
        adapter = new CustomList(this);
        list.setAdapter(adapter);



        back_icon = (ImageView) findViewById(R.id.back_icon);

        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchRecipes(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchRecipes(newText);
                return false;
            }
        });
    }




    private void searchRecipes(String query) {
        List<RECIPEDTO> matchedRecipes = new ArrayList<>();



        for (RECIPEDTO recipe : my_db) {
            if (recipe.getTitle().toLowerCase().contains(query.toLowerCase())) {
                matchedRecipes.add(recipe);
            }
        }

        adapter.clear();
        adapter.setData(matchedRecipes);
        adapter.notifyDataSetChanged();
        if (matchedRecipes == null){
            Toast.makeText(getApplicationContext(), "찾으시는 결과가 없습니다!!", Toast.LENGTH_SHORT).show();
        }

    }

    public class CustomList extends ArrayAdapter<String> {

        private final Activity context;
        private final List<RECIPEDTO> data = new ArrayList<>();

        public CustomList(Activity context) {
            super(context, R.layout.listitem );
            this.context = context;
        }

        @Override
        public int getCount() {
            return this.data.size();
        }


        public void setData(List<RECIPEDTO> data) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
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
                    boolean currentIsLike = my_db.get(position).isIsLike();
                    my_db.get(position).setLike(!currentIsLike);
                    if (!currentIsLike) {
                        Toast.makeText(getApplicationContext(), "즐겨찾기에 추가되었습니다!!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext() , "즐겨찾기에 제거되었습니다!!", Toast.LENGTH_SHORT).show();
                    }

                    // 이미지 리소스를 변경
                    heartImg.setImageResource(!currentIsLike ? R.drawable.heart02 : R.drawable.heart01);
                }
            });


            notifyDataSetChanged();
            return rowView;
        }
    }



}