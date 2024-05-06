import java.awt.*;

public class Buff {

    Toolkit t=Toolkit.getDefaultToolkit();
    ImagesLoading images = new ImagesLoading();

    int itemx , itemy, itemw , itemh ,  extraX , extraY, streght , speed, bullets , hp;
    double timmerSpeed, timmerStreght;
    boolean speedB,streghtB ;

    public Buff(int itemX , int itemY , boolean speedB, int extraX , int extraY, int  itemw ,  int itemh, double timmerSpeed, double timmerStreght, int streght, int speed, int bullets, int hp){
        this.itemx = itemX;
        this.itemy = itemY;
        this.speedB = speedB;
        this.extraX = extraX;
        this.extraY = extraY;
        this.itemw = itemw;
        this.itemh = itemh;
        this.timmerSpeed = timmerSpeed;
        this.timmerStreght = timmerStreght;
        this.streght = streght;
        this.speed = speed;
        this.bullets = bullets;
        this.hp = hp;

    }

    public void drawBuff(Graphics g){
        if(this.speed > 0){
            g.drawImage(images.item1 , this.itemx + this.extraX , this.itemy + this.extraY,null);
        }if(this.streght > 0){
            g.drawImage(images.item2 , this.itemx + this.extraX , this.itemy + this.extraY,null);
        }if(this.bullets > 0){
            g.drawImage(images.item3 , this.itemx + this.extraX , this.itemy + this.extraY,null);
        }if(this.hp > 0){
            g.drawImage(images.Heart , this.itemx + this.extraX , this.itemy + this.extraY,null);
        }
    }

}
