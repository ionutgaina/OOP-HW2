package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public final class Output {

    // singleton
    private static Output instance = null;
    private ArrayNode debugOutput;
    private final ObjectMapper mapper = new ObjectMapper();

    private Output() {
        debugOutput = mapper.createArrayNode();
    }

    /**
     * @return the instance of Output ( singleton )
     */
    public static Output getInstance() {
        if (instance == null) {
            instance = new Output();
        }
        return instance;
    }

    /**
     *  reset the output to null
     */
    public void init() {
        this.debugOutput = mapper.createArrayNode();
    }

    public ArrayNode getOutput() {
        return debugOutput;
    }
}
