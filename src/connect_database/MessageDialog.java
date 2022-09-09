/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect_database;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class MessageDialog {
    public static void showMessageDialog(Component parent, String conten, String tittle){
        JOptionPane.showMessageDialog(parent, conten, tittle, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static int showConfirmDialog(Component parent, String conten, String tittle){
        int choose = JOptionPane.showConfirmDialog(parent, conten, tittle,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return choose;
    }
}
