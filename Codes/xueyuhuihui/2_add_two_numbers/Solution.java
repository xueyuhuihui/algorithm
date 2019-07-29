/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {

    /**
     * addTwoNumbers
     *
     * 执行结果：通过
     * 显示详情
     * 执行用时 : 3 ms
     * 内存消耗 : 44 MB
     *
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     *
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     *
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * 示例：
     *
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-two-numbers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode listNode = this.sumListNode(l1, l2);
        return listNode;
    }

    private ListNode sumListNode(ListNode listNode2, ListNode listNode3){

        ListNode temp2 = listNode2;
        ListNode temp3 = listNode3;
        ListNode result = null;
        ListNode temp = null;
        int advance = 0;

        do {
            int val = 0;
            if (temp2 != null){
                val = temp2.val;
                temp2 = temp2.next;
            }
            if (temp3 != null){
                val += temp3.val;
                temp3 = temp3.next;
            }
            val += advance;
            int mod = val % 10;
            if (null == result){
                ListNode listNode = new ListNode(mod);
                temp = listNode;
                result = listNode;
            }else {
                ListNode listNode = new ListNode(mod);
                temp.next = listNode;
                temp = listNode;
            }
            advance = val / 10;
        }while (temp2 != null || temp3 != null || advance != 0);
        return result;
    }

    /**
     * 传参数用，造链表
     * @param sum
     * @return
     */
    public ListNode  setListNode(int[] sum){

        int length = sum.length;
        int i = 0;
        ListNode listNode = null;
        ListNode temp = null;
        do {
            ListNode next1 = new ListNode(sum[i]);
            if (null == listNode){
                listNode = next1;
                temp = listNode;
            }else {
                temp.next = next1;
                temp = next1;
            }
            i++;
        }while (i < length);
        return listNode;
    }

}