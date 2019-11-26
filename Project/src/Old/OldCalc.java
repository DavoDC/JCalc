package Old;

import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;

/**
 * The main class
 *
 * @author David
 */
public class OldCalc {

    // Program version string
    private static final String VERSION = "1.1";

    // Initialize helper objects
    private final Menu menu = new Menu();
    private final Processor proc = new Processor();

    // Initialize foundational GUI objects
    private JFrame frame = new JFrame();
    private JPanel contentPane = new JPanel();

    // Initialize individual components
    // Row 0
    private final JLabel title = new JLabel("Calculator", SwingConstants.CENTER);

    // Row 1
    private final JLabel expL = new JLabel("Expression", SwingConstants.CENTER);
    private final JLabel exp = new JLabel();

    // Row 2
    private final JLabel termL = new JLabel("Term", SwingConstants.CENTER);
    private final JLabel term = new JLabel();
    private final JButton action = new JButton("Add Term");

    // Row 3a
    private final JButton zeroKey = new JButton();
    private final JButton oneKey = new JButton();
    private final JButton twoKey = new JButton();
    private final JButton threeKey = new JButton();
    private final JButton fourKey = new JButton();
    private final JButton fiveKey = new JButton();
    private final JButton sixKey = new JButton();
    private final JButton sevenKey = new JButton();
    private final JButton eightKey = new JButton();
    private final JButton nineKey = new JButton();
    private final JButton dotKey = new JButton();
    private final JButton negKey = new JButton();
    private final JButton ansKey = new JButton();

    // Row 3b
    private final JButton plusOp = new JButton();
    private final JButton takeOp = new JButton();
    private final JButton divOp = new JButton();
    private final JButton multOp = new JButton();
    private final JButton raiseOp = new JButton();
    private final JButton modOp = new JButton();

    // Row 4
    private final JButton calc = new JButton("Calculate");

    // Row 5
    private final JLabel ansL = new JLabel("Answer", SwingConstants.CENTER);
    private final JLabel answer = new JLabel();
    private final JButton round = new JButton("Round");

    // Retrieve system info
    private static final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int SCREEN_W = (int) screen.getWidth();
    public static final int SCREEN_H = (int) screen.getHeight();

    // Special processing variables
    private int curCPPos = 0;
    private boolean termPhase = true;
    private int terms = 0;

    /**
     * Construct a Calculator
     */
    public OldCalc() {

        // Initialise JFrame
        frame = new JFrame();
        frame.setTitle("RobustCalc V" + VERSION + " - by David C, 2018-19");
        frame.setSize(SCREEN_W - SCREEN_W / 40, SCREEN_H - SCREEN_H / 8);
        frame.setLocation(SCREEN_W / 65, SCREEN_W / 65);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Generate and add menu
        frame.setJMenuBar(menu.getMenu());

        // Initialize content pane 
        contentPane = new JPanel();
        contentPane.setPreferredSize(frame.getSize());
        contentPane.setVisible(true);
        contentPane.setLayout(null);  //very important! stops button location reset!
        contentPane.setBackground(new Color(0, 190, 0));
        frame.add(contentPane);

        // Add all components to contentPane
        setupComponents();

        // Make specific changes        
        exp.setSize(exp.getWidth() + 300, exp.getHeight());
        exp.setForeground(Color.black);
        exp.setBackground(Color.lightGray);

        term.setSize(exp.getWidth(), exp.getHeight());
        term.setForeground(Color.black);
        term.setBackground(Color.white);

        action.setLocation(action.getX() + term.getWidth() + term.getX() - 50, term.getY());
        action.setForeground(Color.white);
        action.setBackground(Color.blue);

        ansL.setLocation(calc.getX(), calc.getY() + 50);
        ansL.setSize(ansL.getWidth() / 2, ansL.getHeight());
        ansL.setFont(new Font("Calibri", 0, 26));
        ansL.setBackground(new Color(240, 140, 0));

        answer.setSize((answer.getWidth() * 2) - 100, answer.getHeight());
        answer.setLocation(ansL.getX() + 230, ansL.getY());
        answer.setForeground(Color.black);
        answer.setBackground(new Color(250, 240, 100));
        answer.setFont(new Font("Calibri", 0, 22));

        round.setLocation(answer.getWidth() + answer.getX() + 15, ansL.getY());
        round.setSize(round.getWidth() / 2, round.getHeight());
        round.setFont(new Font("Calibri", 2, 22));
        round.setForeground(Color.black);
        round.setBackground(new Color(250, 100, 40));

        calc.setLocation(plusOp.getX(), plusOp.getY() + 80);
        calc.setSize(-8 + (modOp.getWidth() + 30) * 5, calc.getHeight() + 10);
        calc.setFont(new Font("Corbel", 1, 28));
        calc.setForeground(Color.yellow);
        calc.setBackground(new Color(195, 39, 234));

        // Make calculator expect a term first (disable operators)
        expectTerms();
    }

    /**
     * Get frame
     *
     * @return
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Adjust all components
     */
    private void setupComponents() {

        // Row 0
        title.setName("title");
        title.setEnabled(true);
        title.setVisible(true);
        title.setSize(frame.getWidth(), 80);
        title.setLocation(0, 0);
        title.setForeground(new Color(0, 250, 0));
        title.setBackground(new Color(60, 120, 40));
        title.setFont(new Font("Segoe UI", 1, 30));
        title.setOpaque(true);
        contentPane.add(title);
        curCPPos++;

        // Group 1
        contentPane.add(expL);
        contentPane.add(exp);

        // Group 2
        contentPane.add(termL);
        contentPane.add(term);

        action.setName("action");
        action.addActionListener(e -> addToExpression());
        contentPane.add(action);

        setupRows1to2();

        // Row 3a
        zeroKey.setText("0");
        zeroKey.setName("key");
        contentPane.add(zeroKey);

        oneKey.setText("1");
        oneKey.setName("key");
        contentPane.add(oneKey);

        twoKey.setText("2");
        twoKey.setName("key");
        contentPane.add(twoKey);

        threeKey.setText("3");
        threeKey.setName("key");
        contentPane.add(threeKey);

        fourKey.setText("4");
        fourKey.setName("key");
        contentPane.add(fourKey);

        fiveKey.setText("5");
        fiveKey.setName("key");
        contentPane.add(fiveKey);

        sixKey.setText("6");
        sixKey.setName("key");
        contentPane.add(sixKey);

        sevenKey.setText("7");
        sevenKey.setName("key");
        contentPane.add(sevenKey);

        eightKey.setText("8");
        eightKey.setName("key");
        contentPane.add(eightKey);

        nineKey.setText("9");
        nineKey.setName("key");
        contentPane.add(nineKey);

        dotKey.setText("DecP");
        dotKey.addActionListener(e -> term.setText(term.getText() + "."));
        contentPane.add(dotKey);

        negKey.setText("Neg");
        negKey.addActionListener(e -> term.setText("-" + term.getText()));
        contentPane.add(negKey);

        ansKey.setText("Ans");
        ansKey.addActionListener(e -> term.setText(term.getText() + answer.getText()));
        contentPane.add(ansKey);

        setupKeys();

        //Row 3b
        plusOp.setText("+");
        plusOp.setName("op");
        contentPane.add(plusOp);

        takeOp.setText("-");
        takeOp.setName("op");
        contentPane.add(takeOp);

        divOp.setText("/");
        divOp.setName("op");
        contentPane.add(divOp);

        multOp.setText("*");
        multOp.setName("op");
        contentPane.add(multOp);

        raiseOp.setText("^");
        raiseOp.setName("op");
        contentPane.add(raiseOp);

        modOp.setText("%");
        modOp.setName("op");
        contentPane.add(modOp);

        setupOps();

        // Group 4
        calc.addActionListener(e -> calcuation());
        contentPane.add(calc);

        // Group 5
        contentPane.add(ansL);

        contentPane.add(answer);

        contentPane.add(round);
        round.addActionListener(e -> roundAction());

        setupRows4to5();
    }

    /**
     * Initialize the first two rows
     */
    private void setupRows1to2() {

        // List of all component properties, divided by type
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(true); // Enabled?
        objects.add(true); // Visible?
        objects.add(Color.white); // Text Color?
        objects.add(Color.darkGray); // Button Color?
        objects.add("Calibri");  // Font?

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(100); // Xpos?
        numbers.add(100); // Ypos
        numbers.add(250); // Width
        numbers.add(40); // Height
        numbers.add(15); // Horizontal spacing
        numbers.add(10); // Vertical spacing
        numbers.add(2); // Columns
        numbers.add(24); // Fontsize

        // Use list to change all currently added objects
        setupComponents(objects, numbers);
    }

    /**
     * Initialize the key buttons
     */
    private void setupKeys() {

        // List of all component properties
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(true); // Enabled?
        objects.add(true); // Visible?
        objects.add(Color.black); // Text Color?
        objects.add(new Color(0, 250, 0)); // Button Color?
        objects.add("Calibri");  // Font?

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(80); // Xpos?
        numbers.add(250); // Ypos
        numbers.add(80); // Width
        numbers.add(50); // Height
        numbers.add(15); // Horizontal spacing
        numbers.add(15); // Vertical spacing
        numbers.add(13); // Columns
        numbers.add(22); // Fontsize

        // Use list to change all currently added objects
        setupComponents(objects, numbers);
    }

    /**
     * Initialize the operators
     */
    private void setupOps() {

        // List of all component properties
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(true); // Enabled?
        objects.add(true); // Visible?
        objects.add(Color.black); // Text Color?
        objects.add(new Color(250, 250, 0)); // Button Color?
        objects.add("Calibri");  // Font?

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(350); // Xpos?
        numbers.add(340); // Ypos
        numbers.add(70); // Width
        numbers.add(70); // Height
        numbers.add(15); // Horizontal spacing
        numbers.add(10); // Vertical spacing
        numbers.add(6); // Columns
        numbers.add(30); // Fontsize

        // Use list to change all currently added objects
        setupComponents(objects, numbers);
    }

    /**
     * Initialize row 4 and 5
     */
    private void setupRows4to5() {
        // List of all component properties, divided by type
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(true); // Enabled?
        objects.add(true); // Visible?
        objects.add(Color.black); // Text Color?
        objects.add(Color.white); // Button Color?
        objects.add("Calibri");  // Font?

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(100); // Xpos?
        numbers.add(450); // Ypos
        numbers.add(400); // Width
        numbers.add(50); // Height
        numbers.add(15); // Horizontal spacing
        numbers.add(10); // Vertical spacing
        numbers.add(3); // Columns
        numbers.add(20); // Fontsize

        // Use list to change all currently added objects
        setupComponents(objects, numbers);
    }

    /**
     * Setup a grid of components with a regular pattern
     *
     * @param Objects Enabled default, Visible default, Text color, Button
     * color, Font as a string
     *
     * @param Numbers The starting position (x and y). The button size (width
     * and height), The spacing (horizontal and vertical), The number of
     * columns, The fontsize
     */
    private void setupComponents(ArrayList<Object> objects, ArrayList<Integer> numbers) {

        // Retrieve object information
        Boolean enable = (boolean) objects.get(0);
        Boolean visible = (boolean) objects.get(1);
        Color textC = (Color) objects.get(2);
        Color bgC = (Color) objects.get(3);
        String font = (String) objects.get(4);

        // Retrieve numerical information
        int xpos = numbers.get(0);
        int ypos = numbers.get(1);
        int bW = numbers.get(2);
        int bH = numbers.get(3);
        int xspacing = numbers.get(4);
        int yspacing = numbers.get(5);
        int columns = numbers.get(6);
        int fontsize = numbers.get(7);

        // Current X and Y position
        int curxpos = xpos;
        int curypos = ypos;

        // Get the number of components
        int length = contentPane.getComponents().length;

        // A count of the buttons done
        int counter = 0;

        // For all components
        for (int i = curCPPos; i < length; i++) {
            Component curComp = contentPane.getComponent(i);

            counter++;

            curComp.setEnabled(enable);
            curComp.setVisible(visible);
            curComp.setSize(bW, bH);
            curComp.setLocation(curxpos, curypos);
            curComp.setForeground(textC);
            curComp.setBackground(bgC);
            curComp.setFont(new Font(font, 0, fontsize));

            // If current component has a name
            if (curComp.getName() != null) {

                // Get the name
                String name = curComp.getName();

                // If the component is a key or operator
                if (name.contains("key") || name.contains("op")) {

                    // Convert the component to a button
                    JButton curBut = ((JButton) curComp);

                    // Make the button add its text 
                    // to the term box when pressed
                    curBut.addActionListener(e -> addTextToTerm(curBut));
                }
            }

            // Fix JLabel transparency
            if (curComp instanceof JLabel) {
                ((JLabel) curComp).setOpaque(true);
            }

            // Move X and Y in regular pattern
            curxpos += (bW + xspacing);
            if ((counter % columns) == 0) {
                curxpos = xpos;
                curypos += (bH + yspacing);
            }

            // Move to next component
            curCPPos++;
        }

    }

    /**
     * Add a Button's text to the term field
     *
     * @param button
     */
    private void addTextToTerm(JButton button) {

        term.setText(term.getText() + button.getText());
    }

    /**
     * Add a term or operator to expression
     */
    private void addToExpression() {

        // Get input
        String addition = term.getText();

        // If term expected
        if (termPhase) {

            // Process as a term and save into processor
            proc.process("term", addition);
            terms++;

            // If we have both needed terms and operator
            if (terms == 2) // if term ,op, term in , do calc
            {
                // Change configuration of enabled buttons
                expectCalculation();

            } else {

                // If we have one term, we need an operator
                // Change configuration of enabled buttons
                expectOperators();

                // Change action button appearance
                switchActionB();
            }
        } else {

            // Else, process as operator and save into processor
            proc.process("op", addition);

            // Change configuration of enabled buttons
            expectTerms();

            // Change action button appearance
            switchActionB();
        }

        // Alternate between needing terms and not needing them
        termPhase = !termPhase;

        // Add the term's text to the full expression
        exp.setText(exp.getText() + term.getText());

        // Clear the term field
        term.setText("");
    }

    /**
     * Change the enabled state of a JButton using its name
     *
     * @param name
     * @param newstat
     */
    private void switchByName(String name, boolean newstat) {
        for (Component cur : contentPane.getComponents()) {
            if (cur instanceof JButton) {
                if (cur.getName() != null && cur.getName().equals(name)) {
                    cur.setEnabled(newstat);
                }
            }
        }
    }

    /**
     * Change the enabled state of a JButton using its text
     *
     * @param text
     * @param newstat
     */
    private void switchByText(String text, boolean newstat) {
        for (Component cur : contentPane.getComponents()) {
            if (cur instanceof JButton) {
                JButton curB = ((JButton) cur);
                if (curB.getText() != null && curB.getText().equals(text)) {
                    curB.setEnabled(newstat);
                }

            }
        }
    }

    /**
     * Enter term mode
     */
    private void expectTerms() {

        // Disable operators + calculate
        switchByName("op", false);
        switchByText("Calculate", false);

        // Enable terms + actionB
        switchByName("action", true);
        switchByName("key", true);
        switchByText("DecP", true);
        switchByText("Neg", true);
        switchByText("Ans", true);
    }

    /**
     * Enter operator mode
     */
    private void expectOperators() {

        // Disable term inputters
        switchByName("key", false);
        switchByText("DecP", false);
        switchByText("Neg", false);
        switchByText("Ans", false);

        // enable operators
        switchByName("op", true);
    }

    /**
     * Enter calculate mode
     */
    private void expectCalculation() {

        // Disable term inputters
        switchByName("key", false);
        switchByText("DecP", false);
        switchByText("Neg", false);
        switchByText("Ans", false);

        // Disable operators + action
        switchByName("op", false);
        switchByName("action", false);

        // Enable Calculate
        switchByText("Calculate", true);
    }

    /**
     * Calculate the full expression
     */
    private void calcuation() {

        // Get full expression and process
        String input = exp.getText();
        proc.process("exp", input);

        // Get output
        String output = proc.getOutput();

        // If error occurs
        if (output.equals("Error")) {

            // Show error popup
            showError();

        } else {

            // Else, reveal answer
            answer.setText(output);
        }

        // Reset fully 
        expectTerms();
        exp.setText("");
        termPhase = true;
        terms = 0;
        proc.reset();
    }

    /**
     * Clear certain input fields
     *
     * @param scope
     */
    public void clear(String scope) {
        switch (scope) {
            case "All":
                exp.setText("");
                term.setText("");
                answer.setText("");
                break;
            case "Expression":
                exp.setText("");
                break;
            case "Term":
                term.setText("");
                break;
            case "Answer":
                answer.setText("");
                break;
            default:
                break;
        }
    }

    /**
     * Round the result
     */
    private void roundAction() {

        // Get input
        String input = answer.getText();

        // If input is not empty or null
        if (!"".equals(input) || input != null) {

            // Process and get output
            proc.process("round", input);
            String output = proc.getOutput();

            // If error occurs
            if (output.equals("Error")) {

                // Show error popup
                showError();

            } else {

                // Else, adjust answer
                answer.setText(output);
            }
        }

    }

    /**
     * Change the appearance of the add term/operator button
     */
    private void switchActionB() {

        // If the button is for terms
        if (action.getText().contains("Term")) {

            // Make it for operator
            action.setText("Add Operator");
            action.setBackground(Color.CYAN);
            action.setForeground(Color.black);
        } else {

            // Else if the button is for operators,
            // make it for terms
            action.setText("Add Term");
            action.setBackground(Color.blue);
            action.setForeground(Color.white);
        }
    }

    /**
     * Generate an error popup
     */
    private void showError() {

        // Create label
        JLabel text = new JLabel("Invalid input");
        text.setFont(new Font("Calibri", 0, 40));
        text.setOpaque(true);
        text.setBackground(Color.darkGray);
        text.setForeground(Color.white);

        // Create JFrame and add label
        JFrame popup = new JFrame();
        popup.add(text);
        popup.setVisible(true);
        popup.setBackground(Color.black);
        popup.setLocation(SCREEN_W / 2 - 500, SCREEN_H / 2);
        popup.pack();
    }
}
