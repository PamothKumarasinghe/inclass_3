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

// This is shit //

import java.util.*;


class Studyroom {
    private int roomNumber;
    private int capacity;
    private boolean isAvailable;

    private final Object lock = new Object();

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
        synchronized (lock) {
        if (!isAvailable) {
            throw new StudyRoomUnavailableException("Study room " + roomNumber + " is unavailable.");
        }
        // Now student is reserving the study room, so availiabilty is false
        isAvailable = false;


        // room is reserved
        System.out.println(Thread.currentThread().getName() + " reserved room " + roomNumber);
        }
    }

    public void release() {
        synchronized (lock) {  // Ensure the safety of the thread
            isAvailable = true;
            System.out.println("Room " + roomNumber + " released.");
        }
    }
}

class Student extends Thread { //Lecture note
    private List<Studyroom> sR; // This sR contains the list of studyrooms
    private Random randomInt = new Random(); //Used Random class to generate a random number between 0 and 3 as three studyrooms

    public Student(List<Studyroom> studyRooms, String name) {
        super(name); // supeer in thread
        this.sR = studyRooms;
    }

    @Override //overriding the run method in therad class
    public void run() {
        for (int i = 0; i < 3; i++) {   // for same student running three times
            try {
                Studyroom room = sR.get(randomInt.nextInt(sR.size()));
                room.reservation();
                Thread.sleep(2000);
                room.release();

            } catch (StudyRoomUnavailableException | InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " selected room coudln't be reserved - " + e.getMessage());
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
    public static void main(String[] args) {
        List<Studyroom> studyRooms = new ArrayList<>(); // This sR contains the list of studyrooms
        studyRooms.add(new Studyroom(1, 4)); //Adding the rooms and capacity to the list by ourelves
        studyRooms.add(new Studyroom(2, 6));
        studyRooms.add(new Studyroom(3, 8));

        Student s1 = new Student(studyRooms, "Student 1");
        Student s2 = new Student(studyRooms, "Student 2");
        Student s3 = new Student(studyRooms, "Student 3");

        s1.start();
        s2.start();
        s3.start();

    }
}
