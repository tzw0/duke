public class DukeException extends Exception {
    private String error;
    public DukeException(String e) {
        this.error = e;
    }
    public String toString() {
        return "DukeException[" + error + "]";
    }
    @Override
    public String getMessage() {
        return this.error;
    }
}
