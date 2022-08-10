package cn.vorbote.smartcampus.exceptions;

/**
 * NotImplementedException<br>
 * Created at 8/5/2022 5:26 PM
 *
 * @author theod
 */
public class NotImplementedException extends RuntimeException {

    public NotImplementedException() {
        super("Function is not implemented yet, please contact the developer for help.");
    }

    public NotImplementedException(String message) {
        super(message);
    }

}
