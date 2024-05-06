import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.SimpleTimeZone;

public class KeyHandler implements KeyListener{
    public boolean rightMove,leftMove,upMove,downMove, Fire, bossFire, aifire;

    static double[] MouseCords(){
        double[] cords = new double[2];
        double mouseX = MouseInfo.getPointerInfo().getLocation().getX() - 360;
        double mouseY = MouseInfo.getPointerInfo().getLocation().getY() - 133;
        cords[0] = mouseX;
        cords[1] = mouseY;
        return cords;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()){
            case KeyEvent.VK_SPACE:
                Fire = true;
                break;
            case 'u':
                System.out.println("X");
                bossFire = true;
                break;
            case 'f':
                aifire = true;
                break;
            case 'g':
                aifire = false;
                break;
        }}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_A){
            leftMove = true;
        }if (keyCode == KeyEvent.VK_D){
            rightMove = true;
        }if (keyCode == KeyEvent.VK_S){
            downMove = true;
        }if (keyCode == KeyEvent.VK_W){
            upMove = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_A){
            leftMove = false;
        }if (keyCode == KeyEvent.VK_D){
            rightMove = false;
        }if (keyCode == KeyEvent.VK_S){
            downMove = false;
        }if (keyCode == KeyEvent.VK_W){
            upMove = false;
        }
    }

}