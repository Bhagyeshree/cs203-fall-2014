package lab2;

public class App {

    static BunchOfObjects<Square> createBunchOfObjectsRecursively(int numLevel) {
        /* This is a helper function */
        return null;
    }

    static void createBunchOfObjectsRecursively(int numLevel, 
        BunchOfObjects<Square> squares) {
        
        /* do this at home: recursively */
    }
    
    static void drawBunchOfObjects(BunchOfObjects<Square> basket) {
        /* do this at home */
    }

    public static void main(String args[]) {
        BunchOfObjects<Square> basket = createBunchOfObjectsRecursively(5);

        basket.print();
        drawBunchOfObjects(basket);
    }
}
