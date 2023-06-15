package com.bachelor.bachelorbd;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Fragment_mother extends Fragment {

    ArrayList<HashMap<String, String >> arrayList = new ArrayList<>();
    HashMap<String, String > hashMap = new HashMap<>();
    RecyclerView recycler_view;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mother, container, false);

        recycler_view = view.findViewById(R.id.recycler_view_data);


        TextInputEditText ed_date = view.findViewById(R.id.ed_date);
        TextInputEditText ed_title = view.findViewById(R.id.ed_title);
        TextInputEditText ed_location = view.findViewById(R.id.ed_location);
        TextInputEditText ed_amount = view.findViewById(R.id.ed_amount);
        TextInputEditText ed_bedroom = view.findViewById(R.id.ed_bedroom);
        TextInputEditText ed_bathroom = view.findViewById(R.id.ed_bathroom);
        TextInputEditText ed_floor = view.findViewById(R.id.ed_floor);
        TextInputEditText ed_squarefeet = view.findViewById(R.id.ed_squarefeet);
        TextInputEditText ed_lift = view.findViewById(R.id.ed_lift);
        TextInputEditText ed_ownername = view.findViewById(R.id.ed_ownername);
        TextInputEditText ed_contract = view.findViewById(R.id.ed_contract);


        ProgressBar progressbar = view.findViewById(R.id.progressbar);
        AppCompatButton post_btn = view.findViewById(R.id.post_btn);
        CardView from_card = view.findViewById(R.id.from_card);

        loadData();

        progressbar.setVisibility(View.VISIBLE);

            post_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String TITLE = ed_title.getText().toString().trim();
                    String DATE = ed_date.getText().toString().trim();
                    String LOCATION = ed_location.getText().toString().trim();
                    String AMOUNT = ed_amount.getText().toString().trim();
                    String BEDROOM = ed_bedroom.getText().toString().trim();
                    String BATHROOM = ed_bathroom.getText().toString().trim();
                    String FLOOR = ed_floor.getText().toString().trim();
                    String SQUAREFEET = ed_squarefeet.getText().toString().trim();
                    String LIFT = ed_lift.getText().toString().trim();
                    String OWNER_NAME = ed_ownername.getText().toString().trim();
                    String CONTRACT = ed_contract.getText().toString().trim();

                    String URL = "https://asifislamjhfgyu.000webhostapp.com/Apps/connectdb.php?t="+TITLE+"&d="+DATE+"&l="+LOCATION+"&a="+AMOUNT+"&b="+BEDROOM+"&ba="+BATHROOM+"&f="+FLOOR+"&s="+SQUAREFEET+"&li="+LIFT+"&ow="+OWNER_NAME+"&c="+CONTRACT;

                    if (TITLE.equals("")){
                        Toast.makeText(getActivity(), "Title filed is empty", Toast.LENGTH_SHORT).show();
                    } else if (DATE.equals("")) {{
                        Toast.makeText(getActivity(), "Date filed is empty", Toast.LENGTH_SHORT).show();
                    }

                    } else if (LOCATION.equals("")) {
                        Toast.makeText(getActivity(), "location filed is empty", Toast.LENGTH_SHORT).show();
                    } else if (AMOUNT.equals("")) {
                        Toast.makeText(getActivity(), "Date amount is empty", Toast.LENGTH_SHORT).show();
                    } else if (BEDROOM.equals("")) {
                        Toast.makeText(getActivity(), "bedroom filed is empty", Toast.LENGTH_SHORT).show();
                    } else if (BATHROOM.equals("")) {
                        Toast.makeText(getActivity(), "bathroom filed is empty", Toast.LENGTH_SHORT).show();
                    } else if (FLOOR.equals("")) {
                        Toast.makeText(getActivity(), "floor filed is empty", Toast.LENGTH_SHORT).show();
                    } else if (SQUAREFEET.equals("")) {
                        Toast.makeText(getActivity(), "squarefeet filed is empty", Toast.LENGTH_SHORT).show();
                    } else if (LIFT.equals("")) {
                        Toast.makeText(getActivity(), "lift filed is empty", Toast.LENGTH_SHORT).show();
                    } else if (OWNER_NAME.equals("")) {
                        Toast.makeText(getActivity(), "owner name filed is empty", Toast.LENGTH_SHORT).show();
                    } else if (CONTRACT.equals("")) {
                        Toast.makeText(getActivity(), "contract filed is empty", Toast.LENGTH_SHORT).show();
                    }else {

                        RequestQueue queue = Volley.newRequestQueue(getActivity());

                        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        progressbar.setVisibility(View.GONE);

                                       new AlertDialog.Builder(getActivity())
                                               .setTitle("Data Response")
                                               .setMessage(response.toString())
                                               .show();

                                       loadData();


                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {


                            }
                        });

                        queue.add(stringRequest);

                    }


                }
            });



        return view;


    }

    public class Xadapter extends RecyclerView.Adapter<Xadapter.XHolder>{

        public class XHolder extends RecyclerView.ViewHolder{

            TextView title,location,date,amount;

            public XHolder(@NonNull View itemView) {
                super(itemView);


                title = itemView.findViewById(R.id.title);
                location = itemView.findViewById(R.id.location);
                date = itemView.findViewById(R.id.date);
                amount = itemView.findViewById(R.id.amount);


            }
        }

        @NonNull
        @Override
        public XHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.item_home, parent, false);

            return new XHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull XHolder holder, int position) {

            HashMap<String, String> hashMap = arrayList.get(position);
            String TITLE = hashMap.get("title");
            String DATE = hashMap.get("date");
            String LOCATION = hashMap.get("location");
            String AMOUNT = hashMap.get("amount");
            String BEDROOM = hashMap.get("bedroom");
            String FLOOR = hashMap.get("floor");
            String SQUARE_FEET = hashMap.get("squarefeet");
            String LIFT = hashMap.get("lift");
            String OWNER_NAME = hashMap.get("ownername");
            String CONTRACT = hashMap.get("contract");


            holder.title.setText(TITLE);
            holder.location.setText(LOCATION);
            holder.date.setText(DATE);
            holder.amount.setText(AMOUNT);

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }


    }

    public void loadData(){

        arrayList = new ArrayList<>();

        String DATA_LINK = "https://asifislamjhfgyu.000webhostapp.com/Apps/getdata.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, DATA_LINK, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                for (int x=0; x<response.length(); x++){

                    try {
                        JSONObject jsonObject = response.getJSONObject(x);
                        String TITLE = jsonObject.getString("title");
                        String DATE = jsonObject.getString("date");
                        String LOCATION = jsonObject.getString("location");
                        String AMOUNT = jsonObject.getString("amount");
                        String BEDROOM = jsonObject.getString("bedroom");
                        String BATHROOM = jsonObject.getString("bathroom");
                        String FLOOR = jsonObject.getString("floor");
                        String SQUARE_FEET = jsonObject.getString("squarefeet");
                        String LIFT = jsonObject.getString("lift");
                        String OWNER_NAME = jsonObject.getString("ownername");
                        String CONTRACT = jsonObject.getString("contract");

                        hashMap = new HashMap<>();
                        hashMap.put("title", TITLE);
                        hashMap.put("date", DATE);
                        hashMap.put("location", LOCATION);
                        hashMap.put("amount", AMOUNT);
                        hashMap.put("bedroom", BEDROOM);
                        hashMap.put("bathroom", BATHROOM);
                        hashMap.put("floor", FLOOR);
                        hashMap.put("squarefeet", SQUARE_FEET);
                        hashMap.put("lift", LIFT);
                        hashMap.put("ownername", OWNER_NAME);
                        hashMap.put("contract", CONTRACT);
                        arrayList.add(hashMap);

                        Xadapter xadapter = new Xadapter();
                        recycler_view.setAdapter(xadapter);
                        recycler_view.setLayoutManager( new LinearLayoutManager(getActivity()));
                        recycler_view.setLayoutManager(new GridLayoutManager(getActivity(), 2));



                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);

    }


}