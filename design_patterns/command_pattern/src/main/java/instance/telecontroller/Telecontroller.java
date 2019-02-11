package instance.telecontroller;

import instance.telecontroller.command.Command;
import instance.telecontroller.command.GarageDoorOpenCommand;
import instance.telecontroller.command.LightOnCommand;
import instance.telecontroller.command.PartyCommand;
import instance.telecontroller.receiver.GarageDoor;
import instance.telecontroller.receiver.Light;

public class Telecontroller {

    Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void buttonPressed() {
        if(command == null) {
            return;
        }
        command.execute();
    }

}

class main {
    public static void main(String[] args) {
        Telecontroller telecontroller = new Telecontroller();

        telecontroller.setCommand(new LightOnCommand(new Light("bathroom")));
        telecontroller.buttonPressed();

        telecontroller.setCommand(new GarageDoorOpenCommand(new GarageDoor()));
        telecontroller.buttonPressed();

        Command[] commands = {new LightOnCommand(new Light("room")), new GarageDoorOpenCommand(new GarageDoor())};
        telecontroller.setCommand(new PartyCommand(commands));
        telecontroller.buttonPressed();
    }
}
