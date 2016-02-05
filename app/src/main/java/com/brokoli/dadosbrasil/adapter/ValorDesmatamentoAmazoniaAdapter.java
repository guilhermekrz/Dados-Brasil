package com.brokoli.dadosbrasil.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brokoli.dadosbrasil.R;
import com.brokoli.dadosbrasil.model.ValorDesmatamentoAmazonia;

import java.util.Locale;

import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

/**
 * Created by guilhermekrzisch on 1/28/16.
 */
public class ValorDesmatamentoAmazoniaAdapter extends RealmBasedRecyclerViewAdapter<ValorDesmatamentoAmazonia, ValorDesmatamentoAmazoniaAdapter.ViewHolder> {

    public ValorDesmatamentoAmazoniaAdapter(
            Context context,
            RealmResults<ValorDesmatamentoAmazonia> realmResults,
            boolean automaticUpdate,
            boolean animateIdType,
            String animateExtraColumnName) {
        super(context, realmResults, automaticUpdate, animateIdType, animateExtraColumnName);
    }

    public class ViewHolder extends RealmViewHolder {
        public RelativeLayout container;
        TextView vda_valor;
        TextView vda_ano;

        public ViewHolder(RelativeLayout container) {
            super(container);
            this.container = container;
            this.vda_valor = (TextView) container.findViewById(R.id.vda_valor);
            this.vda_ano = (TextView) container.findViewById(R.id.vda_ano);
        }
    }

    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int viewType) {
        View v = inflater.inflate(R.layout.valor_desmatamento_amazonia_row, viewGroup, false);
        return new ViewHolder((RelativeLayout) v);
    }

    @Override
    public void onBindRealmViewHolder(ViewHolder viewHolder, int position) {
        final ValorDesmatamentoAmazonia item = realmResults.get(position);
        viewHolder.vda_valor.setText(String.format(Locale.getDefault(), "%d", item.getValor()));
        viewHolder.vda_ano.setText(String.format(Locale.getDefault(), "%d", item.getAno()));
    }
}