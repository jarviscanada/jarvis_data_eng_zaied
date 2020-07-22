package ca.jrvs.practice.codingChallenge;

/**
 * AUTHOR: zaied
 * DATE: 2020-07-16
 * TIME: 6:40 p.m.
 **/

public class LinkedListCycle {

    /***
     * time complexity: O(linked list size)
     * space complexity: O(1)
     * @param head ListNode
     * @return boolean
     */
    public boolean hasCycle(ListNode head) {

        ListNode fp = head;
        ListNode sp = head;

        while(fp != null && sp != null && sp.next != null)
        {

            fp = fp.next;
            sp = sp.next.next;
            if(sp != null)
                if(fp.val == sp.val)
                    return true;

        }
        return false;
    }
}
