import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Main(Robot robot, Table table) {

    // Regex to parse the "PLACE X,Y,F" command
    private static final Pattern PLACE_COMMAND_PATTERN = Pattern.compile("^PLACE (\\d+),(\\d+),(NORTH|SOUTH|EAST|WEST)$");

    public static void main(String[] args) {
        Table table = new Table(5, 5);
        Robot robot = new Robot();
        Main app = new Main(robot, table);
        app.start();
    }

    // Starts the application loop, reading and process input from the console.
    public void start() {
        System.out.println("--- Toy Robot Start! ---");
        System.out.println("Valid commands are: PLACE X,Y,F | MOVE | LEFT | RIGHT | REPORT | EXIT");
        System.out.println("The robot must be placed on the 5x5 table before any other command is accepted.");
        System.out.println("---------------------------");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine().trim().toUpperCase();
                if ("EXIT".equals(input)) {
                    break;
                }
                processCommand(input);
            }
        }
        System.out.println("Robot Shut Down. Goodbye!");
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