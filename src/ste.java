import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;



public class ste {
	static String read(String x) {
		try {
			FileReader r=new FileReader(x);
			String re="";
			int data=r.read();
			while (data !=-1) {
				re=re+(char)data;
				data=r.read();
			}
			return re;
		}catch (Exception e) {System.out.println("Bir Hata Oluþtu ");System.exit(0);return "bunu göremezsin";
		}
	}
	static void write(String x,String y,int z) throws IOException {
		try {		FileReader r=new FileReader(x);
		String re="";
		int data=r.read();
		while (data !=-1) {
			re=re+(char)data;
			data=r.read();
		}
		FileWriter w=new FileWriter(x);
		if (z==0) {
			w.append(y);
			w.close();
		}
		else if(z==1) {
			w.append(re+y);
			w.close();
		}
		else {
			System.out.println("Dosya Yazma Ýþleminde Bir hata oluþtu");
			System.out.println("write(\""+x+"\",\""+y+"\","+String.valueOf(z)+")");
			System.out.println("Kýsmýndaki "+String.valueOf(z)+"\n Yerine lütfen \n Mevcut Dosyadaki Verilerin Silinerek Yazdýrýlmasýný Ýstiyorsanýz : 0 \n Mevcur Dosyadaki verilere eklemek itiyorsanýz : 1 \n yazýnýz");
			System.exit(0);
		}}catch (Exception e) {}
		
	}
	static int count (String x,String y) {
		int ret=0;
		for (int i =0; i<x.length(); i++) {
			if (y.equals(String.valueOf(x.charAt(i)))) {
				ret++;
			}
		}
		return ret;
	}
	static<a> int count (a[] x,a y) {
		int ret=0;
		for (int i =0; i<x.length; i++) {
			if (y.equals(x[i])) {
				ret++;
			}
		}
		return ret;
	}
	static ArrayList<String> readln(String x) throws IOException {
		ArrayList<String> a= new ArrayList<String>();
		try{
			String b=read(x);
			String c="";
			for (int i =0; i<b.length();i++) {
				if ("\n".equals(String.valueOf(b.charAt(i)))) {
					c=c.replace("\n", "");
					a.add(c);
					c="";
				}
				else {
					c=c+String.valueOf(b.charAt(i));
				}
			}

		}catch(Exception s) {} return a;
	}
	static String readkey(String x0,String key) throws IOException {
		ArrayList<String> a=readln(x0);
		String value="Aradaðýnýz key bulunamadý";
		for (int i =0;i<a.size();i++) {
			if (a.get(i).indexOf(key)==0) {
				value= a.get(i).substring(a.get(i).indexOf(":")+1);
				i+=a.size()+1;
			}
		}
		return value;
	}
	static void NewJFrame(JFrame FrameName,String title,int x,int y) {
		FrameName.setTitle(title);
		FrameName.setSize(x,y);
		FrameName.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FrameName.setLayout(null);
		FrameName.setVisible(true);
	}
	static void AddButton(JButton Button,JFrame SelectedFrame,String text,int x,int y,int with,int heith,Runnable command) {
		Button.setText(text);
		Button.setBounds(x, y, with, heith);
		Button.addActionListener(e-> {command.run();} );
		SelectedFrame.add(Button);
	}
	static void sleep(int x) {
		try {Thread.sleep(x);} catch (Exception e) {}
	}
	static void Try(Runnable a) {
		try {a.run();} catch (Exception e) {}
	}
	static String Opposite(String x) {
		String y="";
		for (int i =x.length()-1; i>-1; i--) {
			y=y+x.charAt(i);
		}
		return y;
	}
	static <a> Boolean isThere(a[] arr,a x) {
		boolean a =false;
		for (int i =0; i<arr.length; i++) {
			if (String.valueOf(arr[i]).equals(String.valueOf(x))) {
				a=true;
			}
		}
		return a;
	}

}

