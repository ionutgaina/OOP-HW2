package website.handlers;

import database.Action;
import database.Database;
import utilities.Output;
import website.CurrentDatabase;

public class DatabaseActionsHandler {
    private DatabaseActionsHandler() {
    }

    /**
     * handle the database action
     *
     * @param action action to be handled
     */
    public static void handle(final Action action) {
        Database database = CurrentDatabase.getInstance().getDatabase();
        String feature = action.getFeature();

        boolean noError = switch (feature) {
            case "delete" -> database.delete(action.getDeletedMovie());
            case "add" -> database.add(action.getAddedMovie());
            default -> false;
            };

        // Error handling
        if (!noError) {
//            Output.getInstance().getOutput()
//                    .add("database " + feature);
            Output.getInstance().getOutput()
                    .addPOJO(new ErrorHandler(false));
        }
    }
}
