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
		if (inArabicNum > 2999)
		{
			outRomanNum = outRomanNum + 'M';
			inArabicNum = inArabicNum - 1000;
		}
		else if (inArabicNum < 3000 && inArabicNum > 1999)
		{
			outRomanNum = outRomanNum + 'M';
			inArabicNum = inArabicNum - 1000;
		}
		else if (inArabicNum < 2000 && inArabicNum > 999)
		{
			outRomanNum = outRomanNum + 'M';
			inArabicNum = inArabicNum - 1000;
		}
		else if (inArabicNum < 1000 && inArabicNum > 899)
		{
			outRomanNum = outRomanNum + "CM";
			inArabicNum = inArabicNum - 900;
		}
		else if (inArabicNum < 900 && inArabicNum > 799)
		{
			outRomanNum = outRomanNum + "DCCC";
			inArabicNum = inArabicNum - 800;
		}
		else if (inArabicNum < 800 && inArabicNum > 699)
		{
			outRomanNum = outRomanNum + "DCC";
			inArabicNum = inArabicNum - 700;
		}
		else if (inArabicNum < 700 && inArabicNum > 599)
		{
			outRomanNum = outRomanNum + "DC";
			inArabicNum = inArabicNum - 600;
		}
		else if (inArabicNum < 600 && inArabicNum > 499)
		{
			outRomanNum = outRomanNum + 'D';
			inArabicNum = inArabicNum - 500;
		}
		else if (inArabicNum < 500 && inArabicNum > 399)
		{
			outRomanNum = outRomanNum + "CD";
			inArabicNum = inArabicNum - 400;
		}
		else if (inArabicNum < 400 && inArabicNum > 299)
		{
			outRomanNum = outRomanNum + "CCC";
			inArabicNum = inArabicNum - 300;
		}
		else if (inArabicNum < 300 && inArabicNum > 199)
		{
			outRomanNum = outRomanNum + "CC";
			inArabicNum = inArabicNum - 200;
		}
		else if (inArabicNum < 200 && inArabicNum > 99)
		{
			outRomanNum = outRomanNum + 'C';
			inArabicNum = inArabicNum - 100;
		}
		else if (inArabicNum < 100 && inArabicNum > 89)
		{
			outRomanNum = outRomanNum + "XC";
			inArabicNum = inArabicNum - 90;
		}
		else if (inArabicNum < 90 && inArabicNum > 79)
		{
			outRomanNum = outRomanNum + "LXXX";
			inArabicNum = inArabicNum - 80;
		}
		else if (inArabicNum < 80 && inArabicNum > 69)
		{
			outRomanNum = outRomanNum + "LXX";
			inArabicNum = inArabicNum - 70;
		}
		else if (inArabicNum < 70 && inArabicNum > 59)
		{
			outRomanNum = outRomanNum + "LX";
			inArabicNum = inArabicNum - 60;
		}
		else if (inArabicNum < 60 && inArabicNum > 49)
		{
			outRomanNum = outRomanNum + 'L';
			inArabicNum = inArabicNum - 50;
		}
		else if (inArabicNum < 50 && inArabicNum > 39)
		{
			outRomanNum = outRomanNum + "XL";
			inArabicNum = inArabicNum - 40;
		}
		else if (inArabicNum < 40 && inArabicNum > 29)
		{
			outRomanNum = outRomanNum + "XXX";
			inArabicNum = inArabicNum - 30;
		}
		else if (inArabicNum < 30 && inArabicNum > 19)
		{
			outRomanNum = outRomanNum + "XX";
			inArabicNum = inArabicNum - 20;
		}
		else if (inArabicNum < 20 && inArabicNum > 9)
		{
			outRomanNum = outRomanNum + 'X';
			inArabicNum = inArabicNum - 10;
		}
		else if (inArabicNum == 9)
		{
			outRomanNum = outRomanNum + "IX";
			inArabicNum = inArabicNum - 9;
		}
		else if (inArabicNum == 8)
		{
			outRomanNum = outRomanNum + "VIII";
			inArabicNum = inArabicNum - 8;
		}
		else if (inArabicNum == 7)
		{
			outRomanNum = outRomanNum + "VII";
			inArabicNum = inArabicNum - 7;
		}
		else if (inArabicNum == 6)
		{
			outRomanNum = outRomanNum + "VI";
			inArabicNum = inArabicNum - 6;
		}
		else if (inArabicNum == 5)
		{
			outRomanNum = outRomanNum + 'V';
			inArabicNum = inArabicNum - 5;
		}
		else if (inArabicNum == 4)
		{
			outRomanNum = outRomanNum + "IV";
			inArabicNum = inArabicNum - 4;
		}
		else if (inArabicNum == 3)
		{
			outRomanNum = outRomanNum + "III";
			inArabicNum = inArabicNum - 3;
		}
		else if (inArabicNum == 2)
		{
			outRomanNum = outRomanNum + "II";
			inArabicNum = inArabicNum - 2;
		}
		else if (inArabicNum == 1)
		{
			outRomanNum = outRomanNum + 'I';
			inArabicNum = inArabicNum - 1;
		}
	} while (inArabicNum != 0);
		
	// returns the roman numeral string to main
	return outRomanNum;
    }

    
}
