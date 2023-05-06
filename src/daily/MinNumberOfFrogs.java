package daily;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 1419. 数青蛙[medium]
 * https://leetcode.cn/problems/minimum-number-of-frogs-croaking/
 * 20230506
 */
public class MinNumberOfFrogs {
    public static void main(String[] args) {
//        String croakOfFrogs="crcoackroarkoak";//3
//        String croakOfFrogs="crcoakroak";//2
//        String croakOfFrogs="croakcrook";//-1
//        String croakOfFrogs="crcroakcroakoak";//2
//        String croakOfFrogs="croakcroa";//-1
//        String croakOfFrogs="ccccccccccrrccccccrcccccccccccrcccccccccrcccccccccccrcccccr" +
//                "cccrrcccccccccccccrocrrcccccccccrccrocccccrccccrrcccccccrrrcrrcrccrcoccr" +
//                "occrccccccccorocrocccrrrrcrccrcrcrcrccrcroccccrccccroorcacrkcccrrroacccrr" +
//                "rraocccrrcrrccorooccrocacckcrcrrrrrrkrrccrcoacrcorcrooccacorcrccccoocroacro" +
//                "raoaarcoorrcrcccccocrrcoccarrorccccrcraoocrrrcoaoroccooccororrrccrcrocrrcoroo" +
//                "ocorarccoccocrrrocaccrooaaarrcrarooaarrarrororrcrcckracaccorarorocacrrarorrrao" +
//                "acrcokcarcoccoorcrrkaocorcrcrcrooorrcrroorkkaaarkraroraraarooccrkcrcraocooaoocr" +
//                "aoorrrccoaraocoorrcokrararrkaakaooroorcororcaorckrrooooakcarokokcoarcccroaakkrr" +
//                "ororacrkraooacrkaraoacaraorrorrakaokrokraccaockrookrokoororoooorroaoaokccraorar" +
//                "aokakrookkroakkaookkooraaocakrkokoraoarrakakkakaroaaocakkarkoocokokkrcorkkoorrkr" +
//                "aoorkokkarkakokkkracocoaaaaakaraaooraokarrakkorokkoakokakakkcracarcaoaaoaoorcaakk" +
//                "raooaoakkrrroaoaoaarkkarkarkrooaookkroaaarkooakarakkooaokkoorkroaaaokoarkorraoraor" +
//                "cokokaakkaakrkaaokaaaroarkokokkokkkoakaaookkcakkrakooaooroaaaaooaooorkakrkkakkkkao" +
//                "kkooaakorkaroaorkkokaakaaaaaocrrkakrooaaroroakrakrkrakaoaaakokkaaoakrkkoakocaookkak" +
//                "ooorkakoaaaaakkokakkorakaaaaoaarkokorkakokakckckookkraooaakokrrakkrkookkaaoakaaaokk" +
//                "aokkaaoakarkakaakkakorkaakkakkkakaaoaakkkaoaokkkakkkoaroookakaokaakkkkkkakoaooakcok" +
//                "kkrrokkkkaoakckakokkocaokaakakaaakakaakakkkkrakoaokkaakkkkkokkkkkkkkrkakkokkroaakka" +
//                "kaoakkoakkkkkkakakakkkaakkkkakkkrkoak";  //229
        String croakOfFrogs="cccccccrrooaakk";//-1
        Solution solution = new Solution();
        int count = solution.minNumberOfFrogs(croakOfFrogs);
        System.out.println(count);


    }

    static class Solution {
        public int minNumberOfFrogs(String croakOfFrogs) {
            String croak="croak";
            if (croakOfFrogs==null || croakOfFrogs.length()<croak.length()) return -1;
            if (croak.equals(croakOfFrogs)) return 1;
            if (croakOfFrogs.charAt(croakOfFrogs.length()-1)!='k') return -1;
            // 用来存储不完整的croak
            // 加入map中,key为需要的字符，value为当前拼接的字符串的个数，若已完整则移除
            HashMap<String,Integer> map = new HashMap<>();
            map.put("c",0);
            // 记录有几只青蛙
            int count=0;
            for (int i = 0; i < croakOfFrogs.length(); i++) {
                String substring = croakOfFrogs.substring(i, i + 1);
                Integer num = map.get(croakOfFrogs.substring(i, i + 1));
                if (num!=null){
                    if (croakOfFrogs.charAt(i)!='c') {
                        if (num == 0) {
                            //不是c，则判断num是否大于0，否则是无效组合
                            return -1;
                        }
                        //当前的数量-1
                        map.put(croakOfFrogs.substring(i, i + 1), num - 1);
                    }else if (map.get("c")==0){
                        count++;
                    }else {
                        map.put(croakOfFrogs.substring(i, i + 1), num==0?0:num - 1);
                    }
                    //当前字符在croak中下标
                    int j = croak.indexOf(croakOfFrogs.charAt(i));
                    // j==4 说明当前字符为k
                    if (j!=4) {
                        // 下一次需要的字符
                        String s = croak.substring(j + 1, j + 2);
                        map.put(s, map.get(s) == null ? 1 : map.get(s) + 1);
                    }else{
                        // 空闲青蛙+1
                        map.put("c",map.get("c")+1);
                    }

                }else {
                    return -1;
                }
            }

            // 判断是否存在处理未完成的croak
            Set<String> keySet = map.keySet();
            for (String s : keySet) {
                if (!s.equals("c") && map.get(s)>0){
                    return -1;
                }
            }

            return count;
        }

        //官方解法
        public int minNumberOfFrogs2(String croakOfFrogs) {
            if (croakOfFrogs.length() % 5 != 0) {
                return -1;
            }
            int res = 0, frogNum = 0;
            int[] cnt = new int[4];
            Map<Character, Integer> map = new HashMap<Character, Integer>() {{
                put('c', 0);
                put('r', 1);
                put('o', 2);
                put('a', 3);
                put('k', 4);
            }};
            for (int i = 0; i < croakOfFrogs.length(); i++) {
                char c = croakOfFrogs.charAt(i);
                int t = map.get(c);
                if (t == 0) {
                    cnt[t]++;
                    frogNum++;
                    if (frogNum > res) {
                        res = frogNum;
                    }
                } else {
                    if (cnt[t - 1] == 0) {
                        return -1;
                    }
                    cnt[t - 1]--;
                    if (t == 4) {
                        frogNum--;
                    } else {
                        cnt[t]++;
                    }
                }
            }
            if (frogNum > 0) {
                return -1;
            }
            return res;
        }
    }
}
