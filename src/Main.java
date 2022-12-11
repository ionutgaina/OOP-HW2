import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import database.Action;
import database.Database;
import utilities.Output;
import website.CurrentDatabase;
import website.CurrentPage;
import website.CurrentUser;
import website.handlers.ChangePageHandler;
import website.handlers.OnPageHandler;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String filePath = args[0];
        String resultFile = args[1];

        // Here we choose the test file
        if (!filePath.contains("3.")) {
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();


        Database database = null;
        try {
            database = objectMapper.readValue(new File(filePath), Database.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // create array node for output
        Output outputObject = new Output(objectMapper);

        // start task
        CurrentPage currentPage = CurrentPage.getInstance();
        CurrentUser currentUser = CurrentUser.getInstance();
        CurrentDatabase currentDatabase = CurrentDatabase.getInstance();

        // init singleton instances values
        currentPage.init();
        currentUser.init();
        currentDatabase.init(database);

        // iterate over actions
        for (Action action : database.getActions()) {
            String type = action.getType();
            String nextPage = action.getPage();

            if (type.equals("change page")) {
                ChangePageHandler.handle(nextPage, outputObject);
            }
            if (type.equals("on page")) {
                OnPageHandler.handle(action, outputObject);
            }
        }

        // create output file
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        try {
            objectWriter.writeValue(new File(resultFile), outputObject.getOutput());
            objectWriter.writeValue(new File("out.txt"), outputObject.getOutput());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
