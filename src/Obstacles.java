import java.awt.*;


public class Obstacles {

    int obstacleX , obstacleY, obstacleR , ID;
    ImagesLoading images = new ImagesLoading();
    Toolkit t=Toolkit.getDefaultToolkit();

    public Obstacles(int obstacleX , int obstacleY, int obstacleR, int ID){
            this.obstacleX = obstacleX;
            this.obstacleY = obstacleY;
            this.obstacleR = obstacleR;
            this.ID = ID;
    }

    public void drawObstacle(Graphics g, Image image){
        Graphics2D g9 = (Graphics2D)g.create();
        g9.setColor(new Color(208,207,255));
        g9.fillOval(this.obstacleX, this.obstacleY, this.obstacleR, this.obstacleR);
        g9.drawOval(this.obstacleX, this.obstacleY, this.obstacleR, this.obstacleR);
    }
}
