import javax.swing.*;

import db.MyJDBC;
import guis.LogInFormGUI;
import guis.RegisterFormGUI;

public class AppLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                new LogInFormGUI().setVisible(true);

                //lietotaja tests
                // System.out.println(MyJDBC.checkUser("user"));
            }
        });
    }
}
