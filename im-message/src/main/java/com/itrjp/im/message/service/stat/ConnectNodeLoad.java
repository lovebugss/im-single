package com.itrjp.im.message.service.stat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/31 09:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConnectNodeLoad implements Comparable<ConnectNodeLoad> {
    private String id;
    private String ip;
    private int port;
    private int wsPort;
    private int load = 0;

    @Override
    public String toString() {
        return "ConnectNodeLoad{" +
                "id='" + id + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", wsPort=" + wsPort +
                ", load=" + load +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConnectNodeLoad that = (ConnectNodeLoad) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(ConnectNodeLoad o) {
        return this.load - o.load;
    }
}
