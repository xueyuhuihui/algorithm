
class Solution {

    /**
     * twoSum
     *
     * 执行结果：     通过
     * 显示详情
     * 执行用时 :     10 ms
     * 内存消耗 :     39.4 MB
     * owner remark: 待改进
     * friends remark：
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {

        int temp = 0;
        int length = nums.length;
        Set<Integer> tempSet = new HashSet<>();
        for (int i = 0; i < length; i++) {
            temp = target - nums[i];
            tempSet.add(temp);
        }
        for (int i = 0; i < length; i++) {
            boolean contains = tempSet.contains(nums[i]);
            if (contains) {
                for (int j = 0; j < length; j++) {
                    //System.out.println("no_found"+nums[i] + "-" + nums[j]);
                    if (target == (nums[i] + nums[j]) && i != j) {
                        return new int[]{i, j};
                    }
                }
            }
        }

        System.out.print("no_found");
        return null;
    }




}