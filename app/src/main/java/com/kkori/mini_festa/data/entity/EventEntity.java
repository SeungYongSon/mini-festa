package com.kkori.mini_festa.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventEntity {

    @SerializedName("eventId")
    private int eventId;
    @SerializedName("name")
    private String name;
    @SerializedName("eventSignature")
    private String eventSignature;
    @SerializedName("startDate")
    private String startDate;
    @SerializedName("endDate")
    private String endDate;
    @SerializedName("tickets")
    private List<TicketEntity> tickets;
    @SerializedName("location")
    private LocationEntity location;
    @SerializedName("metadata")
    private MetaDataEntity metaData;
    @SerializedName("hostOrganization")
    private HostOrganizationEntity hostOrganization;

    public int getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public String getEventSignature() {
        return eventSignature;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public List<TicketEntity> getTickets() {
        return tickets;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public MetaDataEntity getMetaData() {
        return metaData;
    }

    public HostOrganizationEntity getHostOrganization() {
        return hostOrganization;
    }

}
