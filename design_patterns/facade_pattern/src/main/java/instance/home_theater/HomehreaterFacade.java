package instance.home_theater;

public class HomehreaterFacade {

    private Amplifier amplifier;
    private DvdPlayer dvdPlayer;
    private PopcornPopper popcornPopper;
    private Projector projector;
    private Screen screen;
    private ThreaterLights threaterLights;
    private Tuner tuner;

    public HomehreaterFacade(Amplifier amplifier, DvdPlayer dvdPlayer, PopcornPopper popcornPopper,
                             Projector projector, Screen screen, ThreaterLights threaterLights, Tuner tuner) {
        this.amplifier = amplifier;
        this.dvdPlayer = dvdPlayer;
        this.popcornPopper = popcornPopper;
        this.projector = projector;
        this.screen = screen;
        this.threaterLights = threaterLights;
        this.tuner = tuner;
    }

    public void watchMovie(String movie) {
        popcornPopper.on();
        popcornPopper.pop();
        threaterLights.dim();
        screen.down();
        projector.on();
        projector.wideScreenMove();
        amplifier.on();
        amplifier.setDvdPlayer(dvdPlayer);
        amplifier.setVolumn(5);
        dvdPlayer.on();
        dvdPlayer.play(movie);
    }

    public void endMovie() {
        popcornPopper.off();
        threaterLights.on();
        screen.up();
        projector.off();
        amplifier.off();
        dvdPlayer.stop();
        dvdPlayer.enject();
        dvdPlayer.off();
    }

    public static void main(String[] args) {
        HomehreaterFacade homehreaterFacade = new HomehreaterFacade(new Amplifier(), new DvdPlayer(),
                new PopcornPopper(), new Projector(), new Screen(), new ThreaterLights(), new Tuner());
        homehreaterFacade.watchMovie("ET");
        System.out.println();
        homehreaterFacade.endMovie();
    }

}
