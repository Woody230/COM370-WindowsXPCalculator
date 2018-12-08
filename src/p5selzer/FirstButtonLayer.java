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
import javax.swing.JTextField;

public class FirstButtonLayer extends JPanel
{
    private final GridBagConstraints gbc = new GridBagConstraints();
    
    private JTextField txtMemory;
    private JButton btnBackspace;
    private JButton btnClearEntry;
    private JButton btnClear;
    private OutputDisplay display;
    
    private final int height = 30;

    public FirstButtonLayer()
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
        txtMemory = new JTextField();
        txtMemory.setPreferredSize(new Dimension(35, height));
        txtMemory.setText("");
        txtMemory.setEditable(false);  
        txtMemory.setHorizontalAlignment(JTextField.CENTER);
        txtMemory.setBackground(Color.WHITE);
        txtMemory.setMargin(new Insets(0, 0, 0, 0));
        gbc.insets = new Insets(0, 0, 5, 10);
        add(txtMemory, gbc);
        
        gbc.insets = new Insets(0, 0, 5, 5);
        
        btnBackspace = new JButton();
        btnBackspace.setPreferredSize(new Dimension(70, height));
        btnBackspace.setText("Backspace");
        btnBackspace.setForeground(Color.RED);
        btnBackspace.setBackground(Color.WHITE);
        btnBackspace.setMargin(new Insets(0, 0, 0, 0));
        btnBackspace.addActionListener(new backspaceAL());
        add(btnBackspace, gbc);
        
        btnClearEntry = new JButton();
        btnClearEntry.setPreferredSize(new Dimension(58, height));
        btnClearEntry.setText("CE");
        btnClearEntry.setForeground(Color.RED);
        btnClearEntry.setBackground(Color.WHITE);
        btnClearEntry.setMargin(new Insets(0, 0, 0, 0));
        btnClearEntry.addActionListener(new clearEntryAL());
        add(btnClearEntry, gbc);
        
        btnClear = new JButton();
        btnClear.setPreferredSize(new Dimension(57, height));
        btnClear.setText("C");
        btnClear.setForeground(Color.RED);
        btnClear.setBackground(Color.WHITE);
        btnClear.setMargin(new Insets(0, 0, 0, 0));
        btnClear.addActionListener(new clearAL());
        gbc.insets = new Insets(0, 0, 5, 0);
        add(btnClear, gbc);
    }//end method
    
    private class clearAL implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            display.clear();
        }
    }
    
    private class clearEntryAL implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            display.clearEntry();
        }
    }
    
    private class backspaceAL implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            display.backspace();
        }
    }
    
    public void setMemoryIndicator(boolean hasActiveMemory)
    {
        if(hasActiveMemory)
        {
            txtMemory.setText("M"); 
        }
        else
        {
            txtMemory.setText("");
        }
    }
}//end class
