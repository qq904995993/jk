package instance.telecontroller.command;

public class PartyCommand implements Command {

    private Command[] commands;

    public PartyCommand(Command[] commands) {
        this.commands = commands;
    }

    public void execute() {
        if(commands == null || commands.length < 1) {
            return;
        }
        for (int i = 0; i < commands.length; i ++) {
            commands[i].execute();
        }
    }
}
