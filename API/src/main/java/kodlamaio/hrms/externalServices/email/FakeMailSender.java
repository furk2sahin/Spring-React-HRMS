package kodlamaio.hrms.externalServices.email;

public class FakeMailSender {

    public void sendMail(String email, String message){
        System.out.println("Mail sent to: " + email + "\n" + "Message: " + message);
    }

    public boolean verifyMail(String email,
                             String verificationCode,
                             String otherVerificationCode){
        if(verificationCode.equals(otherVerificationCode)){
            System.out.println(email + " verified successfully.");
            return true;
        } else {
            System.out.println("Verification code didn't match");
            return false;
        }
    }
}
