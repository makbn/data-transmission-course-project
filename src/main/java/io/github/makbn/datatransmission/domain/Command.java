package io.github.makbn.datatransmission.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Command {
    private String port;
    private String type;
    private String device;
    private String cmd;
}
