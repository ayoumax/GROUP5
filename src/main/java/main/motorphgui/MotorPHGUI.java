/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main.motorphgui;

/**
 *
 * @author Ayou
 */

import javax.swing.SwingUtilities;

public class MotorPHGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EmployeeTableFrame().setVisible(true);
        });
    }
}