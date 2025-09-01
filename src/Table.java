// Record class for represents the square table.
public record Table(int width, int height) {

    // Checks if a given position is within the table boundaries.
    public boolean isValidPosition(Position position) {
        return position.x() >= 0 && position.x() < width
                && position.y() >= 0 && position.y() < height;
    }
}
