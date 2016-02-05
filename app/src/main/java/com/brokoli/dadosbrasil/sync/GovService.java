package com.brokoli.dadosbrasil.sync;

import com.brokoli.dadosbrasil.model.DesmatamentoAmazonia;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by guilhermekrzisch on 1/28/16.
 */
public interface GovService {
    @GET("api/1/serie/551.json")
    Call<DesmatamentoAmazonia> getDesmatamentoAnualDoBiomaAmazonia();
}
