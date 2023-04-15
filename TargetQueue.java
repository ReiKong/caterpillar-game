package assignment2;

//import java.sql.SQLOutput;

public class TargetQueue extends MyQueue<Position>{
    private MyStack<String> positions;

    public TargetQueue() {
        super();
        this.positions = new MyStack<>();
    }

    @Override
    public void clear() {
        super.clear();
        this.positions.clear();
    }

    public void addTargets(String input) {
        StringBuilder numbers = new StringBuilder();
        int numPos = 0;
        int numPeriod = 0;
        int numCommas = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '(') {                                                                       // SITUATION 1: c is left parenthesis
                if (numbers.length() != 0 || !(this.positions.isEmpty()) || (numPos >= 1 && input.charAt(i - 1) != '.')) {                         // wrong cases
                    throw new IllegalArgumentException("The syntax of the input is invalid.");
                }
                this.positions.push(String.valueOf(c));
                numPeriod = 0;

            } else if (c == '-' && (this.positions.peek().equals("(") || this.positions.peek().equals(","))) {
                numbers.append(c);

            } else if (c >= '0' && c <= '9') {                                                    // SITUATION 2: c is a digit
                if (this.positions.isEmpty() || this.positions.peek().equals(")") || this.positions.peek().equals(".")) {
                    throw new IllegalArgumentException("The syntax of the input is invalid.");
                }
                numbers.append(c);

            } else if (c == ',') {                                                                // SITUATION 3: c is a comma
                numCommas++;
                if (numbers.length() == 0 || this.positions.getSize() == 0) {                          // wrong cases
                    throw new IllegalArgumentException("The syntax of the input is invalid.");
                } else if (numCommas > 1) {
                    throw new IllegalArgumentException("The syntax of the input is invalid.");
                }
                this.positions.push(numbers.toString());
                this.positions.push(String.valueOf(c));
                numbers = new StringBuilder();

            } else if (c == ')') {                                                                // SITUATION 4: c is a right parenthesis
                if ((numbers.length() == 0) || !(this.positions.getSize() == 3)) {                     // if no y-coordinate found / first char in coordinate string
                    throw new IllegalArgumentException("The syntax of the input is invalid.");
                }
                this.positions.pop();                                                                  // getting rid of comma
                int xCoord = Integer.parseInt(this.positions.pop());                                   // get number
                Position position = new Position(xCoord, Integer.parseInt(numbers.toString()));              //
                this.enqueue(position);
                numPos++;
                numCommas = 0;
                numbers = new StringBuilder();
                this.positions.clear();

            } else if (c == '.') {                                                                // SITUATION 5: c is a period
                if (numbers.length() != 0 || this.positions.getSize() != 0 || numPeriod >= 1) {                   //check if previous one was digit // correct case
                    throw new IllegalArgumentException("The syntax of the input is invalid.");
                }
                numPeriod++;

            } else {                                                                              // SITUATION 6: c is not a parenthesis, digit, or comma
                throw new IllegalArgumentException("The syntax of the input is invalid.");
            }
        }

        if (!(this.positions.isEmpty())) {
            throw new IllegalArgumentException("The syntax of the input is invalid.");
        }
    }
}
