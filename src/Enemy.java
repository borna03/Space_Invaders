import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.List;
import java.util.Random;

public class Enemy {
    int enemyx , enemyy , enemyHeight , enemyWidth , id, randomx , randomy, enemynum, hp;
    Random rand = new Random();

    public Enemy(int enemyX , int enemY, int ID , int randomx, int randomy,int enemynum, int HP){
            this.enemyx = enemyX;
            this.enemyy = enemY;
            if(enemynum == 3){
                this.enemyHeight = 60;
                this.enemyWidth = 60;
            } else {
                this.enemyHeight = 30;
                this.enemyWidth = 30;
            }
            this.id = ID;
            this.randomx = randomx;
            this.randomy = randomy;
            this.enemynum = enemynum;
            this.hp = HP;
    }
        public void drawEnemy(Graphics g, Image image){
            Graphics2D g1 = (Graphics2D)g.create();
            g1.drawImage(image , this.enemyx , this.enemyy,null);
        }
}
