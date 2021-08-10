package com.makemytripapplication.utility;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ReadMobileOtp {

    private static final String ACCOUNT_SID = "ACc22a4d39915b91fc33e12b3fe5cc120e";
    private static final String AUTH_TOKEN = "a888750a9bd323754e77f01c88c1dec9";

    public static String readOTP() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String smsBody = getMessage();
        System.out.println(smsBody);
        String[] oTPNumber = smsBody.split("[^\\d]+");
        String otp = oTPNumber[1];
        System.out.println(otp);

        return otp;
    }

    private static String getMessage() {
      return getMessages().filter(message -> message.getDirection().compareTo(Message.Direction.INBOUND) == 0)
                .filter(message -> message.getTo().equals("+14805264733")).map(Message::getBody).findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private static Stream<Message> getMessages() {
        ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
        return StreamSupport.stream(messages.spliterator(), false);
    }
}
