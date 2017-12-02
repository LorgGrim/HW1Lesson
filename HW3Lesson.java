import java.util.Scanner;
/**
 * Java. Level 1. Lesson 3. Example of homework
 *
 * @author Stanislav Bandurin
 * @version dated Dec 02, 2017
 */


class HW3Lesson {
    static int minimum;
    static int maximum;
    static int tries;
    static int quest;
    static String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
        "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", 
        "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

    public static void main(String[] args) {
        int choice = 0;
        System.out.println("Select a game: \n 1 - guessTheNumber \n 2 - guessTheWord \nTo select, enter a number (1/2)");
        Scanner scn = new Scanner(System.in);
        choice = scn.nextInt(); 
        if (choice == 1){
            /**
            * 1. Написать программу, которая загадывает случайное число от 0 до 9, и пользователю дается 3 попытки угадать это число. При
            * каждой попытке компьютер должен сообщить больше ли указанное пользователем число чем загаданное, или меньше. После
            * победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
            */

            guessTheNumber();
        }
        else{
            /**
            * 2 ** Прочитать из файла массив слов String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
            * При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
            * сравнивает его с загаданным словом и сообщает правильно ли ответил пользователь. Если слово не угадано, компьютер показывает буквы которые стоят на своих местах.
            * apple – загаданное
            * apricot - ответ игрока 
            * ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
            * Для сравнения двух слов посимвольно, можно пользоваться:
            * String str = "apple";
            * str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
            */
            guessTheWord(words);
        }
    }
    
     public static void guessTheWord(String[] words) {
        String questWord;
        String guessWord;
        StringBuffer visualWord = new StringBuffer("###############");
        questWord = init(words);
        Scanner scn = new Scanner(System.in);
        int minLength;
        do {
            System.out.printf("Enter the guessed word \n", questWord);
            guessWord = scn.next().toLowerCase();
            scn.nextLine();
            minLength = (questWord.length() > guessWord.length()) ? guessWord.length() : questWord.length();
            if (!questWord.equals(guessWord)) {
                for (int i = 0; i < minLength; i++) {
                    if (questWord.charAt(i) == guessWord.charAt(i))
                        visualWord.setCharAt(i, questWord.charAt(i));
                    else
                        visualWord.setCharAt(i, '#');
                }
                for (int i = minLength; i < visualWord.length(); i++) {
                    visualWord.setCharAt(i, '#');
                }
                System.out.println("Result of guessing is: "+visualWord.toString());
            } else break;
        } while (true);
        System.out.println("Yes you guessed!");
    }
    
     public static void guessTheNumber() {
        int guess;
        do {
            init();
            do {
                guess = getNumber();
                isGuessed(quest, guess);
                tries--;
            } while (tries > 0 && quest != guess);
            System.out.println((tries == 0 && quest != guess) ? "You lose!" : "");
        } while (isExit());
        System.out.println("---GAME OVER---");
    }

    public static boolean isExit() {
        Scanner scn = new Scanner(System.in);
        System.out.println("You want to play \"Guess The Number\" again  (Y/N)?");
        String exit = scn.nextLine().toLowerCase();
        return exit.charAt(0) == 'y';
    }

    public static boolean isGuessed(int quest, int guess) {
        System.out.println((guess == quest) ? "Congratulations, you guessed it!" :
                (guess > quest) ? "The guess number is less" : "The guess number is larger");
        return guess == quest;
    }

    public static int getNumber() {
        int x;
        Scanner scn = new Scanner(System.in);
        do {
            System.out.printf("Enter a guess number from 0 to 9 please \n", minimum, maximum, quest);
            while (!scn.hasNextInt()) {
                System.out.println("Enter a NUMBER please");
                scn.nextLine();
            }
            x = scn.nextInt();
        } while (x > minimum && maximum < x);
        return x;
    }

    public static void init() {
        minimum = 0;
        maximum = 9;
        tries = 3;
        quest = minimum + (int) (Math.random() * maximum - minimum);
        System.out.println("Guess the number generated by computer.\nYou have " + tries + " tries");
    }

    public static String init(String[] words) {
        minimum = 0;
        maximum = words.length;
        quest = minimum + (int) (Math.random() * maximum - minimum);
        return words[quest];
    }
}