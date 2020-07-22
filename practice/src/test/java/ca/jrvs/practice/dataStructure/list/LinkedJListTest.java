package ca.jrvs.practice.dataStructure.list;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LinkedJListTest {

    LinkedJList<Integer> curr;
    @Before
    public void setUp() throws Exception {
        curr = new LinkedJList<>();
        curr.add(4);
        curr.add(5);
        curr.add(6);
        curr.add(6);
    }

    @After
    public void tearDown() throws Exception {
        curr.clear();
    }

    @Test
    public void duplicateLinkedListNode() {
        curr.duplicateLinkedListNode();
        Integer[] res = new Integer[curr.size()];
        for(int i=0;i<curr.size();i++)
        {
            res[i] = curr.get(i);
        }
        assertEquals(curr.size(),3);
        assertArrayEquals(res, new Integer[]{4, 5, 6});
    }
}