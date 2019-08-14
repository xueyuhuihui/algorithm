class Solution {

    /**
     * 执行用时 : 8 ms
     * 内存消耗 : 51 MB
     *
     * owner remark: 前后一起查找，用时10天
     * friends remark：
     *
     * 总步数：控制循环
     * 剩余步数：控制相遇情况
     *
     * 两个指针i，j
     * i 方向起始走 i的数值小 加一 较大则不走
     * j 方向起始走 j的数值小 加一 较大则不走
     * <p>
     * i 方向结尾走 i的数值大 减一 较小则不走
     * j 方向结尾走 j的数值大 减一 较小则不走
     * <p>
     * 控制相遇时机
     * 排除简单情况
     *
     * 剩余步数为 1时，
     * i nums1的步数提前走完   计算此时nums2中位数
     * j nums2的步数提前走完   计算此时nums1中位数
     * i j 存在
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int total_step = length1 + length2;
        Double simpleStatus = this.findSimpleStatus(total_step, length1, length2, nums1, nums2);
        if (simpleStatus != null){
            return simpleStatus;
        }
        int i = 0;
        int j = 0;
        int i_right = length1 - 1;
        int j_right = length2 - 1;
        int step = 0;
        Double sum = 0.0;
        int sur_step = 0;
        do {
            boolean i_over = i > i_right;
            boolean j_over = j > j_right;
            if (!i_over || !j_over) {
                step += 1;
                sur_step = total_step - step;
                if (!i_over && !j_over) {
                    if (nums1[i] < nums2[j]) {
                        //logger.info("{},{}", nums1[i], nums2[j]);
                        if (sur_step == 1) {
                            sum = (nums1[i] + nums2[j]) / 2.0;
                            return sum;
                        }
                        //nums1[i] = 0;
                        i++;
                        i_over = i > i_right;
                        sum = this.findOnlyInOneArray(nums2, j, j_right-1, i_over, sur_step);
                        if (sum != null) {
                            return sum;
                        }
                    } else {
                        //logger.info("{},{}", nums1[i], nums2[j]);
                        if (sur_step == 1) {
                            sum = (nums1[i] + nums2[j]) / 2.0;
                            return sum;
                        }
                        //nums2[j] = 0;
                        j++;
                        j_over = j > j_right;
                        sum = this.findOnlyInOneArray(nums1, i, i_right-1, j_over, sur_step);
                        if (sum != null) {
                            return sum;
                        }
                    }
                }

                step += 1;
                sur_step = total_step - step;
                if (nums1[i_right] > nums2[j_right]) {
                    //logger.info("{},{}", nums1[i_right], nums2[j_right]);
                    if (sur_step == 1) {
                        sum = nums2[j_right] / 1.0;
                        return sum;
                    }
                    //nums1[i_right] = 0;
                    i_right--;
                    i_over = i > i_right;
                    sum = this.findOnlyInOneArray(nums2, j, j_right, i_over, sur_step);
                    if (sum != null) {
                        return sum;
                    }
                } else {
                    //logger.info("{},{}", nums1[i_right], nums2[j_right]);
                    if (sur_step == 1) {
                        sum = nums1[i_right] / 1.0;
                        return sum;
                    }
                    //nums2[j_right] = 0;
                    j_right--;
                    j_over = j > j_right;
                    sum = this.findOnlyInOneArray(nums1, i, i_right, j_over, sur_step);
                    if (sum != null) {
                        return sum;
                    }
                }
            }
        } while (step <= total_step);
        //logger.info("查找失败；{},{}", step, total_step);
        return sum;
    }

    /**
     * 中位数在一条数组中的查找
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private Double findOnlyInOneArray(int[] nums, int start, int end, boolean other_nums_over, int sur_step){
        if (other_nums_over) {
            if (sur_step > 1) {
                //logger.info("{},{}", nums[start], nums[end]);
                int average = (start + end) / 2;
                int imod = (end - start) % 2;
                if (imod == 1) {
                    return (nums[average] + nums[average + 1]) / 2.0;
                } else {
                    return nums[average] / 1.0;
                }
            }
        }
        return null;
    }

    /**
     * 查找简单的情况
     *
     * @param total_step
     * @param length1
     * @param length2
     * @param nums1
     * @param nums2
     * @return
     */
    private Double findSimpleStatus(int total_step, int length1, int length2, int[] nums1, int[] nums2) {

        int i1m = (length1-1) / 2;
        int i2m = (length2-1) / 2;
        int i1mod = length1 % 2;
        int i2mod = length2 % 2;
        if (total_step == 2) {
            if (length1 != 0 && length2 != 0) {
                return (nums1[0] + nums2[0]) / 2.0;
            } else if (length1 == 0) {
                return (nums2[0] + nums2[1]) / 2.0;
            } else {
                return (nums1[0] + nums1[1]) / 2.0;
            }
        }

        if (length1 == 0) {
            if (i2mod == 0) {
                return (nums2[i2m] + nums2[i2m + 1]) / 2.0;
            } else {
                return i2m == 0 ? nums2[0] / 1.0 : nums2[i2m] / 1.0;
            }
        }

        if (length2 == 0) {
            if (i1mod == 0) {
                return (nums1[i1m] + nums1[i1m + 1]) / 2.0;
            } else {
                return i1m == 0 ? nums1[0] / 1.0 : nums1[i1m] / 1.0;
            }
        }
        return null;
    }
}
