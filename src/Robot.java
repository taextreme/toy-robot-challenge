// Class representing the Robot.
public class Robot {
    private Position position;
    private Direction direction;
    private boolean isPlaced = false;

    // Places the robot on the table at a specific position and direction.
    // Ignore invalid position.
    public void place(Position position, Direction direction, Table table) {
        if (table.isValidPosition(position)) {
            this.position = position;
            this.direction = direction;
            this.isPlaced = true;
        }
    }

    // Moves the robot one step forward in its current direction.
    // Ignore invalid position.
    public void move(Table table) {
        if (!isPlaced)
            return;
        Position nextPosition = position.move(direction);
        if (table.isValidPosition(nextPosition)) {
            this.position = nextPosition;
        }
    }

    // Rotates the robot to the left.
    public void turnLeft() {
        if (!isPlaced)
            return;
        this.direction = this.direction.turnLeft();
    }

    // Rotates the robot to the right.
    public void turnRight() {
        if (!isPlaced)
            return;
        this.direction = this.direction.turnRight();
    }

    // Report a string representing the robot's current position and direction.
    public String report() {
        if (!isPlaced) {
            return "Robot is not on the table!";
        }
        return String.format("Output: %d,%d,%s", position.x(), position.y(), direction.name());
    }

    public boolean isPlaced() {
        return isPlaced;
    }
}
