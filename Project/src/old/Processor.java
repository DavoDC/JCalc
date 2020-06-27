package old;

import java.util.ArrayList;

/**
 * Provides mathematical processing of strings
 *
 * @author David
 */
public class Processor {

    // The list of numbers as doubles
    private ArrayList<Double> numbers = new ArrayList<>();

    // The operator
    private char op;

    // Output holder
    private String output;

    /**
     * Retrieve output
     *
     * @return
     */
    public String getOutput() {
        return output;
    }

    /**
     * Process a certain type of input
     *
     * @param type
     * @param input
     */
    public void process(String type, String input) {
        try {

            // Act based on type
            switch (type) {
                case "op":
                    // If an operator:

                    // Get characters
                    char[] opchar = input.toCharArray();
                    // Ensure there is only one character
                    if (opchar.length != 1) {
                        throw new IllegalArgumentException("Operator issue");
                    }
                    // Save the character
                    op = opchar[0];
                    break;

                case "term":

                    // If a term, parse and save it
                    double termNum = Double.parseDouble(input);
                    numbers.add(termNum);
                    break;

                case "exp":

                    // If an expression, evaluate it
                    output = evaluate();
                    break;

                case "round":

                    // If rounding required, round and save output
                    double roundNum = Double.parseDouble(input);
                    roundNum = Math.round(roundNum);
                    output = ("" + roundNum + "");
                    break;

                default:
                    break;
            }
        } catch (IllegalArgumentException e) {
            output = "Error";
        }
    }

    /**
     * Evaluate saved information
     *
     * @return
     */
    private String evaluate() {
        return evaluateArgs(numbers.get(0), numbers.get(1), op);
    }

    /**
     * Return the computational result of two numbers and an operator.
     * Otherwise, return 'Error'
     *
     * @param first
     * @param second
     * @param op
     * @return
     */
    public String evaluateArgs(double first, double second, char op) {

        // Act based on operator
        switch (op) {
            case '+':
                return ("" + (first + second) + "");
            case '-':
                return ("" + (first - second) + "");
            case '*':
                return ("" + (first * second) + "");
            case '/':
                return ("" + (first / second) + "");
            case '^':
                return ("" + Math.pow(first, second) + "");
            case '%':
                return ("" + (first % second) + "");
            default:
                break;
        }

        // Return Error if unknown operator encountered
        return "Error";
    }

    /**
     * Reset all information saved in this processor
     */
    public void reset() {
        numbers = null;
        numbers = new ArrayList<>();
        output = null;
        output = "";
    }
}
