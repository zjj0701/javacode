import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainTest {

    public static boolean isAnagram_(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        Map<Character, Integer> sMap = new HashMap<Character, Integer>();
        Map<Character, Integer> tMap = new HashMap<Character, Integer>();
        for (char i : sArray) {
            if (!sMap.containsKey(i)) {
                sMap.put(i, 1);
            } else {
                sMap.put(i, sMap.get(i) + 1);
            }
        }
        for (char i : tArray) {
            if (!tMap.containsKey(i)) {
                tMap.put(i, 1);
            } else {
                tMap.put(i, tMap.get(i) + 1);
            }
        }
        if (sMap.keySet().size() != tMap.keySet().size()) {
            return false;
        } else {
            Set<Character> characters = sMap.keySet();
            for (Character character : characters) {
                if (!tMap.containsKey(character)) {
                    return false;
                }
                if (sMap.get(character) != tMap.get(character)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isAnagram(String s, String t) {
        int[] record = new int[26];
        for (int i = 0; i < s.length(); i++) {
            record[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            record[t.charAt(i) - 'a']--;
        }
        for (int count : record) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        // Stream 表达式，先装箱，再收集
        List<Integer> num1 = Arrays.stream(nums1).boxed().collect(Collectors.toList());
        List<Integer> num2 = Arrays.stream(nums2).boxed().collect(Collectors.toList());
        int resLen = Math.min(num1.size(), num2.size());
        List<Integer> res = new ArrayList<>(resLen);
        if (num1.size() > num2.size()) {
            Set<Integer> set = new HashSet<>(num2);
            for (int i : set) {
                if (num1.contains(i)) {
                    res.add(i);
                }
            }
        } else {
            Set<Integer> set = new HashSet<>(num1);
            for (int i : set) {
                if (num2.contains(i)) {
                    res.add(i);
                }
            }
        }
        // 这里的收集怎么做？
        //int[] res_ = new int[res.size()];
//        for
//        for (int i = 0; i < res.size(); i++) {
//            res_[i] = res.get(i);
//        }
        int[] res_ = res.stream().mapToInt(x -> x).toArray();
        return res_;
    }

    @Test
    public void test1() {
        int[] nums1 = {2, 3, 4};
        int[] nums2 = {3, 4, 5};
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }
        for (int i : nums2) {
            set2.add(i);
        }
        set1.retainAll(set2);
        int[] array = set1.stream().mapToInt(Integer::intValue).toArray();
    }

    @Test
    public void test2() {
        int x = 21;
        System.out.println(x % 10);
        System.out.println(x / 10);
    }

    public static boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();

        while (n > 0) {
            int l = getAllNum(n);
            if (l == 1) {
                return true;
            }
            if (set.contains(l)) {
                return false;
            }
            set.add(l);
            n = l;
        }
        return n == 1;
    }

    public static int getAllNum(int n) {
        int sum = 0;
        while (n > 0) {
            int tmp = n % 10;
            sum += tmp * tmp;
            n = n / 10;
        }
        return sum;
    }

    /**
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int left = target - nums[i];
            if (map.containsKey(left)) {
                return new int[]{i, map.get(left)};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }

    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int res = 0;


        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            for (int j : nums2) {
                map.put(i + j, map.getOrDefault(i + j, 0) + 1);
            }
        }

        for (int i : nums3) {
            for (int j : nums4) {
                res += map.getOrDefault(0 - i - j, 0);
            }
        }
        return res;
    }

    public static boolean canConstruct() {
//        String ransomNote = "fihjjjjei";
//        String magazine = "hjibagacbhadfaefdjaeaebgi";
        String ransomNote = "aa";
        String magazine = "ab";
        Map<Character, Integer> map = new HashMap<>();
        for (char c : magazine.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (Character c : ransomNote.toCharArray()) {
            if (!map.containsKey(c)) {
                return false;
            } else {
                int res = map.get(c) - 1;
                map.put(c, res);
                if (res < 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean canConstruct_2() {
//        String ransomNote = "fihjjjjei";
//        String magazine = "hjibagacbhadfaefdjaeaebgi";
        String ransomNote = "aa";
        String magazine = "ab";
        int[] res = new int[26];
        for (char c : magazine.toCharArray()) {
            res[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            res[c - 'a']--;
        }
        for (int i : res) {
            if (i < 0) {
                return false;
            }
        }
        return true;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return res;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    right--;
                    left++;
                }
            }
        }
        return res;
    }

    @Test
    public void test3() {
        char c = 'C';
        System.out.println(c - 'A');
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] >= 0 && nums[k] > target) {
                break;
            }
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            for (int i = k + 1; i < nums.length; i++) {
                if (nums[i] + nums[k] > target && nums[i] + nums[k] >= 0) {
                    break;
                }
                if (i > k + 1 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    long sum = (long) nums[k] + nums[i] + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        res.add(Arrays.asList(nums[k], nums[i], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    }
                }
            }
        }
        return res;
    }


    public static List<List<Integer>> combine() {
        breaktraking(4, 2, 1);
        return res;
    }

    public static void breaktraking(int n, int k, int start) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < n - (k - path.size()) + 1; i++) {
            path.add(i);
            breaktraking(n, k, i + 1);
            path.removeLast();
        }
    }

    static List<List<Integer>> res = new ArrayList<>();
    static LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        brackingTrace(n, k, 1, 0);
        return res;
    }

    private static void brackingTrace(int n, int k, int start, int sum) {
        if (sum > n) {
            return;
        }
        if (path.size() == k) {
            if (sum == n) {
                res.add(new ArrayList<>(path));
            }
            return;
        }
        for (int i = start; i < 9 - (k - path.size()) + 1; i++) {
            path.add(i);
            sum += i;
            brackingTrace(n, k, i + 1, sum);
            path.removeLast();
            sum -= i;
        }
    }

    static List<String> resString = new ArrayList<>();

    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return resString;
        }
        String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backTracking_str(digits, numString, 0);
        return resString;
    }

    static StringBuilder sb = new StringBuilder();


    private static void backTracking_str(String digits, String[] numString, int num) {
        if (num == digits.length()) {
            resString.add(sb.toString());
            return;
        }
        String str = numString[digits.charAt(num) - '0'];
        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));
            backTracking_str(digits, numString, num + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backTracking_all_num(candidates, target, new ArrayList<Integer>(), 0, 0, res);
        return res;
    }

    private static void backTracking_all_num(int[] candidates, int target, List<Integer> path, int sum, int idx, List<List<Integer>> res) {

        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = idx; i < candidates.length; i++) {
            if (sum + candidates[i] > target) {
                break;
            }
            path.add(candidates[i]);
            backTracking_all_num(candidates, target, path, sum+candidates[i], i, res);
            path.remove(path.size() - 1);

        }

    }

    @Test
    public void test() {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(1);
        path.add(1);
        path.add(1);
        path.add(1);
        res.add(new ArrayList<>());
        res.add(new ArrayList<>(path));
    }
    public static void main(String[] args) {
        int[] r = {2, 3, 6, 7};
        combinationSum(r, 7);
//        letterCombinations("");
//        combine();
//        System.out.println(isAnagram("aa", "a"));
//        List<Integer> allNum = getAllNum(10);
//        int[] s = {2, 7, 11, 5, 9};
//        System.out.println(Arrays.toString(twoSum(s, 9)));
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        List<List<Integer>> results = fourSum(nums, target);
        for (List<Integer> result : results) {
            System.out.println(result);
        }
        int[] nums1 = {1, 2};
        int[] nums2 = {-2, -1};
        int[] nums3 = {-1, 2};
        int[] nums4 = {0, 2};
        fourSumCount(nums1, nums2, nums3, nums4);
    }


}
