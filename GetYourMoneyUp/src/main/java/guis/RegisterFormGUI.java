package guis;

import contents.CommonConstants;
import db.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterFormGUI extends Form{
    public RegisterFormGUI(){
        super("Register");
        addGuiComponents();
    }

    private void addGuiComponents(){
        JLabel registerLabel = new JLabel("Registrejies");
        registerLabel.setBounds(0, 25, 520, 100);
        registerLabel.setForeground(CommonConstants.TEXT_COLOR);
        registerLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(registerLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 150, 400, 25);
        usernameLabel.setForeground(CommonConstants.TEXT_COLOR);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JTextField usernameField = new JTextField();
        usernameField.setBounds(30, 185, 450, 55);
        usernameField.setBackground(CommonConstants.PRIMARY_COLOR);
        usernameField.setForeground(CommonConstants.TEXT_COLOR);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(usernameLabel);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 200, 450, 25);
        passwordLabel.setForeground(CommonConstants.TEXT_COLOR);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30, 365, 450, 55);
        passwordField.setBackground(CommonConstants.PRIMARY_COLOR);
        passwordField.setForeground(CommonConstants.TEXT_COLOR);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(passwordLabel);
        add(passwordField);



        JButton registerButton = new JButton("Pieregistrejies");
        registerButton.setFont(new Font("Dialog", Font.BOLD, 18));

        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setBackground(CommonConstants.PRIMARY_COLOR);
        registerButton.setBounds(125, 520, 250, 50);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if(validateUserInput(username, password)){
                    if(MyJDBC.register(username, password)){
                        RegisterFormGUI.this.dispose();

                        LogInFormGUI loginFormGUI = new LogInFormGUI();
                        loginFormGUI.setVisible(true);

                        JOptionPane.showMessageDialog(loginFormGUI, "Veiksmigi registrejies!");
                    }else {
                        JOptionPane.showMessageDialog(RegisterFormGUI.this, "Lietotajvards ir aiznemts");
                    }
                    }else{
                        JOptionPane.showMessageDialog(RegisterFormGUI.this,
                                "Error: Lietotajvardam ir jabut vismaz 6 simboliem garam \n"+
                                "un/vai parolei ir jabut vismaz 6 simboliem garam");
                    }
                }
        });
        add(registerButton);

        JLabel loginLabel = new JLabel("Esi lietotajs? Ienac caur sejieni!");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.setForeground(CommonConstants.TEXT_COLOR);

        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                RegisterFormGUI.this.dispose();

                new LogInFormGUI().setVisible(true);
            }
        });
        loginLabel.setBounds(125, 600, 250, 30);
        add(loginLabel);
    }
    private boolean validateUserInput(String username, String password){
        if(username.isEmpty() || password.isEmpty()) return false;

        if(username.length() < 6) return false;
        if(password.length() < 6) return false;

        return true;
    }
}
