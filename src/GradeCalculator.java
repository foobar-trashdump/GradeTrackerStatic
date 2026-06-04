public class GradeCalculator {

    // [COMPUTE] AVERAGE COMPUTATION METHOD
    // [STATIC] No instance state used — purely input → output math.
    // [TRACE] Input:  scores array of doubles (size varies)
    // [TRACE] Output: arithmetic mean as double
    // [UNDERSTAND] I used a for-each loop because I only need to read
    // values, not modify them or access indices.
    public static double computeAverage(double[] scores) {
        // [VALIDATE] Guard clause for null or empty array
        if (scores == null || scores.length == 0) return 0.0;

        // [TRACE] Accumulator pattern: sum = sum + score
        double sum = 0.0;
        for (double score : scores) {
            sum += score;
        }

        // [TRACE] Return sum divided by number of elements
        return sum / scores.length;
    }

    // [COMPUTE] RAW GRADE COMPUTATION METHOD (individual parameters)
    // [STATIC] Reads only its parameters and GradeConstants static fields.
    //          No instance variables touched — qualifies as static.
    // [TRACE] Input:  five component scores (0.0 – 100.0 each)
    // [TRACE] Output: weighted raw score rounded to 2 decimal places
    // [UNDERSTAND] Using individual parameters makes the method signature
    // self-documenting; callers must supply all five components explicitly,
    // reducing the chance of misplacing array indices.
    public static double computeRawGrade(double labPerformance,
                                         double classParticipation,
                                         double teacherEvaluation,
                                         double practicalExam,
                                         double project) {
        // [TRACE] Each component multiplied by its weight, then summed
        // [UNDERSTAND] GradeConstants.LAB_WEIGHT replaces old C.LAB_WEIGHT
        //              because GradeConstants fields are now static — accessed
        //              directly via the class name, no object needed.
        double raw = (labPerformance      * GradeConstants.LAB_WEIGHT)
                + (classParticipation  * GradeConstants.PARTICIPATION_WEIGHT)
                + (teacherEvaluation   * GradeConstants.EVALUATION_WEIGHT)
                + (practicalExam       * GradeConstants.PRACTICAL_WEIGHT)
                + (project             * GradeConstants.PROJECT_WEIGHT);

        // [TRACE] Round to 2 decimal places
        return Math.round(raw * 100.0) / 100.0;
    }

    // [GRADE] NUMERIC GRADE ASSIGNMENT METHOD
    // [STATIC] Pure function: same input always produces the same output.
    // [TRACE] avg = 97.0 -> avg >= 96.0 true  -> "4.0"
    // [TRACE] avg = 86.6 -> avg >= 83.0 true  -> "2.5"
    // [TRACE] avg = 65.0 -> all conditions false -> "0.0"
    // [UNDERSTAND] if-else-if ladder used because boundaries are numeric
    // ranges, not discrete values a switch expression can test directly.
    public static String assignNumericGrade(double avg) {
        if      (avg >= 96.0) return "4.0";
        else if (avg >= 93.0) return "3.5";
        else if (avg >= 90.0) return "3.0";
        else if (avg >= 86.0) return "2.5";
        else if (avg >= 83.0) return "2.0";
        else if (avg >= 80.0) return "1.75";
        else if (avg >= 76.0) return "1.5";
        else if (avg >= 73.0) return "1.25";
        else if (avg >= 70.0) return "1.0";
        else if (avg >= 68.0) return "0.75";
        else if (avg >= 65.0) return "0.5";
        else                  return "0.0";
    }

    // [GRADE] LETTER RANK ASSIGNMENT METHOD
    // [STATIC] Pure function — no instance fields referenced.
    // [TRACE] avg = 97.0 -> 'S'   avg = 86.6 -> 'C'
    // [TRACE] avg = 72.0 -> 'P'   avg = 65.0 -> 'F'
    public static char assignLetterRank(double avg) {
        if      (avg >= 96.0) return 'S';
        else if (avg >= 90.0) return 'A';
        else if (avg >= 86.0) return 'B';
        else if (avg >= 80.0) return 'C';
        else if (avg >= 75.0) return 'D';
        else if (avg >= 70.0) return 'E';
        else if (avg >= 65.0) return 'P';
        else                  return 'F';
    }

    // [REMARK] REMARKS METHOD — overloaded for numeric grade String
    // [STATIC] Input string maps deterministically to an output string.
    //          No object state involved — static is correct here.
    // [TRACE] Input:  grade string e.g. "4.0", "3.5" … "0.0"
    // [TRACE] Output: remark string describing performance level
    // [UNDERSTAND] Switch expression used because inputs are a small,
    // discrete set of known strings — more readable than if-else chains.
    public static String getRemarks(String grade) {
        return switch (grade) {
            case "4.0"  -> "Excellent";
            case "3.5"  -> "Superior";
            case "3.0"  -> "Very Good";
            case "2.5"  -> "Good";
            case "2.0"  -> "Above Average";
            case "1.75" -> "Average";
            case "1.5"  -> "Below Average";
            case "1.25" -> "Passing";
            case "1.0"  -> "Conditional";
            case "0.75" -> "Low Conditional";
            case "0.5"  -> "Incomplete";
            default     -> "Failed";
        };
    }

    // [REMARK] REMARKS METHOD — overloaded for letter rank char
    // [STATIC] Same reasoning: pure mapping, no instance state.
    // [UNDERSTAND] Java allows two methods with the same name "getRemarks"
    //              as long as their parameter types differ — this is called
    //              METHOD OVERLOADING. The compiler picks the right one
    //              based on whether you pass a String or a char.
    // [TRACE] Input:  rank char e.g. 'S', 'A', 'B' … 'F'
    // [TRACE] Output: remark string describing performance level
    public static String getRemarks(char rank) {
        return switch (rank) {
            case 'S' -> "Excellent";
            case 'A' -> "Superior";
            case 'B' -> "Very Good";
            case 'C' -> "Good";
            case 'D' -> "Above Average";
            case 'E' -> "Average";
            case 'P' -> "Passing";
            default  -> "Failed";
        };
    }
}