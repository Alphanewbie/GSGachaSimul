import java.util.ArrayList;
import java.util.Random;

public class TestCase {
    private int gachaAllCnt;        // 이 케이스에서 돌린 횟수
    private int gachaCnt;           // 현재 경우의 돌린 횟수
    private int iwant;              // 뽑아야 되는 갯수
    private boolean gachaCeil;      // 현재 천장인지 아닌지의 여부
    private ArrayList<int[]> getNum;

    public TestCase(int iwant, boolean gachaCeil) {
        this.gachaAllCnt = 0;
        this.gachaCnt = 0;
        this.iwant = iwant;
        this.gachaCeil = gachaCeil;
        this.getNum = new ArrayList<>();
    }

    public int getGachaAllCnt() {
        return gachaAllCnt;
    }

    public void drawGacha() {
        while (iwant != 0) {
            gachaCnt++;
            if (gachaCnt <= 73) {
                pullingOutGacha(0.6);
            } else if (gachaCnt <= 89) {
                pullingOutGacha(32.384);
            } else {
                pullingOutGacha(100);
            }
        }
    }

    public void pullingOutGacha(double Probability) {
        Random rand = new Random();
        double currGet = rand.nextDouble() * 100;
        if (currGet <= Probability) {
            // 뽑음
            int answer;
            if (gachaCeil) {
                iwant--;
                gachaCeil = false;
                answer = 1;
            } else if (!rand.nextBoolean()) {
                gachaCeil = true;
                answer = 0;
            } else {
                iwant--;
                gachaCeil = false;
                answer = 1;
            }
            getNum.add(new int[]{gachaCnt, answer});
            gachaAllCnt += gachaCnt;
            gachaCnt = 0;
        }
        // 안 뽑았을 때에는 다음 경우 진행
    }
}
