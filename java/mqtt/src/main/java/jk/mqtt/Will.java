package jk.mqtt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Will {
    private String topic;
    private byte[] content;
    private int qos;
    private boolean retained;
}
