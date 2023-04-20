public class MyException extends Exception {
    //just simple class to throw my Expetions , you can replace it with another idea if you want
    @Override
    public String getMessage() {
        return "ID ERROR ";
    }
}
