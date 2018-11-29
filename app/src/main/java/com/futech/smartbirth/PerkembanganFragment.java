package com.futech.smartbirth;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


public class PerkembanganFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private List<DataModel> dataModelList;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private RequestQueue queue;
    private SwipeRefreshLayout swipeRefreshLayout;




    public PerkembanganFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        dataModelList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_perkembangan, container, false);




        recyclerView = v.findViewById (R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        adapter = new ListAdapter(dataModelList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        recyclerView = v.findViewById (R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        adapter = new ListAdapter(dataModelList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = v.findViewById(R.id.swipe_container);

        loadRiwayatData();

        return v;

    }

    public void loadRiwayatData(){

        swipeRefreshLayout.setRefreshing(true);
        RequestQueue queue = VolleySingleton.getInstance(getActivity().getApplicationContext()).getRequestQueue();
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
