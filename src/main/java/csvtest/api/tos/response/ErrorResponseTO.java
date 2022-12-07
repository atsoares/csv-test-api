package csvtest.api.tos.response;

public class ErrorResponseTO {
    private String errorMessage;

    public ErrorResponseTO(String errorMessage) {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
