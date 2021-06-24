package arraylist;

/**
 * 670.     Medium      最大交换
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 */
public class MaximumSwap670 {
    public int maximumSwap(int num) {
        char[] ch = String.valueOf(num).toCharArray();
        int [] last =  new int[10];
        // 记录每个数字最后一次出现的下标
        for (int i = 0; i < ch.length; i++) {
            last[ch[i] - '0'] = i;
        }

        // 从左向右扫描，找到当前位置右边最大的数字并交换
        for (int i = 0; i < ch.length; i++) {
            // 从9开始比较，如果当前是9，就略过
            for (int d = 9; d > ch[i] - '0'; d--) {
                // 如果比当前位置数大的数的下标在当前位置后面，交换
                if (last[d] > i) {
                  swap(ch, last[d], i);
                  return Integer.parseInt(new String(ch));
                }
            }
        }
        return num;
    }

    private void swap(char [] num, int i, int j) {
        char temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }

    public static void main(String [] args) {
        int num = new MaximumSwap670().maximumSwap(9937);
        System.out.println(num);
    }
}
