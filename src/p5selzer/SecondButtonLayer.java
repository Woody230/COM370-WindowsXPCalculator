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

public class SecondButtonLayer extends JPanel
{
    private final GridBagConstraints gbc = new GridBagConstraints();
    private Calculator calculator;
    private OutputDisplay display;
    private JButton btnMemoryClear, btnMemoryRecall, btnMemoryStore, btnMemoryAdd;
    
    private final int width = 35;
    private final int height = 30;
    
    public SecondButtonLayer()
    {
        initialize();
    }
    
    public void setCalculator(Calculator calculator)
    {
        this.calculator = calculator;
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
        gbc.insets = new Insets(0, 0, 5, 10);
        gbc.gridx = 0;
        
        btnMemoryClear = new JButton();
        btnMemoryClear.setPreferredSize(new Dimension(width, height));
        btnMemoryClear.setText("MC");
        btnMemoryClear.setForeground(Color.RED);
        btnMemoryClear.setBackground(Color.WHITE);
        btnMemoryClear.setMargin(new Insets(0, 0, 0, 0));
        btnMemoryClear.addActionListener(new clearAL());
        gbc.gridy = 0;
        add(btnMemoryClear, gbc);
        
        btnMemoryRecall = new JButton();
        btnMemoryRecall.setPreferredSize(new Dimension(width, height));
        btnMemoryRecall.setText("MR");
        btnMemoryRecall.setForeground(Color.RED);
        btnMemoryRecall.setBackground(Color.WHITE);
        btnMemoryRecall.setMargin(new Insets(0, 0, 0, 0));
        btnMemoryRecall.addActionListener(new recallAL());
        gbc.gridy = 1;
        add(btnMemoryRecall, gbc);
        
        btnMemoryStore = new JButton();
        btnMemoryStore.setPreferredSize(new Dimension(width, height));
        btnMemoryStore.setText("MS");
        btnMemoryStore.setForeground(Color.RED);
        btnMemoryStore.setBackground(Color.WHITE);
        btnMemoryStore.setMargin(new Insets(0, 0, 0, 0));
        btnMemoryStore.addActionListener(new storeAL());
        gbc.gridy = 2;
        add(btnMemoryStore, gbc);
        
        btnMemoryAdd = new JButton();
        btnMemoryAdd.setPreferredSize(new Dimension(width, height));
        btnMemoryAdd.setText("M+");
        btnMemoryAdd.setForeground(Color.RED);
        btnMemoryAdd.setBackground(Color.WHITE);
        btnMemoryAdd.setMargin(new Insets(0, 0, 0, 0));
        btnMemoryAdd.addActionListener(new addAL());
        gbc.gridy = 3;
        add(btnMemoryAdd, gbc);
    }//end method
    
    private class clearAL implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            calculator.clearMemory();
        }
    }
    
    private class recallAL implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            display.clearEntry();
            display.setNumberInMap(calculator.getMemory());
        }
    }
    
    private class storeAL implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            calculator.setMemory(display.getValidText());
        }
    }
    
    private class addAL implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            calculator.addToMemory(display.getText());
        }
    }
}//end class
