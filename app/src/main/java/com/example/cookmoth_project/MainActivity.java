package com.example.cookmoth_project;

import static com.example.cookmoth_project.RecipeData.my_db;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.cookmoth_project.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cookmoth_project.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.Comparator;


public class MainActivity extends AppCompatActivity {

    public static userDTO user = new userDTO();

    private ActivityMainBinding binding;
    TextView dropMenu;
    private HomeFragment hf;

    private TextView textTitle;
    ImageView searchIcon ;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // 액션바 숨기기
        getSupportActionBar().hide();


        // 사용자 지정 툴바를 액션바로 설정
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        searchIcon = (ImageView) findViewById(R.id.icon_search);
        textTitle = (TextView) findViewById(R.id.text_title);
        dropMenu = (TextView) findViewById(R.id.dropdown_menu);
        // DestinationChangedListener 추가
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, Bundle arguments) {
                if (destination.getId() == R.id.navigation_home) {
                    textTitle.setText("레시피 게시판");
                    dropMenu.setVisibility(View.VISIBLE);
                    searchIcon.setVisibility(View.VISIBLE);


                } else if (destination.getId() == R.id.navigation_dashboard) {
                    textTitle.setText("즐겨찾기");
                    dropMenu.setVisibility(View.INVISIBLE);
                    searchIcon.setVisibility(View.INVISIBLE);


                } else if (destination.getId() == R.id.navigation_notifications) {
                    textTitle.setText("My Page");
                    dropMenu.setVisibility(View.INVISIBLE);
                    searchIcon.setVisibility(View.INVISIBLE);
                }
            }
        });
        
        
        
        
        
        

        // 현재 fragment의 adapter를 사용하기 위해 필요
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        Fragment fragment = navHostFragment.getChildFragmentManager().getPrimaryNavigationFragment();
        hf = (HomeFragment) fragment;


        


        // 드롭다운 이벤트
        dropMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.searchView){
                            sortMyDbByViewCount();
                            if (hf != null) hf.updateListView();

                            dropMenu.setText("조회수");
                            return true;
                        } else if (item.getItemId() == R.id.searchLike) {
                            sortMyDbByLikeCount();
                            hf.updateListView();
                            if (hf != null) hf.updateListView();
                            dropMenu.setText("좋아요수");

                            return true;
                        }else if (item.getItemId() == R.id.isMyLike){
                            sortMyDbByIsLike();
                            hf.updateListView();
                            if (hf != null) hf.updateListView();
                            dropMenu.setText("저장한 글");
                            return true;
                        }


                        return false;
                    }
                });
                popupMenu.show(); // 팝업 메뉴 표시
            }
        });

        // 플로팅_버튼_띄우기
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WriteActivity.class);
                startActivity(intent);
            }

        });


        // 검색 아이콘 클릭 이벤트
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });




    }


    private void sortMyDbByViewCount() {
        Collections.sort(my_db, new Comparator<RECIPEDTO>() {
            @Override
            public int compare(RECIPEDTO r1, RECIPEDTO r2) {
                return Integer.compare( Integer.parseInt(r2.getvCnt()) , Integer.parseInt(r1.getvCnt()) ); // 내림차순 정렬
            }
        });
    }
    private void sortMyDbByLikeCount() {
        Collections.sort(my_db, new Comparator<RECIPEDTO>() {
            @Override
            public int compare(RECIPEDTO r1, RECIPEDTO r2) {
                return Integer.compare( Integer.parseInt(r2.gettCnt()) , Integer.parseInt(r1.gettCnt()) ); // 내림차순 정렬
            }
        });
    }


    private void sortMyDbByIsLike() {
        Collections.sort(my_db, new Comparator<RECIPEDTO>() {
            @Override
            public int compare(RECIPEDTO r1, RECIPEDTO r2) {
                boolean isLike1 = r1.isIsLike();
                boolean isLike2 = r2.isIsLike();

                // 두 객체가 모두 true인 경우는 우선순위를 부여하지 않음
                if (isLike1 && isLike2) {
                    return 0;
                }
                // r1이 true이고 r2가 false인 경우 r1을 우선순위로 설정
                else if (isLike1 && !isLike2) {
                    return -1;
                }
                // r1이 false이고 r2가 true인 경우 r2를 우선순위로 설정
                else if (!isLike1 && isLike2) {
                    return 1;
                }
                // 두 객체가 모두 false인 경우는 tCnt를 기준으로 정렬
                else {
                    return Integer.compare(Integer.parseInt(r2.gettCnt()), Integer.parseInt(r1.gettCnt()));
                }
            }
        });
    }





}