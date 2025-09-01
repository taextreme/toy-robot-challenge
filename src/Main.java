import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Main(Robot robot, Table table) {

    // Regex to parse the "PLACE X,Y,F" command
    private static final Pattern PLACE_COMMAND_PATTERN = Pattern.compile("^PLACE (\\d+),(\\d+),(NORTH|SOUTH|EAST|WEST)$");

    public static void main(String[] args) {
        Table table = new Table(5, 5);
        Robot robot = new Robot();
        Main app = new Main(robot, table);
    }

    // Process Command String
    public void processCommand(String command) {
        Matcher placeMatcher = PLACE_COMMAND_PATTERN.matcher(command);

        if (placeMatcher.matches()) {
            // Process PLACE command
            int x = Integer.parseInt(placeMatcher.group(1));
            int y = Integer.parseInt(placeMatcher.group(2));
            Direction direction = Direction.valueOf(placeMatcher.group(3));
            robot.place(new Position(x, y), direction, table);
        } else if (robot.isPlaced()) {
            // Process other commands only if the robot has been placed
            switch (command) {
                case "MOVE" -> robot.move(table);
                case "LEFT" -> robot.turnLeft();
                case "RIGHT" -> robot.turnRight();
                case "REPORT" -> System.out.println(robot.report());
                default -> {
                    // Ignore invalid commands
                }
            }
        }
    }
}