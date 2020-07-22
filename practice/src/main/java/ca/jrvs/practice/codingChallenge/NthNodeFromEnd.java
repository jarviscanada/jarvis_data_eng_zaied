package ca.jrvs.practice.codingChallenge;

/**
 * AUTHOR: zaied
 * DATE: 2020-07-16
 * TIME: 6:59 p.m.
 **/

public class NthNodeFromEnd {
    
    /***
     * time complexity: O(linked list length)
     * space complexity: O(1)
     * @param head ListNode
     * @param n int
     * @return ListNode
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) return null;
        ListNode fp = head;
        ListNode sp = head;
        ListNode prev = null;
        while(n > 0 && sp != null)
        {
            sp = sp.next;
            n--;
        }
        while(sp != null)
        {
            prev = fp;
            fp = fp.next;
            sp = sp.next;
        }
        if(prev != null){
            prev.next = fp.next;
        }
        else{
            return head.next;
        }
        return head;
    }
}
