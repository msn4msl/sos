/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package irrigationmanagementsystem;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author Sandeepa Madhusanka
 */
public class IrrigationManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static MainFrame mf;
    
    public static void main(String[] args) {
        try {
    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
            UIManager.setLookAndFeel(info.getClassName());
            break;
        }
    }
} catch (Exception e) {
    // If Nimbus is not available, you can set the GUI to another look and feel.
}
        mf=new MainFrame();
        mf.getContentPane().setBackground(Color.WHITE);
        mf.setVisible(true);
    }
    
}
