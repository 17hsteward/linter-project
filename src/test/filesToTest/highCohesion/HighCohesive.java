package highCohesion;

/**
 * copied from https://www.javaguides.net/2018/08/cohesion-in-java-with-example.html
 *
 * In this example, the purpose of MyReader class is to read the resource and it does
 * that only. It does not implement other unrelated things. Hence it is highly cohesive.
 */

public class HighCohesive {
    // -------------- functions related to read resource
    // read the resource from disk
    public String readFromDisk(String fileName) {
        return "reading data of " + fileName;
    }

    // read the resource from web
    public String readFromWeb(String url) {
        return "reading data of " + url;
    }

    // read the resource from network
    public String readFromNetwork(String networkAddress) {
        return "reading data of " + networkAddress;
    }
}
