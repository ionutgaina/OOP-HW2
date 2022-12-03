import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Action;
import database.Database;
import website.CurrentPage;
import website.CurrentUser;
import website.Invoker;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = args[0];
        String resultFile = args[1];

        // Here we choose the test file
        if (!filePath.contains("1.")) {
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println(filePath);
        System.out.println(resultFile);

        Database database = objectMapper.readValue(new File(filePath), Database.class);

        // create array node for output
        ArrayNode output = objectMapper.createArrayNode();

        // start task
        CurrentPage currentPage = CurrentPage.getInstance();
        CurrentUser currentUser = CurrentUser.getInstance();
        Invoker invoker = new Invoker();

        for (Action action : database.getActions()) {
            invoker.command(action);
        }

        currentPage.clear();
        currentUser.clear();

        // create output file

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(resultFile), output);
    }
}
