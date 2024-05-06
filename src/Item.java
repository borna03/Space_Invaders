import java.awt.*;

public class Item {
    Toolkit t=Toolkit.getDefaultToolkit();
    ImagesLoading images = new ImagesLoading();

    int itemx , itemy , itemNumber,  extraX , extraY;
    boolean state;

    public Item(int itemX , int itemY , int itemNumber, int extraX , int extraY, boolean state){
        this.itemx = itemX;
        this.itemy = itemY;
        this.itemNumber = itemNumber;
        this.extraX = extraX;
        this.extraY = extraY;
        this.state = state;
    }

    public void drawItem(Graphics g){
        if(!this.state){
            g.drawImage(images.itemslot , this.itemx + this.extraX , this.itemy + this.extraY,null);
        }else if(this.state == true){
            g.drawImage(images.Heart , this.itemx + this.extraX , this.itemy + this.extraY,null);
        }
    }

}
