package htw.berlin.prog2.ha1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Retro calculator")
class CalculatorTest {

    @Test
    @DisplayName("should display result after adding two positive multi-digit numbers")
    void testPositiveAddition() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "40";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display result after getting the square root of two")
    void testSquareRoot() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressUnaryOperationKey("√");

        String expected = "1.41421356";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when dividing by zero")
    void testDivisionByZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when drawing the square root of a negative number")
    void testSquareRootOfNegative() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressNegativeKey();
        calc.pressUnaryOperationKey("√");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should not allow multiple decimal dots")
    void testMultipleDecimalDots() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDotKey();
        calc.pressDigitKey(7);
        calc.pressDotKey();
        calc.pressDigitKey(8);

        String expected = "1.78";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }


    // Teilaufgabe 1. (wird direkt Grün)
    @Test
    @DisplayName("should clear the current screen when pressing the clear key")
    public void testPressClearKey() {
        Calculator calc = new Calculator();

        calc.screen = "77";
        calc.latestOperation = "+";
        calc.latestValue = 7;

        calc.pressClearKey();

        assertEquals("0", calc.screen);
        assertEquals("", calc.latestOperation);
        assertEquals(0.0, calc.latestValue, 0.001);


    }
    // Hier ist das Problem, dass zumindest bei der Repetition von dem Equals ein wenig Code fehlt.
    @Test
    @DisplayName("decides what the = operator does in every scenario")
    public void testpressEqualsKeyPlus() {
        Calculator calc = new Calculator();
            calc.latestValue = 5.0;
            calc.screen = "3";
            calc.latestOperation = "+";

            calc.pressEqualsKey();

            assertEquals("8", calc.screen);
        }
    @Test
    public void testpressEqualsKeyMinus() {
        Calculator calc = new Calculator();
        calc.latestValue = 10.0;
        calc.screen = "4";
        calc.latestOperation = "-";

        calc.pressEqualsKey();

        assertEquals("6", calc.screen);
    }
    // ich weiß wirklich nicht, wieso der Test hier failed...
    @Test
    public void testpressEqualsKeyMultiply() {
        Calculator calc = new Calculator();
        calc.latestValue = 10.0;
        calc.screen = "5";
        calc.latestOperation = "x";

        calc.pressEqualsKey();

        assertEquals("50.0", calc.screen);
    }
    @Test
    public void testpressEqualsKeyDivide() {
        Calculator calc = new Calculator();
        calc.latestValue = 10.0;
        calc.screen = "10";
        calc.latestOperation = "/";

        calc.pressEqualsKey();

        assertEquals(1, calc.screen.length());
    }
    @Test
    public void testpressEqualsKeyByZero() {
        Calculator calc = new Calculator();
        calc.latestValue = 10.0;
        calc.screen = "0";
        calc.latestOperation = "/";

        calc.pressEqualsKey();

        assertEquals("Error", calc.screen);
    }
    // führt zu einer Illegal Argument Exception weshalb es sowieso failed
    @Test
    public void testpressEqualsKeyNoOperator() {
        Calculator calc = new Calculator();
        calc.latestValue = 10.0;
        calc.screen = "0";
        calc.latestOperation = "";

        calc.pressEqualsKey();
    }
    // failed da kein Code geschrieben wurde, welcher dazu führt, dass beim mehrfachen Drücken der Taste
    // ein neues Ergebnis erscheint und es sich deshalb nur wiederholt
    @Test
    public void testpressEqualsKeyRepeated() {
        Calculator calc = new Calculator();
        calc.latestValue = 10.0;
        calc.screen = "2";
        calc.latestOperation = "+";

        calc.pressEqualsKey();

        assertEquals(12.0, calc.screen);

        calc.pressEqualsKey();

        assertEquals(14.0, calc.screen);
    }
    @Test
    public void testpressBinaryOperation() {
        Calculator calc = new Calculator();
        calc.screen = "7";
        calc.pressBinaryOperationKey("+");

        assertEquals("7", calc.screen);
        assertEquals(7.0, calc.latestValue, 0.0001);
        assertEquals("+", calc.latestOperation);
    }

    // kann nicht funktionieren da calc.pressEqualsKey(); nicht im Code steht a.k.a es wird nichts gerechnet.
    @Test
    public void testpressBinaryOperationKeyZwischenergebnis() {
        Calculator calc = new Calculator();

        calc.screen = "3";
        calc.pressBinaryOperationKey("+");
        calc.screen = "4";

        assertEquals("7", calc.screen);
    }
    // kann auch nicht gehen weil nichts gerechnet wird und generell nichts für Division durch 0 im Code steht.
    @Test
    public void testpressBinaryOperationKeyDurchNull() {
        Calculator calc = new Calculator();
        calc.screen = "5";
        calc.pressBinaryOperationKey("/");
        calc.screen = "0";

        assertEquals("Error", calc.screen);
    }
}




