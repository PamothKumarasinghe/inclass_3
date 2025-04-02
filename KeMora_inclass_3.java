/** Functional : 
 * Display study room information
 * reserve a study room
 * release a study room
 * concurrency handling
 * simulated student actions
 * error handling and exception
 * 
 * multiple test runs
 */
/** Non-Functionaldsf
 * concurrency management
 * execution requirments
 */


class Studyroom {
    private int roomNumber;
    private int capacity;
    private boolean isAvailable;

    Studyroom(int roomNumber, int capacity) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.isAvailable = true;
    }

    public int getRoom() {
        return roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean availability() {
        return isAvailable;
    }
}
class StudyRoomUnavailableException extends Exception {
    public StudyRoomUnavailableException(String message) {
        super(message);
    }
}

public class KeMora_inclass_3 {
    public static void main() {

    }
}
