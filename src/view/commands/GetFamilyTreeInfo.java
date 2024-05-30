package view.commands;

import view.ConsoleUI;

public class GetFamilyTreeInfo extends Command{
    public GetFamilyTreeInfo(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Получить информацию о членах семейного дерева";
    }

    public void execute() {
        consoleUI.getFamilyTreeInfo();
    }
}
