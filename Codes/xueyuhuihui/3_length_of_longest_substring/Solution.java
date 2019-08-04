class Solution {

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * 执行用时 : 66 ms
     * 内存消耗 : 49 MB
     *
     * owner remark: 待改进
     * friends remark：
     *
     * 示例 1:
     *
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     *
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     *
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     */
    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        if (0 == length|| 1 == length){
            return length;
        }
        char[] chars = s.toCharArray();

        Set<Character> foundRightSet = new HashSet<>();
        int result = 0;
        int lengthOfLongestSubstring = 0;
        int startIndex = 0;
        Map<String, Integer> stringIntegerMap = new HashMap<>();
        stringIntegerMap.put("lengthOfLongestSubstring", 0);
        for (int i = startIndex; i < length; i++){
            char aChar = chars[i];
            if (foundRightSet.contains(aChar)){
                if (result < lengthOfLongestSubstring){
                    result = lengthOfLongestSubstring;
                }
                startIndex = this.foundMult(stringIntegerMap, startIndex, length, chars, foundRightSet, lengthOfLongestSubstring, aChar);
                lengthOfLongestSubstring = stringIntegerMap.get("lengthOfLongestSubstring");
                if (result < lengthOfLongestSubstring){
                    result = lengthOfLongestSubstring;
                }
                foundRightSet.add(aChar);
            }else {
                foundRightSet.add(aChar);
                lengthOfLongestSubstring += 1;
                if (result < lengthOfLongestSubstring){
                    result = lengthOfLongestSubstring;
                }
                stringIntegerMap.put("lengthOfLongestSubstring", lengthOfLongestSubstring);
            }
        }
        return result;
    }

    /**
     * 思路：找到重复的后，重新进行子串的开始下标定位，子串里面的不重复的保存，用于重复判断，更新子串的长度
     *
     * @param stringIntegerMap 存了子串的长度
     * @param startIndex 最新匹配的子串所在的索引
     * @param length 字符串长度
     * @param chars 字符串的字符数组
     * @param foundRightSet 子串的元素set
     * @param lengthOfLongestSubstring 子串的长度
     * @param multChar 相同的字符
     * @return
     */
    private int foundMult(Map<String, Integer> stringIntegerMap,int startIndex, long length, char[] chars, Set<Character> foundRightSet,
                          int lengthOfLongestSubstring, char multChar){
        HashSet<Character> needRemoveSet = new HashSet<>();
        for (int i = startIndex; i < length; i++){
            char aChar = chars[i];
            needRemoveSet.add(aChar);
            startIndex += 1;
            if (Objects.equals(aChar, multChar)) {
                foundRightSet.removeAll(needRemoveSet);
                return  startIndex;
            }
            lengthOfLongestSubstring -= 1;
            stringIntegerMap.put("lengthOfLongestSubstring", lengthOfLongestSubstring);
        }
        return startIndex;
    }


}