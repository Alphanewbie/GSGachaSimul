import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            Gacha gacha = new Gacha(100000, 1, true);
            System.out.println(gacha.run());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

