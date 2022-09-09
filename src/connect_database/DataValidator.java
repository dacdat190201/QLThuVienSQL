/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect_database;

import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class DataValidator {
    //Kiểm tra dữ liệu không được bỏ trống
    public static void validatorEmpty(JTextField txt, StringBuilder sb, String error){
        if (txt.getText().equals("")){
            sb.append(error).append("\n");
            txt.setBackground(Color.LIGHT_GRAY);
            txt.requestFocus();
        }else {
            txt.setBackground(Color.WHITE);
        }
    }
    
    public static void validatorEmpty(JPasswordField txtpw, StringBuilder sb, String error){
        String ps = new String(txtpw.getPassword());
        if (ps.equals("")){
            sb.append(error).append("\n");
            txtpw.setBackground(Color.LIGHT_GRAY);
            txtpw.requestFocus();
        }else {
            txtpw.setBackground(Color.WHITE);
        }
    } 
}
