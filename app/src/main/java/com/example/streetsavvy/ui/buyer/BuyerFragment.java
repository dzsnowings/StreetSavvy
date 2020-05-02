package com.example.streetsavvy.ui.buyer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.streetsavvy.R;
import com.example.streetsavvy.ui.addProducts.AddProductsFragment;
import com.example.streetsavvy.ui.map.MapFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class BuyerFragment extends Fragment {

    private BuyerViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(BuyerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_buyer, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView productsList = (ListView) view.findViewById(R.id.products);
        ArrayList<String> products = new ArrayList<>();
        products.add("Tomatoes");
        products.add("Apples");
        products.add("Bananas");
        products.add("Okra");
        if (AddProductsFragment.getProductName() != null) {
            products.add(AddProductsFragment.getProductName());
        }
        ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, products);
        productsList.setAdapter(adapter);

        ListView pricesList = (ListView) view.findViewById(R.id.prices);
        ArrayList<String> prices = new ArrayList<>();
        prices.add("$4.00/lb");
        prices.add("$5.00/lb");
        prices.add("$2.00/lb");
        prices.add("$6.00/lb");
        if (AddProductsFragment.getProductPrice() != null) {
            prices.add(AddProductsFragment.getProductPrice());
        }
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, prices);
        pricesList.setAdapter(adapter);

        ListView distancesList = (ListView) view.findViewById(R.id.distances);
        ArrayList<String> distances = new ArrayList<>();
        distances.add("0.2 mi");
        distances.add("0.1 mi");
        distances.add("1.0 mi");
        distances.add("1.5 mi");
        if (AddProductsFragment.getProductPrice() != null) {
            distances.add("0.4 mi");
        }
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, distances);
        distancesList.setAdapter(adapter);

        FloatingActionButton fab = view.findViewById(R.id.map_nav);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment mapView = new MapFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.nav_host_fragment, mapView);
                transaction.addToBackStack(null);
                transaction.commit();
                Snackbar.make(view, "Switched to Map View", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}