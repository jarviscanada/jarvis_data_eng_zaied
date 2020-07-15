package ca.jrvs.practice.codingChallenge;

public class ReverseList {

    /***
     * time complexity: O(length of linked list)
     * space complexity: O(1)
     * @param head ListNode
     * @return ListNode
     */
    public ListNode reverseListIter(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;

        while(curr != null)
        {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    ListNode prev = null;

    /***
     * time complexity: O(length of linked list) with recursive overhead
     * space complexity: O(1) with implicit stack
     * @param curr ListNode
     * @return ListNode
     */
    public ListNode reverseListRecur(ListNode curr) {
        if(curr == null) return prev;
        ListNode temp = curr.next;
        curr.next = prev;
        prev = curr;
        curr = temp;
        return reverseListRecur(curr);
    }
}
