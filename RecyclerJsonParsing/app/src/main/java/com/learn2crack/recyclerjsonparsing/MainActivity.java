package com.learn2crack.recyclerjsonparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * todo 0
 * @see <a href="https://www.learn2crack.com/2016/02/recyclerview-json-parsing.html"></a>
 * @see <a href="https://github.com/PhongHuynh93/recyclerview-json-parsing"></a>
 *
 *
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<AndroidVersion> data;
    private DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }
    private void initViews(){
        recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }

    private void loadJSON(){
        /**
         * look {@link RequestInterface} for base url
         *
         * todo 5 we initialize Retrofit and carry out network operations.
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.learn2crack.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        /**
         * todo 6 {@link RequestInterface} object is created using
         */
        RequestInterface request = retrofit.create(RequestInterface.class);

        /**
         * todo 7 To carry out a asyncronous request Call object should be created from RequestInterface object by calling getJSON() method.
         */
        Call<JSONResponse> call = request.getJSON();


        /**
         * todo 8 sau khi khai báo xong xuoi thì chạy kết nối internet torng background bằng method enqueue
         */
        call.enqueue(new Callback<JSONResponse>() {
            //  If the request is success and response is received onResponse() callback method is executed.
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {

//                The JSONResponse object is obtained by calling body() method on the Response object.
                JSONResponse jsonResponse = response.body();

                // . From the JSONResponse object we obtain the AndroidVersion array object.
                // chuyển từ array ssang arraylist
                data = new ArrayList<>(Arrays.asList(jsonResponse.getAndroid()));
                adapter = new DataAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
}
