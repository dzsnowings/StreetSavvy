package com.example.streetsavvy.ui.vendor;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class VendorFragment extends Fragment implements CustomDialog.OnInputListener {

    private VendorViewModel galleryViewModel;
    ArrayList<String> productNames = new ArrayList<>();
    ArrayList<String> productPrices = new ArrayList<>();
    ListView productNamesList, productPricesList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(VendorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_vendor, container, false);
        final TextView textView = root.findViewById(R.id.add_product_text);
        galleryViewModel.getText().observe(this, new Observer<String>() {
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

        productNamesList = (ListView) view.findViewById(R.id.productNames);
        productNames.add("Tomatoes");
        productNames.add("Apples");
        productNames.add("Bananas");
        productNames.add("Okra");
        if (AddProductsFragment.getProductName() != null) {
            productNames.add(AddProductsFragment.getProductName());
        }
        ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, productNames);
        productNamesList.setAdapter(adapter);

        productPricesList = (ListView) view.findViewById(R.id.productPrices);
        productPrices.add("$4.00/lb");
        productPrices.add("$5.00/lb");
        productPrices.add("$2.00/lb");
        productPrices.add("$6.00/lb");
        if (AddProductsFragment.getProductPrice() != null) {
            productPrices.add(AddProductsFragment.getProductPrice());
        }
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, productPrices);
        productPricesList.setAdapter(adapter);

        FloatingActionButton fab = view.findViewById(R.id.add_new_product);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment addProductsView = new AddProductsFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.nav_host_fragment, addProductsView);
                transaction.addToBackStack(null);
                transaction.commit();
                Snackbar.make(view, "Switched to Add Products View", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton changeLocationButton = view.findViewById(R.id.change_location);
        changeLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialog dialog = new CustomDialog();
                dialog.show(getFragmentManager(), "CustomDialog");
            }
        });
    }

    @Override
    public void sendInput(String input) {

    }
}