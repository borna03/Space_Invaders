public class CalcMath {
    int valid = 0;
    public static double FindX(double xCord, int quadrant, int screenWidth){
        double x = 0;
        if(quadrant == 2 || quadrant == 3){
            x = screenWidth- xCord;
            return x;
        }else if(quadrant == 1 || quadrant == 4){
            x =  xCord - screenWidth;
            return x;
        }
        return x;
    }

    public static double FindDiagonal(double x, double y){
        double diag =0;
        diag = Math.sqrt((Math.pow(x,2) + Math.pow(y,2)));
        return diag;
    }

    public static double FindY(double yCord, int quadrant, int screenHeight){
        double y = 0;
        if(quadrant == 1 || quadrant == 2){
            y = screenHeight - yCord;
            return y;
        }else if(quadrant == 3 || quadrant == 4){
            y =  yCord - screenHeight;
            return y;
        }
        return 0;
    }

    public static double FindAngle(double xCord, double yCord, double diag, int quadrant, double mouseX, double mouseY){
        double angle = 0;
        if(quadrant == 2 || quadrant == 4){
            angle = Math.asin(yCord/diag);
            angle = Math.toDegrees(angle);
            return angle;
        }if(quadrant == 1 || quadrant == 3){
            angle = Math.asin(xCord/diag);
            angle = Math.toDegrees(angle);
            return angle;
        }
        return angle;
    }

    public double MathMethod(double mouseX , double mouseY, int screenWidth , int screenHeight , int playerX , int playerY, int odsutpanjex, int odsutpanjexy){
        if (mouseX >= 0 && mouseY >= 0 && mouseX <= screenWidth && mouseY <= screenHeight){
            int quadrant = 0;
            int multiplayer = 0;
            double  x , y, halfHeight = screenHeight / 2 , halfWidth = screenWidth / 2, diagonal = 0, degres;
            playerX += odsutpanjex*screenWidth;
            playerY += odsutpanjexy*screenHeight;

            if(mouseX > playerX && mouseY  < playerY){
                quadrant = 1;
            }if(mouseX < playerX && mouseY  < playerY){
                quadrant = 2;
                multiplayer = 3;
            }if(mouseX < playerX && mouseY  > playerY){
                quadrant = 3;
                multiplayer = 2;
            }if(mouseX > playerX && mouseY  > playerY){
                quadrant = 4;
                multiplayer = 1;
            }
            x = FindX(mouseX , quadrant, playerX);
            y = FindY(mouseY , quadrant, playerY);
            diagonal = FindDiagonal(x , y);
            degres = FindAngle(x, y, diagonal , quadrant, mouseX , mouseY) + multiplayer*90;
            degres = getDegres(mouseX, mouseY, screenWidth, screenHeight, playerX, playerY, quadrant, degres);
            return degres;
        }
        return 0;
    }

    private static double getDegres(double mouseX, double mouseY, int screenWidth, int screenHeight, int playerX, int playerY, int quadrant, double degres) {
        if (quadrant == 0){
            if(mouseY  < playerY && mouseX == (screenWidth/2)){
                degres = 0;
            }if(mouseX > playerX && mouseY == (screenHeight/2)){
                degres = 90;
            }if(mouseY  > playerY && mouseX == (screenWidth/2)){
                degres = 180;
            }if(mouseX < playerX && mouseY == (screenHeight/2)){
                degres = 280;
            }
        }
        return degres;
    }

    static double[] Kurcina(double mouseX , double mouseY, int screenWidth , int screenHeight , int playerX , int playerY, int odsutpanjex, int odsutpanjexy){
        double[] cords = new double[4];
        if (mouseX >= 0 && mouseY >= 0 && mouseX <= screenWidth && mouseY <= screenHeight){
            int quadrant = 0;
            double  x , y , diagonal = 0, degres;
            playerX += odsutpanjex*screenWidth;
            playerY += odsutpanjexy*screenHeight;

            if(mouseX > playerX && mouseY  < playerY){
                quadrant = 1;
            }if(mouseX < playerX && mouseY  < playerY){
                quadrant = 2;
            }if(mouseX < playerX && mouseY  > playerY){
                quadrant = 3;
            }if(mouseX > playerX && mouseY  > playerY){
                quadrant = 4;
            }
            x = FindX(mouseX , quadrant, playerX);
            y = FindY(mouseY , quadrant, playerY);
            diagonal = FindDiagonal(x , y);
            degres = FindAngle(x, y, diagonal , quadrant, mouseX , mouseY);
            double vertexX , vertexY;
            vertexX = x/y;
            vertexY = y/x;
            cords[0] = vertexX;
            cords[1] = vertexY;
            cords[2] = quadrant;
            degres = getDegres(mouseX, mouseY, screenWidth, screenHeight, playerX, playerY, quadrant, degres);
            cords[3] = degres;
            return cords;
        }return  cords;
    }

}
