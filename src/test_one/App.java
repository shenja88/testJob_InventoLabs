package test_one;

import java.net.UnknownHostException;
import java.util.List;

/**
 * Testing of IP address conversion 3 ways to int and 3 ways back to string.
 * After start application:
        1) IP-address convert 1 way from 'StringProceedingType' to int;
        2) Result previous operation convert back to string 3 ways from 'IntProceedingType';
        3) After each operation (int -> string) is a check of result with the original IP;
*/

public class App {

    public static void main(String[] args) throws UnknownHostException {
        IpV4ConverterUtil util = new IpV4ConverterUtil();
        List<String> ipList = List.of(
                "128.32.10.0",
                "0.0.0.255",
                "127.0.0.1",
                "231.251.1.4",
                "101.32.11.0",
                "0.0.0.0");

        ipList.forEach(ipS -> {
            System.out.println(String.format("========================== Converting ip-address: %s. ==============================", ipS));
            for (StringProceedingType typeS : StringProceedingType.values()) {
                System.out.print("\nStart converting string to int32.");
                int ipI = util.proceedToInt(ipS, typeS);

                System.out.println("\nStart converting int32 to string.");
                for (IntProceedingType typeI : IntProceedingType.values()) {
                    String result = util.proceedToString(ipI, typeI);
                    System.out.println(String.format("Successful operation: %s.", ipS.equals(result)));
                }
            }
        });
    }
}
