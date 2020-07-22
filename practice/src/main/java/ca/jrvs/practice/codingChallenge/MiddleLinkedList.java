package ca.jrvs.practice.codingChallenge;

/**
 * AUTHOR: zaied
 * DATE: 2020-07-16
 * TIME: 6:49 p.m.
 **/

public class MiddleLinkedList {

    /**
     * time complexity: O(linked list length)
     * space complexity: O(1)
     * @param head ListNode
     * @return ListNode
     */
    public ListNode middleNode(ListNode head) {

        ListNode fp = head;
        ListNode sp = head;

        while(sp != null && sp.next != null)
        {
            fp = fp.next;
            sp = sp.next.next;
        }
        return fp;
    }
}
