package pl.venixpll.app.render;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import pl.venixpll.app.DodgeAPP;

public interface IRender {

    void render(Graphics graphics, GameContainer container, DodgeAPP app);

    void tick(GameContainer container,DodgeAPP app);

}
