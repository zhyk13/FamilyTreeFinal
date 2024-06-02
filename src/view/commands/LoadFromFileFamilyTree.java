package view.commands;

import view.ConsoleUI;

public class LoadFromFileFamilyTree extends Command{
    public LoadFromFileFamilyTree(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Загрузить проект из файла";
    }

    public void execute() {
        consoleUI.loadFromFileFamilyTree();
    }
}
