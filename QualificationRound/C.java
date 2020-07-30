import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class C {
    
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
        Map<Long,Long> sizes = new HashMap<Long,Long>();
        List<List<Long>> trees = new ArrayList<List<Long>>();
        long ans = 0l;
        for(int i=0;i<n;i++){
            List<Long> tree = new ArrayList<Long>();
            tree.add(in.nextLong());
            tree.add(in.nextLong());
            trees.add(tree);
        }
        Collections.sort(trees, (a,b)->(int)(a.get(0)-b.get(0)));
        for(List<Long> tree:trees){
            long right = tree.get(0) + tree.get(1), left = tree.get(0)-tree.get(1);
            long sizeRight = Math.max(tree.get(1) + sizes.getOrDefault(tree.get(0), 0l), sizes.getOrDefault(right,0l));
            long sizeLeft = Math.max(tree.get(1) + sizes.getOrDefault(left, 0l), sizes.getOrDefault(tree.get(0), 0l));
            sizes.put(right, sizeRight);
            sizes.put(tree.get(0), sizeLeft);
            ans = Math.max(ans,Math.max(sizeLeft, sizeRight));
            System.out.println(sizes);
        }
        return ans;
    }
}