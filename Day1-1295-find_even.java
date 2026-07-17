class Solution {
    public int findNumbers(int[] nums) {
        int count = 0;
        int outercount = 0;
        for(int x:nums){
            int y = x;
            count = 0;
            while(y != 0 ){
                y /= 10;
                count +=1;
            }
            if(count % 2 == 0){
                outercount +=1;
            }
        }
        return outercount;
    }
}
