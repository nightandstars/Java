package example.movingtruck;
import java.util.Scanner;

public class MovingTruck {
    public static void main(String[] args) {
        Scanner object = new Scanner(System.in);
        System.out.println("Enter the number of boxes to be moved: ");
        int totalBoxes = object.nextInt();
        Scanner object2 = new Scanner(System.in);
        System.out.println("Enter the size of the truck: ");
        int truckCapacity = object2.nextInt();
        while(totalBoxes > 0){
            if(truckCapacity > totalBoxes){
                truckCapacity = totalBoxes;
            }
            System.out.println("Moved " + truckCapacity + " boxes");
            totalBoxes -= truckCapacity;
        }
    }
}
