class Solution {
    public int[] shuffle(int[] nums, int n) {
     int[] result = new int[n * 2];
     int index = 0;
     for(int i = 0; i < n; i++){
        result[index] = nums[i];
        index++;
        result[index] = nums[i+n];
        index++;
     }
     return result;
    }
}
