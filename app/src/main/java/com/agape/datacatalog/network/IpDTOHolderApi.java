package com.agape.datacatalog.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IpDTOHolderApi {
    @GET("json")
    public Call<IpDTO> getIpDevice();
}
