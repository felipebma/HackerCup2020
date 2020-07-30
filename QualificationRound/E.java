import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class E {
    
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int nCases = in.nextInt();
        for(int i=1;i<=nCases;i++){
            System.out.printf("Case #%d: %d\n", i, solveCase(in));
        }
        in.close();
    }

    static List<Integer>[] graph;
    static int[] parent;
    static long[] price;
    static int[] distance;
    static long gas;

    private static long solveCase(Scanner in){
        int n = in.nextInt();
        gas = in.nextInt();
        int start = in.nextInt(), target = in.nextInt();
        graph = new List[n+1];
        parent = new int[n+1];
        distance = new int[n+1];
        Arrays.fill(parent, -1);
        price = new long[n+1];
        for(int i=0;i<=n;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=1;i<=n;i++){
            int a = in.nextInt();
            long p = in.nextLong();
            if(a != 0){
                graph[i].add(a);
                graph[a].add(i);    
            }
            price[i] = p;
        }
        parent[start] = 0;
        dfs(start);
        List<Integer> path = findPath(start, target);
        PriorityQueue<List<Long>> heap = new PriorityQueue<>((a,b) -> (a.get(0)<b.get(0))?-1:1);
        heap.add(Arrays.asList(0l,gas));
        for(int i=0;i<distance[target];i++){
            int curr = path.get(i), next = path.get(i+1);
            while(!heap.isEmpty() && heap.peek().get(1) < distance[curr]) heap.poll();
            if(heap.isEmpty()) return -1;
            long currPrice = heap.peek().get(0) + price[curr];
            if(price[curr]!=0) heap.add(Arrays.asList(currPrice, distance[curr] + gas));
            getGas(heap,curr,next);
        }
        while(!heap.isEmpty() && heap.peek().get(1) < distance[target]) heap.poll();
        if(heap.isEmpty()) return -1;
        return heap.peek().get(0);
    }

    private static void getGas(PriorityQueue<List<Long>> heap, int curr, int next){
        int level = 0;
        List<List<Long>> ret = new ArrayList<>(); 
        Queue<Integer> currLevel, nextLevel = new ArrayDeque<>();
        for(int i:graph[curr]){
            if(i!= next && i!= parent[curr]) nextLevel.add(i);
        }
        int dist = distance[curr], diff = 0;
        while(nextLevel.size() > 0){
            if(++level >= gas) break;
            dist++;
            diff++;
            currLevel = nextLevel;
            nextLevel = new ArrayDeque<>();
            while(!heap.isEmpty() && heap.peek().get(1) < dist) ret.add(heap.poll());
            if(heap.isEmpty()) break;
            for(int i:currLevel){
                if(price[i]!=0) ret.add(Arrays.asList(heap.peek().get(0) + price[i], gas-diff+distance[curr]));
                for(int j:graph[i]) if(j != parent[i]) nextLevel.add(j);
            }
        }
        for(List<Long> l:ret) heap.add(l);
    }

    private static void dfs(int curr){
        Queue<Integer> queue = new ArrayDeque();
        queue.add(curr);
        parent[curr] = 0;
        distance[curr] = 0;
        while(queue.size() > 0){
            curr = queue.poll();
            for(int i:graph[curr]){
                if(parent[i] == -1){
                    parent[i] = curr;
                    distance[i] = distance[curr]+1;
                    queue.add(i);
                }
            }
        }
    }

    private static List<Integer> findPath(int start, int target){
        List<Integer> path = new ArrayList<>();
        while(target != start){
            path.add(target);
            target = parent[target];
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }
}