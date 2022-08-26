package net.a6te.lazycoder.andropos.fragments;


import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.a6te.lazycoder.andropos.R;
import net.a6te.lazycoder.andropos.adapters.InvoiceAdapter;
import net.a6te.lazycoder.andropos.databinding.FragmentInvoiceBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class InvoiceFragment extends Fragment {

    private InvoiceAdapter adapter;

    public InvoiceFragment() {
        // Required empty public constructor
    }

    private FragmentInvoiceBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_invoice, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.invoiceTabLayout.addTab(binding.invoiceTabLayout.newTab().setText("Search"));
        binding.invoiceTabLayout.addTab(binding.invoiceTabLayout.newTab().setText("Details"));
        binding.invoiceTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        adapter = new InvoiceAdapter
                (getActivity().getSupportFragmentManager(), binding.invoiceTabLayout.getTabCount());
        binding.invoicePager.setAdapter(adapter);
        binding.invoicePager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.invoiceTabLayout));

        binding.invoiceTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.invoicePager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
