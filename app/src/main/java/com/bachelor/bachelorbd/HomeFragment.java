package com.bachelor.bachelorbd;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment {

    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recycler_view = view.findViewById(R.id.recycler_view);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);


        progressBar.setVisibility(View.VISIBLE);
        String URL = "https://asifislamjhfgyu.000webhostapp.com/Apps/info.json";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {

                    for (int x=0; x<response.length(); x++){

                        progressBar.setVisibility(View.GONE);

                        JSONObject jsonObject = response.getJSONObject(x);
                        String TITLE = jsonObject.getString("title");
                        String LOCATION = jsonObject.getString("location");
                        String DATE = jsonObject.getString("date");
                        String AMOUNT = jsonObject.getString("amount");
                        String COVER_IMAGE = jsonObject.getString("coverimage");

                        hashMap = new HashMap<>();
                        hashMap.put("title", TITLE);
                        hashMap.put("location",LOCATION);
                        hashMap.put("date", DATE);
                        hashMap.put("amount", AMOUNT);
                        hashMap.put("coverimage", COVER_IMAGE);
                        arrayList.add(hashMap);

                        XAdapter adapter = new XAdapter();
                        recycler_view.setAdapter(adapter);
                        recycler_view.setLayoutManager( new LinearLayoutManager(getActivity()));
                        recycler_view.setLayoutManager(new GridLayoutManager(getActivity(), 2));




                    }


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);


        return view;
    }

    public class XAdapter extends  RecyclerView.Adapter<XAdapter.Holder>{

        public class Holder extends RecyclerView.ViewHolder{

            ImageView image_cover;
            TextView title,location,date,amount;

            public Holder(@NonNull View itemView) {
                super(itemView);

                image_cover = itemView.findViewById(R.id.image_cover);
                title = itemView.findViewById(R.id.title);
                location = itemView.findViewById(R.id.location);
                date = itemView.findViewById(R.id.date);
                amount = itemView.findViewById(R.id.amount);

            }
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.item_home, parent, false);

            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {

            HashMap<String, String> hashMap = arrayList.get(position);
            String TITLE = hashMap.get("title");
            String LOCATION = hashMap.get("location");
            String DATE = hashMap.get("date");
            String AMOUNT = hashMap.get("amount");
            String IMAGE = hashMap.get("coverimage");

          holder.title.setText(TITLE);
          holder.location.setText(LOCATION);
          holder.date.setText(DATE);
          holder.amount.setText(AMOUNT);

            Picasso.get().load(IMAGE)
                    .placeholder(R.drawable.loading)
                    .into(holder.image_cover);


        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }
}