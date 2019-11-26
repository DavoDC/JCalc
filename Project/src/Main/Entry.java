package Main;

import Old.OldCalc;
import Simple.SimpCalc;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * The entry class
 *
 * @author David
 */
public class Entry {

    // Globally accessible calculators
    public static OldCalc oldCalc;
    public static SimpCalc simpCalc;

    /**
     * The entry point
     *
     * @param args
     */
    public static void main(String args[]) {

        // Start with simple calculator
        switchToSimple();
    }

    /**
     * Show simple calculator
     */
    public static void switchToSimple() {

        // If old is initialized
        if (oldCalc != null) {

            // End
            oldCalc.getFrame().dispose();
        }

        // Set look and feel
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.print(e.toString());
            System.exit(1);
        }

        // Schedule creation of new calc
        java.awt.EventQueue.invokeLater(() -> {
            simpCalc = new SimpCalc();
            SimpCalc.adjustFrame();
        });

    }

    /**
     * Switch to old calculator
     */
    public static void switchToOld() {

        // If new is initialized
        if (simpCalc != null) {

            // End
            simpCalc.dispose();
        }

        // Start old calc
        Entry.oldCalc = new OldCalc();
    }

}
