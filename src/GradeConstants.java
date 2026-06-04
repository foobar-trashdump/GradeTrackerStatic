public class GradeConstants {
    // [STATIC] static final = class-level constant (shared, immutable)
    // [UNDERSTAND] "static" means it belongs to GradeConstants the class,
    //              not to any particular GradeConstants object.
    //              "final" means it can never be reassigned after this line.

    // [CAPACITY] Student roster limit
    public static final int    MAX_STUDENTS       = 50;

    // [RANGE] Valid score bounds
    public static final double MIN_SCORE          = 0.0;
    public static final double MAX_SCORE          = 100.0;

    // [WEIGHT] Component weights for raw grade computation
    // [TRACE] All five weights must sum to 1.0 (100%)
    //         0.40 + 0.05 + 0.05 + 0.20 + 0.30 = 1.00
    public static final double LAB_WEIGHT         = 0.40;
    public static final double PARTICIPATION_WEIGHT = 0.05;
    public static final double EVALUATION_WEIGHT  = 0.05;
    public static final double PRACTICAL_WEIGHT   = 0.20;
    public static final double PROJECT_WEIGHT     = 0.30;

    // [MODULE] Number of lab performance modules
    public static final int    NUM_MODULES        = 5;

    // [ID] ID number constraints
    public static final int    ID_LENGTH          = 8;
    public static final int    ID_DIVISOR         = 11;
    public static final int    FACULTY_THRESHOLD  = 16;

    // [FORMAT] Separator line width
    public static final int    SEPARATOR          = 80;
}