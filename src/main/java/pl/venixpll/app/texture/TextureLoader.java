package pl.venixpll.app.texture;

import lombok.Data;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


@Data
public class TextureLoader {

    private Image background;
    private Image asteroid;
    private Image ship;
    private boolean ready = false;

    public void init() throws SlickException {
        asteroid = new Image("assets/asteroid.png");
        background = new Image("assets/background.png");
        ship = new Image("assets/ship.png");
        ready = true;
    }

}
