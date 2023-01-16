import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import database.Action;
import database.Database;
import database.Recomandation;
import database.User;
import utilities.Output;
import website.CurrentDatabase;
import website.CurrentPage;
import website.CurrentUser;
import website.handlers.DatabaseActionsHandler;
import website.handlers.OnPageHandler;
import website.handlers.PageListHandler;

import java.io.File;
import java.io.IOException;

public final class Main {
    private Main() {
    }

    /**
     * @param args the command line arguments
     * @throws IOException if an I/O error occurs
     */
    public static void main(final String[] args) {
        String filePath   = args[0];
        String resultFile = args[1];



        ObjectMapper objectMapper = new ObjectMapper();


        Database database = null;
        try {
            database = objectMapper.readValue(new File(filePath), Database.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // start task
        CurrentPage     currentPage     = CurrentPage.getInstance();
        CurrentUser     currentUser     = CurrentUser.getInstance();
        CurrentDatabase currentDatabase = CurrentDatabase.getInstance();
        Output outputObject = Output.getInstance();
        PageListHandler pageListHandler = PageListHandler.getInstance();

        // init singleton instances values
        currentPage.init();
        currentUser.init();
        currentDatabase.init(database);
        outputObject.init();
        pageListHandler.init();


        // iterate over actions
        assert database != null;
        for (Action action : database.getActions()) {
            String type     = action.getType();

            if (type.equals("change page")) {
                pageListHandler.changePage(action);
            }
            if (type.equals("back")) {
                pageListHandler.undo();
            }
            if (type.equals("on page")) {
                OnPageHandler.handle(action);
            }
            if (type.equals("database")) {
                DatabaseActionsHandler.handle(action);
            }

        }

        // set the reccomended movies
        User user = currentUser.getUser();
        if (user != null && user.getCredentials().getAccountType().equals("premium")) {
            Recomandation recomandation = new Recomandation();
            outputObject.getOutput().addPOJO(recomandation);
        }

        // create output file
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        try {
            objectWriter.writeValue(new File(resultFile), outputObject.getOutput());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
