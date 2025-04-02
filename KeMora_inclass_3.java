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
import java.util.*;


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

    public void reservation() throws StudyRoomUnavailableException {
        if (!isAvailable) {
            throw new StudyRoomUnavailableException("Study room " + roomNumber + " is unavailable.");
        }
        // Now student is reserving the study room, so availiabilty is false
        isAvailable = false;

        // Threading is done here
        // room is reserved
 
    }
}

class Student extends Thread { //Lecture note
    private List<Studyroom> sR;
    private Random randomInt = new Random(); //Used Random class to generate a random number between 0 and 3 as three studyrooms

    public Student(List<Studyroom> studyRooms, String name) {
        super(name);
        this.sR = studyRooms;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {   // for same student running three times
            try {
                Studyroom room = sR.get(randomInt.nextInt(sR.size()));
                room.reservation();
                Thread.sleep(2000); // Simulating study session
                room.reservation();
            } catch (StudyRoomUnavailableException | InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " couldn't reserve a room: " + e.getMessage());
            }
        }
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
