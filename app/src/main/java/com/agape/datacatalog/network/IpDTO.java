package com.agape.datacatalog.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IpDTO {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("query")
    @Expose
    private String query;

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getQuery() { return query; }
    public void setQuery(String query) { this.query = query; }

    @Override
    public String toString() {
        return "GeoDTO{" +
                "status='" + status + '\'' +
                ", query='" + query + '\'' +
                '}';
    }
}
