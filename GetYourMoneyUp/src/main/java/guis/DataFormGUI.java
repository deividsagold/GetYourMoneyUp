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

public class DataFormGUI extends Form{
    private static int user_id;
    public DataFormGUI(int user_id){
        super("Izmaksas");
        this.user_id = user_id;
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
        IzmaksasField.setBounds(125, 150, 250, 50);
        IzmaksasField.setBackground(CommonConstants.PRIMARY_COLOR);
        IzmaksasField.setForeground(CommonConstants.TEXT_COLOR);
        IzmaksasField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(IzmaksasField);

        //add button
        JButton addButton = new JButton("+");
        addButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButton.setBackground(CommonConstants.PRIMARY_COLOR);
        addButton.setBounds(75, 150, 50, 50);
        addButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                FinanseInput AddFinanse = new FinanseInput(user_id);
                String izmaksas = IzmaksasField.getText();

                try {
                    double summa = Double.parseDouble(izmaksas);
                    summa = 0.0 + summa;
                    AddFinanse.dataInput(summa);
                } catch (NumberFormatException nfe) {
                    System.out.println("Invalid Double input");
                    JOptionPane.showMessageDialog(DataFormGUI.this, "nepareizi ievadīts skaitlis");
                }
            }
        });
        add(addButton);

        JLabel dateLabel = new JLabel();
        dateLabel.setSize(200,25);
        dateLabel.setForeground(CommonConstants.TEXT_COLOR);
        dateLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(dateLabel);

        //izmaksu saraksts window
        JPanel IzmSaraksts = new JPanel();
        IzmSaraksts.setBounds(100, 200, 300, 300);
        IzmSaraksts.setBackground(CommonConstants.PRIMARY_COLOR);


        JLabel IzmSarakstsLabel = new JLabel();
        IzmSarakstsLabel.setSize(200,25);
        IzmSarakstsLabel.setForeground(CommonConstants.TEXT_COLOR);
        IzmSarakstsLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        add(IzmSarakstsLabel);
        add(IzmSaraksts);

        //izmaksu summa
        JPanel IzmSum = new JPanel();
        IzmSum.setBackground(CommonConstants.PRIMARY_COLOR);
        IzmSum.setBounds(100, 510, 300, 50);
        add(IzmSum);

        JLabel IzmSumLabel = new JLabel();
        dateLabel.setSize(200,40);
        dateLabel.setForeground(CommonConstants.TEXT_COLOR);
        dateLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        IzmSum.add(IzmSumLabel);
        IzmSaraksts.add(IzmSarakstsLabel, dateLabel);

        //calendar button
        JButton dateButton = new JButton("Date");
        dateButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        dateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dateButton.setBackground(CommonConstants.PRIMARY_COLOR);
        dateButton.setBounds(375, 150, 50, 50);
        dateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FinanseInput reviewFinanse = new FinanseInput(user_id);
                String date = IzmaksasField.getText();

                if (reviewFinanse.checkForValidDate(date)) {

                    String sumText = reviewFinanse.returnWholeSum(date) + "€";

                    IzmSumLabel.setText(sumText);

                    String[] finanseArray = new String[5];
                    reviewFinanse.returnSmallData(finanseArray, date);
                    int i = 0;
                    String text = "<html>";
                    while (finanseArray[i] != null && i < finanseArray.length) {
                        text += finanseArray[i] + "€<br>";
                        i++;
                    }
                    text += "</html>";
                    IzmSarakstsLabel.setText(text);
                } else
                    JOptionPane.showMessageDialog(DataFormGUI.this, "Datumu formātam jābūt dd.mm.yyyy");
            }
        });
        add(dateButton);


        //expanded saraksts window, idk man
       // JButton ExpandButton = new JButton("Palielinat");
       // ExpandButton.setBackground(CommonConstants.SECONDARY_COLOR);
       // ExpandButton.setBounds(370, 200, 30, 30);
        //ExpandButton.setFont(new Font("Dialog", Font.PLAIN, 18));
       // ExpandButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       // ExpandButton.addActionListener(new ActionListener() {

         //   @Override
         //   public void actionPerformed(ActionEvent e) {
         //       IzmSumExpanded();
                
         //   }
            
       // });
       // add(ExpandButton);


    }

    public void IzmSumExpanded(){
        JPanel Expanded = new JPanel();
        Expanded.setBackground(CommonConstants.PRIMARY_COLOR);
        Expanded.setBounds(100, 510, 500, 500);
        add(Expanded);
    }
}
