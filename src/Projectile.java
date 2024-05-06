import java.awt.*;

public class Projectile {
    double range, x, y ,vertx,verty,angle;
    int quadrant, pWidth , pHeight,projcNum;

    public Projectile(double x, double y,double range, double vertx, double verty, int quadrant,double angle, int projcNum){
        this.x = x;
        this.y = y;
        this.range = range;
        this.vertx = vertx;
        this.verty = verty;
        this.quadrant = quadrant;
        this.angle = angle;
        this.pWidth = 20;
        this.pHeight = 20;
        this.projcNum = projcNum;
    }

    public void ProjectileTick(){
        double speed = 4.5;
        if(this.range < 750){
            if(this.quadrant == 1){
                this.x += speed * Math.cos((Math.toRadians(90 - this.angle)));
                this.y -= speed * Math.sin(Math.toRadians(90 - this.angle));
                this.range+=1;
            }if(this.quadrant == 2){
                this.x -= speed * Math.cos((Math.toRadians(this.angle)));
                this.y -= speed * Math.sin(Math.toRadians(this.angle));
                this.range+=1;
            }if(this.quadrant == 3){
                this.x -= speed * Math.cos((Math.toRadians(90 - this.angle)));
                this.y += speed * Math.sin(Math.toRadians(90 - this.angle));
                this.range+=1;
            }if(this.quadrant == 4){
                this.x += speed * Math.cos((Math.toRadians(this.angle)));
                this.y += speed * Math.sin(Math.toRadians(this.angle));
                this.range+=1;
            }
        }
    }

    public void drawProjectile(Graphics g, Image image){
        Graphics2D g2 = (Graphics2D)g.create();
        g.drawImage(image , (int)this.x  , (int)this.y,null);
    }

}
