package net.a6te.lazycoder.andropos.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import net.a6te.lazycoder.andropos.invoiceTab.DetailsInvoiceFragment;
import net.a6te.lazycoder.andropos.invoiceTab.SearchInvoiceTabFrg;

/**
 * Created by Programmer on 5/8/2017.
 */

public class InvoiceAdapter extends FragmentStatePagerAdapter{
    int mNumOfTabs;

    public InvoiceAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                SearchInvoiceTabFrg tab1 = new SearchInvoiceTabFrg();
                return tab1;
            case 1:
                DetailsInvoiceFragment tab2 = new DetailsInvoiceFragment();
                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}
