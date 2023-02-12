package utils;

/**
 * This is a generator that generate a html respons
 *
 * @author Ole Kristian
 * @author Håvard Vestbø
 * @version 1.0
 */
public class ResponseGenerator {

    /**
     * Generate a html response
     * @param serverText the server text
     * @param time1 start time
     * @param time2 end time
     * @return the body
     */
    public static String generatorResponseHTML(String serverText, long time1, long time2) {
        return ("<html><body>" +
                "Singlethreaded Server: " +
                time1 + " - " + time2 +
                "</body></html>");
    }

    /**
     * Generate header response
     * @param contentLength lenght of the content
     * @return the header.
     */
    public static String generatorResponseHeader(int contentLength) {
        return ("HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html; charset=UTF-8\r\n" +
                "Content-Length: " + contentLength +
                "\r\n\r\n");
    }
}