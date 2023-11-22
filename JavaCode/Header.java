package JavaCode;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

class Header {
    private static int messageIdCounter = 0;

    private int messageId;
    private Client sender;
    private String subject;
    private String message;
    private String date;

    public Header(Client sender, String subject, String message) {
        this.messageId = generateMessageId();
        this.sender = sender;
        this.subject = subject;
        this.message = message;
        updateDate();
        formatHeader();
    }

    private int generateMessageId() {
        return messageIdCounter++;
    }

    private void formatHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("STATUS", "POST");
        header.put("Message ID", messageId);
        header.put("Username", sender.getUsername());
        header.put("Subject", subject);
        header.put("Date and Time", date);
        header.put("Body", message);
        header.put("Group ID", sender.getCurrentGroup());

        // Dump the header to be sent
        String jsonHeader = toJson(header);
        System.out.println(jsonHeader);
    }

    private void updateDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = dateFormat.format(new Date());
        formatHeader();
    }

    private String toJson(Map<String, Object> map) {
        StringBuilder json = new StringBuilder("{");
        boolean first = true;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (!first) {
                json.append(", ");
            }
            json.append("\"").append(entry.getKey()).append("\": ");
            if (entry.getValue() instanceof String) {
                json.append("\"").append(entry.getValue()).append("\"");
            } else {
                json.append(entry.getValue());
            }
            first = false;
        }
        json.append("}");
        return json.toString();
    }

    public void printMessage() {
        if (subject == null || subject.isEmpty()) {
            subject = "[No Subject]";
        }
        System.out.printf("Message ID:    %d%n" +
                          "From:          %s%n" +
                          "Subject:       %s%n" +
                          "On:            %s%n%n" +
                          "%s%n",
                          messageId, sender.getUsername(), subject, date, message);
    }
}