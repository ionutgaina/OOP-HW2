package website;

import database.Action;
import website.commands.Command;

import java.util.LinkedList;

public class Invoker {
    private final LinkedList<Command> history = new LinkedList<>();

    public void command(Action action) {
    }
}
