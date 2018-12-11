package com.example.paulomello.banca_valdir.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.paulomello.banca_valdir.adapters.ClienteEditDialogFragment;
import com.example.paulomello.banca_valdir.adapters.ClientePayDialogFragment;
import com.example.paulomello.banca_valdir.adapters.FornecedorEditDialogFragment;
import com.example.paulomello.banca_valdir.adapters.FornecedorPayDialogFragment;
import com.example.paulomello.banca_valdir.adapters.ViewPagerAdapter;
import com.example.paulomello.banca_valdir.fragments.ClientePurchaseFragment;
import com.example.paulomello.banca_valdir.fragments.FornecedorPurchaseFragment;
import com.example.paulomello.banca_valdir.models.Compra;
import com.example.paulomello.banca_valdir.models.Venda;
import com.example.paulomello.banca_valdir.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PurchaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,ClientePayDialogFragment.NoticeDialogListener,ClienteEditDialogFragment.NoticeDialogListener,FornecedorPayDialogFragment.NoticeDialogListener,FornecedorEditDialogFragment.NoticeDialogListener {

    @BindView(R.id.tab_layout_purchase)
    TabLayout tabLayout;

    @BindView(R.id.view_pager_purchase)
    ViewPager viewPager;

    @BindView(R.id.toolbar_purchase)
    Toolbar toolbar;

    @BindView(R.id.drawer_purchase)
    DrawerLayout drawerLayout;

    public ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        ButterKnife.bind(this);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),viewPager,this);

        viewPagerAdapter.addFragment(new ClientePurchaseFragment(), "Vendas");
        viewPagerAdapter.addFragment(new FornecedorPurchaseFragment(), "Compras");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        setSupportActionBar(toolbar);

        setNavigationViewListener();
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.menu_cadastro:
                Intent intent = new Intent(PurchaseActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_dinheiro:
                drawerLayout.closeDrawer(GravityCompat.START);drawerLayout.closeDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = findViewById(R.id.nav_view_purchase);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onDialogPositiveClick(Venda venda, int position) {
        ClientePurchaseFragment.updateVendas(venda,position);
    }

    @Override
    public void onDialogPositiveClick(Compra compra, int position) {
        FornecedorPurchaseFragment.updateCompras(compra,position);
    }
}