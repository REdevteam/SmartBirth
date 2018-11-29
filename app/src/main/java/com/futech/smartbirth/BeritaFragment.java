package com.futech.smartbirth;


import android.os.Bundle;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class BeritaFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private List<BeritaDataModel> beritaDataModelList;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private RequestQueue queue;
    private SwipeRefreshLayout swipeRefreshLayout;




    public BeritaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        beritaDataModelList = new ArrayList<>();
        beritaDataModelList.add(new BeritaDataModel("Berita 1", "Lorem Ipsum ...", "23"));
        beritaDataModelList.add(new BeritaDataModel("Berita 2", "Lorem Ipsum ...", "24"));
        beritaDataModelList.add(new BeritaDataModel("Berita 3", "Lorem Ipsum ...", "25"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_berita, container, false);


        recyclerView = v.findViewById (R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        adapter = new BeritaAdapter(beritaDataModelList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        recyclerView = v.findViewById (R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        adapter = new BeritaAdapter(beritaDataModelList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = v.findViewById(R.id.swipe_container);

        //loadRiwayatData();

        return v;

    }

    public void loadRiwayatData(){

        swipeRefreshLayout.setRefreshing(true);
        RequestQueue queue = VolleySingleton.getInstance(getActivity().getApplicationContext()).getRequestQueue();
        String url = "https://www.tokosms.com/api/smartbirth/getlist.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                if(beritaDataModelList.size()!=0) {
                    beritaDataModelList.clear();
                }

                try {
                    // Parsing json array response
                    // loop through each json object
                    String jsonResponse = "";
                    for (int i = 0; i < response.length(); i++) {

                        JSONObject kontak = (JSONObject) response
                                .get(i);

                        String judul = kontak.getString("judul");
                        String deskripsi = kontak.getString("deskripsi");
                        String tanggal = kontak.getString("tanggal");
                        //contacts.add(new Contact(nama, nomor));

                        beritaDataModelList.add(new BeritaDataModel(judul, deskripsi, tanggal));

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
       // loadRiwayatData();
    }

}
