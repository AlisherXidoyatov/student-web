package Employee.ishchilar.Exception;

public class ServerBadRequestException extends RuntimeException{
    public ServerBadRequestException(String message){
        super(message);
    }
}
