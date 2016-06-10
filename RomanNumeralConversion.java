/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roman.numeral.conversion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author rburkhardt
 */
public class RomanNumeralConversion {

    public static JFrame frame;
    public static JRadioButton romanToArabicRadio, arabicToRomanRadio;
    public static JTextField inputField, outputField;
    public static String inuputString;
    public static JButton convertButton;
    
    public static void main(String[] args) {
        frame = new JFrame("Roman Numeral Conversion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        romanToArabicRadio = new JRadioButton("Roman to Arabic");
        arabicToRomanRadio = new JRadioButton("Arabic to Roman");
        
        ButtonGroup selectConversionGroup = new ButtonGroup();
        selectConversionGroup.add(romanToArabicRadio);
        selectConversionGroup.add(arabicToRomanRadio);
        
        JPanel radioPanel = new JPanel(new GridLayout(1, 2));
        radioPanel.add(romanToArabicRadio);
        radioPanel.add(arabicToRomanRadio);
        romanToArabicRadio.setSelected(true);
        mainPanel.add(radioPanel, BorderLayout.NORTH);
        
        JPanel contentPanel = new JPanel();
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        JPanel innerContentPanel = new JPanel(new GridLayout(4, 1));
        contentPanel.add(innerContentPanel);
        
        JLabel inputLabel = new JLabel("Enter your input here:");
        inputField = new JTextField();
        JLabel ouputLabel = new JLabel("Your converted input:");
        outputField = new JTextField();
        outputField.setEditable(false);
        
        innerContentPanel.add(inputLabel);
        innerContentPanel.add(inputField);
        innerContentPanel.add(ouputLabel);
        innerContentPanel.add(outputField);
        
        JPanel buttonPanel = new JPanel(new GridLayout(1,1));
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        convertButton = new JButton("Convert");
        convertButton.setPreferredSize(new Dimension(20, 30));
        buttonPanel.add(convertButton);
        
        ButtonListener buttonListen = new ButtonListener();
        convertButton.addActionListener(buttonListen);
        
        frame.getContentPane().add(mainPanel);
        
        frame.pack();
        frame.setSize(400, 200);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
        
    }
    
    public static class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if (event.getSource() == convertButton)
            {
                if (romanToArabicRadio.isSelected())
                {
                    if (testInput(inputField.getText()))
                    {
                        //String output = romanToArabic(inputField.getText());
                        //System.out.println(output);
                        outputField.setText(romanToArabic(inputField.getText()));
                    }
                    
                    
                }
            }
        }

        
    }
    
    public static boolean testInput(String inputTest)
    {
        inputTest = inputTest.toUpperCase();
        
        for (int i = 0; i < inputTest.length(); i++)
        {
            char test = inputTest.charAt(i);
            if ((test != 'I') && (test != 'V') && (test != 'X') && (test != 'L') && (test != 'C') && (test != 'D') && (test != 'M'))
            {
                return false;
            }
        }
        
        return true;
    }
    
    public static String romanToArabic(String romanIn)
    {
        romanIn = romanIn.toUpperCase();
        
        int romanTotal = 0;
        
        char inRomanNum[] = romanIn.toCharArray();
        
        System.out.println(inRomanNum.length);
        
        for (int i = inRomanNum.length - 1; i >= 0; --i) {
            if (inRomanNum[i] != 'I')
            {
                
            }
            
            /*if ((inRomanNum[i] == 'I') && (inRomanNum[i + 1] == 'X')) {
                romanTotal--;
            } else if ((inRomanNum[i] == 'I') && (inRomanNum[i + 1] == 'V')) {
                romanTotal--;
            } else if ((inRomanNum[i] == 'X') && (inRomanNum[i + 1] == 'L')) {
                romanTotal = romanTotal - 10;
            } else if ((inRomanNum[i] == 'X') && (inRomanNum[i + 1] == 'C')) {
                romanTotal = romanTotal - 10;
            } else if ((inRomanNum[i] == 'C') && (inRomanNum[i + 1] == 'M')) {
                romanTotal = romanTotal - 100;
            } else if (inRomanNum[i] == 'I') {
                romanTotal++;
            } else if (inRomanNum[i] == 'V') {
                romanTotal = romanTotal + 5;
            } else if (inRomanNum[i] == 'X') {
                romanTotal = romanTotal + 10;
            } else if (inRomanNum[i] == 'L') {
                romanTotal = romanTotal + 50;
            } else if (inRomanNum[i] == 'C') {
                romanTotal = romanTotal + 100;
            } else if (inRomanNum[i] == 'D') {
                romanTotal = romanTotal + 500;
            } else if (inRomanNum[i] == 'M') {
                romanTotal = romanTotal + 1000;
            }*/
            System.out.println(inRomanNum[i]);
        }
        return Integer.toString(romanTotal);
    }

    
}
