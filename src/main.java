import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class main {
	static JFrame frame;
	static int[][] csh;
	static int player;
	static boolean bol2=true;
	static boolean bol=true;
	static boolean bol3=true;
	static int ClickedX=-1;
	static int ClickedY=-1;
	static String ClickedV="asd";
	static String[][] table;
	static boolean playability;
	static boolean bol4=true;
	public static void main(String[] args) throws IOException {
		String[][]table0= {
				{"8","9","s","i","j","s","9","8"},
				{"7","7","7","7","7","7","7","7"},
				{"0","0","0","0","0","0","0","0"},
				{"0","0","0","0","0","0","0","0"},
				{"0","0","0","0","0","0","0","0"},
				{"0","0","0","0","0","0","0","0"},
				{"1","1","1","1","1","1","1","1"},
				{"2","3","4","5","6","4","3","2"}					
		};
		table=table0;
		if (ste.read("login").indexOf("\\player-1\\")==-1) {
			ste.write("login", "\\player-1\\\n", 0);
			player=1;
			playability=true;
		}
		else {
			ste.write("login","\\player-2\\", 1);
			player=2;
		}
		
		frame=new JFrame();
		
		frame.setLayout(null);
		frame.setSize(620,660);
		frame.setResizable(false);
		if (ste.read("login").equals("\\player-1\\\n\\player-2\\")) {cs();}
		
		Thread th=new Thread(()-> {while (true) {
			try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
			if (!frame.isVisible() && bol) {
				try{ste.write("login", "",0);}catch (Exception e) {}
			}
			if (ste.read("login").equals("\\player-1\\\n\\player-2\\") && bol2 && player==1) {bol=false;
				frame.setVisible(false);
				cs();
				frame.setVisible(true);
				bol2=false;
				bol=true;
			}
			try {
				if (ste.readln("table").get(0).equals(((player==1)?"10":"20"))) {
					table[Integer.valueOf(ste.readln("table").get(1))][Integer.valueOf(ste.readln("table").get(2))]=ste.readln("table").get(3);
					ste.write("table","",0);
					playability=true;
				}
			}catch(Exception e0) {}
			if (ste.read("login").isEmpty()) {
				System.exit(0);}
		}});
		th.start();
		frame.setVisible(true);}

	
	static void  cs() {
		if (player==1) {
			int w=0;
			int h=0;
			for (int i=70; i<550; i+=62) {
				for (int j=70; j<550; j+=62) {
					frame.add(btn(i,j,w,h));
					h++;
			}
				h=0;
				w++;
				}
		}
		else {
			int w=7;
			int h=7;
			for (int i=70; i<550; i+=62) {
				for (int j=70; j<550; j+=62) {
					frame.add(btn(i,j,w,h));
					h--;
			}
				h=7;
				w--;
				}
		}
		JLabel lb=new JLabel();
		lb.setBounds(0,0,620,620);
		lb.setIcon(new ImageIcon("images.png"));
		frame.add(lb);
	}
	
	static JLabel btn(int x, int y,int a_x,int a_y) {
		JLabel a = new JLabel();
		a.setBounds(x,y,45,45);
		a.addMouseListener(new MouseListener() {
			@Override public void mouseReleased(MouseEvent e) {}
			@Override public void mousePressed(MouseEvent e) {}
			@Override public void mouseExited(MouseEvent e) {}		
			@Override public void mouseEntered(MouseEvent e) {}
	
			@Override
			public void mouseClicked(MouseEvent e) { if (playability) {
				try {
					if (player==1)  {
						if (ste.isThere(white(),table()[a_y][a_x])) {
							ClickedX=a_x;
							ClickedY=a_y;
							ClickedV=table()[a_y][a_x];
						}
						else if (table()[a_y][a_x].equals("0")) {
							if (ClickedV.equals("1")) {
								if ((ClickedX==a_x && ClickedY==a_y+1) || (ClickedY==6 && a_y==4)) {
									Move(a_x,a_y);
								}
								else {
									ClickedX=-1;
									ClickedY=-1;
									ClickedV="asd";
								}
							}
							else if (ClickedV.equals("2")) {

								boolean clickable=false;
								int varX=0;
								int varY=0;
								int varZ=0;
								for (int i =-8; i<8; i++) {try {if(false) {}
									else if	((ClickedX==a_x+i && ClickedY==a_y)) {varX=i;  varY=0;  varZ=(i<0) ? i*-1:i;}
									else if ((ClickedX==a_x-i && ClickedY==a_y)) {varX=-i; varY=0;  varZ=(i<0) ? i*-1:i;}
									else if ((ClickedX==a_x && ClickedY==a_y+i)) {varX=0;  varY=i; varZ=(i<0) ? i*-1:i;}
									else if ((ClickedX==a_x && ClickedY==a_y-i)) {varX=-0; varY=-i; varZ=(i<0) ? i*-1:i;}
								}catch(Exception ea) {}}if(false) {}
								else if (varX>0 && varY==0) {
									boolean test0=true;
									for (int i =0; i<varZ; i++ ) {
										if (!table()[a_y][a_x+i].equals("0")) {test0=false;}
									}
									clickable=test0;
								}
								else if (varX<0 && varY==0) {
									boolean test0=true;
									for (int i =0; i<varZ; i++ ) {
										if (!table()[a_y][a_x-i].equals("0")) {test0=false;}
									}
									clickable=test0;
								}
								else if (varX==0 && varY>0) {
									boolean test0=true;
									for (int i =0; i<varZ; i++ ) {
										if (!table()[a_y+i][a_x].equals("0")) {test0=false;}
									}
									clickable=test0;
								}
								else if (varX==0 && varY<0) {
									boolean test0=true;
									for (int i =0; i<varZ; i++ ) {
										if (!table()[a_y-i][a_x].equals("0")) {test0=false;}
									}
									clickable=test0;
								}
								if(clickable) {Move(a_x,a_y);}
								else {System.out.println("eroor");}
							
							}
							else if (ClickedV.equals("3")) {
								if ((ClickedX==a_x+1 && ClickedY==a_y+2) || (ClickedX==a_x+1 && ClickedY==a_y-2)||(ClickedX==a_x-1 && ClickedY==a_y+2)||(ClickedX==a_x-1 && ClickedY==a_y-2)||(ClickedX==a_x+2 && ClickedY==a_y+1)||(ClickedX==a_x+2 && ClickedY==a_y-1)||(ClickedX==a_x-2 && ClickedY==a_y+1)||(ClickedX==a_x-2 && ClickedY==a_y-1)) {													
									Move(a_x,a_y);
								}
								else {
									ClickedX=-1;
									ClickedY=-1;
									ClickedV="asd";
								}
							}
							else if (ClickedV.equals("4")) {
								boolean clickable=false;
								int varX=0;
								int varY=0;
								int varZ=0;
								for (int i =-8; i<8; i++) {try {if(false) {}
									else if	((ClickedX==a_x+i && ClickedY==a_y+i)) {varX=i;  varY=i;  varZ=(i<0) ? i*-1:i;}
									else if ((ClickedX==a_x-i && ClickedY==a_y+i)) {varX=-i; varY=i;  varZ=(i<0) ? i*-1:i;}
									else if ((ClickedX==a_x+i && ClickedY==a_y-i)) {varX=i;  varY=-i; varZ=(i<0) ? i*-1:i;}
									else if ((ClickedX==a_x-i && ClickedY==a_y-i)) {varX=-i; varY=-i; varZ=(i<0) ? i*-1:i;}
								}catch(Exception ea) {}}if(false) {}
								else if (varX>0 && varY>0) {
									boolean test0=true;
									for (int i =0; i<varZ; i++ ) {
										if (!table()[a_y+i][a_x+i].equals("0")) {test0=false;}
									}
									clickable=test0;
								}
								else if (varX<0 && varY>0) {
									boolean test0=true;
									for (int i =0; i<varZ; i++ ) {
										if (!table()[a_y+i][a_x-i].equals("0")) {test0=false;}
									}
									clickable=test0;
								}
								else if (varX>0 && varY<0) {
									boolean test0=true;
									for (int i =0; i<varZ; i++ ) {
										if (!table()[a_y-i][a_x+i].equals("0")) {test0=false;}
									}
									clickable=test0;
								}
								else if (varX<0 && varY<0) {
									boolean test0=true;
									for (int i =0; i<varZ; i++ ) {
										if (!table()[a_y-i][a_x-i].equals("0")) {test0=false;}
									}
									clickable=test0;
								}
								if(clickable) {Move(a_x,a_y);}
								else {System.out.println("eroor");}
							}
							else if (ClickedV.equals("5")) {

								boolean clickable=false;
								int varX=0;
								int varY=0;
								int varZ=0;
								for (int i =-8; i<8; i++) {try {if(false) {}
									else if	((ClickedX==a_x+i && ClickedY==a_y+i)) {varX=i;  varY=i;  varZ=(i<0) ? i*-1:i;}
									else if ((ClickedX==a_x-i && ClickedY==a_y+i)) {varX=-i; varY=i;  varZ=(i<0) ? i*-1:i;}
									else if ((ClickedX==a_x+i && ClickedY==a_y-i)) {varX=i;  varY=-i; varZ=(i<0) ? i*-1:i;}
									else if ((ClickedX==a_x-i && ClickedY==a_y-i)) {varX=-i; varY=-i; varZ=(i<0) ? i*-1:i;}
								}catch(Exception ea) {}}if(false) {}
								else if (varX>0 && varY>0) {
									boolean test0=true;
									for (int i =0; i<varZ; i++ ) {
										if (!table()[a_y+i][a_x+i].equals("0")) {test0=false;}
									}
									clickable=test0;
								}
								else if (varX<0 && varY>0) {
									boolean test0=true;
									for (int i =0; i<varZ; i++ ) {
										if (!table()[a_y+i][a_x-i].equals("0")) {test0=false;}
									}
									clickable=test0;
								}
								else if (varX>0 && varY<0) {
									boolean test0=true;
									for (int i =0; i<varZ; i++ ) {
										if (!table()[a_y-i][a_x+i].equals("0")) {test0=false;}
									}
									clickable=test0;
								}
								else if (varX<0 && varY<0) {
									boolean test0=true;
									for (int i =0; i<varZ; i++ ) {
										if (!table()[a_y-i][a_x-i].equals("0")) {test0=false;}
									}
									clickable=test0;
								}
								if(clickable) {Move(a_x,a_y);}
								else {

									 clickable=false;
									 varX=0;
									 varY=0;
									 varZ=0;
									for (int i =-8; i<8; i++) {try {if(false) {}
										else if	((ClickedX==a_x+i && ClickedY==a_y)) {varX=i;  varY=0;  varZ=(i<0) ? i*-1:i;}
										else if ((ClickedX==a_x-i && ClickedY==a_y)) {varX=-i; varY=0;  varZ=(i<0) ? i*-1:i;}
										else if ((ClickedX==a_x && ClickedY==a_y+i)) {varX=0;  varY=i; varZ=(i<0) ? i*-1:i;}
										else if ((ClickedX==a_x && ClickedY==a_y-i)) {varX=-0; varY=-i; varZ=(i<0) ? i*-1:i;}
									}catch(Exception ea) {}}if(false) {}
									else if (varX>0 && varY==0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y][a_x+i].equals("0")) {test0=false;}
										}
										clickable=test0;
									}
									else if (varX<0 && varY==0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y][a_x-i].equals("0")) {test0=false;}
										}
										clickable=test0;
									}
									else if (varX==0 && varY>0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y+i][a_x].equals("0")) {test0=false;}
										}
										clickable=test0;
									}
									else if (varX==0 && varY<0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y-i][a_x].equals("0")) {test0=false;}
										}
										clickable=test0;
									}
									if(clickable) {Move(a_x,a_y);}
									else {System.out.println("eroor");}
								
								}
							
							}
							else if (ClickedV.equals("6")) {
								if ((ClickedX==a_x && ClickedY==a_y+1)||(ClickedX==a_x+1 && ClickedY==a_y+1)||(ClickedX==a_x-1 && ClickedY==a_y+1)||(ClickedX==a_x+1 && ClickedY==a_y)||(ClickedX==a_x-1 && ClickedY==a_y)||(ClickedX==a_x+1 && ClickedY==a_y-1)||(ClickedX==a_x && ClickedY==a_y-1)||(ClickedX==a_x-1 && ClickedY==a_y-1)) {
									Move(a_x,a_y);
								}
							}
							else {
								ClickedX=-1;
								ClickedY=-1;
								ClickedV="asd";
							}
						}
						else if (ste.isThere(black(),table()[a_y][a_x])) {
							if (ClickedV.equals("1")) {
								if (ClickedY==a_y+1 && (ClickedX==a_x-1 || ClickedX==a_x+1)) {
									Move(a_x,a_y);
								}
								else {
									ClickedX=-1;
									ClickedY=-1;
									ClickedV="asd";
								}
							}
							else if (ClickedV.equals("2")) {
								String valueV=table()[a_y][a_x];
								boolean clickable=false;
								int varX=0;
								int varY=0;
								int varZ=0;
								for (int i =-8; i<8; i++) {try {if(false) {}
									else if	((ClickedX==a_x+i && ClickedY==a_y)) {varX=i;  varY=0;  varZ=(i<0) ? i*-1:i; SetTable(a_y, a_x, "0" );}
									else if ((ClickedX==a_x-i && ClickedY==a_y)) {varX=-i; varY=0;  varZ=(i<0) ? i*-1:i;SetTable(a_y, a_x, "0");}
									else if ((ClickedX==a_x && ClickedY==a_y+i)) {varX=0;  varY=i; varZ=(i<0) ? i*-1:i;SetTable(a_y, a_x, "0" );}
									else if ((ClickedX==a_x && ClickedY==a_y-i)) {varX=-0; varY=-i; varZ=(i<0) ? i*-1:i;SetTable(a_y, a_x, "0" );}
								}catch(Exception ea) {}}if(false) {}
								else if (varX>0 && varY==0) {
									boolean test0=true;
									for (int i =0; i<varZ; i++ ) {
										if (!table()[a_y][a_x+i].equals("0")) {test0=false;}
									}
									clickable=test0;
								}
								else if (varX<0 && varY==0) {
									boolean test0=true;
									for (int i =0; i<varZ; i++ ) {
										if (!table()[a_y][a_x-i].equals("0")) {test0=false;}
									}
									clickable=test0;
								}
								else if (varX==0 && varY>0) {
									boolean test0=true;
									for (int i =0; i<varZ; i++ ) {
										if (!table()[a_y+i][a_x].equals("0")) {test0=false;}
									}
									clickable=test0;
								}
								else if (varX==0 && varY<0) {
									boolean test0=true;
									for (int i =0; i<varZ; i++ ) {
										if (!table()[a_y-i][a_x].equals("0")) {test0=false;}
									}
									clickable=test0;
								}
								if(clickable) { SetTable(a_y, a_x, valueV);Move(a_x,a_y);}
								else {System.out.println("eroor"); SetTable(a_y, a_x, valueV);}
							
							}
							else if (ClickedV.equals("3")) {
								if ((ClickedX==a_x+1 && ClickedY==a_y+2) || (ClickedX==a_x+1 && ClickedY==a_y-2)||(ClickedX==a_x-1 && ClickedY==a_y+2)||(ClickedX==a_x-1 && ClickedY==a_y-2)||(ClickedX==a_x+2 && ClickedY==a_y+1)||(ClickedX==a_x+2 && ClickedY==a_y-1)||(ClickedX==a_x-2 && ClickedY==a_y+1)||(ClickedX==a_x-2 && ClickedY==a_y-1)) {													
									Move(a_x,a_y);
								}}
								else if (ClickedV.equals("4")) {
									boolean clickable=false;
									boolean test=true;
									int varX=0;
									int varY=0;
									int varZ=0;
									for (int i =-8; i<8; i++) {try {if(false) {}
										else if	((ClickedX==a_x+i && ClickedY==a_y+i)) { 	varX=i;		varY=i;		varZ=(i<0) ? i*-1:i;}
										else if ((ClickedX==a_x-i && ClickedY==a_y+i)) { 	varX=-i;	varY=i;		varZ=(i<0) ? i*-1:i;}
										else if ((ClickedX==a_x+i && ClickedY==a_y-i)) {    varX=i;		varY=-i;	varZ=(i<0) ? i*-1:i;}
										else if ((ClickedX==a_x-i && ClickedY==a_y-i)) { 	varX=-i;	varY=-i;	varZ=(i<0) ? i*-1:i;}
									}catch(Exception ea) {}}if(false) {}
									else if (varX>0 && varY>0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y+i][a_x+i].equals("0") && !ste.isThere(black(),table()[a_y+i][a_x+i] )) {test0=false;}
										}
										clickable=test0;
									}
									else if (varX<0 && varY>0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y+i][a_x-i].equals("0")&& !ste.isThere(black(),table()[a_y+i][a_x+i])) {test0=false;}
										}
										clickable=test0;
									}
									else if (varX>0 && varY<0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y-i][a_x+i].equals("0")&& !ste.isThere(black(),table()[a_y+i][a_x+i])) {test0=false;}
										}
										clickable=test0;
									}
									else if (varX<0 && varY<0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y-i][a_x-i].equals("0")&& !ste.isThere(black(),table()[a_y+i][a_x+i])) {test0=false;}
										}
										clickable=test0;
									}
									if(clickable) {Move(a_x,a_y);}
									else {}
								}
								else if (ClickedV.equals("5")) {

									boolean clickable=false;
									boolean test=true;
									int varX=0;
									int varY=0;
									int varZ=0;
									for (int i =-8; i<8; i++) {try {if(false) {}
										else if	((ClickedX==a_x+i && ClickedY==a_y+i)) { 	varX=i;		varY=i;		varZ=(i<0) ? i*-1:i;}
										else if ((ClickedX==a_x-i && ClickedY==a_y+i)) { 	varX=-i;	varY=i;		varZ=(i<0) ? i*-1:i;}
										else if ((ClickedX==a_x+i && ClickedY==a_y-i)) {    varX=i;		varY=-i;	varZ=(i<0) ? i*-1:i;}
										else if ((ClickedX==a_x-i && ClickedY==a_y-i)) { 	varX=-i;	varY=-i;	varZ=(i<0) ? i*-1:i;}
									}catch(Exception ea) {}}if(false) {}
									else if (varX>0 && varY>0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y+i][a_x+i].equals("0") && !ste.isThere(black(),table()[a_y+i][a_x+i] )) {test0=false;}
										}
										clickable=test0;
									}
									else if (varX<0 && varY>0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y+i][a_x-i].equals("0")&& !ste.isThere(black(),table()[a_y+i][a_x+i])) {test0=false;}
										}
										clickable=test0;
									}
									else if (varX>0 && varY<0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y-i][a_x+i].equals("0")&& !ste.isThere(black(),table()[a_y+i][a_x+i])) {test0=false;}
										}
										clickable=test0;
									}
									else if (varX<0 && varY<0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y-i][a_x-i].equals("0")&& !ste.isThere(black(),table()[a_y+i][a_x+i])) {test0=false;}
										}
										clickable=test0;
									}
									if(clickable) {Move(a_x,a_y);}
									else {
										String valueV=table()[a_y][a_x];
										 clickable=false;
										varX=0;
										varY=0;
										varZ=0;
										for (int i =-8; i<8; i++) {try {if(false) {}
											else if	((ClickedX==a_x+i && ClickedY==a_y)) {varX=i;  varY=0;  varZ=(i<0) ? i*-1:i; SetTable(a_y, a_x, "0" );}
											else if ((ClickedX==a_x-i && ClickedY==a_y)) {varX=-i; varY=0;  varZ=(i<0) ? i*-1:i;SetTable(a_y, a_x, "0");}
											else if ((ClickedX==a_x && ClickedY==a_y+i)) {varX=0;  varY=i; varZ=(i<0) ? i*-1:i;SetTable(a_y, a_x, "0" );}
											else if ((ClickedX==a_x && ClickedY==a_y-i)) {varX=-0; varY=-i; varZ=(i<0) ? i*-1:i;SetTable(a_y, a_x, "0" );}
										}catch(Exception ea) {}}if(false) {}
										else if (varX>0 && varY==0) {
											boolean test0=true;
											for (int i =0; i<varZ; i++ ) {
												if (!table()[a_y][a_x+i].equals("0")) {test0=false;}
											}
											clickable=test0;
										}
										else if (varX<0 && varY==0) {
											boolean test0=true;
											for (int i =0; i<varZ; i++ ) {
												if (!table()[a_y][a_x-i].equals("0")) {test0=false;}
											}
											clickable=test0;
										}
										else if (varX==0 && varY>0) {
											boolean test0=true;
											for (int i =0; i<varZ; i++ ) {
												if (!table()[a_y+i][a_x].equals("0")) {test0=false;}
											}
											clickable=test0;
										}
										else if (varX==0 && varY<0) {
											boolean test0=true;
											for (int i =0; i<varZ; i++ ) {
												if (!table()[a_y-i][a_x].equals("0")) {test0=false;}
											}
											clickable=test0;
										}
										if(clickable) { SetTable(a_y, a_x, valueV);Move(a_x,a_y);}
										else {System.out.println("eroor"); SetTable(a_y, a_x, valueV);}
									
									}}
								else if (ClickedV.equals("6")) {

									if ((ClickedX==a_x && ClickedY==a_y+1)||(ClickedX==a_x+1 && ClickedY==a_y+1)||(ClickedX==a_x-1 && ClickedY==a_y+1)||(ClickedX==a_x+1 && ClickedY==a_y)||(ClickedX==a_x-1 && ClickedY==a_y)||(ClickedX==a_x+1 && ClickedY==a_y-1)||(ClickedX==a_x && ClickedY==a_y-1)||(ClickedX==a_x-1 && ClickedY==a_y-1)) {
										Move(a_x,a_y);
									}
								
								}
								else {
									ClickedX=-1;
									ClickedY=-1;
									ClickedV="asd";
								}
						}
					}
					else {
						if (ste.isThere(black(),table()[a_y][a_x])) {
							ClickedX=a_x;
							ClickedY=a_y;
							ClickedV=table()[a_y][a_x];
						}
						else if (table()[a_y][a_x].equals("0")) {
							if (ClickedV.equals("7")) {
								if ((ClickedX==a_x && ClickedY==a_y-1) || (ClickedY==1 && a_y==3)) {
									Move(a_x,a_y);
								}
								else {
									ClickedX=-1;
									ClickedY=-1;
									ClickedV="asd";
								}
							}
							else if (ClickedV.equals("8")) {

								boolean clickable=false;
								int varX=0;
								int varY=0;
								int varZ=0;
								for (int i =-8; i<8; i++) {try {if(false) {}
									else if	((ClickedX==a_x+i && ClickedY==a_y)) {varX=i;  varY=0;  varZ=(i<0) ? i*-1:i;}
									else if ((ClickedX==a_x-i && ClickedY==a_y)) {varX=-i; varY=0;  varZ=(i<0) ? i*-1:i;}
									else if ((ClickedX==a_x && ClickedY==a_y+i)) {varX=0;  varY=i; varZ=(i<0) ? i*-1:i;}
									else if ((ClickedX==a_x && ClickedY==a_y-i)) {varX=-0; varY=-i; varZ=(i<0) ? i*-1:i;}
								}catch(Exception ea) {}}if(false) {}
								else if (varX>0 && varY==0) {
									boolean test0=true;
									for (int i =0; i<varZ; i++ ) {
										if (!table()[a_y][a_x+i].equals("0")) {test0=false;}
									}
									clickable=test0;
								}
								else if (varX<0 && varY==0) {
									boolean test0=true;
									for (int i =0; i<varZ; i++ ) {
										if (!table()[a_y][a_x-i].equals("0")) {test0=false;}
									}
									clickable=test0;
								}
								else if (varX==0 && varY>0) {
									boolean test0=true;
									for (int i =0; i<varZ; i++ ) {
										if (!table()[a_y+i][a_x].equals("0")) {test0=false;}
									}
									clickable=test0;
								}
								else if (varX==0 && varY<0) {
									boolean test0=true;
									for (int i =0; i<varZ; i++ ) {
										if (!table()[a_y-i][a_x].equals("0")) {test0=false;}
									}
									clickable=test0;
								}
								if(clickable) {Move(a_x,a_y);}
								else {System.out.println("eroor");}
							
							}
							else if (ClickedV.equals("9")) {
								if ((ClickedX==a_x+1 && ClickedY==a_y+2) || (ClickedX==a_x+1 && ClickedY==a_y-2)||(ClickedX==a_x-1 && ClickedY==a_y+2)||(ClickedX==a_x-1 && ClickedY==a_y-2)||(ClickedX==a_x+2 && ClickedY==a_y+1)||(ClickedX==a_x+2 && ClickedY==a_y-1)||(ClickedX==a_x-2 && ClickedY==a_y+1)||(ClickedX==a_x-2 && ClickedY==a_y-1)) {													
									Move(a_x,a_y);
								}}
								else if (ClickedV.equals("s")) {
									boolean clickable=false;
									int varX=0;
									int varY=0;
									int varZ=0;
									for (int i =-8; i<8; i++) {try {if(false) {}
										else if	((ClickedX==a_x+i && ClickedY==a_y+i)) {varX=i;  varY=i;  varZ=(i<0) ? i*-1:i;}
										else if ((ClickedX==a_x-i && ClickedY==a_y+i)) {varX=-i; varY=i;  varZ=(i<0) ? i*-1:i;}
										else if ((ClickedX==a_x+i && ClickedY==a_y-i)) {varX=i;  varY=-i; varZ=(i<0) ? i*-1:i;}
										else if ((ClickedX==a_x-i && ClickedY==a_y-i)) {varX=-i; varY=-i; varZ=(i<0) ? i*-1:i;}
									}catch(Exception ea) {}}if(false) {}
									else if (varX>0 && varY>0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y+i][a_x+i].equals("0")) {test0=false;}
										}
										clickable=test0;
									}
									else if (varX<0 && varY>0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y+i][a_x-i].equals("0")) {test0=false;}
										}
										clickable=test0;
									}
									else if (varX>0 && varY<0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y-i][a_x+i].equals("0")) {test0=false;}
										}
										clickable=test0;
									}
									else if (varX<0 && varY<0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y-i][a_x-i].equals("0")) {test0=false;}
										}
										clickable=test0;
									}
									if(clickable) {Move(a_x,a_y);}
									else {System.out.println("eroor");}
								}
								else if (ClickedV.equals("i")) {

									boolean clickable=false;
									int varX=0;
									int varY=0;
									int varZ=0;
									for (int i =-8; i<8; i++) {try {if(false) {}
										else if	((ClickedX==a_x+i && ClickedY==a_y+i)) {varX=i;  varY=i;  varZ=(i<0) ? i*-1:i;}
										else if ((ClickedX==a_x-i && ClickedY==a_y+i)) {varX=-i; varY=i;  varZ=(i<0) ? i*-1:i;}
										else if ((ClickedX==a_x+i && ClickedY==a_y-i)) {varX=i;  varY=-i; varZ=(i<0) ? i*-1:i;}
										else if ((ClickedX==a_x-i && ClickedY==a_y-i)) {varX=-i; varY=-i; varZ=(i<0) ? i*-1:i;}
									}catch(Exception ea) {}}if(false) {}
									else if (varX>0 && varY>0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y+i][a_x+i].equals("0")) {test0=false;}
										}
										clickable=test0;
									}
									else if (varX<0 && varY>0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y+i][a_x-i].equals("0")) {test0=false;}
										}
										clickable=test0;
									}
									else if (varX>0 && varY<0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y-i][a_x+i].equals("0")) {test0=false;}
										}
										clickable=test0;
									}
									else if (varX<0 && varY<0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y-i][a_x-i].equals("0")) {test0=false;}
										}
										clickable=test0;
									}
									if(clickable) {Move(a_x,a_y);}
									else {

										clickable=false;
										 varX=0;
										 varY=0;
										 varZ=0;
										for (int i =-8; i<8; i++) {try {if(false) {}
											else if	((ClickedX==a_x+i && ClickedY==a_y)) {varX=i;  varY=0;  varZ=(i<0) ? i*-1:i;}
											else if ((ClickedX==a_x-i && ClickedY==a_y)) {varX=-i; varY=0;  varZ=(i<0) ? i*-1:i;}
											else if ((ClickedX==a_x && ClickedY==a_y+i)) {varX=0;  varY=i; varZ=(i<0) ? i*-1:i;}
											else if ((ClickedX==a_x && ClickedY==a_y-i)) {varX=-0; varY=-i; varZ=(i<0) ? i*-1:i;}
										}catch(Exception ea) {}}if(false) {}
										else if (varX>0 && varY==0) {
											boolean test0=true;
											for (int i =0; i<varZ; i++ ) {
												if (!table()[a_y][a_x+i].equals("0")) {test0=false;}
											}
											clickable=test0;
										}
										else if (varX<0 && varY==0) {
											boolean test0=true;
											for (int i =0; i<varZ; i++ ) {
												if (!table()[a_y][a_x-i].equals("0")) {test0=false;}
											}
											clickable=test0;
										}
										else if (varX==0 && varY>0) {
											boolean test0=true;
											for (int i =0; i<varZ; i++ ) {
												if (!table()[a_y+i][a_x].equals("0")) {test0=false;}
											}
											clickable=test0;
										}
										else if (varX==0 && varY<0) {
											boolean test0=true;
											for (int i =0; i<varZ; i++ ) {
												if (!table()[a_y-i][a_x].equals("0")) {test0=false;}
											}
											clickable=test0;
										}
										if(clickable) {Move(a_x,a_y);}
										else {System.out.println("eroor");}
									
									}
								
								}
								else if(ClickedV.equals("j")) {

									if ((ClickedX==a_x && ClickedY==a_y+1)||(ClickedX==a_x+1 && ClickedY==a_y+1)||(ClickedX==a_x-1 && ClickedY==a_y+1)||(ClickedX==a_x+1 && ClickedY==a_y)||(ClickedX==a_x-1 && ClickedY==a_y)||(ClickedX==a_x+1 && ClickedY==a_y-1)||(ClickedX==a_x && ClickedY==a_y-1)||(ClickedX==a_x-1 && ClickedY==a_y-1)) {
										Move(a_x,a_y);
									}
								
								}
								else {
										ClickedX=-1;
										ClickedY=-1;
										ClickedV="asd";
								}
						}
						else if (ste.isThere(white(),table()[a_y][a_x])) {
							if (ClickedV.equals("7") ) {
								if (ClickedY==a_y-1 && (ClickedX==a_x+1 || ClickedX==a_x-1)) {
									Move(a_x,a_y);
								}
								else {	
								ClickedX=-1;
								ClickedY=-1;
								ClickedV="asd";
								}
							}
							else if (ClickedV.equals("8")) {
								String valueV=table()[a_y][a_x];
								boolean clickable=false;
								int varX=0;
								int varY=0;
								int varZ=0;
								for (int i =-8; i<8; i++) {try {if(false) {}
									else if	((ClickedX==a_x+i && ClickedY==a_y)) {varX=i;  varY=0;  varZ=(i<0) ? i*-1:i; SetTable(a_y, a_x, "0");}
									else if ((ClickedX==a_x-i && ClickedY==a_y)) {varX=-i; varY=0;  varZ=(i<0) ? i*-1:i;SetTable(a_y, a_x, "0" );}
									else if ((ClickedX==a_x && ClickedY==a_y+i)) {varX=0;  varY=i; varZ=(i<0) ? i*-1:i;SetTable(a_y, a_x, "0" );}
									else if ((ClickedX==a_x && ClickedY==a_y-i)) {varX=-0; varY=-i; varZ=(i<0) ? i*-1:i;SetTable(a_y, a_x, "0");}
								}catch(Exception ea) {}}if(false) {}
								else if (varX>0 && varY==0) {
									boolean test0=true;
									for (int i =0; i<varZ; i++ ) {
										if (!table()[a_y][a_x+i].equals("0")) {test0=false;}
									}
									clickable=test0;
								}
								else if (varX<0 && varY==0) {
									boolean test0=true;
									for (int i =0; i<varZ; i++ ) {
										if (!table()[a_y][a_x-i].equals("0")) {test0=false;}
									}
									clickable=test0;
								}
								else if (varX==0 && varY>0) {
									boolean test0=true;
									for (int i =0; i<varZ; i++ ) {
										if (!table()[a_y+i][a_x].equals("0")) {test0=false;}
									}
									clickable=test0;
								}
								else if (varX==0 && varY<0) {
									boolean test0=true;
									for (int i =0; i<varZ; i++ ) {
										if (!table()[a_y-i][a_x].equals("0")) {test0=false;}
									}
									clickable=test0;
								}
								if(clickable) { SetTable(a_y, a_x, valueV);Move(a_x,a_y);}
								else {System.out.println("eroor"); SetTable(a_y, a_x, valueV);}
							
							}
							else if (ClickedV.equals("9")) {
								if ((ClickedX==a_x+1 && ClickedY==a_y+2) || (ClickedX==a_x+1 && ClickedY==a_y-2)||(ClickedX==a_x-1 && ClickedY==a_y+2)||(ClickedX==a_x-1 &&ClickedY==a_y-2)||(ClickedX==a_x+2 && ClickedY==a_y+1)||(ClickedX==a_x+2 && ClickedY==a_y-1)||(ClickedX==a_x-2 && ClickedY==a_y+1)||(ClickedX==a_x-2 && ClickedY==a_y-1)) {													
									Move(a_x,a_y);
								}}
								else if (ClickedV.equals("s")) {
									boolean clickable=false;
									int varX=0;
									int varY=0;
									int varZ=0;
									for (int i =-8; i<8; i++) {try {if(false) {}
										else if	((ClickedX==a_x+i && ClickedY==a_y+i)) {varX=i;  varY=i;  varZ=(i<0) ? i*-1:i;}
										else if ((ClickedX==a_x-i && ClickedY==a_y+i)) {varX=-i; varY=i;  varZ=(i<0) ? i*-1:i;}
										else if ((ClickedX==a_x+i && ClickedY==a_y-i)) {varX=i;  varY=-i; varZ=(i<0) ? i*-1:i;}
										else if ((ClickedX==a_x-i && ClickedY==a_y-i)) {varX=-i; varY=-i; varZ=(i<0) ? i*-1:i;}
									}catch(Exception ea) {}}if(false) {}
									else if (varX>0 && varY>0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y+i][a_x+i].equals("0")&&!ste.isThere(white(),table()[a_y+i][a_x+i])) {test0=false;}
										}
										clickable=test0;
									}
									else if (varX<0 && varY>0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y+i][a_x-i].equals("0")&&!ste.isThere(white(),table()[a_y+i][a_x+i])) {test0=false;}
										}
										clickable=test0;
									}
									else if (varX>0 && varY<0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y-i][a_x+i].equals("0")&&!ste.isThere(white(),table()[a_y+i][a_x+i])) {test0=false;}
										}
										clickable=test0;
									}
									else if (varX<0 && varY<0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y-i][a_x-i].equals("0")&&!ste.isThere(white(),table()[a_y+i][a_x+i])) {test0=false;}
										}
										clickable=test0;
									}
									if(clickable) {Move(a_x,a_y);}
									else {System.out.println("eroor");}
								}
								else if (ClickedV.equals("i")) {

									boolean clickable=false;
									int varX=0;
									int varY=0;
									int varZ=0;
									for (int i =-8; i<8; i++) {try {if(false) {}
										else if	((ClickedX==a_x+i && ClickedY==a_y+i)) {varX=i;  varY=i;  varZ=(i<0) ? i*-1:i;}
										else if ((ClickedX==a_x-i && ClickedY==a_y+i)) {varX=-i; varY=i;  varZ=(i<0) ? i*-1:i;}
										else if ((ClickedX==a_x+i && ClickedY==a_y-i)) {varX=i;  varY=-i; varZ=(i<0) ? i*-1:i;}
										else if ((ClickedX==a_x-i && ClickedY==a_y-i)) {varX=-i; varY=-i; varZ=(i<0) ? i*-1:i;}
									}catch(Exception ea) {}}if(false) {}
									else if (varX>0 && varY>0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y+i][a_x+i].equals("0")&&!ste.isThere(white(),table()[a_y+i][a_x+i])) {test0=false;}
										}
										clickable=test0;
									}
									else if (varX<0 && varY>0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y+i][a_x-i].equals("0")&&!ste.isThere(white(),table()[a_y+i][a_x+i])) {test0=false;}
										}
										clickable=test0;
									}
									else if (varX>0 && varY<0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y-i][a_x+i].equals("0")&&!ste.isThere(white(),table()[a_y+i][a_x+i])) {test0=false;}
										}
										clickable=test0;
									}
									else if (varX<0 && varY<0) {
										boolean test0=true;
										for (int i =0; i<varZ; i++ ) {
											if (!table()[a_y-i][a_x-i].equals("0")&&!ste.isThere(white(),table()[a_y+i][a_x+i])) {test0=false;}
										}
										clickable=test0;
									}
									if(clickable) {Move(a_x,a_y);}
									else {
										String valueV=table()[a_y][a_x];
										clickable=false;
										 varX=0;
										 varY=0;
										 varZ=0;
										for (int i =-8; i<8; i++) {try {if(false) {}
											else if	((ClickedX==a_x+i && ClickedY==a_y)) {varX=i;  varY=0;  varZ=(i<0) ? i*-1:i; SetTable(a_y, a_x, "0");}
											else if ((ClickedX==a_x-i && ClickedY==a_y)) {varX=-i; varY=0;  varZ=(i<0) ? i*-1:i;SetTable(a_y, a_x, "0");}
											else if ((ClickedX==a_x && ClickedY==a_y+i)) {varX=0;  varY=i; varZ=(i<0) ? i*-1:i;SetTable(a_y, a_x, "0");}
											else if ((ClickedX==a_x && ClickedY==a_y-i)) {varX=-0; varY=-i; varZ=(i<0) ? i*-1:i;SetTable(a_y, a_x, "0");}
										}catch(Exception ea) {}}if(false) {}
										else if (varX>0 && varY==0) {
											boolean test0=true;
											for (int i =0; i<varZ; i++ ) {
												if (!table()[a_y][a_x+i].equals("0")) {test0=false;}
											}
											clickable=test0;
										}
										else if (varX<0 && varY==0) {
											boolean test0=true;
											for (int i =0; i<varZ; i++ ) {
												if (!table()[a_y][a_x-i].equals("0")) {test0=false;}
											}
											clickable=test0;
										}
										else if (varX==0 && varY>0) {
											boolean test0=true;
											for (int i =0; i<varZ; i++ ) {
												if (!table()[a_y+i][a_x].equals("0")) {test0=false;}
											}
											clickable=test0;
										}
										else if (varX==0 && varY<0) {
											boolean test0=true;
											for (int i =0; i<varZ; i++ ) {
												if (!table()[a_y-i][a_x].equals("0")) {test0=false;}
											}
											clickable=test0;
										}
										if(clickable) { SetTable(a_y, a_x, valueV);Move(a_x,a_y);}
										else {System.out.println("eroor"); SetTable(a_y, a_x, valueV);}
									
									}
								
								}
								else if (ClickedV.equals("j")) {

									if ((ClickedX==a_x && ClickedY==a_y+1)||(ClickedX==a_x+1 && ClickedY==a_y+1)||(ClickedX==a_x-1 && ClickedY==a_y+1)||(ClickedX==a_x+1 && ClickedY==a_y)||(ClickedX==a_x-1 && ClickedY==a_y)||(ClickedX==a_x+1 && ClickedY==a_y-1)||(ClickedX==a_x && ClickedY==a_y-1)||(ClickedX==a_x-1 && ClickedY==a_y-1)) {
										Move(a_x,a_y);
									}
								
								}
							else {
									ClickedX=-1;
									ClickedY=-1;
									ClickedV="asd";
								}}
						}
					
						
				}catch (Exception e0) {}
			}
				
			}
		}
		);
		Thread th0 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					ste.sleep(100);
					if (bol4) {
					try {
						if (table()[a_y][a_x].equals("1")) {
							a.setIcon(new ImageIcon("zlz.png"));
						}
						else if (table()[a_y][a_x].equals("7")) {
							a.setIcon(new ImageIcon("jBa (2).png"));
						}
						else if (table()[a_y][a_x].equals("2")) {
							a.setIcon(new ImageIcon("zlj.png"));
						}
						else if (table()[a_y][a_x].equals("8")) {
							a.setIcon(new ImageIcon("jbc.png"));
						}
						else if (table()[a_y][a_x].equals("3")) {
							a.setIcon(new ImageIcon("zlf.png"));
						}
						else if (table()[a_y][a_x].equals("9")) {
							a.setIcon(new ImageIcon("jby.png"));
						}
						else if (table()[a_y][a_x].equals("4")) {
							a.setIcon(new ImageIcon("dne.png"));
						}
						else if (table()[a_y][a_x].equals("s")) {
							a.setIcon(new ImageIcon("jbm.png"));
						}
						else if (table()[a_y][a_x].equals("5")) {
							a.setIcon(new ImageIcon("zla.png"));
						}
						else if (table()[a_y][a_x].equals("i")) {
							a.setIcon(new ImageIcon("jbz.png"));
						}
						else if (table()[a_y][a_x].equals("6")) {
							a.setIcon(new ImageIcon("zlu.png"));
						}
						else if (table()[a_y][a_x].equals("j")) {
							a.setIcon(new ImageIcon("jbd.png"));
						}
						else if (table()[a_y][a_x].equals("0")) {
							a.setIcon(new ImageIcon("0.png"));
						}
					} catch(Exception e){}
				}
				
			}}
		});
		th0.start();
		return a;
	}
	static String[][] table() throws IOException {
		return table;
	}
	static String[] black() {
		String[] a={"7","8","9","s","i","j"};
		return a;
	}
	static String[] white() {
		String[] a={"1","2","3","4","5","6"};
		return a;
	}
	static void SetTable(int y ,int x ,String s) throws IOException {boolean t=true;while (t) {
		if (ste.read("table").isEmpty()) {
			table[y][x]=s;
			ste.write("table", (player==1) ? "20\n":"10\n", 0);
			ste.write("table", String.valueOf(y)+"\n", 1);
			ste.write("table", String.valueOf(x)+"\n", 1);
			ste.write("table", s+"\n", 1);
			t=false;
		}
	}}

	static void Move(int x,int y) throws IOException {
		String[][] tablex=table;
		String testV =tablex[y][x];
		tablex[ClickedY][ClickedX]="0";
		tablex[y][x]=ClickedV;
				SetTable(ClickedY,ClickedX,"0");
				SetTable(y,x,ClickedV);
				playability=false;
		Thread thr=new Thread(()-> {
			
				try {
					bol4=false;
					if (!king(tablex)) {
							SetTable(ClickedY,ClickedX,ClickedV);
							SetTable(y,x,testV);
							playability=true;
						}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			bol4=true;
			ClickedX=-1;
			ClickedY=-1;
			ClickedV="asd";
		});
		thr.start();
	}
	static boolean king(String[][] ar) throws IOException {
		boolean a =true;
		int K_X = -1;
		int K_Y=-1;
		for (int i =0; i<8; i++) {for (int j =0; j<8; j++) {if (ar[i][j]==((player==1)?"6":"j")) {K_X=j;K_Y=i;}}}
		System.out.println(K_X+" "+K_Y);
		for (int a_y =0; a_y<8; a_y++) {for (int a_x=0; a_x<8; a_x++) {
			if (player==1) {
				if (table[a_y][a_x].equals("7") && a_y+1==K_Y && (a_x+1==K_X || a_x-1==K_X)) {
				a=false;
				}
				else if (table[a_y][a_x].equals("8")) {
					String valueV=table[K_Y][K_X];
					boolean clickable=false;
					int varX=0;
					int varY=0;
					int varZ=0;
					for (int i =-8; i<8; i++) {try {if(false) {}
						else if	((a_x==K_X+i && a_y==K_Y)) {varX=i;  varY=0;  varZ=(i<0) ? i*-1:i; SetTable(K_Y, K_X, "0" );}
						else if ((a_x==K_X-i && a_y==K_Y)) {varX=-i; varY=0;  varZ=(i<0) ? i*-1:i;SetTable(K_Y, K_X, "0");}
						else if ((a_x==K_X && a_y==K_Y+i)) {varX=0;  varY=i; varZ=(i<0) ? i*-1:i;SetTable(K_Y, K_X, "0" );}
						else if ((a_x==K_X && a_y==K_Y-i)) {varX=-0; varY=-i; varZ=(i<0) ? i*-1:i;SetTable(K_Y, K_X, "0" );}
					}catch(Exception ea) {}}if(false) {}
					else if (varX>0 && varY==0) {
						boolean test0=true;
						for (int i =0; i<varZ; i++ ) {
							if (!table[K_Y][K_X+i].equals("0")) {test0=false;}
						}
						clickable=test0;
					}
					else if (varX<0 && varY==0) {
						boolean test0=true;
						for (int i =0; i<varZ; i++ ) {
							if (!table[K_Y][K_X-i].equals("0")) {test0=false;}
						}
						clickable=test0;
					}
					else if (varX==0 && varY>0) {
						boolean test0=true;
						for (int i =0; i<varZ; i++ ) {
							if (!table[K_Y+i][K_X].equals("0")) {test0=false;}
						}
						clickable=test0;
					}
					else if (varX==0 && varY<0) {
						boolean test0=true;
						for (int i =0; i<varZ; i++ ) {
							if (!table[K_Y-i][K_X].equals("0")) {test0=false;}
						}
						clickable=test0;
					}
					if(clickable) {a=false;}
					else {System.out.println("eroor"); SetTable(K_Y, K_X, valueV);}
					SetTable(K_Y, K_X, valueV);
				
				}
				else if (table[a_y][a_x].equals("9")) {
					if ((K_X==a_x+1 && K_Y==a_y+2) || (K_X==a_x+1 && K_Y==a_y-2)||(K_X==a_x-1 && K_Y==a_y+2)||(K_X==a_x-1 && K_Y==a_y-2)||(K_X==a_x+2 && K_Y==a_y+1)||(K_X==a_x+2 && K_Y==a_y-1)||(K_X==a_x-2 && K_Y==a_y+1)||(K_X==a_x-2 && K_Y==a_y-1)) {													
						a=false;
					}}
				else if (table[a_y][a_x].equals("s")) {
					try {
						SetTable(K_Y,K_X,"0");
						boolean clickable=false;
						int varX=0;
						int varY=0;
						int varZ=0;
						for (int i =-8; i<8; i++) {try {if(false) {}
							else if	((a_x==K_X+i && a_y==K_Y+i)) {varX=i;  varY=i;  varZ=(i<0) ? i*-1:i; System.out.println("deneme");}
							else if ((a_x==K_X-i && a_y==K_Y+i)) {varX=-i; varY=i;  varZ=(i<0) ? i*-1:i;System.out.println("deneme");}
							else if ((a_x==K_X+i && a_y==K_Y-i)) {varX=i;  varY=-i; varZ=(i<0) ? i*-1:i;System.out.println("deneme");}
							else if ((a_x==K_X-i && a_y==K_Y-i)) {varX=-i; varY=-i; varZ=(i<0) ? i*-1:i;System.out.println("deneme");}
						}catch(Exception ea) {}}if(false) {}
						else if (varX>0 && varY>0) {
							boolean test0=true;
							for (int i =0; i<varZ; i++ ) {
								if (!table()[K_Y+i][K_X+i].equals("0")) {test0=false;}
							}
							clickable=test0;
						}
						else if (varX<0 && varY>0) {
							boolean test0=true;
							for (int i =0; i<varZ; i++ ) {
								if (!table()[K_Y+i][K_X-i].equals("0")) {test0=false;}
							}
							clickable=test0;
						}
						else if (varX>0 && varY<0) {
							boolean test0=true;
							for (int i =0; i<varZ; i++ ) {
								if (!table()[K_Y-i][K_X+i].equals("0")) {test0=false;}
							}
							clickable=test0;
						}
						else if (varX<0 && varY<0) {
							boolean test0=true;
							for (int i =0; i<varZ; i++ ) {
								if (!table()[K_Y-i][K_X-i].equals("0")) {test0=false;}
							}
							clickable=test0;
						}
						SetTable(K_Y,K_X,"6");
						if(clickable) {a=false;}
						else {System.out.println("eroor");}
					
					} catch(Exception e0) {}
				}
				else if (table[a_y][a_x].equals("i")) {

					try {
						SetTable(K_Y,K_X,"0");
						boolean clickable=false;
						int varX=0;
						int varY=0;
						int varZ=0;
						for (int i =-8; i<8; i++) {try {if(false) {}
							else if	((a_x==K_X+i && a_y==K_Y+i)) {varX=i;  varY=i;  varZ=(i<0) ? i*-1:i; System.out.println("deneme");}
							else if ((a_x==K_X-i && a_y==K_Y+i)) {varX=-i; varY=i;  varZ=(i<0) ? i*-1:i;System.out.println("deneme");}
							else if ((a_x==K_X+i && a_y==K_Y-i)) {varX=i;  varY=-i; varZ=(i<0) ? i*-1:i;System.out.println("deneme");}
							else if ((a_x==K_X-i && a_y==K_Y-i)) {varX=-i; varY=-i; varZ=(i<0) ? i*-1:i;System.out.println("deneme");}
						}catch(Exception ea) {}}if(false) {}
						else if (varX>0 && varY>0) {
							boolean test0=true;
							for (int i =0; i<varZ; i++ ) {
								if (!table()[K_Y+i][K_X+i].equals("0")) {test0=false;}
							}
							clickable=test0;
						}
						else if (varX<0 && varY>0) {
							boolean test0=true;
							for (int i =0; i<varZ; i++ ) {
								if (!table()[K_Y+i][K_X-i].equals("0")) {test0=false;}
							}
							clickable=test0;
						}
						else if (varX>0 && varY<0) {
							boolean test0=true;
							for (int i =0; i<varZ; i++ ) {
								if (!table()[K_Y-i][K_X+i].equals("0")) {test0=false;}
							}
							clickable=test0;
						}
						else if (varX<0 && varY<0) {
							boolean test0=true;
							for (int i =0; i<varZ; i++ ) {
								if (!table()[K_Y-i][K_X-i].equals("0")) {test0=false;}
							}
							clickable=test0;
						}
						SetTable(K_Y,K_X,"6");
						if(clickable) {a=false;}
						else {
							String valueV=table[K_Y][K_X];
							 clickable=false;
							varX=0;
							 varY=0;
							 varZ=0;
							for (int i =-8; i<8; i++) {try {if(false) {}
								else if	((a_x==K_X+i && a_y==K_Y)) {varX=i;  varY=0;  varZ=(i<0) ? i*-1:i; SetTable(K_Y, K_X, "0" );}
								else if ((a_x==K_X-i && a_y==K_Y)) {varX=-i; varY=0;  varZ=(i<0) ? i*-1:i;SetTable(K_Y, K_X, "0");}
								else if ((a_x==K_X && a_y==K_Y+i)) {varX=0;  varY=i; varZ=(i<0) ? i*-1:i;SetTable(K_Y, K_X, "0" );}
								else if ((a_x==K_X && a_y==K_Y-i)) {varX=-0; varY=-i; varZ=(i<0) ? i*-1:i;SetTable(K_Y, K_X, "0" );}
							}catch(Exception ea) {}}if(false) {}
							else if (varX>0 && varY==0) {
								boolean test0=true;
								for (int i =0; i<varZ; i++ ) {
									if (!table[K_Y][K_X+i].equals("0")) {test0=false;}
								}
								clickable=test0;
							}
							else if (varX<0 && varY==0) {
								boolean test0=true;
								for (int i =0; i<varZ; i++ ) {
									if (!table[K_Y][K_X-i].equals("0")) {test0=false;}
								}
								clickable=test0;
							}
							else if (varX==0 && varY>0) {
								boolean test0=true;
								for (int i =0; i<varZ; i++ ) {
									if (!table[K_Y+i][K_X].equals("0")) {test0=false;}
								}
								clickable=test0;
							}
							else if (varX==0 && varY<0) {
								boolean test0=true;
								for (int i =0; i<varZ; i++ ) {
									if (!table[K_Y-i][K_X].equals("0")) {test0=false;}
								}
								clickable=test0;
							}
							if(clickable) {a=false;}
							else {System.out.println("eroor"); SetTable(K_Y, K_X, valueV);}
							SetTable(K_Y, K_X, valueV);
						
						}
					
					} catch(Exception e0) {}
				
				
				}
				}
			
			else {
				if (table[a_y][a_x].equals("1") && a_y-1==K_Y && (a_x+1==K_X || a_x-1==K_X)) {
					a=false;
			}
				else if (table[a_y][a_x].equals("2")) {
					String valueV=table[K_Y][K_X];
					boolean clickable=false;
					int varX=0;
					int varY=0;
					int varZ=0;
					for (int i =-8; i<8; i++) {try {if(false) {}
						else if	((a_x==K_X+i && a_y==K_Y)) {varX=i;  varY=0;  varZ=(i<0) ? i*-1:i; SetTable(K_Y, K_X, "0" );}
						else if ((a_x==K_X-i && a_y==K_Y)) {varX=-i; varY=0;  varZ=(i<0) ? i*-1:i;SetTable(K_Y, K_X, "0");}
						else if ((a_x==K_X && a_y==K_Y+i)) {varX=0;  varY=i; varZ=(i<0) ? i*-1:i;SetTable(K_Y, K_X, "0" );}
						else if ((a_x==K_X && a_y==K_Y-i)) {varX=-0; varY=-i; varZ=(i<0) ? i*-1:i;SetTable(K_Y, K_X, "0" );}
					}catch(Exception ea) {}}if(false) {}
					else if (varX>0 && varY==0) {
						boolean test0=true;
						for (int i =0; i<varZ; i++ ) {
							if (!table[K_Y][K_X+i].equals("0")) {test0=false;}
						}
						clickable=test0;
					}
					else if (varX<0 && varY==0) {
						boolean test0=true;
						for (int i =0; i<varZ; i++ ) {
							if (!table[K_Y][K_X-i].equals("0")) {test0=false;}
						}
						clickable=test0;
					}
					else if (varX==0 && varY>0) {
						boolean test0=true;
						for (int i =0; i<varZ; i++ ) {
							if (!table[K_Y+i][K_X].equals("0")) {test0=false;}
						}
						clickable=test0;
					}
					else if (varX==0 && varY<0) {
						boolean test0=true;
						for (int i =0; i<varZ; i++ ) {
							if (!table[K_Y-i][K_X].equals("0")) {test0=false;}
						}
						clickable=test0;
					}
					if(clickable) {a=false;}
					else {System.out.println("eroor"); SetTable(K_Y, K_X, valueV);}
					SetTable(K_Y, K_X, valueV);
				
				}
				else if (table[a_y][a_x].equals("3")) {
					if ((K_X==a_x+1 && K_Y==a_y+2) || (K_X==a_x+1 && K_Y==a_y-2)||(K_X==a_x-1 && K_Y==a_y+2)||(K_X==a_x-1 && K_Y==a_y-2)||(K_X==a_x+2 && K_Y==a_y+1)||(K_X==a_x+2 && K_Y==a_y-1)||(K_X==a_x-2 && K_Y==a_y+1)||(K_X==a_x-2 && K_Y==a_y-1)) {													
						a=false;
					}}
				else if (table[a_y][a_x].equals("4")) {
					try {
						SetTable(K_Y,K_X,"0");
						boolean clickable=false;
						int varX=0;
						int varY=0;
						int varZ=0;
						for (int i =-8; i<8; i++) {try {if(false) {}
							else if	((a_x==K_X+i && a_y==K_Y+i)) {varX=i;  varY=i;  varZ=(i<0) ? i*-1:i; System.out.println("deneme");}
							else if ((a_x==K_X-i && a_y==K_Y+i)) {varX=-i; varY=i;  varZ=(i<0) ? i*-1:i;System.out.println("deneme");}
							else if ((a_x==K_X+i && a_y==K_Y-i)) {varX=i;  varY=-i; varZ=(i<0) ? i*-1:i;System.out.println("deneme");}
							else if ((a_x==K_X-i && a_y==K_Y-i)) {varX=-i; varY=-i; varZ=(i<0) ? i*-1:i;System.out.println("deneme");}
						}catch(Exception ea) {}}if(false) {}
						else if (varX>0 && varY>0) {
							boolean test0=true;
							for (int i =0; i<varZ; i++ ) {
								if (!table()[K_Y+i][K_X+i].equals("0")) {test0=false;}
							}
							clickable=test0;
						}
						else if (varX<0 && varY>0) {
							boolean test0=true;
							for (int i =0; i<varZ; i++ ) {
								if (!table()[K_Y+i][K_X-i].equals("0")) {test0=false;}
							}
							clickable=test0;
						}
						else if (varX>0 && varY<0) {
							boolean test0=true;
							for (int i =0; i<varZ; i++ ) {
								if (!table()[K_Y-i][K_X+i].equals("0")) {test0=false;}
							}
							clickable=test0;
						}
						else if (varX<0 && varY<0) {
							boolean test0=true;
							for (int i =0; i<varZ; i++ ) {
								if (!table()[K_Y-i][K_X-i].equals("0")) {test0=false;}
							}
							clickable=test0;
						}
						SetTable(K_Y,K_X,"j");
						if(clickable) {a=false;}
						else {System.out.println("eroor");}
					
					} catch(Exception e0) {}
				
				}
				else if (table[a_y][a_x].equals("5")) {

					String valueV=table[K_Y][K_X];
					boolean clickable=false;
					int varX=0;
					int varY=0;
					int varZ=0;
					for (int i =-8; i<8; i++) {try {if(false) {}
						else if	((a_x==K_X+i && a_y==K_Y)) {varX=i;  varY=0;  varZ=(i<0) ? i*-1:i; SetTable(K_Y, K_X, "0" );}
						else if ((a_x==K_X-i && a_y==K_Y)) {varX=-i; varY=0;  varZ=(i<0) ? i*-1:i;SetTable(K_Y, K_X, "0");}
						else if ((a_x==K_X && a_y==K_Y+i)) {varX=0;  varY=i; varZ=(i<0) ? i*-1:i;SetTable(K_Y, K_X, "0" );}
						else if ((a_x==K_X && a_y==K_Y-i)) {varX=-0; varY=-i; varZ=(i<0) ? i*-1:i;SetTable(K_Y, K_X, "0" );}
					}catch(Exception ea) {}}if(false) {}
					else if (varX>0 && varY==0) {
						boolean test0=true;
						for (int i =0; i<varZ; i++ ) {
							if (!table[K_Y][K_X+i].equals("0")) {test0=false;}
						}
						clickable=test0;
					}
					else if (varX<0 && varY==0) {
						boolean test0=true;
						for (int i =0; i<varZ; i++ ) {
							if (!table[K_Y][K_X-i].equals("0")) {test0=false;}
						}
						clickable=test0;
					}
					else if (varX==0 && varY>0) {
						boolean test0=true;
						for (int i =0; i<varZ; i++ ) {
							if (!table[K_Y+i][K_X].equals("0")) {test0=false;}
						}
						clickable=test0;
					}
					else if (varX==0 && varY<0) {
						boolean test0=true;
						for (int i =0; i<varZ; i++ ) {
							if (!table[K_Y-i][K_X].equals("0")) {test0=false;}
						}
						clickable=test0;
					}
					SetTable(K_Y, K_X, valueV);
					if(clickable) {a=false;}
					else {
						try {
							SetTable(K_Y,K_X,"0");
							clickable=false;
							 varX=0;
							 varY=0;
							 varZ=0;
							for (int i =-8; i<8; i++) {try {if(false) {}
								else if	((a_x==K_X+i && a_y==K_Y+i)) {varX=i;  varY=i;  varZ=(i<0) ? i*-1:i; System.out.println("deneme");}
								else if ((a_x==K_X-i && a_y==K_Y+i)) {varX=-i; varY=i;  varZ=(i<0) ? i*-1:i;System.out.println("deneme");}
								else if ((a_x==K_X+i && a_y==K_Y-i)) {varX=i;  varY=-i; varZ=(i<0) ? i*-1:i;System.out.println("deneme");}
								else if ((a_x==K_X-i && a_y==K_Y-i)) {varX=-i; varY=-i; varZ=(i<0) ? i*-1:i;System.out.println("deneme");}
							}catch(Exception ea) {}}if(false) {}
							else if (varX>0 && varY>0) {
								boolean test0=true;
								for (int i =0; i<varZ; i++ ) {
									if (!table()[K_Y+i][K_X+i].equals("0")) {test0=false;}
								}
								clickable=test0;
							}
							else if (varX<0 && varY>0) {
								boolean test0=true;
								for (int i =0; i<varZ; i++ ) {
									if (!table()[K_Y+i][K_X-i].equals("0")) {test0=false;}
								}
								clickable=test0;
							}
							else if (varX>0 && varY<0) {
								boolean test0=true;
								for (int i =0; i<varZ; i++ ) {
									if (!table()[K_Y-i][K_X+i].equals("0")) {test0=false;}
								}
								clickable=test0;
							}
							else if (varX<0 && varY<0) {
								boolean test0=true;
								for (int i =0; i<varZ; i++ ) {
									if (!table()[K_Y-i][K_X-i].equals("0")) {test0=false;}
								}
								clickable=test0;
							}
							SetTable(K_Y,K_X,"j");
							if(clickable) {a=false;}
							else {System.out.println("eroor");}
						
						} catch(Exception e0) {}
					
					}
				
				
				}
				}
		}}
		return a;
	}
}
