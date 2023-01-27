package registrationform.hw11;

public class Validate {

    public static boolean validateName( String name )
    {
        return name.matches( "[A-Z][a-zA-Z]* + [A-Z][a-zA-Z]*" ) && name.length() >= 2;
    }

    public static boolean validatePhone( String phone )
    {
        return phone.matches( "([(0-9)])+([(0-9)]){6,9}" );
    }

    public static boolean validateEmail(String email)
    {
        return email.matches("^[a-zA-Z0-9+_.-]([._-](?![._-])|[a-zA-Z0-9])+@[a-zA-Z0-9.-]([._-](?![._-])|[a-zA-Z0-9])+$");
    }

    public static boolean validatePassword(String pass)
    {
        return pass.matches("[a-zA-Z0-9+_.-]([._-](?![._-])|[a-zA-Z0-9])+[a-zA-Z0-9.-]([._-](?![._-])|[a-zA-Z0-9])+$");
    }

    public static boolean validateConfirmPass(String pass, String confirmPass)
    {
        return pass.equals(confirmPass);
    }
}
