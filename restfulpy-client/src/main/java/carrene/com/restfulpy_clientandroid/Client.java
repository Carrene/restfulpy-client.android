package carrene.com.restfulpy_clientandroid;

public class Client {

    Authenticator authenticator;

    public Client(){
    }

    public Client(Authenticator a){
        this.authenticator = a;
    }

    public Request request(String url){
        return new Request(this, url);
    }
}
