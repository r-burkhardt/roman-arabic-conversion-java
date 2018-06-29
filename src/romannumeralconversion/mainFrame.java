/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package romannumeralconversion;

import javax.swing.JFrame;

/**
 *
 * @author rburkhardt
 */
public class mainFrame extends JFrame
{
    public static final int WIDTH = 675;
    public static final int HEIGHT = 515;
    
    public mainFrame(String title)
    {
        super(title);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
