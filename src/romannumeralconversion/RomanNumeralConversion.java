/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package romannumeralconversion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Roderick Burkhardt
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
        romanToArabicRadio = new JRadioButton("Roman to Arabic\nMax: MMMCMXCIX");
        arabicToRomanRadio = new JRadioButton("Arabic to Roman\nMax: 2147483647");
        
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
        inputField = new JTextField("", 20);
        JLabel ouputLabel = new JLabel("Your converted input:");
        outputField = new JTextField("", 20);
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
                String message;
                
                if (romanToArabicRadio.isSelected())
                {
                    if (testInputForNumeral(inputField.getText()))
                    {
                        //String output = romanToArabic(inputField.getText());
                        //System.out.println(output);
                        outputField.setText(romanToArabic(inputField.getText()));
                    }
                    else 
                    {
                        message = "Input contains non-roman numeral input. \nPlease try again.";
                        JOptionPane.showMessageDialog(null, message);
                    }
                }
                else if (arabicToRomanRadio.isSelected())
                {
                    if (testInputForArabic(inputField.getText()))
                    {
                        outputField.setText(arabicToRoman(inputField.getText()));
                    }
                    else 
                    {
                        message = "Input contains not arabic input. \nPlease try again.";
                        JOptionPane.showMessageDialog(null, message);
                    }
                }
                else
                {
                    message = "You must select \'Roman to Arabic\' or \'Arabic to Roman\'. \nPlease try again.";
                    JOptionPane.showMessageDialog(null, message);                    
                }
            }
        }

        
    }
    
    public static boolean testInputForNumeral(String inputTest)
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
    
    public static boolean testInputForArabic(String inputTest)
    {
        for (int i = 0; i < inputTest.length()-1; i++)
        {
            if (Character.isLetter(inputTest.charAt(i)))
            {
                System.out.println(inputTest.charAt(i));
                return false;
            }
        }
        
        return true;
    }
    
    public static String romanToArabic(String romanIn)
    {
        romanIn = romanIn.toUpperCase();
        
        romanIn = romanIn + " ";
        
        int romanTotal = 0;
        
        char inRomanNum[] = romanIn.toCharArray();
        
        System.out.println(inRomanNum.length);
        
        for (int i = inRomanNum.length - 1; i >= 0; --i) {
            /*if (inRomanNum[i] != 'I')*/
            if ((inRomanNum[i] == 'I') && (inRomanNum[i + 1] == 'X')) {
                romanTotal--;
            } else if ((inRomanNum[i] == 'I') && (inRomanNum[i + 1] == 'V')) {
                romanTotal--;
            } else if ((inRomanNum[i] == 'X') && (inRomanNum[i + 1] == 'L')) {
                romanTotal = romanTotal - 10;
            } else if ((inRomanNum[i] == 'X') && (inRomanNum[i + 1] == 'C')) {
                romanTotal = romanTotal - 10;
            } else if ((inRomanNum[i] == 'C') && (inRomanNum[i + 1] == 'D')) {
                romanTotal = romanTotal - 100;
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
            }
            System.out.println(inRomanNum[i]);
        }
        return Integer.toString(romanTotal);
    }
    
    public static String arabicToRoman(String arabicIn)
    {
        int inArabicNum = Integer.parseInt(arabicIn);
        System.out.println(inArabicNum);
        
        String outRomanNum = "";
	
	// loops through the inArabicNum creating a roman numeral string to be sent back to main
	do
	{
            if (inArabicNum >= 1000000000 && inArabicNum <= 2147483647)
            {
                outRomanNum = outRomanNum + "M\u033F";
                inArabicNum = inArabicNum - 1000000000;
            }
            else if (inArabicNum >= 900000000 && inArabicNum < 1000000000)
            {
                outRomanNum = outRomanNum + "C\u033FM\u033F";
                inArabicNum = inArabicNum - 900000000;
            }
            else if (inArabicNum >= 500000000 && inArabicNum < 900000000)
            {
                outRomanNum = outRomanNum + "D\u033F";
                inArabicNum = inArabicNum - 500000000;
            }
            else if (inArabicNum >= 400000000 && inArabicNum < 500000000)
            {
                outRomanNum = outRomanNum + "C\u033FD\u033F";
                inArabicNum = inArabicNum - 400000000;
            }
            else if (inArabicNum >= 100000000 && inArabicNum < 400000000)
            {
                outRomanNum = outRomanNum + "C\u033F";
                inArabicNum = inArabicNum - 100000000;
            }
            else if (inArabicNum >= 90000000 && inArabicNum < 100000000)
            {
                outRomanNum = outRomanNum + "X\u033FC\u033F";
                inArabicNum = inArabicNum - 90000000;
            }
            else if (inArabicNum >= 50000000 && inArabicNum < 90000000)
            {
                outRomanNum = outRomanNum + "L\u033F";
                inArabicNum = inArabicNum - 50000000;
            }
            else if (inArabicNum >= 40000000 && inArabicNum < 50000000)
            {
                outRomanNum = outRomanNum + "X\u033FL\u033F";
                inArabicNum = inArabicNum - 40000000;
            }
            else if (inArabicNum > 9999999 && inArabicNum < 40000000)
            {
                outRomanNum = outRomanNum + "X\u033F";
                inArabicNum = inArabicNum - 10000000;
            }
            else if (inArabicNum > 8999999 && inArabicNum < 10000000)
            {
                outRomanNum = outRomanNum + "M\u0305X\u033F";
                inArabicNum = inArabicNum - 9000000;
            }
            else if (inArabicNum > 4999999 && inArabicNum < 9000000)
            {
                outRomanNum = outRomanNum + "V\u033F";
                inArabicNum = inArabicNum - 5000000;
            }
            else if (inArabicNum > 3999999 && inArabicNum < 5000000)
            {
                outRomanNum = outRomanNum + "M\u0305V\u033F";
                inArabicNum = inArabicNum - 4000000;
            }
            else if (inArabicNum > 999999 && inArabicNum < 4000000)
            {
                outRomanNum = outRomanNum + "M\u0305";
                inArabicNum = inArabicNum - 1000000;
            }
            if (inArabicNum > 899999 && inArabicNum < 1000000)
            {
                outRomanNum = outRomanNum + "C\u0305M\u0305";
                inArabicNum = inArabicNum - 900000;
            }
            else if (inArabicNum > 499999 && inArabicNum < 900000)
            {
                outRomanNum = outRomanNum + "D\u0305";
                inArabicNum = inArabicNum - 500000;
            }
            else if (inArabicNum > 399999 && inArabicNum < 500000)
            {
                outRomanNum = outRomanNum + "C\u0305D\u0305";
                inArabicNum = inArabicNum - 400000;
            }
            else if (inArabicNum > 99999 && inArabicNum < 400000)
            {
                outRomanNum = outRomanNum + "C\u0305";
                inArabicNum = inArabicNum - 100000;
            }
            else if (inArabicNum > 89999 && inArabicNum < 100000)
            {
                outRomanNum = outRomanNum + "X\u0305C\u0305";
                inArabicNum = inArabicNum - 90000;
            }
            else if (inArabicNum > 49999 && inArabicNum < 90000)
            {
                outRomanNum = outRomanNum + "L\u0305";
                inArabicNum = inArabicNum - 50000;                 
            }
            else if (inArabicNum > 39999 && inArabicNum < 50000)
            {
                outRomanNum = outRomanNum + "X\u0305L\u0305";
                inArabicNum = inArabicNum - 40000;
            }
            else if (inArabicNum > 9999 && inArabicNum < 40000)
            {
                outRomanNum = outRomanNum + "X\u0305";
                inArabicNum = inArabicNum - 10000;
            }
            else if (inArabicNum > 8999 && inArabicNum < 10000)
            {
                outRomanNum = outRomanNum + "MX\u0305";
                inArabicNum = inArabicNum -9000;
            }
            else if (inArabicNum > 4999 && inArabicNum < 9000)
            {
                outRomanNum = outRomanNum + "V\u0305";
                inArabicNum = inArabicNum - 5000;
            }
            else if (inArabicNum > 3999 && inArabicNum < 5000)
            {
                outRomanNum = outRomanNum + "MV\u0305";
                inArabicNum = inArabicNum - 4000;
            }
            else if (inArabicNum < 4000 && inArabicNum > 999)
            {
                outRomanNum = outRomanNum + 'M';
                inArabicNum = inArabicNum - 1000;
            }
            else if (inArabicNum < 1000 && inArabicNum > 899)
            {
                outRomanNum = outRomanNum + "CM";
                inArabicNum = inArabicNum - 900;
            }
            else if (inArabicNum < 900 && inArabicNum > 499)
            {
                outRomanNum = outRomanNum + 'D';
                inArabicNum = inArabicNum - 500;
            }
            else if (inArabicNum < 500 && inArabicNum > 399)
            {
                outRomanNum = outRomanNum + "CD";
                inArabicNum = inArabicNum - 400;
            }
            else if (inArabicNum < 400 && inArabicNum > 99)
            {
                outRomanNum = outRomanNum + 'C';
                inArabicNum = inArabicNum - 100;
            }
            else if (inArabicNum < 100 && inArabicNum > 89)
            {
                outRomanNum = outRomanNum + "XC";
                inArabicNum = inArabicNum - 90;
            }
            else if (inArabicNum < 90 && inArabicNum > 49)
            {
                outRomanNum = outRomanNum + 'L';
                inArabicNum = inArabicNum - 50;
            }
            else if (inArabicNum < 50 && inArabicNum > 39)
            {
                outRomanNum = outRomanNum + "XL";
                inArabicNum = inArabicNum - 40;
            }
            else if (inArabicNum < 40 && inArabicNum > 9)
            {
                outRomanNum = outRomanNum + 'X';
                inArabicNum = inArabicNum - 10;
            }
            else if (inArabicNum == 9)
            {
                outRomanNum = outRomanNum + "IX";
                inArabicNum = inArabicNum - 9;
            }
            else if (inArabicNum >= 5 && inArabicNum < 9)
            {
                outRomanNum = outRomanNum + 'V';
                inArabicNum = inArabicNum - 5;
            }
            else if (inArabicNum == 4)
            {
                outRomanNum = outRomanNum + "IV";
                inArabicNum = inArabicNum - 4;
            }
            else if (inArabicNum > 0 && inArabicNum <= 3)
            {
                outRomanNum = outRomanNum + 'I';
                inArabicNum = inArabicNum - 1;
            }
            System.out.println(outRomanNum);
	} while (inArabicNum != 0);
		
	// returns the roman numeral string to main
	return outRomanNum;
    }

    
}
