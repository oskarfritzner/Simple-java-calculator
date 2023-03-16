package Osfr002.ExtraProsjekter.Kalkulator;

import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {

    ArrayList<String> calculationData = new ArrayList<>();

    float sum = 0;

    private float getInput(Scanner scanner) {
        System.out.println("Skriv inn et tall");
        float valgtNr;
        if(scanner.hasNextFloat()) {
            return valgtNr = scanner.nextFloat();
        } else {
            System.out.println("Feil format! Skriv inn et tall.");
            scanner.next();
            return getInput(scanner);
        }
    }

    private String getOperation(Scanner scanner) {
        System.out.println("Skriv inn operatør \n +, -, *, eller /");
        String valgtOperator = scanner.next();
        switch (valgtOperator) {
            case "+", "-", "*", "/":
                return valgtOperator;
            default:
                System.out.println("du tastet feil");
                return getOperation(scanner);
        }
    }

    private float doFirstOperation(float nr1, String valgtOperator, float nr2) {
        calculationData.add(String.valueOf(nr1));
        calculationData.add(valgtOperator);
        calculationData.add(String.valueOf(nr2));

        switch(valgtOperator) {
            case "+":
                return sum = nr1 + nr2;
            case "-":
                return sum = nr1 - nr2;
            case "*":
                return sum = nr1 * nr2;
            case "/":
                return sum = nr1 / nr2;
            default:
                System.out.println("Wrong Operator");
                return doFirstOperation(nr1, valgtOperator, nr2);
        }
    }

    private void doExtraOperation(Scanner scanner) {
        System.out.println(sum);
        calculate(getOperation(scanner), getInput(scanner));
        printCalculation();
        handleMenu(scanner);
    }

    private float calculate(String valgtOperator, float nr1) {
        calculationData.add(valgtOperator);
        calculationData.add(String.valueOf(nr1));

        switch(valgtOperator) {
            case "+":
                return sum += nr1;
            case "-":
                return sum -= nr1;
            case "*":
                return sum *= nr1;
            case "/":
                return sum /= nr1;
            default:
                System.out.println("Wrong Operator");
                return calculate(valgtOperator, nr1);
        }
    }

    private void handleMenu(Scanner scanner) {
        System.out.println("What next?");
        ArrayList<String> menu = new ArrayList<>();

        menu.add("Calculate more");
        menu.add("Restart");
        menu.add("Avslutt");

        for(int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i));
        }

        System.out.println("Write 1, 2 eller 3");

        int chosenMenu = scanner.nextInt();

        switch(chosenMenu) {
            case 1:
                doExtraOperation(scanner);
                break;
            case 2:
                this.runCalc();
                break;
            case 3:
                break;
            default:
                System.out.println("Skriv 1 eller 2");
                handleMenu(scanner);
        }
    }

    private void printCalculation() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < calculationData.size(); i++) {
            sb.append(calculationData.get(i)).append(" ");
        }
        sb.append("= ").append(sum);

        String fullCalculation = sb.toString();
        System.out.println(fullCalculation);
    }

    public void runCalc() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Velkommen til min kalkulator");
        sum = doFirstOperation(getInput(scanner), getOperation(scanner), getInput(scanner));
        printCalculation();
        handleMenu(scanner);
        scanner.close();
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.runCalc();

    }
}
