public class MarkCommand extends Command {

    private int num;

    public MarkCommand(int num) {
        this.num = num;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.mark(num);
        ui.printMarkMessage(task);
        String toBeWritten = taskList.toString();
        storage.write(toBeWritten);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
