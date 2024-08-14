import java.util.concurrent.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Countdown{
  public Countdown(JFrame f,ArrayList<Dice> die,int score) {
    JButton timer = new JButton("");
    timer.setBounds(400,100,200,100);
    f.add(timer);
    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    final Runnable runnable = new Runnable() {
    int countdownStarter = 180;
    public void run() {
      if(countdownStarter%60<10){timer.setText(""+countdownStarter/60+":"+0+countdownStarter%60);}
      else{timer.setText(""+countdownStarter/60 + ":"+countdownStarter%60);}
      countdownStarter--;

      if (countdownStarter < 0) {
        timer.setText("<html>Game Over!<br />Click here to reroll</html>");
        timer.addActionListener(new ActionListener(){  
      public void actionPerformed(ActionEvent e){
        try{
          f.dispose();
          Game.reset();
          Game.game();
        }
        catch(Exception dsfae){
          System.out.println("error");
        }
      }
    });
        for(int i=0;i<16;i++){
          die.get(i).getButton().setVisible(false);
        }
        scheduler.shutdown();
        }
      }
    };
    scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
  }
}