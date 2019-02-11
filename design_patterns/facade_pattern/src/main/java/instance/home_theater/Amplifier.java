package instance.home_theater;

public class Amplifier {

    public void on() {
        System.out.println("Amplifier on");
    }

    public void setDvdPlayer(DvdPlayer dvdPlayer) {
        System.out.println("Amplifier set DvdPlayer");
    }

    public void setVolumn(int volumn) {
        System.out.println("Amplifier set volumn " + volumn);
    }

    public void off() {
        System.out.println("Amplifier off");
    }

}
