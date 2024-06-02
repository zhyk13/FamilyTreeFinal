package view.commands;

import view.ConsoleUI;

public class SaveToFileFamilyTree extends Command{
    public SaveToFileFamilyTree(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Записать проект в файл";
    }

    public void execute() {
        consoleUI.saveToFileFamilyTree();
    }
}
