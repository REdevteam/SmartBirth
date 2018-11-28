package com.futech.smartbirth;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private List<DataModel> dataModelList;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private RequestQueue queue;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        dataModelList = new ArrayList<>();

        recyclerView = findViewById (R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        adapter = new ListAdapter(dataModelList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {

                swipeRefreshLayout.setRefreshing(true);
                loadRiwayatData();
            }
        });;

    }

    public void loadRiwayatData(){

        String url = "https://www.tokosms.com/api/smartbirth/getlist.php";
        RequestQueue requestQueue = VolleySingleton.getInstance(getApplicationContext()).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {
                        if(dataModelList.size()!=0) {
                            dataModelList.clear();
                        }

                        try{

                            JSONArray jsonArray=new JSONArray(response);

                            for(int i=0;i<jsonArray.length();i++) {

                                try {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                        String tanggal = jsonObject.getString("tanggal");
                                        String berat = jsonObject.getString("berat");

                                        dataModelList.add(new DataModel(tanggal,berat));

                                } catch (JSONException e) {

                                    e.printStackTrace();

                                }

                                adapter.notifyDataSetChanged();

                            }

                        }catch (JSONException e2){
                            e2.printStackTrace();


                        }

                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                swipeRefreshLayout.setRefreshing(false);
            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                //params.put("token", mPhone);

                return params;
            }

        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000,1,1));
        requestQueue.add(stringRequest);
        //VolleySingleton.getInstance(this).getRequestQueue().add(stringRequest);
    }



    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        loadRiwayatData();
        swipeRefreshLayout.setRefreshing(false);
    }
}
