package pl.venixpll.app.render;

import lombok.Data;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import pl.venixpll.app.DodgeAPP;

import java.awt.*;

@Data
public class RenderPlayer implements IRender {

    private int posX;
    private int posY;

    private Rectangle boundbox;

    public RenderPlayer(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        boundbox = new Rectangle(posX,posY,80,100);
    }

    @Override
    public void render(Graphics graphics, GameContainer container, DodgeAPP app) {
        app.getLoader().getShip().draw(posX,posY,80,100);
    }

    @Override
    public void tick(GameContainer container, DodgeAPP app) {
        app.getLoader().getShip().setCenterOfRotation(40,50);
        boolean restore = true;
        boolean mright = false,mleft = false;
        int gs = 2 * app.getGameSpeed();
        if(Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
            if(posX == 0 || posX < 0){
                posX = 0;
                return;
            }
            mleft = true;
            posX -= gs;
            restore = false;
            if(app.getLoader().getShip().getRotation() > -27) {
                app.getLoader().getShip().rotate(-3);
            }
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
            if(posX + 80 == container.getWidth()|| posX + 80 > container.getWidth()){
                posX = container.getWidth() - 80;
                return;
            }
            mright = true;
            posX += gs;
            restore = false;
            if(app.getLoader().getShip().getRotation() < 27) {
                app.getLoader().getShip().rotate(+3);
            }
        }

        if(mleft) {
            boundbox.setBounds(posX + 22, posY + 5, 35, 80);
        }else if(mright){
            boundbox.setBounds(posX + 14, posY + 7, 45, 80);
        }else{
            boundbox.setBounds(posX + 12, posY + 5, 50, 80);
        }
        if(restore){
            if(app.getLoader().getShip().getRotation() > 0){
                app.getLoader().getShip().rotate(-3);
            }
            if(app.getLoader().getShip().getRotation() < 0){
                app.getLoader().getShip().rotate(3);
            }
        }
    }

}
