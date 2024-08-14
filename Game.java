import javax.swing.*;
import java.util.*;
import java.io.File;
import java.awt.event.*;
import java.awt.Color;
class Game {
  private static int score;
  public static JButton scoreButton;
  public static void game() throws Exception{
    ArrayList<String> words = new ArrayList(138735);
    Scanner s = new Scanner(new File("words.txt"));
    while (s.hasNext()){
      words.add(s.next());
    }
    s.close();
    JFrame j = new JFrame("game");
    Frame f = new Frame(j,600,420);
    JButton submit = new JButton("submit");
    submit.setBounds(400,300,200,100);
    j.add(submit);
    scoreButton = new JButton("score: 0");
    scoreButton.setBounds(400,0,200,100);
    j.add(scoreButton);
    
    
    Dice newroll = new Dice();
    newroll.generate();
    ArrayList<Dice> die = new ArrayList<Dice>();
    for(int i=0;i<16;i++){
      die.add(new Dice(j,((char)((i/4+97)))+""+(i%4),i%4*100,i/4*100));
    }
    
    submit.addActionListener(new ActionListener(){  
      public void actionPerformed(ActionEvent e){
        if(words.contains(newroll.getWord())){
          scoreSum(newroll.getWord().length());
          words.remove(words.indexOf(newroll.getWord()));
          newroll.reset();
          newroll.setCWDisplay("correct!");
        }
        else{
          newroll.reset();
          newroll.setCWDisplay("wrong!");
        }
        for(int i=0;i<16;i++){
            die.get(i).getButton().setBackground(Color.decode("#918f8e"));
        }
      }
    });
    Countdown c = new Countdown(j,die,score);
  }
  public static void scoreSum(int len){
    if(len==3||len==4){score++;}
    if(len==5){score+=2;}
    if(len==6){score+=3;}
    if(len==7){score+=5;}
    if(len>=8){score+=11;}
    scoreButton.setText("score: "+score);
  }
  public static void reset(){
    score=0;
    Dice.reset();
  }
}