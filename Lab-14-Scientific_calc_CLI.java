import java.util.Arrays;
import java.util.Scanner;

public class MasterScientificCalculator {

    private static final Scanner scanner = new Scanner(System.in);
    private static double memoryRegister = 0.0;

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printHeader("IMPERIAL SCIENTIFIC COMMAND SYSTEM - MASTER MENU");
            System.out.println("Select a Functional Module (1-12):");
            System.out.println("  1.  [Page 1]  Basic Arithmetic & Powers");
            System.out.println("  2.  [Page 2]  Trigonometry & Inverse Trigonometry");
            System.out.println("  3.  [Page 3]  Logarithms & Exponential Functions");
            System.out.println("  4.  [Page 4]  Statistics & Data Analysis");
            System.out.println("  5.  [Page 5]  Number Bases & Bitwise Logic");
            System.out.println("  6.  [Page 6]  Combinatorics & Factorials");
            System.out.println("  7.  [Page 7]  Memory Register & Physics Constants");
            System.out.println("  8.  [Page 8]  Financial & Business Math");
            System.out.println("  9.  [Page 9]  Matrix Algebra (2x2 & 3x3)");
            System.out.println("  10. [Page 10] Complex Number Arithmetic");
            System.out.println("  11. [Page 11] Unit & Metric Conversions");
            System.out.println("  12. [Page 12] Numerical Calculus & Integration");
            System.out.println("  13. Exit Imperial System");
            System.out.print("\nEnter page choice (1-13): ");

            int choice = readInt();

            switch (choice) {
                case 1  -> page1BasicPowers();
                case 2  -> page2Trigonometry();
                case 3  -> page3LogExponents();
                case 4  -> page4Statistics();
                case 5  -> page5BasesAndBitwise();
                case 6  -> page6Combinatorics();
                case 7  -> page7MemoryAndConstants();
                case 8  -> page8Financial();
                case 9  -> page9MatrixOperations();
                case 10 -> page10ComplexNumbers();
                case 11 -> page11UnitConversions();
                case 12 -> page12NumericalCalculus();
                case 13 -> {
                    System.out.println("\nOperations concluded. Terminating Imperial System...");
                    running = false;
                }
                default -> System.out.println("\n[Error] Choice out of range. Select 1 through 13.");
            }
        }
        scanner.close();
    }

    // =========================================================================
    // PAGE 1: Basic Arithmetic & Powers
    // =========================================================================
    private static void page1BasicPowers() {
        printHeader("PAGE 1: BASIC ARITHMETIC & POWERS");
        System.out.println("1. Addition (+)");
        System.out.println("2. Subtraction (-)");
        System.out.println("3. Multiplication (*)");
        System.out.println("4. Division (/)");
        System.out.println("5. Power (x^y)");
        System.out.println("6. Square Root (√x)");
        System.out.println("7. Cube Root (∛x)");
        System.out.print("Select operation (1-7): ");

        int op = readInt();
        switch (op) {
            case 1 -> {
                double a = promptDouble("Enter first number: ");
                double b = promptDouble("Enter second number: ");
                System.out.printf("Result: %.4f + %.4f = %.4f%n", a, b, a + b);
            }
            case 2 -> {
                double a = promptDouble("Enter first number: ");
                double b = promptDouble("Enter second number: ");
                System.out.printf("Result: %.4f - %.4f = %.4f%n", a, b, a - b);
            }
            case 3 -> {
                double a = promptDouble("Enter first number: ");
                double b = promptDouble("Enter second number: ");
                System.out.printf("Result: %.4f * %.4f = %.4f%n", a, b, a * b);
            }
            case 4 -> {
                double a = promptDouble("Enter dividend: ");
                double b = promptDouble("Enter divisor: ");
                if (b == 0) System.out.println("[Error] Cannot divide by zero.");
                else System.out.printf("Result: %.4f / %.4f = %.4f%n", a, b, a / b);
            }
            case 5 -> {
                double base = promptDouble("Enter base (x): ");
                double exp = promptDouble("Enter exponent (y): ");
                System.out.printf("Result: %.4f ^ %.4f = %.4f%n", base, exp, Math.pow(base, exp));
            }
            case 6 -> {
                double x = promptDouble("Enter number: ");
                if (x < 0) System.out.println("[Error] Cannot compute real square root of negative number.");
                else System.out.printf("Result: √%.4f = %.4f%n", x, Math.sqrt(x));
            }
            case 7 -> {
                double x = promptDouble("Enter number: ");
                System.out.printf("Result: ∛%.4f = %.4f%n", x, Math.cbrt(x));
            }
            default -> System.out.println("[Error] Invalid option.");
        }
    }

    // =========================================================================
    // PAGE 2: Trigonometry & Inverse Trigonometry
    // =========================================================================
    private static void page2Trigonometry() {
        printHeader("PAGE 2: TRIGONOMETRY & INVERSE TRIGONOMETRY");
        System.out.println("1. Sine (sin)");
        System.out.println("2. Cosine (cos)");
        System.out.println("3. Tangent (tan)");
        System.out.println("4. Inverse Sine (asin)");
        System.out.println("5. Inverse Cosine (acos)");
        System.out.println("6. Inverse Tangent (atan)");
        System.out.print("Select operation (1-6): ");

        int op = readInt();
        System.out.print("Use Degrees or Radians? (1 = Degrees, 2 = Radians): ");
        boolean isDegrees = (readInt() == 1);

        switch (op) {
            case 1 -> {
                double angle = promptDouble("Enter angle: ");
                double rad = isDegrees ? Math.toRadians(angle) : angle;
                System.out.printf("Result: sin(%.4f) = %.6f%n", angle, Math.sin(rad));
            }
            case 2 -> {
                double angle = promptDouble("Enter angle: ");
                double rad = isDegrees ? Math.toRadians(angle) : angle;
                System.out.printf("Result: cos(%.4f) = %.6f%n", angle, Math.cos(rad));
            }
            case 3 -> {
                double angle = promptDouble("Enter angle: ");
                double rad = isDegrees ? Math.toRadians(angle) : angle;
                System.out.printf("Result: tan(%.4f) = %.6f%n", angle, Math.tan(rad));
            }
            case 4 -> {
                double val = promptDouble("Enter value [-1 to 1]: ");
                if (val < -1 || val > 1) System.out.println("[Error] Domain [-1, 1] exceeded.");
                else {
                    double res = Math.asin(val);
                    if (isDegrees) res = Math.toDegrees(res);
                    System.out.printf("Result: asin(%.4f) = %.6f %s%n", val, res, isDegrees ? "°" : "rad");
                }
            }
            case 5 -> {
                double val = promptDouble("Enter value [-1 to 1]: ");
                if (val < -1 || val > 1) System.out.println("[Error] Domain [-1, 1] exceeded.");
                else {
                    double res = Math.acos(val);
                    if (isDegrees) res = Math.toDegrees(res);
                    System.out.printf("Result: acos(%.4f) = %.6f %s%n", val, res, isDegrees ? "°" : "rad");
                }
            }
            case 6 -> {
                double val = promptDouble("Enter value: ");
                double res = Math.atan(val);
                if (isDegrees) res = Math.toDegrees(res);
                System.out.printf("Result: atan(%.4f) = %.6f %s%n", val, res, isDegrees ? "°" : "rad");
            }
            default -> System.out.println("[Error] Invalid option.");
        }
    }

    // =========================================================================
    // PAGE 3: Logarithms & Exponential Functions
    // =========================================================================
    private static void page3LogExponents() {
        printHeader("PAGE 3: LOGARITHMS & EXPONENTIAL FUNCTIONS");
        System.out.println("1. Natural Logarithm (ln x)");
        System.out.println("2. Base-10 Logarithm (log10 x)");
        System.out.println("3. Custom Base Logarithm (log_b x)");
        System.out.println("4. Natural Exponential (e^x)");
        System.out.println("5. Base-10 Exponential (10^x)");
        System.out.print("Select operation (1-5): ");

        int op = readInt();
        switch (op) {
            case 1 -> {
                double x = promptDouble("Enter x (x > 0): ");
                if (x <= 0) System.out.println("[Error] Logarithm undefined for x <= 0.");
                else System.out.printf("Result: ln(%.4f) = %.6f%n", x, Math.log(x));
            }
            case 2 -> {
                double x = promptDouble("Enter x (x > 0): ");
                if (x <= 0) System.out.println("[Error] Logarithm undefined for x <= 0.");
                else System.out.printf("Result: log10(%.4f) = %.6f%n", x, Math.log10(x));
            }
            case 3 -> {
                double x = promptDouble("Enter value (x > 0): ");
                double base = promptDouble("Enter base (b > 0, b != 1): ");
                if (x <= 0 || base <= 0 || base == 1) System.out.println("[Error] Invalid domain parameters.");
                else System.out.printf("Result: log_%.2f(%.4f) = %.6f%n", base, x, Math.log(x) / Math.log(base));
            }
            case 4 -> {
                double x = promptDouble("Enter exponent x: ");
                System.out.printf("Result: e^(%.4f) = %.6f%n", x, Math.exp(x));
            }
            case 5 -> {
                double x = promptDouble("Enter exponent x: ");
                System.out.printf("Result: 10^(%.4f) = %.6f%n", x, Math.pow(10, x));
            }
            default -> System.out.println("[Error] Invalid option.");
        }
    }

    // =========================================================================
    // PAGE 4: Statistics & Data Analysis
    // =========================================================================
    private static void page4Statistics() {
        printHeader("PAGE 4: STATISTICS & DATA ANALYSIS");
        System.out.print("How many data points will you enter? ");
        int n = readInt();
        if (n <= 0) {
            System.out.println("[Error] Sample size must be greater than zero.");
            return;
        }

        double[] data = new double[n];
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            data[i] = promptDouble("Enter element " + (i + 1) + ": ");
            sum += data[i];
        }

        double mean = sum / n;
        Arrays.sort(data);

        double median = (n % 2 != 0) ? data[n / 2] : (data[(n / 2) - 1] + data[n / 2]) / 2.0;

        double sumSqDiff = 0.0;
        for (double val : data) {
            sumSqDiff += Math.pow(val - mean, 2);
        }
        double variance = sumSqDiff / n;
        double stdDev = Math.sqrt(variance);

        System.out.println("\n--- Statistical Report ---");
        System.out.printf("Sample Size (N):    %d%n", n);
        System.out.printf("Min Value:          %.4f%n", data[0]);
        System.out.printf("Max Value:          %.4f%n", data[n - 1]);
        System.out.printf("Sum:                %.4f%n", sum);
        System.out.printf("Arithmetic Mean:    %.4f%n", mean);
        System.out.printf("Median:             %.4f%n", median);
        System.out.printf("Population Variance:%.4f%n", variance);
        System.out.printf("Population Std Dev: %.4f%n", stdDev);
    }

    // =========================================================================
    // PAGE 5: Number Bases & Bitwise Logic
    // =========================================================================
    private static void page5BasesAndBitwise() {
        printHeader("PAGE 5: NUMBER BASES & BITWISE LOGIC");
        System.out.println("1. Convert Decimal to Binary / Hex / Octal");
        System.out.println("2. Bitwise AND (&)");
        System.out.println("3. Bitwise OR (|)");
        System.out.println("4. Bitwise XOR (^)");
        System.out.println("5. Bitwise NOT (~)");
        System.out.println("6. Left Shift (<<)");
        System.out.println("7. Right Shift (>>)");
        System.out.print("Select operation (1-7): ");

        int op = readInt();
        switch (op) {
            case 1 -> {
                System.out.print("Enter integer value: ");
                long val = readLong();
                System.out.println("Decimal:     " + val);
                System.out.println("Binary:      0b" + Long.toBinaryString(val));
                System.out.println("Hexadecimal: 0x" + Long.toHexString(val).toUpperCase());
                System.out.println("Octal:       0o" + Long.toOctalString(val));
            }
            case 2 -> {
                System.out.print("Enter Operand A: ");
                long a = readLong();
                System.out.print("Enter Operand B: ");
                long b = readLong();
                System.out.printf("Result: %d & %d = %d (0b%s)%n", a, b, (a & b), Long.toBinaryString(a & b));
            }
            case 3 -> {
                System.out.print("Enter Operand A: ");
                long a = readLong();
                System.out.print("Enter Operand B: ");
                long b = readLong();
                System.out.printf("Result: %d | %d = %d (0b%s)%n", a, b, (a | b), Long.toBinaryString(a | b));
            }
            case 4 -> {
                System.out.print("Enter Operand A: ");
                long a = readLong();
                System.out.print("Enter Operand B: ");
                long b = readLong();
                System.out.printf("Result: %d ^ %d = %d (0b%s)%n", a, b, (a ^ b), Long.toBinaryString(a ^ b));
            }
            case 5 -> {
                System.out.print("Enter Operand: ");
                long a = readLong();
                System.out.printf("Result: ~%d = %d (0b%s)%n", a, ~a, Long.toBinaryString(~a));
            }
            case 6 -> {
                System.out.print("Enter Value: ");
                long val = readLong();
                System.out.print("Enter Shift Amount: ");
                int shift = readInt();
                System.out.printf("Result: %d << %d = %d%n", val, shift, (val << shift));
            }
            case 7 -> {
                System.out.print("Enter Value: ");
                long val = readLong();
                System.out.print("Enter Shift Amount: ");
                int shift = readInt();
                System.out.printf("Result: %d >> %d = %d%n", val, shift, (val >> shift));
            }
            default -> System.out.println("[Error] Invalid option.");
        }
    }

    // =========================================================================
    // PAGE 6: Combinatorics & Factorials
    // =========================================================================
    private static void page6Combinatorics() {
        printHeader("PAGE 6: COMBINATORICS & FACTORIALS");
        System.out.println("1. Factorial (n!)");
        System.out.println("2. Permutations (nPr)");
        System.out.println("3. Combinations (nCr)");
        System.out.print("Select operation (1-3): ");

        int op = readInt();
        switch (op) {
            case 1 -> {
                System.out.print("Enter integer n (0 to 20): ");
                int n = readInt();
                if (n < 0 || n > 20) System.out.println("[Error] Factorial out of long precision limit [0-20].");
                else System.out.printf("Result: %d! = %d%n", n, computeFactorial(n));
            }
            case 2 -> {
                System.out.print("Enter n: ");
                int n = readInt();
                System.out.print("Enter r: ");
                int r = readInt();
                if (r < 0 || r > n || n > 20) System.out.println("[Error] Invalid constraints (0 <= r <= n <= 20).");
                else {
                    long nPr = computeFactorial(n) / computeFactorial(n - r);
                    System.out.printf("Result: %dP%d = %d%n", n, r, nPr);
                }
            }
            case 3 -> {
                System.out.print("Enter n: ");
                int n = readInt();
                System.out.print("Enter r: ");
                int r = readInt();
                if (r < 0 || r > n || n > 20) System.out.println("[Error] Invalid constraints (0 <= r <= n <= 20).");
                else {
                    long nCr = computeFactorial(n) / (computeFactorial(r) * computeFactorial(n - r));
                    System.out.printf("Result: %dC%d = %d%n", n, r, nCr);
                }
            }
            default -> System.out.println("[Error] Invalid option.");
        }
    }

    private static long computeFactorial(int n) {
        long fact = 1;
        for (int i = 2; i <= n; i++) fact *= i;
        return fact;
    }

    // =========================================================================
    // PAGE 7: Memory Register & Physical Constants
    // =========================================================================
    private static void page7MemoryAndConstants() {
        printHeader("PAGE 7: MEMORY REGISTER & PHYSICAL CONSTANTS");
        System.out.printf("Current Memory Register (M): %.6f%n%n", memoryRegister);
        System.out.println("1. Store in Memory (MS)");
        System.out.println("2. Add to Memory (M+)");
        System.out.println("3. Subtract from Memory (M-)");
        System.out.println("4. Clear Memory (MC)");
        System.out.println("5. Recall Constants (π, e, Speed of Light c, Gravitational Constant G)");
        System.out.print("Select operation (1-5): ");

        int op = readInt();
        switch (op) {
            case 1 -> {
                memoryRegister = promptDouble("Enter value to store: ");
                System.out.printf("Memory updated: M = %.6f%n", memoryRegister);
            }
            case 2 -> {
                double val = promptDouble("Enter value to add: ");
                memoryRegister += val;
                System.out.printf("Memory updated: M = %.6f%n", memoryRegister);
            }
            case 3 -> {
                double val = promptDouble("Enter value to subtract: ");
                memoryRegister -= val;
                System.out.printf("Memory updated: M = %.6f%n", memoryRegister);
            }
            case 4 -> {
                memoryRegister = 0.0;
                System.out.println("Memory cleared: M = 0.0");
            }
            case 5 -> {
                System.out.println("\n--- Fundamental Constants ---");
                System.out.printf("Pi (π):               %.10f%n", Math.PI);
                System.out.printf("Euler's Number (e):    %.10f%n", Math.E);
                System.out.printf("Speed of Light (c):   2.99792458e8 m/s%n");
                System.out.printf("Gravitation (G):      6.67430e-11 m^3/(kg*s^2)%n");
                System.out.printf("Planck Constant (h):  6.62607015e-34 J*s%n");
            }
            default -> System.out.println("[Error] Invalid option.");
        }
    }

    // =========================================================================
    // PAGE 8: Financial & Business Math
    // =========================================================================
    private static void page8Financial() {
        printHeader("PAGE 8: FINANCIAL & BUSINESS MATH");
        System.out.println("1. Simple Interest");
        System.out.println("2. Compound Interest");
        System.out.println("3. Percentage Increase / Decrease");
        System.out.print("Select operation (1-3): ");

        int op = readInt();
        switch (op) {
            case 1 -> {
                double p = promptDouble("Enter Principal amount: ");
                double r = promptDouble("Enter Annual Rate (%): ");
                double t = promptDouble("Enter Time (years): ");
                double interest = (p * r * t) / 100.0;
                System.out.printf("Simple Interest Earned: %.2f%n", interest);
                System.out.printf("Total Future Value:     %.2f%n", p + interest);
            }
            case 2 -> {
                double p = promptDouble("Enter Principal amount: ");
                double r = promptDouble("Enter Annual Rate (%): ");
                double t = promptDouble("Enter Time (years): ");
                int n = (int) promptDouble("Enter Compounding Frequency per year (e.g. 12 for monthly): ");
                double amount = p * Math.pow(1 + (r / (100.0 * n)), n * t);
                System.out.printf("Compound Interest Value: %.2f%n", amount);
                System.out.printf("Interest Earned:         %.2f%n", amount - p);
            }
            case 3 -> {
                double orig = promptDouble("Enter Original value: ");
                double valNew = promptDouble("Enter New value: ");
                double change = ((valNew - orig) / orig) * 100.0;
                System.out.printf("Percentage Change: %.2f%%%n", change);
            }
            default -> System.out.println("[Error] Invalid option.");
        }
    }

    // =========================================================================
    // PAGE 9: Matrix Algebra (2x2 & 3x3)
    // =========================================================================
    private static void page9MatrixOperations() {
        printHeader("PAGE 9: MATRIX ALGEBRA");
        System.out.println("1. 2x2 Matrix Determinant & Inverse");
        System.out.println("2. 3x3 Matrix Determinant");
        System.out.println("3. 2x2 Matrix Addition");
        System.out.print("Select operation (1-3): ");

        int op = readInt();
        switch (op) {
            case 1 -> {
                System.out.println("Enter elements for 2x2 Matrix [[a, b], [c, d]]:");
                double a = promptDouble("a: "); double b = promptDouble("b: ");
                double c = promptDouble("c: "); double d = promptDouble("d: ");
                double det = (a * d) - (b * c);
                System.out.printf("Determinant: %.4f%n", det);
                if (det == 0) {
                    System.out.println("Matrix is singular (Inverse does not exist).");
                } else {
                    System.out.println("Inverse Matrix:");
                    System.out.printf("[ %8.4f  %8.4f ]%n",  d / det, -b / det);
                    System.out.printf("[ %8.4f  %8.4f ]%n", -c / det,  a / det);
                }
            }
            case 2 -> {
                System.out.println("Enter elements for 3x3 Matrix:");
                double[][] m = new double[3][3];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        m[i][j] = promptDouble(String.format("m[%d][%d]: ", i + 1, j + 1));
                    }
                }
                double det = m[0][0] * (m[1][1] * m[2][2] - m[1][2] * m[2][1])
                           - m[0][1] * (m[1][0] * m[2][2] - m[1][2] * m[2][0])
                           + m[0][2] * (m[1][0] * m[2][1] - m[1][1] * m[2][0]);
                System.out.printf("3x3 Determinant: %.4f%n", det);
            }
            case 3 -> {
                System.out.println("Matrix A:");
                double a1 = promptDouble("a11: "); double a2 = promptDouble("a12: ");
                double a3 = promptDouble("a21: "); double a4 = promptDouble("a22: ");
                System.out.println("Matrix B:");
                double b1 = promptDouble("b11: "); double b2 = promptDouble("b12: ");
                double b3 = promptDouble("b21: "); double b4 = promptDouble("b22: ");

                System.out.println("Sum Matrix A + B:");
                System.out.printf("[ %8.4f  %8.4f ]%n", a1 + b1, a2 + b2);
                System.out.printf("[ %8.4f  %8.4f ]%n", a3 + b3, a4 + b4);
            }
            default -> System.out.println("[Error] Invalid option.");
        }
    }

    // =========================================================================
    // PAGE 10: Complex Number Arithmetic
    // =========================================================================
    private static void page10ComplexNumbers() {
        printHeader("PAGE 10: COMPLEX NUMBER ARITHMETIC");
        System.out.println("1. Complex Addition & Subtraction");
        System.out.println("2. Complex Multiplication");
        System.out.println("3. Magnitude & Phase Angle (Polar Form)");
        System.out.print("Select operation (1-3): ");

        int op = readInt();
        switch (op) {
            case 1 -> {
                double r1 = promptDouble("Real part 1: "); double i1 = promptDouble("Imaginary part 1: ");
                double r2 = promptDouble("Real part 2: "); double i2 = promptDouble("Imaginary part 2: ");
                System.out.printf("Sum:        (%.4f) + (%.4fi)%n", (r1 + r2), (i1 + i2));
                System.out.printf("Difference: (%.4f) + (%.4fi)%n", (r1 - r2), (i1 - i2));
            }
            case 2 -> {
                double r1 = promptDouble("Real part 1: "); double i1 = promptDouble("Imaginary part 1: ");
                double r2 = promptDouble("Real part 2: "); double i2 = promptDouble("Imaginary part 2: ");
                double realProd = (r1 * r2) - (i1 * i2);
                double imagProd = (r1 * i2) + (r2 * i1);
                System.out.printf("Product: (%.4f) + (%.4fi)%n", realProd, imagProd);
            }
            case 3 -> {
                double r = promptDouble("Real part: ");
                double i = promptDouble("Imaginary part: ");
                double magnitude = Math.hypot(r, i);
                double phaseRad = Math.atan2(i, r);
                double phaseDeg = Math.toDegrees(phaseRad);
                System.out.printf("Magnitude (|Z|): %.6f%n", magnitude);
                System.out.printf("Phase Angle:     %.4f rad (%.2f°)%n", phaseRad, phaseDeg);
            }
            default -> System.out.println("[Error] Invalid option.");
        }
    }

    // =========================================================================
    // PAGE 11: Unit & Metric Conversions
    // =========================================================================
    private static void page11UnitConversions() {
        printHeader("PAGE 11: UNIT & METRIC CONVERSIONS");
        System.out.println("1. Temperature (Celsius / Fahrenheit / Kelvin)");
        System.out.println("2. Distance (Meters / Kilometers / Miles / Feet)");
        System.out.println("3. Mass (Kilograms / Pounds / Ounces)");
        System.out.print("Select category (1-3): ");

        int cat = readInt();
        switch (cat) {
            case 1 -> {
                double c = promptDouble("Enter temperature in Celsius: ");
                double f = (c * 9.0 / 5.0) + 32.0;
                double k = c + 273.15;
                System.out.printf("Fahrenheit: %.2f °F%n", f);
                System.out.printf("Kelvin:     %.2f K%n", k);
            }
            case 2 -> {
                double meters = promptDouble("Enter distance in Meters: ");
                System.out.printf("Kilometers: %.4f km%n", meters / 1000.0);
                System.out.printf("Miles:      %.4f mi%n", meters / 1609.344);
                System.out.printf("Feet:       %.2f ft%n", meters * 3.28084);
            }
            case 3 -> {
                double kg = promptDouble("Enter mass in Kilograms: ");
                System.out.printf("Pounds: %.4f lbs%n", kg * 2.20462);
                System.out.printf("Ounces: %.2f oz%n", kg * 35.274);
            }
            default -> System.out.println("[Error] Invalid option.");
        }
    }

    // =========================================================================
    // PAGE 12: Numerical Calculus & Integration
    // =========================================================================
    private static void page12NumericalCalculus() {
        printHeader("PAGE 12: NUMERICAL CALCULUS");
        System.out.println("1. Numerical Derivative d/dx of f(x) = x^2");
        System.out.println("2. Numerical Derivative d/dx of f(x) = sin(x)");
        System.out.println("3. Definite Integral (Simpson's 1/3 Rule) of f(x) = x^2");
        System.out.print("Select operation (1-3): ");

        int op = readInt();
        double h = 1e-6; // Small delta step

        switch (op) {
            case 1 -> {
                double x = promptDouble("Evaluate derivative at x: ");
                double df = ((x + h) * (x + h) - (x - h) * (x - h)) / (2 * h);
                System.out.printf("d/dx(x^2) at x=%.4f ≈ %.6f (Exact: %.4f)%n", x, df, 2 * x);
            }
            case 2 -> {
                double x = promptDouble("Evaluate derivative at x (radians): ");
                double df = (Math.sin(x + h) - Math.sin(x - h)) / (2 * h);
                System.out.printf("d/dx(sin x) at x=%.4f ≈ %.6f (Exact cos(x): %.4f)%n", x, df, Math.cos(x));
            }
            case 3 -> {
                double a = promptDouble("Lower bound (a): ");
                double b = promptDouble("Upper bound (b): ");
                int n = 1000; // Even number of intervals
                double step = (b - a) / n;
                double sum = (a * a) + (b * b);

                for (int i = 1; i < n; i++) {
                    double xi = a + i * step;
                    sum += (i % 2 == 0 ? 2 : 4) * (xi * xi);
                }
                double integral = (step / 3.0) * sum;
                System.out.printf("∫[a=%.2f to b=%.2f] x^2 dx ≈ %.6f%n", a, b, integral);
            }
            default -> System.out.println("[Error] Invalid option.");
        }
    }

    // =========================================================================
    // HELPER METHODS FOR INPUT & DISPLAY
    // =========================================================================
    private static void printHeader(String title) {
        System.out.println("\n==================================================");
        System.out.println(" " + title);
        System.out.println("==================================================");
    }

    private static double promptDouble(String message) {
        System.out.print(message);
        while (!scanner.hasNextDouble()) {
            System.out.println("[Error] Invalid double-precision float.");
            System.out.print(message);
            scanner.next();
        }
        return scanner.nextDouble();
    }

    private static int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("[Error] Invalid integer choice.");
            System.out.print("Re-enter choice: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static long readLong() {
        while (!scanner.hasNextLong()) {
            System.out.println("[Error] Invalid integer format.");
            System.out.print("Re-enter integer: ");
            scanner.next();
        }
        return scanner.nextLong();
    }
}
