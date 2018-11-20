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
                // Fetching data from server
                loadRiwayatData();
            }
        });;



    }

    public void loadRiwayatData(){

        swipeRefreshLayout.setRefreshing(true);
        RequestQueue queue = VolleySingleton.getInstance(this).getRequestQueue();
        String url = "https://www.tokosms.com/api/smartbirth/getlist.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                if(dataModelList.size()!=0) {
                    dataModelList.clear();
                }

                try {
                    // Parsing json array response
                    // loop through each json object
                    String jsonResponse = "";
                    for (int i = 0; i < response.length(); i++) {

                        JSONObject kontak = (JSONObject) response
                                .get(i);

                        String nama = kontak.getString("tanggal");
                        String nomor = kontak.getString("berat");
                        //contacts.add(new Contact(nama, nomor));

                        dataModelList.add(new DataModel(nama,nomor));
                        Log.d("Msg",nama + nomor);

                    }
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
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
                //params.put("token","qwertyuiop");

                return params;
            }

        };
        queue.add(jsonArrayRequest);
    }



    @Override
    public void onRefresh() {
        loadRiwayatData();
    }
}
