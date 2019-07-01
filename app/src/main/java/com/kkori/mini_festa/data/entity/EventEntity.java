package com.kkori.mini_festa.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventEntity {

    @SerializedName("eventId")
    public int eventId;
    @SerializedName("name")
    public String name;
    @SerializedName("eventSignature")
    public String eventSignature;
    @SerializedName("startDate")
    public String startDate;
    @SerializedName("tickets")
    public List<TicketEntity> tickets;
    @SerializedName("location")
    public LocationEntity location;
    @SerializedName("coverImage")
    public MetaDataEntity metaData;
    @SerializedName("hostOrganization")
    public HostOrganizationEntity hostOrganization;

}
