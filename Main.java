import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Lottery r = new Lottery();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("""
                    Выберите действие :
                    1. Добавить игрушку в призовой фонд
                    2. Изменить частоту выпадения игрушки
                    3. Розыгрыш призов 
                    z. Выход
                    >\s""");
            var selection = sc.next();
            switch (selection) {
                case "1" -> r.addToy();
                case "2" -> r.setFrequency();
                case "3" -> r.Lottery();
                case "z" -> {
                    System.exit(0);
                }
                default -> System.out.println("Ошибка ввода. Попробуйте снова");
            }
        }
    }
}
