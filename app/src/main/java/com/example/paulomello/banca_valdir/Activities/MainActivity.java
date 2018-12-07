package com.example.paulomello.banca_valdir.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.paulomello.banca_valdir.Adapters.ClienteDialogFragment;
import com.example.paulomello.banca_valdir.Adapters.ClientePurchaseDialogFragment;
import com.example.paulomello.banca_valdir.Adapters.FornecedorDialogFragment;
import com.example.paulomello.banca_valdir.Adapters.ViewPagerAdapter;
import com.example.paulomello.banca_valdir.Fragments.ClienteFragment;
import com.example.paulomello.banca_valdir.Fragments.ClientePurchaseFragment;
import com.example.paulomello.banca_valdir.Fragments.FornecedorFragment;
import com.example.paulomello.banca_valdir.Fragments.FornecedorPurchaseFragment;
import com.example.paulomello.banca_valdir.Models.Cliente;
import com.example.paulomello.banca_valdir.Models.Fornecedor;
import com.example.paulomello.banca_valdir.Models.Venda;
import com.example.paulomello.banca_valdir.R;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ClienteDialogFragment.NoticeDialogListener,FornecedorDialogFragment.NoticeDialogListener,NavigationView.OnNavigationItemSelectedListener,ClientePurchaseDialogFragment.NoticeDialogListener {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer)
    DrawerLayout drawerLayout;

    @BindView(R.id.add_action_button)
    FloatingActionButton fab;

    public int teste = 0;
    public ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),viewPager,this);

        viewPagerAdapter.addFragment(new ClienteFragment(), "Clientes");
        viewPagerAdapter.addFragment(new FornecedorFragment(), "Fornecedores");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        setSupportActionBar(toolbar);
        setNavigationViewListener();
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();
    }

    @OnClick(R.id.add_action_button)
    public void addFragment(){
        if(viewPager.getCurrentItem()==0)
        {
            showClienteDialogFragment(ClienteDialogFragment.CREATE_TAG, -1);
        }
        else if(viewPager.getCurrentItem()==1)
        {
            showFornecedorDialogFragment(FornecedorDialogFragment.CREATE_TAG,-1);
        }
    }

    private void showFornecedorDialogFragment(String tag, int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FornecedorDialogFragment dialog;
        if (tag.equals(FornecedorDialogFragment.CREATE_TAG)) {
            dialog = FornecedorDialogFragment.newInstance(getString(R.string.new_fornecedor), getString(R.string.add));
            dialog.setCancelable(false);
        } else {
            dialog = FornecedorDialogFragment.newInstance(getString(R.string.edit_fornecedor), getString(R.string.update), FornecedorFragment.fornecedores.get(position), position);
        }
        dialog.show(fragmentManager, tag);
    }

    private void showClienteDialogFragment(String tag, int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ClienteDialogFragment dialog;
        if (tag.equals(ClienteDialogFragment.CREATE_TAG)) {
            dialog = ClienteDialogFragment.newInstance(getString(R.string.new_client), getString(R.string.add));
            dialog.setCancelable(false);
        } else {
            dialog = ClienteDialogFragment.newInstance(getString(R.string.edit_client), getString(R.string.update), ClienteFragment.clientes.get(position), position);
        }
        dialog.show(fragmentManager, tag);
    }

    @Override
    public void onDialogPositiveClick(Venda venda, int position) {
        Toast.makeText(this, "DEU CERTO", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDialogPositiveClick(Cliente cliente, int position) {
        ClienteFragment.updateClientes(cliente,position);
    }

    @Override
    public void onDialogPositiveClick(Fornecedor fornecedor, int position) {
        FornecedorFragment.updateFornecedores(fornecedor,position);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.menu_cadastro:
                 if(teste==1)
                 {
                     viewPagerAdapter.removeFragment(1);
                     viewPagerAdapter.removeFragment(0);
                     viewPagerAdapter.addFragment(new ClienteFragment(),"Clientes");
                     viewPagerAdapter.addFragment(new FornecedorFragment(), "Fornecedores");
                     fab.show();
                     teste = 0;
                 }
                break;
            case R.id.menu_dinheiro:
                if(teste==0)
                {
                    viewPagerAdapter.removeFragment(1);
                    viewPagerAdapter.removeFragment(0);
                    viewPagerAdapter.addFragment(new ClientePurchaseFragment(),"Vendas");
                    viewPagerAdapter.addFragment(new FornecedorPurchaseFragment(), "Compras");
                    fab.hide();
                    teste = 1;
                }
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

}
