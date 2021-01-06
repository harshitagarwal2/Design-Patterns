package ObserverPattern;

import java.util.ArrayList;

public class StockGrabber  implements Subject{

    ArrayList<Observer> observers;
    double ibmPrice, googPrice , aaplPrice;

    StockGrabber(){
        observers = new ArrayList<>();
    }


    StockGrabber(double newIBMPrice, double newGOOGLPrice, double newAPPLPrice){
        observers = new ArrayList<>();
        this.ibmPrice = newIBMPrice;
        this.googPrice = newGOOGLPrice;
        this.aaplPrice = newAPPLPrice;
    }

    public void setIBMPrice(double newIBMPrice) {
        this.ibmPrice = newIBMPrice;
        notifyObserver();
    }

    public void setGOOGLPrice(double newGOOGLPrice) {
        this.googPrice = newGOOGLPrice;
        notifyObserver();
    }

    public void setAAPLPrice(double newAPPLPrice) {
        this.aaplPrice = newAPPLPrice;
        notifyObserver();
    }


    @Override
    public void register(Observer o) {

        observers.add(o);

    }

    @Override
    public void unregister(Observer o) {
        int observerIndex = observers.indexOf(o);
        System.out.println("Observer " + (observerIndex+1) + " deleted");
        observers.remove(observerIndex);

    }

    @Override
    public void notifyObserver() {

        for(Observer observer : observers){
            observer.update(ibmPrice, aaplPrice, googPrice);
        }

    }
}
