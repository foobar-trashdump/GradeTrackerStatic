public class GradeTrackerApp {

    // [DECLARE] Instance fields — each running app gets its own copies
    private StudentRepository   repo          = new StudentRepository();
    private StudentInputHandler inputHandler  = new StudentInputHandler();
    private IDVerifier          idVerifier    = new IDVerifier();

    // [ENTRY] Standard Java entry point — must be static
    // [UNDERSTAND] The JVM starts here. Because main() is static,
    //              it has no "this" — so we create one GradeTrackerApp
    //              instance and hand control to its instance method.
    public static void main(String[] args) {
        // [TRACE] Create one app object, then start the menu loop
        new GradeTrackerApp().displayMenu();
    }

    // [MENU] Main menu loop (instance method — uses repo, inputHandler, etc.)
    // [UNDERSTAND] do-while used so the menu always appears at least
    // once before checking if the user chose to exit.
    void displayMenu() {
        int choice;

        do {
            IO.println("\n" + "=".repeat(GradeConstants.SEPARATOR));
            IO.println("                  GRADE TRACKER SYSTEM");
            IO.println("=".repeat(GradeConstants.SEPARATOR));
            IO.println("  [1] Enter Student Data");
            IO.println("  [2] View Grade Report");
            IO.println("  [3] View Class Statistics");
            IO.println("  [4] Verify ID Number");
            IO.println("  [5] Exit");
            IO.println("=".repeat(GradeConstants.SEPARATOR));
            IO.print("Select option: ");

            choice = Integer.parseInt(IO.readln().trim());

            switch (choice) {
                case 1 -> inputStudentData();
                case 2 -> ReportPrinter.printReport(repo);
                case 3 -> ReportPrinter.printClassStats(repo);
                case 4 -> idVerifier.verifyID();
                case 5 -> IO.println("Goodbye!");
                default -> IO.println("Invalid option. Please enter 1–5.");
            }

        } while (choice != 5);
    }

    // [INPUT] Collects data for all students and stores them in the repo
    // [UNDERSTAND] for loop used because studentCount is known upfront —
    // the index and termination condition are visible on a single line.
    private void inputStudentData() {
        IO.println("STUDENT DATA ENTRY:");
        int studentCount = inputHandler.inputStudentCount();

        for (int i = 1; i <= studentCount; i++) {
            // [TRACE] inputOneStudent() handles all prompts for one student
            Student student = inputHandler.inputOneStudent(i);
            repo.addStudent(student);
        }

        IO.println("\nData entry complete. " + studentCount + " student(s) recorded.");
    }
}