package Old;

import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;

/**
 * Provides additional utilities
 *
 * @author David
 */
public class Utils {

    // Current color values
    private int R = 0;
    private int G = 0;
    private int B = 0;

    // RGB Text Area
    private final JTextArea rgbVals = new JTextArea(0 + " , " + 0 + " , " + 0);

    /**
     * Create ASCII converter popup utility
     */
    public void asciiConverter() {

        // Size of components
        int width = 800;
        int height = 40;

        // Initialise popupwindow and contentpanel
        JPanel panel = generateColJP("ASCII Converter", width, Color.orange);

        // Text area
        JTextArea field = new JTextArea();
        field.setForeground(Color.black);
        field.setBackground(Color.white);
        field.setFont(new Font("Cambria", 0, 26));
        field.setSize(width, height);
        field.setLocation(0, 100);
        panel.add(field);

        // Button
        JButton convert = new JButton("Convert");
        convert.setForeground(Color.black);
        convert.setBackground(Color.yellow);
        convert.setFont(new Font("Cambria", 0, 28));
        convert.setSize(width / 2, height);
        int conXpos = width / 2 - convert.getWidth() / 2;
        convert.setLocation(conXpos, field.getY() + 60);
        panel.add(convert);

        // Output
        JLabel output = new JLabel();
        output.setForeground(Color.black);
        output.setBackground(Color.lightGray);
        output.setFont(new Font("Cambria", 0, 26));
        output.setSize(width, height);
        output.setLocation(0, convert.getY() + 60);
        output.setOpaque(true);
        panel.add(output);

        // Add button action
        convert.addActionListener(e -> output.setText(asciiConv(field.getText())));

        // Make popup and add content
        JFrame popup = new JFrame();
        popup.setSize(width, width / 2);
        popup.add(panel);
        popup.setVisible(true);
        popup.setResizable(false);
        popup.setLocation(300, 200);
    }

    /**
     * Return a string of space-separated ASCII values of the input string
     *
     * @param s
     * @return
     */
    private String asciiConv(String s) {

        // If length is too large, do not process further
        if (s.length() > 1000) {
            return "Overload";
        }

        // Result holder
        String result = "";

        // For each character, append char + ASCII val + space
        for (char c : s.toCharArray()) {
            result += ("'" + c + "':" + (int) c + "  ");
        }

        // Return holder
        return result;
    }

    public void rgbSampler() {

        // Initialise popupwindow and contentpanel
        JPanel jpanel = generateColJP("RGB Sampler", 300, Color.black);

        // Buttons
        ArrayList<JButton> buttons = new ArrayList<>();

        // Increment between base value changes
        int incr = 5;

        JButton redUp = new JButton();
        redUp.setText("+");
        redUp.setBackground(Color.red);
        redUp.addActionListener(e -> processChange("r", incr, jpanel));
        buttons.add(redUp);

        JButton greenUp = new JButton();
        greenUp.setText("+");
        greenUp.setBackground(Color.green);
        greenUp.addActionListener(e -> processChange("g", incr, jpanel));
        buttons.add(greenUp);

        JButton blueUp = new JButton();
        blueUp.setText("+");
        blueUp.setBackground(Color.blue);
        blueUp.addActionListener(e -> processChange("b", incr, jpanel));
        buttons.add(blueUp);

        JButton redDown = new JButton();
        redDown.setText("-");
        redDown.setBackground(Color.red);
        redDown.addActionListener(e -> processChange("r", -incr, jpanel));
        buttons.add(redDown);

        JButton greenDown = new JButton();
        greenDown.setText("-");
        greenDown.setBackground(Color.green);
        greenDown.addActionListener(e -> processChange("g", -incr, jpanel));
        buttons.add(greenDown);

        JButton blueDown = new JButton();
        blueDown.setText("-");
        blueDown.setBackground(Color.blue);
        blueDown.addActionListener(e -> processChange("b", -incr, jpanel));
        buttons.add(blueDown);

        ArrayList<Object> objects = new ArrayList<>();
        objects.add(true); //enabled default   
        objects.add(true); //visible default         
        objects.add(Color.black); //text color
        objects.add(null); //button color
        objects.add("Corbel"); //font as a string
        objects.add(jpanel);  //destination

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(75); //xpos
        numbers.add(75); //ypos
        numbers.add(45); //button width
        numbers.add(45); //button height
        numbers.add(10); //horizontal spacing
        numbers.add(150);  //vertical spacing
        numbers.add(3); //columns
        numbers.add(24); //fontsize

        setupButtonGrid(buttons, objects, numbers);

        // Perfect text area
        rgbVals.setOpaque(true);
        rgbVals.setForeground(Color.black);
        rgbVals.setBackground(Color.white);
        rgbVals.setFont(new Font("Cambria", 0, 26));
        rgbVals.setAlignmentX(0.5f);
        rgbVals.setSize(180, 40);
        rgbVals.setLocation(redUp.getX() - 20, redUp.getY() + 100);

        // Add label
        jpanel.add(rgbVals);

        // Make popup and add content
        JFrame popup = new JFrame();
        popup.setSize(300, 400);
        popup.add(jpanel);
        popup.setVisible(true);
        popup.setResizable(false);
        popup.setLocation(300, 200);

    }

    /**
     * Process a change in colour
     *
     * @param colS
     * @param change
     * @param dest
     */
    private void processChange(String colS, int change, JPanel dest) {

        // Act based on base colour
        switch (colS) {
            case "r":
                R += change;
                R = wrapAroundRGB(R);
                break;
            case "g":
                G += change;
                G = wrapAroundRGB(G);
                break;
            case "b":
                B += change;
                B = wrapAroundRGB(B);
                break;
            default:
                break;
        }

        // Generate new colour and change background
        Color col = new Color(R, G, B);
        dest.setBackground(col);

        // Update text area
        rgbVals.setText(" " + R + " , " + G + " , " + B);
    }

    /**
     * Keep a RGB base value within the correct limits
     *
     * @param input
     * @return output
     */
    private int wrapAroundRGB(int input) {

        // If too small
        if (0 > input) {

            // Wrap back to 255
            return 255;
        }

        // Otherwise, return modulo 255
        // This will return the input as is when below 255, 
        // and will wrap around when above 255
        return input % 255;
    }

    /**
     * Generate a JPanel with a certain title and color
     *
     * @param titleS
     * @param titleWidth
     * @param col
     * @return
     */
    private JPanel generateColJP(String titleS, int titleWidth, Color col) {

        // Initialize JPanel and its colour
        JPanel jpanel = new JPanel(null);
        jpanel.setBackground(col);

        // Make title label
        JLabel title = new JLabel();
        title.setSize(titleWidth, 40);
        title.setLocation(0, 10);
        title.setForeground(Color.white);
        title.setBackground(Color.darkGray);
        title.setFont(new Font("Cambria", 0, 24));
        title.setText(titleS);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setHorizontalTextPosition(SwingConstants.CENTER);
        title.setOpaque(true);
        title.setEnabled(true);
        title.setVisible(true);

        // Add title label
        jpanel.add(title);

        return jpanel;
    }

    /**
     * Setup a grid of buttons with a regular pattern and spacing
     *
     * @param buttons An ArrayList of JButtons
     *
     * @param objects Common name as a string, Enabled default, Visible default,
     * Text color, Button color, Font as a string, Destination of buttons
     *
     * @param numbers The starting position (x and y), The button size (width
     * and height), The spacing (horizontal and vertical), The number of
     * columns, The font size
     */
    public void setupButtonGrid(ArrayList<JButton> buttons,
            ArrayList<Object> objects, ArrayList<Integer> numbers) {

        // Get object information
        Boolean enable = (boolean) objects.get(0);
        Boolean visible = (boolean) objects.get(1);
        Color textC = (Color) objects.get(2);
        Color bgC = (Color) objects.get(3);
        String font = (String) objects.get(4);
        JPanel cp = (JPanel) objects.get(5);

        // Get numerical information
        int xpos = numbers.get(0);
        int ypos = numbers.get(1);
        int bW = numbers.get(2);
        int bH = numbers.get(3);
        int xspacing = numbers.get(4);
        int yspacing = numbers.get(5);
        int columns = numbers.get(6);
        int fontsize = numbers.get(7);

        // Make adjustments to put buttons in a regular pattern
        int curxpos = xpos;
        int curypos = ypos;

        int counter = 0;
        for (JButton curButton : buttons) {
            counter++;

            curButton.setEnabled(enable);
            curButton.setVisible(visible);
            curButton.setSize(bW, bH);
            curButton.setLocation(curxpos, curypos);

            if (textC != null && bgC != null) {
                curButton.setForeground(textC);
                curButton.setBackground(bgC);
            }

            curButton.setFont(new Font(font, 0, fontsize));

            cp.add(curButton);

            curxpos += (bW + xspacing);
            if ((counter % columns) == 0) {
                curxpos = xpos;
                curypos += (bH + yspacing);
            }
        }
    }

}
