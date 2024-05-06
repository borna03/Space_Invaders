import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GamePanel extends JPanel implements Runnable{
    int screenwidth = 1200, screenheight = 800, BarMGap = 0;
    boolean Possible = true,PlayerFire  = true, delay = false, playerFiree = false,AIFire;
    int width = 150, height = 20, obstaclemin = -3000,obstaclmax = 3000,radiusminn = 80,radiusmax = 250, enemymin = -3000 , enemymax = 3000,CameraX = 0, CameraY = 0, odstupanjex = 0, odstupanjey = 0, bkcx , bkcy,cameraBoundXF = 0, cemeraBoundYF = 0,cameraBoundXS = screenwidth , cameraBoundYS = screenheight;
    double range = 300, FPS = 60;
    int aiodstupanjeX , aiodstupanjeY, playerX = screenwidth / 2, playerY = screenheight / 2, playermoveX = 0, playermoveY = 0 ,heartsgap, itemgap, projectileNum = 0,AiX = 100 , AiY = 200,AImoveX = 0, AImoveY = 0,randomXAI = 0,randomYAI = 0;
    double angle = 0, projtimer , bulletsLeft = 5 , reloadTimmer, AIangle = 0;

    Thread gameThread;
    Player player = new Player(45 , 25, 2,1, Color.blue, 10);
    Player playerAI = new Player(45 , 25, 2,1, Color.yellow,4);

    int bossX = -500, bossY = - 500 , bossWidth = 100 , bossHeight = 175,score;
    Boss boss = new Boss(bossX , bossY , bossWidth , bossHeight , 1, 20, 1 ,1  , 2);


    CalcMath degenDegres = new CalcMath();
    Random rand = new Random();
    KeyHandler keyH = new KeyHandler();
    MouseClick mouse = new MouseClick();
    ImagesLoading images = new ImagesLoading();
    public List<Projectile> projectiles = new ArrayList<Projectile>();
    public List<Projectile> enemyprojectiles = new ArrayList<Projectile>();
    public List<Projectile> AIprojectiles = new ArrayList<Projectile>();

    public List<Enemy> enemys = new ArrayList<Enemy>();
    public List<Obstacles> obstacles = new ArrayList<Obstacles>();
    public List<Item> items = new ArrayList<Item>();
    public List<Buff> buffs = new ArrayList<Buff>();
    public List<Item> Hearts = new ArrayList<Item>();



    public List<Image> imagess = new ArrayList<Image>();
    Toolkit t=Toolkit.getDefaultToolkit();


    public GamePanel(){

        this.setPreferredSize(new Dimension(screenwidth, screenheight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouse);
        this.setFocusable(true);

    }

    public void startGameThread() {

        gameThread = new Thread(this);

        makeEnemys(enemys,100 , 60);
        makeObstacles(obstacles , 140);
        makeItemsSlots(items , 5);
        makeHearts(Hearts , player.Lives);
        GenerateBuffd(buffs);

        gameThread.start();
    }


    @Override
    public void run() {

        double drawInterval = 100000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null){

            tick();
            AItick();
            fire();
            repaint();

        try {
            double remainingTime = nextDrawTime - System.nanoTime();
            remainingTime = remainingTime / 100000;
            if (remainingTime < 0){
                remainingTime = 0;
            }
            Thread.sleep((long) remainingTime);
            nextDrawTime += drawInterval;
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void GenerateBuffd(List buffs){
        int counter = 0;
        for(int i = 0;i < 4 ;  i++ ){
            for(int j = 0; j < 25 ; j++){
                int st = 0, sp = 0, hape = 0, bule = 0;
                if(counter == 0 ){
                    st = 3;
                }if(counter == 1){
                    sp = 3;
                }if(counter == 2){
                    bule = 8;
                }if(counter == 3){
                    hape = 1;
                }
                int cordx = (int)(Math.random()*(obstaclmax-obstaclemin+1)+obstaclemin);
                int cordy = (int)(Math.random()*(obstaclmax-obstaclemin+1)+obstaclemin);
                buffs.add(new Buff(cordx , cordy ,false, 0 , 0 , 36  , 30 ,0 , 0 , st , sp , bule , hape));
            }counter+=1;
        }
    }
    public void makeEnemys(List enemysList, int enemynum2 ,int enemynum3 ){
        int a = 0;
        for(int i = 0;i < enemynum2 ;  i++ ){
            int cordx = (int)(Math.random()*(enemymax-enemymin+1)+enemymin);
            int cordy = (int)(Math.random()*(enemymax-enemymin+1)+enemymin);
            enemysList.add(new Enemy(cordx , cordy , i , cordx, cordy,2, 1));
            a+=1;
        }for(int j = 0;j < enemynum3 /2  ;  j++ ){
            int cordx = (int)(Math.random()*(enemymax-enemymin+1)+enemymin);
            int cordy = (int)(Math.random()*(enemymax-enemymin+1)+enemymin);
            enemysList.add(new Enemy(cordx , cordy , a + j , cordx, cordy,3, 3));
        }
    }
    public void makeItemsSlots(List itemList, int itemnum){
        for(int i = 0;i < itemnum ;  i++ ){
            itemList.add(new Item(940 + itemgap , 10 , i, 0 ,0 , false));
            itemgap +=50;
        }
    }
    public void makeHearts(List itemList, int heartsnum){
        for(int i = 0;i < heartsnum ;  i++ ){
            Hearts.add(new Item(400 + heartsgap , 10 , i, 0 ,0 , true));
            heartsgap +=40;
        }
    }
    public void makeObstacles(List obstacleList, int obstaclenum){
        Image moon=t.getImage("Images/moon.PNG");
        for(int i = 0;i < obstaclenum ;  i++ ){
            int cordx = (int)(Math.random()*(obstaclmax-obstaclemin+1)+obstaclemin);
            int cordy = (int)(Math.random()*(obstaclmax-obstaclemin+1)+obstaclemin);
            int rsize = (int)(Math.random()*(radiusmax-radiusminn+1)+radiusminn);
            obstacleList.add(new Obstacles(cordx , cordy , rsize, i));
            Image newmoon = moon.getScaledInstance(rsize*2, rsize, Image.SCALE_DEFAULT);
            imagess.add(newmoon);
        }
    }
    public void tick(){
        double[] cords = keyH.MouseCords();
        int playerXaxis , playerYaxis;
        playerXaxis = playerX + playermoveX;
        playerYaxis = playerY + playermoveY;
        angle = degenDegres.MathMethod(cords[0] , cords[1] , screenwidth , screenheight, playerXaxis , playerYaxis,odstupanjex,odstupanjey);
    }
    public void AImove(){
        double x = AiX + AImoveX;
        double y = AiY + AImoveY;

        int maxAIX , maxAIY , minAIX , minAIY, AIspeed = 2;
        minAIX = odstupanjex*-1 * screenwidth;
        maxAIX = odstupanjex*-1 * screenwidth + screenwidth;

        if(x <= maxAIX && x >= minAIX){
            aiodstupanjeX = 0;
        }else{
            aiodstupanjeX = 1;
        }

        minAIY = odstupanjey*-1 * screenheight;
        maxAIY = odstupanjey*-1 * screenheight + screenheight;

        if(y <= maxAIY && y >= minAIY){
            aiodstupanjeY = 0;
        }else{
            aiodstupanjeY = 1;
        }

        if((x == randomXAI ||(x - 1) == randomXAI ||(x + 1) == randomXAI)  && (y == randomYAI ||(y - 1) == randomYAI ||(y + 1) == randomYAI)){
            int AIcordx = (int)(Math.random()*(maxAIX-minAIX+1)+minAIX);
            int AIcordy = (int)(Math.random()*(maxAIY-minAIY+1)+minAIY);

            randomXAI = AIcordx;
            randomYAI = AIcordy;
        }else{
            if(randomXAI  < x){
                AImoveX -=AIspeed;
            }else if(randomXAI > x){
                AImoveX +=AIspeed;
            }if(randomYAI  < y){
                AImoveY -=AIspeed;
            }else if(randomYAI > y){
                AImoveY +=AIspeed;
            }
        }

    }
    public void AItick(){

        double x = AiX + AImoveX ;
        double y = AiY + AImoveY ;

        double playerx = (playerX + playermoveX);
        double playery = (playerY + playermoveY);

        if(odstupanjex > 0 ){
            playerx +=odstupanjex*screenwidth;
        }if(odstupanjex < 0){
            playerx +=odstupanjex*screenwidth;
        } if(odstupanjey > 0 ){
            playery +=odstupanjey*screenheight;
        }if(odstupanjey < 0){
            playery +=odstupanjey*screenheight;
        }
        AIangle = degenDegres.MathMethod(playerx, playery, screenwidth , screenheight, (int)x , (int)y,odstupanjex,odstupanjey);
    }
    public void Bossfire(){
        if(keyH.bossFire == true || AIFire == true){
            double x = 0;
            double y = 0;
            if(keyH.bossFire == true){
                x = boss.bossX + 50;
                y = boss.bossY + 50;
            }if(AIFire == true){
                x = AiX + AImoveX;
                y = AiY + AImoveY;
            }
            double playerx = (playerX + playermoveX);
            double playery = (playerY + playermoveY);
            if(odstupanjex > 0 ){
                playerx +=odstupanjex*screenwidth;
            }if(odstupanjex < 0){
                playerx +=odstupanjex*screenwidth;
            } if(odstupanjey > 0 ){
                playery +=odstupanjey*screenheight;
            }if(odstupanjey < 0){
                playery +=odstupanjey*screenheight;
            }

            double[] vertexes = CalcMath.Kurcina(playerx, playery,screenwidth,screenheight,(int)x,(int)y,odstupanjex,odstupanjey);
            if(keyH.bossFire == true){
                enemyprojectiles.add(new Projectile(x,y,0,vertexes[0],vertexes[1], (int)vertexes[2], vertexes[3],projectileNum));
                keyH.bossFire = false;
            }if(AIFire == true){
                AIprojectiles.add(new Projectile(x,y,0,vertexes[0],vertexes[1], (int)vertexes[2], vertexes[3],projectileNum));
                AIFire = false;
            }
        }
    }
    public void fire(){
        if(bulletsLeft == 0){
            reloadTimmer+=1;
            if(reloadTimmer/270 == 5.0){
                bulletsLeft = 5;
                reloadTimmer = 0;
            }
        }else {
            if(keyH.Fire == true && PlayerFire == true){
            double x = (playerX + playermoveX);
            double y =(playerY + playermoveY);
            double[] cords = keyH.MouseCords();

            double[] vertexes = CalcMath.Kurcina(cords[0], cords[1],screenwidth,screenheight,(int)x,(int)y,odstupanjex,odstupanjey);

            projectiles.add(new Projectile(x,y,0,vertexes[0],vertexes[1], (int)vertexes[2], vertexes[3],projectileNum));
            keyH.Fire = false;
            mouse.fire = false;
            bulletsLeft-=1;
            delay = true;
            playerFiree = false;
        }}
    }
    public void enemyMove(Enemy enemy){
        if(enemy.enemyx == enemy.randomx && enemy.enemyy == enemy.randomy){
        }else{
            if(enemy.randomx  < enemy.enemyx){
                enemy.enemyx -=1;
            }else if(enemy.randomx > enemy.enemyx){
                enemy.enemyx +=1;
            }if(enemy.randomy  < enemy.enemyy){
                enemy.enemyy -=1;
            }else if(enemy.randomy > enemy.enemyy){
                enemy.enemyy +=1;
            }
        }
    }
    public void move(){
        int playerspeed = player.playerSpeed;
        if(Possible == true){
            if(keyH.rightMove == true){
                playermoveX+=playerspeed;
            }if(keyH.leftMove == true){
                playermoveX-=playerspeed;
            }if(keyH.upMove == true){
                playermoveY-=playerspeed;
            }if(keyH.downMove == true){
                playermoveY+=playerspeed;
            }
    }}
    public void  CollisionProjectileEnemy(Projectile projectile) {
        for(Enemy enemy: enemys){
            if(new Rectangle((int)projectile.x,(int)projectile.y, projectile.pWidth , projectile.pHeight).intersects(enemy.enemyx, enemy.enemyy , enemy.enemyWidth , enemy.enemyHeight)){
                projectile.x = 9000;
                enemy.hp -= player.playerStrength;
                if(enemy.hp  <= 0){
                    enemys.remove(enemy);
                    if(enemy.enemynum == 3){
                        score+=3;
                    }else if(enemy.enemynum == 2){
                        score+=1;
                    }
                }
                enemy.randomx = enemy.enemyx;
                enemy.randomy = enemy.enemyy;

                makeEnemys(enemys,1 , 1 );

            }
        }
    }
    public void  CollisionProjectilePlayer(Projectile projectile , boolean bossfire , boolean aifire) {
        if(new Rectangle((int)projectile.x,(int)projectile.y, projectile.pWidth , projectile.pHeight).intersects(playerX + playermoveX - 45 / 2, playerY + playermoveY - 25 / 2 , 45 , 25)){
            projectile.x = 9000;
            if(bossfire == true){
                for(int i = 0; i < boss.bossPower; i++){
                    player.Lives -= 1;
                    Hearts.remove(player.Lives);
                }
            }
            if(aifire == true){
                for(int i = 0; i < playerAI.playerStrength; i++){
                    player.Lives -= 1;
                    heartsgap -=40;
                    Hearts.remove(player.Lives);
                }
            }
        }
    }
    public void  CollisionProjectileAI(Projectile projectile) {
        if(new Rectangle((int)projectile.x,(int)projectile.y, projectile.pWidth , projectile.pHeight).intersects(AiX + AImoveX - 45 / 2, AiY + AImoveY - 25 / 2 , 45 , 25)){
            projectile.x = 9000;
            playerAI.Lives -= player.playerStrength;
        }
    }
    public void  CollisionProjectileBoss(Projectile projectile) {
        if(new Rectangle((int)projectile.x,(int)projectile.y, projectile.pWidth , projectile.pHeight).intersects(boss.bossX , boss.bossY , 100, 175)){
            boss.hp -=1;
            if(boss.abbilityState == 1){
                boss.abbilityState += 1;
            }else if(boss.abbilityState == 2){
                boss.abbilityState -= 1;
            }if(boss.hp <= 0){
                boss.bossX = 12000;
                boss.bossY = 12000;

                score+=20;
            }
            projectiles.remove(projectile);
        }
    }
    public boolean CollisionBuffsPlayer(Buff buff) {
        if(new Rectangle(buff.itemx,buff.itemy, buff.itemw , buff.itemh).intersects(playerX + playermoveX - 45 / 2, playerY + playermoveY - 25 / 2, 45 , 25)){
            buff.itemx = 6000;
            return true;
        }
        return false;
    }
    public void  CollisionPlayerObstacles(Obstacles obstacle){
        if(new Rectangle(obstacle.obstacleX,obstacle.obstacleY,obstacle.obstacleR , obstacle.obstacleR).intersects(playerX + playermoveX - 45 / 2, playerY + playermoveY - 25 / 2, 45 , 25)){
            double PlayerX , PlayerY, PlayerCenterX,PlayerCenterY , CricleCenterX, CricleCenterY, Radius;
            PlayerX = playerX + playermoveX - 45 / 2;
            PlayerY = playerY + playermoveY - 25 / 2;
            Radius = obstacle.obstacleR /2;
            PlayerCenterX = playerX + playermoveX + 45 / 2;
            PlayerCenterY = playerY + playermoveY + 25 / 2;
            CricleCenterX = obstacle.obstacleX + Radius;
            CricleCenterY = obstacle.obstacleY + Radius;

            double x = 0, y = 0 , diag, corner = 0 , ph = 25 , pw = 45;

            if(CricleCenterY > PlayerCenterY){
                if(PlayerCenterX > CricleCenterX){
                    x = PlayerX - CricleCenterX;
                    y = CricleCenterY - PlayerY - 25;
                    corner = 2;
                }else if(PlayerCenterX < CricleCenterX){
                    x = CricleCenterX - PlayerCenterX;
                    y = CricleCenterY - PlayerCenterY;
                    corner = 1;
                }
            }if(CricleCenterY < PlayerCenterY){
                if(PlayerCenterX > CricleCenterX){
                    x = PlayerCenterX - CricleCenterX - 35;
                    y = PlayerCenterY - CricleCenterY - 25;
                    corner = 4;
                }else if(PlayerCenterX < CricleCenterX){
                    x = CricleCenterX - PlayerCenterX;
                    y = PlayerCenterY - CricleCenterY - 25;
                    corner = 3;
                }
            }
            diag = Math.sqrt((Math.pow(x,2) + Math.pow(y,2)));
            if(diag <Radius){
                if(corner == 1){
                    playermoveX -=5;
                    playermoveY -=5;
                }if(corner == 2){
                    playermoveX +=5;
                    playermoveY -=5;
                }if(corner == 3){
                    playermoveX -=5;
                    playermoveY +=5;
                }if(corner == 4){
                    playermoveX +=5;
                    playermoveY +=5;
                }
            }else{
                Possible = true;
            }
        }
    }
    public void DrawBullets(Graphics g, int extraX , int extraY){
        int Bgap =0 ;
        if(bulletsLeft == 0.0){
            Graphics2D bulletBar = (Graphics2D)g.create();

            bulletBar.setColor(Color.red);
            bulletBar.drawRect(5 + extraX ,5 + extraY,380  - BarMGap, 8);
            bulletBar.fillRect(5 + extraX ,5 + extraY,380 - BarMGap, 8);
            BarMGap = BarMGap + 1;

        }else if(bulletsLeft > 0.0){
            BarMGap =0 ;
        }
        for(int i = 0; i < bulletsLeft; i++){
            Graphics2D bullet = (Graphics2D)g.create();
            bullet.setColor(Color.red);
            bullet.drawRect(5 + extraX + Bgap,5 + extraY,8 , 25);
            bullet.fillRect(5 + extraX + Bgap,5 + extraY,8 , 25);

            Bgap+=11;
        }
    }
    public void cameraMove(){
        int ppx , ppy;
        ppx = playerX + playermoveX;
        ppy = playerY + playermoveY;
        if(ppx < cameraBoundXF){
            cameraBoundXF -= screenwidth;
            cameraBoundXS -= screenwidth;
            CameraX += screenwidth;
            bkcx -= screenwidth;
            odstupanjex +=1;
        }if(ppx > cameraBoundXS){
            cameraBoundXF += screenwidth;
            cameraBoundXS += screenwidth;
            CameraX -= screenwidth;
            bkcx += screenwidth;
            odstupanjex -=1;
        }if(ppy < cemeraBoundYF){
            cemeraBoundYF -= screenheight;
            cameraBoundYS -= screenheight;
            CameraY += screenheight;
            bkcy -= screenheight;
            odstupanjey +=1;
        }if(ppy > cameraBoundYS){
            cemeraBoundYF += screenheight;
            cameraBoundYS += screenheight;
            CameraY -= screenheight;
            bkcy += screenheight;
            odstupanjey -=1;
        }
    }
    public void AiBossShoot(){

        int enemyproc = enemyprojectiles.size();
        int aiproc = AIprojectiles.size();

        for(int i = 0; i < enemyprojectiles.size(); i++){
            if (enemyprojectiles.get(i).range > 120){
                enemyproc -=1;
            }
        }for(int i = 0; i < AIprojectiles.size(); i++){
            if (AIprojectiles.get(i).range > 120){
                aiproc -=1;
            }
        }

        if(enemyproc == 0){
            if(boss.odtsupanjex == odstupanjex && boss.odtsupanjey == odstupanjey){
                keyH.bossFire = true;
            }
        }if(aiproc == 0){
            if(aiodstupanjeX == 0 && aiodstupanjeY == 0 && keyH.aifire == true){
                AIFire = true;
            }
        }
        Bossfire();

    }


    public void paint(Graphics g){
        super.paintComponent(g);

        move();
        cameraMove();
        AImove();


        g.translate(CameraX  , CameraY);
        g.drawImage(images.background , 0 - CameraX , 0 - CameraY,this);

        for(Obstacles obstacle: obstacles){
            obstacle.drawObstacle(g, imagess.get(obstacle.ID));
            CollisionPlayerObstacles(obstacle);
        }

        player.DrawPlayer(g,(int) angle , playerX ,playerY, playermoveX , playermoveY);
        playerAI.DrawPlayer(g,(int) AIangle , AiX ,AiY, AImoveX , AImoveY);

        boss.DrawBoss(g , bossX , bossY , bossWidth , bossHeight , boss.abbilityState);

        for(int i = 0; i < projectiles.size(); i++){
            if(projectiles.get(i).range == 749){
                projectiles.remove(i);
            }
        }

        for(Enemy enemy: enemys){
            if(enemy.enemyx == enemy.randomx && enemy.enemyy == enemy.randomy){
                int cordx = (int)(Math.random()*(enemymax-enemymin+1)+enemymin);
                int cordy = (int)(Math.random()*(enemymax-enemymin+1)+enemymin);
                enemy.randomx = cordx;
                enemy.randomy = cordy;
            }
            enemyMove(enemy);
            if(enemy.enemynum == 2){
                enemy.drawEnemy(g , images.enemy2re);
            }if(enemy.enemynum == 3){
                enemy.drawEnemy(g , images.enemy3re);
            }
        }
        for(Projectile i: projectiles){
            i.ProjectileTick();
            i.drawProjectile(g, images.projectile2re);
            CollisionProjectileAI(i);
            CollisionProjectileEnemy(i);
            CollisionProjectileBoss(i);

            if(i.range ==  749){
                projectiles.remove(i);
            }
        }



        for(Projectile i: enemyprojectiles){
            i.ProjectileTick();
            i.drawProjectile(g, images.bossPro);
            CollisionProjectilePlayer(i , true, false);

            if(i.range <  85){
                boss.abbilityState = 2;
            }if(i.range >  85){
                boss.abbilityState = 1;
            }
            if(i.range >  400){
                enemyprojectiles.remove(i);
            }
        }

        for(Projectile i: AIprojectiles){
            i.ProjectileTick();
            i.drawProjectile(g, images.bossPro);
            CollisionProjectilePlayer(i , false,true);

            //System.out.printf("%f \n", 100.0);


            if(i.range <  85){
                boss.abbilityState = 2;
            }if(i.range >  85){
                boss.abbilityState = 1;
            }
            if(i.range >  400){
                AIprojectiles.remove(i);
            }
        }

        for(Item i: items){
            i.extraX = odstupanjex*screenwidth * -1;
            i.extraY = odstupanjey*screenheight * -1;
            i.drawItem(g);
        }

        for(Item i: Hearts){
            i.extraX = odstupanjex*screenwidth * -1;
            i.extraY = odstupanjey*screenheight * -1;
            i.drawItem(g);
        }

        AiBossShoot();
        DrawBullets(g, odstupanjex*screenwidth * -1 , odstupanjey*screenheight * -1);

        for(Buff i: buffs){
            if (CollisionBuffsPlayer(i)){
                if(i.speed > 0){
                    i.speedB = true;
                }if(i.streght > 0){
                    i.streghtB = true;
                }if(i.bullets > 0){
                    bulletsLeft += i.bullets;
                }if(i.hp > 0){
                    player.Lives += i.hp;
                    Hearts.add(new Item(400 + heartsgap , 10 , 10, 0 ,0 , true));

                }
            }
            if(i.speedB == true || i.streghtB == true){
                if(i.speedB == true){
                    player.playerSpeed = i.speed;
                    i.timmerSpeed +=1;
                }if(i.streghtB == true){
                    player.playerStrength = i.streght;
                    i.timmerStreght +=1;
                }
            }
            if(i.timmerSpeed/270 > 5.0){
                player.playerSpeed = 2;
                i.speedB = false;
                i.timmerSpeed = 0;
            }if(i.timmerStreght/270 > 6.0){
                player.playerStrength = 1;
                i.streghtB = false;
                i.timmerStreght = 0;
            }
            i.drawBuff(g);
        }

        Graphics2D label = (Graphics2D)g.create();
        Graphics2D labelRec = (Graphics2D)g.create();

        label.setColor(Color.yellow);
        label.setFont(new Font("Rockwell", 0, 25));
        labelRec.setColor(Color.black);
        labelRec.fillRect(1040 + odstupanjex*screenwidth * -1,735 + odstupanjey*screenheight * -1 , 105 , 30);
        label.drawString("Score: " + score , 1045 + odstupanjex*screenwidth * -1,760 + odstupanjey*screenheight * -1);

    }
}

