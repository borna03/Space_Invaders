import java.awt.*;
import java.awt.geom.Path2D;

public class Boss {

    int bossX , bossY , bossWidth , bossHegiht, abbilityState, hp,odtsupanjex,odtsupanjey,bossPower;
    Toolkit t=Toolkit.getDefaultToolkit();

    Image bosspre1=t.getImage("Images/char1rew.png");
    Image bosspre2=t.getImage("Images/char1rewfire.png");
    Image boss = bosspre1.getScaledInstance(100, 175, Image.SCALE_DEFAULT);
    Image bossfire = bosspre2.getScaledInstance(130, 175, Image.SCALE_DEFAULT);

    public Boss(int bossX , int bossY , int bossWidth , int bossHegiht, int abbilityState, int hp, int odtsupanjex , int odtsupanjey, int bossPowers){
        this.bossX = bossX;
        this.bossY = bossY;
        this.bossWidth = bossWidth;
        this.bossHegiht = bossY;
        this.abbilityState = abbilityState;
        this.hp = hp;
        this.odtsupanjex = odtsupanjex;
        this.odtsupanjey = odtsupanjey;
        this.bossPower = bossPowers;


    }

    public void DrawBoss(Graphics g, int bossX, int bossY, int bossWidth , int bossHegiht , int abbilityState){
        Graphics2D g1 = (Graphics2D)g.create();
        Graphics2D g2 = (Graphics2D)g.create();

        if(abbilityState == 1){
            g.drawImage(boss , this.bossX , this.bossY,null);
        }else if(abbilityState == 2){
            g.drawImage(bossfire , this.bossX - 15 , this.bossY,null);
        }
    }
}
