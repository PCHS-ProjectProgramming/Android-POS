package net.a6te.lazycoder.andropos.sellTabes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.appcompat.app.AlertDialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import net.a6te.lazycoder.andropos.R;
import net.a6te.lazycoder.andropos.adapters.ProductListAdapter;
import net.a6te.lazycoder.andropos.databinding.FragmentListProductFrgBinding;
import net.a6te.lazycoder.andropos.modelClass.ProductListModel;

import java.util.ArrayList;

public class ListProductFrg extends Fragment {

    private FragmentListProductFrgBinding binder;
    private ProductListAdapter adapter;
    private ArrayList<ProductListModel> products;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binder = DataBindingUtil.inflate(inflater,R.layout.fragment_list_product_frg, container, false);
        products = new ArrayList<>();

        binder = DataBindingUtil.inflate(inflater,R.layout.fragment_list_product_frg, container, false);
        return binder.getRoot();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver
                ,new IntentFilter("selectedProduct"));
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(messageReceiver
                ,new IntentFilter("clearAll"));

        updateListView();
        binder.productListLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setMessage("Are sure you want to remove this item?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        products.remove(position);
                        sendBroadCastToReceiptPage(position);
                        updateListView();
                    }
                });
                dialog.setNegativeButton("No",null);

                dialog.show();
                return true;
            }
        });
    }

    public void updateListView(){
        adapter = new ProductListAdapter(getActivity(),products);
        binder.productListLv.setAdapter(adapter);
    }

    private void sendBroadCastToReceiptPage(int position) {
        Intent intent = new Intent("removeProduct");
        intent.putExtra("position",position);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }

    BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ProductListModel temp = (ProductListModel) intent.getSerializableExtra("product");
            products.add(temp);
            updateListView();
        }
    };

    @Override
    public void onDestroy() {
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }
    BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean flag = intent.getBooleanExtra("flag",false);
            if (flag){
                products = new ArrayList<>();
                updateListView();
            }
        }
    };

}
