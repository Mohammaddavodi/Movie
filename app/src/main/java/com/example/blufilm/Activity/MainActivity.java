package com.example.blufilm.Activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Movie;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.blufilm.Adapter.FilmListAdapter;
import com.example.blufilm.Api.API;
import com.example.blufilm.DataBase.Database;
import com.example.blufilm.DataBase.UserDao;
import com.example.blufilm.Domain.ListFilm;
import com.example.blufilm.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterNewMovie,adapterUpComing;
    private RecyclerView recyclerViewNewMovies,recyclerViewUpComing;
    private RequestQueue mRequestQueue;
    private MaterialToolbar toolbar;
    private AppBarLayout appBarLayout;
    private DrawerLayout drawerLayout;
    private MenuItem info;
    private ConstraintLayout secondLayout;
    private StringRequest mStringRequest,mStringRequest2;
    private ProgressBar Loading2;
    private ImageView imageRetry;
    private ScrollView scrollView2;
    private RecyclerView viewMain;
    private List<Movie> movieList = new ArrayList<>();
    private Database userDb;
    private UserDao userDao;
    private FloatingActionButton linkBtn;
    private AlertDialog.Builder alertBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();
       toolbar();
       if (isNetworkAvailable()){
           fetchJSON();
       }else {
           viewMain.setVisibility(View.INVISIBLE);
           Snackbar snackbar = Snackbar
                   .make(viewMain, "NetWork Unavailable", Snackbar.LENGTH_LONG)
                   .setAction("close app", v -> {
                        finish();
                       System.exit(0);
                   });
           snackbar.show();
       }
    }

    private void fetchJSON() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final String JSON_URL = "https://raw.githubusercontent.com/theapache64/top250/master/top250_min.json";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, JSON_URL, null,
                response -> {
                    // Parse JSON response
                    try {
                            List<API> item=new ArrayList<>();
                            String movieName = null;
                            String cover = null;
                            String desc=null;
                            String url=null;
                            List<String> actors=new ArrayList<>();
                            List<String> director=new ArrayList<>();
                            List<String> genre=new ArrayList<>();
                            double rating=0.0;
                            Integer date=0;
                            API api = null;
                        for (int i = 0; i < response.length(); i++) {

                            JSONObject movie = response.getJSONObject(i);
                             movieName = movie.getString("name");
                             cover = movie.getString("thumb_url");
                            rating = movie.getDouble("rating");
                            date=movie.getInt("year");
                            desc=movie.getString("desc");
                            actors= Collections.singletonList((movie.getString("actors").toString()));
                            director= Collections.singletonList(movie.getString("directors").toString());
                            genre= Collections.singletonList(movie.getString("genre").toString());
                           url=movie.getString("imdb_url").toString();

                             api=new API(movieName,cover,actors,date,
                                    "imageUrl",director,genre,
                                    rating,url,desc);
                            item.add(api);

                            Log.d("TAG", "onResponse:imdb"+url);

                        }


                        Log.d("TAG", "fetchJSON: "+item.get(0).getName());
                        adapterNewMovie=new FilmListAdapter(item);
                        recyclerViewNewMovies.setAdapter(adapterNewMovie);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Log.e("Volley Error", error.toString());
                    }
                });

        // Add the request to the RequestQueue.

        requestQueue.add(request);
    }

    private void initview(){
        scrollView2=findViewById(R.id.scrollView2);
        viewMain=findViewById(R.id.view1);
        imageRetry=findViewById(R.id.imageRetry);
        scrollView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        recyclerViewNewMovies=findViewById(R.id.view1);
        recyclerViewNewMovies.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        linkBtn=findViewById(R.id.pageLinkBtn);
        linkBtn.setOnClickListener(v -> {
            dialogClick();

        });
    }
//    private void sendRequest1(){
//        mRequestQueue= Volley.newRequestQueue(this);
//        Loading1.setVisibility(View.VISIBLE);
//        mStringRequest=new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=1", response -> {
//            Gson gson=new Gson();
//            Loading1.setVisibility(View.GONE);
//            ListFilm items=gson.fromJson(response, ListFilm.class);
//            adapterNewMovie=new FilmListAdapter(items);
//            recyclerViewNewMovies.setAdapter(adapterNewMovie);
//        }, error -> {
//            Log.i("error","send1:"+error.toString());
//            Loading1.setVisibility(View.GONE);
//        });
//mRequestQueue.add(mStringRequest);
//    }


    
//    private void sendRequest2(){
//        mRequestQueue= Volley.newRequestQueue(this);
//        Loading2.setVisibility(View.VISIBLE);
//        mStringRequest2=new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=3", response -> {
//            Gson gson=new Gson();
//            Loading2.setVisibility(View.GONE);
//            ListFilm items=gson.fromJson(response, ListFilm.class);
//            adapterUpComing=new FilmListAdapter(items);
//            recyclerViewUpComing.setAdapter(adapterUpComing);
//        }, error -> {
//            Loading2.setVisibility(View.GONE);
//        });
//mRequestQueue.add(mStringRequest2);
//    }
    private void toolbar(){
        userDb=Room.databaseBuilder(this, Database.class,"userTable")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        userDao=userDb.getDao();


        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("logged_in_user", "Guest");

        secondLayout=findViewById(R.id.secondLayout);
        toolbar=findViewById(R.id.toolbarMain);
        info=findViewById(R.id.menuItem_info);
        appBarLayout=findViewById(R.id.appbarLayout);
        drawerLayout=findViewById(R.id.drawerLayout_main);

        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(getParent(),drawerLayout,toolbar,R.string.drawerOpen,R.string.drawerClose);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        NavigationView navigationMenu=findViewById(R.id.navigationView);
        navigationMenu.setNavigationItemSelectedListener(Item -> {
          final TextView userNameTv = navigationMenu.findViewById( R.id.userNameTv);

         //userNameTv.setText(userDao.User);

            if (Item.getItemId() == R.id.menuItem_info) {
                Intent info=new Intent(getApplicationContext(), infoActivity.class);
                startActivity(info);
            }
            return true;
        });
    }
    private void dialogClick(){
        final String[] websites = {"uptvs", "film2movie", "hexdl", "filimo"};
        final String[] urls = {"https://www.uptvs.com/", "https://www.film2movie.asia/", "https://hexdl.com/", "https://www.filimo.com/"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("you Can DownLoad Movie Here:");
        builder.setItems(websites, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urls[which]));
                startActivity(browserIntent);
            }
        });
        builder.show();
    }
    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }
}