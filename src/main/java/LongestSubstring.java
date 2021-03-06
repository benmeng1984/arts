import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * leecode第三题,取子字符串中没有重复的字符最长的一组字符。
 * Created by mengwei on 2019/3/19.
 */
public class LongestSubstring {

    /**
     * 设定了一个窗口，不停地扩大窗口也就是右边不停地往右移，如果遇到重复的 就挪动窗口左边 直到到左边头一个出现重复的字母的位置。
     * 1 注意sum++的判断，要跟set的size比，是因为会删除重复字母之前所有的字母。
     * 2 遇到重复的字母从左边开始挨个删set里面的值，直到删到重复的那个字母为止。
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<Character>();
        int left = 0,right = 0,sum = 0;
        while(right < s.length()){
            final char c = s.charAt(right);
            if(!set.contains(c)){
                right++;
                set.add(c);
                if(sum < set.size()){
                    sum++;
                }
            }else{
                set.remove(s.charAt(left));
                left++;
            }
        }
        return sum;
    }

    /**
     * 优化版本: 使用set是2n的复杂度，采用map的是n的复杂度
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }



    public static void main(String[] args) {
        int aewfgwb = new LongestSubstring().lengthOfLongestSubstring2("aewfgwb");
        System.out.println(aewfgwb);
    }

}
