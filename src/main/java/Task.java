public class Task {
        protected String description;
        protected boolean isDone;
        protected String tt;
        protected String extra;
        public Task(String description, boolean b)  throws DukeException {
            this.tt = "";
            this.extra = "";
            this.description = description;
            this.isDone = b;
            if (description.isBlank()) {
                throw new DukeException("empty task");
            }
        }
        public String extra_() {return this.extra;}
        public String task_type() {return this.tt;}
        public Boolean is_Done() {
            return this.isDone; //return tick or X symbols
        }
        public String describe() {
                return this.description;
        }
        public void done(boolean b) {
            this.isDone = b;
        }
        public String toString() {
//            return (isDone ? "[✓] " : "[✗] ") + this.description;
            return (isDone ? "[Done] " : "[X] ") + this.description;
        }
}