public class IDVerifier {

    // [VERIFY] Runs the interactive ID verification loop (instance method)
    // [UNDERSTAND] do-while used because the ID prompt must appear at
    // least once before asking whether to continue.
    public void verifyID() {
        String continueInput;

        do {
            IO.print("Enter ID: ");
            String idNumber = IO.readln().trim();

            // [TRACE] Static call — delegates to validateID() for the message
            IO.println(validateID(idNumber));

            IO.print("Check another ID? (yes/y to continue): ");
            continueInput = IO.readln().trim().toLowerCase();

        } while (continueInput.equalsIgnoreCase("yes")
                || continueInput.equalsIgnoreCase("y"));

        IO.println("Exiting ID verification.");
    }

    // [VERIFY] Returns true if ID passes all three format checks, false otherwise
    // [STATIC] Pure predicate — same input always gives the same boolean result.
    // [TRACE] Check 1: length must equal ID_LENGTH (8)
    // [TRACE] Check 2: every character must be a digit
    // [TRACE] Check 3: dot product must be divisible by ID_DIVISOR (11)
    public static boolean isValidID(String idNumber) {
        if (idNumber == null || idNumber.length() != GradeConstants.ID_LENGTH)
            return false;

        if (!idNumber.chars().allMatch(Character::isDigit))
            return false;

        // [TRACE] Divisibility check via dot product
        return calculateDotProduct(idNumber) % GradeConstants.ID_DIVISOR == 0;
    }

    // [VERIFY] Returns "faculty" or "student" — call ONLY after isValidID() is true
    // [STATIC] Pure classifier — no side effects, no instance state.
    // [TRACE] quotient = dotProduct / ID_DIVISOR
    //         quotient >= FACULTY_THRESHOLD (16) → "faculty"; else → "student"
    public static String getIDRole(String idNumber) {
        int quotient = calculateDotProduct(idNumber) / GradeConstants.ID_DIVISOR;
        return (quotient >= GradeConstants.FACULTY_THRESHOLD) ? "faculty" : "student";
    }

    // [VERIFY] Returns a human-readable result message for a given ID string
    // [STATIC] Thin wrapper — delegates to isValidID() and getIDRole() so
    //          the actual rules live in exactly one place each.
    // [REFACTOR] one call to isValidID() covers all three checks;
    //            one call to getIDRole() determines faculty vs student.
    // [TRACE] Invalid ID  → returns specific reason message starting with "Invalid"
    // [TRACE] Valid ID    → returns "Valid faculty ID number." or
    //                       "Valid student ID number."
    public static String validateID(String idNumber) {
        // [VALIDATE] Length check — gives a specific message, not just "false"
        if (idNumber == null || idNumber.length() != GradeConstants.ID_LENGTH)
            return "Invalid ID number. Please enter "
                    + GradeConstants.ID_LENGTH + " characters.";

        // [VALIDATE] Digit check — specific message for non-digit input
        if (!idNumber.chars().allMatch(Character::isDigit))
            return "Invalid ID number. All characters must be digits.";

        // [VALIDATE] Divisibility check — delegates to isValidID() for the math
        // [TRACE] At this point length and digits are confirmed; isValidID()
        //         re-runs those checks cheaply and then tests divisibility.
        //         The small cost is worth it to keep a single source of truth.
        if (!isValidID(idNumber))
            return "Invalid ID number. Dot product must be divisible by "
                    + GradeConstants.ID_DIVISOR + ".";

        // [TRACE] All checks passed — delegate role decision to getIDRole()
        return "Valid " + getIDRole(idNumber) + " ID number.";
    }

    // [COMPUTE] Dot product of ID digits against descending weights [8..1]
    // [STATIC] Pure math function — no instance fields, no I/O.
    // [TRACE] ID "11191031", weights [8,7,6,5,4,3,2,1]:
    //         1×8 + 1×7 + 1×6 + 9×5 + 1×4 + 0×3 + 3×2 + 1×1 = 66
    //         66 % 11 = 0 → valid; 66 / 11 = 6 → student (6 < 16)
    private static int calculateDotProduct(String idNumber) {
        int dotProduct = 0;
        int weight = GradeConstants.ID_LENGTH; // starts at 8, decrements to 1

        for (int i = 0; i < GradeConstants.ID_LENGTH; i++) {
            int digit = Character.getNumericValue(idNumber.charAt(i));
            dotProduct += digit * weight;
            weight--;
        }
        return dotProduct;
    }
}