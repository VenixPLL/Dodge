package pl.venixpll.app.render;

import lombok.Data;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import pl.venixpll.app.DodgeAPP;

import java.awt.*;
import java.util.Random;

@Data
public class RenderAsteroid implements IRender {

    private int posX;
    private int posY;
    private Image asteroid;

    private Rectangle boundbox;

    public RenderAsteroid(int posX,int posY,DodgeAPP app){
        this.posX = posX;
        this.posY = posY;
        asteroid = app.getLoader().getAsteroid();
        boundbox = new Rectangle(posX,posY,50,50);
    }

    @Override
    public void render(Graphics graphics, GameContainer container, DodgeAPP app) {
        asteroid.draw(posX,posY,50,50);
    }

    @Override
    public void tick(GameContainer container, DodgeAPP app) {
        posY += app.getGameSpeed() * 2;
        this.boundbox.setBounds(posX + 13,posY + 15,25,25);
    }
}
