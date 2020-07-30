import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class D {
    
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int nCases = in.nextInt();
        for(int i=1;i<=nCases;i++){
            System.out.printf("Case #%d: %d\n", i, solveCase(in));
        }
        in.close();
    }

    private static long solveCase(Scanner in){
        int n = in.nextInt();
        long gas = in.nextInt();
        PriorityQueue<List<Long>> heap = new PriorityQueue<>((a,b) -> (a.get(0)-b.get(0)<0)?-1:1);
        heap.add(Arrays.asList(0l,(long)gas));
        for(int i=0;i<n;i++){
            while(!heap.isEmpty() && heap.peek().get(1) < i) heap.poll();
            long price = in.nextInt();
            if(!heap.isEmpty()){
                if(price != 0){ 
                    price += heap.peek().get(0);
                    heap.add((Arrays.asList(price,i+gas)));
                }
            }
        }
        while(!heap.isEmpty() && heap.peek().get(1) < n-1) heap.poll();
        if(heap.isEmpty()) return -1;
        return heap.peek().get(0);
    }
}