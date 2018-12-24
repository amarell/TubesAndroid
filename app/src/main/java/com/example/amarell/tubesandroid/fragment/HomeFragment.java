package com.example.amarell.tubesandroid.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.amarell.tubesandroid.MainActivity;
import com.example.amarell.tubesandroid.R;
import com.example.amarell.tubesandroid.adapter.ListGuruAdapter;
import com.example.amarell.tubesandroid.model.DataGuru;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeFragment extends Fragment {
    private static final String TAG = "Home Fragment";
    private static String URL="http://192.168.43.64/temanbelajar/coba.php";
    private List<DataGuru> listGuru;
    private RecyclerView recyclerView;

    String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        guruRequest();



    }



    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        rootView.setTag(TAG);

        id = "2";

        listGuru = new ArrayList<>();

        recyclerView=rootView.findViewById(R.id.rv);


        return rootView;

    }

    private void guruRequest() {

        final String id2 = "2";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if (success.equals("1")){
                                for (int i=0; i<jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    DataGuru dataGuru = new DataGuru();

                                    dataGuru.setId(object.getString("id"));
                                    dataGuru.setNama(object.getString("nama"));
                                    dataGuru.setPendidikan(object.getString("pendidikan"));
                                    dataGuru.setNama_mapel(object.getString("nama_mapel"));
                                    dataGuru.setDeskripsi(object.getString("deskripsi"));
                                    dataGuru.setPengalaman(object.getString("pengalaman"));
                                    dataGuru.setPrestasi(object.getString("prestasi"));
                                    dataGuru.setFoto_profil(object.getString("foto_profil"));

                                    dataGuru.setId_mengajar(object.getString("id_mengajar"));
                                    dataGuru.setTarif(object.getString("tarif"));
                                    dataGuru.setTelpon(object.getString("telpon"));



                                    listGuru.add(dataGuru);
                                }

                            }else if (success.equals("0")){
                               Toast.makeText(getActivity(), "Belum Ada Guru", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Error "+e.toString(), Toast.LENGTH_SHORT).show();
                        }

                        setuprecyclerview(listGuru);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Gagal "+ error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id2);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
    private void setuprecyclerview(List<DataGuru> listGuru) {

        ListGuruAdapter myadapter = new ListGuruAdapter(getActivity(), listGuru);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(myadapter);
    }

}
