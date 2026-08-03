class Solution {
    public int[] findEvenNumbers(int[] digits) {
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < digits.length; i++){
            for(int j = 0; j < digits.length; j++){
                if(i == j) continue;
                for(int k = 0; k < digits.length; k++){
                    if(i == k || j == k) continue;
                    if(digits[i] != 0 && digits[k] % 2 == 0){
                        int num = digits[i] * 100 + digits[j] * 10 + digits[k];
                        set.add(num);
                    }
                }
            }
        }
        ArrayList<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        int[] res = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            res[i] = list.get(i);
        }
        return res;
    }
}
