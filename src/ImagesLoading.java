import java.awt.*;

public class ImagesLoading {

    Toolkit t=Toolkit.getDefaultToolkit();

    Image enemy1=t.getImage("Images/enemy1.png");
    Image enemy2=t.getImage("Images/enemy2.PNG");
    Image enemy3=t.getImage("Images/enemy3.PNG");
    Image background=t.getImage("Images/background2.png");

    Image projectile1=t.getImage("Images/projectile1.png");
    Image projectile2=t.getImage("Images/projectile2.png");

    Image newImage = projectile1.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    Image projectile2re = projectile2.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    Image bosspro=t.getImage("Images/projectile3.png");

    Image bossPro = bosspro.getScaledInstance(35, 35, Image.SCALE_DEFAULT);
    Image enemy2re = enemy2.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
    Image enemy3re = enemy3.getScaledInstance(60, 60, Image.SCALE_DEFAULT);

    Image itemog=t.getImage("Images/itemslot.png");

    Image itemslot = itemog.getScaledInstance(45, 45, Image.SCALE_DEFAULT);

    Image item1og=t.getImage("Images/item1.png");

    Image item1 = item1og.getScaledInstance(36, 30, Image.SCALE_DEFAULT);

    Image item2og=t.getImage("Images/item2.png");

    Image item2 = item2og.getScaledInstance(36, 30, Image.SCALE_DEFAULT);

    Image item3og=t.getImage("Images/item4.png");

    Image item3 = item3og.getScaledInstance(36, 30, Image.SCALE_DEFAULT);

    Image heartog=t.getImage("Images/heart.png");

    Image Heart = heartog.getScaledInstance(36, 30, Image.SCALE_DEFAULT);

    Image starship =t.getImage("Images/starship1.png");

    Image starshipmain = starship.getScaledInstance(100, 50, Image.SCALE_DEFAULT);

}