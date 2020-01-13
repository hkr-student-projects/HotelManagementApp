package hkrFX;

public class Injection {

    public boolean injected;
    public PersonalAreaEmp homeSession;
    public String colorCode;

    public Injection(PersonalAreaEmp home, boolean injected, String colorCode){
        homeSession = home;
        this.injected = injected;
        this.colorCode = colorCode;
    }
}
