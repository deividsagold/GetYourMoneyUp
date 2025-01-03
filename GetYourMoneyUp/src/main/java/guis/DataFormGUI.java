package guis;

import contents.CommonConstants;
import db.FinanseInput;
import db.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

public class DataFormGUI<Int> extends Form{
    public DataFormGUI(){
        super("Izmaksas");
        addGuiComponents();
    }

    private void addGuiComponents(){
        JLabel nameLabel = new JLabel("Get Your Money Up");
        nameLabel.setBounds(0, 0, 520, 100);
        nameLabel.setForeground(CommonConstants.TEXT_COLOR);
        nameLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(nameLabel);


        JLabel izmaksasLabel = new JLabel("Izmaksas");
        izmaksasLabel.setBounds(215, 120, 400, 25);
        izmaksasLabel.setForeground(CommonConstants.TEXT_COLOR);
        izmaksasLabel.setFont(new Font("Dialog", Font.ITALIC, 18));
        add(izmaksasLabel);

        JTextField IzmaksasField = new JTextField();
        IzmaksasField.setBounds(125, 150, 250, 30);
        IzmaksasField.setBackground(CommonConstants.PRIMARY_COLOR);
        IzmaksasField.setForeground(CommonConstants.TEXT_COLOR);
        IzmaksasField.setFont(new Font("Dialog", Font.PLAIN, 24));
        Int summa = (Int) IzmaksasField.getText();
        //to add only integers without text

        add(IzmaksasField);

        //add button
        JButton addButton = new JButton("+");
        addButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButton.setBackground(CommonConstants.PRIMARY_COLOR);
        addButton.setBounds(95, 150, 30, 30);
        add(addButton);

        //calendar button
        JButton dateButton = new JButton("Date");
        dateButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        dateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dateButton.setBackground(CommonConstants.PRIMARY_COLOR);
        dateButton.setBounds(375, 150, 30, 30);
        add(dateButton);
        
        //saraksts window
        JPanel IzmSaraksts = new JPanel();
        //Saraksts.setLocation(100, 300);
        //Saraksts.setSize(100, 200);
        IzmSaraksts.setBounds(100, 200, 300, 300);
        IzmSaraksts.setBackground(CommonConstants.PRIMARY_COLOR);
        
        add(IzmSaraksts);

        JPanel IzmSum = new JPanel();
        IzmSum.setBackground(CommonConstants.PRIMARY_COLOR);
        IzmSum.setBounds(100, 510, 300, 50);
        add(IzmSum);


        //expanded saraksts window, idk man
        JButton ExpandButton = new JButton("Palielinat");
        ExpandButton.setBackground(CommonConstants.SECONDARY_COLOR);
        ExpandButton.setBounds(370, 200, 30, 30);
        ExpandButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        ExpandButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));     
        ExpandButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                IzmSumExpanded();
                
            }
            
        });
        add(ExpandButton);
        
    }

    public void IzmSumExpanded(){
        JPanel Expanded = new JPanel();
        Expanded.setBackground(CommonConstants.PRIMARY_COLOR);
        Expanded.setBounds(100, 510, 500, 500);
        add(Expanded);
    }
}
