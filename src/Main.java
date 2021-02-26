import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            Gacha gacha = new Gacha(100000, 3, true);
            gacha.run();
            System.out.println(gacha.summary());
//            System.out.println(gacha.detail());
            System.out.println(gacha.distribution());
//            System.out.println(gacha.gachaList());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

