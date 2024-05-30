package view.commands;

import view.ConsoleUI;

public class GetHumansList extends Command {
    public GetHumansList(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Получить список членов семейного дерева";
    }

    public void execute() {
        consoleUI.getHumansList();
    }
}
