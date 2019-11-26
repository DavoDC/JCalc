package Old;

import Main.Entry;
import java.awt.*;
import javax.swing.*;

/**
 * Provides the Menu for the GUI
 *
 * @author David
 */
public class Menu extends JFrame {

    // Helper objects
    private final JMenuBar menuBar = new JMenuBar();
    private Utils util = new Utils();

    /**
     * Constructor for objects of class Menu
     */
    public Menu() {

        // Common fonts
        int mainSize = 22;
        Font mainFont = new Font("Calibri", 0, mainSize);
        Font subFont = new Font("Calibri", 0, mainSize - 2);

        // Main 1 = Switch
        JButton sw = new JButton("Switch");
        sw.addActionListener(e -> Entry.switchToSimple());
        sw.setFont(mainFont);
        sw.setForeground(Color.darkGray);
        sw.setOpaque(false);
        sw.setContentAreaFilled(false);
        sw.setBorderPainted(false);

        // Main 2 = Resetting Fields
        JMenu reset = new JMenu("Clear");
        reset.setFont(mainFont);

        JMenuItem all = new JMenuItem("All");
        all.setFont(subFont);
        all.addActionListener(e -> Entry.oldCalc.clear("All"));
        reset.add(all);

        JMenuItem exp = new JMenuItem("Expression");
        exp.setFont(subFont);
        exp.addActionListener(e -> Entry.oldCalc.clear("Expression"));
        reset.add(exp);

        JMenuItem term = new JMenuItem("Term");
        term.setFont(subFont);
        term.addActionListener(e -> Entry.oldCalc.clear("Term"));
        reset.add(term);

        JMenuItem ans = new JMenuItem("Answer");
        ans.setFont(subFont);
        ans.addActionListener(e -> Entry.oldCalc.clear("Answer"));
        reset.add(ans);

        // Main 3 = Utilities
        JMenu utils = new JMenu("Utilities");
        utils.setFont(mainFont);

        JMenuItem ascii = new JMenuItem("ASCII Converter");
        ascii.setFont(subFont);
        ascii.addActionListener(e -> util.asciiConverter());
        utils.add(ascii);

        JMenuItem rgb = new JMenuItem("RGB Color Sampler");
        rgb.setFont(subFont);
        rgb.addActionListener(e -> util.rgbSampler());
        utils.add(rgb);

        // Main 4 = Exit
        JMenu exitM = new JMenu("Exit");
        exitM.setFont(mainFont);

        JMenuItem exit = new JMenuItem("Confirm");
        exit.addActionListener(e -> System.exit(0));
        exit.setFont(subFont);
        exitM.add(exit);

        // Add JMenu objects to menuBar
        menuBar.add(sw);
        menuBar.add(utils);
        menuBar.add(reset);
        menuBar.add(exitM);
    }

    /**
     * Retrieve the underlying menuBar - for adding to JFrames
     *
     * @return menuBar
     */
    public JMenuBar getMenu() {
        return menuBar;
    }
}
