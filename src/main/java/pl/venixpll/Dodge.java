package pl.venixpll;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import pl.venixpll.app.DodgeAPP;

public class Dodge {

    public static void main(String[] args) throws SlickException {
        final AppGameContainer container = new AppGameContainer(new DodgeAPP("Dodge"));
        container.setDisplayMode(450,600,false);
        container.setVSync(true);
        container.setTargetFrameRate(60);
        container.start();
    }

}
