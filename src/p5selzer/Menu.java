//Brandon Selzer

package p5selzer;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class Menu extends JMenuBar
{
    private JMenu edit;
    private JMenu view;
    private JMenu help; 
    
    public Menu()
    {
        initialize();
    }
    
    private void initialize()
    {
        initializeComponents();
    }
    
    private void initializeComponents()
    {
        edit = new JMenu();
        edit.setText("Edit");
        add(edit);
        
        view = new JMenu();
        view.setText("View");
        add(view);
        
        help = new JMenu();
        help.setText("Help");
        add(help);
    }
}//end class
