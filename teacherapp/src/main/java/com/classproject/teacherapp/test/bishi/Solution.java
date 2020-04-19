package com.classproject.teacherapp.test.bishi;

/**
 * @ClassName Solution
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/9 22:37
 **/
 class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

public class Solution {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(0);
        ListNode now = listNode;
        for (int i = 1; i <= 10 ; i++) {
            listNode.next = new ListNode(i);
            listNode = listNode.next;
        }
        ListNode ll = ReverseList(now);
        while(ll != null){
            System.out.println(ll.val);
            ll = ll.next;
        }
    }

    public int movingCount(int threshold, int rows, int cols)
    {
        int all = 0;
        int r = 0;
        int l = 0;
        for(int i = 0; i <= rows && i <= threshold; i++){
            for(int j = 0; j <= cols && j <= threshold; j++){
                r = getNum(i) + getNum(j);
                if(r <= threshold){
                    all++;
                }
            }
        }
        return all;
    }

    public int getNum(int num){
        int sum=0;
        while(num!=0){
            sum = sum + num%10;
            num = num/10;
        }
        return sum;
    }
    // TODO: 2020/02/28  完成
    // 出现的问题：
    //
    public static ListNode ReverseList(ListNode head) {
        ListNode tmp = head.next;
        ListNode nodeNow = head;
        ListNode nodeLast = head.next.next;
        while(nodeLast != null){
            if(nodeNow != head){

                tmp.next = nodeNow;
                nodeNow = tmp;
                tmp = nodeLast;
                nodeLast = nodeLast.next;

            }else{
                nodeNow.next = null;
                tmp.next = nodeNow;
                nodeNow = tmp;
                tmp = nodeLast;
                nodeLast = nodeLast.next;
            }
        }
        tmp = nodeNow;
        return tmp;

    }
    /*
    public ListNode ReverseList(ListNode head) {
		Stack<ListNode> stack = new Stack<ListNode>();
		if (head == null || head.next == null) {
			return head;
		}

		while (head.next != null) {
			stack.push(head);
			head = head.next;
		}
		ListNode node = null;
		node = head;
		while (!stack.isEmpty()) {
			head.next = stack.pop();
			head = head.next;
		}
		head.next = null;
		return node;
	}
    */
}