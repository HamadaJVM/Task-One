public class MyException extends Exception {
    @Override
    public String getMessage() {
        return "The Error is about the ID ";
    }
}
