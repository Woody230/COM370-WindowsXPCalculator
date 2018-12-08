//Brandon Selzer

package p5selzer;

import java.awt.Color;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OutputDisplay extends JPanel
{
    private JTextField txtDisplay;
    private final Map<String, String> expression;
    
    //If user hits a number after calculating, then Number1 is replaced.
    //If user hits an operation after calculating, then result is kept as Number1 for the next calculation.
    //This flag is used for the number case in order to replace Number1.
    private boolean resultFlag;
    
    //If Number1 is being manipulated, this flag is true. Otherwise Number2 is being manipulated, and this flag is false.
    private boolean Number1Flag;
    
    private final String n1 = "Number1";
    private final String n2 = "Number2";
    private final String op = "Operation";
    private final MathContext rounding = MathContext.DECIMAL32;
    
    public OutputDisplay()
    {
        resultFlag = false;
        Number1Flag = true;
        expression = new HashMap<>(); //Keys: Number1, Number2, Operation
        expression.put(n1, "0");
        
        initialize();
    }
    
    private void initialize()
    {
        initializeComponents();
    }
    
    private void initializeComponents()
    {
        txtDisplay = new JTextField();
        txtDisplay.setPreferredSize(new Dimension(240, 30));
        txtDisplay.setText("0");
        txtDisplay.setFont(txtDisplay.getFont().deriveFont(25f));
        txtDisplay.setEditable(false);  
        txtDisplay.setHorizontalAlignment(JTextField.RIGHT);
        txtDisplay.setBackground(Color.WHITE);
        add(txtDisplay);
    }
    
    public void appendText(String text)
    {
        txtDisplay.setText(txtDisplay.getText() + text);
    }
    
    public String getText()
    {
        return txtDisplay.getText();
    }
    
    public void setText(String text)
    {
        txtDisplay.setText(text);
    }
    
    public String getValidText()
    {
        if(txtDisplay.getText().endsWith("."))
        {
            return txtDisplay.getText() + "0";
        }
        else
        {
            return txtDisplay.getText();
        }
    }
    
    public void clear()
    {
        txtDisplay.setText("0");
        expression.put(n1, "0");
        expression.remove(n2);
        expression.remove(op);
    }
    
    public void clearEntry()
    {
        txtDisplay.setText("0");
        
        if(Number1Flag)
        {
            expression.put(n1, "0");
        }
        else
        {
            expression.remove(n2);
        }
    }//end method
    
    public void backspace()
    {
        if(resultFlag) //Don't change the result.
        {
            return;
        }
        else if(txtDisplay.getText().length() <= 1) //Return to the default
        {
            clearEntry();
            return;
        }
        
        String substring;
        String mapNumber = getMapNumber();
        
        substring = expression.get(mapNumber).substring(0, expression.get(mapNumber).length() - 1);
        expression.put(mapNumber, substring);
        setText(substring);
    }//end method 
    
    public void setNumberInMap(String text)
    {          
        String mapNumber = getMapNumber();
        
        //Just after a calculation, Number1 needs to be overwritten if a number is pressed.
        //The map should always contain Number1 as the default 0 or another number, but just in case.
        //If the number is the default of 0, it should be overwritten instead of appended to since it looks better visually.
        if(resultFlag || !expression.containsKey(mapNumber) || expression.get(mapNumber).contentEquals("0")) 
        {
            expression.put(mapNumber, text);
            setText(text);
        }
        else
        {
            expression.put(mapNumber, expression.get(mapNumber) + text);
            appendText(text);
        }
        
        resultFlag = false;
    }//end method
    
    public void setOperationInMap(String operation)
    {
        expression.put(op, operation); 
        
        resultFlag = false;
        Number1Flag = false;
    }

    public void setDecimalInNumberInMap()
    {
        String mapNumber = getMapNumber();
        
        if(resultFlag || !expression.containsKey(mapNumber) || expression.get(mapNumber).contentEquals("0"))
        {
            expression.put(mapNumber, "0.");
            setText("0.");
        }
        else
        {
            if(!txtDisplay.getText().contains("."))
            {
                expression.put(mapNumber, expression.get(mapNumber) + ".");
                appendText(".");
            }
            else
            {
                return;
            }
        }//end else
        
        resultFlag = false;
    }//end method
    
    public void setNegationOfNumberInMap()
    {
        if(resultFlag) //Don't change the result.
        {
            return;
        }
        
        String number;
        String mapNumber = getMapNumber();
        
        if(!expression.containsKey(mapNumber))
        {
            clearEntry();
        }
        else if(expression.get(mapNumber).contentEquals("0"))
        {
            //Do nothing.
        }
        else
        {
            if(expression.get(mapNumber).contains("-"))
            {
                number = expression.get(mapNumber).substring(1, expression.get(mapNumber).length()); 
            }
            else
            {
                number = "-" + expression.get(mapNumber);
            }

            expression.put(mapNumber, number);
            setText(number);
        }//end else
    }//end method
    
    public void setSqrtOfNumberInMap()
    {
        if(resultFlag) //Don't change the result.
        {
            return;
        }
        
        String number;
        String mapNumber = getMapNumber();
        
        if(!expression.containsKey(mapNumber))
        {
            clearEntry();
        }
        else if(expression.get(mapNumber).contains("-"))
        {
            JOptionPane.showMessageDialog(null, "Can't sqrt a negative number.\nClearing the calculator.");  
            clear();
        }
        else
        {
            if(expression.get(mapNumber).endsWith("."))
            {
                expression.put(mapNumber, expression.get(mapNumber) + "0");
            }
            
            BigDecimal num = new BigDecimal(expression.get(mapNumber));
            BigDecimal value1 = new BigDecimal(Math.sqrt(num.doubleValue())); //Approx sqrt
            BigDecimal value2 = BigDecimal.ZERO;
            
            //Since BigDecimal.pow() only accepts integers for the power, an alternate solution is used.
            while(!value2.equals(value1))
            {
                value2 = value1;
                value1 = num.divide(value2, rounding);
                value1 = value1.add(value2, rounding);
                value1 = value1.divide(new BigDecimal("2"), rounding);
            }
            
            number = value1.toString();
            expression.put(mapNumber, number);
            setText(number);
        }//end else   
    }//end method
    
    public void setReciprocalOfNumberInMap()
    {
        if(resultFlag) //Don't change the result.
        {
            return;
        }
        
        String number;
        String mapNumber = getMapNumber();
        
        if(!expression.containsKey(mapNumber))
        {
            clearEntry();
        }
        else
        {
            if(expression.get(mapNumber).endsWith("."))
            {
                expression.put(mapNumber, expression.get(mapNumber) + "0");
            }
            
            BigDecimal num = new BigDecimal(expression.get(mapNumber));
            
            BigDecimal result = BigDecimal.ONE;
            result = result.divide(num, rounding);
            
            number = result.toString();
            expression.put(mapNumber, number);
            setText(number);
        }//end else
    }//end method
    
    public void setPercentageOfNumberInMap()
    {
        if(resultFlag) //Don't change the result.
        {
            return;
        }
        
        String number;
        String mapNumber = getMapNumber();
        
        if(!expression.containsKey(mapNumber))
        {
            clearEntry();
        }
        else
        {
            if(expression.get(mapNumber).endsWith("."))
            {
                expression.put(mapNumber, expression.get(mapNumber) + "0");
            }
            
            BigDecimal num = new BigDecimal(expression.get(mapNumber));
            BigDecimal result;
            
            if(Number1Flag) //Number1 / 100
            {
                result = num;
                result = result.divide(new BigDecimal("100"), rounding);
            }
            else //Number1 * Number2% -- assumption is that user wants to take Number2% of Number1, not Number2 / 100
            {
                BigDecimal percent = num;
                percent = percent.divide(new BigDecimal("100"), rounding);
                
                result = new BigDecimal(expression.get(n1));
                result = result.multiply(percent, rounding);
            }
            
            number = result.toString();
            expression.put(mapNumber, number);
            setText(number);
        }//end else
    }//end method 
    
    public String getMapNumber()
    {
        if(Number1Flag)
        {
            return n1;
        }
        else
        {
            return n2;
        }
    }//end method
    
    public boolean mapContainsNumber1()
    {
        return expression.containsKey(n1);
    }
    
    public boolean mapContainsNumber2()
    {
        return expression.containsKey(n2);
    }
    
    public boolean mapContainsOperation()
    {
        return expression.containsKey(op);
    }
    
    //This is only called when there are 2 numbers and an operation.
    public void calculate() 
    {
        BigDecimal num1 = new BigDecimal(expression.get(n1));
        BigDecimal num2 = new BigDecimal(expression.get(n2));
        BigDecimal result = num1;
        
        switch(expression.get(op))
        {
            case "+":
                result = result.add(num2, rounding);
                break;
            case "-":
                result = result.subtract(num2, rounding);
                break;
            case "*":
                result = result.multiply(num2, rounding);
                break;
            case "/":
                if(num2.compareTo(BigDecimal.ZERO) != 0)
                {
                    result = result.divide(num2, rounding);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Can't divide by zero.\nClearing the calculator.");
                    clear();
                    return;
                }
                break;
        }//end switch

        setText(result.toString());
        expression.put(n1, result.toString());
        expression.remove(n2);
        expression.remove(op);
        
        Number1Flag = true;
        resultFlag = true;
    }//end method
    
}//endclass
