/**
 * Copyright 2022 bejson.com
 */
package com.itrjp.im.message.pojo.consul;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2022-12-25 18:3:47
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Checks {

    @JsonProperty("Node")
    private String node;
    @JsonProperty("CheckID")
    private String checkID;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("Notes")
    private String notes;
    @JsonProperty("Output")
    private String output;
    @JsonProperty("ServiceID")
    private String serviceID;
    @JsonProperty("ServiceName")
    private String serviceName;
    @JsonProperty("ServiceTags")
    private List<String> serviceTags;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Definition")
    private Definition definition;
    @JsonProperty("CreateIndex")
    private int createIndex;
    @JsonProperty("ModifyIndex")
    private int modifyIndex;

}