import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;
import java.lang.Math;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;
public class Dice{
  private static ArrayList<String> diceList = new ArrayList<String>(16);
  private static Scanner sc;
  private String face;
  public JButton b;
  private String ID;
  private int x;
  private int y;
  private static String curword="";
  private static String curtile;
  private static ArrayList<String> usedDice = new ArrayList<String>(16);
  public static JButton word = new JButton("current word: ");
  public Dice(JFrame f, String id, int ix, int iy){
    x=ix;
    y=iy;
    ID=id;
    face=diceList.get((((int) ID.charAt(0))-97)*4+Integer.parseInt(ID.substring(1)));
    b=new JButton(face);
    b.setBackground(Color.decode("#918f8e"));
    Border bored = BorderFactory.createLineBorder(Color.decode("#6E6E6E"));
    b.setBorder(bored);
    b.setBounds(x,y,100,100);
    b.setForeground(Color.decode("#000000"));
    word.setBounds(400,200,200,100);
    f.add(b);
    f.add(word);
    b.addActionListener(new ActionListener(){  
      public void actionPerformed(ActionEvent e){
        //init arraylist and save ids of all the previous chars and dont let them overlap until submitted
        if(usedDice.contains(ID)==false){
          try{
            int trymove=ID.charAt(0)-curtile.charAt(0);
            if(trymove==-1||trymove==1||trymove==0){
              trymove=Integer.parseInt(ID.substring(1))-Integer.parseInt(curtile.substring(1));
              if(trymove==-1||trymove==1||trymove==0){
                b.setBackground(Color.decode("#9A2E2E"));
                curtile=ID;
                curword+=face;
                usedDice.add(curtile);
                word.setText("current word: "+curword);
              }
            }
          }
          catch(Exception eg){
            b.setBackground(Color.decode("#9A2E2E"));
            curtile=ID;
            curword+=face;
            usedDice.add(curtile);
            word.setText("current word: "+curword);
          }
        }
      }  
        
    });
  }
  public Dice() throws FileNotFoundException{
    File f = new File("dielist.txt");
    sc = new Scanner(f);
    
  }
  //randomly generate which face each die will be on
  public static void generate(){
    for(int i=0;i<16;i++){
      int num = (int)(Math.random()*6);
      String txtDie = sc.nextLine();
      for(int j=0;j<6;j++){
        if(j==num){
          if(txtDie.charAt(j)!='0'){
            diceList.add(Character.toString(txtDie.charAt(j)));
          }
          else{
            diceList.add("QU");
          }
        }
      }
    }
    sc.close();//testing this
    //randomizes dice order
    Collections.shuffle(diceList);
  }
  public String toString(){
    return "id#: "+ID+" x: "+x+" y:"+y;
  }
  public static String getWord(){
    return curword.toLowerCase();
  }
  public static void reset(){
    curword="";
    usedDice.clear();
    curtile=null;
    word.setText("current word:");
  }
  public JButton getButton(){
    return b;
  }
  public static void setCWDisplay(String text){
    word.setText(text);
  }
}
