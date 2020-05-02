package com.example.streetsavvy.ui.addProducts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.streetsavvy.R;
import com.example.streetsavvy.ui.vendor.VendorFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class AddProductsFragment extends DialogFragment {

    private AddProductsViewModel toolsViewModel;
    private EditText inputName, inputPrice;
    private static String productName, productPrice;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(AddProductsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_products, container, false);
        final TextView textView = root.findViewById(R.id.add_product_page);
        toolsViewModel.getText().observe(this, new Observer<String>() {
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
        inputName = view.findViewById(R.id.product_name);
        inputPrice = view.findViewById(R.id.product_price);

        FloatingActionButton addProductButton = view.findViewById(R.id.add_product_button);
        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productName = inputName.getText().toString();
                productPrice = inputPrice.getText().toString();

                Fragment vendorView = new VendorFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.nav_host_fragment, vendorView);
                transaction.addToBackStack(null);
                transaction.commit();
                Snackbar.make(view, "Switched to Vendor View", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static String getProductName() {
        return productName;
    }

    public static String getProductPrice() {
        return productPrice;
    }
}