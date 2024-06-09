package view.commands;

import view.ConsoleUI;

public class AddInformation extends Command {
    public AddInformation(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Добавить информацию о существующем члене семейного дерева";
    }

    public void execute() {
        consoleUI.AddInformation();
    }
}
