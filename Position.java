package assignment2;

// import javafx.geometry.Pos;

public class Position {
    private int xCoord;
    private int yCoord;

    public Position(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public Position(Position position) {
        this.xCoord = position.xCoord;
        this.yCoord = position.yCoord;
    }

    public void reset(int newXCoord, int newYCoord) {
        this.xCoord = newXCoord;
        this.yCoord = newYCoord;
    }

    public void reset(Position position) {
        this.xCoord = position.xCoord;
        this.yCoord = position.yCoord;
    }

    public static int getDistance(Position A, Position B) {
        int xDistance = Math.abs(A.xCoord - B.xCoord);
        int yDistance = Math.abs(A.yCoord - B.yCoord);

        return xDistance + yDistance;
    }

    public int getX() {
        return this.xCoord;
    }

    public int getY() {
        return this.yCoord;
    }

    public void moveWest() {
        this.xCoord--;
    }

    public void moveEast() {
        this.xCoord++;
    }

    public void moveNorth() {
        this.yCoord--;
    }

    public void moveSouth() {
        this.yCoord++;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position)) {
            return false;
        }

        boolean sameX = this.xCoord == ((Position) obj).xCoord;
        boolean sameY = this.yCoord == ((Position) obj).yCoord;

        return sameX && sameY;

    }
}
