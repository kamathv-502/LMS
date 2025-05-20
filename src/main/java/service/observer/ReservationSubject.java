
package main.java.service.observer;

public interface ReservationSubject {
    void addObserver(ReservationObserver observer);
    void removeObserver(ReservationObserver observer);
    void notifyObservers();
}
