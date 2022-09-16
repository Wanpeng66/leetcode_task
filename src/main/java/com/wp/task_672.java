package com.wp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class task_672 {
    public static void main(String[] args) {
        System.out.println(flipLights(4, 100));
    }
    public static int flipLights(int n, int presses) {
        Set<List<Boolean>> states = new HashSet<>();
        List<Boolean> booleans = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            booleans.add(i, true);
        }
        flipLights(presses, states, booleans);
        return states.size();
    }

    private static void flipLights(int presses, Set<List<Boolean>> states, List<Boolean> booleans) {
        if (presses==0) {
            states.add(booleans);
            return;
        }

        for (int i = 0; i < 4; i++) {
            List<Boolean> tmp = new ArrayList<>(booleans);
            if (i==0) {
                for (int j = 0; j < tmp.size(); j++) {
                    tmp.set(j, !tmp.get(j));
                }
            } else if (i==1) {
                for (int j = 0; j < tmp.size(); j+=2) {
                    tmp.set(j, !tmp.get(j));
                }
            } else if (i==2) {
                for (int j = 1; j < tmp.size(); j+=2) {
                    tmp.set(j, !tmp.get(j));
                }
            }else{
                for (int j = 0; j <= (tmp.size() - 1)/3; j++) {
                    tmp.set(j, !tmp.get(j));
                }
            }
            flipLights(presses-1,states, tmp);
        }
    }
}
