//Brandon Selzer

package p5selzer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ThirdButtonLayer extends JPanel
{
    private final GridBagConstraints gbc = new GridBagConstraints();
    private OutputDisplay display;
    private JButton btn0, btnNegate, btnDecimal, btnEqual;
    
    private final int width = 35;
    private final int height = 30;
    
    public ThirdButtonLayer()
    {
        initialize();
    }
    
    public void setOutputDisplay(OutputDisplay display)
    {
        this.display = display;
    }
    
    private void initialize()
    {
        setLayout(new GridBagLayout());
        initializeComponents();
    }
    
    private void initializeComponents()
    {
        gbc.insets = new Insets(0, 0, 5, 5);

        btn0 = new JButton();
        btn0.setPreferredSize(new Dimension(width, height));
        btn0.setText("0");
        btn0.setForeground(Color.BLUE);
        btn0.setBackground(Color.WHITE);
        btn0.setMargin(new Insets(0, 0, 0, 0));
        btn0.addActionListener(new numberAL());
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(btn0, gbc);
        
        gbc.gridy = -1;
        
        //Numbers 1 - 9 JButtons
        for(int i = 9; i >= 1; i--)
        {
            JButton btn = new JButton();
            btn.setPreferredSize(new Dimension(width, height));
            btn.setText(Integer.toString(i));
            btn.setForeground(Color.BLUE);
            btn.setBackground(Color.WHITE);
            btn.setMargin(new Insets(0, 0, 0, 0));
            btn.addActionListener(new numberAL());
            
            if(i % 3 == 0)
            {
                gbc.gridx = 2;
                gbc.gridy++;
            }
            else
            {
                gbc.gridx--;
            }
            
            add(btn, gbc);  
        }//end loop
        
        btnNegate = new JButton();
        btnNegate.setPreferredSize(new Dimension(width, height));
        btnNegate.setText("+/-");
        btnNegate.setForeground(Color.BLUE);
        btnNegate.setBackground(Color.WHITE);
        btnNegate.setMargin(new Insets(0, 0, 0, 0));
        btnNegate.addActionListener(new negateAL());
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(btnNegate, gbc);
        
        btnDecimal = new JButton();
        btnDecimal.setPreferredSize(new Dimension(width, height));
        btnDecimal.setText(".");
        btnDecimal.setForeground(Color.BLUE);
        btnDecimal.setBackground(Color.WHITE);
        btnDecimal.setMargin(new Insets(0, 0, 0, 0));
        btnDecimal.addActionListener(new decimalAL());
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(btnDecimal, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 0;
        
        //Operations JButtons
        for(int i = 0; i <= 3; i++)
        {
            JButton btn = new JButton();
            btn.setPreferredSize(new Dimension(width, height));
            
            switch (i) 
            {
                case 0:
                    btn.setText("/");
                    break;
                case 1:
                    btn.setText("*");
                    break;
                case 2:
                    btn.setText("-");
                    break;
                default:
                    btn.setText("+");
                    break;
            }//end switch
            
            btn.setForeground(Color.RED);
            btn.setBackground(Color.WHITE);
            btn.setMargin(new Insets(0, 0, 0, 0));
            btn.addActionListener(new operationAL());
            add(btn, gbc);
            gbc.gridy++;
        }//end loop
        
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = 4;
        gbc.gridy = 0;
        
        for(int i = 0; i <= 2; i++)
        {
            JButton btn = new JButton();
            btn.setPreferredSize(new Dimension(width, height));
            
            switch(i)
            {
                case 0:
                    btn.setText("sqrt");
                    btn.addActionListener(new sqrtAL());
                    break;
                case 1:
                    btn.setText("%");
                    btn.addActionListener(new percentageAL());
                    break;
                default:
                    btn.setText("1/x");
                    btn.addActionListener(new reciprocalAL());
                    break;
            }//end switch
            
            btn.setForeground(Color.BLUE);
            btn.setBackground(Color.WHITE);
            btn.setMargin(new Insets(0, 0, 0, 0));
            add(btn, gbc);
            gbc.gridy++;
        }//end loop
        
        btnEqual = new JButton();
        btnEqual.setPreferredSize(new Dimension(width, height));
        btnEqual.setText("=");
        btnEqual.setForeground(Color.RED);
        btnEqual.setBackground(Color.WHITE);
        btnEqual.setMargin(new Insets(0, 0, 0, 0));
        btnEqual.addActionListener(new equalAL());
        gbc.gridx = 4;
        gbc.gridy = 3;
        add(btnEqual, gbc);
    }//end method
    
    private class numberAL implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            display.setNumberInMap(((JButton)ae.getSource()).getText());
        }    
    }
    
    private class operationAL implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            display.setOperationInMap(((JButton)ae.getSource()).getText());
        }
    }
    
    private class equalAL implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            if(display.mapContainsNumber1() && display.mapContainsNumber2() && display.mapContainsOperation())
            {
                display.calculate();
            }
        }
    }
    
    private class decimalAL implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            display.setDecimalInNumberInMap();
        }
    }
    
    private class negateAL implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            display.setNegationOfNumberInMap();
        }
    }
    
    private class sqrtAL implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            display.setSqrtOfNumberInMap();
        }
    }
    
    private class reciprocalAL implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            display.setReciprocalOfNumberInMap();
        }
    }
    
    private class percentageAL implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            display.setPercentageOfNumberInMap();
        }
    }
}//end class
