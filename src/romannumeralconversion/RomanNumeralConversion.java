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
import java.util.ArrayList;
import java.util.List;
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
    public static JButton overlineV, overlineX, overlineL, overlineC, overlineD, overlineM, doubleOverlineV, doubleOverlineX, doubleOverlineL, doubleOverlineC, doubleOverlineD, doubleOverlineM, convertButton;
    public static int arabicTotal;
    
    public static void main(String[] args) {
        frame = new JFrame("Roman Numeral Conversion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        romanToArabicRadio = new JRadioButton("Roman to Arabic");
        arabicToRomanRadio = new JRadioButton("Arabic to Roman");
        JLabel romanMaxLabel = new JLabel("Max: M̿M̿C̿X̿L̿V̿M̅M̅C̅D̅L̅X̅X̅X̅MMMDCXLVII");
        JLabel arabicMaxLabel = new JLabel("Max: 2147483647");
        
        ButtonGroup selectConversionGroup = new ButtonGroup();
        selectConversionGroup.add(romanToArabicRadio);
        selectConversionGroup.add(arabicToRomanRadio);
        
        JPanel radioPanel = new JPanel(new GridLayout(2, 2));
        radioPanel.add(romanToArabicRadio);
        radioPanel.add(arabicToRomanRadio);
        radioPanel.add(romanMaxLabel);
        radioPanel.add(arabicMaxLabel);
        romanToArabicRadio.setSelected(true);
        mainPanel.add(radioPanel, BorderLayout.NORTH);
        
        JPanel contentPanel = new JPanel();
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        JPanel leftContentPanel = new JPanel(new GridLayout(4, 3));
        contentPanel.add(leftContentPanel, BorderLayout.EAST);
        
        overlineV = new JButton("V\u0305");
        leftContentPanel.add(overlineV);
        overlineX = new JButton("X\u0305");
        leftContentPanel.add(overlineX);
        overlineL = new JButton("L\u0305");
        leftContentPanel.add(overlineL);
        overlineC = new JButton("C\u0305");
        leftContentPanel.add(overlineC);
        overlineD = new JButton("D\u0305");
        leftContentPanel.add(overlineD);
        overlineM = new JButton("M\u0305");
        leftContentPanel.add(overlineM);
        doubleOverlineV = new JButton("V\u033F");
        leftContentPanel.add(doubleOverlineV);
        doubleOverlineX = new JButton("X\u033F");
        leftContentPanel.add(doubleOverlineX);
        doubleOverlineL = new JButton("L\u033F");
        leftContentPanel.add(doubleOverlineL);
        doubleOverlineC = new JButton("C\u033F");
        leftContentPanel.add(doubleOverlineC);
        doubleOverlineD = new JButton("D\u033F");
        leftContentPanel.add(doubleOverlineD);
        doubleOverlineM = new JButton("M\u033F");
        leftContentPanel.add(doubleOverlineM);
        
        JPanel rightContentPanel = new JPanel(new GridLayout(4, 1));
        contentPanel.add(rightContentPanel, BorderLayout.WEST);
        
        JLabel inputLabel = new JLabel("Enter your input here:");
        inputField = new JTextField("", 20);
        JLabel ouputLabel = new JLabel("Your converted input:");
        outputField = new JTextField("", 20);
        outputField.setEditable(false);
        
        rightContentPanel.add(inputLabel);
        rightContentPanel.add(inputField);
        rightContentPanel.add(ouputLabel);
        rightContentPanel.add(outputField);
        
        JPanel buttonPanel = new JPanel(new GridLayout(1,1));
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        convertButton = new JButton("Convert");
        convertButton.setPreferredSize(new Dimension(20, 30));
        buttonPanel.add(convertButton);
        
        ButtonListener buttonListen = new ButtonListener();
        convertButton.addActionListener(buttonListen);
        overlineV.addActionListener(buttonListen);
        overlineX.addActionListener(buttonListen);
        overlineL.addActionListener(buttonListen);
        overlineC.addActionListener(buttonListen);
        overlineD.addActionListener(buttonListen);
        overlineM.addActionListener(buttonListen);
        doubleOverlineV.addActionListener(buttonListen);
        doubleOverlineX.addActionListener(buttonListen);
        doubleOverlineL.addActionListener(buttonListen);
        doubleOverlineC.addActionListener(buttonListen);
        doubleOverlineD.addActionListener(buttonListen);
        doubleOverlineM.addActionListener(buttonListen);
        
        frame.getContentPane().add(mainPanel);
        
        frame.pack();
        frame.setSize(600, 350);
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
                        outputField.setText(Integer.toString(romanToArabic(inputField.getText())));
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
            else if (event.getSource() == overlineV)
            {
                inputField.setText(inputField.getText() + "V\u0305");
            }
            else if (event.getSource() == overlineX)
            {
                inputField.setText(inputField.getText() + "X\u0305");
            }
            else if (event.getSource() == overlineL)
            {
                inputField.setText(inputField.getText() + "L\u0305");
            }
            else if (event.getSource() == overlineC)
            {
                inputField.setText(inputField.getText() + "C\u0305");
            }
            else if (event.getSource() == overlineD)
            {
                inputField.setText(inputField.getText() + "D\u0305");
            }
            else if (event.getSource() == overlineM)
            {
                inputField.setText(inputField.getText() + "M\u0305");
            }
            else if (event.getSource() == doubleOverlineV)
            {
                inputField.setText(inputField.getText() + "V\u033F");
            }
            else if (event.getSource() == doubleOverlineX)
            {
                inputField.setText(inputField.getText() + "X\u033F");
            }
            else if (event.getSource() == doubleOverlineL)
            {
                inputField.setText(inputField.getText() + "L\u033F");
            }
            else if (event.getSource() == doubleOverlineC)
            {
                inputField.setText(inputField.getText() + "C\u033F");
            }
            else if (event.getSource() == doubleOverlineD)
            {
                inputField.setText(inputField.getText() + "D\u033F");
            }
            else if (event.getSource() == doubleOverlineM)
            {
                inputField.setText(inputField.getText() + "M\u033F");
            }
        }

        
    }
    
    public static boolean testInputForNumeral(String inputTest)
    {
        inputTest = inputTest.toUpperCase();

        for (int i = 0; i < inputTest.length()-1; i++)
        {
            //String test = String.valueOf(inputTest.charAt(i));
            char test = inputTest.charAt(i);
            //System.out.println(test);
            if ((test != 'I') && (test != 'V') && (test != 'X') && (test != 'L') && (test != 'C') && (test != 'D') && (test != 'M') && (test != '\u0305') && (test != '\u033F'))
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
                //System.out.println(inputTest.charAt(i));
                return false;
            }
        }
        
        return true;
    }
    
    public static int romanToArabic(String romanString)
    {
        int arabicTotal = 0;
        
        romanString = romanString.toUpperCase();
        
        //int i = (romanString.length() - 1);
        //System.out.println(romanString);
        
        List<Integer> arabicNumbers = new ArrayList<>(); 
        
        for (int i = 0; i < romanString.length()-1; i++)
        {
            if (romanString.charAt(i+1) == '\u0305')
            {
                //System.out.println(returnArabic(romanString.charAt(i)) * 1000);
                arabicNumbers.add(returnArabic(romanString.charAt(i)) * 1000);
                i++;
            }
            else if (romanString.charAt(i+1) == '\u033F')
            {
                //System.out.println(returnArabic(romanString.charAt(i)) * 1000000);
                arabicNumbers.add(returnArabic(romanString.charAt(i)) * 1000000);
                i++;
            }
            else 
            {
                //System.out.println(returnArabic(romanString.charAt(i)));
                arabicNumbers.add(returnArabic(romanString.charAt(i)));
            }
        }
        arabicNumbers.add(returnArabic(romanString.charAt(romanString.length()-1)));
        
        for (int j = 0; j < arabicNumbers.size()-1; j++)
        {
            if (arabicNumbers.get(j) < arabicNumbers.get(j + 1))
            {
                arabicTotal = arabicTotal - arabicNumbers.get(j);
            }
            else
            {
                arabicTotal = arabicTotal + arabicNumbers.get(j);
            }
        }
        arabicTotal = arabicTotal + arabicNumbers.get(arabicNumbers.size()-1);
        
        return arabicTotal;
    }
    
    public static int returnArabic(char romanChar)
    {
        switch (romanChar)
        {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
    
    /*public static int romanToArabic(String romanIn)
    {
        romanIn = romanIn.toUpperCase();
        
        //romanIn = romanIn + " ";
        
        int romanTotal = 0;
        
        //System.out.println(romanIn.length());
        System.out.println(romanIn);
        
        int i = romanIn.length() - 1;
        
        //for (int i = romanIn.length() - 1; i >= 0; --i) {
        while (i >= 0)
        {            
            
            if (romanIn.charAt(i) == '\u0305')
            {
                romanTotal = romanTotal + (1000 * romanToArabic(String.valueOf(romanIn.charAt(i - 1))));
                System.out.println(romanTotal);
                i--;
            }
                
            if ((romanIn.charAt(i) == 'I') && (romanIn.charAt(i + 1) == 'X')) {
                romanTotal--;
            } else if ((romanIn.charAt(i) == 'I') && (romanIn.charAt(i + 1) == 'V')) {
                romanTotal--;
            } else if ((romanIn.charAt(i) == 'X') && (romanIn.charAt(i + 1) == 'L')) {
                romanTotal = romanTotal - 10;
            } else if ((romanIn.charAt(i) == 'X') && (romanIn.charAt(i + 1) == 'C')) {
                romanTotal = romanTotal - 10;
            } else if ((romanIn.charAt(i) == 'C') && (romanIn.charAt(i + 1) == 'D')) {
                romanTotal = romanTotal - 100;
            } else if ((romanIn.charAt(i) == 'C') && (romanIn.charAt(i + 1) == 'M')) {
                romanTotal = romanTotal - 100;
            } else if (romanIn.charAt(i) == 'I') {
                romanTotal++;
            } else if (romanIn.charAt(i) == 'V') {
                romanTotal = romanTotal + 5;
            } else if (romanIn.charAt(i) == 'X') {
                romanTotal = romanTotal + 10;
            } else if (romanIn.charAt(i) == 'L') {
                romanTotal = romanTotal + 50;
            } else if (romanIn.charAt(i) == 'C') {
                romanTotal = romanTotal + 100;
            } else if (romanIn.charAt(i) == 'D') {
                romanTotal = romanTotal + 500;
            } else if (romanIn.charAt(i) == 'M') {
                romanTotal = romanTotal + 1000;
            }
            System.out.println(romanIn.valueOf(romanIn.charAt(i)));
            i--;
        } //while (i >= 0);
        return romanTotal;
    }*/
    
    public static String arabicToRoman(String arabicIn)
    {
        int inArabicNum = Integer.parseInt(arabicIn);
        //System.out.println(inArabicNum);
        
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
                outRomanNum = outRomanNum + "M";
                inArabicNum = inArabicNum - 1000;
            }
            else if (inArabicNum < 1000 && inArabicNum > 899)
            {
                outRomanNum = outRomanNum + "CM";
                inArabicNum = inArabicNum - 900;
            }
            else if (inArabicNum < 900 && inArabicNum > 499)
            {
                outRomanNum = outRomanNum + "D";
                inArabicNum = inArabicNum - 500;
            }
            else if (inArabicNum < 500 && inArabicNum > 399)
            {
                outRomanNum = outRomanNum + "CD";
                inArabicNum = inArabicNum - 400;
            }
            else if (inArabicNum < 400 && inArabicNum > 99)
            {
                outRomanNum = outRomanNum + "C";
                inArabicNum = inArabicNum - 100;
            }
            else if (inArabicNum < 100 && inArabicNum > 89)
            {
                outRomanNum = outRomanNum + "XC";
                inArabicNum = inArabicNum - 90;
            }
            else if (inArabicNum < 90 && inArabicNum > 49)
            {
                outRomanNum = outRomanNum + "L";
                inArabicNum = inArabicNum - 50;
            }
            else if (inArabicNum < 50 && inArabicNum > 39)
            {
                outRomanNum = outRomanNum + "XL";
                inArabicNum = inArabicNum - 40;
            }
            else if (inArabicNum < 40 && inArabicNum > 9)
            {
                outRomanNum = outRomanNum + "X";
                inArabicNum = inArabicNum - 10;
            }
            else if (inArabicNum == 9)
            {
                outRomanNum = outRomanNum + "IX";
                inArabicNum = inArabicNum - 9;
            }
            else if (inArabicNum >= 5 && inArabicNum < 9)
            {
                outRomanNum = outRomanNum + "V";
                inArabicNum = inArabicNum - 5;
            }
            else if (inArabicNum == 4)
            {
                outRomanNum = outRomanNum + "IV";
                inArabicNum = inArabicNum - 4;
            }
            else if (inArabicNum > 0 && inArabicNum <= 3)
            {
                outRomanNum = outRomanNum + "I";
                inArabicNum = inArabicNum - 1;
            }
            //System.out.println(outRomanNum);
	} while (inArabicNum != 0);
		
	// returns the roman numeral string to main
	return outRomanNum;
    }

    
}
