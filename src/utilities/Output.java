package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class Output {
    private final ArrayNode debugOutput;

    public Output(ObjectMapper objectMapper) {
        this.debugOutput = objectMapper.createArrayNode();
    }

    public ArrayNode getOutput() {
        return debugOutput;
    }
}
