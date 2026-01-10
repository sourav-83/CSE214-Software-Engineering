interface Command {
    void execute();
}

class TurnOnCommand implements Command {
    private Device device;

    public TurnOnCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOn();
    }
}

class TurnOffCommand implements Command {
    private Device device;

    public TurnOffCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOff();
    }
}

class AdjustVolumeCommand implements Command {
    private Stereo stereo;

    public AdjustVolumeCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.adjustVolume();
    }
}

class ChangeChannelCommand implements Command {
    private TV tv;

    public ChangeChannelCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.changeChannel();
    }
}

interface Device {
    void turnOn();
    void turnOff();
}

class TV implements Device {
    public void turnOn() {
        System.out.println("TV is now on");
    }

    public void turnOff() {
        System.out.println("TV is now off");
    }

    public void changeChannel() {
        System.out.println("Channel changed");
    }
}

class Stereo implements Device {
    public void turnOn() {
        System.out.println("Stereo is now on");
    }

    public void turnOff() {
        System.out.println("Stereo is now off");
    }

    public void adjustVolume() {
        System.out.println("Volume adjusted");
    }
}

class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

public class commandPattern {
    public static void main(String[] args) {
        TV tv = new TV();
        Stereo stereo = new Stereo();

        Command turnOnTVCommand = new TurnOnCommand(tv);
        Command turnOffTVCommand = new TurnOffCommand(tv);
        Command adjustVolumeStereoCommand = new AdjustVolumeCommand(stereo);
        Command changeChannelTVCommand = new ChangeChannelCommand(tv);

        RemoteControl remote = new RemoteControl();

        remote.setCommand(turnOnTVCommand);
        remote.pressButton(); 

        remote.setCommand(adjustVolumeStereoCommand);
        remote.pressButton(); 

        remote.setCommand(changeChannelTVCommand);
        remote.pressButton(); 

        remote.setCommand(turnOffTVCommand);
        remote.pressButton(); 
    }
}