package com.brokoli.dadosbrasil.sync;

import com.brokoli.dadosbrasil.event.DesmatamentoAmazoniaSyncedEvent;
import com.brokoli.dadosbrasil.model.DesmatamentoAmazonia;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.greenrobot.event.EventBus;
import io.realm.Realm;
import io.realm.RealmObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * Created by guilhermekrzisch on 1/28/16.
 */
public class DesmatamentoAmazoniaSync {
    public void sync() {
        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
               })
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.pgi.gov.br")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GovService govService = retrofit.create(GovService.class);
        Call<DesmatamentoAmazonia> listCall = govService.getDesmatamentoAnualDoBiomaAmazonia();
        listCall.enqueue(new Callback<DesmatamentoAmazonia>() {
            @Override
            public void onResponse(final Response<DesmatamentoAmazonia> response) {
                Timber.d("Received DesmatamentoAmazonia data: " + response.message());
                Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealmOrUpdate(response.body().getValores());
                    }
                }, new Realm.Transaction.Callback() {
                    @Override
                    public void onSuccess() {
                        super.onSuccess();
                        EventBus.getDefault().post(new DesmatamentoAmazoniaSyncedEvent());
                    }

                    @Override
                    public void onError(Exception e) {
                        super.onError(e);
                        EventBus.getDefault().post(new DesmatamentoAmazoniaSyncedEvent());
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {
                Timber.w("Error when getting DesmatamentoAmazonia data: " + t.getLocalizedMessage());
                EventBus.getDefault().post(new DesmatamentoAmazoniaSyncedEvent());
            }
        });
    }
}
