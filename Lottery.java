import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Lottery {

    private static ArrayList<Toy> toys = new ArrayList<>();
    private static PriorityQueue<Toy> prizes = new PriorityQueue<>();

    private static int idCounter = 0;
    
// Метод добавления игрушки
    
    public void addToy() {
        Scanner scan = new Scanner(System.in);
        String title;
        int frequency;
        while (true) {
            System.out.print("Введите название игрушки на английском: ");
            title = scan.nextLine();
            if (title.isEmpty()) {
                System.out.println("Ошибка ввода попробуйте снова");
                break;
            }
            System.out.print("Введите частоту выпадения игрушки: ");
            String value = scan.nextLine();
            if (isDigit(value)) {
                frequency = Integer.parseInt(value);
                if (frequency <= 0) {
                    System.out.println("");
                } else {
                    Toy toy = new Toy(idCounter, title, frequency);
                    if (!toys.contains(toy) || toys.size() == 0) {
                        idCounter++;
                        toys.add(toy);
                        System.out.println("Игрушка успешно добавлена");
                    } else {
                        System.out.println("Игрушка уже есть в призовом фонде");
                    }
                }
            } else {
                System.out.println("Ошибка ввода.Попробуйте снова");
            }
            break;
        }
    }
     // Исключение числовой формат данных

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

//Метод изменения частоты выпадения игрушки
    
    public void setFrequency() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите ID игрушки : ");
        String value = scan.nextLine();
        if (isDigit(value)) {
            int selectedId = Integer.parseInt(value);
            if (selectedId >= 0 && selectedId < toys.size()) {
                System.out.println("Toy " + toys.get(selectedId).getToyTitle() +
                        " Частота победы " + toys.get(selectedId).getToyVictoryFrequency());
                System.out.print("Введите новую частоту выпадения: ");
                value = scan.nextLine();
                if (isDigit(value)) {
                    int newFrequency = Integer.parseInt(value);
                    toys.get(selectedId).setToyVictoryFrequency(newFrequency);
                    System.out.println("Частота успешно изменена.");
                } else {
                    System.out.println("Ошибка ввода. Попробуйте снова");
                }
            } else {
                System.out.println("В призовом фонде нет игрушки с таким ID");
            }
        } else {
            System.out.println("Ошибка ввода.Попробуйте снова");
        }
    }
    
    //Метод выпадения игрушки
    
    public Toy getPrize() {
        if (prizes.size() == 0) {
            Random rnd = new Random();
            for (Toy toy : toys) {
                for (int i = 0; i < toy.getToyVictoryFrequency(); i++) {
                    Toy temp = new Toy(toy.getToyId(), toy.getToyTitle(), rnd.nextInt(1, 10));
                    prizes.add(temp);
                }
            }
        }
        return prizes.poll();
    }
    
//Метод сохранение результата в файле Результат.txt
    
    private void saveResult(String text) {
        File file = new File("Результат.txt");
        try {
            file.createNewFile();
        } catch (Exception ignored) {
            throw new RuntimeException();
        }
        try (FileWriter fw = new FileWriter("Результат.txt", true)) {
            fw.write(text + "\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
        public void Lottery() {
        if (toys.size() >= 2) {
            Toy prize = getPrize();
            System.out.println("Prize: " + prize.getToyTitle());
            saveResult(prize.getInfo());
        } else {
            System.out.println("Добавьте в призовой фонд как минимум 2 игрушки.");
        }
    }
}
