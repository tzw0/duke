public class DukeException extends Exception {
    String error;
    public DukeException(String e) {
        this.error = e;
    }
    public String toString() {
        return "DukeException[" + error + "]";
    }
}
