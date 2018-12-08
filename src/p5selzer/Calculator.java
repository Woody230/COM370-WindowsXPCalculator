//Brandon Selzer

package p5selzer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.math.BigDecimal;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Calculator extends JFrame
{
    private final GridBagConstraints gbc = new GridBagConstraints();
    private JPanel topContainer;
    private JPanel bottomContainer; 
    private OutputDisplay display;
    private FirstButtonLayer layer1;
    private SecondButtonLayer layer2;
    private ThirdButtonLayer layer3;
    private String memory = "0";
    
    public Calculator(String title)
    {  
        initialize(title);
    }
    
    private void initialize(String title)
    {
        setLayout(new GridBagLayout());
        setTitle(title);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        initializeComponents();
        
        pack();
        setLocationRelativeTo(null);
    }
    
    private void initializeComponents()
    {
        setJMenuBar(new Menu());
        
        topContainer = new JPanel();
        topContainer.setLayout(new GridBagLayout());
        
        display = new OutputDisplay();
        gbc.gridx = 0;
        gbc.gridy = 0;
        topContainer.add(display, gbc);
        
        layer1 = new FirstButtonLayer();
        gbc.gridx = 0;
        gbc.gridy = 1;
        topContainer.add(layer1, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(topContainer, gbc);
        
        bottomContainer = new JPanel();
        bottomContainer.setLayout(new GridBagLayout());
        
        layer2 = new SecondButtonLayer();
        gbc.gridx = 0;
        gbc.gridy = 0;
        bottomContainer.add(layer2, gbc);
        
        layer3 = new ThirdButtonLayer();
        gbc.gridx = 1;
        gbc.gridy = 0;
        bottomContainer.add(layer3, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(bottomContainer, gbc);
        
        layer1.setOutputDisplay(display);
        layer2.setCalculator(this);
        layer2.setOutputDisplay(display);
        layer3.setOutputDisplay(display);
        
        layer1.setMemoryIndicator(false);
    }//end method
    
    public void clearMemory()
    {
        memory = "0";
        layer1.setMemoryIndicator(false);
    }
    
    public String getMemory()
    {
        return memory;
    }
    
    public void setMemory(String memory)
    {
        this.memory = memory;
        
        if(memory.equals("0"))
        {
            layer1.setMemoryIndicator(false);
        }
        else
        {
            layer1.setMemoryIndicator(true);
        }
    }
    
    public void addToMemory(String add)
    {
        BigDecimal mem = new BigDecimal(memory);
        BigDecimal number = new BigDecimal(add);
        
        memory = mem.add(number).toString();
        
        if(memory.equals("0"))
        {
            layer1.setMemoryIndicator(false);
        }
        else
        {
            layer1.setMemoryIndicator(true);
        }
    }
}//end class
