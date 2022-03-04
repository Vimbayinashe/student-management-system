package se.iths.service.validatorservice;

public class ValidatorService {

    public boolean isUpdated(String value) {
        if(value == null)
            return false;
        return  value.length() > 0;
    }
}
