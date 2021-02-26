import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

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
    private int maxGachaCnt;
    private ArrayList<TestCase> testList;

    public Gacha(int testCase, int iwant, boolean gachaCeil) {
        this.testCase = testCase;
        this.iwant = iwant;
        this.gachaCeil = gachaCeil;
        this.gachaSum = 0;
        this.testList = new ArrayList<>();
        this.maxGachaCnt = 0;
    }

    public void run() {
        TestCase currCase;
        for (int i = 0; i < testCase; i++) {
            currCase = new TestCase(iwant, gachaCeil);
            currCase.drawGacha();
            testList.add(currCase);
            gachaSum += currCase.getGachaAllCnt();
            maxGachaCnt = Math.max(maxGachaCnt, currCase.getGachaAllCnt());
        }
    }

    public String summary() {
        StringBuilder sb = new StringBuilder();
        sb.append("예상 연차수 : ");
        sb.append((double) gachaSum / testCase);
        return sb.toString();
    }

    public String detail() {
        StringBuilder sb = new StringBuilder();
        for (TestCase item : testList) {
            sb.append(item.obtainedNumtoString());
            sb.append("\n");
        }
        return sb.toString();
    }

    // 각각 분포도를 알려준다.
    public String distribution() {
        int[] dist = new int[maxGachaCnt + 1];
        for (TestCase item : testList) {
            dist[item.getGachaAllCnt()]++;
        }
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        StringBuilder sb = new StringBuilder();
        int[] queIn;
        for (int i = 1; i <= maxGachaCnt/10; i++) {
            queIn = new int[] {i*10,0};
            for(int j=queIn[0]-9;j<=queIn[0];j++) {
                if(j>maxGachaCnt)
                    break;
                queIn[1] += dist[j];
            }
            priorityQueue.add(queIn);
        }
        int[] output;
        while (!priorityQueue.isEmpty()) {
            output = priorityQueue.poll();
            sb.append(String.format("%3d연 : %d회\n", output[0], output[1]));
        }
        return sb.toString();
    }

    // 각각 분포도를 알려준다.
    public String gachaList() {
        int[] dist = new int[maxGachaCnt + 1];
        for (TestCase item : testList) {
            dist[item.getGachaAllCnt()]++;
        }
        StringBuilder sb = new StringBuilder();
        int[] output;
        for (int i = 1; i <= maxGachaCnt/10; i++) {
            output = new int[] {i*10,0};
            for(int j=output[0]-9;j<=output[0];j++) {
                if(j>maxGachaCnt)
                    break;
                output[1] += dist[j];
            }
            sb.append(String.format("%3d연 : %d회\n", output[0], output[1]));
        }
        return sb.toString();
    }
}

