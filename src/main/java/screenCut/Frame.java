package screenCut;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class Frame extends JFrame implements ActionListener {
    private JPanel panel1;
    private JButton catchBtn1;
    private JButton catchBtn2;
    private JButton catchBtn31;
    private JButton catchBtn32;
    private JLabel label1;
    private JLabel label2;
    private JLabel labelPage;
    private JLabel label31;
    private JLabel label32;
    private JLabel folderL;
    private JTextField field11;
    private JTextField field12;
    private JTextField field13;
    private JTextField field14;
    private JTextField field21;
    private JTextField field22;
    private JTextField field23;
    private JTextField field24;
    private JTextField field311;
    private JTextField field312;
    private JTextField field313;
    private JTextField field314;
    private JTextField field321;
    private JTextField field322;
    private JTextField field323;
    private JTextField field324;
    private JTextField folderField;
    private JButton snipBtn1;
    private JButton snipBtn2;
    private JButton snipBtn31;
    private JButton snipBtn32;
    private JTextField times;
    private JTextField pagesField;
    private BufferedImage get;
    private int ButtonListen = 0;
    private int SnipListen = 0;
    private int page = 1;
    int maxX,maxY,minX,minY;
    Boolean SINGLE = false;
    Boolean DOUBLE = false;
    public Frame(){
        super("螢幕擷取軟體");
        initWindow();
    }
    private void initWindow() {

        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        catchBtn1 = new JButton("getCoordinate");
        catchBtn2 = new JButton("getCoordinate");
        catchBtn31 = new JButton("getCoordinate");
        catchBtn32 = new JButton("getCoordinate");
        label1  = new JLabel("封面              ");
        label2  = new JLabel("全彩頁          ");
        label31 = new JLabel("單數頁          ");
        label32 = new JLabel("雙數頁          ");
        folderL = new JLabel("資料夾");
        labelPage = new JLabel("頁數");
        field11 = new JTextField(5);//startx
        field12 = new JTextField(5);//starty
        field13 = new JTextField(5);//endx
        field14 = new JTextField(5);//endy
        field21 = new JTextField(5);
        field22 = new JTextField(5);
        field23 = new JTextField(5);
        field24 = new JTextField(5);
        field311 = new JTextField(5);
        field312 = new JTextField(5);
        field313 = new JTextField(5);
        field314 = new JTextField(5);
        field321 = new JTextField(5);
        field322 = new JTextField(5);
        field323 = new JTextField(5);
        field324 = new JTextField(5);
        folderField = new JTextField(15);
        pagesField = new JTextField(10);

//F11全螢幕，漫畫截單頁 0, 195, 1080, 1726
        field11.setText("0");
        field12.setText("195");
        field13.setText("1080");
        field14.setText("1726");
        field311.setText("0");
        field312.setText("150");
        field313.setText("1080");
        field314.setText("1771");
        pagesField.setText("1");

        snipBtn1  = new JButton("  開始截首頁  ");
        snipBtn2  = new JButton("  開始截彩頁  ");
        snipBtn31 = new JButton("  開始截單頁  ");
        snipBtn32 = new JButton("  開始截雙頁  ");
        times = new JTextField(5);

        catchBtn1.addActionListener(this);
        catchBtn2.addActionListener(this);
        catchBtn31.addActionListener(this);
        catchBtn32.addActionListener(this);
        snipBtn1.addActionListener(this);
        snipBtn2.addActionListener(this);
        snipBtn31.addActionListener(this);
        snipBtn32.addActionListener(this);

        panel1.add(label1);
        panel1.add(field11);
        panel1.add(field12);
        panel1.add(field13);
        panel1.add(field14);
        panel1.add(catchBtn1);
        panel1.add(label2);
        panel1.add(field21);
        panel1.add(field22);
        panel1.add(field23);
        panel1.add(field24);
        panel1.add(catchBtn2);
        panel1.add(label31);
        panel1.add(field311);
        panel1.add(field312);
        panel1.add(field313);
        panel1.add(field314);
        panel1.add(catchBtn31);
        panel1.add(label32);
        panel1.add(field321);
        panel1.add(field322);
        panel1.add(field323);
        panel1.add(field324);
        panel1.add(catchBtn32);
        panel1.add(snipBtn1);
        panel1.add(snipBtn2);
        panel1.add(snipBtn31);
        panel1.add(snipBtn32);
        panel1.add(times);
        panel1.add(folderL);
        panel1.add(folderField);
        panel1.add(labelPage);
        panel1.add(pagesField);

        this.getContentPane().add(panel1,BorderLayout.CENTER);
        this.setSize(500,250);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
//        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source=ae.getSource();
        if(source == catchBtn1){
            ButtonListen = 1 ;
        } else if (source == catchBtn2) {
            ButtonListen = 2 ;
        }else if (source == catchBtn31) {
            ButtonListen = 3 ;
        }else if (source == snipBtn1) {
            SnipListen = 1 ;
        } else if (source == snipBtn2) {
            SnipListen = 2 ;
        }else if (source == snipBtn31) {
            SnipListen = 3 ;
        }
        if(ButtonListen > 0)
            doStart();
        else if ( SnipListen ==1 || SnipListen ==2 ){
            if (SnipListen == 1 ) {
                maxX = Math.max(Integer.valueOf(field11.getText()), Integer.valueOf(field13.getText()));
                maxY = Math.max(Integer.valueOf(field12.getText()), Integer.valueOf(field14.getText()));
                minX = Math.min(Integer.valueOf(field11.getText()), Integer.valueOf(field13.getText()));
                minY = Math.min(Integer.valueOf(field12.getText()), Integer.valueOf(field14.getText()));
            }else if (SnipListen == 2 ){
                maxX = Math.max(Integer.valueOf(field21.getText()), Integer.valueOf(field23.getText()));
                maxY = Math.max(Integer.valueOf(field22.getText()), Integer.valueOf(field24.getText()));
                minX = Math.min(Integer.valueOf(field21.getText()), Integer.valueOf(field23.getText()));
                minY = Math.min(Integer.valueOf(field22.getText()), Integer.valueOf(field24.getText()));
            }
            snip( maxX, maxY, minX ,minY,1);
        }else if (SnipListen == 3 ){
            SINGLE = true;
            initPrint();

        }
    }

    private void initPrint() {

        try {
            int time = Integer.valueOf(times.getText());
            for (int i=0;i<time;i++){
                print();
            }

        }catch (Exception e){
            print();
        }finally {
            SINGLE = false;
            page = 1;
            pagesField.setText(String.valueOf(page));

            this.setVisible(true);
        }
    }

    private void print() {
        maxX = Math.max(Integer.valueOf(field311.getText()), Integer.valueOf(field313.getText()));
        maxY = Math.max(Integer.valueOf(field312.getText()), Integer.valueOf(field314.getText()));
        minX = Math.min(Integer.valueOf(field311.getText()), Integer.valueOf(field313.getText()));
        minY = Math.min(Integer.valueOf(field312.getText()), Integer.valueOf(field314.getText()));
        if(SINGLE){
            snip( maxX, maxY, minX, minY, 1);
        }
    }

    public void snip( int maxx,int maxy,int minx,int miny, int finish){
        try{
            page = Integer.valueOf(pagesField.getText());
        }catch (Exception e){
            page = 1;
        }
        Robot robot = null;
        try {
            if(true) {
                this.setVisible(false);
                Thread.sleep(800);
            }
            robot = new Robot();
            Rectangle rect = new Rectangle(minx, miny, maxx-minx, maxy-miny);
            BufferedImage image = robot.createScreenCapture(rect);
            String path = "D:/待上傳/"+folderField.getText();
            File dir = new File(path);
            dir.mkdir();
            ImageIO.write(image, "png", new File(path + "/" + page++ + ".png"));
            pagesField.setText(String.valueOf(page));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            this.setVisible(true);
            if(finish == 1){
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);//切換程式forcus
                robot.keyPress(KeyEvent.VK_PAGE_DOWN);
                robot.keyRelease(KeyEvent.VK_PAGE_DOWN);//真正換頁
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void doStart(){
        try{
            this.setVisible(false);
            Thread.sleep(500);//睡500毫秒是為了讓主窗完全不見
            Robot ro=new Robot();
            Toolkit tk=Toolkit.getDefaultToolkit();
            Dimension di=tk.getScreenSize();
            Rectangle rec=new Rectangle(0,0,di.width,di.height);
            BufferedImage bi=ro.createScreenCapture(rec);
            JFrame jf=new JFrame();
            Temp temp=new Temp(jf,bi,di.width,di.height);
            jf.getContentPane().add(temp,BorderLayout.CENTER);
            jf.setUndecorated(true);
            jf.setSize(di);
            jf.setVisible(true);
            jf.setAlwaysOnTop(true);
        } catch(Exception exe){
            exe.printStackTrace();
        }
    }
    private void updates() {
        this.setVisible(true);
        ButtonListen = 0;
        SnipListen = 0;
    }
    private class Temp extends JPanel implements MouseListener, MouseMotionListener {
        Integer x;
        Integer y;
        private BufferedImage bi;
        private int width,height;
        private int startX,startY,endX,endY,tempX,tempY;
        private JFrame jf;
        private Rectangle select=new Rectangle(0,0,0,0);//表示選中的區域
        private Cursor cs=new Cursor(Cursor.CROSSHAIR_CURSOR);//表示一般情況下的滑鼠狀態
        private States current= States.DEFAULT;// 表示當前的編輯狀態
        private Rectangle[] rec;//表示八個編輯點的區域
        //下面四個常量,分別表示誰是被選中的那條線上的端點
        public static final int START_X=1;
        public static final int START_Y=2;
        public static final int END_X=3;
        public static final int END_Y=4;
        private int currentX,currentY;//當前被選中的X和Y,只有這兩個需要改變
        private Point p=new Point();//當前滑鼠移的地點
        private boolean showTip=true;//是否顯示提示.如果滑鼠左鍵一按,則提示不再顯了
        public Temp(JFrame jf,BufferedImage bi,int width,int height){
            this.jf=jf;
            this.bi=bi;
            this.width=width;
            this.height=height;
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            initRecs();
        }
        private void initRecs(){
            rec=new Rectangle[8];
            for(int i=0;i<rec.length;i++  ){
                rec[i]=new Rectangle();
            }
        }
        public void paintComponent(Graphics g){
            g.drawImage(bi,0,0,width,height,this);
            g.setColor(Color.RED);
            g.drawLine(startX,startY,endX,startY);
            g.drawLine(startX,endY,endX,endY);
            g.drawLine(startX,startY,startX,endY);
            g.drawLine(endX,startY,endX,endY);
            int x=startX<endX?startX:endX;
            int y=startY<endY?startY:endY;
            select=new Rectangle(x,y,Math.abs(endX-startX),Math.abs(endY-startY));
            int x1=(startX +endX)/2;
            int y1=(startY +endY)/2;
//            g.fillRect(x1-2,startY-2,5,5);
//            g.fillRect(x1-2,endY-2,5,5);
//            g.fillRect(startX-2,y1-2,5,5);
//            g.fillRect(endX-2,y1-2,5,5);
//            g.fillRect(startX-2,startY-2,5,5);
//            g.fillRect(startX-2,endY-2,5,5);
//            g.fillRect(endX-2,startY-2,5,5);
//            g.fillRect(endX-2,endY-2,5,5);
            rec[0]=new Rectangle(x-5,y-5,10,10);
            rec[1]=new Rectangle(x1-5,y-5,10,10);
            rec[2]=new Rectangle((startX>endX?startX:endX)-5,y-5,10,10);
            rec[3]=new Rectangle((startX>endX?startX:endX)-5,y1-5,10,10);
            rec[4]=new Rectangle((startX>endX?startX:endX)-5,(startY>endY?startY:endY)-5,10,10);
            rec[5]=new Rectangle(x1-5,(startY>endY?startY:endY)-5,10,10);
            rec[6]=new Rectangle(x-5,(startY>endY?startY:endY)-5,10,10);
            rec[7]=new Rectangle(x-5,y1-5,10,10);
            if(showTip){
                g.setColor(Color.CYAN);
                g.fillRect(p.x,p.y,170,20);
                g.setColor(Color.RED);
                g.drawRect(p.x,p.y,170,20);
                g.setColor(Color.BLACK);
                g.drawString("請按住滑鼠左鍵不放選擇截圖區",p.x,p.y+ 15);
            }
        }
        //根據東南西北等八個方向決定選中的要修改的X和Y的座標
        private void initSelect(States state){
            switch(state){
                case DEFAULT:
                    currentX=0;
                    currentY=0;
                    break;
                case EAST:
                    currentX=(endX>startX?END_X:START_X);
                    currentY=0;
                    break;
                case WEST:
                    currentX=(endX>startX?START_X:END_X);
                    currentY=0;
                    break;
                case NORTH:
                    currentX=0;
                    currentY=(startY>endY?END_Y:START_Y);
                    break;
                case SOUTH:
                    currentX=0;
                    currentY=(startY>endY?START_Y:END_Y);
                    break;
                case NORTH_EAST:
                    currentY=(startY>endY?END_Y:START_Y);
                    currentX=(endX>startX?END_X:START_X);
                    break;
                case NORTH_WEST:
                    currentY=(startY>endY?END_Y:START_Y);
                    currentX=(endX>startX?START_X:END_X);
                    break;
                case SOUTH_EAST:
                    currentY=(startY>endY?START_Y:END_Y);
                    currentX=(endX>startX?END_X:START_X);
                    break;
                case SOUTH_WEST:
                    currentY=(startY>endY?START_Y:END_Y);
                    currentX=(endX>startX?START_X:END_X);
                    break;
                default:
                    currentX=0;
                    currentY=0;
                    break;
            }
        }
        public void mouseMoved(MouseEvent me){
            doMouseMoved(me);
            initSelect(current);
            if(showTip){
                p=me.getPoint();
                repaint();
            }
        }
        //特意定義一個方法處理滑鼠移動,是為了每次都能初始化一下所要選擇的地區
        private void doMouseMoved(MouseEvent me){
            if(select.contains(me.getPoint())){
                this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
                current= States.MOVE;
            } else{
                States[] st= States.values();
                for(int i=0;i<rec.length;i++  ){
                    if(rec[i].contains(me.getPoint())){
                        current=st[i];
                        this.setCursor(st[i].getCursor());
                        return;
                    }
                }
                this.setCursor(cs);
                current= States.DEFAULT;
            }
        }
        public void mouseExited(MouseEvent me){
        }
        public void mouseEntered(MouseEvent me){
        }
        public void mouseDragged(MouseEvent me){
            int x=me.getX();
            int y=me.getY();
            if(current== States.MOVE){
                startX =(x-tempX);
                startY =(y-tempY);
                endX =(x-tempX);
                endY =(y-tempY);
                tempX=x;
                tempY=y;
            }else if(current== States.EAST||current== States.WEST){
                if(currentX==START_X){
                    startX =(x-tempX);
                    tempX=x;
                }else{
                    endX =(x-tempX);
                    tempX=x;
                }
            }else if(current== States.NORTH||current== States.SOUTH){
                if(currentY==START_Y){
                    startY =(y-tempY);
                    tempY=y;
                }else{
                    endY =(y-tempY);
                    tempY=y;
                }
            }else if(current== States.NORTH_EAST||current== States.NORTH_EAST||
                    current== States.SOUTH_EAST||current== States.SOUTH_WEST){
                if(currentY==START_Y){
                    startY =(y-tempY);
                    tempY=y;
                }else{
                    endY =(y-tempY);
                    tempY=y;
                }
                if(currentX==START_X){
                    startX =(x-tempX);
                    tempX=x;
                }else{
                    endX =(x-tempX);
                    tempX=x;
                }
            }else{
                startX=tempX;
                startY=tempY;
                endX=me.getX();
                endY=me.getY();
            }
            this.repaint();
        }

        public void mousePressed(MouseEvent me){
            showTip=false;
            tempX=me.getX();
            tempY=me.getY();
//            System.out.println(""+me.getXOnScreen());
//            System.out.println(""+me.getYOnScreen());
            fillPressed(me);
        }

        public void mouseReleased(MouseEvent me){
//            System.out.println(""+me.getXOnScreen());
//            System.out.println(""+me.getYOnScreen());
            fillRelease(me);
            jf.dispose();
            updates();
        }

        private void fillPressed(MouseEvent me) {
            x = me.getXOnScreen();
            y = me.getYOnScreen();
            if (ButtonListen == 1){
                field11.setText(x.toString());
                field12.setText(y.toString());
            }else if (ButtonListen == 2){
                field21.setText(x.toString());
                field22.setText(y.toString());
            }else if (ButtonListen == 3){
                field311.setText(x.toString());
                field312.setText(y.toString());
            }else if (ButtonListen == 4){
                field321.setText(x.toString());
                field322.setText(y.toString());
            }
        }
        private void fillRelease(MouseEvent me) {
            x = me.getXOnScreen();
            y = me.getYOnScreen();
            if (ButtonListen == 1){
                field13.setText(x.toString());
                field14.setText(y.toString());
            }else if (ButtonListen == 2){
                field23.setText(x.toString());
                field24.setText(y.toString());
            }else if (ButtonListen == 3){
                field313.setText(x.toString());
                field314.setText(y.toString());
            }else if (ButtonListen == 4){
                field323.setText(x.toString());
                field324.setText(y.toString());
            }
        }

        public void mouseClicked(MouseEvent me){
        }


    }


}


enum States{
    NORTH_WEST(new Cursor(Cursor.NW_RESIZE_CURSOR)),//表示西北角
    NORTH(new Cursor(Cursor.N_RESIZE_CURSOR)),
    NORTH_EAST(new Cursor(Cursor.NE_RESIZE_CURSOR)),
    EAST(new Cursor(Cursor.E_RESIZE_CURSOR)),
    SOUTH_EAST(new Cursor(Cursor.SE_RESIZE_CURSOR)),
    SOUTH(new Cursor(Cursor.S_RESIZE_CURSOR)),
    SOUTH_WEST(new Cursor(Cursor.SW_RESIZE_CURSOR)),
    WEST(new Cursor(Cursor.W_RESIZE_CURSOR)),
    MOVE(new Cursor(Cursor.MOVE_CURSOR)),
    DEFAULT(new Cursor(Cursor.DEFAULT_CURSOR));
    private Cursor cs;
    States(Cursor cs){
        this.cs=cs;
    }
    public Cursor getCursor(){
        return cs;
    }
}