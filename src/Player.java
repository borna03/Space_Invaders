import java.awt.*;
import java.awt.geom.Path2D;

public class Player {

    int playerWidth , playerHeight , playerSpeed , playerStrength, Lives ;
    Color color;
    ImagesLoading images = new ImagesLoading();
    private Path2D myPath = new Path2D.Double();

    public Player(int playerWidth , int playerHeight, int playerSpeed, int playerStrength, Color color, int Lives){
        this.playerWidth = playerWidth;
        this.playerHeight = playerHeight;
        this.playerSpeed = playerSpeed;
        this.playerStrength = playerStrength;
        this.color = color;
        this.Lives = Lives;
    }

    public void DrawPlayer(Graphics g, double Rotation, int playerX, int playerY , int playerMoveX , int playermoveY){
        Graphics2D g1 = (Graphics2D)g.create();
        Graphics2D g2 = (Graphics2D)g.create();
        Graphics2D starship = (Graphics2D)g.create();

        g1.setColor(this.color);
        g1.rotate(Math.toRadians(Rotation), playerX + playerMoveX, playerY + playermoveY);
        g1.fill(new Polygon(new int[]{playerX + (playerWidth/2) + playerMoveX,playerX + playerMoveX,playerX - (playerWidth/2) + playerMoveX}, new int[]{playerY + (playerHeight/2) + playermoveY,playerY - (playerHeight /2) + playermoveY,playerY + (playerHeight/2) + playermoveY},3));

        g2.setColor(Color.red);
        g2.rotate(Math.toRadians(Rotation), playerX + playerMoveX, playerY + playermoveY);
        g2.fill(new Polygon(new int[]{playerX + (playerWidth/2) + playerMoveX - 14,playerX + playerMoveX,playerX - (playerWidth/2) + playerMoveX + 14}, new int[]{playerY + (playerHeight/2) + playermoveY - 15,playerY - (playerHeight /2) + playermoveY,playerY + (playerHeight/2) + playermoveY - 15},3));

        //starship.rotate(Math.toRadians(Rotation + 90), playerX + playerMoveX, playerY + playermoveY);
        //starship.drawImage(images.starshipmain , playerX + playerMoveX - 10, playerY + playermoveY - 28, null);
    }

}
