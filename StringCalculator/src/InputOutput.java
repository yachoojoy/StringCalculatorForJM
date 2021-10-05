/*
Калькулятор умеет выполнять операции сложения строк, вычитания строки из строки, умножения строки на число и деления строки на число: "a" + "b", "a" - "b", "a" * b, "a" / b. Данные передаются в одну строку(смотрите пример)! Решения, в которых каждая строка, число и арифмитеческая операция передаются с новой строки считаются неверными.
Значения строк передаваемых в выражении выделяются двойными кавычками.
Результатом сложения двух строк, является строка состоящая из переданных.
Результатом деления строки на число n, является строка в n раз короче исходной (смотрите пример).
Результатом умножения строки на число n, является строка, в которой переданная строка повторяется ровно n раз.
Результатом вычитания строки из строки, является строка, в которой удалена переданная подстрока или сама исходная строка, если в нее нет вхождения вычитаемой строки (смотрите пример).
Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более. И строки длинной не более 10 символов. Если строка, полученная в результате работы приложения длинее 40 симовлов, то в выводе после 40 символа должны стоять три точки (...)
Калькулятор умеет работать только с целыми числами.
Первым аргументом выражения, подаваемого на вход, должна быть строка, при вводе пользователем выражения вроде 3 + "hello", калькулятор должен выбросить исключение и прекратить свою работу.
При вводе пользователем неподходящих чисел, строк или неподдерживаемых операций (например, деление строки на строку) приложение выбрасывает исключение и завершает свою работу.
При вводе пользователем выражения, не соответствующего одной из вышеописанных арифметических операций, приложение выбрасывает исключение и завершает свою работу.
*/

import java.util.Arrays;
import java.util.Scanner;

//получение строки и возврат результата
public class InputOutput {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String date = input.nextLine();

        ExtractingOperands.dismember(date);
        ExtractingOperands.oper(date);

        Calculation.answer(ExtractingOperands.operand1, ExtractingOperands.operand2, ExtractingOperands.operator, ExtractingOperands.strForOperand2);
    }

}

    //получение операндов и оператора
    class ExtractingOperands {
        public static String operand1;
        public static String operand2;
        public static char operator;
        public static boolean strForOperand2;

        public static void dismember(String input) {
            try {
            String[] operands = input.split("[+-/*]");

            if (operands.length != 2){
                System.out.println("Выполнение выражения невозможно. \n(Формат ввода: Строка оператор Строка/число)");
                System.exit(1);
            }
            operand1 = getOperand1(operands[0].trim());
            operand2 = getOperand2(operands[1].trim());
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Выполнение выражения невозможно. \n(Используйте + - / *)");
                System.exit(1);
            }
        }

        public static String getOperand1(String input){
            int lenght = input.length();

            if ((input.charAt(0) == '\"') && (input.charAt(input.length()-1) == '\"')) { //определение кавычек и их изьятие
                String operand = input.replaceAll("[\"]", "");
                if (operand.length()>10) {
                    System.out.println("Выполнение выражения невозможно. \n(Строка должна быть не длиннее 10-и символов)");
                    System.exit(1);
                }
                return operand;
            } else {
                System.out.println("Выполнение выражения невозможно. \n(Первым значением обязательно должна быть строка) \n(Строка должна быть написана в кавычках)");
                System.exit(1);
                return "";
            }
        }

        public static String getOperand2(String input) {
            int lenght = input.length();

            String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

            if ((input.charAt(0) == '\"') && (input.charAt(input.length() - 1) == '\"')) { //определение кавычек и их изьятие
                String operand = input.replaceAll("[\"]", "");
                if (operand.length()>10) {
                    System.out.println("Выполнение выражения невозможно. \n(Строка должна быть не длиннее 10-и символов)");
                    System.exit(1);
                }
                strForOperand2 = true;
                return operand;
            }
            if (!Arrays.asList(numbers).contains(input)){
                System.out.println("Выполнение выражения невозможно. \n(Вторым значением дожна быть строка или число 1-10)");
                System.exit(1);
            } else {
                strForOperand2 = false;
            }
                return input;
        }

        public static void oper(String input) {
            if (input.indexOf(" + ") > 0) {
                operator = '+';
            } else if (input.indexOf(" - ") > 0) {
                operator = '-';
            } else if (input.indexOf(" * ") > 0) {
                operator = '*';
            } else if (input.indexOf(" / ") > 0) {
                operator = '/';
            } else {
                System.out.println("Выполнение выражения невозможно. \n(Оператор должен быть выделен пробелами)");
                System.exit(1);
            }
        }
    }

    class Calculation {

    public static void answer (String operand1, String operand2, char operator, boolean strForOperand2){
        if (operator == '+'){
            System.out.println(Calculation.addition(operand1, operand2, strForOperand2));
        }
        if (operator == '-'){
            System.out.println(Calculation.subtraction(operand1, operand2, strForOperand2));
        }
        if (operator == '*'){
            System.out.println(Calculation.multiplication(operand1, operand2, strForOperand2));
        }
        if (operator == '/'){
            System.out.println(Calculation.division(operand1, operand2, strForOperand2));
        }
    }

    public static String addition(String operand1, String operand2, boolean strForOperand2){
        String result;
        if (strForOperand2 == true){
            result = operand1 + operand2;
            return "\"" + result + "\"";
        }
        return "Выполнение выражения невозможно. \n(Складывать можно только две строки)";
    }

    public static String subtraction(String operand1, String operand2, boolean strForOperand2){
        String result;
        if (strForOperand2 == true){
            result = operand1.replace (operand2, "");
            return "\"" + result + "\"";
        }
        return "Выполнение выражения невозможно. \n(Вычитать можно только строку из строки)";
    }

        public static String multiplication(String operand1, String operand2, boolean strForOperand2){
            String result = "";

            if (strForOperand2 == false){
                int num2 = Integer.parseInt (operand2);
                for (int i = 0; i < num2; i++) {
                    result = result + operand1;
                }
                if (result.length()>40){
                    result = result.substring(0, 40) + "...";
                }
                return "\"" + result + "\"";
            }
            return "Выполнение выражения невозможно. \n(Умножить можно только строку на число)";
        }

        public static String division(String operand1, String operand2, boolean strForOperand2){
            String result = "";
            if (strForOperand2 == false){
                int num2 = Integer.parseInt (operand2);
                int quantity = operand1.length() / num2;
                for (int i = 0; i < quantity; i++) {
                    result = result + operand1.charAt(i);
                }
                return "\"" + result + "\"";
            }
            return "Выполнение выражения невозможно. \n(Делить можно только строку на число)";
        }
}