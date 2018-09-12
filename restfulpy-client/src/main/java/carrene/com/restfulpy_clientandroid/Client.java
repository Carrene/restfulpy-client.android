package carrene.com.restfulpy_clientandroid;

public class Client {

    Authenticator authenticator;

    public Client(){
    }

    public Client(Authenticator a){
        this.authenticator = a;
    }

    public Request request(String url, String verb){
        return new Request(this, url, verb);
    }

    public void login(){

    }
    public void logout(){

    }

    public Authenticator getAuthenticator() {
        return authenticator;
    }


}
