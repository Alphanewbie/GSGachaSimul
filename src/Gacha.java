import java.util.ArrayList;

/*
생각해보면 iwant가 3이 될 때까지 해야 하는데....
있어야 하는 경우
int Gacha : 반천장이면 0 천장이면 1, 근데 난 지금 천장이니까 1부터 하지만 다음부터는 0부터
가챠 횟수도 세야 하는데, 90일때는 100, 0~73 : 0.6%, 74~89은 32.384%, 90: 100%
저기서 뽑혔을때 천장이 1일때는 무조건 1
천장이 0일때는 반의 확률로 0, 1. 다만, 1일때는 천장을 0으로, 0일때는 천장에 1을 더한다.
그리고 나오는 수를 iwant에 더해주고 만약 3이 된다면 break;
 */
public class Gacha {
    private int testCase;   // 테스트할 케이스의 수
    private int iwant;      // 뽑아야 되는 갯수
    private boolean gachaCeil;      // 현재 천장인지 아닌지의 여부
    private int gachaSum = 0;    // 총 가챠 횟수
    private ArrayList<TestCase> testList;

    public Gacha(int testCase, int iwant, boolean gachaCeil) {
        this.testCase = testCase;
        this.iwant = iwant;
        this.gachaCeil = gachaCeil;
        this.gachaSum = 0;
        this.testList = new ArrayList<>();
    }

    public String run() {
        TestCase currCase;
        for (int i = 0; i < testCase; i++) {
            currCase = new TestCase(iwant, gachaCeil);
            currCase.drawGacha();
            testList.add(currCase);
            gachaSum += currCase.getGachaAllCnt();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("예상 연차수 : ");
        sb.append((double) gachaSum / testCase);
        return sb.toString();
    }
}
