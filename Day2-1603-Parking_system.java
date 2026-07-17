class ParkingSystem {
    private int b = 0,m = 0,s = 0;
    int bigcount = 0,medcount = 0,smallcount = 0;
    public ParkingSystem(int big, int medium, int small) {
        this.b = big;
        this.m = medium;
        this.s = small;
    }
    public boolean addCar(int carType) {
        
         if(carType == 1){
            bigcount+=1;
            boolean check = bigcount<=b ? true:false;
            return check;
         }
            if(carType == 2){
                medcount+=1;
                boolean check = medcount<=m ? true:false;
            return check;
            }
            if(carType == 3){
                smallcount+=1;
                boolean check = smallcount<=s ? true:false;
            return check;
            }
            return true;
    }
    
}

/**
 * Your ParkingSystem object will be instantiated and called as such:
 * ParkingSystem obj = new ParkingSystem(big, medium, small);
 * boolean param_1 = obj.addCar(carType);
 */
