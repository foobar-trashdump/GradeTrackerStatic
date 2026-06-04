class Student {
    // [DECLARE] Instance fields — each Student object gets its own copy
    // [UNDERSTAND] "private" means only methods inside Student can read
    // or write these directly. Outside classes must use getters/setters.
    private String name;
    private String idNumber;
    private double rawGrade;
    private String numericGrade;
    private char   letterRank;

    // [CONSTRUCT] Default constructor
    public Student() {
        // default constructor
    }

    // [CONSTRUCT] Constructor: accepts all fields at creation
    // [UNDERSTAND] Uses setters so validation logic is applied automatically.
    //              If a bad value is passed, the setter prints an error
    //              and leaves the field at its default (0 / null / '\0').
    public Student(String name, String idNumber,
                   double rawGrade, String numericGrade, char letterRank) {
        setName(name);
        setIdNumber(idNumber);
        setRawGrade(rawGrade);
        setNumericGrade(numericGrade);
        setLetterRank(letterRank);
    }

    // [SETTER] Sets letter rank with validation
    // [UNDERSTAND] Valid letter ranks are S, A, B, C, D, E, P, F
    // [DECISION] If invalid, prints error and leaves letterRank unchanged
    public void setLetterRank(char letterRank) {
        // [VALIDATE] Accept only known grade symbols
        String valid = "SABCDEPFsabcdepf";
        if (valid.indexOf(letterRank) == -1) {
            System.out.println("Error: '" + letterRank
                    + "' is not a valid letter rank (S/A/B/C/D/E/P/F).");
            return;
        }
        this.letterRank = Character.toUpperCase(letterRank);
    }

    // [SETTER] Sets numeric grade string with validation
    // [UNDERSTAND] Numeric grade must be a valid non-blank string
    // [DECISION] If null or blank, prints error and leaves numericGrade unchanged
    public void setNumericGrade(String numericGrade) {
        if (numericGrade == null || numericGrade.isBlank()) {
            System.out.println("Error: Numeric grade cannot be null or blank.");
            return;
        }
        this.numericGrade = numericGrade;
    }

    // [SETTER] Sets raw grade with validation
    // [UNDERSTAND] Raw grade must be between 0.0 and 100.0
    // [DECISION] If out of range, prints error and leaves rawGrade unchanged
    public void setRawGrade(double rawGrade) {
        // [VALIDATE] Use static constants — no object needed to read them
        if (rawGrade < GradeConstants.MIN_SCORE || rawGrade > GradeConstants.MAX_SCORE) {
            System.out.println("Error: Raw grade " + rawGrade
                    + " is out of range ["
                    + GradeConstants.MIN_SCORE + ", "
                    + GradeConstants.MAX_SCORE + "].");
            return;
        }
        // [TRACE] Round to 2 decimal places (e.g., 87.666 → 87.67)
        this.rawGrade = Math.round(rawGrade * 100.0) / 100.0;
    }

    // [SETTER] Sets student name with validation
    // [UNDERSTAND] Name cannot be null or blank
    // [DECISION] If invalid, prints error and leaves name unchanged
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            System.out.println("Error: Name cannot be null or blank.");
            return;
        }
        this.name = name;
    }

    // [SETTER] Sets student ID number
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    // [GETTER] Returns student name
    public String getName() {
        return name;
    }

    // [GETTER] Returns raw grade (weighted score)
    public double getRawGrade() {
        return rawGrade;
    }

    // [GETTER] Returns numeric grade string (e.g., "3.5")
    public String getNumericGrade() {
        return numericGrade;
    }

    // [GETTER] Returns letter rank char (e.g., 'A')
    public char getLetterRank() {
        return letterRank;
    }

    // [GETTER] Returns student ID number string
    public String getIdNumber() {
        return idNumber;
    }
}