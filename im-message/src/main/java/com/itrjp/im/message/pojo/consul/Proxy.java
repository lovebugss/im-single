/**
 * Copyright 2022 bejson.com
 */
package com.itrjp.im.message.pojo.consul;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Auto-generated: 2022-12-25 18:3:47
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Proxy {

    @JsonProperty("MeshGateway")
    private MeshGateway meshGateway;
    @JsonProperty("Expose")
    private Expose expose;

}