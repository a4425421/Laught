package com.md.test.testmd;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.md.test.testmd.bean.News;
import com.md.test.testmd.bean.Weather;
import com.md.test.testmd.component.RetrofitTogether;
import com.md.test.testmd.util.HttpRequest;
import com.md.test.testmd.util.HttpRevMsg;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerView;
    RecycleViewAdapter adapter;
    ArrayList<News> list;
    String key = "2925103ca91b4f63a95672aa3619c1f5";
    @SuppressLint("HandlerLeak")private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);



            Toast.makeText(getApplicationContext(),"加载成功",Toast.LENGTH_LONG).show();
        }
    };


    private Observer<Weather.WeatherInfoEntity> observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBaseUI();


        updateUI();



        observer = new Observer<Weather.WeatherInfoEntity>() {
            @Override
            public void onCompleted() {
            handler.sendEmptyMessage(0);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Weather.WeatherInfoEntity weather) {

            }
        };

        fetchDataByNetWork(observer);


    }

    private void fetchDataByNetWork(Observer<Weather.WeatherInfoEntity> observer ) {
        RetrofitTogether.getApiService().mWeatherApi("深圳",key).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).filter(new Func1<Weather, Boolean>() {
            @Override
            public Boolean call(Weather weather) {
                return weather.WeatherInfo.get(0).status.equals("ok");
            }
        }).map(new Func1<Weather, Weather.WeatherInfoEntity>() {

            @Override
            public Weather.WeatherInfoEntity call(Weather weather) {
                return weather.WeatherInfo.get(0);
            }
        }).doOnNext(new Action1<Weather.WeatherInfoEntity>() {
            @Override
            public void call(Weather.WeatherInfoEntity weatherInfoEntity) {
                Log.i("weather", weatherInfoEntity.now.tmp);
                Log.i("weather", weatherInfoEntity.basic.city);
            }
        }).subscribe(observer);
    }

    private void updateUI() {
        recyclerView=(RecyclerView)findViewById(R.id.recycleview);

        list = new ArrayList<News>();


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter = new RecycleViewAdapter(this, list));
        adapter.setOnRecyclerItemClickListener(new RecycleViewAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "click :" + position, Toast.LENGTH_LONG).show();
            }
        });


        HttpRequest.sendPost("http://120.24.235.202:8080/QhWebNewsServer/api/main/new", "species=r", new HttpRevMsg() {
            @Override
            public void revMsg(final String msg) {


                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject obj = new JSONObject(msg);
                            JSONObject joData = obj.getJSONObject("joData");


                            JSONArray array = joData.getJSONArray("news");
                            News news;
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject oj = array.getJSONObject(i);
                                news = new News();
                                news.setPurl(oj.optString("purl"));
                                news.setTitle(oj.optString("title"));
                                list.add(news);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

            }
        });
    }

    private void initBaseUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
