import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContaTerminal {
    public static void main(String[] args) {
        Scanner _in = new Scanner(System.in);

        Locale localeBR = new Locale("pt", "BR");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeBR);

        String agencia;
        int numero;
        String nomeCliente;
        double saldo;

        System.out.println("Por favor, digite o número da agência!");
        agencia = entradaAgencia(_in.nextLine());
        System.out.println("Por favor, digite o número da conta!");
        numero = entradaNumero(_in.nextLine());
        System.out.println("Por favor, digite seu nome!");
        nomeCliente = entradaNomeCliente(_in.nextLine());
        System.out.println("Por favor, digite o saldo!");
        saldo = entradaSaldo(_in.nextLine());

        System.out.print(STR."""
        Olá \{nomeCliente}, obrigado por criar uma conta em nosso banco. Seu número de agência é \{agencia}, conta \{numero} e seu saldo de \{numberFormat.format(saldo)} já está disponível para saque.
        """);
    }

    public static String entradaAgencia(String value) {
        String ret = "";
        if (!value.replaceAll("\\D", "").isEmpty()) {
            ret = value.replaceAll("\\D", "");
        } else {
            System.out.println("É necessário digitar um número de agência válido! Por favor, digite novamente o número da agência!");
            Scanner _in = new Scanner(System.in);
            ret = entradaAgencia(_in.nextLine());
        }
        return ret;
    }

    public static int entradaNumero(String value) {
        int ret = 0;
        if (!value.replaceAll("\\D", "").isEmpty()) {
            ret = Integer.parseInt(value.replaceAll("\\D", ""));
        } else {
            System.out.println("É necessário digitar um número de conta válido! Por favor, digite novamente o número da conta!");
            Scanner _in = new Scanner(System.in);
            ret = entradaNumero(_in.nextLine());
        }
        return ret;
    }

    public static String entradaNomeCliente(String value) {
        String ret = "";
        if (!value.replaceAll("\\S", "").isEmpty()) {
            ret = value.trim();
        } else {
            System.out.println("É necessário digitar um nome válido! Por favor, digite novamente seu nome!");
            Scanner _in = new Scanner(System.in);
            ret = entradaNomeCliente(_in.nextLine());
        }
        return ret;
    }

    public static double entradaSaldo(String value) {
        double ret = 0;
        if (!caracteresInvalidosSaldo(value) || !value.replaceAll("\\S", "").isEmpty()) {
            value = value.replaceAll(",", ".");
            ret = Double.parseDouble(value);
        } else {
            System.out.println("É necessário digitar um saldo válido! Por favor, digite novamente seu saldo!");
            Scanner _in = new Scanner(System.in);
            ret = entradaSaldo(_in.nextLine());
        }
        return ret;
    }

    public static boolean caracteresInvalidosSaldo(String input) {
        Pattern pattern = Pattern.compile("[^0-9.,]");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
}