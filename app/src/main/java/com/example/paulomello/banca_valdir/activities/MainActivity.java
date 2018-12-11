package com.example.paulomello.banca_valdir.activities;

import android.content.Intent;
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

import com.example.paulomello.banca_valdir.adapters.ClienteDialogFragment;
import com.example.paulomello.banca_valdir.adapters.ClientePurchaseDialogFragment;
import com.example.paulomello.banca_valdir.adapters.FornecedorDialogFragment;
import com.example.paulomello.banca_valdir.adapters.FornecedorPurchaseDialogFragment;
import com.example.paulomello.banca_valdir.adapters.ViewPagerAdapter;
import com.example.paulomello.banca_valdir.fragments.ClienteFragment;
import com.example.paulomello.banca_valdir.fragments.FornecedorFragment;
import com.example.paulomello.banca_valdir.models.Cliente;
import com.example.paulomello.banca_valdir.models.Compra;
import com.example.paulomello.banca_valdir.models.Fornecedor;
import com.example.paulomello.banca_valdir.models.Venda;
import com.example.paulomello.banca_valdir.providers.CompraDAO;
import com.example.paulomello.banca_valdir.providers.VendaDAO;
import com.example.paulomello.banca_valdir.R;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ClienteDialogFragment.NoticeDialogListener,FornecedorDialogFragment.NoticeDialogListener,NavigationView.OnNavigationItemSelectedListener,ClientePurchaseDialogFragment.NoticeDialogListener,FornecedorPurchaseDialogFragment.NoticeDialogListener {

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

    public ViewPagerAdapter viewPagerAdapter;
    private VendaDAO vendaDAO = new VendaDAO(this);
    private CompraDAO compraDAO = new CompraDAO(this);

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
        try {
            vendaDAO.create(venda);
            Toast.makeText(this,"Venda adicionada com sucesso!",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDialogPositiveClick(Compra compra, int position) {
        try {
            compraDAO.create(compra);
            Toast.makeText(this,"Compra adicionada com sucesso!",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                drawerLayout.closeDrawer(GravityCompat.START);drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.menu_dinheiro:
                Intent intent = new Intent(MainActivity.this,PurchaseActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
}
