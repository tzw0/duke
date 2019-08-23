public class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description, boolean b) {
            this.description = description;
            this.isDone = false;
        }
        public String getStatusIcon() {
            return (isDone ? "[✓] " : "[✗] "); //return tick or X symbols
        }
        public String describe() {
            return this.description;
        }
        public void done(boolean b) {
            this.isDone = b;
        }
        public void setDescription(String d) {
            this.description = d;
        }
}