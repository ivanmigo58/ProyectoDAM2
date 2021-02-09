package com.example.proyectoapp.TabbedPrincipal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.widget.PullRefreshLayout;
import com.example.proyectoapp.R;
import com.example.proyectoapp.TabbedPrincipal.EventosFragment;
import com.example.proyectoapp.TabbedPrincipal.FavoritosFragment;
import com.example.proyectoapp.TabbedPrincipal.LigasFragment;
import com.example.proyectoapp.databinding.FragmentTabbedGraphBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class TabbedGraphFragment extends Fragment {

    private FragmentTabbedGraphBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("FutMundo");

        // Inflate the layout for this fragment
        return (binding = FragmentTabbedGraphBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new LigasFragment();
                    case 1:
                    default:
                        return new EventosFragment();
                    case 2:
                        return new FavoritosFragment();

                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
        binding.tabLayout.setViewPager(binding.viewPager, 1);

        PullRefreshLayout layout = (PullRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);

        // listen refresh event
        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // start refresh
                layout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        layout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }
}