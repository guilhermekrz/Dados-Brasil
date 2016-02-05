package com.brokoli.dadosbrasil.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brokoli.dadosbrasil.R;
import com.brokoli.dadosbrasil.adapter.ValorDesmatamentoAmazoniaAdapter;
import com.brokoli.dadosbrasil.event.DesmatamentoAmazoniaSyncedEvent;
import com.brokoli.dadosbrasil.model.ValorDesmatamentoAmazonia;
import com.brokoli.dadosbrasil.sync.DesmatamentoAmazoniaSync;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import de.greenrobot.event.EventBus;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class DesmatamentoAmazoniaFragment extends Fragment {
    @Bind(R.id.desmatamento_amazonia_recycler_view)
    RealmRecyclerView realmRecyclerView;
    private ValorDesmatamentoAmazoniaAdapter adapter;

    public DesmatamentoAmazoniaFragment() {
        // Required empty public constructor
    }

    public static DesmatamentoAmazoniaFragment newInstance() {
        return new DesmatamentoAmazoniaFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new DesmatamentoAmazoniaSync().sync();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_desmatamento_amazonia, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView() {
        RealmResults<ValorDesmatamentoAmazonia> valorDesmatamentoAmazonias = Realm.getDefaultInstance().where(ValorDesmatamentoAmazonia.class).findAll();
        valorDesmatamentoAmazonias.sort(ValorDesmatamentoAmazonia.ANO, Sort.DESCENDING);
        adapter = new ValorDesmatamentoAmazoniaAdapter(getContext(), valorDesmatamentoAmazonias, true, true, null);
        realmRecyclerView.setAdapter(adapter);
        realmRecyclerView.setOnRefreshListener(
                new RealmRecyclerView.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        new DesmatamentoAmazoniaSync().sync();
                    }
                }
        );
    }

    public void onEvent(DesmatamentoAmazoniaSyncedEvent event) {
        realmRecyclerView.setRefreshing(false);
    }
}
