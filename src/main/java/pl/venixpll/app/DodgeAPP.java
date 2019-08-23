package pl.venixpll.app;

import lombok.Data;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import pl.venixpll.app.render.RenderAsteroid;
import pl.venixpll.app.render.RenderPlayer;
import pl.venixpll.app.texture.TextureLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

@Data
public class DodgeAPP extends BasicGame {

    public DodgeAPP(String title) {
        super(title);
    }

    private RenderPlayer player;
    private TextureLoader loader;

    private long score = 0;
    private int gameSpeed = 2;
    private boolean paused = true;
    private boolean gameOver = true;

    private final Random random = new Random();

    private GameContainer container;

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        container = gameContainer;
        loader = new TextureLoader();
        loader.init();
        backgroundSegments.add(0);
        backgroundSegments.add(gameContainer.getHeight());
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        if(loader.isReady() && !paused){
            if(player != null) {
                player.tick(gameContainer, this);
            }
            asteroids.forEach(a -> a.tick(gameContainer,this));
            int index = 0;
            for(int k : backgroundSegments){
                k += gameSpeed;
                backgroundSegments.set(index,k);
                index++;
            }
            backgroundPos += gameSpeed;
            score = backgroundPos;
            bp += gameSpeed;
            s += gameSpeed;

            asteroids.forEach(a -> {
                if (a.getBoundbox().intersects(player.getBoundbox())) {
                    this.setPaused(true);
                    this.setGameOver(true);
                }
            });
        }
    }

    private int backgroundPos = 0;

    private List<Integer> backgroundSegments = new CopyOnWriteArrayList<>();

    private int bp = 0;
    private int s = 0;

    private List<RenderAsteroid> asteroids = new CopyOnWriteArrayList<>();

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        if(loader.isReady()){

            if(s > 5000){
                s = 0;
                if(gameSpeed != 10 || !(gameSpeed > 10)) {
                    gameSpeed++;
                }
            }

            if(bp > 575){
                bp = 0;
                System.out.println("Added segment");
                final int amount = random.nextInt(8) + 3;
                IntStream.range(0,amount).forEach(i -> {
                    asteroids.add(new RenderAsteroid(random.nextInt(gameContainer.getWidth() - 15) - 30, -((random.nextInt(500) + 300)- 300),this));
                });
                backgroundSegments.add(0);
            }

            for(RenderAsteroid a : asteroids){
                if(a.getPosY() > 1000){
                    asteroids.remove(a);
                }
            }

            for (int i : backgroundSegments) {
                if (i > 2500) {
                    System.out.println("Removed segment");
                    backgroundSegments.remove(0);
                }
                loader.getBackground().draw(0, i - 600, gameContainer.getWidth(), 600);
            }

            asteroids.forEach(a -> a.render(graphics,gameContainer,this));

            if(player != null) {
                player.render(graphics, gameContainer, this);
            }
            final String s = "Score: " + score;
            graphics.drawString(s,10,22);
            if(paused && !gameOver){
                graphics.drawString("PAUSED",(gameContainer.getWidth() / 2) - graphics.getFont().getWidth("PAUSED") / 2,gameContainer.getHeight() / 2);
            }else if(gameOver){
                graphics.drawString("CLICK ESC TO PLAY",(gameContainer.getWidth() /2)- graphics.getFont().getWidth("CLICK ESC TO PLAY") / 2,gameContainer.getHeight() / 2);
            }
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        if(key == 1){
            paused = !paused;
            if(gameOver){
                asteroids.clear();
                backgroundPos = 0;
                score = 0;
                gameSpeed = 2;
                s = 0;
                player = new RenderPlayer((container.getWidth() / 2) - 40,container.getHeight() - 140);
                gameOver = false;
            }
        }
    }
}
